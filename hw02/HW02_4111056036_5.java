/*
 * 思路: 將 4Sum 化簡為 2Sum 的問題
 * 問題: 解決 2Sum 中子集合重複的問題
 * 方式: 只取符合正排序位置的組合
 */

 class TableNode {
	/*
	 * position: 組合中後面點的位置
	 * value:    紀錄所有小於該點目前的總數
	 */
    int position, value;
    TableNode next;

    public TableNode(int position) {
        this.position = position;
        this.value = 0;
        this.next = null;
    }
}

class SumTable {
    int tableSize;

    /*
	 * 用來儲存相對應 key 值的矩陣
	 * Head Node 儲存鏈結訊息 -> TableNode{ position: 真正的 key 值, value: 沒用 }
	 * Body Node 儲存和為 sum 的組合訊息
	 */
    TableNode[] table;

    public SumTable(int tableSize) {
        this.table = new TableNode[this.tableSize = tableSize];
    }

	// 使用模數將傳入的任何數字轉為 0 ~ tableSize 間的數字
    public int hash(int key) {
        int hashKey = ((key % tableSize) + tableSize) % tableSize;

		// 解決 key 的碰撞問題 -> 如果矩陣已經存在鏈結且 sum 不同，則往後移
        while (table[hashKey] != null && table[hashKey].position != key) {
            hashKey = (hashKey + 1) % tableSize;
        }

        return hashKey;
    }

    public void put(int sum, int endPosition) {
		// 找到對應的鏈結 -> 具有相同的 sum
        TableNode head = table[hash(sum)];

		// 如果還沒有任何組合為該 sum
        if (head == null) {
            head = table[hash(sum)] = new TableNode(sum);
        }

		// 沒用
        head.value++;

		// 以組合後面元素的位置進行插入 -> 應該會由小到大
        for (TableNode node = head; ; node = node.next) {
			// 如果遇上相同的位置，則該節點的 count + 1
            if (node != head && node.position == endPosition) {
                node.value++;
                return;
            }
            else if (node.next == null || node.next.position > endPosition) {
                TableNode newNode = new TableNode(endPosition);
                newNode.next = node.next;
                node.next = newNode;
                newNode.value++;
                return;
            }
        }
    }

    public int count(int sum, int startPosition) {
		// 找到對應的鏈結 -> 具有相同的 sum
        TableNode head = table[hash(sum)];

		// 如果還沒有任何組合為該 sum，則返回 0
        if (head == null) {
            return 0;
        }

        int acc = 0;

		// 從頭開始查找，直到 startPosition 小於節點的位置
        for (TableNode node = head.next; node != null; node = node.next) {
            if (node.position >= startPosition) {
                break;
            }
            acc += node.value;
        }

        return acc;
    }

    public boolean containsKey(int key) {
        return table[hash(key)] != null;
    }
}

public class HW02_4111056036_5 extends FourSum {
    @Override
    public int F_sum(int[] A) {
        int count = 0;

        SumTable table = new SumTable(500000); // 25000

        java.util.Arrays.sort(A);

        for (int i = 0; i < A.length; ++i) {
            for (int j = i + 1; j < A.length; ++j) {
                int sum = A[i] + A[j];

                if (sum >= 0) {
                    count += table.count(-sum, i);
                }

                if (sum <= 0) {
                    table.put(sum, j);
                }
            }
        }

        return count;
    }
}
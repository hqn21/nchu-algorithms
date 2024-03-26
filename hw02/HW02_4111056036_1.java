class Node {
    int id, value;
    Node next;

    public Node(int id) {
        this.id = id;
        this.value = 0;
        this.next = null;
    }
}

class HashTable {
    int size;
    Node[] nodes;

    public HashTable(int size) {
        this.nodes = new Node[this.size = size];
    }

    public int hash(int key) {
        int hashKey = ((key % size) + size) % size;
        while (nodes[hashKey] != null && nodes[hashKey].id != key) {
            hashKey = (hashKey + 1) % size;
        }

        return hashKey;
    }

    public void put(int sum, int id) {
        int key = hash(sum);
        Node head = nodes[key];

        if(head == null) {
            head = nodes[key] = new Node(sum);
        }

        for (Node node = head; ; node = node.next) {
            if (node != head && node.id == id) {
                ++node.value;
                return;
            }
            else if (node.next == null || node.next.id > id) {
                Node newNode = new Node(id);
                newNode.next = node.next;
                node.next = newNode;
                ++newNode.value;
                return;
            }
        }
    }

    public int count(int sum, int startId) {
        Node head = nodes[hash(sum)];

        if(head == null) {
            return 0;
        }

        int count = 0;

        for (Node node = head.next; node != null; node = node.next) {
            if (node.id >= startId) {
                break;
            }
            count += node.value;
        }

        return count;
    }
}

public class HW02_4111056036_1 extends FourSum {
    public HW02_4111056036_1() {

    }

    @Override
    public int F_sum(int[] A) {
        int ans = 0, n = A.length, sum;
        HashTable table = new HashTable(25000);
        java.util.Arrays.sort(A);

        for(int i = 0; i < n - 1; ++i) {
            for(int j = i + 1; j < n; ++j) {
                sum = A[i] + A[j];
                if(sum >= 0) {
                    ans += table.count(-sum, i);
                }
                if(sum <= 0) {
                    table.put(sum, j);
                }
            }
        }
        
        return ans;
    }
}

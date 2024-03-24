class Node {
    int id, value;
    Node next;

    Node(int id, Node next) {
        this.id = id;
        this.value = 1;
        this.next = next;
    }
}

class HashTable {
    int size;
    Node[] nodes;
    int[] sums;

    HashTable(int size) {
        this.size = size;
        this.nodes = new Node[size];
        this.sums = new int[size];
    }

    int hash(int key) {
        int hashKey = ((key % size) + size) % size;
        while (nodes[hashKey] != null && sums[hashKey] != key) {
            hashKey = (hashKey + 1) % size;
        }
        return hashKey;
    }

    void put(int sum, int id) {
        int key = hash(sum);
        Node head = nodes[key];

        if(head == null) {
            nodes[key] = new Node(id, null);
            sums[key] = sum;
            return;
        }

        if(head.id > id) {
            nodes[key] = new Node(id, head);
            return;
        }

        while(head != null) {
            if(head.id == id) {
                head.value++;
                return;
            } else if(head.next == null || head.next.id > id) {
                Node newNode = new Node(id, head.next);
                head.next = newNode;
                return;
            }
            head = head.next;
        }
    }

    int count(int sum, int startId) {
        int count = 0;
        Node head = nodes[hash(sum)];

        while(head != null) {
            if(head.id >= startId) {
                break;
            }
            count += head.value;
            head = head.next;
        }

        return count;
    }
}

public class HW02_4111056036_1 extends FourSum {
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

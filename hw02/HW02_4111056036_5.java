public class HW02_4111056036_5 extends FourSum {
    class Node {
        int sum, value;
    
        public Node(int sum) {
            this.sum = sum;
            this.value = 1;
        }
    }
    
    class HashTable {
        int size;
        Node[] nodes;
    
        public HashTable(int size) {
            this.nodes = new Node[this.size = size];
        }
    
        public int hash(int sum) {
            int hashKey = ((sum % size) + size) % size;
            while (nodes[hashKey] != null && nodes[hashKey].sum != sum) {
                hashKey = (hashKey + 1) % size;
            }
            return hashKey;
        }
    
        public void add(int sum) {
            int key = hash(sum);
            Node node = nodes[key];
    
            if(node == null) {
                nodes[key] = new Node(sum);
                return;
            }
    
            ++nodes[key].value;
        }
    
        public int get(int sum) {
            int key = hash(sum);
            Node node = nodes[key];
            return node == null ? 0 : node.value;
        }
    }

    public HW02_4111056036_5() {
        
    }

    @Override
    public int F_sum(int[] A) {
        int ans = 0;
        int n = A.length;
        HashTable table = new HashTable(25000);
        int sum;
        java.util.Arrays.sort(A);
        for(int i = 0; i < n - 1; ++i) {
            for(int j = n - 1; j > i; --j) {
                sum = A[i] + A[j];
                if(sum < 0) {
                    break;
                }
                ans += table.get(sum);
            }

            for(int j = 0; j < i; ++j) {
                sum = A[i] + A[j];
                if(sum > 0) {
                    break;
                }
                table.add(-sum);
            }
        }

        return ans;
    }
}
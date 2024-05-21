public class HW02_4111056036_4_comment extends FourSum {
    public HW02_4111056036_4_comment() {
        
    }

    @Override
    public int F_sum(int[] A) {
        int n = A.length;
        int ans = 0;
        int sum;
        HashMap<Integer, Position[]> map = new HashMap<>(n * (n - 1) / 2);
        Position[] result;
        Position[] data;
        int target;
        int targetCount;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                sum = A[i] + A[j];
                result = map.get(-sum);
                if(result == null) { // 負值不存在
                    result = map.get(sum);
                    if(result == null) { // sum 不存在
                        data = new Position[1];
                        data[0] = new Position(i, j);
                    } else { // sum 存在
                        data = new Position[result.length + 1];
                        System.arraycopy(result, 0, data, 0, result.length);
                        data[result.length] = new Position(i, j);
                    }
                    map.put(sum, data);
                } else { // 負值存在
                    target = -1;
                    targetCount = 0;
                    for(int k = 0; k < result.length; k++) {
                        if((result[k].i != i && result[k].j != i && result[k].i != j && result[k].j != j) &&
                            (result[k].i != j && result[k].j != j && result[k].i != i && result[k].j != i)) {
                            target = k;
                            targetCount++;
                        }
                    }
                    if(target != -1) { // 有找到
                        result = map.get(sum);
                        if(result == null) { // sum 不存在
                            data = new Position[1];
                            data[0] = new Position(i, j);
                        } else { // sum 存在
                            data = new Position[result.length + 1];
                            System.arraycopy(result, 0, data, 0, result.length);
                            data[result.length] = new Position(i, j);
                        }
                        map.put(sum, data);
                        ans += targetCount;
                    } else { // 沒找到
                        result = map.get(sum);
                        if(result == null) { // sum 不存在
                            data = new Position[1];
                            data[0] = new Position(i, j);
                        } else { // sum 存在
                            data = new Position[result.length + 1];
                            System.arraycopy(result, 0, data, 0, result.length);
                            data[result.length] = new Position(i, j);
                        }
                        map.put(sum, data);
                    }
                }
            }
        }
        
        return ans / 3;
    }

    private class Position {
        int i;
        int j;

        private Position(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private class HashMap<K, V> {

        private Node<K, V>[] table;
    
        @SuppressWarnings("unchecked")
        public HashMap(int capacity) {
            this.table = new Node[capacity];
        }
    
        public void put(K key, V value) {
            int index = hash(key);
            Node<K, V> node = new Node<>(key, value);
            node.next = table[index];
            table[index] = node;
        }
    
        public V get(K key) {
            int index = hash(key);
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
            return null;
        }
    
        public void remove(K key) {
            int index = hash(key);
            Node<K, V> prev = null;
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    if (prev == null) {
                        table[index] = node.next;
                    } else {
                        prev.next = node.next;
                    }
                    break;
                }
                prev = node;
                node = node.next;
            }
        }
    
        private int hash(K key) {
            return key.hashCode() & (table.length - 1);
        }
    
        private class Node<K, V> {
    
            private K key;
            private V value;
            private Node<K, V> next;
    
            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}

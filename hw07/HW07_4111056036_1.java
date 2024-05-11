public class HW07_4111056036_1 extends LSD {
    private class HashMap<K, V> {
        private static final int DEFAULT_CAPACITY = 16;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;
        public Node<K, V>[] table;
        private int size;
    
        @SuppressWarnings("unchecked")
        public HashMap() {
            this.table = new Node[DEFAULT_CAPACITY];
        }
    
        @SuppressWarnings("unchecked")
        private void grew() {
            if ((size + 1) == (table.length * DEFAULT_LOAD_FACTOR)) {
                Node<K, V>[] oldTable = table;
                table = new Node[table.length << 1];
                size = 0;
                for (int i = 0; i < oldTable.length; ++i) {
                    Node<K, V> node = oldTable[i];
                    while (node != null) {
                        put(node.key, node.value);
                        node = node.next;
                    }
                }
            }
        }
    
        public void put(K key, V value) {
            grew();
    
            int hash = hash(key);
            int index = calcIndex(hash);
            Node<K, V> node = table[index];
            Node<K, V> newNode = new Node<>(key, value, null);
            if (node == null) {
                table[index] = newNode;
            } else {
                boolean keyRepeat = false;
                Node<K, V> last = null;
                while (node != null) {
                    if (node.key.equals(key)) {
                        keyRepeat = true;
                        node.value = value;
                        break;
                    } else {
                        last = node;
                        node = node.next;
                    }
                }
                if (!keyRepeat) {
                    last.next = newNode;
                }
            }
    
            ++size;
        }
    
        public V get(K key) {
            int hash = hash(key);
            int index = calcIndex(hash);
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                } else {
                    node = node.next;
                }
            }
            return null;
        }

        public int size() {
            return this.size;
        }
    
        public int hash(Object key) {
            if (key == null) {
                return 0;
            }
    
            int h = key.hashCode();
            h = h ^ (h >>> 16);
    
            return h;
        }
    
        public int calcIndex(int hash) {
            return  (table.length - 1) & hash;
        }
    
        public class Node<E, A> {
            E key;
            A value;
            Node<E, A> next;
    
            public Node(E key, A value, Node<E, A> next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
    }

    private class HashSet<E> {
        public transient HashMap<E, Object> map;
    
        private static final Object PRESENT = new Object();
    
        public HashSet() {
            map = new HashMap<>();
        }
    
        public boolean add(E e) {
            if(map.get(e) != null) {
                return false;
            }
            map.put(e, PRESENT);
            return true;
        }
    
        public int size() {
            return map.size();
        }
    }

    @Override
    public int distance(int[][] array) {
        HashSet<Integer> nodes = new HashSet<>();
        for (int[] edge : array) {
            nodes.add(edge[0]);
            nodes.add(edge[1]);
        }

        int n = nodes.size();
        HashMap<Integer, Integer> nodeToIndex = new HashMap<>();
        int index = 0;

        for(int i = 0; i < nodes.map.table.length; ++i) {
            if(nodes.map.table[i] != null) {
                nodeToIndex.put(nodes.map.table[i].key, index++);
            }
        }

        int[][] dist = new int[n][n];
        int i, j, k;
        for (i = 0; i < n; i++) {
            java.util.Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        int u, v;
        for (int[] edge : array) {
            u = nodeToIndex.get(edge[0]);
            v = nodeToIndex.get(edge[1]);
            dist[u][v] = dist[v][u] = 1;
        }

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int maxDist = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (dist[i][j] != Integer.MAX_VALUE) {
                    maxDist = Math.max(maxDist, dist[i][j]);
                }
            }
        }

        return maxDist;
    }
}
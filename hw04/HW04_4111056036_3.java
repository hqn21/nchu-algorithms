public class HW04_4111056036_3 extends LanguageModel {
    class HashMap<K, V> {
        private static final int DEFAULT_CAPACITY = 16;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private Node<K, V>[] table;
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
                for (int i = 0; i < oldTable.length; i++) {
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
    
            size++;
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
            return size;
        }
    
        private int hash(Object key) {
            if (key == null) {
                return 0;
            }
    
            int h = key.hashCode();
            h = h ^ (h >>> 16);
    
            return h;
        }
    
        private int calcIndex(int hash) {
            return  (table.length - 1) & hash;
        }

        public V getOrDefault(K key, V defaultValue) {
            V value = get(key);
            return (value != null) ? value : defaultValue;
        }
    
        private class Node<E, A> {
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

    @Override
    public String nextPredictToken(String[] A) {
        HashMap<Integer, Integer> record = new HashMap<>();
        int targetLength = A[0].length();
        
        int nowChecking = 0;
        int now = 0;
        int n = A[1].length();
        int max = 0;
        int prev = 0;
        int ansStart = 0, ansEnd = 0;
        boolean insert = false;

        for(int i = 0; i < n; ++i) {
            if(A[1].charAt(i) == ' ') {
                if(insert) {
                    record.put(now, record.getOrDefault(now, 0) + 1);
                    if(record.get(now) > max) {
                        max = record.get(now);
                        ansStart = prev;
                        ansEnd = i;
                    }
                    now = 0;
                    insert = false;
                } else {
                    insert = true;
                    prev = i + 1;
                }
            } else if(insert) {
                now += (int) A[1].charAt(i);
            } else {
                if(nowChecking >= targetLength || (A[1].charAt(i) != A[0].charAt(nowChecking))) {
                    while(i < n && A[1].charAt(i) != ' ') {
                        ++i;
                    }
                    nowChecking = 0;
                } else {
                    ++nowChecking;
                }
            }
        }

        if(insert) {
            record.put(now, record.getOrDefault(now, 0) + 1);
            if(record.get(now) > max) {
                max = record.get(now);
                ansStart = prev;
                ansEnd = n;
            }
        }

        return A[1].substring(ansStart, ansEnd);
    }
}

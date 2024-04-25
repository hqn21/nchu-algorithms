public class HW05_4111056036_2 extends WordChain {
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

    class Data {
        int firstStart;
        int firstEnd;
        float firstP;
        int secondStart;
        int secondEnd;
        float secondP;

        public Data(int firstStart, int firstEnd, float firstP, int secondStart, int secondEnd, float secondP) {
            this.firstStart = firstStart;
            this.firstEnd = firstEnd;
            this.firstP = firstP;
            this.secondStart = secondStart;
            this.secondEnd = secondEnd;
            this.secondP = secondP;
        }
    }

    private HashMap<String, Data> cache = new HashMap<>();

    private Data findTarget(String target, String A) {
        if (cache.get(target) != null) {
            return cache.get(target);
        }

        int[] record = new int[997];
        int targetLength = target.length();
        int nowChecking = 0;
        int now = 0;
        int n = A.length();
        int first = 0, firstStart = 0, firstEnd = 0;
        int second = 0, secondStart = 0, secondEnd = 0;
        int prev = 0;
        int sum = 0;

        for(int i = 0; i < n; ++i) {
            while(i < n && target.charAt(nowChecking) != A.charAt(i)) {
                ++i;
                if(nowChecking > 0) {
                    nowChecking = 0;
                }
            }

            ++nowChecking;

            if(nowChecking == targetLength) {
                if(++i < n && A.charAt(i) == ' ') {
                    ++sum;
                    prev = ++i;
                    while(i < n && A.charAt(i) != ' ') {
                        now += A.charAt(i);
                        ++i;
                    }
                    now %= 997;
                    if(++record[now] > first) {
                        first = record[now];
                        firstStart = prev;
                        firstEnd = i;
                    } else if(record[now] > second) {
                        second = record[now];
                        secondStart = prev;
                        secondEnd = i;
                    }
                    now = 0;
                }
                nowChecking = 0;
            }
        }

        Data result = new Data(firstStart, firstEnd, (float) first / sum, secondStart, secondEnd, (float) second / sum);
        cache.put(target, result);
        return result;
    }

    @Override
    public String sequenceProbability(String[] A) {
        Data d1 = findTarget(A[0], A[1]);
        Data d2_1 = findTarget(A[1].substring(d1.firstStart, d1.firstEnd), A[1]);
        Data d2_2 = findTarget(A[1].substring(d1.secondStart, d1.secondEnd), A[1]);

        Data d3;
        if(d1.firstP * d2_1.firstP > d1.secondP * d2_2.firstP) {
            d3 = findTarget(A[1].substring(d2_1.firstStart, d2_1.firstEnd), A[1]);
            return A[0] + " " + A[1].substring(d1.firstStart, d1.firstEnd) + " " + A[1].substring(d2_1.firstStart, d2_1.firstEnd) + " " + A[1].substring(d3.firstStart, d3.firstEnd);
        } else {
            d3 = findTarget(A[1].substring(d2_2.firstStart, d2_2.firstEnd), A[1]);
            return A[0] + " " + A[1].substring(d1.secondStart, d1.secondEnd) + " " + A[1].substring(d2_2.firstStart, d2_2.firstEnd) + " " + A[1].substring(d3.firstStart, d3.firstEnd);
        }
    }
}
public class HW05_4111056036_4 extends WordChain {
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
        String firstString;
        float firstP;
        String secondString;
        float secondP;

        public Data(String firstString, float firstP, String secondString, float secondP) {
            this.firstString = firstString;
            this.firstP = firstP;
            this.secondString = secondString;
            this.secondP = secondP;
        }
    }

    private HashMap<String, Data> cache = new HashMap<>();

    private Data findTarget(String target, String A, boolean checkSecond) {
        Data result = cache.get(target);
        if (result != null) {
            return result;
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
                    } else if(checkSecond && (record[now] > second)) {
                        second = record[now];
                        secondStart = prev;
                        secondEnd = i;
                    }
                    now = 0;
                }
                nowChecking = 0;
            }
        }

        if(checkSecond) {
            result =  new Data(A.substring(firstStart, firstEnd), (float) first / sum, A.substring(secondStart, secondEnd), (float) second / sum);
        } else {
            result = new Data(A.substring(firstStart, firstEnd), (float) first / sum, "", 0);
        }

        cache.put(target, result);
        return result;
    }

    @Override
    public String sequenceProbability(String[] A) {
        Data d1 = findTarget(A[0], A[1], true);
        Data d2_1 = findTarget(d1.firstString, A[1], false);
        Data d2_2 = findTarget(d1.secondString, A[1], false);

        Data d3;
        if(d1.firstP * d2_1.firstP > d1.secondP * d2_2.firstP) {
            d3 = findTarget(d2_1.firstString, A[1], false);
            return A[0] + " " + d1.firstString + " " + d2_1.firstString + " " + d3.firstString;
        } else {
            d3 = findTarget(d2_2.firstString, A[1], false);
            return A[0] + " " + d1.firstString + " " + d2_2.firstString + " " + d3.firstString;
        }
    }
}
public class HW05_4111056036_5 extends WordChain {
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

    private static class Position {
        int start;
        int end;
        float probability;

        public Position(int start, int end, float probability) {
            this.start = start;
            this.end = end;
            this.probability = probability;
        }
    }

    private static Position greedyFind(String target, String A) {
        int[] record = new int[128];
        int start = A.indexOf(target);
        int end;
        int targetLength = target.length();
        int dataId = 0;
        int max = 0;
        int amount = 0;
        Position position = new Position(0, 0, 0);

        while(start != -1) {
            ++amount;
            start += targetLength;
            end = A.indexOf(" ", start + 1);
            if(end == -1) {
                break;
            }
            for(int i = start; i <= end; ++i) {
                dataId += A.charAt(i);
            }
            dataId &= 127;
            if(++record[dataId] > max) {
                max = record[dataId];
                position.start = start;
                position.end = end;
            }
            dataId = 0;
            start = A.indexOf(target, start);
        }
        position.probability = (float) max / amount;
        return position;
    }

    private static void simpleFind(StringBuilder sb, String A, String[] targets, int targetAmount, float probability, float maxProbability, int level) {

    }

    @Override
    public String sequenceProbability(String[] A) {
        StringBuilder sb = new StringBuilder();
        String target = A[0] + " ", first, second;
        int targetLength = target.length();

        int[] record = new int[128];
        int start = A[1].indexOf(target);
        int end;
        int dataId = 0;
        int max = 0;
        int amount = 0;
        Position position = new Position(0, 0, 0);
        Position[] targets = new Position[100];
        HashMap<Integer, Integer> targetDatas = new HashMap<Integer, Integer>();
        float[] probabilities = new float[100];

        while(start != -1) {
            start += targetLength;
            end = A[1].indexOf(" ", start + 1);
            if(end == -1) {
                break;
            }
            // targets[amount] = 
            // ++amount;
            for(int i = start; i <= end; ++i) {
                dataId += A[1].charAt(i);
            }
            dataId &= 127;
            if(++record[dataId] > max) {
                max = record[dataId];
                position.start = start;
                position.end = end;
            }
            dataId = 0;
            start = A[1].indexOf(target, start);
        }
        position.probability = (float) max / amount;

        float maxProbability = position.probability;
        first = A[1].substring(position.start, position.end) + " ";
        position = greedyFind(first, A[1]);
        maxProbability *= position.probability;
        second = A[1].substring(position.start, position.end) + " ";
        position = greedyFind(second, A[1]);
        maxProbability *= position.probability;
        sb.append(target);
        sb.append(first);
        sb.append(second);
        sb.append(A[1].substring(position.start, position.end));
        // simpleFind(sb, A[1], targets, amount, maxProbability, 1, 1);
        return sb.toString();
    }
}
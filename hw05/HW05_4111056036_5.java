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

    static class Node {
        String word;
        double frequency;

        Node next;

        Node(String word, double frequency) {
            this.word = word;
            this.frequency = frequency;
        }
 
        public void connect(String word) {
            frequency += 1;

            Node currentNode = next;
            while (currentNode != null) {
                if (currentNode.word.equals(word)) {
                    currentNode.frequency += 1;
                    return;
                }
                currentNode = currentNode.next;
            }

            Node newNode = new Node(word, 1);

            newNode.next = next;
            next = newNode;
        }
    }

    HashMap<String, Node> nodes = new HashMap<>();

    String sequence, initialSequence;
    int sequenceLength;

    String[] path = new String[4];
    double maxProbability = 0;

    public void depthFirstSearch(String current, double probability, int depth) {
        if (depth == 0) {
            if (probability > maxProbability) {
                maxProbability = probability;
            }

            return;
        }

        Node sourceNode = nodes.get(current);

        if (sourceNode == null) {
            sourceNode = new Node(current, 0);
            nodes.put(current, sourceNode);

            for (int i = sequence.indexOf(current); i != -1 && i + 5 <= sequenceLength; i = sequence.indexOf(current, i + 1)) {
                sourceNode.connect(sequence.substring(i + 3, i + 5));
            }
        }

        for (Node next = sourceNode.next; next != null; next = next.next) {
            double nextProbability = probability * next.frequency / sourceNode.frequency;
            if (nextProbability <= maxProbability) {
                continue;
            }

            path[4 - depth] = next.word;
            depthFirstSearch(next.word, nextProbability, depth - 1);
        }
    }

    @Override
    public String sequenceProbability(String[] A) {
        initialSequence = A[0];

        sequence = " " + A[1];
        sequenceLength = sequence.length();

        path[0] = initialSequence;
        depthFirstSearch(initialSequence, 1, 3);

        return String.join(" ", path);
    }
}
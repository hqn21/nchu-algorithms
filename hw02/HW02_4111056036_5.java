public class HW02_4111056036_5 extends FourSum {
    public HW02_4111056036_5() {
        
    }

    private class Position {
        int i;
        int j;

        private Position(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public class HashMap<K, V> {
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
            Node<K, V> newNode = new Node<>(hash, key, value, null);
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

        public V remove(K key) {
            int hash = hash(key);
            int index = calcIndex(hash);
            Node<K, V> node = table[index];
            if (node != null && node.key.equals(key)) {
                Node<K, V> next = node.next;
                table[index] = next;
                size--;
                return node.value;
            } else {
                Node<K, V> pre = node;
                node = node.next;
                while (node != null) {
                    if (node.key.equals(key)) {
                        Node<K, V> next = node.next;
                        pre.next = next;
                        size--;
                        return node.value;
                    } else {
                        pre = node;
                        node = node.next;
                    }
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

        private class Node<K, V> {
            int hash;
            K key;
            V value;
            Node<K, V> next;

            public Node(int hash, K key, V value, Node<K, V> next) {
                this.hash = hash;
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
    }

    public class List<E> {
        private static final int DEFAULT_CAPACITY = 10;
        private final Object[] DEFAULT_EMPTY_ELEMENTDATA = {};
        private Object[] elementData;
        private int size;

        public List() {
            elementData = DEFAULT_EMPTY_ELEMENTDATA;
        }

        public List(int originalSize) {
            if (originalSize <= 0) {
                elementData = DEFAULT_EMPTY_ELEMENTDATA;
            } else {
                elementData = new Object[originalSize];
            }
        }

        public void add(E e) {
            if (size == 0) {
                elementData = new Object[DEFAULT_CAPACITY];
            } else {
                grew(size + 1);
            }
            elementData[size++] = e;
        }

        public void add(int index, E e) {
            if (index > size || index < 0) {
                throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
            }

            if (size == 0) {
                elementData = new Object[DEFAULT_CAPACITY];
            } else {
                grew(size + 1);
                System.arraycopy(elementData, index, elementData, index + 1, size - index);
            }
            elementData[index] = e;
            size++;
        }

        private void grew(int minSize) {
            int oldSize = elementData.length;
            if (minSize - oldSize > 0) {
                int newSize = oldSize + (oldSize >> 1);
                Object[] newArray = new Object[newSize];
                System.arraycopy(elementData, 0, newArray, 0, Math.min(elementData.length, newSize));
                elementData = newArray;
            }
        }

        @SuppressWarnings("unchecked")
        public E get(int index) {
            if (index > size || index < 0) {
                throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
            }

            return (E) elementData[index];
        }

        @SuppressWarnings("unchecked")
        public E remove(int index) {
            if (index > size || index < 0) {
                throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
            }
            Object data = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);

            size--;

            return (E) data;
        }

        public int size() {
            return size;
        }
    }

    @Override
    public int F_sum(int[] A) {
        HashMap<Integer, List<Position>> map = new HashMap<>();
        int n = A.length;
        int ans = 0;
        List<Position> positions;
        Position pair;
        int sum;
    
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                sum = A[i] + A[j];
                positions = map.get(-sum);
                if (positions != null) {
                    for (int k = 0; k < positions.size(); k++) {
                        pair = positions.get(k);
                        if (pair.i != i && pair.i != j && pair.j != i && pair.j != j) {
                            ans++;
                        }
                    }
                }
            }
    
            for (int k = 0; k < i; k++) {
                sum = A[i] + A[k];
                if (map.get(sum) == null) {
                    map.put(sum, new List<Position>());
                }
                map.get(sum).add(new Position(k, i));
            }
        }
    
        return ans;
    }
}
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

class DataNode {
    int id;
    DataNode next;
    DataNode last;

    public DataNode(int id, DataNode next, DataNode last) {
        this.id = id;
        this.next = next;
        this.last = last;
    }

    public void put(int id) {
        DataNode newDataNode = new DataNode(id, null, null);
        last.next = newDataNode;
    }
}

public class HW02_4111056036_4 extends FourSum {
    @Override
    public int F_sum(int[] A) {
        int ans = 0;
        int n = A.length;
        java.util.Arrays.sort(A);

        HashMap<Integer, DataNode> table = new HashMap<Integer, DataNode>();

        int sum;
        DataNode dataNode;
        for(int i = 0; i < n - 1; ++i) {
            for(int j = i + 1; j < n; ++j) {
                sum = A[i] + A[j];
                if(sum >= 0) {
                    dataNode = table.get(-sum);
                    while(dataNode != null) {
                        if(dataNode.id >= i) {
                            dataNode = dataNode.next;
                            continue;
                        } else {
                            ans++;
                        }
                        dataNode = dataNode.next;
                    }
                }

                if(sum <= 0) {
                    dataNode = table.get(sum);
                    if(dataNode == null) {
                        DataNode newDataNode = new DataNode(j, null, null);
                        newDataNode.last = newDataNode;
                        table.put(sum, newDataNode);
                    } else {
                        DataNode newNode = new DataNode(j, null, null);
                        dataNode.last.next = newNode;
                        dataNode.last = newNode;
                    }
                }
            }
        }

        return ans;
    }
}
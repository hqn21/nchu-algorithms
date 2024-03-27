public class HW04_4111056036_1 extends LanguageModel {
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
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int max = 0;
        String ans = "";
        String temp = "";
        boolean insert = false;
        for(int i = 0; i < A[1].length(); ++i) {
            if(A[1].charAt(i) == ' ') {
                if(temp.equals(A[0])) {
                    temp = "";
                    insert = true;
                    continue;
                }
                if(!insert) {
                    temp = "";
                    continue;
                }

                Integer data = map.get(temp);
                if(data == null) {
                    data = 0;
                }
                data += 1;
                map.put(temp, data);
                if(data > max) {
                    ans = temp;
                    max = data;
                }
                temp = "";
                insert = false;
                continue;
            }
            temp += A[1].charAt(i);
        }

        if(insert && !temp.equals(A[0])) {
            Integer data = map.get(temp);
            if(data == null) {
                data = 0;
            }
            data += 1;

            if(data > max) {
                ans = temp;
                max = data;
            }
        }

        return ans;
    }
}

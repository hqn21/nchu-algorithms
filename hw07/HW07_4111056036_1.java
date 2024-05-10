import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

public class HW07_4111056036_1 extends LSD {
    private int maxDist;

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

    class Graph {
        private HashMap<Integer, ArrayList<Integer>> adjacencyList;

        public Graph() {
            this.adjacencyList = new HashMap<Integer, ArrayList<Integer>>();
        }

        public void addEdge(int from, int to) {
            ArrayList<Integer> data = this.adjacencyList.get(from);

            if(data == null) {
                data = new ArrayList<>();
            }
            data.add(to);
            this.adjacencyList.put(from, data);

            data = this.adjacencyList.get(to);
            if(data == null) {
                data = new ArrayList<>();
            }
            data.add(from);
            this.adjacencyList.put(to, data);
        }

        public Iterable<Integer> adjacencyList(int id) {
            ArrayList<Integer> adjList = this.adjacencyList.get(id);
            if (adjList == null) {
                return Collections.emptyList();
            }
            return adjList;
        }
    }

    private void bfs(Graph graph, int s) {
        Stack<Integer> currentLevel = new Stack<>();
        Stack<Integer> nextLevel = new Stack<>();
        Stack<Integer> temp;
        HashMap<Integer, Boolean> marked = new HashMap<Integer, Boolean>();
        HashMap<Integer, Integer> edgeTo = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> distTo = new HashMap<Integer, Integer>();
        currentLevel.push(s);
        marked.put(s, true);
        distTo.put(s, 0);
        
        int v;
        while(!currentLevel.isEmpty()) {
            v = currentLevel.pop();
            for(int w : graph.adjacencyList(v)) {
                if(marked.get(w) == null) {
                    nextLevel.push(w);
                    marked.put(w, true);
                    edgeTo.put(w, v);
                    distTo.put(w, distTo.getOrDefault(v, 0) + 1);
                    if(distTo.get(w) > maxDist) {
                        maxDist = distTo.get(w);
                    }
                }
            }
            if(currentLevel.isEmpty()) {
                temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
    }

    @Override
    public int distance(int[][] array) {
        maxDist = 0;
        Graph graph = new Graph();
        int n = array.length;

        for(int i = 0; i < n; ++i) {
            graph.addEdge(array[i][0], array[i][1]);
        }

        for(int[] edge : array) {
            for(int node : edge) {
                bfs(graph, node);
            }
        }

        return maxDist;
    }
}
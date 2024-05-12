import java.util.ArrayList;
import java.util.Collections;

public class HW07_4111056036_3 extends LSD {
    private int maxDist;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    private class HashMap<K, V> {
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

    private class LinkedList<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;
    
        private class Node<K> {
            private K data;
            private Node<K> next;
    
            public Node(K data, Node<K> next) {
                this.data = data;
                this.next = next;
            }
        }
    
        public LinkedList() {
            head = null;
            tail = null;
            size = 0;
        }
    
        public void addLast(T item) {
            Node<T> newNode = new Node<T>(item, null);
            if (tail == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
            size++;
        }
    
        public T removeFirst() {
            if (head == null) {
                return null;
            }
            T item = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return item;
        }
    
        public boolean isEmpty() {
            return size == 0;
        }
    }

   private class Queue<T> {
        private LinkedList<T> list = new LinkedList<T>();
    
        public void enqueue(T item) {
            list.addLast(item);
        }
    
        public T dequeue() {
            if (list.isEmpty()) {
                return null;
            }
            return list.removeFirst();
        }
    
        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

    private class Graph {
        private HashMap<Integer, Integer> mapping;
        private ArrayList<ArrayList<Integer>> adjacencyList;
        public int size;

        public Graph() {
            this.mapping = new HashMap<Integer, Integer>();
            this.adjacencyList = new ArrayList<>();
            this.size = 0;
        }

        public void addEdge(int from, int to) {
            Integer fromId = mapping.get(from);
            Integer toId = mapping.get(to);

            if(fromId == null) {
                fromId = size;
                this.mapping.put(from, fromId);
                this.adjacencyList.add(new ArrayList<Integer>());
                ++size;
            }

            if(toId == null) {
                toId = this.size;
                this.mapping.put(to, toId);
                ++this.size;
                this.adjacencyList.add(new ArrayList<Integer>());
            }

            this.adjacencyList.get(fromId).add(toId);
            this.adjacencyList.get(toId).add(fromId);
        }

        public Iterable<Integer> adjacencyList(int id) {
            ArrayList<Integer> adjList = this.adjacencyList.get(id);
            if (adjList == null) {
                return Collections.emptyList();
            }
            return adjList;
        }
    }

    private int bfs(Graph graph, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        distTo[s] = 0;
        
        int v;
        int lastNode = s;
        int distToV;
        while(!queue.isEmpty()) {
            v = queue.dequeue();
            lastNode = v;
            distToV = distTo[v] + 1;
            for(int w : graph.adjacencyList(v)) {
                if(marked[w] == false) {
                    queue.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distToV;
                    if(distTo[w] > maxDist) {
                        maxDist = distTo[w];
                    }
                }
            }
        }

        return lastNode;
    }

    @Override
    public int distance(int[][] array) {
        maxDist = 0;
        Graph graph = new Graph();
        int n = array.length;

        for(int i = 0; i < n; ++i) {
            graph.addEdge(array[i][0], array[i][1]);
        }

        n = graph.size;        
        marked = new boolean[n];
        edgeTo = new int[n];
        distTo = new int[n];

        int farthestNode = bfs(graph, 0);

        java.util.Arrays.fill(marked, false);

        bfs(graph, farthestNode);

        return maxDist;
    }
}
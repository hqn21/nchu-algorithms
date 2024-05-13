import java.util.ArrayList;
import java.util.Collections;

public class HW07_4111056036_1 extends LSD {
    private int maxDist;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private HashMap mapping;
    private ArrayList<ArrayList<Integer>> adjacencyList;

    private class HashMap {
        private static final int DEFAULT_CAPACITY = 16;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private Node[] table;
        private int size;
        public int counter = 0;
    
        public HashMap() {
            this.table = new Node[DEFAULT_CAPACITY];
        }
    
        private void grew() {
            if ((size + 1) == (table.length * DEFAULT_LOAD_FACTOR)) {
                Node[] oldTable = table;
                table = new Node[table.length << 1];
                size = 0;
                for (int i = 0; i < oldTable.length; ++i) {
                    Node node = oldTable[i];
                    while (node != null) {
                        put(node.key, node.value);
                        node = node.next;
                    }
                }
            }
        }
    
        private void put(int key, int value) {
            grew();
    
            int hash = hash(key);
            int index = calcIndex(hash);
            Node node = table[index];
            Node newNode = new Node(key, value, null);
            if (node == null) {
                table[index] = newNode;
            } else {
                boolean keyRepeat = false;
                Node last = null;
                while (node != null) {
                    if (node.key == key) {
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
    
        public int get(int key) {
            int hash = hash(key);
            int index = calcIndex(hash);
            Node node = table[index];
            while (node != null) {
                if (node.key == key) {
                    return node.value;
                } else {
                    node = node.next;
                }
            }
            put(key, counter);
            adjacencyList.add(new ArrayList<Integer>());
            ++counter;
            return counter - 1;
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
    
        private class Node {
            int key;
            int value;
            Node next;
    
            public Node(int key, int value, Node next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
    }

    private class Queue {
        private Node head;
        private Node tail;
        private int size;

        private class Node {
            private int data;
            private Node next;
    
            public Node(int data, Node next) {
                this.data = data;
                this.next = next;
            }
        }

        public Queue() {
            head = null;
            tail = null;
            size = 0;
        }

        public void enqueue(int item) {
            Node newNode = new Node(item, null);
            if (tail == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
            ++size;
        }
    
        public Integer dequeue() {
            if(size == 0) {
                return null;
            }

            int item = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            --size;
            return item;
        }
    
        public boolean isEmpty() {
            return size == 0;
        }
    }

    private class Graph {
        public Graph() {
            mapping = new HashMap();
            adjacencyList = new ArrayList<>();
        }

        public void addEdge(int from, int to) {
            int fromId = mapping.get(from);
            int toId = mapping.get(to);
            adjacencyList.get(fromId).add(toId);
            adjacencyList.get(toId).add(fromId);
        }

        public Iterable<Integer> adjacencyList(int id) {
            ArrayList<Integer> adjList = adjacencyList.get(id);
            if (adjList == null) {
                return Collections.emptyList();
            }
            return adjList;
        }
    }

    private int bfs(Graph graph, int s) {
        Queue queue = new Queue();
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

        n = mapping.counter;        
        marked = new boolean[n];
        edgeTo = new int[n];
        distTo = new int[n];

        int farthestNode = bfs(graph, 0);

        java.util.Arrays.fill(marked, false);
        java.util.Arrays.fill(edgeTo, 0);
        java.util.Arrays.fill(distTo, 0);

        bfs(graph, farthestNode);

        return maxDist;
    }
}
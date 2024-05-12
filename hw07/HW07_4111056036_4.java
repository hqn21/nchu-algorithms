import java.util.ArrayList;
import java.util.Collections;

public class HW07_4111056036_4 extends LSD {
    private int maxDist;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private ArrayList<ArrayList<Integer>> adjacencyList;
    
    private class HashTable {
        public int counter;
        private int size;
        private Node[] nodes;
    
        public HashTable(int size) {
            this.nodes = new Node[this.size = size];
            this.counter = 0;
        }
    
        public int hash(int key) {
            int hashKey = ((key % size) + size) % size;
            while (nodes[hashKey] != null && nodes[hashKey].id != key) {
                hashKey = (hashKey + 1) % size;
            }
    
            return hashKey;
        }

        public int get(int id) {
            int key = hash(id);
            Node head = nodes[key];
            if(head == null) {
                nodes[key] = new Node(id, this.counter);
                ++this.counter;
                adjacencyList.add(new ArrayList<Integer>());
                return this.counter - 1;
            }
            return head.value;
        }

        private class Node {
            int id, value;
        
            public Node(int id, int value) {
                this.id = id;
                this.value = value;
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
        private HashTable mapping;

        public Graph() {
            this.mapping = new HashTable(5000);
            adjacencyList = new ArrayList<>();
        }

        public void addEdge(int from, int to) {
            int fromId = mapping.get(from);
            int toId = mapping.get(to);
            adjacencyList.get(fromId).add(toId);
            adjacencyList.get(toId).add(fromId);
        }

        public int getCounter() {
            return this.mapping.counter;
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

        n = graph.getCounter();        
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
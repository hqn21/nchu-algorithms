import java.util.ArrayList;
import java.util.Collections;

public class HW07_4111056036_2 extends LSD {
    private int maxDist;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    
    private class HashTable {
        private int size;
        private Node[] nodes;
    
        public HashTable(int size) {
            this.nodes = new Node[this.size = size];
        }
    
        public int hash(int key) {
            int hashKey = ((key % size) + size) % size;
            while (nodes[hashKey] != null && nodes[hashKey].id != key) {
                hashKey = (hashKey + 1) % size;
            }
    
            return hashKey;
        }
    
        public void put(int id, int value) {
            int key = hash(id);
            Node head = nodes[key];
            if(head != null) {
                return;
            }
            nodes[key] = new Node(id, value);
        }

        public Integer get(int id) {
            int key = hash(id);
            Node head = nodes[key];
            if(head == null) {
                return null;
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
        private ArrayList<ArrayList<Integer>> adjacencyList;
        public int size;

        public Graph() {
            this.mapping = new HashTable(2500);
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
        java.util.Arrays.fill(edgeTo, 0);
        java.util.Arrays.fill(distTo, 0);

        bfs(graph, farthestNode);

        return maxDist;
    }
}
import java.util.ArrayList;

public class HW07_4111056036_2 extends LSD {
    private int maxDist;
    private boolean[] marked;
    private int[] distTo;
    private ArrayList<Integer>[] adjacencyList;
    private HashTable mapping;
    
    private class HashTable {
        public int counter;
        private Node[] nodes;
    
        public HashTable() {
            this.nodes = new Node[8192];
            this.counter = 0;
        }
    
        public int hash(int key) {
            int hashKey = key & 8191;
            while (nodes[hashKey] != null && nodes[hashKey].id != key) {
                hashKey = (hashKey + 1) & 8191;
            }
    
            return hashKey;
        }

        public int get(int id) {
            int key = hash(id);
            Node head = nodes[key];
            if(head == null) {
                nodes[key] = new Node(id, this.counter);
                adjacencyList[this.counter] = new ArrayList<Integer>();
                ++this.counter;
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

    private class Queue {
        private int[] queue;
        private int head;
        private int tail;
        private int size;
    
        public Queue() {
            queue = new int[2];
            head = 0;
            tail = 0;
            size = 0;
        }
    
        public void enqueue(int item) {
            if (size == queue.length) {
                resize(2 * queue.length);
            }
            queue[tail++] = item;
            if (tail == queue.length) {
                tail = 0;
            }
            size++;
        }
    
        public Integer dequeue() {
            if (isEmpty()) {
                return null;
            }
            int item = queue[head];
            queue[head] = 0;
            head++;
            if (head == queue.length) {
                head = 0;
            }
            size--;
            if (size > 0 && size == queue.length / 4) {
                resize(queue.length / 2);
            }
            return item;
        }
    
        public boolean isEmpty() {
            return size == 0;
        }
    
        private void resize(int capacity) {
            int[] copy = new int[capacity];
            if (head < tail) {
                System.arraycopy(queue, head, copy, 0, size);
            } else {
                System.arraycopy(queue, head, copy, 0, queue.length - head);
                System.arraycopy(queue, 0, copy, queue.length - head, tail);
            }
            queue = copy;
            head = 0;
            tail = size;
        }
    }

    @SuppressWarnings("unchecked")
    private class Graph {
        public Graph() {
            mapping = new HashTable();
            adjacencyList = new ArrayList[8192];
        }

        public void addEdge(int from, int to) {
            int fromId = mapping.get(from);
            int toId = mapping.get(to);
            adjacencyList[fromId].add(toId);
            adjacencyList[toId].add(fromId);
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
            
            for(int w : adjacencyList[v]) {
                if(marked[w] == false) {
                    queue.enqueue(w);
                    marked[w] = true;
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
        distTo = new int[n];

        int farthestNode = bfs(graph, 0);

        marked = new boolean[n];

        bfs(graph, farthestNode);

        return maxDist;
    }
}
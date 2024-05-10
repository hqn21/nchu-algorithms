import java.util.ArrayList;

public class HW07_4111056036_5 extends LSD {
	private HashMap<Integer, ArrayList<Integer>> hmap;
	private boolean[] visited;
	private int[] distTo;
	@Override
	public int distance(int[][] array) {
		hmap = new HashMap<Integer, ArrayList<Integer>>();
		
		int maxNum = 0;
		for(int i=0;i<array.length;i++) {
			if(hmap.get(array[i][0])==null) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(array[i][1]);
				hmap.put(array[i][0],temp);
			}else {
				if(!hmap.get(array[i][0]).contains(array[i][1]))
					hmap.get(array[i][0]).add(array[i][1]);
			}
			if(hmap.get(array[i][1])==null) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(array[i][0]);
				hmap.put(array[i][1],temp);
			}else {
				if(!hmap.get(array[i][1]).contains(array[i][0]))
					hmap.get(array[i][1]).add(array[i][0]);
			}
			maxNum = Math.max(maxNum,Math.max(array[i][0], array[i][1]) );
		}
		
		
		int maxNode = -1;
		int maxSize = Integer.MIN_VALUE;
		for(int i=0;i<hmap.size();i++) {
			if(hmap.get(i) != null && hmap.get(i).size() > maxSize) {
				maxSize = hmap.get(i).size();
				maxNode = i;
			}
		}
		int k = 4;
		int v = maxNode;
		int max = Integer.MIN_VALUE;
		for(int i=0;i<k;i++) {
			visited = new boolean[maxNum+1];
			distTo = new int [maxNum+1];
			bfs(v);
			
			for(int j=0;j<distTo.length;j++) {
				if(distTo[j] > max) {
					max = distTo[j];
					v = j;
				}
			}
		}
		
		return max;
	}
	public void bfs(int s) {
		Queue q = new CircularQueue(hmap.size());
		q.add(s);
		visited[s] = true;
		distTo[s] = 0;
		while(!q.isEmpty()) {
			int v = q.remove();
			if(hmap.get(v)!=null) {
				for(int node:hmap.get(v)) {
					if(!visited[node]) {
						q.add(node);
						visited[node] = true;
						distTo[node] = distTo[v] + 1;
					}
				}
			}
		}
	}
	public <T> void ensureCapaticy(ArrayList<T> arr,int size) {
		arr.ensureCapacity(size);
		while(arr.size()<=size) {
			arr.add(null);
		}
	}
	class Queue{
		protected int[] queue;
		protected int size;
		protected int front = -1;
		protected int rear = -1;
		public Queue(int size) {
			queue = new int[size];
			this.size = size;
		}
		public void add(int x) {
			queue[++rear] = x;
		}
		public int remove() {
			return queue[++front];
		}
		public boolean isEmpty() {
			return front==rear;
		}
		public boolean isFull() {
			return rear == size - 1;
		}
	}
	class CircularQueue extends Queue{
		public CircularQueue(int size) {
			super(size);
		}
		public void add(int x) {
			rear = (rear+1)%size;
			queue[rear] = x;
		}
		public int remove() {
			front = (front+1)%size;
			return queue[front];
		}
		public boolean isFull() {
			return (rear+1)%size == front;
		}
	}
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
	class LinkedList{
		private class Node{
			public int s;
			public ArrayList<Integer> data;
			public Node next;
			public Node(int s,ArrayList<Integer> data) {
				this.s = s;
				this.data = data;
				this.next = null;
			}
		}
		private Node first = null;
		public void add(int s,ArrayList<Integer> data) {
			Node temp = new Node(s,data);
			temp.next = first;
			first = temp;
		}
		public ArrayList<Integer> get(int s) {
			Node temp = first;
			if(temp==null) return null;
			while(temp.next!=null && temp.s != s) {
				temp = temp.next;
			}
			if(temp.s!=s) return null;
			else return temp.data;
		}
	}
}
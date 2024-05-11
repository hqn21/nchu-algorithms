import java.util.ArrayList;

public class HW07_4111056036_5 extends LSD {
	private ArrayList<ArrayList<Integer>> adjList;
	private boolean[] visited;
	private int[] distTo;
	@Override
	public int distance(int[][] array) {
		adjList = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<array.length;i++) {
			ensureCapaticy(adjList,array[i][0]);
			ensureCapaticy(adjList,array[i][1]);
			if(adjList.get(array[i][0])==null) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(array[i][1]);
				adjList.set(array[i][0],temp);
			}else {
				if(!adjList.get(array[i][0]).contains(array[i][1]))
					adjList.get(array[i][0]).add(array[i][1]);
			}
			if(adjList.get(array[i][1])==null) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(array[i][0]);
				adjList.set(array[i][1],temp);
			}else {
				if(!adjList.get(array[i][1]).contains(array[i][0]))
					adjList.get(array[i][1]).add(array[i][0]);
			}
			
		}
		
		int maxNode = -1;
		int maxSize = Integer.MIN_VALUE;
		for(int i=0;i<adjList.size();i++) {
			if(adjList.get(i)!=null && adjList.get(i).size()>maxSize) {
				maxSize = adjList.get(i).size();
				maxNode = i;
			}
		}
		int k = 4;
		int v = maxNode;
		int max = Integer.MIN_VALUE;
		for(int i=0;i<k;i++) {
			visited = new boolean[adjList.size()];
			distTo = new int [adjList.size()];
			
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
		CircularQueue q = new CircularQueue(adjList.size());
		q.add(s);
		visited[s] = true;
		distTo[s] = 0;
		while(!q.isEmpty()) {
			int v = q.remove();
			for(int node:adjList.get(v)) {
				if(!visited[node]) {
					q.add(node);
					visited[node] = true;
					distTo[node] = distTo[v] + 1;
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
	class CircularQueue{
		protected int[] queue;
		protected int size;
		protected int front = -1;
		protected int rear = -1;
		public CircularQueue(int size) {
			queue = new int[size];
			this.size = size;
		}
		public void add(int x) {
			rear = (rear+1)%size;
			queue[rear] = x;
		}
		public int remove() {
			front = (front+1)%size;
			return queue[front];
		}
		public boolean isEmpty() {
			return front==rear;
		}
		public boolean isFull() {
			return (rear+1)%size == front;
		}
	}
}
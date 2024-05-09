import java.util.ArrayList;

public class HW07_4111056036_3 extends LSD {
    ArrayList<Integer>[] graph;
    boolean[] visited;
    int farthestNode;
    int maxDist;

    @Override
    @SuppressWarnings("unchecked")
    public int distance(int[][] array) {
        int n = 0;
        for (int[] edge : array) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }
        n++;

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int[] edge : array) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        visited = new boolean[n];
        farthestNode = -1;
        maxDist = -1;
        dfs(0, 0);

        java.util.Arrays.fill(visited, false);
        maxDist = -1;
        dfs(farthestNode, 0);

        return maxDist;
    }

    private void dfs(int node, int dist) {
        visited[node] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = node;
        }

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, dist + 1);
            }
        }
    }
}
import java.util.Arrays;

public class HW07_4111056036_2 extends LSD {
    @Override
    public int distance(int[][] array) {
        int n = 0;
        for (int[] edge : array) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }
        n++;

        int maxDist = 0;
        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }
        for (int[] edge : array) {
            dist[edge[0]][edge[1]] = 1;
            dist[edge[1]][edge[0]] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        if (dist[i][j] != Integer.MAX_VALUE) {
                            maxDist = Math.max(maxDist, dist[i][j]);
                        }
                    }
                }
            }
        }

        return maxDist;
    }
}
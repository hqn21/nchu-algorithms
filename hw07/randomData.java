import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomData {
    public static void main(String[] args) {
        List<int[]> c = generateRandomGraph(1000, 500); // 生成包含 10 個頂點和 15 條邊的圖
        // 打印生成的邊集合
        for (int[] edge : c) {
            System.out.print("{" + edge[0] + ", " + edge[1] + "}, ");
        }
    }

    // 生成隨機圖的方法
    public static List<int[]> generateRandomGraph(int numVertices, int numEdges) {
        if (numEdges > numVertices * (numVertices - 1) / 2) {
            throw new IllegalArgumentException("The number of edges exceeds the maximum possible number of edges for the given number of vertices.");
        }
        
        Random random = new Random();
        List<int[]> edges = new ArrayList<>();
        List<Integer> vertices = new ArrayList<>();
        // 生成頂點集合
        for (int i = 0; i < numVertices; i++) {
            vertices.add(i);
        }
        // 生成邊集合
        for (int i = 0; i < numEdges; i++) {
            int index1 = random.nextInt(vertices.size()); // 隨機選擇一個頂點作為起點
            int start = vertices.get(index1);
            vertices.remove(index1); // 從頂點集合中移除已選擇的起點
            int index2 = random.nextInt(vertices.size()); // 再次隨機選擇一個頂點作為終點
            int end = vertices.get(index2);
            edges.add(new int[]{start, end}); // 將起點和終點組成一條邊並加入邊集合
            vertices.add(start); // 將起點重新加入頂點集合，以便它可以作為其他邊的起點
        }
        return edges;
    }
}

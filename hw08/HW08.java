import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HW08 {
    public static int[][] readTxtFileTo2DArray(String fileName) {
        List<int[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }

            // 去除大括號和空格，並根據逗號拆分
            String[] pairs = content.toString()
                                    .replace("{", "")
                                    .replace("}", "")
                                    .split("\\),\\(");

            for (String pair : pairs) {
                String[] numbers = pair.split(",");
                int[] row = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    row[i] = Integer.parseInt(numbers[i].trim());
                }
                rows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 將 ArrayList 轉換為二維陣列
        int[][] array = new int[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            array[i] = rows.get(i);
        }

        return array;
    }

    public static void test(LLK l) {
        int[][] a = {{1, 2}, {1, 3}, {1, 4}};
        int[][] b = {{0, 0}, {1, 0}, {1, 1}, {0, 1}};
        int[][] c = readTxtFileTo2DArray("HW8_test_data/hw8_public_data_1.txt");
        int[][] d = readTxtFileTo2DArray("HW8_test_data/hw8_public_data_2.txt");

        boolean result;
        double time;
        StopWatch stopWatch;

        System.out.println("====================");
        stopWatch = new StopWatch();
        result = l.checkLLK(a);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + "\t" + (result == true ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time + "ns");
        stopWatch = new StopWatch();
        result = l.checkLLK(b);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + "\t" + (result == false ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time + "ns");
        stopWatch = new StopWatch();
        result = l.checkLLK(c);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + "\t" + (result == true ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time + "ns");
        stopWatch = new StopWatch();
        result = l.checkLLK(d);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + "\t" + (result == true ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time + "ns");
        System.out.println("====================");
    }

    public static void main(String[] args) {
        LLK build1 = new HW08_4111056036_1();
        LLK build2 = new HW08_4111056036_2();
        LLK build3 = new HW08_4111056036_3();
        test(build1);
        test(build2);
        test(build3);
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HW08 {
    public static int[][] readTxtFileTo2DArray(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String input = contentBuilder.toString();
        
        // Remove outer braces
        input = input.substring(2, input.length() - 2);
        
        // Split the string into pairs
        String[] pairs = input.split("\\},\\{");
        
        // Initialize the resulting array
        int[][] result = new int[pairs.length][2];
        
        // Iterate over each pair
        for (int i = 0; i < pairs.length; i++) {
            // Split the pair into individual numbers
            String[] numbers = pairs[i].split(", ");
            result[i][0] = Integer.parseInt(numbers[0]);
            result[i][1] = Integer.parseInt(numbers[1]);
        }
        
        return result;
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
        System.out.println("Result: " + result + "\t" + (result == false ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time + "ns");
        System.out.println("====================");
    }

    public static void main(String[] args) {
        LLK build1 = new HW08_4111056036_1();
        LLK build2 = new HW08_4111056036_2();
        LLK build3 = new HW08_4111056036_3();
        LLK build4 = new HW08_4111056036_4();
        LLK build5 = new HW08_4111056036_5();
        test(build1);
        test(build2);
        test(build3);
        test(build4);
        test(build5);
    }
}

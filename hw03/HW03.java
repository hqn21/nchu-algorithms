import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HW03 {
    public static void test(DogeCoin d) {
        int a[] = new int[]{7, 1, 5, 3, 6};
        int b[] = new int[]{7, 6, 4, 3, 1};
        int result;
        double time;
        StopWatch stopWatch;
        int[] numbers;

        try {
            File file = new File("data.txt"); // 請將"data.txt"替換為您的檔案路徑
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            String[] numberStrings = line.split(",");
            numbers = new int[numberStrings.length];
            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i].trim());
            }
            // System.out.println(Arrays.toString(numbers));
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("檔案未找到");
            e.printStackTrace();
            numbers = new int[]{0};
        }

        System.out.println("====================");
        stopWatch = new StopWatch();
        result = d.doge(a);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result == 5 ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        stopWatch = new StopWatch();
        result = d.doge(b);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result == 0 ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        stopWatch = new StopWatch();
        result = d.doge(numbers);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result == 3 ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        System.out.println("====================");
    }

    public static void main(String[] args) {
        HW03_4111056036_1 build1 = new HW03_4111056036_1();
        HW03_4111056036_2 build2 = new HW03_4111056036_2();
        HW03_4111056036_3 build3 = new HW03_4111056036_3();
        test(build1);
        test(build2);
        test(build3);
    }
}

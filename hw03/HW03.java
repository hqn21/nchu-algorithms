public class HW03 {
    public static void test(DogeCoin d) {
        int a[] = new int[]{7, 1, 5, 3, 6};
        int b[] = new int[]{7, 6, 4, 3, 1};
        int result;
        double time;
        StopWatch stopWatch;

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
        System.out.println("====================");
    }

    public static void main(String[] args) {
        HW03_4111056036_1 build1 = new HW03_4111056036_1();
        test(build1);
    }
}

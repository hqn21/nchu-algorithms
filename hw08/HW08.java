public class HW08 {
    public static void test(LLK l) {
        int[][] a = {{1, 2}, {1, 3}, {1, 4}};
        int[][] b = {{0, 0}, {1, 0}, {1, 1}, {0, 1}};
        int[][] c = new int[8000][];

        for(int i = 0; i < 8000; ++i) {
            c[i] = new int[2];
            c[i][0] = i + 1;
            c[i][1] = i + 1;
        }

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

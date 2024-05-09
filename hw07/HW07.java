public class HW07 {
    public static void test(LSD l) {
        int[][] a = {{0, 1}, {0, 2}, {0, 4}, {1, 3}, {1, 4}, {2, 5}, {6, 7}};
        int result;
        double time;
        StopWatch stopWatch;

        System.out.println("====================");
        stopWatch = new StopWatch();
        result = l.distance(a);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + "\t" + (result == 4 ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time + "ns");
        System.out.println("====================");
    }

    public static void main(String[] args) {
        LSD build1 = new HW07_4111056036_1();
        LSD build2 = new HW07_4111056036_2();
        LSD build3 = new HW07_4111056036_3();
        LSD build4 = new HW07_4111056036_4();
        LSD build5 = new HW07_4111056036_5();
        test(build1);
        test(build2);
        test(build3);
        test(build4);
        test(build5);
    }
}
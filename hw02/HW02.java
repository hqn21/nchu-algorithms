public class HW02 {
    public static void test(FourSum f) {
        int[] a = new int[]{-1, 1, 2, -4, 4, 8, -3};
        int result;
        double time;
        StopWatch stopWatch;

        System.out.println("====================");
        stopWatch = new StopWatch();
        result = f.F_sum(a);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result == 2 ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        System.out.println("====================");
    }

    public static void main(String[] args) {
        HW02_4111056036_1 build1 = new HW02_4111056036_1();
        HW02_4111056036_2 build2 = new HW02_4111056036_2();
        HW02_4111056036_3 build3 = new HW02_4111056036_3();
        HW02_4111056036_4 build4 = new HW02_4111056036_4();
        HW02_4111056036_5 build5 = new HW02_4111056036_5();

        test(build1);
        test(build2);
        test(build3);
        test(build4);
        test(build5);
    }
}

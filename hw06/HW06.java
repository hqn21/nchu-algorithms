import java.io.*;

public class HW06 {
    static class Result {
        Double[] ans;
        double time;
        boolean correct;

        public Result(Double[] ans, double time, boolean correct) {
            this.ans = ans;
            this.time = time;
            this.correct = correct;
        }
    }

    public static Result test(SortIsAllYouNeed s, int id) {
        int[] lines = {1000, 1000, 1000, 1000, 1000, 1000, 1000, 2001, 1000};
        Double[] A = new Double[lines[id - 1]];
        int now = 0;

        // read file
        FileReader fr = null;
        try {
            fr = new FileReader("./HW6_test_data/test_data_" + id + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String tmp = null;

        try {
            while (((tmp = br.readLine()) != null)) {
                A[now] = Double.parseDouble(tmp);
                ++now;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // start test
        StopWatch stopWatch = new StopWatch();
        s.sortWhat(A);
        double time = stopWatch.elapsedTime();
        boolean correct = checkAnswer(A, id);
        Result result = new Result(A, time, correct);
        return result;
    }


    public static boolean checkAnswer(Double[] ans, int id) {
        int now = 0;
        FileReader fr = null;
        try {
            fr = new FileReader("./HW6_test_data/answer_" + id + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String tmp = null;

        try {
            while (((tmp = br.readLine()) != null)) {
                if(ans[now] != Double.parseDouble(tmp)) {
                    return false;
                }
                ++now;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public static void printArray(Double[] A) {
        System.out.print("{");
        System.out.print(A[0]);
        for(int i = 1; i < A.length; ++i) {
            System.out.print(", " + A[i]);
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        HW06_4111056036_1 build1 = new HW06_4111056036_1();
        HW06_4111056036_2 build2 = new HW06_4111056036_2();
        HW06_4111056036_3 build3 = new HW06_4111056036_3();
        HW06_4111056036_4 build4 = new HW06_4111056036_4();
        HW06_4111056036_5 build5 = new HW06_4111056036_5();
        Result result;

        for(int i = 1; i <= 9; ++i) {
            System.out.println("=====[TEST " + i + "]=====");
            result = test(build1, i);
            System.out.println("Build 1: " + result.time + "ns\t[" + (result.correct ? "CORRECT" : "WRONG") + "]");
            // printArray(result.ans);
            result = test(build2, i);
            System.out.println("Build 2: " + result.time + "ns\t[" + (result.correct ? "CORRECT" : "WRONG") + "]");
            // printArray(result.ans);
            result = test(build3, i);
            System.out.println("Build 3: " + result.time + "ns\t[" + (result.correct ? "CORRECT" : "WRONG") + "]");
            result = test(build4, i);
            System.out.println("Build 4: " + result.time + "ns\t[" + (result.correct ? "CORRECT" : "WRONG") + "]");
            result = test(build5, i);
            System.out.println("Build 5: " + result.time + "ns\t[" + (result.correct ? "CORRECT" : "WRONG") + "]");
            System.out.println("==================");
        }
    }
}

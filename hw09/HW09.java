import java.util.Arrays;

public class HW09 {
    public static void printArray(int[][] array) {
        System.out.print("{");
        for(int i = 0; i < array.length; i++) {
            System.out.print("{" + array[i][0] + ", " + array[i][1] + "}");
            if(i != array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

    public static void test(BuyPhone bp) {
        int[][] a = {{2,5},{2,6},{4,8},{5,5},{5,9},{6,5},{7,3},{7,10},{8,4},{9,8}};
        int[][] b = {{5445,3965},{704,651},{6680,5158},{7748,4731},{4212,4913},{3200,9157},{4101,8452},{6802,5249},{6650,3139},{1390,84},{7384,6351},{4595,6298},{3015,4150},{8466,9643},{159,7456},{8789,9542},{8065,1387},{8644,4291},{7057,9520},{9724,382},{4568,2064},{522,7586},{6514,5407},{6062,3667},{2798,9908},{0,385},{1163,5667},{3044,9691},{944,3854},{2500,7973},{4768,7626},{4931,2634},{2015,615},{9136,1042},{418,7648},{8238,5397},{6123,9932},{4614,60},{7851,1455},{6080,3244},{3885,1273},{4268,1866},{8401,5123},{1158,153},{7189,8820},{5929,5514},{4245,9372},{4030,4933},{3691,1463},{4490,6378},{9466,213},{6658,9203},{820,5602},{6304,6022},{7619,5129},{343,2460},{1007,1708},{4012,7362},{8130,8254},{5118,1624},{5996,1060},{2054,5668},{3503,713},{1278,444},{8433,9597},{2148,8865},{7082,2437},{6682,9631},{1618,6959},{9575,4240},{8497,1717},{7255,9405},{2572,9731},{6680,1835},{4724,2205},{3658,7239},{1421,2641},{9821,7908},{3679,6557},{4159,8493},{5471,410},{9552,4188},{2892,2049},{1447,3512},{9777,497},{1406,7189},{6244,7350},{4787,6474},{6254,4465},{2159,2641},{9515,446},{6825,9745},{2858,3110},{3929,6154},{353,6685},{9600,7897},{1571,1554},{6861,8867},{9011,2788},{9200,1333}};

        int[][] aAns = {{7,10},{9,8}};
        int[][] bAns = {{8466,9643},{8789,9542},{6123,9932},{9821,7908},{6825,9745}};

        int[][] result;
        double time;
        StopWatch stopWatch;

        System.out.println("====================");
        stopWatch = new StopWatch();
        result = bp.bestPhone(a);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + (Arrays.deepEquals(result, aAns) ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time + "ns");
        printArray(result);
        stopWatch = new StopWatch();
        result = bp.bestPhone(b);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + (Arrays.deepEquals(result, bAns) ? "[CORRECT]" : "[WRONG]"));
        printArray(result);
        System.out.println("Time: " + time + "ns");
        System.out.println("====================");
    }

    public static void main(String[] args) {
        BuyPhone build1 = new HW09_4111056036_1();
        BuyPhone build2 = new HW09_4111056036_2();
        BuyPhone build3 = new HW09_4111056036_3();
        BuyPhone build4 = new HW09_4111056036_4();
        BuyPhone build5 = new HW09_4111056036_5();
        test(build1);
        test(build2);
        test(build3);
        test(build4);
        test(build5);
    }
}
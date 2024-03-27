public class HW04 {
    public static void test(LanguageModel lm) {
        String[] a = {"臺中市", "臺中市 中華民國 直轄市 中臺灣 臺中市 中華民國 臺灣"};
        String[] b = {"柯文哲", "柯文哲 外科 醫師 政治人物 柯文哲 民眾黨 主席 柯文哲 民眾黨"};
        String[] c = {"新北市", "新北市 中華民國 直轄市 北臺灣 新北市 中華民國 臺灣 新北市 中華民國 直轄市 北臺灣 新北市 中華民國 臺灣"};
        String[] d = {"蔡英文", "蔡英文 法學學者 政治人物 蔡英文 民進黨 主席 蔡英文 民進黨 蔡英文 中華民國 總統 蔡英文 民進黨"};
        String[] e = {"高雄市", "高雄市 中華民國 直轄市 南臺灣 高雄市 中華民國 臺灣 高雄市 中華民國 直轄市 南臺灣 高雄市 中華民國 臺灣"};
        String[] f = {"韓國瑜", "韓國瑜 商人 政治人物 韓國瑜 國民黨 主席 韓國瑜 國民黨 韓國瑜 高雄市 市長 韓國瑜 國民黨"};
        String result;
        double time;
        StopWatch stopWatch;

        System.out.println("====================");
        stopWatch = new StopWatch();
        result = lm.nextPredictToken(a);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result.equals("中華民國") ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        stopWatch = new StopWatch();
        result = lm.nextPredictToken(b);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result.equals("民眾黨") ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        stopWatch = new StopWatch();
        result = lm.nextPredictToken(c);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result.equals("中華民國") ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        stopWatch = new StopWatch();
        result = lm.nextPredictToken(d);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result.equals("民進黨") ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        stopWatch = new StopWatch();
        result = lm.nextPredictToken(e);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result.equals("中華民國") ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        stopWatch = new StopWatch();
        result = lm.nextPredictToken(f);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result.equals("國民黨") ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        System.out.println("====================");
    }

    public static void main(String[] args) {
        HW04_4111056036_1 build1 = new HW04_4111056036_1();
        test(build1);
    }
}
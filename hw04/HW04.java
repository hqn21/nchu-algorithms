public class HW04 {
    public static void test(LanguageModel lm) {
        String[] a = {"臺中市", "臺中市 中華民國 直轄市 中臺灣 臺中市 中華民國 臺中市萬歲 臺灣 臺中市萬歲 臺灣 臺中市萬歲 臺灣"};
        String[] b = {"柯文哲", "柯文哲 外科 醫師 政治人物 柯文哲 民眾黨 主席 柯文哲 民眾黨"};
        String[] c = {"新北市", "新北市 中華民國 直轄市 北臺灣 新北市 中華民國 臺灣 新北市 中華民國 直轄市 北臺灣 新北市 中華民國 臺灣"};
        String[] d = {"蔡英文", "蔡英文 法學學者 政治人物 蔡英文 民進黨 主席 蔡英文 民進黨 蔡英文 中華民國 總統 蔡英文 民進黨"};
        String[] e = {"高雄市", "高雄市 中華民國 直轄市 南臺灣 高雄市 中華民國 臺灣 高雄市 中華民國 直轄市 南臺灣 高雄市 中華民國 臺灣"};
        String[] f = {"韓國瑜", "韓國瑜 商人 政治人物 韓國瑜 國民黨 主席 韓國瑜 國民黨 韓國瑜 高雄市 市長 韓國瑜 國民黨"};
        String[] g = {"櫻桃", "蘋果 橘子 香蕉 西瓜 芒果 草莓 櫻桃 桃子 葡萄 柿子 梨子 檸檬 柳丁 菠蘿 蓮霧 桑葚 覆盆子 黑莓 李子 荔枝 火龍果 芭樂 香瓜 榴槤 榴蓮 棗子 枇杷 桂圓 紅毛丹 鳳梨 椰子 檳榔 椪柑 柚子 蓮子 蓮藕 橄欖 蔓越莓 藍莓 芒果 菠蘿 西瓜 蘋果 芭樂 鳳梨 草莓 櫻桃 橘子 柳丁 雞蛋果 蓮霧 鳳梨 芒果 荔枝 香蕉 蓮霧 西瓜 蘋果 桃子 橘子 柚子 鳳梨 草莓 橘子 櫻桃 柚子 蓮霧 柳丁 蘋果 香蕉 芒果 荔枝 鳳梨 櫻桃 橘子 柚子 香蕉 西瓜 芒果 草莓 櫻桃 桃子 葡萄 柿子 梨子 檸檬 柳丁 菠蘿 蓮霧 桑葚 覆盆子 黑莓 李子 荔枝 火龍果 芭樂 香瓜 榴槤 榴蓮 棗子 枇杷 桂圓 紅毛丹 鳳梨 椰子 檳榔 椪柑 柚子 蓮子 蓮藕 橄欖 蔓越莓 藍莓 芒果 菠蘿 西瓜 蘋果 芭樂 鳳梨 草莓 櫻桃 橘子 柳丁 雞蛋果 蓮霧 鳳梨 芒果 荔枝 香蕉 蓮霧 西瓜 蘋果 桃子 橘子 柚子 鳳梨 草莓 橘子 櫻桃 柚子 蓮霧 柳丁 蘋果 香蕉 芒果 荔枝 鳳梨 櫻桃 橘子 柚子 香蕉 西瓜 芒果 草莓 櫻桃 桃子 葡萄 柿子 梨子 檸檬 柳丁 菠蘿 蓮霧 桑葚 覆盆子 黑莓 李子 荔枝 火龍果 芭樂 香瓜 榴槤 榴蓮 棗子 枇杷 桂圓 紅毛丹 鳳梨 椰子 檳榔 椪柑 柚子 蓮子 蓮藕 橄欖 蔓越莓 藍莓 芒果 菠蘿 西瓜 蘋果 芭樂 鳳梨 草莓 櫻桃 橘子 柳丁 雞蛋果 蓮霧 鳳梨 芒果 荔枝 香蕉 蓮霧 西瓜 蘋果 桃子 橘子 柚子 鳳梨 草莓 橘子 櫻桃 柚子 蓮霧 柳丁 蘋果 香蕉 芒果 荔枝 鳳梨 櫻桃 橘子 柚子 香蕉 西瓜 芒果 草莓 櫻桃 桃子 葡萄 柿子 梨子 檸檬 柳丁 菠蘿 蓮霧 桑葚 覆盆子 黑莓 李子 荔枝 火龍果 芭樂 香瓜 榴槤 榴蓮 棗子 枇杷 桂圓 紅毛丹 鳳梨 椰子 檳榔 椪柑 柚子 蓮子 蓮藕 橄欖 蔓越莓 藍莓 芒果 菠蘿 西瓜 蘋果 芭樂 鳳梨 草莓 櫻桃 橘子 柳丁 雞蛋果 蓮霧 鳳梨 芒果 荔枝 香蕉 蓮霧 西瓜 蘋果 桃子 橘子 柚子 鳳梨 草莓 橘子 櫻桃 柚子 蓮霧 柳丁 蘋果 香蕉 芒果 荔枝"};
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
        stopWatch = new StopWatch();
        result = lm.nextPredictToken(g);
        time = stopWatch.elapsedTime();
        System.out.println("Result: " + result + " " + (result.equals("橘子") ? "[CORRECT]" : "[WRONG]"));
        System.out.println("Time: " + time);
        System.out.println("====================");
    }

    public static void main(String[] args) {
        HW04_4111056036_1 build1 = new HW04_4111056036_1();
        HW04_4111056036_2 build2 = new HW04_4111056036_2();
        HW04_4111056036_3 build3 = new HW04_4111056036_3();
        HW04_4111056036_4 build4 = new HW04_4111056036_4();
        HW04_4111056036_5 build5 = new HW04_4111056036_5();
        test(build1);
        test(build2);
        test(build3);
        test(build4);
        test(build5);
    }
}
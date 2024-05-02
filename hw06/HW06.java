import java.io.*;

public class HW06 {
    public static void test() {
        FileReader fr = null;
        try {
            fr = new FileReader("./HW6_test_data/answer_1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String tmp = null;

        try {
            while (((tmp = br.readLine()) != null)) {
                System.out.println(Double.parseDouble(tmp));
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
    }

    public static void main(String[] args) {
        test();
    }
}

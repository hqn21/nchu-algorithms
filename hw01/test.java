import java.util.Random;

public class test {
    public static void main(String[] args) {
        Random rand = new Random();
        System.out.print("{");
        for (int i = 0; i < 1000; i++) {
            System.out.print(rand.nextInt() + ", ");
        }
        System.out.print("}\n");
    }
}

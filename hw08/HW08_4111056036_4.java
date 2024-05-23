public class HW08_4111056036_4 extends LLK {
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public boolean checkLLK(int[][] array) {
        int n = array.length;
        if (n < 3) {
            return true;
        }

        int deltaX1 = array[0][0] - array[1][0];
        int deltaY1 = array[0][1] - array[1][1];
        int gcd1 = gcd(Math.abs(deltaX1), Math.abs(deltaY1));
        deltaX1 /= gcd1;
        deltaY1 /= gcd1;

        if (deltaX1 < 0 || (deltaX1 == 0 && deltaY1 < 0)) {
            deltaX1 = -deltaX1;
            deltaY1 = -deltaY1;
        }

        for (int i = 2; i < n; ++i) {
            int deltaX = array[i][0] - array[i - 1][0];
            int deltaY = array[i][1] - array[i - 1][1];
            int gcd = gcd(Math.abs(deltaX), Math.abs(deltaY));
            deltaX /= gcd;
            deltaY /= gcd;

            if (deltaX < 0 || (deltaX == 0 && deltaY < 0)) {
                deltaX = -deltaX;
                deltaY = -deltaY;
            }

            if (deltaX != deltaX1 || deltaY != deltaY1) {
                return false;
            }
        }

        return true;
    }
}
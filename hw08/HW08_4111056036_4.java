public class HW08_4111056036_4 extends LLK {
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    @Override
    public boolean checkLLK(int[][] array) {
        int n = array.length;
        if(n < 3) {
            return true;
        }

        int firstDeltaX = array[0][0] - array[1][0];
        int firstDeltaY = array[0][1] - array[1][1];
        int gcd = gcd(firstDeltaX, firstDeltaY);
        firstDeltaX /= gcd;
        firstDeltaY /= gcd;

        int deltaX, deltaY;
        for(int i = 2; i < n; ++i) {
            deltaX = array[i][0] - array[i - 1][0];
            deltaY = array[i][1] - array[i - 1][1];
            gcd = gcd(deltaX, deltaY);
            deltaX /= gcd;
            deltaY /= gcd;
            if(deltaX != firstDeltaX || deltaY != firstDeltaY) {
                return false;
            }
        }

        return true;
    }
}
public class HW08_4111056036_5 extends LLK {
    public int gcd(int a, int b) {
        int temp;
        while (b != 0) {
            temp = a;
            a = b;
            b = temp % b;
        }
        return a;
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

        if(firstDeltaX < 0) {
            firstDeltaX = ~firstDeltaX + 1;
            firstDeltaY = ~firstDeltaY + 1;
        }

        int deltaX, deltaY;
        for(int i = 2; i < n; ++i) {
            deltaX = array[i][0] - array[i - 1][0];
            deltaY = array[i][1] - array[i - 1][1];
            gcd = gcd(deltaX, deltaY);
            deltaX /= gcd;
            deltaY /= gcd;

            if(deltaX < 0) {
                deltaX = ~deltaX + 1;
                deltaY = ~deltaY + 1;
            }

            if(deltaX != firstDeltaX || deltaY != firstDeltaY) {
                return false;
            }
        }

        return true;
    }
}
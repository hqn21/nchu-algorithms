public class HW01_4111056036_1 extends ArrayData {
    public HW01_4111056036_1 (int[] A) {
        this.A = A;
    }

    public static void main(String[] args) {

    }

    @Override
    public int max() {
        int max = -2147483648;
        for(int i = 0; i < A.length; i++) {
            if(A[i] > max) {
                max = A[i];
            }
        }
        return max;
    }

    @Override
    public int min() {
        int min = 2147483647;
        for(int i = 0; i < A.length; i++) {
            if(A[i] < min) {
                min = A[i];
            }
        }
        return min;
    }
}

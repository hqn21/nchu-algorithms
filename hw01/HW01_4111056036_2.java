public class HW01_4111056036_2 extends ArrayData {
    public HW01_4111056036_2 (int[] A) {
        this.A = A;
    }

    public static void main(String[] args) {

    }

    @Override
    public int max() {
        int max = A[0];
        for(int i = 1; i < A.length; i++) {
            if(A[i] > max) {
                max = A[i];
            }
        }
        return max;
    }

    @Override
    public int min() {
        int min = A[0];
        for(int i = 1; i < A.length; i++) {
            if(A[i] < min) {
                min = A[i];
            }
        }
        return min;
    }
}

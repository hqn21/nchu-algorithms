public class HW01_4111056036_4 extends ArrayData {
    int max;
    int min;

    public HW01_4111056036_4 (int[] A) {
        this.A = A;
        this.min = A[0];
        this.max = A[0];

        for(int i = 1; i < A.length; ++i) {
            if(A[i] > max) {
                max = A[i];
            } else if(A[i] < min) {
                min = A[i];
            }
        }

        System.gc();
    }

    public static void main(String[] args) {

    }

    @Override
    public int max() {
        return this.max;
    }

    @Override
    public int min() {
        return this.min;
    }
}

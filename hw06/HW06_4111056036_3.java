public class HW06_4111056036_3 extends SortIsAllYouNeed {
    @Override
    public Double[] sortWhat(Double[] A) {
        threeWayQuickSort(A, 0, A.length - 1);
        return A;
    }

    private void threeWayQuickSort(Double[] A, int low, int high) {
        if (high <= low) return;
        int lt = low, gt = high;
        Double v = A[low];
        int i = low;
        while (i <= gt) {
            int cmp = A[i].compareTo(v);
            if (cmp < 0) swap(A, lt++, i++);
            else if (cmp > 0) swap(A, i, gt--);
            else i++;
        }
        threeWayQuickSort(A, low, lt - 1);
        threeWayQuickSort(A, gt + 1, high);
    }

    private void swap(Double[] A, int i, int j) {
        Double temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

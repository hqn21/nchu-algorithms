public class HW06_4111056036_5 extends SortIsAllYouNeed {
    private static final int RUN = 32;

    @Override
    public Double[] sortWhat(Double[] A) {
        int n = A.length;
        for (int i = 0; i < n; i += RUN) {
            insertionSort(A, i, Math.min((i + 31), (n - 1)));
        }
    
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(A, left, mid, right);
            }
        }
        return A;
    }

    private void insertionSort(Double[] A, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Double temp = A[i];
            int j = i - 1;
            while (j >= left && A[j] > temp) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = temp;
        }
    }

    private void merge(Double[] A, int left, int mid, int right) {
        int len1 = mid - left + 1, len2 = right - mid;
        Double[] leftArr = new Double[len1];
        Double[] rightArr = new Double[len2];
        System.arraycopy(A, left, leftArr, 0, len1);
        System.arraycopy(A, mid + 1, rightArr, 0, len2);

        int i = 0, j = 0, k = left;
        while (i < len1 && j < len2) {
            if (leftArr[i] <= rightArr[j]) {
                A[k] = leftArr[i];
                i++;
            } else {
                A[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            A[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < len2) {
            A[k] = rightArr[j];
            j++;
            k++;
        }
    }
}
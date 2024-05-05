public class HW06_4111056036_5 extends SortIsAllYouNeed {
    private static final int RUN = 32;

    @Override
    public Double[] sortWhat(Double[] A) {
        int n = A.length;
        for (int i = 0; i < n; i += RUN) {
            insertionSort(A, i, Math.min((i + 31), (n - 1)));
        }
    
        Double[] temp = new Double[n];
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(A, temp, left, mid, right);
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

    private void merge(Double[] A, Double[] temp, int left, int mid, int right) {
        System.arraycopy(A, left, temp, left, right - left + 1);

        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                A[k] = temp[i];
                i++;
            } else {
                A[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            A[k] = temp[i];
            i++;
            k++;
        }

        while (j <= right) {
            A[k] = temp[j];
            j++;
            k++;
        }
    }
}
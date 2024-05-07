public class HW06_4111056036_4 extends SortIsAllYouNeed {
    @Override
    public Double[] sortWhat(Double[] A) {
        quickSort(A, 0, A.length - 1);
        return A;
    }

    private void quickSort(Double[] A, int low, int high) {
        if (high - low <= 10) {
            insertionSort(A, low, high);
            return;
        }

        int pivotIndex = medianOfThree(A, low, high);
        Double pivot = A[pivotIndex];
        swap(A, pivotIndex, high);

        int lt = low, gt = high;
        int i = low;
        while (i <= gt) {
            if (A[i] < pivot) {
                swap(A, lt++, i++);
            } else if (A[i] > pivot) {
                swap(A, i, gt--);
            } else {
                i++;
            }
        }

        quickSort(A, low, lt - 1);
        quickSort(A, gt + 1, high);
    }

    private void insertionSort(Double[] A, int low, int high) {
        int i, j;
        Double key;
        for (i = low + 1; i <= high; i++) {
            key = A[i];
            j = i - 1;
            while (j >= low && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }

    private int medianOfThree(Double[] A, int low, int high) {
        int a = low + (int)(Math.random() * (high - low + 1));
        int b = low + (int)(Math.random() * (high - low + 1));
        int c = low + (int)(Math.random() * (high - low + 1));

        if((A[a] >= A[b] && A[a] <= A[c]) || (A[a] >= A[c] && A[a] <= A[b])) {
            return a;
        }

        if((A[b] >= A[a] && A[b] <= A[c]) || (A[b] >= A[c] && A[b] <= A[a])) {
            return b;
        }

        return c;
    }

    private void swap(Double[] A, int i, int j) {
        Double temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
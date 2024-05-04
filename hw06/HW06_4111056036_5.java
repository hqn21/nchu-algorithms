public class HW06_4111056036_5 extends SortIsAllYouNeed {
    @Override
    public Double[] sortWhat(Double[] A) {
        quickSort(A, 0, A.length - 1);
        return A;
    }

    private void quickSort(Double[] A, int low, int high) {
        if (low < high) {
            int pi = partition(A, low, high);

            quickSort(A, low, pi - 1);
            quickSort(A, pi + 1, high);
        }
    }

    private int partition(Double[] A, int low, int high) {
        double pivot = A[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (A[j] <= pivot) {
                i++;

                double temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }

        double temp = A[i + 1];
        A[i + 1] = A[high];
        A[high] = temp;

        return i + 1;
    }
}
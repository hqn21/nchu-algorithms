public class HW09_4111056036_2 extends BuyPhone {
    private void quickSort(int[][] arr, int[] ids, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, ids, low, high);
            quickSort(arr, ids, low, pivotIndex - 1);
            quickSort(arr, ids, pivotIndex + 1, high);
        }
    }

    private int partition(int[][] arr, int[] ids, int low, int high) {
        int mid = low + (high - low) >> 1;
        int pivotIndex = medianOfThree(arr, low, mid, high);
        int pivot = arr[pivotIndex][0];
        swap(arr, ids, pivotIndex, high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j][0] <= pivot) {
                i++;
                swap(arr, ids, i, j);
            }
        }
        swap(arr, ids, i + 1, high);
        return i + 1;
    }

    private int medianOfThree(int[][] arr, int low, int mid, int high) {
        if (arr[low][0] > arr[mid][0]) {
            if (arr[mid][0] > arr[high][0])
                return mid;
            else if (arr[low][0] > arr[high][0])
                return high;
            else
                return low;
        } else {
            if (arr[low][0] > arr[high][0])
                return low;
            else if (arr[mid][0] > arr[high][0])
                return high;
            else
                return mid;
        }
    }

    private void swap(int[][] arr, int[] ids, int i, int j) {
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        int tp = ids[i];
        ids[i] = ids[j];
        ids[j] = tp;
    }

    @Override
    public int[][] bestPhone(int[][] phones) {
        int[][] backup = phones.clone();
        int n = phones.length;
        int[] ids = new int[n];
        boolean[] good = new boolean[n];
        int count = n;
        for (int i = 0; i < n; ++i) {
            ids[i] = i;
        }

        quickSort(phones, ids, 0, n - 1);

        for (int i = 0; i < n; ++i) {
            good[ids[i]] = true;
            for (int j = n - 1; j > i; --j) {
                if (phones[i][1] < phones[j][1]) {
                    good[ids[i]] = false;
                    --count;
                    break;
                }
            }
        }

        int[][] ans = new int[count][2];
        int now = 0;

        for (int i = 0; i < n; ++i) {
            if (good[i]) {
                ans[now] = backup[i];
                ++now;
            }
        }

        return ans;
    }
}
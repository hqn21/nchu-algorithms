public class HW09_4111056036_3 extends BuyPhone {
    private void quickSort(int[][] arr, int[] ids, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, ids, low, high);
            quickSort(arr, ids, low, pivotIndex - 1);
            quickSort(arr, ids, pivotIndex + 1, high);
        }
    }

    private int partition(int[][] arr, int[] ids, int low, int high) {
        int mid = low + (high - low) >> 1;
        int pivotIndex = medianOfThree(arr, ids, low, mid, high);
        int pivotPrimary = arr[ids[pivotIndex]][0];
        int pivotSecondary = arr[ids[pivotIndex]][1];
        swap(ids, pivotIndex, high);
        int i = low - 1;
    
        for (int j = low; j < high; j++) {
            if (arr[ids[j]][0] < pivotPrimary || (arr[ids[j]][0] == pivotPrimary && arr[ids[j]][1] < pivotSecondary)) {
                i++;
                swap(ids, i, j);
            }
        }
        swap(ids, i + 1, high);
        return i + 1;
    }

    private int medianOfThree(int[][] arr, int[] ids, int low, int mid, int high) {
        if (arr[ids[low]][0] > arr[ids[mid]][0]) {
            if (arr[ids[mid]][0] > arr[ids[high]][0])
                return mid;
            else if (arr[ids[low]][0] > arr[ids[high]][0])
                return high;
            else
                return low;
        } else {
            if (arr[ids[low]][0] > arr[ids[high]][0])
                return low;
            else if (arr[ids[mid]][0] > arr[ids[high]][0])
                return high;
            else
                return mid;
        }
    }

    private void swap(int[] ids, int i, int j) {
        int temp = ids[i];
        ids[i] = ids[j];
        ids[j] = temp;
    }

    @Override
    public int[][] bestPhone(int[][] phones) {
        int n = phones.length;
        int[] ids = new int[n];
        boolean[] good = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; ++i) {
            ids[i] = i;
        }

        quickSort(phones, ids, 0, n - 1);

        int maxY = Integer.MIN_VALUE;

        for(int i = n - 1; i >= 0; --i) {
            if(phones[ids[i]][1] >= maxY) {
                good[ids[i]] = true;
                ++count;
                maxY = phones[ids[i]][1];
            }
        }

        int[][] ans = new int[count][2];
        int now = 0;

        for (int i = 0; i < n; ++i) {
            if (good[i]) {
                ans[now] = phones[i];
                ++now;
            }
        }

        return ans;
    }
}
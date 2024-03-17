public class HW02_4111056036_1 extends FourSum {
    public HW02_4111056036_1() {
        
    }

    public void quickSort(int[] arr, int low, int high) {
        int pi;
        if (low < high) {
            pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        int temp;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    @Override
    public int F_sum(int[] A) {
        int n = A.length;
        int ans = 0;
        int start, end, sum, low, high;
        this.quickSort(A, 0, n - 1);

        for(int i = 0; i < n; i++) {
            if(i > 0 && A[i] == A[i - 1]) {
                continue;
            }

            for(int j = i + 1; j < n; j++) {
                if(j > (i + 1) && A[j] == A[j - 1]) {
                    continue;
                }

                start = j + 1;
                end = n - 1;
                while(start < end) {
                    sum = A[i] + A[j] + A[start] + A[end];
                    if(sum == 0) {
                        ans++;
                        low = A[start];
                        high = A[end];
                        start++;
                        end--;
                        while(start < end && A[start] == low) {
                            start++;
                            ans++;
                        }
                        while(start < end && A[end] == high) {
                            end--;
                            ans++;
                        }
                    } else if(sum > 0) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        
        return ans;
    }
}

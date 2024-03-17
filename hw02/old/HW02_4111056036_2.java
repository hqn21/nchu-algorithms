public class HW02_4111056036_2 extends FourSum {
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    @Override
    public int F_sum(int[] A) {
        int n = A.length;
        this.quickSort(A, 0, n - 1);
        int ans = 0;
        
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && A[i] == A[i - 1]) {
                continue; 
            }
            
            if (i + 4 < n && A[i + 4] > 0 && A[i] > 0) {
                break;          
            }
            
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && A[j] == A[j - 1]) {
                    continue; 
                }
                
                if (j < n - 1 && A[j] > 0 && A[j + 1] > 0 && 0 - A[i] == 0) {
                    break;
                }
                
                if (A[i] + A[j] + A[n - 1] + A[n - 2] < 0) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;
                
                while (left < right) {
                    int sum = A[i] + A[j] + A[left] + A[right];
                    
                    if (sum == 0) {
                        ans++;
                        
                        while (left < right && A[left] == A[left + 1]) {
                            ans++;
                            left++;
                        }
                        while (left < right && A[right] == A[right - 1]) {
                            ans++;
                            right--;
                        }
                        
                        left++;
                        right--; 
                    } else if (sum < 0) { 
                        left++;
                    } else { 
                        right--;
                    }
                }
            }
        }
        
        return ans;
    }
}

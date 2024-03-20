public class HW02_4111056036_4 extends FourSum {
    public HW02_4111056036_4() {
        
    }

    public void quickSort(Position[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Position[] arr, int low, int high) {
        int pivot = arr[high].value;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].value < pivot) {
                i++;

                Position temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Position temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private class Position {
        int value;
        int id_1;
        int id_2;

        Position(int value, int id_1, int id_2) {
            this.value = value;
            this.id_1 = id_1;
            this.id_2 = id_2;
        }
    }

    @Override
    public int F_sum(int[] A) {
        int n = A.length;
        Position[] sums = new Position[n * (n - 1) / 2];
        int ans = 0;
        
        int now = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                sums[now] = new Position(A[i] + A[j], i, j);
                now++;
            }
        }

        this.quickSort(sums, 0, sums.length - 1);

        int left = 0;
        int right = sums.length - 1;
        int[] record = new int[sums.length];
        int leftCount, rightCount, low, high, initLeft, initRight;
        int sum;

        while(left < right) {
            sum = sums[left].value + sums[right].value;
            if(sum == 0) {
                if(sums[left].value == 0) {
                    for(int i = left; i <= right; i++) {
                        for(int j = i + 1; j <= right; j++) {
                            if(sums[i].id_1 != sums[j].id_1 && sums[i].id_1 != sums[j].id_2 && sums[i].id_2 != sums[j].id_1 && sums[i].id_2 != sums[j].id_2) {
                                ans++;
                            }
                        }
                    }
                    break;
                }

                leftCount = 0;
                rightCount = 0;
                low = sums[left].value;
                high = sums[right].value;

                initLeft = left;
                initRight = right;

                while(left < right && sums[left].value == low) {
                    record[sums[left].id_1]++;
                    record[sums[left].id_2]++;
                    leftCount++;
                    left++;
                }

                while(left <= right && sums[right].value == high) {
                    ans -= record[sums[right].id_1];
                    ans -= record[sums[right].id_2];
                    rightCount++;
                    right--;
                }

                while(initLeft < initRight && sums[initLeft].value == low) {
                    record[sums[initLeft].id_1]--;
                    record[sums[initLeft].id_2]--;
                    initLeft++;
                }

                ans += leftCount * rightCount;
            } else if(sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        return ans / 3;
    }
}
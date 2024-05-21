public class HW02_4111056036_5 extends FourSum {
    Position[] sums;

    public HW02_4111056036_5() {
        
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
        sums = new Position[n * (n - 1) / 2];
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
        int ans = 0;
        while(left < right) {
            if(sums[left].value + sums[right].value == 0) { // 如果 sum 是 0
                int[] record = new int[sums.length];
                int leftCount = 0;
                int rightCount = 0;
                int low = sums[left].value;
                int high = sums[right].value;

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

                if(low == 0) {
                    System.out.println(leftCount + ", " + rightCount);
                }

                ans += leftCount * rightCount;
            } else if(sums[left].value + sums[right].value > 0) {
                right--;
            } else {
                left++;
            }
        }

        return ans / 3;
    }
}

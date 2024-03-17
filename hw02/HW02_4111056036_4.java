public class HW02_4111056036_4 extends FourSum {
    public HW02_4111056036_4() {

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

    private static class SplitCheck extends Thread {
        private int[] A;
        private int from;
        private int to;
        private int last;
        private int result;

        public SplitCheck(int[] A, int from, int to, int last) {
            this.A = A;
            this.from = from;
            this.to = to;
            this.last = last;
        }

        @Override
        public void run() {
            int ans = 0;
            int start, end, sum, low, high;
            for(int i = this.from; i < this.to; i++) {
                if(i > 0 && this.A[i] == this.A[i - 1]) {
                    continue;
                }
    
                for(int j = i + 1; j < this.last; j++) {
                    if(j > (i + 1) && this.A[j] == this.A[j - 1]) {
                        continue;
                    }
    
                    start = j + 1;
                    end = this.last - 1;
                    while(start < end) {
                        sum = this.A[i] + this.A[j] + this.A[start] + this.A[end];
                        if(sum == 0) {
                            ans++;
                            low = this.A[start];
                            high = this.A[end];
                            start++;
                            end--;
                            while(start < end && this.A[start] == low) {
                                start++;
                                ans++;
                            }
                            while(start < end && this.A[end] == high) {
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

            this.result = ans;
        }

        public int getResult() {
            return this.result;
        }
    }

    @Override
    public int F_sum(int[] A) {
        int n = A.length;
        int ans = 0;
        this.quickSort(A, 0, n - 1);

        int threadNums = Runtime.getRuntime().availableProcessors();
        int amountOnce = n / threadNums;

        SplitCheck[] workQueue = new SplitCheck[threadNums];

        for(int i = 0; i < threadNums - 1; i++) {
            workQueue[i] = new SplitCheck(A, amountOnce * i, amountOnce * (i + 1), n);
            workQueue[i].start();
        }

        workQueue[threadNums - 1] = new SplitCheck(A, amountOnce * (threadNums - 1), n, n);
        workQueue[threadNums - 1].start();

        for(int i = 0; i < threadNums; i++) {
            try {
                workQueue[i].join();
            } catch (InterruptedException e) { }
            
            ans += workQueue[i].getResult();
        }
        
        return ans;
    }
}

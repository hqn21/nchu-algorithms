public class HW02_4111056036_4 extends FourSum {
    int[] A;
    Position[] sums;
    int n;

    public HW02_4111056036_4() {
        
    }

    private int min(int a, int b) {
        if(a < b) {
            return a;
        }
        return b;
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

    private class SplitSum extends Thread {
        int from;
        int to;
        int now;

        public SplitSum(int from, int to) {
            this.from = from;
            this.to = to;
            int up = n - 1;
            int down = n - from;
            this.now = (up + down) * (from) / 2;
        }

        @Override
        public void run() {
            for(int i = this.from; i < this.to; i++) {
                for(int j = i + 1; j < n; j++) {
                    sums[now] = new Position(A[i] + A[j], i, j);
                    now++;
                }
            }
        }
    }

    class QuickSortThread extends Thread {
        private static int threadCount = 0;
        private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();
        private static final int THRESHOLD = 10;
    
        private Position[] arr;
        private int low, high;
    
        public QuickSortThread(Position[] arr, int low, int high) {
            this.arr = arr;
            this.low = low;
            this.high = high;
        }
    
        @Override
        public void run() {
            quickSort(arr, low, high);
        }
    
        private void quickSort(Position[] arr, int low, int high) {
            if (high - low < THRESHOLD) {
                insertionSort(arr, low, high);
            } else if (low < high) {
                int pi = partition(arr, low, high);
    
                boolean createNewThreads = false;
                synchronized (QuickSortThread.class) {
                    if (threadCount < MAX_THREADS - 1) {
                        threadCount += 2;
                        createNewThreads = true;
                    }
                }
    
                if (createNewThreads) {
                    QuickSortThread leftSort = new QuickSortThread(arr, low, pi - 1);
                    QuickSortThread rightSort = new QuickSortThread(arr, pi + 1, high);
    
                    leftSort.start();
                    rightSort.start();
    
                    try {
                        leftSort.join();
                        rightSort.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
    
                    synchronized (QuickSortThread.class) {
                        threadCount -= 2;
                    }
                } else {
                    quickSort(arr, low, pi - 1);
                    quickSort(arr, pi + 1, high);
                }
            }
        }
    
        private void insertionSort(Position[] arr, int low, int high) {
            for (int i = low + 1; i <= high; i++) {
                Position key = arr[i];
                int j = i - 1;
    
                while (j >= low && arr[j].value > key.value) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = key;
            }
        }
    
        private int partition(Position[] arr, int low, int high) {
            int pivot = arr[high].value;
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (arr[j].value < pivot) {
                    i++;
    
                    if (i != j) {
                        Position temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
    
            Position temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
    
            return i + 1;
        }
    }

    @Override
    public int F_sum(int[] A) {
        this.A = A;
        this.n = A.length;
        this.sums = new Position[n * (n - 1) / 2];
        int ans = 0;

        int threadNums = min(Runtime.getRuntime().availableProcessors(), n - 1);
        int amountOnce = n / threadNums;

        SplitSum[] workQueue = new SplitSum[threadNums];

        for(int i = 0; i < threadNums - 1; i++) {
            workQueue[i] = new SplitSum(amountOnce * i, amountOnce * (i + 1));
            workQueue[i].start();
        }

        workQueue[threadNums - 1] = new SplitSum(amountOnce * (threadNums - 1), n);
        workQueue[threadNums - 1].start();

        for(int i = 0; i < threadNums; i++) {
            try {
                workQueue[i].join();
            } catch (InterruptedException e) { }
        }

        QuickSortThread sortThread = new QuickSortThread(this.sums, 0, this.sums.length - 1);
        sortThread.start();
        try {
            sortThread.join();
        } catch (InterruptedException e) { }

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

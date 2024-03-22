public class HW02_4111056036_4 extends FourSum {
    class TableNode {
        int position, value;
        TableNode next;
    
        public TableNode(int position) {
            this.position = position;
            this.value = 0;
            this.next = null;
        }
    }
    
    class SumTable {
        int tableSize;
        TableNode[] table;
    
        public SumTable(int tableSize) {
            this.table = new TableNode[this.tableSize = tableSize];
        }
    
        public int hash(int key) {
            int hashKey = ((key % tableSize) + tableSize) % tableSize;
            while (table[hashKey] != null && table[hashKey].position != key) {
                hashKey = (hashKey + 1) % tableSize;
            }
            return hashKey;
        }
    
        public void put(int sum, int endPosition) {
            TableNode head = table[hash(sum)];
            if (head == null) {
                head = table[hash(sum)] = new TableNode(sum);
            }
    
            head.value++;
    
            for (TableNode node = head; ; node = node.next) {
                if (node != head && node.position == endPosition) {
                    node.value++;
                    return;
                } else if (node.next == null || node.next.position > endPosition) {
                    TableNode newNode = new TableNode(endPosition);
                    newNode.next = node.next;
                    node.next = newNode;
                    newNode.value++;
                    return;
                }
            }
        }
    
        public int count(int sum, int startPosition) {
            TableNode head = table[hash(sum)];
    
            if (head == null) {
                return 0;
            }
    
            int acc = 0;
    
            for (TableNode node = head.next; node != null; node = node.next) {
                if (node.position >= startPosition) {
                    break;
                }
                acc += node.value;
            }
    
            return acc;
        }
    
        public boolean containsKey(int key) {
            return table[hash(key)] != null;
        }
    }
    
    class SplitPut extends Thread {
        int from, to, last;
        SumTable table;
        int[] A;
        
        public SplitPut(int from, int to, int last, SumTable table, int[] A) {
            this.from = from;
            this.to = to;
            this.last = last;
            this.table = table;
            this.A = A;
        }
    
        @Override
        public void run() {
            int sum;
            for(int i = this.from; i < this.to; ++i) {
                for(int j = i + 1; j < this.last; ++j) {
                    sum = A[i] + A[j];
                    if(sum <= 0) {
                        table.put(sum, j);
                    }
                }
            }
        }
    }
    
    class SplitCount extends Thread {
        int from, to, last, result;
        SumTable table;
        int[] A;
    
        public SplitCount(int from, int to, int last, SumTable table, int[] A) {
            this.from = from;
            this.to = to;
            this.last = last;
            this.table = table;
            this.A = A;
        }
    
        @Override
        public void run() {
            int sum;
            int count = 0;
            for(int i = this.from; i < this.to; ++i) {
                for(int j = i + 1; j < this.last; ++j) {
                    sum = A[i] + A[j];
                    if (sum >= 0) {
                        count += table.count(-sum, i);
                    }
                }
            }
            this.result = count;
        }
    
        public int getResult() {
            return this.result;
        }
    }

    @Override
    public int F_sum(int[] A) {
        int count = 0;
        int n = A.length;

        SumTable table = new SumTable(500000);

        java.util.Arrays.sort(A);

        int threadNums = Runtime.getRuntime().availableProcessors();
        int amountOnce = n / threadNums;

        SplitPut[] workQueue = new SplitPut[threadNums];
        SplitCount[] workQueue2 = new SplitCount[threadNums];

        for(int i = 0; i < threadNums - 1; i++) {
            workQueue[i] = new SplitPut(amountOnce * i, amountOnce * (i + 1), n, table, A);
            workQueue[i].start();
        }
        workQueue[threadNums - 1] = new SplitPut(amountOnce * (threadNums - 1), n - 1, n, table, A);
        workQueue[threadNums - 1].start();
        for(int i = 0; i < threadNums; i++) {
            try {
                workQueue[i].join();
            } catch (InterruptedException e) { }
        }

        for(int i = 0; i < threadNums - 1; i++) {
            workQueue2[i] = new SplitCount(amountOnce * i, amountOnce * (i + 1), n, table, A);
            workQueue2[i].start();
        }
        workQueue2[threadNums - 1] = new SplitCount(amountOnce * (threadNums - 1), n - 1, n, table, A);
        workQueue2[threadNums - 1].start();
        for(int i = 0; i < threadNums; i++) {
            try {
                workQueue2[i].join();
            } catch (InterruptedException e) { }
            count += workQueue2[i].getResult();
        }

        return count;
    }
}
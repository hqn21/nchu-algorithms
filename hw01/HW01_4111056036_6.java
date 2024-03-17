public class HW01_4111056036_6 extends ArrayData {
    public HW01_4111056036_6 (int[] A) {
        this.A = A;
    }

    public static void main(String[] args) {

    }

    @Override
    public int max() {
        final int[] max = new int[]{A[0], A[0]};
        
        Thread thread1 = new Thread(() -> {
            for(int i = 1; i < A.length / 2; i++) {
                if(A[i] > max[0]) {
                    max[0] = A[i];
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for(int i = A.length / 2; i < A.length; i++) {
                if(A[i] > max[1]) {
                    max[1] = A[i];
                }
            }
        });
        
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(max[0] > max[1]) {
            return max[0];
        }

        return max[1];
    }

    @Override
    public int min() {
        final int[] min = new int[]{A[0], A[0]};
    
        Thread thread1 = new Thread(() -> {
            for(int i = 1; i < A.length / 2; i++) {
                if(A[i] < min[0]) {
                    min[0] = A[i];
                }
            }
        });
    
        Thread thread2 = new Thread(() -> {
            for(int i = A.length / 2; i < A.length; i++) {
                if(A[i] < min[1]) {
                    min[1] = A[i];
                }
            }
        });
    
        thread1.start();
        thread2.start();
    
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        if(min[0] < min[1]) {
            return min[0];
        }

        return min[1];
    }
}

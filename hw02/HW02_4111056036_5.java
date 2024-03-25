public class HW02_4111056036_5 extends FourSum {
    public HW02_4111056036_5() {
        
    }

    @Override
    public int F_sum(int[] A) {
        int ans = 0;
        int n = A.length;
        int sums[] = new int[19000];
        java.util.Arrays.sort(A);
        for(int i = 0; i < n - 1; ++i) {
            for(int j = n - 1; j > i; --j) {
                if((A[i] + A[j]) < 0) {
                    break;
                }
                ans += sums[A[i] + A[j]];
            }

            for(int j = 0; j < i; ++j) {
                if((A[i] + A[j]) > 0) {
                    break;
                }
                
                ++sums[~(A[i] + A[j]) + 1];
            }
        }

        return ans;
    }
}
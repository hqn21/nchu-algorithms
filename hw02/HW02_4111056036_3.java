public class HW02_4111056036_3 extends FourSum {
    public HW02_4111056036_3() {
        
    }

    @Override
    public int F_sum(int[] A) {
        int ans = 0;
        int n = A.length;
        int sums[] = new int[20000];
        int sum;
        java.util.Arrays.sort(A);
        for(int i = 0; i < n - 1; ++i) {
            for(int j = n - 1; j > i; --j) {
                sum = A[i] + A[j];
                if(sum < 0) {
                    break;
                }
                ans += sums[sum];
            }

            for(int j = 0; j < i; ++j) {
                sum = A[i] + A[j];
                if(sum > 0) {
                    break;
                }
                
                ++sums[-sum];
            }
        }

        return ans;
    }
}
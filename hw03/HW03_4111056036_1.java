public class HW03_4111056036_1 extends DogeCoin {
    @Override
    public int doge(int[] price) {
        int n = price.length;
        int[] dp = new int[n];
        int minimum = 2147483647;
        for(int i = 0; i < n; i++) {
            minimum = Math.min(price[i], minimum);
            dp[i] = minimum;
        }
        int ans = 0;
        for(int i = 0; i < n; i++) {
            ans = Math.max(ans, price[i] - dp[i]);
        }
        return ans;
    }
}

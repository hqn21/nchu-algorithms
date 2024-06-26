public class HW03_4111056036_5 extends DogeCoin {
    @Override
    public int doge(int[] price) {
        int profit = 0;
        int min = price[0];
        int n = price.length;
        for(int i = 1; i < n; ++i) {
            if(price[i] < min) {
                min = price[i];
            } else if(profit < price[i] - min) {
                profit = price[i] - min;
            }
        }
        return profit;
    }
}
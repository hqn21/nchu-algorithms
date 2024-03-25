public class HW03_4111056036_1 extends DogeCoin {
    @Override
    public int doge(int[] price) {
        int profit = 0;
        int min = price[0];
        for(int i = 1; i < price.length; ++i) {
            if(price[i] < min) {
                min = price[i];
            } else if(profit < price[i] - min) {
                profit = price[i] - min;
            }
        }
        return profit;
    }
}
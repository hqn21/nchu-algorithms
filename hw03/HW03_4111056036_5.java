public class HW03_4111056036_5 extends DogeCoin {
    @Override
    public int doge(int[] price) {
        int min = price[0];
        int profit = 0;
        for(int i = 1; i < price.length; i++){
            if(price[i] < min) {
                min = price[i];
            } else {
                profit = Math.max(profit, price[i] - min);
            }
        }
        return profit;
    }
}

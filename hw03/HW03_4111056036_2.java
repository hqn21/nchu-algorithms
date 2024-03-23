public class HW03_4111056036_2 extends DogeCoin {
    @Override
    public int doge(int[] price) {
        int[] minPrice = new int[price.length];
        minPrice[0] = price[0];
        int profit = 0;
        for(int i = 1; i < price.length; i++){
            minPrice[i] = Math.min(minPrice[i-1], price[i]);
            profit = Math.max(profit, price[i] - minPrice[i]);
        }
        return profit;
    }
}
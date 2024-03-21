public class HW03_4111056036_3 extends DogeCoin {
    public HW03_4111056036_3() {
        System.gc();
    }

    @Override
    public int doge(int[] price) {
        int profit = 0;
        int min = price[0];
        for(int i = 1; i < price.length; i++) {
            min = Math.min(min, price[i]);
            profit = Math.max(price[i] - min, profit);
        }
        return profit;
    }
}

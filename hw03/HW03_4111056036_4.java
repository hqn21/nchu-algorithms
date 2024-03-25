public class HW03_4111056036_4 extends DogeCoin {
    @Override
    public int doge(int[] price) {
        int profit = 0;
        int min = price[0];
        int temp;
        int n = price.length;
        for(int i = 1; i < n; ++i) {
            temp = price[i] - min;
            if(temp < 0) {
                min = price[i];
            } else if(profit < temp) {
                profit = temp;
            }
        }
        return profit;
    }
}
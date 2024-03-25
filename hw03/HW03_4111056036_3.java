public class HW03_4111056036_3 extends DogeCoin {
    @Override
    public int doge(int[] price) {
        int n = price.length;
        int maxValue = price[n - 1];
        int maxReturn = 0; 

        for (int i = n - 2; i >= 0; --i){
            if (price[i] > maxValue){
                maxValue = price[i]; 
            } else {
                maxReturn = maxValue - price[i] > maxReturn ? maxValue - price[i] : maxReturn; 
            }
        }

        return maxReturn; 
    }
}
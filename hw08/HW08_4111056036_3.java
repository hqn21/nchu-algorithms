public class HW08_4111056036_3 extends LLK {
    @Override
    public boolean checkLLK(int[][] array) {
        int n = array.length;
        if(n < 3) {
            return true;
        }

        double slope = 1.0 * (array[1][1] - array[0][1]) / (array[1][0] - array[0][0]);

        for (int i = 2; i < array.length; i++) {
            if((1.0 * (array[i][1] - array[0][1]) / (array[i][0] - array[0][0])) != slope) {
                return false;
            }
        }

        return true;
    }
}
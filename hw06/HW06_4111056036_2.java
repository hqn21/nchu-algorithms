public class HW06_4111056036_2 extends SortIsAllYouNeed {
    @Override
    public Double[] sortWhat(Double[] A) {
        java.util.Arrays.parallelSort(A);
        return A;
    }
}

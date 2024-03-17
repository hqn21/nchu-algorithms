// int[] A = {-100, 5, 2222, 45, 666, 90, 87, -55, 123, -88888};
// HW01_4111056036_1 test = new HW01_4111056036_1(A);
// System.out.println(test.max());
// System.out.println(test.min());

public abstract class ArrayData {
    public int[] A;
    public abstract int min();
    public abstract int max();
}
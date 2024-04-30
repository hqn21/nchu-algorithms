public class HW05_4111056036_1 extends WordChain {
    @Override
    public String sequenceProbability(String[] A) {
        String[] words = A[1].split(" ");
        int[] record = new int[500];
        int max = 0;
        int dataId = 0;
        String first = "", second = "", third = "";

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(A[0])) {
                if (i + 1 < words.length) {
                    dataId = words[i + 1].hashCode() % 500;
                    if (++record[dataId] > max) {
                        max = record[dataId];
                        first = words[i + 1];
                    }
                }
            }
        }

        java.util.Arrays.fill(record, 0);
        max = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(first)) {
                if (i + 1 < words.length) {
                    dataId = words[i + 1].hashCode() % 500;
                    if (++record[dataId] > max) {
                        max = record[dataId];
                        second = words[i + 1];
                    }
                }
            }
        }

        java.util.Arrays.fill(record, 0);
        max = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(second)) {
                if (i + 1 < words.length) {
                    dataId = words[i + 1].hashCode() % 500;
                    if (++record[dataId] > max) {
                        max = record[dataId];
                        third = words[i + 1];
                    }
                }
            }
        }

        return A[0] + " " + first + " " + second + " " + third;
    }
}
public class HW05_4111056036_1 extends WordChain {
    @Override
    public String sequenceProbability(String[] A) {
        int firstStart = 0, firstEnd = 0, secondStart = 0, secondEnd = 0, thirdStart = 0, thirdEnd = 0;
        int n = A[1].length();
        int[] record = new int[100];
        boolean insert = false;
        int temp = 0;
        int t;
        int max = 0;
        int i;
        for(i = 0; i < n; i += 3) {
            if(insert) {
                temp = (A[1].charAt(i) + A[1].charAt(i + 1)) % 100;
                if(++record[temp] > max) {
                    max = record[temp];
                    firstStart = i;
                    firstEnd = i + 2;
                }
                insert = false;
                temp = 0;
                continue;
            }
            if(A[1].charAt(i) != A[0].charAt(0) || A[1].charAt(i + 1) != A[0].charAt(1)) {
                continue;
            }
            insert = true;
        }

        t = firstStart + 1;
        insert = false;
        max = 0;
        java.util.Arrays.fill(record, 0);
        for(i = 0; i < n; i += 3) {
            if(insert) {
                temp = (A[1].charAt(i) + A[1].charAt(i + 1)) % 100;
                if(++record[temp] > max) {
                    max = record[temp];
                    secondStart = i;
                    secondEnd = i + 2;
                }
                insert = false;
                temp = 0;
                continue;
            }
            if(A[1].charAt(i) != A[1].charAt(firstStart) || A[1].charAt(i + 1) != A[1].charAt(t)) {
                continue;
            }
            insert = true;
        }

        t = secondStart + 1;
        insert = false;
        max = 0;
        java.util.Arrays.fill(record, 0);
        for(i = 0; i < n; i += 3) {
            if(insert) {
                temp = (A[1].charAt(i) + A[1].charAt(i + 1)) % 100;
                if(++record[temp] > max) {
                    max = record[temp];
                    thirdStart = i;
                    thirdEnd = i + 2;
                }
                insert = false;
                temp = 0;
                continue;
            }
            if(A[1].charAt(i) != A[1].charAt(secondStart) || A[1].charAt(i + 1) != A[1].charAt(t)) {
                continue;
            }
            insert = true;
        }

        return A[0] + " " + A[1].substring(firstStart, firstEnd) + " " + A[1].substring(secondStart, secondEnd) + " " + A[1].substring(thirdStart, thirdEnd);
    }
}
public class HW05_4111056036_1 extends WordChain {
    @Override
    public String sequenceProbability(String[] A) {
        int firstStart = 0, firstEnd = 0, secondStart = 0, secondEnd = 0, thirdStart = 0, thirdEnd = 0;
        int n = A[1].length();
        int[] record = new int[100];
        boolean insert = false;
        int max = 0;
        int i;
        int nowCharStart;
        int nowCharEnd;
        int targetCharStart = A[0].charAt(0);
        int targetCharEnd = A[0].charAt(1);

        for(i = 0; i < n; i += 3) {
            nowCharStart = A[1].charAt(i);
            nowCharEnd = A[1].charAt(i + 1);
            if(insert) {
                nowCharStart = (nowCharStart + nowCharEnd) % 100;
                if(++record[nowCharStart] > max) {
                    max = record[nowCharStart];
                    firstStart = i;
                    firstEnd = i + 2;
                }
                insert = false;
                continue;
            }
            if(nowCharStart != targetCharStart || nowCharEnd != targetCharEnd) {
                continue;
            }
            insert = true;
        }

        insert = false;
        max = 0;
        targetCharStart = A[1].charAt(firstStart);
        targetCharEnd = A[1].charAt(firstStart + 1);
        java.util.Arrays.fill(record, 0);
        for(i = 0; i < n; i += 3) {
            nowCharStart = A[1].charAt(i);
            nowCharEnd = A[1].charAt(i + 1);
            if(insert) {
                nowCharStart = (nowCharStart + nowCharEnd) % 100;
                if(++record[nowCharStart] > max) {
                    max = record[nowCharStart];
                    secondStart = i;
                    secondEnd = i + 2;
                }
                insert = false;
                continue;
            }
            if(nowCharStart != targetCharStart || nowCharEnd != targetCharEnd) {
                continue;
            }
            insert = true;
        }

        insert = false;
        max = 0;
        targetCharStart = A[1].charAt(secondStart);
        targetCharEnd = A[1].charAt(secondStart + 1);
        java.util.Arrays.fill(record, 0);
        for(i = 0; i < n; i += 3) {
            nowCharStart = A[1].charAt(i);
            nowCharEnd = A[1].charAt(i + 1);
            if(insert) {
                nowCharStart = (nowCharStart + nowCharEnd) % 100;
                if(++record[nowCharStart] > max) {
                    max = record[nowCharStart];
                    thirdStart = i;
                    thirdEnd = i + 2;
                }
                insert = false;
                continue;
            }
            if(nowCharStart != targetCharStart || nowCharEnd != targetCharEnd) {
                continue;
            }
            insert = true;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(A[0]);
        sb.append(" ");
        sb.append(A[1], firstStart, firstEnd);
        sb.append(" ");
        sb.append(A[1], secondStart, secondEnd);
        sb.append(" ");
        sb.append(A[1], thirdStart, thirdEnd);

        return sb.toString();
    }
}
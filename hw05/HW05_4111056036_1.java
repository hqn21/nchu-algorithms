public class HW05_4111056036_1 extends WordChain {
    @Override
    public String sequenceProbability(String[] A) {
        int firstStart = 0, firstEnd = 0, secondStart = 0, secondEnd = 0, thirdStart = 0, thirdEnd = 0;
        String first, second, third;
        String target = A[0] + " ";
        int targetLength = target.length();
        int start = A[1].indexOf(target);
        int end;
        int[] record = new int[10];
        int max = 0;
        int dataId = 0;
        while(start != -1) {
            start += targetLength;
            end = A[1].indexOf(" ", start + 1);
            if(end != -1) {
                for(int i = start; i <= end; ++i) {
                    dataId += A[1].charAt(i);
                }
                dataId %= 10;
                if(++record[dataId] > max) {
                    max = record[dataId];
                    firstStart = start;
                    firstEnd = end;
                }
                dataId = 0;
            }
            start = A[1].indexOf(target, start + 1);
        }
        first = A[1].substring(firstStart, firstEnd) + " ";

        targetLength = first.length();
        start = A[1].indexOf(first);
        java.util.Arrays.fill(record, 0);
        max = 0;
        while(start != -1) {
            start += targetLength;
            end = A[1].indexOf(" ", start + 1);
            if(end != -1) {
                for(int i = start; i <= end; ++i) {
                    dataId += A[1].charAt(i);
                }
                dataId %= 10;
                if(++record[dataId] > max) {
                    max = record[dataId];
                    secondStart = start;
                    secondEnd = end;
                }
                dataId = 0;
            }
            start = A[1].indexOf(first, start + 1);
        }
        second = A[1].substring(secondStart, secondEnd) + " ";

        targetLength = second.length();
        start = A[1].indexOf(second);
        java.util.Arrays.fill(record, 0);
        max = 0;
        while(start != -1) {
            start += targetLength;
            end = A[1].indexOf(" ", start + 1);
            if(end != -1) {
                for(int i = start; i <= end; ++i) {
                    dataId += A[1].charAt(i);
                }
                dataId %= 10;
                if(++record[dataId] > max) {
                    max = record[dataId];
                    thirdStart = start;
                    thirdEnd = end;
                }
                dataId = 0;
            }
            start = A[1].indexOf(second, start + 1);
        }
        third = A[1].substring(thirdStart, thirdEnd);

        return target + first + second + third;
    }
}
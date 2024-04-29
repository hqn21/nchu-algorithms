public class HW05_4111056036_2 extends WordChain {
    @Override
    public String sequenceProbability(String[] A) {
        int first = 0, firstStart = 0, firstEnd = 0;
        int second = 0, secondStart = 0, secondEnd = 0;
        int third = 0, thirdStart = 0, thirdEnd = 0;

        int[] record = new int[997];
        int targetLength = A[0].length();
        int dataLength = A[1].length();
        int nowChecking = 0;
        int dataId = 0;
        int prev = 0;

        for(int i = 0; i < dataLength; ++i) {
            while(i < dataLength && A[0].charAt(nowChecking) != A[1].charAt(i)) {
                ++i;
                if(nowChecking > 0) {
                    nowChecking = 0;
                }
            }

            ++nowChecking;

            if(nowChecking == targetLength) {
                if(++i < dataLength && A[1].charAt(i) == ' ') {
                    prev = ++i;
                    while(i < dataLength && A[1].charAt(i) != ' ') {
                        dataId += A[1].charAt(i);
                        ++i;
                    }
                    dataId %= 997;
                    if(++record[dataId] > first) {
                        first = record[dataId];
                        firstStart = prev;
                        firstEnd = i;
                    }
                    dataId = 0;
                }
                nowChecking = 0;
            }
        }

        nowChecking = firstStart;
        record = new int[997];

        for(int i = 0; i < dataLength; ++i) {
            while(i < dataLength && A[1].charAt(nowChecking) != A[1].charAt(i)) {
                ++i;
                if(nowChecking > firstStart) {
                    nowChecking = firstStart;
                }
            }

            ++nowChecking;

            if(nowChecking == firstEnd) {
                if(++i < dataLength && A[1].charAt(i) == ' ') {
                    prev = ++i;
                    while(i < dataLength && A[1].charAt(i) != ' ') {
                        dataId += A[1].charAt(i);
                        ++i;
                    }
                    dataId %= 997;
                    if(++record[dataId] > second) {
                        second = record[dataId];
                        secondStart = prev;
                        secondEnd = i;
                    }
                    dataId = 0;
                }
                nowChecking = firstStart;
            }
        }

        nowChecking = secondStart;
        record = new int[997];

        for(int i = 0; i < dataLength; ++i) {
            while(i < dataLength && A[1].charAt(nowChecking) != A[1].charAt(i)) {
                ++i;
                if(nowChecking > secondStart) {
                    nowChecking = secondStart;
                }
            }

            ++nowChecking;

            if(nowChecking == secondEnd) {
                if(++i < dataLength && A[1].charAt(i) == ' ') {
                    prev = ++i;
                    while(i < dataLength && A[1].charAt(i) != ' ') {
                        dataId += A[1].charAt(i);
                        ++i;
                    }
                    dataId %= 997;
                    if(++record[dataId] > third) {
                        third = record[dataId];
                        thirdStart = prev;
                        thirdEnd = i;
                    }
                    dataId = 0;
                }
                nowChecking = secondStart;
            }
        }

        return A[0] + " " + A[1].substring(firstStart, firstEnd) + " " + A[1].substring(secondStart, secondEnd) + " " + A[1].substring(thirdStart, thirdEnd);
    }
}
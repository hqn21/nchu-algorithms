public class HW05_4111056036_3 extends WordChain {
    class Data {
        String firstString;
        float firstP;
        String secondString;
        float secondP;

        public Data(String firstString, float firstP, String secondString, float secondP) {
            this.firstString = firstString;
            this.firstP = firstP;
            this.secondString = secondString;
            this.secondP = secondP;
        }
    }

    private Data findTarget(String target, String A, boolean checkSecond) {
        int[] record = new int[997];
        int targetLength = target.length();
        int nowChecking = 0;
        int now = 0;
        int n = A.length();
        int first = 0, firstStart = 0, firstEnd = 0;
        int second = 0, secondStart = 0, secondEnd = 0;
        int prev = 0;
        int sum = 0;

        for(int i = 0; i < n; ++i) {
            while(i < n && target.charAt(nowChecking) != A.charAt(i)) {
                ++i;
                if(nowChecking > 0) {
                    nowChecking = 0;
                }
            }

            ++nowChecking;

            if(nowChecking == targetLength) {
                if(++i < n && A.charAt(i) == ' ') {
                    ++sum;
                    prev = ++i;
                    while(i < n && A.charAt(i) != ' ') {
                        now += A.charAt(i);
                        ++i;
                    }
                    now %= 997;
                    if(++record[now] > first) {
                        first = record[now];
                        firstStart = prev;
                        firstEnd = i;
                    } else if(checkSecond && (record[now] > second)) {
                        second = record[now];
                        secondStart = prev;
                        secondEnd = i;
                    }
                    now = 0;
                }
                nowChecking = 0;
            }
        }

        if(checkSecond) {
            return new Data(A.substring(firstStart, firstEnd), (float) first / sum, A.substring(secondStart, secondEnd), (float) second / sum);
        } else {
            return new Data(A.substring(firstStart, firstEnd), (float) first / sum, "", 0);
        }

    }


    @Override
    public String sequenceProbability(String[] A) {
        Data d1 = findTarget(A[0], A[1], true);
        Data d2_1 = findTarget(d1.firstString, A[1], false);
        Data d2_2 = findTarget(d1.secondString, A[1], false);

        Data d3;
        if(d1.firstP * d2_1.firstP > d1.secondP * d2_2.firstP) {
            d3 = findTarget(d2_1.firstString, A[1], false);
            return A[0] + " " + d1.firstString + " " + d2_1.firstString + " " + d3.firstString;
        } else {
            d3 = findTarget(d2_2.firstString, A[1], false);
            return A[0] + " " + d1.firstString + " " + d2_2.firstString + " " + d3.firstString;
        }
    }
}
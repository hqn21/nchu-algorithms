public class HW05_4111056036_5 extends WordChain {
    class Data {
        int firstStart;
        int firstEnd;
        float firstP;
        int secondStart;
        int secondEnd;
        float secondP;

        public Data(int firstStart, int firstEnd, float firstP, int secondStart, int secondEnd, float secondP) {
            this.firstStart = firstStart;
            this.firstEnd = firstEnd;
            this.firstP = firstP;
            this.secondStart = secondStart;
            this.secondEnd = secondEnd;
            this.secondP = secondP;
        }
    }

    private Data findTarget(String target, String A) {
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
                    } else if(record[now] > second) {
                        second = record[now];
                        secondStart = prev;
                        secondEnd = i;
                    }
                    now = 0;
                }
                nowChecking = 0;
            }
        }

        return new Data(firstStart, firstEnd, (float) first / sum, secondStart, secondEnd, (float) second / sum);
    }


    @Override
    public String sequenceProbability(String[] A) {
        Data d1 = findTarget(A[0], A[1]);
        Data d2 = findTarget(A[1].substring(d1.firstStart, d1.firstEnd), A[1]);
        Data d3 = findTarget(A[1].substring(d2.firstStart, d2.firstEnd), A[1]);
        return A[0] + " " + A[1].substring(d1.firstStart, d1.firstEnd) + " " + A[1].substring(d2.firstStart, d2.firstEnd) + " " + A[1].substring(d3.firstStart, d3.firstEnd);
    }
}
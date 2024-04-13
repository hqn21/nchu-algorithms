public class HW04_4111056036_1 extends LanguageModel {
    @Override
    public String nextPredictToken(String[] A) {
        int[] record = new int[997];
        int targetLength = A[0].length();
        int nowChecking = 0;
        int now = 0;
        int n = A[1].length();
        int max = 0;
        int prev = 0;
        int ansStart = 0, ansEnd = 0;

        for(int i = 0; i < n; ++i) {
            while(i < n && A[0].charAt(nowChecking) != A[1].charAt(i)) {
                ++i;
                if(nowChecking > 0) {
                    nowChecking = 0;
                }
            }

            ++nowChecking;

            if(nowChecking == targetLength) {
                if(++i < n && A[1].charAt(i) == ' ') {
                    prev = ++i;
                    while(i < n && A[1].charAt(i) != ' ') {
                        now += A[1].charAt(i);
                        ++i;
                    }
                    now %= 997;
                    if(++record[now] > max) {
                        max = record[now];
                        ansStart = prev;
                        ansEnd = i;
                    }
                    now = 0;
                }
                nowChecking = 0;
            }
        }

        return A[1].substring(ansStart, ansEnd);
    }
}
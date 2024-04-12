public class HW04_4111056036_1 extends LanguageModel {
    @Override
    public String nextPredictToken(String[] A) {
        int[] record = new int[1000];
        int targetLength = A[0].length();
        
        int nowChecking = 0;
        int now = 0;
        int n = A[1].length();
        int max = 0;
        int prev = 0;
        int ansStart = 0, ansEnd = 0;
        boolean insert = false;

        for(int i = 0; i < n; ++i) {
            if(insert) {
                prev = i;
                while(i < n && A[1].charAt(i) != ' ') {
                    now += A[1].charAt(i);
                    ++i;
                }
                now %= 1000;
                if(++record[now] > max) {
                    max = record[now];
                    ansStart = prev;
                    ansEnd = i;
                }
                now = 0;
                insert = false;
            }

            while(i < n && A[0].charAt(nowChecking) != A[1].charAt(i)) {
                ++i;
                nowChecking = 0;
            }

            ++nowChecking;

            if(nowChecking == targetLength) {
                if(i != n - 1 && A[1].charAt(++i) == ' ') {
                    insert = true;
                }
                nowChecking = 0;
            }
        }

        return A[1].substring(ansStart, ansEnd);
    }
}
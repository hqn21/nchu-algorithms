public class HW04_4111056036_2 extends LanguageModel {
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
            if(A[1].charAt(i) == ' ') {
                if(insert) {
                    now %= 1000;
                    if(++record[now] > max) {
                        max = record[now];
                        ansStart = prev;
                        ansEnd = i;
                    }
                    now = 0;
                    insert = false;
                } else {
                    insert = true;
                    prev = i + 1;
                }
            } else if(insert) {
                now += A[1].charAt(i);
            } else {
                if(nowChecking >= targetLength || (A[1].charAt(i) != A[0].charAt(nowChecking))) {
                    while(i < n && A[1].charAt(i) != ' ') {
                        ++i;
                    }
                    nowChecking = 0;
                } else {
                    ++nowChecking;
                }
            }
        }

        if(insert) {
            now %= 1000;
            if(++record[now] > max) {
                max = record[now];
                ansStart = prev;
                ansEnd = n;
            }
        }

        return A[1].substring(ansStart, ansEnd);
    }
}
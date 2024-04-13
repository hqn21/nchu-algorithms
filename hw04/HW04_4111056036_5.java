public class HW04_4111056036_5 extends LanguageModel {
    @Override
    public String nextPredictToken(String[] A) {
        int[] record = new int[997];
        int targetLength = A[0].length();
        int n = A[1].length();
        int max = 0;
        int prev = 0;
        int ansStart = 0, ansEnd = 0;

        int[] lps = computeLPSArray(A[0], targetLength);

        int i = 0;
        int j = 0;
        while (i < n) {
            if (A[0].charAt(j) == A[1].charAt(i)) {
                j++;
                i++;
            }
            if (j == targetLength) {
                if(i < n && A[1].charAt(i) == ' ') {
                    prev = ++i;
                    int now = 0;
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
                }
                j = lps[j - 1];
            } else if (i < n && A[0].charAt(j) != A[1].charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }

        return A[1].substring(ansStart, ansEnd);
    }

    private int[] computeLPSArray(String pat, int M) {
        int len = 0;
        int i = 1;
        int[] lps = new int[M];
        lps[0] = 0;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}
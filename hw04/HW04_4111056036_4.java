public class HW04_4111056036_4 extends LanguageModel {
	@Override
    public String nextPredictToken(String[] A) {
        int[] record = new int[25000];
        int target = A[0].chars().sum() % 25000;
        int now = 0;
        int n = A[1].length();
        int max = 0;
        int temp;
        int prev = 0;
        String ans = "";
        boolean insert = false;

        for(int i = 0; i < n; i++) {
            if(A[1].charAt(i) == ' ') {
                if(now % 25000 == target) {
                    insert = true;
                    now = 0;
                    prev = i + 1;
                    continue;
                }

                if(insert) {
                    temp = now % 25000;
                    if(++record[temp] > max) {
                        max = record[temp];
                        ans = A[1].substring(prev, i);
                    }
                    now = 0;
                    insert = false;
                    prev = i + 1;
                    continue;
                }

                now = 0;
                prev = i + 1;
                continue;
            }

            now += (int) A[1].charAt(i);
        }

        if(insert) {
            temp = now % 25000;
            if(++record[temp] > max) {
                max = record[temp];
                ans = A[1].substring(prev, n);
            }
        }

		return ans;
    }
}
public class HW04_4111056036_1 extends LanguageModel {
	@Override
    public String nextPredictToken(String[] A) {
        int[] record = new int[1000];
        int target = A[0].chars().sum() % 1000;
        int now = 0;
        int n = A[1].length();
        int max = 0;
        int temp;
        int prev = 0;
        StringBuilder ans = new StringBuilder();
        boolean insert = false;

        for(int i = 0; i < n; i++) {
            if(A[1].charAt(i) == ' ') {
                if(now % 1000 == target) {
                    insert = true;
                    now = 0;
                    prev = i + 1;
                    continue;
                }

                if(insert) {
                    temp = now % 1000;
                    if(++record[temp] > max) {
                        max = record[temp];
                        ans.setLength(0);
                        ans.append(A[1], prev, i);
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
            temp = now % 1000;
            if(++record[temp] > max) {
                max = record[temp];
                ans.setLength(0);
                ans.append(A[1], prev, n);
            }
        }

		return ans.toString();
    }
}
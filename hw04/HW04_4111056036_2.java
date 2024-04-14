public class HW04_4111056036_2 extends LanguageModel {
    private class Node {
        int value;
        int from;
        int to;
        Node next;

        public Node(int value, int from, int to) {
            this.value = value;
            this.from = from;
            this.to = to;
            this.next = null;
        }
    }

    @Override
    public String nextPredictToken(String[] A) {
        int[] record = new int[997];
        int targetLength = A[0].length();
        int nowChecking = 0;
        int now = 0;
        int n = A[1].length();
        int prev = 0;
        int ansStart = 0, ansEnd = 0;
        Node head = null;
        Node runner = null;

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
                    if(++record[now] == 1) {
                        if(head == null) {
                            head = new Node(now, prev, i);
                            runner = head;
                        } else {
                            runner.next = new Node(now, prev, i);
                            runner = runner.next;
                        }
                    }
                    now = 0;
                }
                nowChecking = 0;
            }
        }

        int max = 0;
        while(head != null) {
            if(record[head.value] > max) {
                max = record[head.value];
                ansStart = head.from;
                ansEnd = head.to;
            }
            head = head.next;
        }

        return A[1].substring(ansStart, ansEnd);
    }
}
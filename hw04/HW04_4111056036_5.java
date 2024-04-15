public class HW04_4111056036_5 extends LanguageModel {
    private class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    @Override
    public String nextPredictToken(String[] A) {
        int targetLength = A[0].length();
        int nowChecking = 0;
        int now = 0;
        int n = A[1].length();
        int top1 = -1, top2 = -1;
        int count1 = 0, count2 = 0;
        int top1Start = 0, top1End = 0;
        int top2Start = 0, top2End = 0;

        int prev = 0;

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

                    if(head == null) {
                        head = new Node(now);
                        runner = head;
                    } else {
                        runner.next = new Node(now);
                        runner = runner.next;
                    }

                    if(now == top1) {
                        ++count1;
                    } else if(now == top2) {
                        ++count2;
                    } else {
                        if(count1 == 0) {
                            top1 = now;
                            top1Start = prev;
                            top1End = i;
                            ++count1;
                        } else if(count2 == 0) {
                            top2 = now;
                            top2Start = prev;
                            top2End = i;
                            ++count2;
                        } else {
                            --count1;
                            --count2;
                        }
                    }

                    now = 0;
                }
                nowChecking = 0;
            }
        }

        count1 = 0;
        count2 = 0;
        while(head != null) {
            if(head.value == top1) {
                ++count1;
            } else if(head.value == top2) {
                ++count2;
            }
            head = head.next;
        }

        if(count1 > count2) {
            return A[1].substring(top1Start, top1End);
        } else {
            return A[1].substring(top2Start, top2End);
        }
    }
}
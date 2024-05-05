public class HW05_4111056036_1 extends WordChain {
    private static class Position {
        int start;
        int end;

        public Position(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static Position greedyFind(String target, String A) {
        int[] record = new int[100];
        int start = A.indexOf(target);
        int end;
        int targetLength = target.length();
        int dataId = 0;
        int max = 0;
        Position position = new Position(0, 0);

        while(start != -1) {
            start += targetLength;
            end = A.indexOf(" ", start + 1);
            if(end == -1) {
                break;
            }
            for(int i = start; i <= end; ++i) {
                dataId += A.charAt(i);
            }
            dataId %= 100;
            if(++record[dataId] > max) {
                max = record[dataId];
                position.start = start;
                position.end = end;
            }
            dataId = 0;
            start = A.indexOf(target, start);
        }
        return position;
    }

    @Override
    public String sequenceProbability(String[] A) {
        StringBuilder sb = new StringBuilder();
        sb.append(A[0]);
        sb.append(" ");
        int oldLength = sb.length();
        int newLength;
        Position position = greedyFind(sb.toString(), A[1]);
        sb.append(A[1].substring(position.start, position.end));
        sb.append(" ");
        newLength = sb.length();
        position = greedyFind(sb.substring(oldLength, newLength), A[1]);
        oldLength = newLength;
        sb.append(A[1].substring(position.start, position.end));
        sb.append(" ");
        newLength = sb.length();
        position = greedyFind(sb.substring(oldLength, newLength), A[1]);
        sb.append(A[1].substring(position.start, position.end));
        return sb.toString();
    }
}
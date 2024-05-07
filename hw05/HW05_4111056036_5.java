public class HW05_4111056036_5 extends WordChain {
    static class Vertex {
        String word;
        double count;

        Vertex next;

        Vertex(String word, double count) {
            this.word = word;
            this.count = count;
        }

        public void connect(String word) {
            count += 1;

            Vertex current = next;
            while (current != null) {
                if (current.word.equals(word)) {
                    current.count += 1;
                    return;
                }
                current = current.next;
            }

            Vertex newVertex = new Vertex(word, 1);

            newVertex.next = next;
            next = newVertex;
        }
    }

    Vertex[] vertices = new Vertex[512];

    String A1, A0;
    int A1Len;

    String[] path = new String[4];
    double maxProb = 0;

    public void dfs(String current, double prob, int k) {
        if (k == 0) {
            if (prob > maxProb) {
                maxProb = prob;
            }

            return;
        }

        Vertex srcVertex = vertices[current.hashCode() & 511];

        if (srcVertex == null) {
            srcVertex = vertices[current.hashCode() & 511] = new Vertex(current, 0);

            for (int i = A1.indexOf(current); i != -1 && i + 5 <= A1Len; i = A1.indexOf(current, i + 1)) {
                srcVertex.connect(A1.substring(i + 3, i + 5));
            }
        }

        for (Vertex next = srcVertex.next; next != null; next = next.next) {
            double nextProb = prob * next.count / srcVertex.count;
            if (nextProb <= maxProb) {
                continue;
            }

            path[4 - k] = next.word;
            dfs(next.word, prob * next.count / srcVertex.count, k - 1);
        }
    }

    @Override
    public String sequenceProbability(String[] A) {
        A0 = A[0];

        A1 = " " + A[1];
        A1Len = A1.length();

        path[0] = A[0];
        dfs(A[0], 1, 3);

        return String.join(" ", path);
    }
}
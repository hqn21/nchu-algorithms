package hw01_paper;

public class QuickUnionUF {
   private int[] id;

   public QuickUnionUF(int N) {
      id = new int[N];
      for (int i = 0; i < N; i++)
         id[i] = i;
   }

   public int find(int i) {
      while (i != id[i]) {
         i = id[i];
      }
      return i;
   }

   public void union(int p, int q) {
      int proot = find(p);
      int qroot = find(q);
      id[proot] = qroot;
   }
}

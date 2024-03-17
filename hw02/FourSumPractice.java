import java.util.*;

public class FourSumPractice {
    public List<List<Integer>> quadTuple(int[] arr, int len, int sum){
    Map<Integer, Set<List<Integer>>> map = new HashMap<>();
    Set<List<Integer>> res = new HashSet<>();

    for (int i = 0; i < len - 1; i++) {
        for (int j = i + 1; j < len; j++) {
            int requiredSum = sum - (arr[i] + arr[j]);
            if(map.containsKey(requiredSum)){
                for (List<Integer> l : map.get(requiredSum)) {
                    int x = l.get(0);
                    int y = l.get(1);

                    if((x != i && x != j) && (y != i && y != j)){
                        List<Integer> quard = Arrays.asList(arr[i], arr[j], arr[x], arr[y]);
                        Collections.sort(quard);
                        res.add(quard);
                    }
                }
            }
            map.putIfAbsent(arr[i] + arr[j], new HashSet<>());
            map.get(arr[i] + arr[j]).add(Arrays.asList(i,j));
        }
    }
    return new ArrayList<>(res);
}

public static void main(String[] args)
{
    int[] A = {-1, 1, 1, 2, -4, 4, 8, -3};
    int sum = 0;
    FourSumPractice obj = new FourSumPractice();
    List<List<Integer>> l = obj.quadTuple(A, A.length, sum);
    for(List<Integer> sub:l){
        for(int i : sub){
            System.out.print(" "+ i);
        }
        System.out.println(" ");
       }
    }
}
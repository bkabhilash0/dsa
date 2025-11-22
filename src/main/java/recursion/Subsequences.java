package recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsequences {
    void subsequences(List<Integer> arr, List<List<Integer>> res, List<Integer> temp, int i) {
        if (i == arr.size()) {
            res.add(List.copyOf(temp));
            return;
        }
        temp.add(arr.get(i));
        subsequences(arr, res, temp, i + 1);
        temp.removeLast();
        subsequences(arr, res, temp, i + 1);
    }

    public static void main(String[] args) {
        Subsequences sub = new Subsequences();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> arr = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        sub.subsequences(arr, res, new ArrayList<>(), 0);
        System.out.println(res);
    }
}

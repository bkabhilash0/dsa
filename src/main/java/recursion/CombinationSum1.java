package recursion;

import java.util.ArrayList;
import java.util.List;

/*
* Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of
*  candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times.
* Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
*
* Assuming the array is in ascending order
* */

public class CombinationSum1 {
    private void combinations2(int index, int[] arr, int target, ArrayList<Integer> subset, List<List<Integer>> res) {
        if (target == 0) {
            res.add(List.copyOf(subset));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            if (arr[i] > target) {
                break;
            }
            subset.add(arr[i]);
            combinations2(i, arr, target - arr[i], subset, res);
            subset.removeLast();
        }
    }

    private void combinations(int index, int[] arr, int target, ArrayList<Integer> subset,
                              List<List<Integer>> res) {
        if (target == 0) {
            res.add(List.copyOf(subset));
            return;
        }
        if (index == arr.length) {
            return;
        }
        // Assume array is in sorted order
        if (target >= arr[index]) {
            subset.add(arr[index]);
            combinations(index, arr, target - arr[index], subset, res);
            subset.removeLast();
        }
        combinations(index + 1, arr, target, subset, res);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinations(0, candidates, target, new ArrayList<>(), res);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        int target = 7;
        CombinationSum1 comb = new CombinationSum1();
        List<List<Integer>> res = comb.combinationSum(arr, target);
        System.out.println(res);
    }

}

package recursion;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.
 */
public class CombinationSum2 {
    private void combinations2(int index, int[] arr, int target, List<Integer> subset, List<List<Integer>> res) {
        if (target == 0) {
            res.add(List.copyOf(subset));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            if (i > index && arr[i] == arr[i - 1]) {
                continue;
            }
            if (arr[i] > target) {
                break;
            }
            subset.add(arr[i]);
            combinations2(i + 1, arr, target - arr[i], subset, res);
            subset.removeLast();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new java.util.ArrayList<>();
        java.util.Arrays.sort(candidates);
        combinations2(0, candidates, target, new java.util.ArrayList<>(), res);
        return res;
    }

    private void getResults(int[] arr, int sum, List<List<Integer>> result) {
        java.util.Arrays.sort(arr);
        combinations2(0, arr, sum, new ArrayList<>(), result);
    }

    // Anonymous Abstract List is faster as its lazy loaded
    public List<List<Integer>> combinationSum2Optimised(int[] candidates, int target) {
        return new AbstractList<List<Integer>>() {
            List<List<Integer>> res;

            private void init(){
                res = new java.util.ArrayList<>();
                getResults(candidates, target,res);
            }

            @Override
            public List<Integer> get(int index) {
                return res.get(index);
            }

            @Override
            public int size() {
                if(res == null){
                    init();
                }
                return res.size();
            }
        };
    }

    public static void main(String[] args) {
        int[] arr = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        CombinationSum2 comb = new CombinationSum2();
        List<List<Integer>> res = comb.combinationSum2Optimised(arr, target);
        System.out.println(res);
    }
}

package recursion;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class Subsets1 {
    private void subsetsBackTracking(int[] nums, List<List<Integer>> res, List<Integer> subset, int index) {
        if(index == nums.length){
            res.add(List.copyOf(subset));
            return;
        }
        subset.add(nums[index]);
        subsetsBackTracking(nums, res, subset, index + 1);
        subset.removeLast();
        subsetsBackTracking(nums, res, subset, index + 1);
    }

    private void subsets(int[] nums, List<List<Integer>> res, List<Integer> subset, int index) {
        // Can also use backtracking here
        for(int i = index; i < nums.length; i++){
            subset.add(nums[i]);
            subsets(nums, res, subset, i + 1);
            subset.removeLast();
        }
        res.add(List.copyOf(subset));
    }

    private void execute(int[] nums, List<List<Integer>> res) {
        Arrays.sort(nums);
        subsetsBackTracking(nums, res, new java.util.ArrayList<>(), 0);
    }

    public List<List<Integer>> subsets(int[] nums) {
        return new AbstractList<List<Integer>>() {
            List<List<Integer>> res;

            private void init() {
                res = new java.util.ArrayList<>();
                execute(nums, res);
            }

            @Override
            public List<Integer> get(int index) {
                return res.get(index);
            }

            @Override
            public int size() {
                if (res == null) {
                    init();
                }
                return res.size();
            }
        };
    }

    public static void main(String[] args) {
        Subsets1 sub = new Subsets1();
        List<List<Integer>> res = sub.subsets(new int[]{1, 2, 3});
        System.out.println(res);
    }
}

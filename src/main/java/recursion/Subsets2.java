package recursion;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * */
public class Subsets2 {
    private void subsets(int[] nums, List<Integer> subset, List<List<Integer>> result, int index) {
        result.add(List.copyOf(subset));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            subsets(nums, subset, result, i + 1);
            subset.removeLast();
        }
    }

    private void execute(int[] nums, List<List<Integer>> result) {
        Arrays.sort(nums);
        subsets(nums, new java.util.ArrayList<>(), result, 0);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
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
        Subsets2 sub = new Subsets2();
        int[] arr = {4, 4, 4, 1, 4};
        List<List<Integer>> res = sub.subsetsWithDup(arr);
        System.out.println(res);
    }
}

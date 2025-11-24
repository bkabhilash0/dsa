package recursion;

import java.util.AbstractList;
import java.util.List;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: [[1,1,2],[1,2,1],[2,1,1]]
 */
public class Permutations2 {
    private void permuteUnique(int[] nums, List<List<Integer>> res, List<Integer> subset, int[] map) {
        if (subset.size() == nums.length) {
            res.add(List.copyOf(subset));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (map[i] == 1) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && map[i - 1] == 0) {
                continue;
            }
            map[i] = 1;
            subset.add(nums[i]);
            permuteUnique(nums, res, subset, map);
            subset.removeLast();
            map[i] = 0;
        }
    }

    private void execute(int[] nums, List<List<Integer>> res) {
        int[] map = new int[nums.length];
        permuteUnique(nums, res, new java.util.ArrayList<>(), map);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        return new AbstractList<List<Integer>>() {
            List<List<Integer>> res;

            void init() {
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
        Permutations2 perm = new Permutations2();
        int[] arr = {3, 3, 0, 3};
        java.util.Arrays.sort(arr);
        List<List<Integer>> res = perm.permuteUnique(arr);
        System.out.println(res);
    }
}

package recursion;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/*
 * Given an array nums of distinct integers,
 * return all the possible permutations. You can return the answer in any order.
 *    Example 1:
 *    Input: nums = [1,2,3]
 *    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Swap positions of each index
 * */
public class Permutations {
    private void swap(List<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    // Using map method
    private void permuteMap(List<Integer> nums, int index, List<Integer> temp, List<List<Integer>> res, int[] map) {
        if(index == nums.size()){
            res.add(List.copyOf(temp));
            return;
        }
        for(int i = 0; i < nums.size(); i++){
            if(map[i] == 1){
                continue;
            }
            temp.add(nums.get(i));
            map[i] = 1;
            permuteMap(nums, index + 1, temp, res, map);
            temp.removeLast();
            map[i] = 0;
        }
    }

    // Using the swap method
    private void permute(List<Integer> nums, int index, List<List<Integer>> res) {
        if (index == nums.size()) {
            res.add(List.copyOf(nums));
            return;
        }
        for (int i = index; i < nums.size(); i++) {
            swap(nums, index, i);
            permute(nums, index + 1, res);
            swap(nums, index, i);
        }
    }

    private void execute(int[] nums, List<List<Integer>> res) {
        List<Integer> temp = new ArrayList<>();
        for (int num : nums) {
            temp.add(num);
        }
        permute(temp, 0, res);
    }

    private void executeMap(int[] nums, List<List<Integer>> res) {
        List<Integer> temp = new ArrayList<>();
        for (int num : nums) {
            temp.add(num);
        }
        int[] map = new int[nums.length + 1];
        permuteMap(temp, 0, new ArrayList<>(), res, map);
    }

    public List<List<Integer>> permute(int[] nums) {
        return new AbstractList<List<Integer>>() {
            List<List<Integer>> res;

            private void init() {
                res = new ArrayList<>();
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

    public List<List<Integer>> permuteMap(int[] nums) {
        return new AbstractList<List<Integer>>() {
            List<List<Integer>> res;

            private void init() {
                res = new ArrayList<>();
                executeMap(nums, res);
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
        Permutations perm = new Permutations();
        int[] arr = {1, 2, 3};
        List<List<Integer>> res = perm.permute(arr);
        System.out.println(res);
        System.out.println("===================================");
        List<List<Integer>> res2 = perm.permuteMap(arr);
        System.out.println(res2);
    }
}

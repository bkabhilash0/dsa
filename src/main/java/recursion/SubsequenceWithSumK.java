package recursion;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Given an array find all subsequences with the sum K.
 * Subsequences are set of elements in the array in the same order
 */
public class SubsequenceWithSumK {
    List<Integer> oneSubsequenceWithSumK(List<Integer> arr, List<Integer> temp, int sum, int i, int k) {
        if (sum == k) {
            System.out.printf("Subsequence with sum %d is %s\n", k, temp);
            return temp;
        }
        if (i == arr.size()) {
            return new java.util.ArrayList<>();
        }
        temp.add(arr.get(i));
        List<Integer> result = oneSubsequenceWithSumK(arr, temp, sum + arr.get(i), i + 1, k);
        if (!result.isEmpty()) {
            return result;
        }
        temp.removeLast();
        result = oneSubsequenceWithSumK(arr, temp, sum, i + 1, k);
        return result;
    }

    void subsequenceWithSumK(List<Integer> arr, List<Integer> temp, int sum, int i, int k) {
        if (sum == k) {
            System.out.printf("Subsequence with sum %d is %s\n", k, temp);
            return;
        }
        if (i == arr.size()) {
            return;
        }
        temp.add(arr.get(i));
        subsequenceWithSumK(arr, temp, sum + arr.get(i), i + 1, k);
        temp.removeLast();
        subsequenceWithSumK(arr, temp, sum, i + 1, k);
    }

    void subsequenceWithSumKForSortedArray(List<Integer> arr, List<Integer> temp, int target, int i) {
        if (target == 0) {
            System.out.printf("Subsequence with sum %d is %s\n", target, temp);
            return;
        }

        if (i == arr.size()) {
            return;
        }
        int element = arr.get(i);
        if (element <= target) {
            temp.add(element);
            subsequenceWithSumKForSortedArray(arr, temp, target - element, i + 1);
            temp.removeLast();
        }
        subsequenceWithSumKForSortedArray(arr, temp, target, i + 1);
    }

    void subUniqueSubsequenceWithSumK(List<Integer> arr, List<Integer> temp, int target, int i) {
        for (int j = i; j < arr.size(); j++) {
            if (j != i && Objects.equals(arr.get(j), arr.get(j - 1))) {
                continue;
            }
            temp.add(arr.get(j));
            subUniqueSubsequenceWithSumK(arr, temp, target - arr.get(j), j + 1);
            temp.removeLast();
        }
        if (target == 0) {
            System.out.printf("Subsequence with sum %d is %s\n", target, temp);
            return;
        }
    }

    public static void main(String[] args) {
        SubsequenceWithSumK sub = new SubsequenceWithSumK();
        List<Integer> elements = List.of(1, 2, 3, 2, 5);
        sub.subsequenceWithSumK(elements, new java.util.ArrayList<>(), 0, 0, 5);
        System.out.println("----------------------------");
        sub.oneSubsequenceWithSumK(elements, new java.util.ArrayList<>(), 0, 0, 5);
        System.out.println("----------------------------");
        elements = List.of(1, 2, 2, 3, 4, 5);
        sub.subsequenceWithSumKForSortedArray(elements, new java.util.ArrayList<>(), 5, 0);
        System.out.println("----------------------------");
        sub.subUniqueSubsequenceWithSumK(elements, new java.util.ArrayList<>(), 5, 0);
    }
}

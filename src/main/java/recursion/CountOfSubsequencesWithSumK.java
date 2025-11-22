package recursion;

import java.util.List;

public class CountOfSubsequencesWithSumK {
    int countOfSubsequencesWithSumK(List<Integer> arr, List<Integer> temp, int sum, int i, int k) {
        int result = 0;
        for (int j = i; j < arr.size(); j++) {
            temp.add(arr.get(j));
            result += countOfSubsequencesWithSumK(arr, temp, sum + arr.get(j), j + 1, k);
            temp.removeLast();
        }
        if (sum == k) {
            return 1;
        }
        return result;
    }

    int countOfSubsequencesWithSumK(List<Integer> arr, int sum, int i, int k) {
        if (sum == k) {
            return 1;
        }
        if (i == arr.size()) {
            return 0;
        }
        return countOfSubsequencesWithSumK(arr, sum + arr.get(i), i + 1, k) + countOfSubsequencesWithSumK(arr, sum, i + 1, k);
    }

    public static void main(String[] args) {
        CountOfSubsequencesWithSumK sub = new CountOfSubsequencesWithSumK();
        List<Integer> elements = List.of(1, 2, 2, 4, 5);
        int res = sub.countOfSubsequencesWithSumK(elements, 0, 0, 5);
        System.out.printf("The number of subsequences with sum %d is %d\n", 5, res);

        res = sub.countOfSubsequencesWithSumK(elements, new java.util.ArrayList<>(), 0, 0, 5);
        System.out.printf("The number of subsequences with sum %d is %d", 5, res);
    }
}

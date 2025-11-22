package recursion;

/**
 * Count the number of inversions in an array.
 * An inversion is a pair (i, j) such that i < j and arr[i] > arr[j].
 * Example:
 * Input: arr = [2, 4, 1, 3, 5]
 * Output: 3
 * Explanation: The inversions are (2, 1), (4, 1), and (4, 3).
 */
public class CountInversionsInArray {
    private int merge(int l, int mid, int h, int[] arr) {
        int start1 = l;
        int start2 = mid + 1;
        int[] merged = new int[h - l + 1];
        int index = 0;
        int count = 0;

        while (start1 <= mid && start2 <= h) {
            if (arr[start1] <= arr[start2]) {
                merged[index++] = arr[start1];
                start1++;
            } else {
                // If an element from the left subarray is greater than an element from the right subarray,
                // then all remaining elements in the left subarray will also be greater than the current element in the right subarray.
                // Hence, we count all these inversions at once.
                // [5,6,7] and [1,2,3] -> when we find 5>1, then 6>1 and 7>1 are also true.
                count += mid - start1 + 1;
                merged[index++] = arr[start2];
                start2++;
            }
        }

        while (start1 <= mid) {
//            count++;
            merged[index++] = arr[start1];
            start1++;
        }

        while (start2 <= h) {
            merged[index++] = arr[start2];
            start2++;
        }

        for (int i = 0; i < merged.length; i++) {
            arr[l + i] = merged[i];
        }
        return count;
    }

    int execute(int l, int h, int[] arr) {
        if (l >= h) {
            return 0;
        }
        int mid = (l + h) / 2;
        int count = 0;
        count = execute(l, mid, arr);
        count += execute(mid + 1, h, arr);
        count += merge(l, mid, h, arr);
        return count;
    }

    public static void main(String[] args) {
//        int[] arr = {4, 3, 2, 1};
        int[] arr = {5, 3, 2, 1, 4};
        CountInversionsInArray countInversionsInArray = new CountInversionsInArray();
        int res = countInversionsInArray.execute(0, arr.length - 1, arr);
        System.out.println("Number of inversions in the array: " + res);
    }
}

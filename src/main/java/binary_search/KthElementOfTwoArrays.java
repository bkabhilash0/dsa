package binary_search;

/**
 * <a href="https://www.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1">...</a>
 * Given two sorted arrays arr1 and arr2 of size n and m respectively and an integer k.
 * Your task is to find the k-th element in the union of these two arrays.
 * Note: Expected time complexity is O(log(n+m)).
 * Example 1:
 * Input: n = 5, m = 4, k = 5
 * arr1[] = {2, 3, 6, 7, 9}
 * arr2[] = {1, 4, 8, 10}
 * Output: 6
 * Explanation: The union of both arrays is {1,2,3,4,6,7,8,9,10}. The 5th element in this union is 6.
 */
public class KthElementOfTwoArrays {
    // Merge Sort Approach - Merge and Find Kth Element
    // Time Complexity: O(n + m) where n and m are the lengths of and b
    // Space Complexity: O(n + m)
    private int bruteForce(int[] a, int[] b, int k) {
        int[] temp = new int[a.length + b.length];
        int i = 0, j = 0, index = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                temp[index++] = a[i++];
            } else {
                temp[index++] = b[j++];
            }
        }
        while (i < a.length) {
            temp[index++] = a[i++];
        }
        while (j < b.length) {
            temp[index++] = b[j++];
        }
        return temp[k - 1];
    }

    // Better Approach - Merge Until Kth Element
    // Time Complexity: O(k)
    // Space Complexity: O(1)
    private int better(int[] a, int[] b, int k) {
        int n = a.length;
        int m = b.length;
        int i = 0, j = 0, count = 0;
        int kthElement = -1;

        while (i < n && j < m) {
            if (a[i] < b[j]) {
                count++;
                if (count == k) {
                    kthElement = a[i];
                    break;
                }
                i++;
            } else {
                count++;
                if (count == k) {
                    kthElement = b[j];
                    break;
                }
                j++;
            }
        }

        while (i < n && kthElement == -1) {
            count++;
            if (count == k) {
                kthElement = a[i];
                break;
            }
            i++;
        }

        while (j < m && kthElement == -1) {
            count++;
            if (count == k) {
                kthElement = b[j];
                break;
            }
            j++;
        }

        return kthElement;
    }

    private int optimal(int[] a, int[] b, int k) {
        // code here
        if (a.length > b.length) {
            return optimal(b, a, k);
        }

        int start = Math.max(0, k - b.length);
        int end = Math.min(k, a.length);

        while (start <= end) {
            // Mid is the number of elements to take from the first array. The rest will be taken from the second array.
            int mid = (start + end) / 2;
            int elementsFromB = k - mid;

            int l1 = mid > 0 ? a[mid - 1] : Integer.MIN_VALUE;
            int l2 = elementsFromB > 0 ? b[elementsFromB - 1] : Integer.MIN_VALUE;
            int r1 = mid < a.length ? a[mid] : Integer.MAX_VALUE;
            int r2 = elementsFromB < b.length ? b[elementsFromB] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }


    public int kthElement(int[] a, int[] b, int k) {
        // code here
        return optimal(a, b, k);
    }

    public static void main(String[] args) {
        KthElementOfTwoArrays solver = new KthElementOfTwoArrays();
        int[] arr1 = {2, 3, 6, 7, 9};
        int[] arr2 = {1, 4, 8, 10};
        int k = 5;
        int result = solver.kthElement(arr1, arr2, k);
        System.out.println("K-th Element: " + result); // Expected Output: 6
    }
}

package binary_search;

/**
 * <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/">...</a>
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * Example 1:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 */
public class MedianOfTwoSortedArrays {

    // Brute Force Approach - Merge and Find Median
    // Time Complexity: O(m + n) where m and n are the lengths of nums1 and nums2
    // Space Complexity: O(m + n)
    private double bruteForce(int[] nums1, int[] nums2) {
        int[] temp = new int[nums1.length + nums2.length];
        int start1 = 0, start2 = 0, index = 0;
        while (start1 < nums1.length && start2 < nums2.length) {
            if (nums1[start1] < nums2[start2]) {
                temp[index++] = nums1[start1++];
            } else {
                temp[index++] = nums2[start2++];
            }
        }

        while (start1 < nums1.length) {
            temp[index++] = nums1[start1++];
        }

        while (start2 < nums2.length) {
            temp[index++] = nums2[start2++];
        }

        if (temp.length % 2 == 0) {
            return (temp[temp.length / 2] + temp[(temp.length / 2) - 1]) / 2.0;
        } else {
            return temp[temp.length / 2];
        }
    }

    // Better Approach - Merge Until Median
    // Time Complexity: O(m + n) where m and n are the lengths of nums1 and nums2
    // Space Complexity: O(1)
    private double better(int[] nums1, int[] nums2) {
        int requiredElement1 = Integer.MIN_VALUE;
        int requiredElement2 = Integer.MIN_VALUE;
        int start1 = 0, start2 = 0, index = 0;
        int length = nums1.length + nums2.length;
        while (start1 < nums1.length && start2 < nums2.length) {
            if (nums1[start1] < nums2[start2]) {
                if (index == length / 2 - 1) {
                    requiredElement1 = nums1[start1];
                }
                if (index == length / 2) {
                    requiredElement2 = nums1[start1];
                }
                start1++;
            } else {
                if (index == length / 2 - 1) {
                    requiredElement1 = nums2[start2];
                }
                if (index == length / 2) {
                    requiredElement2 = nums2[start2];
                }
                start2++;
            }
            index++;
        }

        while (start1 < nums1.length) {
            if (index == length / 2 - 1) {
                requiredElement1 = nums1[start1];
            }
            if (index == length / 2) {
                requiredElement2 = nums1[start1];
            }
            start1++;
            index++;
        }

        while (start2 < nums2.length) {
            if (index == length / 2 - 1) {
                requiredElement1 = nums2[start2];
            }
            if (index == length / 2) {
                requiredElement2 = nums2[start2];
            }
            start2++;
            index++;
        }
        System.out.println(index);
        if (length % 2 == 0) {
            return (requiredElement1 + requiredElement2) / 2.0;
        } else {
            return requiredElement2;
        }
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(log(min(m, n))) where m and n are the lengths of nums1 and nums2
    // Space Complexity: O(1)
    private double optimal(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return optimal(nums2, nums1);
        }
        int start = 0;
        int end = nums1.length;
        int elementRequiredInAPartition = (nums1.length + nums2.length + 1) / 2;
        while (start <= end) {
            int mid = (start + end) / 2;
            // Mid points to the first element of the right partition
            int partitionNums1 = mid;
            int partitionNums2 = elementRequiredInAPartition - partitionNums1;
            int l1 = partitionNums1 <= 0 ? Integer.MIN_VALUE : nums1[partitionNums1 - 1];
            int l2 = partitionNums2 <= 0 ? Integer.MIN_VALUE : nums2[partitionNums2 - 1];
            int r1 = partitionNums1 >= nums1.length ? Integer.MAX_VALUE : nums1[partitionNums1];
            int r2 = partitionNums2 >= nums2.length ? Integer.MAX_VALUE : nums2[partitionNums2];

            // Check if the partitions are correct
            if (l1 <= r2 && l2 <= r1) {
                if ((nums1.length + nums2.length) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0.0;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return better(nums1, nums2);
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2, 4};
        double result = medianOfTwoSortedArrays.findMedianSortedArrays(nums1, nums2);
        System.out.println("Median of Two Sorted Arrays: " + result); // Expected output: 2.0
    }
}

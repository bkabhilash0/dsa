package binary_search;

import commons.Utils;

/**
 * <a href="https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1">...</a>
 * Given an array arr[] of integers, where each element arr[i] represents the number of pages in the i-th book.
 * You also have an integer k representing the number of students. The task is to allocate books to each student such that:
 * Each student receives least one book.
 * Each student is assigned a contiguous sequence of books.
 * No book is assigned to more than one student.
 * The goal is to minimize the maximum number of pages assigned to any student.
 * In other words, out of all possible allocations, find the arrangement where the student who receives the most pages still has the smallest possible maximum.
 * <p>
 * Note: If it is not possible to allocate books to all students, return -1.
 */
public class AllocateMinimumPages {

    // Can we allocate books such that no student gets more than 'capacity' pages?
    // Can we allocate all books to k students with the given capacity?
    private int getStudentsAssigned(int[] arr, int capacity) {
        int studentsAssigned = 1;
        int currentPages = 0;
        for (int pages : arr) {
            if (pages + currentPages > capacity) {
                currentPages = 0;
                studentsAssigned++;
            }
            currentPages += pages;
        }
        return studentsAssigned;
    }

    // Brute Force Approach - Linear Search
    // Time Complexity: O(n * m) where n is the number of books and m is the range between max pages in a book and sum of all pages
    // Space Complexity: O(1)
    private int bruteForce(int[] arr, int numberOfStudents) {
        // Starting from the maximum number of pages in a single book
        // The capacity must be at least the maximum number of pages in a single book
        int start = Utils.getMax(arr);
        int end = Utils.getSummation(arr);
        for (int i = start; i <= end; i++) {
            int studentsNeeded = getStudentsAssigned(arr, i);
            if (studentsNeeded <= numberOfStudents) {
                return i;
            }
        }
        return -1;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(n * log m) where n is the number of books and m is the range between max pages in a book and sum of all pages
    // Space Complexity: O(1)
    private int optimal(int[] arr, int k) {
        int start = Utils.getMax(arr);
        int end = Utils.getSummation(arr);
        while(start <= end){
            int mid = (start + end) / 2;
            int studentsNeeded = getStudentsAssigned(arr, mid);
            if(studentsNeeded <= k){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return start;
    }

    public int findPages(int[] arr, int numberOfStudents) {
        if(numberOfStudents > arr.length || numberOfStudents <= 0){
            return -1;
        }
        // code here
        return optimal(arr, numberOfStudents);
    }

    public static void main(String[] args) {
        AllocateMinimumPages amp = new AllocateMinimumPages();
        int[] arr = {15, 10, 19, 10, 5, 18, 7};
//        int[] arr = {15, 17, 20};
        int k = 5;
        System.out.println("arr: " + java.util.Arrays.toString(arr));
        System.out.println("\nk: " + k);
        int result = amp.findPages(arr, k);
        System.out.println("Result: " + result);
        // Expected Output: 25
    }
}

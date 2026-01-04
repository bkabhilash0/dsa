# 📘 Array Algorithms - Complete Guide (Beginner & Kid Friendly)

This document explains classic array algorithms in a way that is friendly for beginners (and even kids), with math intuition, step-by-step examples, and real-world uses.

---

## 📑 Table of Contents (Index)

**Quick Navigation:** Click on any problem to jump directly to its detailed explanation.

### 🔵 Subarray Sum Problems (Prefix Sum Pattern)
1. [Prefix Sum Method to Find a Subarray With Sum = K](#1-prefix-sum-method-to-find-a-subarray-with-sum--k)
2. [Prefix Sum Method to Count Subarrays With Sum = K](#2-prefix-sum-method-to-count-the-number-of-subarrays-with-sum--k)

### 🟢 Sorting & Partitioning
3. [Dutch National Flag Algorithm (Sort 0s, 1s, 2s)](#3-dutch-national-flag-algorithm)
4. [Moore's Voting Algorithm (Find Majority Element)](#4-moores-voting-algorithm)

### 🟡 Subarray Problems (Kadane's Algorithm)
5. [Kadane's Algorithm (Maximum Subarray Sum)](#5-kadanes-algorithm-maximum-subarray-sum)
6. [Longest Subarray with Sum K](#6-summary)

### 🟣 Two Pointers Technique
7. [Remove Duplicates from Sorted Array](#7-two-pointers-remove-duplicates-from-sorted-array)
8. [Move Zeros to End](#8-two-pointers-move-zeros-to-end)

### 🔴 XOR & Math Tricks
9. [Missing Number (Sum Method & XOR Method)](#9-missing-number-two-brilliant-methods)
10. [Single Number (Find Unique Element)](#11-single-number-xor-method)

### 🟠 Running Counter / Streak Problems
11. [Max Consecutive Ones](#10-max-consecutive-ones)
12. [Leaders in an Array](#12-leaders-in-an-array)

### 🟤 HashSet / HashMap Problems
13. [Longest Consecutive Sequence](#13-longest-consecutive-sequence)
14. [Union and Intersection of Two Arrays](#18-union-and-intersection-of-two-arrays)

### 🔵 Array Manipulation & Rearrangement
15. [Rotate Array by K Steps](#14-rotate-array-by-k-steps-the-brilliant-reverse-trick)
16. [Rearrange Array Elements by Sign](#15-rearrange-array-elements-by-sign)
17. [Next Permutation](#16-next-permutation-finding-the-next-bigger-arrangement)
18. [Check if Array is Sorted and Rotated](#17-check-if-array-is-sorted-and-rotated)

### 🟢 Stock & Optimization Problems
19. [Best Time to Buy and Sell Stock](#19-best-time-to-buy-and-sell-stock)

### 🟡 Combinatorics & Special Structures
20. [Pascal's Triangle](#20-pascals-triangle)

### 🟣 Matrix (2D Array) Problems
21. [Set Matrix Zeroes](#21-set-matrix-zeroes)
22. [Spiral Matrix Traversal](#22-spiral-matrix-traversal)
23. [Rotate Matrix 90 Degrees](#23-rotate-imagematrix-90-degrees)

### 📊 Summary
24. [Summary of All Algorithms](#24-summary-of-all-algorithms)

---

## 🎯 Quick Pattern Reference

| Pattern | Problems | Key Technique |
|---------|----------|---------------|
| **Prefix Sum** | 1, 2, 6 | HashMap + running sum |
| **Two Pointers** | 7, 8, 14, 18 | slow/fast pointers, reverse |
| **XOR Tricks** | 9, 10 | Bit manipulation |
| **Running Counter** | 11, 12 | Track current & max |
| **HashSet/Map** | 13, 14 | O(1) lookups |
| **Array Rearrange** | 15, 16, 17 | Swap, reverse, partition |
| **Greedy/DP-lite** | 4, 5, 19 | Kadane's, Boyer-Moore |
| **Matrix Ops** | 21, 22, 23 | Boundaries, in-place |
| **Sorting** | 3 | Dutch National Flag |
| **Combinatorics** | 20 | Pascal's Triangle |

---

## 💡 Difficulty Levels

| Difficulty | Problems |
|------------|----------|
| **Easy** | 7, 8, 9, 10, 11, 12 |
| **Easy-Medium** | 1, 2, 14, 15, 18, 19 |
| **Medium** | 3, 4, 5, 6, 13, 16, 17, 20 |
| **Medium-Hard** | 21, 22, 23 |

---

## 🚀 Recommended Learning Order

**For Complete Beginners:**
1. Start with: 10, 7, 8, 9 (simple traversal & two pointers)
2. Then: 11, 12 (running counters)
3. Next: 1, 2, 6 (prefix sum pattern)
4. Continue: 5, 19 (Kadane's algorithm)
5. Advanced: 3, 4, 13, 14, 15, 16, 17
6. Matrix problems: 20, 21, 22, 23

---

## 1. Prefix Sum Method to Find a Subarray With Sum = K

### 1.1 What Is a Prefix Sum?

Given an array `a[0..n-1]`, the **prefix sum** at index `i` is:

\[
\text{prefix}[i] = a[0] + a[1] + \dots + a[i]
\]

So:

- `prefix[0] = a[0]`
- `prefix[1] = a[0] + a[1]`
- `prefix[2] = a[0] + a[1] + a[2]`
- and so on.

### 1.2 Math Idea for Subarray Sum = K

Suppose we want a subarray from index `L` to `R` whose sum is `K`.

The sum of this subarray is:

\[
\text{sum}(L,R) = a[L] + a[L+1] + \dots + a[R]
\]

Using prefix sums:

- `prefix[R] = a[0] + ... + a[R]`
- `prefix[L-1] = a[0] + ... + a[L-1]`

So:

\[
\text{sum}(L,R) = \text{prefix}[R] - \text{prefix}[L-1]
\]

We want this to be equal to `K`:

\[
\text{prefix}[R] - \text{prefix}[L-1] = K
\]

Rearrange:

\[
\text{prefix}[L-1] = \text{prefix}[R] - K
\]

**Key idea:**  
While scanning the array and computing the current prefix sum `P = prefix[R]`,  
if we have seen a prefix sum equal to `P - K` earlier, then there exists a subarray ending at `R` with sum `K`.

We can use a **set** (or map) to remember the prefix sums we have seen.

### 1.3 Step-by-Step Algorithm (Find Any One Subarray)

1. Let `prefix = 0`.
2. Create an empty set `seenPrefix`.
3. Optionally add `0` to the set to allow subarrays starting at index 0.
4. For each index `i` from `0` to `n-1`:
   - Update `prefix += a[i]`.
   - Check if `(prefix - K)` is in `seenPrefix`.
     - If yes → There exists some `L` such that `prefix[L-1] = prefix - K`, so subarray `(L..i)` has sum `K`.
   - Insert `prefix` into `seenPrefix`.

### 1.4 Example

Array: `[3, 4, -7, 1, 2]`  
Goal: find a subarray with sum `K = 0`.

We also add initial prefix `0` to the set.

| i   | a[i] | prefix (P) | P - K = P | Seen Before? | Explanation                          |
|-----|------|------------|-----------|--------------|--------------------------------------|
| -1  | —    | 0          | —         | {0}          | Initial, subarray can start at 0    |
| 0   | 3    | 3          | 3         | No           | Add prefix 3                        |
| 1   | 4    | 7          | 7         | No           | Add prefix 7                        |
| 2   | -7   | 0          | 0         | Yes (0)      | Found subarray (0..2) with sum = 0  |

At index 2, we have `prefix = 0`. Since `0` is already in the set (the initial prefix), that means there is a subarray starting from index 0 to index 2 whose sum is `0`.

You can continue further to find other subarrays if needed.

### 1.5 Where Is This Used?

- Detecting specific patterns in financial transaction streams.
- Finding zero-sum or K-sum intervals in sensor data.
- Debugging where a specific cumulative effect reaches a threshold.
- Game development: detecting score stretches where net gain is K.

---

## 2. Prefix Sum Method to Count the Number of Subarrays With Sum = K

In the previous section, we just checked **if** there is such a subarray.  
Now we want to know **how many** subarrays have sum exactly `K`.

### 2.1 Math Idea (Same, but Counting)

We still use:

\[
\text{prefix}[L-1] = \text{prefix}[R] - K
\]

But now we don’t just want to know if `prefix[R] - K` has occurred before; we want to know **how many times** it has occurred before.

If:

- `prefix[R] = P`
- `prefix[R] - K = X`

Let `freq[X]` be the number of times prefix sum `X` appeared at earlier indices.

Then there are exactly `freq[X]` subarrays ending at `R` with sum K.

### 2.2 Algorithm

1. `prefix = 0`
2. Use a map (dictionary) `freq`, where key = prefix sum, value = how many times it appeared.
3. Initialize `freq[0] = 1` (empty prefix).
4. `count = 0`
5. For each element `a[i]`:
   - `prefix += a[i]`
   - Let `need = prefix - K`
   - If `need` exists in `freq`, add `freq[need]` to `count`
   - Increase `freq[prefix]` by 1
6. At the end, `count` is the total number of subarrays with sum = K.

### 2.3 Example

Array: `[1, 1, 1]`, `K = 2`

Initial:  
`prefix = 0`  
`freq = {0: 1}` (prefix sum 0 seen once)  
`count = 0`

Step-by-step:

#### i = 0, a[0] = 1

- `prefix = 0 + 1 = 1`
- `need = 1 - 2 = -1`
- `freq[-1]` doesn’t exist → add 0 to count
- `freq[1] = 1`

Now:
- `freq = {0: 1, 1: 1}`
- `count = 0`

#### i = 1, a[1] = 1

- `prefix = 1 + 1 = 2`
- `need = 2 - 2 = 0`
- `freq[0] = 1` (we have seen prefix sum 0 once)
- `count += 1` → `count = 1`
- `freq[2] = 1`

Now:
- `freq = {0:1, 1:1, 2:1}`
- `count = 1`

This corresponds to subarray `[1,1]` from indices `(0..1)`.

#### i = 2, a[2] = 1

- `prefix = 2 + 1 = 3`
- `need = 3 - 2 = 1`
- `freq[1] = 1`
- `count += 1` → `count = 2`
- `freq[3] = 1`

Now:
- `freq = {0:1, 1:1, 2:1, 3:1}`
- `count = 2`

The two subarrays with sum = 2 are:
- `[1, 1]` from indices `(0..1)`
- `[1, 1]` from indices `(1..2)`

### 2.4 Real-World Uses

- Counting how many time intervals have a certain total energy usage.
- Finding how many stretches of days have total rainfall K.
- Analytics: how many periods of user activity add up to a certain level.
- Signal processing and anomaly detection.

---

## 3. Dutch National Flag Algorithm

This algorithm is used to sort an array of **three distinct values**, often 0, 1, and 2.

Imagine balls of three colors: red (0), white (1), blue (2).  
We want to arrange them in this order: all reds, then whites, then blues.

### 3.1 Intuition

We maintain three regions:

1. `0` to `low - 1` → all 0s
2. `low` to `mid - 1` → all 1s
3. `high + 1` to `n - 1` → all 2s

The part from `mid` to `high` is **unknown** and will be processed.

We use three pointers:

- `low` → boundary for 0s
- `mid` → current element to look at
- `high` → boundary for 2s

### 3.2 Algorithm Steps

While `mid <= high`:

- If `a[mid] == 0`:
  - Swap `a[low]` and `a[mid]`
  - `low++`, `mid++`
- Else if `a[mid] == 1`:
  - `mid++`
- Else if `a[mid] == 2`:
  - Swap `a[mid]` and `a[high]`
  - `high--` (do **not** increase `mid` yet, because we must check the new `a[mid]`)

### 3.3 Example

Array: `[2, 0, 1, 2, 1, 0]`  

Initial:
- `low = 0`
- `mid = 0`
- `high = 5`

We’ll track the array at each step:

1. `mid = 0`, `a[mid] = 2`  
   → Swap `a[mid]` and `a[high]` → swap positions 0 and 5  
   → Array becomes: `[0, 0, 1, 2, 1, 2]`  
   → `high = 4` (mid stays 0)

2. `mid = 0`, `a[mid] = 0`  
   → Swap `a[low]` and `a[mid]` (same index, so no change)  
   → `low = 1`, `mid = 1`  
   → Array: `[0, 0, 1, 2, 1, 2]`

3. `mid = 1`, `a[mid] = 0`  
   → Swap `a[low]` and `a[mid]` (again same index)  
   → `low = 2`, `mid = 2`  
   → Array: `[0, 0, 1, 2, 1, 2]`

4. `mid = 2`, `a[mid] = 1`  
   → Just `mid++`  
   → `mid = 3`

5. `mid = 3`, `a[mid] = 2`  
   → Swap `a[mid]` and `a[high]` → swap indices 3 and 4  
   → Array: `[0, 0, 1, 1, 2, 2]`  
   → `high = 3`

6. Now `mid = 3`, `high = 3`, `a[mid] = 2`  
   → Swap `a[mid]` and `a[high]` (same index)  
   → `high = 2`  

Now `mid (3) > high (2)`, we stop.

Final array: `[0, 0, 1, 1, 2, 2]`

### 3.4 Use Cases

- Sorting colors in image processing (three classes).
- Partitioning array into three priority segments (low, medium, high).
- Used internally in some variants of QuickSort (three-way partitioning).
- Grouping users or items into three known categories efficiently.

---

## 4. Moore’s Voting Algorithm

This algorithm finds a **majority element**: an element that appears **more than n/2 times** in an array, if such an element exists.

### 4.1 Intuition

Think of each element as a “vote” for a candidate.

- If two different elements meet, they “cancel each other out”.
- If there is a majority element, it will survive these cancellations.

### 4.2 Algorithm

We keep:

- `candidate` → current guess of majority
- `count` → how strong this candidate is

Steps:

1. Set `candidate = None`, `count = 0`
2. For each element `x` in the array:
   - If `count == 0`, set `candidate = x`
   - If `x == candidate`, do `count++`
   - Else, do `count--`
3. At the end, `candidate` is the majority **candidate**.

> Note: To be 100% sure, you usually do one more pass to **count how many times `candidate` appears** and check if it's actually > n/2.

### 4.3 Example

Array: `[2, 2, 1, 1, 1, 2, 2]`

Step-by-step:

- Start: `candidate = None`, `count = 0`

1. x = 2  
   - `count == 0` → `candidate = 2`  
   - `count = 1`
2. x = 2  
   - `x == candidate` → `count = 2`
3. x = 1  
   - `x != candidate` → `count = 1`
4. x = 1  
   - `x != candidate` → `count = 0`
5. x = 1  
   - `count == 0` → `candidate = 1`, `count = 1`
6. x = 2  
   - `x != candidate` → `count = 0`
7. x = 2  
   - `count == 0` → `candidate = 2`, `count = 1`

End result: `candidate = 2`

If we count 2s in the array:
- 2 appears 4 times
- array length n = 7
- 4 > 7/2 = 3.5 → Yes, 2 is the majority.

### 4.4 Use Cases

- Finding the most common choice in a survey quickly.
- Real-time stream processing to find dominant event type.
- In networking, to agree on a common value in some consensus settings (as a part of bigger systems).
- Finding most-used feature or button in app logs (if majority exists).

---

## 5. Kadane’s Algorithm (Maximum Subarray Sum)

Given an array of integers (may include negatives), find the **maximum possible sum of a contiguous subarray**.

### 5.1 Intuition

At each position, you have two choices:

- Either **extend** the previous subarray by including `a[i]`,  
- Or **start a new subarray** at `a[i]`.

So we maintain:

- `currentMax` = maximum sum of subarray ending exactly at index i.
- `globalMax` = maximum sum of any subarray found so far.

Math formula:

\[
\text{currentMax} = \max(a[i],\ currentMax + a[i])
\]

\[
\text{globalMax} = \max(globalMax,\ currentMax)
\]

### 5.2 Algorithm

1. Initialize `currentMax = a[0]`, `globalMax = a[0]`
2. For each index `i` from 1 to n-1:
   - `currentMax = max(a[i], currentMax + a[i])`
   - `globalMax = max(globalMax, currentMax)`
3. At the end, `globalMax` is the answer.

### 5.3 Example

Array: `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`

We’ll compute:

| i | a[i] | currentMax                         | globalMax |
|---|------|------------------------------------|-----------|
| 0 | -2   | -2                                 | -2        |
| 1 | 1    | max(1, -2 + 1) = 1                 | 1         |
| 2 | -3   | max(-3, 1 + -3) = -2               | 1         |
| 3 | 4    | max(4, -2 + 4) = 4                 | 4         |
| 4 | -1   | max(-1, 4 + -1) = 3                | 4         |
| 5 | 2    | max(2, 3 + 2) = 5                  | 5         |
| 6 | 1    | max(1, 5 + 1) = 6                  | 6         |
| 7 | -5   | max(-5, 6 + -5) = 1                | 6         |
| 8 | 4    | max(4, 1 + 4) = 5                  | 6         |

So, `globalMax = 6`.

The subarray that gives this sum is `[4, -1, 2, 1]`.

### 5.4 Use Cases

- **Stock market**: maximum profit over a contiguous time if we treat daily gain/loss as array values (simplified model).
- **Weather**: longest stretch of days with highest total temperature change.
- **Gaming**: best streak of scores.
- **Signal processing**: find the strongest continuous signal segment.

---

## 6. Summary

In this document, we learned:

1. **Prefix Sum (find subarray with sum K)**  
   - Math: `prefix[R] - prefix[L-1] = K`  
   - Use a set or map to detect if `prefix - K` was seen before.

2. **Prefix Sum (count subarrays with sum K)**  
   - Count frequencies of prefix sums in a map.  
   - Each time, add `freq[prefix - K]` to the answer.

3. **Dutch National Flag Algorithm**  
   - Three pointers (`low`, `mid`, `high`) to sort 0s, 1s, and 2s in one pass.

4. **Moore’s Voting Algorithm**  
   - Cancels different elements and finds majority candidate in O(n) time and O(1) space.

5. **Kadane’s Algorithm**  
   - Dynamic choice at each index: extend or restart.  
   - Tracks `currentMax` and `globalMax` to find max subarray sum.

You can now use this `.md` file as notes, documentation, or a study guide!

---

## 7. Two Pointers: Remove Duplicates from Sorted Array

### 7.1 Problem Statement

**Given:** A sorted array with duplicate elements.  
**Goal:** Remove duplicates **in-place** (without creating a new array) and return the count of unique elements.  
**Important:** The first `k` positions should contain the unique elements in sorted order.

**Example Input:** `[1, 1, 2, 2, 3, 4, 4]`  
**Expected Output:** `4` (representing 4 unique elements)  
**Modified Array:** `[1, 2, 3, 4, _, _, _]` (only first 4 positions matter)

### 7.2 Intuition – Why Two Pointers?

Think of this like **organizing books on a shelf**:
- You have a shelf with many duplicate books (already sorted by title).
- You want to keep only one copy of each book.
- You use your **left hand** (slow pointer) to mark where the next unique book should go.
- You use your **right hand** (fast pointer) to scan through all books.
- When you find a new unique book, you place it at the slow pointer position.

**Key insight:** Since the array is **sorted**, all duplicates are **grouped together**. So if `arr[fast] != arr[slow]`, we've found a new unique element!

### 7.3 Algorithm (Step-by-Step)

```
1. If array is empty or has 1 element, return its length (already unique).

2. Initialize slow = 0
   (The first element is always unique, so it stays at position 0)

3. For fast from 1 to n-1:
   
   a. Check if arr[fast] is different from arr[slow]:
      
      If YES (found a new unique element):
         - Move slow one step forward: slow++
         - Place the new unique element at slow position: arr[slow] = arr[fast]
      
      If NO (it's a duplicate):
         - Just move fast forward (do nothing with slow)

4. Return slow + 1
   (This is the count of unique elements)
```

**Time Complexity:** O(n) – single pass through array  
**Space Complexity:** O(1) – only using two pointers

### 7.4 Comprehensive Dry Run

Let's trace through `[1, 1, 2, 2, 3, 4, 4]` step by step:

**Initial State:**
```
Array: [1, 1, 2, 2, 3, 4, 4]
Index:  0  1  2  3  4  5  6
        ↑
      slow (0)
```

---

**Step 1:** fast = 1
```
Array: [1, 1, 2, 2, 3, 4, 4]
        ↑  ↑
      slow fast

Check: arr[fast] = 1, arr[slow] = 1
Are they different? NO (1 == 1)
Action: Skip (it's a duplicate)
```

---

**Step 2:** fast = 2
```
Array: [1, 1, 2, 2, 3, 4, 4]
        ↑     ↑
      slow  fast

Check: arr[fast] = 2, arr[slow] = 1
Are they different? YES! (2 != 1)
Action: 
  - slow++ → slow becomes 1
  - arr[slow] = arr[fast] → arr[1] = 2

After: [1, 2, 2, 2, 3, 4, 4]
           ↑
         slow
```

---

**Step 3:** fast = 3
```
Array: [1, 2, 2, 2, 3, 4, 4]
           ↑  ↑
         slow fast

Check: arr[fast] = 2, arr[slow] = 2
Are they different? NO (2 == 2)
Action: Skip (duplicate)
```

---

**Step 4:** fast = 4
```
Array: [1, 2, 2, 2, 3, 4, 4]
           ↑        ↑
         slow     fast

Check: arr[fast] = 3, arr[slow] = 2
Are they different? YES! (3 != 2)
Action:
  - slow++ → slow becomes 2
  - arr[slow] = arr[fast] → arr[2] = 3

After: [1, 2, 3, 2, 3, 4, 4]
              ↑
            slow
```

---

**Step 5:** fast = 5
```
Array: [1, 2, 3, 2, 3, 4, 4]
              ↑        ↑
            slow     fast

Check: arr[fast] = 4, arr[slow] = 3
Are they different? YES! (4 != 3)
Action:
  - slow++ → slow becomes 3
  - arr[slow] = arr[fast] → arr[3] = 4

After: [1, 2, 3, 4, 3, 4, 4]
                 ↑
               slow
```

---

**Step 6:** fast = 6
```
Array: [1, 2, 3, 4, 3, 4, 4]
                 ↑        ↑
               slow     fast

Check: arr[fast] = 4, arr[slow] = 4
Are they different? NO (4 == 4)
Action: Skip (duplicate)
```

---

**Final Result:**
```
Array: [1, 2, 3, 4, _, _, _]
                 ↑
               slow (position 3)

Unique count = slow + 1 = 3 + 1 = 4
The first 4 elements are: [1, 2, 3, 4]
```

### 7.5 Visual Summary Table

| Step | fast | slow | arr[fast] | arr[slow] | Different? | Action | Array State |
|------|------|------|-----------|-----------|------------|--------|-------------|
| Init | -    | 0    | -         | 1         | -          | -      | [1,1,2,2,3,4,4] |
| 1    | 1    | 0    | 1         | 1         | ❌ No      | Skip   | [1,1,2,2,3,4,4] |
| 2    | 2    | 0→1  | 2         | 1         | ✅ Yes     | Move & Copy | [1,**2**,2,2,3,4,4] |
| 3    | 3    | 1    | 2         | 2         | ❌ No      | Skip   | [1,2,2,2,3,4,4] |
| 4    | 4    | 1→2  | 3         | 2         | ✅ Yes     | Move & Copy | [1,2,**3**,2,3,4,4] |
| 5    | 5    | 2→3  | 4         | 3         | ✅ Yes     | Move & Copy | [1,2,3,**4**,3,4,4] |
| 6    | 6    | 3    | 4         | 4         | ❌ No      | Skip   | [1,2,3,4,3,4,4] |

**Return:** `slow + 1 = 4`

### 7.6 Another Example (Shorter Array)

Array: `[0, 0, 1, 1, 1, 2]`

**Dry Run:**
- slow = 0, fast = 1: arr[1]=0 == arr[0]=0 → Skip
- slow = 0, fast = 2: arr[2]=1 != arr[0]=0 → slow=1, arr[1]=1 → `[0,1,1,1,1,2]`
- slow = 1, fast = 3: arr[3]=1 == arr[1]=1 → Skip
- slow = 1, fast = 4: arr[4]=1 == arr[1]=1 → Skip
- slow = 1, fast = 5: arr[5]=2 != arr[1]=1 → slow=2, arr[2]=2 → `[0,1,2,1,1,2]`

**Result:** `[0, 1, 2, _, _, _]` → Count = 3

### 7.7 Common Mistakes & Edge Cases

**Mistake 1:** Starting fast from 0 instead of 1
- The first element is always unique, so fast should start from 1.

**Mistake 2:** Forgetting to increment slow before assigning
- We need `slow++` FIRST, then `arr[slow] = arr[fast]`.

**Edge Cases:**
- Empty array `[]` → return 0
- Single element `[5]` → return 1
- All same `[7,7,7,7]` → return 1
- All unique `[1,2,3,4]` → return 4

### 7.8 Use Cases (Real World)

1. **Database Query Results:** Remove duplicate rows from sorted results.
2. **Log File Analysis:** Clean up sorted timestamps with duplicate entries.
3. **Data Preprocessing:** Prepare sorted data for binary search (requires unique elements).
4. **Memory-Efficient Deduplication:** When you can't afford to create a new array.
5. **Sensor Data:** Remove duplicate readings from sorted sensor logs.

---

## 8. Two Pointers: Move Zeros to End

### 8.1 Problem Statement

**Given:** An array with some zeros and non-zero numbers.  
**Goal:** Move all zeros to the **end** of the array while maintaining the **relative order** of non-zero elements.  
**Constraint:** Modify the array **in-place** (no extra array allowed).

**Example Input:** `[0, 1, 0, 3, 12]`  
**Expected Output:** `[1, 3, 12, 0, 0]`

**Key Point:** The order of non-zero elements must stay the same (1 comes before 3, which comes before 12).

### 8.2 Intuition – The "Snowplow" Analogy

Imagine you're driving a snowplow down a street:
- The **snowplow** (slow pointer) marks where the next non-zero element should go.
- You **drive along** the street (fast pointer scanning the array).
- When you find a **non-zero element** (non-snow item), you push it to the snowplow position.
- All zeros (snow) naturally get pushed to the end.

### 8.3 Two Approaches

#### Approach 1: Copy Method (Two Passes)
1. Use `slow` pointer to track position for next non-zero.
2. Copy all non-zero elements to the front.
3. Fill remaining positions with zeros.

#### Approach 2: Swap Method (One Pass) ⭐ **Optimal**
1. Use `slow` to track position for next non-zero.
2. When you find a non-zero at `fast`, **swap** it with position `slow`.
3. This automatically pushes zeros backward!

### 8.4 Algorithm (Swap Method – Recommended)

```
1. Initialize slow = 0
   (This marks where the next non-zero should go)

2. For fast from 0 to n-1:
   
   a. If arr[fast] != 0 (found a non-zero):
      
      - Swap arr[slow] and arr[fast]
      - slow++
      
   b. Else (it's a zero):
      
      - Just move fast forward (zeros will be left behind)

3. Done! All zeros are now at the end.
```

**Time Complexity:** O(n) – single pass  
**Space Complexity:** O(1) – only two pointers

### 8.5 Comprehensive Dry Run (Swap Method)

Let's trace `[0, 1, 0, 3, 12]` step by step:

**Initial State:**
```
Array: [0, 1, 0, 3, 12]
Index:  0  1  2  3  4
        ↑
      slow
      fast
```

---

**Step 1:** fast = 0
```
Array: [0, 1, 0, 3, 12]
        ↑
      slow
      fast

Check: arr[fast] = 0
Is it non-zero? NO
Action: Skip (don't move slow, just increment fast)
```

---

**Step 2:** fast = 1
```
Array: [0, 1, 0, 3, 12]
        ↑  ↑
      slow fast

Check: arr[fast] = 1
Is it non-zero? YES!
Action: Swap arr[slow] and arr[fast]
  - Swap arr[0] and arr[1] → swap(0, 1)
  - slow++ → slow becomes 1

After swap:
Array: [1, 0, 0, 3, 12]
           ↑
         slow
```
**Visual:** The 1 moved to the front, and 0 moved to where 1 was!

---

**Step 3:** fast = 2
```
Array: [1, 0, 0, 3, 12]
           ↑  ↑
         slow fast

Check: arr[fast] = 0
Is it non-zero? NO
Action: Skip
```

---

**Step 4:** fast = 3
```
Array: [1, 0, 0, 3, 12]
           ↑     ↑
         slow  fast

Check: arr[fast] = 3
Is it non-zero? YES!
Action: Swap arr[slow] and arr[fast]
  - Swap arr[1] and arr[3] → swap(0, 3)
  - slow++ → slow becomes 2

After swap:
Array: [1, 3, 0, 0, 12]
              ↑
            slow
```
**Visual:** The 3 jumped over the zeros to position 1!

---

**Step 5:** fast = 4
```
Array: [1, 3, 0, 0, 12]
              ↑      ↑
            slow   fast

Check: arr[fast] = 12
Is it non-zero? YES!
Action: Swap arr[slow] and arr[fast]
  - Swap arr[2] and arr[4] → swap(0, 12)
  - slow++ → slow becomes 3

After swap:
Array: [1, 3, 12, 0, 0]
                 ↑
               slow
```
**Visual:** The 12 jumped to position 2!

---

**Final Result:**
```
Array: [1, 3, 12, 0, 0]

✅ All non-zeros [1, 3, 12] are at the front in original order
✅ All zeros [0, 0] are at the end
```

### 8.6 Visual Step-by-Step Summary

```
Initial:  [0, 1, 0, 3, 12]
          ↑
        slow/fast

After 1:  [1, 0, 0, 3, 12]  (swapped 0 and 1)
             ↑
           slow

After 3:  [1, 3, 0, 0, 12]  (swapped 0 and 3)
                ↑
              slow

After 12: [1, 3, 12, 0, 0]  (swapped 0 and 12)
                   ↑
                 slow

DONE! ✅
```

### 8.7 Detailed Trace Table

| Step | fast | slow | arr[fast] | arr[slow] | Non-zero? | Action | Array After Step |
|------|------|------|-----------|-----------|-----------|--------|------------------|
| Init | 0    | 0    | 0         | 0         | -         | -      | [0,1,0,3,12]     |
| 1    | 0    | 0    | 0         | 0         | ❌ No     | Skip   | [0,1,0,3,12]     |
| 2    | 1    | 0→1  | 1         | 0         | ✅ Yes    | Swap(0,1) | [**1**,0,0,3,12] |
| 3    | 2    | 1    | 0         | 0         | ❌ No     | Skip   | [1,0,0,3,12]     |
| 4    | 3    | 1→2  | 3         | 0         | ✅ Yes    | Swap(1,3) | [1,**3**,0,0,12] |
| 5    | 4    | 2→3  | 12        | 0         | ✅ Yes    | Swap(2,4) | [1,3,**12**,0,0] |

### 8.8 Another Example (More Zeros)

Array: `[0, 0, 1]`

**Dry Run:**
```
Initial: [0, 0, 1]  slow=0, fast=0

fast=0: arr[0]=0 → Skip → [0,0,1]  slow=0
fast=1: arr[1]=0 → Skip → [0,0,1]  slow=0
fast=2: arr[2]=1 → Swap arr[0] and arr[2] → [1,0,0]  slow=1

Result: [1, 0, 0] ✅
```

### 8.9 Why Swap Method is Better than Copy Method

**Copy Method (2 passes):**
```java
// Pass 1: Copy non-zeros
slow = 0
for each element:
    if element != 0:
        arr[slow] = element
        slow++

// Pass 2: Fill zeros
for i from slow to n-1:
    arr[i] = 0
```

**Swap Method (1 pass):**
```java
slow = 0
for fast from 0 to n-1:
    if arr[fast] != 0:
        swap(arr[slow], arr[fast])
        slow++
```

✅ Swap method is more elegant and does it in one pass!

### 8.10 Edge Cases

| Input | Output | Explanation |
|-------|--------|-------------|
| `[0]` | `[0]` | Single zero stays |
| `[1]` | `[1]` | Single non-zero stays |
| `[0,0,0]` | `[0,0,0]` | All zeros, no change |
| `[1,2,3]` | `[1,2,3]` | No zeros, no change |
| `[1,0,2,0,3]` | `[1,2,3,0,0]` | Multiple zeros pushed |

### 8.11 Common Mistakes

❌ **Mistake 1:** Swapping even when arr[fast] is 0
- Only swap when you find a non-zero!

❌ **Mistake 2:** Incrementing slow even when skipping
- slow should only increment after a swap.

❌ **Mistake 3:** Starting slow from 1 instead of 0
- slow should start at 0 to handle cases where first element is non-zero.

### 8.12 Use Cases (Real World)

1. **UI Rendering:** Move inactive/hidden items to bottom of a list.
2. **Data Processing:** Separate valid entries from null/empty entries.
3. **Game Development:** Move destroyed/inactive game objects to end of array.
4. **File Systems:** Move deleted file markers to end while maintaining order.
5. **Task Schedulers:** Push completed/cancelled tasks to end of queue.

---

## 9. Missing Number (Two Brilliant Methods!)

### 9.1 Problem Statement

**Given:** An array containing `n` **distinct** numbers taken from the range `0, 1, 2, ..., n`.  
**Goal:** Find the **one missing number** from this range.

**Example Input:** `[3, 0, 1]` (n = 3, so range is 0 to 3)  
**Expected Output:** `2` (the missing number)

**Why this works:** If we have n distinct numbers from range 0 to n, exactly ONE number is missing.

### 9.2 Understanding the Problem

Let's visualize:

```
Range 0 to 3: [0, 1, 2, 3]  ← Complete set
Given array:  [3, 0, 1]     ← One number missing
Missing:       2            ← This is what we need to find!
```

**Key insight:** We know the **complete range**, and we know **what we have**. The difference tells us what's missing!

### 9.3 Method 1: Mathematical Sum Method 🧮

#### Concept: Using Sum Formula

Remember from school: Sum of first n natural numbers = `n × (n + 1) / 2`

For range 0 to n:
- **Expected sum** = 0 + 1 + 2 + ... + n = `n × (n + 1) / 2`
- **Actual sum** = sum of all elements in the array
- **Missing number** = Expected sum - Actual sum

It's like having a jar with numbered candies:
- You know there should be candies numbered 0 to 5 (6 candies total)
- You count and find the total is less than expected
- The difference is the missing candy number!

#### Algorithm

```
1. Calculate n = length of array

2. Calculate expected_sum = n × (n + 1) / 2

3. Calculate actual_sum = sum of all elements in array

4. Return expected_sum - actual_sum
```

**Time Complexity:** O(n) – one pass to sum  
**Space Complexity:** O(1) – only variables

#### Comprehensive Dry Run (Sum Method)

**Example:** `[3, 0, 1]`

```
Step 1: Find n
  n = length of array = 3
  So the complete range is 0 to 3: [0, 1, 2, 3]

Step 2: Calculate expected sum
  Formula: n × (n + 1) / 2
  = 3 × (3 + 1) / 2
  = 3 × 4 / 2
  = 12 / 2
  = 6
  
  Verification: 0 + 1 + 2 + 3 = 6 ✅

Step 3: Calculate actual sum
  Sum of [3, 0, 1]
  = 3 + 0 + 1
  = 4

Step 4: Find missing number
  Missing = Expected - Actual
  = 6 - 4
  = 2 ✅

Answer: 2
```

#### Visual Representation

```
Complete range: 0 + 1 + 2 + 3 = 6
                ✅  ✅  ❌  ✅
Given array:    0 + 1 +    + 3 = 4
                          ↑
                      Missing = 6 - 4 = 2
```

### 9.4 Method 2: XOR Bit Manipulation Method ⚡

#### Concept: XOR Properties

XOR (exclusive OR) has magical properties:
1. `a ^ a = 0` (any number XOR itself = 0)
2. `a ^ 0 = a` (any number XOR 0 = itself)
3. XOR is **commutative**: `a ^ b = b ^ a`
4. XOR is **associative**: `(a ^ b) ^ c = a ^ (b ^ c)`

**Magic trick:** 
If we XOR all numbers from 0 to n WITH all numbers in the array:
- Every number that appears will cancel itself out (becomes 0)
- Only the missing number remains!

#### Visual Understanding

```
Complete range: 0 ^ 1 ^ 2 ^ 3
Array:          0 ^ 1 ^   ^ 3
                ─   ─       ─
When XORed:     0 ^ 0 ^ 0  (all cancel except 2)
Result:         2 ✅
```

It's like having pairs of socks:
- You have pairs numbered 0, 1, 2, 3
- You find socks: 0, 1, 3 (one is missing its pair)
- The unpaired sock number is 2!

#### Algorithm

```
1. result = 0

2. XOR all numbers from 0 to n:
   for i from 0 to n:
       result = result ^ i

3. XOR all numbers in the array:
   for each num in array:
       result = result ^ num

4. Return result
   (All pairs cancel out, only missing number remains!)
```

**Optimized in one loop:**
```
result = n  (start with the last number)
for i from 0 to n-1:
    result = result ^ i ^ array[i]
return result
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

#### Comprehensive Dry Run (XOR Method)

**Example:** `[3, 0, 1]` (n = 3)

**Method:** XOR all from 0 to n, then XOR all array elements

```
Step 1: XOR numbers 0 to 3
  result = 0 ^ 1 ^ 2 ^ 3

  Let's calculate step by step:
  0 ^ 1 = 1   (binary: 0 ^ 1 = 1)
  1 ^ 2 = 3   (binary: 01 ^ 10 = 11)
  3 ^ 3 = 0   (binary: 11 ^ 11 = 00)
  
  So far: result = 0

Wait, that doesn't seem right. Let me recalculate:

  0 (binary: 00)
  1 (binary: 01)
  2 (binary: 10)
  3 (binary: 11)

  0 ^ 1:
    00
  ^ 01
    --
    01 = 1

  1 ^ 2:
    01
  ^ 10
    --
    11 = 3

  3 ^ 3:
    11
  ^ 11
    --
    00 = 0

  Hmm, wait. Let me do this properly:
  
  0 ^ 1 ^ 2 ^ 3
  = (0 ^ 1) ^ (2 ^ 3)
  = 1 ^ 1
  = 0
  
  No wait, let me be more careful:
  
  Step by step:
  Start: result = 0
  result = 0 ^ 0 = 0
  result = 0 ^ 1 = 1
  result = 1 ^ 2 = 3
  result = 3 ^ 3 = 0
  
  After XORing 0 to 3: result = 0

Step 2: XOR with array elements [3, 0, 1]
  result = 0 ^ 3 = 3
  result = 3 ^ 0 = 3
  result = 3 ^ 1 = 2

  Final result = 2 ✅
```

**Better visualization:**
```
XOR all from range:     0 ^ 1 ^ 2 ^ 3
XOR all from array:     3 ^ 0 ^ 1

Combined:  0 ^ 1 ^ 2 ^ 3 ^ 3 ^ 0 ^ 1
Rearrange: 0 ^ 0 ^ 1 ^ 1 ^ 3 ^ 3 ^ 2
Cancel:    (0)   (0)   (0)   ^ 2
Result:    2 ✅
```

#### Detailed XOR Trace Table

| Operation | Binary | Decimal | Note |
|-----------|--------|---------|------|
| Initial | - | 0 | Starting value |
| ^ 0 | 0000 ^ 0000 | 0 | |
| ^ 1 | 0000 ^ 0001 | 1 | |
| ^ 2 | 0001 ^ 0010 | 3 | |
| ^ 3 | 0011 ^ 0011 | 0 | Canceled! |
| ^ 3 (array) | 0000 ^ 0011 | 3 | |
| ^ 0 (array) | 0011 ^ 0000 | 3 | |
| ^ 1 (array) | 0011 ^ 0001 | 2 | |
| **Final** | **0010** | **2** | ✅ Missing number |

### 9.5 Another Complete Example

**Input:** `[9, 6, 4, 2, 3, 5, 7, 0, 1]` (n = 9, missing 8)

#### Sum Method:
```
n = 9
Expected sum = 9 × 10 / 2 = 45
Actual sum = 9+6+4+2+3+5+7+0+1 = 37
Missing = 45 - 37 = 8 ✅
```

#### XOR Method:
```
XOR (0^1^2^3^4^5^6^7^8^9) with (9^6^4^2^3^5^7^0^1)

All numbers appear twice except 8:
0^0 = 0 (cancel)
1^1 = 0 (cancel)
2^2 = 0 (cancel)
...
8 (appears once) = 8 ✅
```

### 9.6 Comparison of Both Methods

| Aspect | Sum Method | XOR Method |
|--------|------------|------------|
| **Time** | O(n) | O(n) |
| **Space** | O(1) | O(1) |
| **Overflow Risk** | ⚠️ Can overflow for large n | ✅ No overflow |
| **Simplicity** | ✅ Easy to understand | ⚠️ Requires bit knowledge |
| **Math Required** | Basic arithmetic | Bit manipulation |
| **Best For** | Beginners, small arrays | Production code, large n |

### 9.7 Edge Cases

| Input | n | Complete Range | Missing | Output |
|-------|---|----------------|---------|--------|
| `[0]` | 1 | [0,1] | 1 | 1 |
| `[1]` | 1 | [0,1] | 0 | 0 |
| `[1,0]` | 2 | [0,1,2] | 2 | 2 |
| `[]` (conceptual) | 0 | [0] | 0 | 0 |

### 9.8 Common Mistakes

❌ **Mistake 1:** Forgetting that range is 0 to n (n+1 total numbers)
- If array has 3 elements, range is 0 to 3 (4 numbers total)!

❌ **Mistake 2:** Integer overflow in sum method
- For large n, use `long` instead of `int`

❌ **Mistake 3:** Wrong XOR initialization
- Always start with result = 0

### 9.9 Use Cases (Real World)

1. **Network Packets:** Detecting which packet number is missing in a sequence.
2. **Database IDs:** Finding missing ID in a sequential ID system.
3. **Quality Control:** Finding which serial number in a batch is missing.
4. **File Systems:** Detecting missing file chunk in ordered downloads.
5. **Data Integrity:** Verifying complete data transmission.

---

## 10. Max Consecutive Ones

### 10.1 Problem Statement

**Given:** A binary array (contains only 0s and 1s).  
**Goal:** Find the **maximum number of consecutive 1s** in the array.

**Example Input:** `[1, 1, 0, 1, 1, 1, 0, 1]`  
**Expected Output:** `3` (the longest streak of 1s is three 1s in a row)

### 10.2 Intuition – The "Streak Counter" Approach

Think of this like counting your **workout streak**:
- Every day you work out (1) → your streak increases
- Any day you skip (0) → your streak resets to 0
- You want to find your **longest streak ever**

We need **two counters**:
1. `currentCount` → how many consecutive 1s **right now**
2. `maxCount` → the **best streak** we've seen so far

### 10.3 Algorithm (Simple & Beautiful)

```
1. Initialize:
   currentCount = 0  (current streak)
   maxCount = 0      (best streak so far)

2. For each element in the array:
   
   If element == 1:
      - currentCount++  (continue the streak)
      - maxCount = max(maxCount, currentCount)  (update best if needed)
   
   Else (element == 0):
      - currentCount = 0  (streak broken! reset)

3. Return maxCount
```

**Time Complexity:** O(n) – single pass  
**Space Complexity:** O(1) – only two variables

### 10.4 Comprehensive Dry Run

**Example:** `[1, 1, 0, 1, 1, 1, 0, 1]`

Let's trace through every step:

**Initial State:**
```
Array: [1, 1, 0, 1, 1, 1, 0, 1]
currentCount = 0
maxCount = 0
```

---

**Step 1:** Index 0, element = 1
```
Element is 1 (found a one!)
  → currentCount++ → currentCount = 1
  → maxCount = max(0, 1) = 1

State: currentCount = 1, maxCount = 1

Visual streak: ■□□□□□□□ (1 consecutive 1)
```

---

**Step 2:** Index 1, element = 1
```
Element is 1 (streak continues!)
  → currentCount++ → currentCount = 2
  → maxCount = max(1, 2) = 2

State: currentCount = 2, maxCount = 2

Visual streak: ■■□□□□□□ (2 consecutive 1s)
```

---

**Step 3:** Index 2, element = 0
```
Element is 0 (streak broken!)
  → currentCount = 0 (reset)

State: currentCount = 0, maxCount = 2

Visual streak: ■■░□□□□□ (streak ended at 2)
```

---

**Step 4:** Index 3, element = 1
```
Element is 1 (new streak starts!)
  → currentCount++ → currentCount = 1
  → maxCount = max(2, 1) = 2 (no change)

State: currentCount = 1, maxCount = 2

Visual streak: ■■░■□□□□ (new streak of 1)
```

---

**Step 5:** Index 4, element = 1
```
Element is 1 (streak continues!)
  → currentCount++ → currentCount = 2
  → maxCount = max(2, 2) = 2 (tied with previous best)

State: currentCount = 2, maxCount = 2

Visual streak: ■■░■■□□□ (streak of 2)
```

---

**Step 6:** Index 5, element = 1
```
Element is 1 (streak continues!)
  → currentCount++ → currentCount = 3
  → maxCount = max(2, 3) = 3 (new record!)

State: currentCount = 3, maxCount = 3

Visual streak: ■■░■■■□□ (streak of 3 - NEW BEST!)
```

---

**Step 7:** Index 6, element = 0
```
Element is 0 (streak broken again!)
  → currentCount = 0 (reset)

State: currentCount = 0, maxCount = 3

Visual streak: ■■░■■■░□ (best streak still 3)
```

---

**Step 8:** Index 7, element = 1
```
Element is 1 (new streak starts!)
  → currentCount++ → currentCount = 1
  → maxCount = max(3, 1) = 3 (no change)

State: currentCount = 1, maxCount = 3

Visual streak: ■■░■■■░■ (final streak of 1)
```

---

**Final Answer:** `maxCount = 3` ✅

### 10.5 Visual Summary

```
Array:        [1, 1, 0, 1, 1, 1, 0, 1]
Streaks:       ━━━   ━━━━━━━   ━
               streak  streak  streak
Length:          2       3       1
                       ↑
                   Maximum = 3
```

### 10.6 Detailed Trace Table

| Index | Element | currentCount Before | Action | currentCount After | maxCount | Streak Visual |
|-------|---------|---------------------|--------|-------------------|----------|---------------|
| -     | -       | 0                   | Init   | 0                 | 0        | `________` |
| 0     | 1       | 0                   | Count++| 1                 | 1        | `■_______` |
| 1     | 1       | 1                   | Count++| 2                 | 2        | `■■______` |
| 2     | 0       | 2                   | Reset  | 0                 | 2        | `■■░_____` |
| 3     | 1       | 0                   | Count++| 1                 | 2        | `■■░■____` |
| 4     | 1       | 1                   | Count++| 2                 | 2        | `■■░■■___` |
| 5     | 1       | 2                   | Count++| 3                 | **3**    | `■■░■■■__` |
| 6     | 0       | 3                   | Reset  | 0                 | 3        | `■■░■■■░_` |
| 7     | 1       | 0                   | Count++| 1                 | 3        | `■■░■■■░■` |

**Result:** Maximum = 3

### 10.7 Another Example (All Ones)

**Input:** `[1, 1, 1, 1, 1]`

```
Index 0: element=1 → current=1, max=1
Index 1: element=1 → current=2, max=2
Index 2: element=1 → current=3, max=3
Index 3: element=1 → current=4, max=4
Index 4: element=1 → current=5, max=5

Result: 5 (entire array is one long streak!)
```

### 10.8 Another Example (No Consecutive Ones)

**Input:** `[1, 0, 1, 0, 1]`

```
Index 0: element=1 → current=1, max=1
Index 1: element=0 → current=0, max=1  (reset)
Index 2: element=1 → current=1, max=1
Index 3: element=0 → current=0, max=1  (reset)
Index 4: element=1 → current=1, max=1

Result: 1 (no consecutive ones, longest streak is 1)
```

### 10.9 Edge Cases

| Input | Output | Explanation |
|-------|--------|-------------|
| `[1]` | 1 | Single 1 |
| `[0]` | 0 | Single 0, no ones |
| `[0, 0, 0]` | 0 | All zeros |
| `[1, 1, 1]` | 3 | All ones |
| `[1, 0]` | 1 | One followed by zero |
| `[]` | 0 | Empty array |

### 10.10 Common Mistakes

❌ **Mistake 1:** Not updating maxCount every time you increment currentCount
```java
// WRONG:
if (arr[i] == 1) {
    currentCount++;
}
// You forgot to update maxCount!
```

✅ **CORRECT:**
```java
if (arr[i] == 1) {
    currentCount++;
    maxCount = Math.max(maxCount, currentCount);  // Update!
}
```

❌ **Mistake 2:** Not handling the last streak
- If array ends with 1s, make sure maxCount is updated before returning!

❌ **Mistake 3:** Initializing maxCount to -1 or not updating it properly

### 10.11 Code Pattern

```java
public int findMaxConsecutiveOnes(int[] nums) {
    int currentCount = 0;
    int maxCount = 0;
    
    for (int num : nums) {
        if (num == 1) {
            currentCount++;
            maxCount = Math.max(maxCount, currentCount);
        } else {
            currentCount = 0;  // Reset streak
        }
    }
    
    return maxCount;
}
```

### 10.12 Use Cases (Real World)

1. **Network Uptime:** Find longest period of continuous server uptime (1=up, 0=down).
2. **Game Development:** Track longest winning streak or combo streak.
3. **Sensor Monitoring:** Find longest period where sensor was active continuously.
4. **Quality Control:** Longest streak of products passing quality checks.
5. **Sports Analytics:** Longest sequence of successful free throws, hits, etc.
6. **Trading:** Longest streak of profitable days (1=profit, 0=loss).
7. **Habit Tracking:** Longest streak of completing daily goals.

---

## 11. Single Number (XOR Method)

Find the element that appears only once when every other element appears twice.

### 11.1 Intuition

Using XOR property: `a ^ a = 0` and `a ^ 0 = a`

All pairs cancel out, leaving only the single number.

### 11.2 Algorithm

1. `result = 0`
2. For each element `x`:
   - `result = result ^ x`
3. Return `result`

### 11.3 Example

Array: `[4, 1, 2, 1, 2]`

- `0 ^ 4 = 4`
- `4 ^ 1 = 5`
- `5 ^ 2 = 7`
- `7 ^ 1 = 6` (the previous 1 cancels)
- `6 ^ 2 = 4` (the previous 2 cancels)

Answer: 4

### 11.4 Use Cases

- Finding unpaired item in logistics.
- Error detection in data transmission.
- Finding unique configuration in systems.

---

## 12. Leaders in an Array

A leader is an element that is greater than all elements to its right.

### 12.1 Intuition

Scan from **right to left**, keeping track of the maximum seen so far.

### 12.2 Algorithm

1. `maxFromRight = -∞`
2. `leaders = []`
3. For `i` from n-1 down to 0:
   - If `arr[i] > maxFromRight`:
     - Add `arr[i]` to leaders
     - `maxFromRight = arr[i]`
4. Reverse `leaders` (since we scanned right to left)
5. Return `leaders`

### 12.3 Example

Array: `[16, 17, 4, 3, 5, 2]`

Scanning right to left:

| i | arr[i] | maxFromRight | Is Leader? |
|---|--------|--------------|------------|
| 5 | 2      | -∞           | Yes, max=2 |
| 4 | 5      | 2            | Yes, max=5 |
| 3 | 3      | 5            | No         |
| 2 | 4      | 5            | No         |
| 1 | 17     | 5            | Yes, max=17|
| 0 | 16     | 17           | No         |

Leaders (reversed): `[17, 5, 2]`

### 12.4 Use Cases

- Finding peak performers in time-series data.
- Stock price analysis (higher prices than future).
- Tournament brackets.

---

## 13. Longest Consecutive Sequence

Find the length of the longest consecutive elements sequence (elements don't need to be contiguous in array).

### 13.1 Intuition

Use a **HashSet** for O(1) lookups.

For each number, check if it's the **start** of a sequence (i.e., `num - 1` is not in the set).  
If yes, count how long the sequence extends.

### 13.2 Algorithm

1. Add all elements to a HashSet.
2. `maxLength = 0`
3. For each `num` in set:
   - If `num - 1` is not in set (this is a sequence start):
     - `currentNum = num`
     - `currentLength = 1`
     - While `currentNum + 1` is in set:
       - `currentNum++`
       - `currentLength++`
     - `maxLength = max(maxLength, currentLength)`
4. Return `maxLength`

### 13.3 Example

Array: `[100, 4, 200, 1, 3, 2]`

Set: `{100, 4, 200, 1, 3, 2}`

- 100: 99 not in set → sequence start. 101 not in set → length 1
- 4: 3 in set → not a start
- 200: 199 not in set → sequence start. 201 not in set → length 1
- 1: 0 not in set → sequence start. Check 2, 3, 4 → length 4
- 3: 2 in set → not a start
- 2: 1 in set → not a start

Answer: 4 (sequence: 1, 2, 3, 4)

### 13.4 Use Cases

- Finding longest streak in user activity.
- Version control: longest chain of commits.
- Database: finding continuous ID ranges.

---

## 14. Rotate Array by K Steps (The Brilliant Reverse Trick!)

### 14.1 Problem Statement

**Given:** An array and a number `k`.  
**Goal:** Rotate the array to the **right** by `k` steps.

**Example Input:** `[1, 2, 3, 4, 5, 6, 7]`, k = 3  
**Expected Output:** `[5, 6, 7, 1, 2, 3, 4]`

**Visual Understanding:**
```
Original:  [1, 2, 3, 4, 5, 6, 7]
           
Rotate right by 1: [7, 1, 2, 3, 4, 5, 6]  (7 moves to front)
Rotate right by 2: [6, 7, 1, 2, 3, 4, 5]  (6 moves to front)
Rotate right by 3: [5, 6, 7, 1, 2, 3, 4]  (5 moves to front)
                    ━━━━━━  ━━━━━━━━━━━
                    last k  first (n-k)
```

### 14.2 Intuition – Why the Reverse Trick Works

Think of a **deck of cards**:
- You want to move the **bottom k cards** to the **top**.
- Naive approach: Remove each card from bottom and place on top (slow!)
- **Brilliant trick:** Three flips!

**The Magic 3-Step Reverse Method:**
1. **Flip entire deck** (all cards upside down and reversed)
2. **Flip top k cards** (put those back in order)
3. **Flip remaining cards** (put those back in order)

**Why this works:**
- After step 1: Everything is reversed
- After step 2: The elements that should be at the end are now correctly positioned at the start
- After step 3: The elements that should be at the start are now correctly positioned at the end

### 14.3 Algorithm (The 3-Reverse Technique)

```
Important: Handle cases where k > n
   k = k % n  (if k=10 and n=7, rotating by 10 = rotating by 3)

Step 1: Reverse entire array [0 to n-1]
Step 2: Reverse first k elements [0 to k-1]
Step 3: Reverse remaining elements [k to n-1]
```

**Time Complexity:** O(n) – each reverse is O(n)  
**Space Complexity:** O(1) – in-place reversal

### 14.4 Why We Need k = k % n

**Example:** array `[1, 2, 3]`, k = 10

```
Rotate by 1: [3, 1, 2]
Rotate by 2: [2, 3, 1]
Rotate by 3: [1, 2, 3] ← Back to original!
Rotate by 4: [3, 1, 2] ← Same as rotate by 1
Rotate by 10: Same as rotate by 10 % 3 = 1

So rotating by 10 = rotating by 1!
```

### 14.5 Comprehensive Dry Run

**Example:** `[1, 2, 3, 4, 5, 6, 7]`, k = 3

**Preparation:**
```
Array: [1, 2, 3, 4, 5, 6, 7]
n = 7
k = 3
k % n = 3 % 7 = 3 (no change needed)
```

---

**Step 1: Reverse Entire Array [0 to 6]**

```
Original: [1, 2, 3, 4, 5, 6, 7]
           ↓           ↑
          swap 1 and 7

After:    [7, 2, 3, 4, 5, 6, 1]
              ↓        ↑
            swap 2 and 6

After:    [7, 6, 3, 4, 5, 2, 1]
                 ↓     ↑
               swap 3 and 5

After:    [7, 6, 5, 4, 3, 2, 1]
                    ↓
                 (4 in middle, no swap needed)

Result after Step 1: [7, 6, 5, 4, 3, 2, 1]
```

**Visual:**
```
[1, 2, 3, 4, 5, 6, 7]
 ↓  ↓  ↓  ↓  ↓  ↓  ↓  flip entire array
[7, 6, 5, 4, 3, 2, 1]
```

---

**Step 2: Reverse First k=3 Elements [0 to 2]**

```
Current:  [7, 6, 5, 4, 3, 2, 1]
           ━━━━━━
          reverse these 3

Process:
           [7, 6, 5, 4, 3, 2, 1]
            ↓     ↑
          swap 7 and 5

After:    [5, 6, 7, 4, 3, 2, 1]
               ↓
            (6 in middle, already in place)

Result after Step 2: [5, 6, 7, 4, 3, 2, 1]
```

**Visual:**
```
[7, 6, 5, | 4, 3, 2, 1]
 ↓  ↓  ↓   flip first 3
[5, 6, 7, | 4, 3, 2, 1]
```

---

**Step 3: Reverse Remaining Elements [3 to 6]**

```
Current:  [5, 6, 7, 4, 3, 2, 1]
                   ━━━━━━━━━━
                  reverse these 4

Process:
          [5, 6, 7, 4, 3, 2, 1]
                    ↓        ↑
                  swap 4 and 1

After:    [5, 6, 7, 1, 3, 2, 4]
                       ↓  ↑
                     swap 3 and 2

Final:    [5, 6, 7, 1, 2, 3, 4] ✅
```

**Visual:**
```
[5, 6, 7, | 4, 3, 2, 1]
            ↓  ↓  ↓  ↓  flip last 4
[5, 6, 7, | 1, 2, 3, 4]
```

---

**Final Result: `[5, 6, 7, 1, 2, 3, 4]`**

### 14.6 Step-by-Step Visual Summary

```
Original Array:
[1, 2, 3, 4, 5, 6, 7]
 ━━━━━━━━━━━  ━━━━━━
  first (n-k)   last k
 
Goal: Move last k=3 elements to front

════════════════════════════════════════

STEP 1: Reverse Entire Array
[1, 2, 3, 4, 5, 6, 7]  →  [7, 6, 5, 4, 3, 2, 1]
 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓       ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
        FLIP ALL

════════════════════════════════════════

STEP 2: Reverse First k=3
[7, 6, 5 | 4, 3, 2, 1]  →  [5, 6, 7 | 4, 3, 2, 1]
 ↓↓↓↓↓↓↓                     ↑↑↑↑↑↑↑
 FLIP k

════════════════════════════════════════

STEP 3: Reverse Remaining n-k=4
[5, 6, 7 | 4, 3, 2, 1]  →  [5, 6, 7 | 1, 2, 3, 4]
           ↓↓↓↓↓↓↓↓↓↓↓                 ↑↑↑↑↑↑↑↑↑↑↑
           FLIP (n-k)

════════════════════════════════════════

FINAL: [5, 6, 7, 1, 2, 3, 4] ✅
```

### 14.7 Another Example (k > n)

**Input:** `[1, 2, 3, 4, 5]`, k = 12

**Preparation:**
```
n = 5
k = 12
k % n = 12 % 5 = 2
So we need to rotate by 2, not 12!
```

**Steps:**
```
Original:        [1, 2, 3, 4, 5]

Reverse all:     [5, 4, 3, 2, 1]

Reverse first 2: [4, 5, 3, 2, 1]

Reverse last 3:  [4, 5, 1, 2, 3] ✅
```

**Verification:** Rotating `[1,2,3,4,5]` right by 2:
- By 1: `[5,1,2,3,4]`
- By 2: `[4,5,1,2,3]` ✅ Correct!

### 14.8 Detailed Trace Table

| Step | Action | Array State | Explanation |
|------|--------|-------------|-------------|
| 0    | Initial | `[1,2,3,4,5,6,7]` | Original array |
| 1    | Reverse all | `[7,6,5,4,3,2,1]` | Flipped entire array |
| 2    | Reverse [0,2] | `[5,6,7,4,3,2,1]` | First 3 elements corrected |
| 3    | Reverse [3,6] | `[5,6,7,1,2,3,4]` | Last 4 elements corrected |
| ✅   | Done | `[5,6,7,1,2,3,4]` | Last 3 moved to front |

### 14.9 Edge Cases

| Input | k | k%n | Output | Note |
|-------|---|-----|--------|------|
| `[1,2,3]` | 0 | 0 | `[1,2,3]` | No rotation |
| `[1,2,3]` | 3 | 0 | `[1,2,3]` | Full rotation = no change |
| `[1,2,3]` | 1 | 1 | `[3,1,2]` | Move last to front |
| `[1]` | 5 | 0 | `[1]` | Single element |
| `[]` | 3 | - | `[]` | Empty array |

### 14.10 Common Mistakes

❌ **Mistake 1:** Forgetting to do `k = k % n`
```java
// WRONG: If k=100 and n=7, you'll try to reverse [0 to 99]!
reverse(arr, 0, k-1);
```

✅ **CORRECT:**
```java
k = k % n;  // Always do this first!
reverse(arr, 0, k-1);
```

❌ **Mistake 2:** Wrong order of reverses
- Must be: entire → first k → remaining
- NOT: first k → remaining → entire

❌ **Mistake 3:** Off-by-one errors in reverse boundaries
- First k: `[0 to k-1]` not `[0 to k]`
- Remaining: `[k to n-1]` not `[k+1 to n-1]`

### 14.11 Complete Code Template

```java
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n;  // Handle k > n
    
    // Step 1: Reverse entire array
    reverse(nums, 0, n - 1);
    
    // Step 2: Reverse first k elements
    reverse(nums, 0, k - 1);
    
    // Step 3: Reverse remaining elements
    reverse(nums, k, n - 1);
}

private void reverse(int[] arr, int start, int end) {
    while (start < end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        start++;
        end--;
    }
}
```

### 14.12 Use Cases (Real World)

1. **Circular Buffers:** Ring buffer operations in networking/streaming.
2. **Scheduling Algorithms:** Round-robin task scheduling.
3. **Image Processing:** Rotate image pixels or apply rotation effects.
4. **Game Development:** Rotating game boards or circular menus.
5. **Data Streams:** Time-series data with circular windows.
6. **Cryptography:** Caesar cipher and shift operations.
7. **UI/UX:** Carousel/slideshow rotations.

---

## 15. Rearrange Array Elements by Sign

Rearrange array so that positive and negative numbers alternate, starting with positive.

### 15.1 Intuition

Use two pointers: one for positive positions (0, 2, 4...) and one for negative positions (1, 3, 5...).

### 15.2 Algorithm

1. Create result array of same size.
2. `posIndex = 0`, `negIndex = 1`
3. For each element:
   - If positive:
     - `result[posIndex] = element`
     - `posIndex += 2`
   - Else:
     - `result[negIndex] = element`
     - `negIndex += 2`
4. Return result

### 15.3 Example

Array: `[3, 1, -2, -5, 2, -4]`

Positives: 3, 1, 2  
Negatives: -2, -5, -4

Result:
- Position 0: 3
- Position 1: -2
- Position 2: 1
- Position 3: -5
- Position 4: 2
- Position 5: -4

Result: `[3, -2, 1, -5, 2, -4]`

### 15.4 Use Cases

- Balancing positive/negative feedback display.
- Alternating resource allocation.
- UI design patterns.

---

## 16. Next Permutation (Finding the Next Bigger Arrangement!)

### 16.1 Problem Statement

**Given:** An array of numbers representing a permutation.  
**Goal:** Rearrange it to the **next lexicographically greater** permutation.  
**If it's the last permutation:** Rearrange to the smallest permutation (reverse it).

**Think of it like:** Counting in a special number system where you use all digits of the array.

**Example Input:** `[1, 3, 5, 4, 2]`  
**Expected Output:** `[1, 4, 2, 3, 5]` (the next bigger arrangement)

### 16.2 Understanding Lexicographic Order

**Lexicographic Order** = Dictionary order for numbers.

**Example:** All permutations of `[1, 2, 3]` in order:
```
1. [1, 2, 3]  ← smallest
2. [1, 3, 2]
3. [2, 1, 3]
4. [2, 3, 1]
5. [3, 1, 2]
6. [3, 2, 1]  ← largest
```

**Our Goal:** Given any permutation, find the one that comes **immediately after** it in this sorted list.

### 16.3 Intuition – The Key Observations

**Observation 1:** A **decreasing sequence** from right to left is the **largest** possible arrangement of those numbers.

Example: `[5, 4, 3, 2, 1]` is the largest permutation. There's no "next" one for this alone.

**Observation 2:** To get the next permutation:
1. Find the **first increasing pair** from the **right**.
2. This marks where we can make the number "bigger".

**Analogy:** Think of an odometer rolling over:
```
1299 → 1300 (9s roll to 0, then 2 becomes 3)
     ↑
   found the digit to increase
```

### 16.4 The 4-Step Algorithm

```
Step 1: Find the "pivot" (rightmost increasing pair)
   - Scan from right to left
   - Find first index i where arr[i] < arr[i+1]
   - This is the element we need to "increment"
   
Step 2: If no pivot found (entire array is descending)
   - This is the largest permutation
   - Reverse entire array to get smallest
   - DONE

Step 3: Find the "successor" (next larger element than pivot)
   - From the right, find first element > arr[i]
   - Call this index j
   
Step 4: Make the swap and sort the suffix
   - Swap arr[i] and arr[j]
   - Reverse everything after position i (the suffix)
   - This gives the smallest arrangement with new prefix
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

### 16.5 Comprehensive Dry Run (Step-by-Step)

**Example:** `[1, 3, 5, 4, 2]`

Let's find the next permutation!

---

**STEP 1: Find the Pivot (rightmost i where arr[i] < arr[i+1])**

Scan from right to left:

```
Array: [1, 3, 5, 4, 2]
Index:  0  1  2  3  4

Start from right:
- Compare arr[3]=4 and arr[4]=2: 4 > 2 (decreasing, keep going)
- Compare arr[2]=5 and arr[3]=4: 5 > 4 (decreasing, keep going)
- Compare arr[1]=3 and arr[2]=5: 3 < 5 ✅ FOUND IT!

Pivot index i = 1 (value = 3)
```

**Visual:**
```
[1, 3, 5, 4, 2]
    ↑  ━━━━━━━
    i  (decreasing from here)
   pivot
```

**Why here?** Everything to the right of position 1 is **decreasing**, which means it's the largest possible arrangement of those numbers. To get the next permutation, we need to "increase" the element at position 1.

---

**STEP 2: Find the Successor (smallest element > pivot from the right)**

Now find the **next larger element** than `arr[i]=3` from the right side:

```
Scan from right: [1, 3, 5, 4, 2]
                         ━━━━━━
                       search here

- arr[4]=2: Is 2 > 3? NO
- arr[3]=4: Is 4 > 3? YES! ✅

Successor index j = 3 (value = 4)
```

**Visual:**
```
[1, 3, 5, 4, 2]
    ↑     ↑
    i     j
  pivot successor
   (3)    (4)
```

**Why 4?** We want the **smallest** element that's **still larger** than 3. From the right portion `[5, 4, 2]`, the candidates are 5 and 4. We pick 4 because it's smaller than 5, giving us the "next" smallest increment.

---

**STEP 3: Swap pivot and successor**

```
Before: [1, 3, 5, 4, 2]
            ↕     ↕
Swap arr[1]=3 and arr[3]=4

After:  [1, 4, 5, 3, 2]
```

**What happened?** We replaced 3 with 4 at position 1, making the number "larger". But now the suffix `[5, 3, 2]` needs to be arranged in the **smallest** possible way.

---

**STEP 4: Reverse the suffix (everything after position i)**

The suffix is everything from index `i+1` to end: `[5, 3, 2]`

```
Current: [1, 4, 5, 3, 2]
               ━━━━━━━
              reverse this

Reverse [5, 3, 2] → [2, 3, 5]

Final:   [1, 4, 2, 3, 5] ✅
```

**Why reverse?** After swapping, the suffix is still in **descending order** (5, 3, 2). To make it the **smallest** arrangement, we reverse it to get ascending order (2, 3, 5).

---

**RESULT: `[1, 4, 2, 3, 5]`**

This is the next permutation after `[1, 3, 5, 4, 2]`!

### 16.6 Visual Summary (All Steps)

```
Original:         [1, 3, 5, 4, 2]
                      ↑  ━━━━━━━
                    pivot (i=1)
                    
Find successor:   [1, 3, 5, 4, 2]
                      ↑     ↑
                      i     j
                      
After swap:       [1, 4, 5, 3, 2]
                         ━━━━━━━
                        still descending
                        
After reverse:    [1, 4, 2, 3, 5] ✅
                         ━━━━━━━
                        now ascending
```

### 16.7 Another Complete Example

**Input:** `[1, 2, 3]`

**Step 1:** Find pivot
```
Compare arr[1]=2 and arr[2]=3: 2 < 3 ✅
Pivot i = 1
```

**Step 2:** Find successor
```
From right, arr[2]=3 > arr[1]=2 ✅
Successor j = 2
```

**Step 3:** Swap
```
[1, 2, 3] → swap(1, 2) → [1, 3, 2]
```

**Step 4:** Reverse suffix (nothing after index 1, so just one element)
```
[1, 3, 2] ✅
```

**Answer:** `[1, 3, 2]` is the next permutation after `[1, 2, 3]`!

### 16.8 Edge Case: Largest Permutation

**Input:** `[3, 2, 1]` (largest permutation, fully descending)

**Step 1:** Find pivot
```
Compare arr[1]=2 and arr[2]=1: 2 > 1 (decreasing)
Compare arr[0]=3 and arr[1]=2: 3 > 2 (decreasing)
No pivot found! (i = -1)
```

**Step 2:** Since no pivot, this is the largest permutation
```
Action: Reverse entire array
[3, 2, 1] → [1, 2, 3] ✅
```

**Answer:** `[1, 2, 3]` (wraps to smallest permutation)

### 16.9 Detailed Step Table for `[1, 3, 5, 4, 2]`

| Step | Action | Details | Array State | Note |
|------|--------|---------|-------------|------|
| 0 | Initial | - | `[1,3,5,4,2]` | Start |
| 1 | Find pivot | Scan right-to-left for i: arr[i]<arr[i+1] | `[1,3,5,4,2]` | i=1, pivot=3 |
| 2 | Find successor | Scan right for j: arr[j]>arr[i] | `[1,3,5,4,2]` | j=3, succ=4 |
| 3 | Swap | Swap arr[1] and arr[3] | `[1,4,5,3,2]` | Incremented pivot position |
| 4 | Reverse suffix | Reverse arr[2..4] | `[1,4,2,3,5]` | Minimized suffix |
| ✅ | Done | Next permutation found | `[1,4,2,3,5]` | Result |

### 16.10 All Permutations of `[1, 3, 5, 4, 2]` (Partial List)

To understand where our answer falls:

```
...
[1, 3, 4, 5, 2]
[1, 3, 5, 2, 4]
[1, 3, 5, 4, 2]  ← Our input
[1, 4, 2, 3, 5]  ← Our output (next one!) ✅
[1, 4, 2, 5, 3]
[1, 4, 3, 2, 5]
...
```

### 16.11 Common Patterns to Recognize

| Input Pattern | Pivot Location | Action |
|---------------|----------------|--------|
| `[1,2,3,4,5]` | i=3 (4<5) | Swap last two → `[1,2,3,5,4]` |
| `[5,4,3,2,1]` | None (all descending) | Reverse all → `[1,2,3,4,5]` |
| `[1,5,1]` | i=0 (1<5) | Complex swap+reverse |
| `[1,3,2]` | i=1 (3>2 but 1<3) | i=0, swap 1 and 2 → `[2,1,3]` |

### 16.12 Edge Cases

| Input | Expected Output | Explanation |
|-------|----------------|-------------|
| `[1]` | `[1]` | Single element, no change |
| `[1,2]` | `[2,1]` | Swap them |
| `[2,1]` | `[1,2]` | Largest → smallest |
| `[1,1,5]` | `[1,5,1]` | Handle duplicates |

### 16.13 Common Mistakes

❌ **Mistake 1:** Sorting the suffix instead of reversing
- Sorting takes O(n log n), reversing takes O(n)
- Since suffix is already descending, reversing gives ascending

❌ **Mistake 2:** Finding pivot from left to right
- Must scan from **right to left**!

❌ **Mistake 3:** Swapping with the wrong element
- Swap with the **smallest element larger than pivot** from the right

❌ **Mistake 4:** Not handling the "no pivot" case
- When array is fully descending, reverse entire array

### 16.14 Complete Code

```java
public void nextPermutation(int[] nums) {
    int n = nums.length;
    
    // Step 1: Find pivot (rightmost i where nums[i] < nums[i+1])
    int i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        i--;
    }
    
    // Step 2: If pivot found, find successor and swap
    if (i >= 0) {
        int j = n - 1;
        while (nums[j] <= nums[i]) {
            j--;
        }
        swap(nums, i, j);
    }
    
    // Step 3: Reverse suffix
    reverse(nums, i + 1, n - 1);
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}

private void reverse(int[] nums, int start, int end) {
    while (start < end) {
        swap(nums, start, end);
        start++;
        end--;
    }
}
```

### 16.15 Use Cases (Real World)

1. **Combinatorial Generation:** Generate permutations in lexicographic order.
2. **Password Crackers:** Try permutations of characters in order.
3. **Puzzle Solvers:** Explore state spaces systematically.
4. **Test Case Generation:** Generate all input combinations.
5. **Scheduling:** Enumerate task orderings.
6. **Genetics:** Generate DNA sequence variations.
7. **Cryptography:** Systematic key space exploration.

---

## 17. Check if Array is Sorted and Rotated

Check if an array is sorted (ascending) and then rotated by some positions.

### 17.1 Intuition

In a sorted-rotated array, there should be **at most one** point where `arr[i] > arr[i+1]` (the rotation point).

Also check that `arr[n-1] <= arr[0]` to ensure it wraps around properly.

### 17.2 Algorithm

1. Count how many times `arr[i] > arr[i+1]` occurs.
2. If count > 1, return false.
3. If count == 1, check if `arr[n-1] <= arr[0]`.
4. If count == 0, it's just sorted (rotation of 0).

### 17.3 Example

Array: `[3, 4, 5, 1, 2]`

Breaks: 
- 3 < 4 ✓
- 4 < 5 ✓
- 5 > 1 ✗ (count = 1)
- 1 < 2 ✓

Check wrap: arr[4] = 2, arr[0] = 3, 2 < 3 ✓

Result: True

### 17.4 Use Cases

- Validating rotated sorted data.
- Circular array problems.
- Search optimization checks.

---

## 18. Union and Intersection of Two Arrays

### 18.1 Union (All Unique Elements)

**Using HashSet:**
1. Add all elements from both arrays to a set.
2. Return set as list.

Time: O(n + m), Space: O(n + m)

**For Sorted Arrays (Two Pointers):**
1. Use two pointers, pick smaller element, skip duplicates.

### 18.2 Intersection (Common Elements)

**Using HashSet:**
1. Add all elements from first array to set.
2. For each element in second array:
   - If in set, add to result and remove from set (to avoid duplicates).

**For Sorted Arrays:**
1. Two pointers, when elements match, add to result.

### 18.3 Example

Array1: `[1, 2, 2, 3, 4]`  
Array2: `[2, 2, 3, 5]`

**Union:** `[1, 2, 3, 4, 5]` (unique)  
**Intersection:** `[2, 3]` (or `[2, 2, 3]` if keeping duplicates)

### 18.4 Use Cases

- Database query operations.
- Set operations in data analysis.
- Finding common/all users across platforms.

---

## 19. Best Time to Buy and Sell Stock

Find maximum profit from one buy and one sell transaction.

### 19.1 Intuition

Track the **minimum price** seen so far, and for each price, calculate profit if we sell today.

### 19.2 Algorithm

1. `minPrice = ∞`, `maxProfit = 0`
2. For each price:
   - `minPrice = min(minPrice, price)`
   - `profit = price - minPrice`
   - `maxProfit = max(maxProfit, profit)`
3. Return `maxProfit`

### 19.3 Example

Prices: `[7, 1, 5, 3, 6, 4]`

| i | price | minPrice | profit | maxProfit |
|---|-------|----------|--------|-----------|
| 0 | 7     | 7        | 0      | 0         |
| 1 | 1     | 1        | 0      | 0         |
| 2 | 5     | 1        | 4      | 4         |
| 3 | 3     | 1        | 2      | 4         |
| 4 | 6     | 1        | 5      | 5         |
| 5 | 4     | 1        | 3      | 5         |

Answer: 5 (buy at 1, sell at 6)

### 19.4 Use Cases

- Stock trading algorithms.
- Price trend analysis.
- Optimal timing decisions.

---

## 20. Pascal's Triangle

Generate the first n rows of Pascal's Triangle.

### 20.1 Intuition

Each number is the sum of the two numbers directly above it.

Row formula: `C(n, k) = n! / (k! * (n-k)!)`

Or iteratively build each row from the previous row.

### 20.2 Algorithm (Row by Row)

1. Result = []
2. For rowNum from 0 to n-1:
   - currentRow = [1]
   - If rowNum > 0:
     - For j from 1 to rowNum:
       - `val = previousRow[j-1] + previousRow[j]` (if j < len(previousRow))
       - Append val
   - Result.add(currentRow)

### 20.3 Example

n = 5

```
Row 0: [1]
Row 1: [1, 1]
Row 2: [1, 2, 1]
Row 3: [1, 3, 3, 1]
Row 4: [1, 4, 6, 4, 1]
```

### 20.4 Use Cases

- Combinatorics calculations.
- Binomial coefficients.
- Probability theory.

---

## 21. Set Matrix Zeroes

If an element is 0, set its entire row and column to 0 (in-place).

### 21.1 Intuition

Use the **first row and first column** as markers.

Also use two flags to track if first row/column themselves need to be zeroed.

### 21.2 Algorithm

1. Check if first row has any zero → `firstRowZero`
2. Check if first column has any zero → `firstColZero`
3. Use matrix[i][0] and matrix[0][j] as markers for row i and column j.
4. Scan from (1,1) onwards, mark first row/col if zero found.
5. Use markers to set zeros (skip first row/col for now).
6. Finally, zero first row/col if flags are set.

### 21.3 Example

```
[1, 1, 1]
[1, 0, 1]
[1, 1, 1]
```

After marking:
- matrix[1][0] = 0 (row 1 has zero)
- matrix[0][1] = 0 (col 1 has zero)

After processing:
```
[1, 0, 1]
[0, 0, 0]
[1, 0, 1]
```

### 21.4 Use Cases

- Image processing (masking).
- Sparse matrix operations.
- Game boards (mine sweeper effects).

---

## 22. Spiral Matrix Traversal

Return elements of matrix in spiral order (clockwise from outside to inside).

### 22.1 Intuition

Maintain four boundaries: top, bottom, left, right.

Move in order: right → down → left → up, shrinking boundaries after each direction.

### 22.2 Algorithm

1. `top = 0, bottom = rows-1, left = 0, right = cols-1`
2. While `top <= bottom` and `left <= right`:
   - Traverse top row from left to right, then `top++`
   - Traverse right column from top to bottom, then `right--`
   - If still valid, traverse bottom row from right to left, then `bottom--`
   - If still valid, traverse left column from bottom to top, then `left++`

### 22.3 Example

```
[1, 2, 3]
[4, 5, 6]
[7, 8, 9]
```

Order: 1→2→3→6→9→8→7→4→5

### 22.4 Use Cases

- Image processing (spiral scan).
- Matrix printing utilities.
- Puzzle generation.

---

## 23. Rotate Image/Matrix 90 Degrees

Rotate an n×n matrix 90 degrees clockwise in-place.

### 23.1 Intuition

Two steps:
1. **Transpose** the matrix (swap matrix[i][j] with matrix[j][i])
2. **Reverse** each row

### 23.2 Algorithm

1. Transpose:
   - For i from 0 to n-1:
     - For j from i+1 to n-1:
       - Swap matrix[i][j] and matrix[j][i]
2. Reverse each row:
   - For each row:
     - Reverse it

### 23.3 Example

```
[1, 2, 3]
[4, 5, 6]
[7, 8, 9]
```

After transpose:
```
[1, 4, 7]
[2, 5, 8]
[3, 6, 9]
```

After reversing rows:
```
[7, 4, 1]
[8, 5, 2]
[9, 6, 3]
```

### 23.4 Use Cases

- Image rotation.
- Game board rotation.
- Graphics transformations.

---

## 24. Summary of All Algorithms

This comprehensive guide now covers:

1. **Prefix Sum** – Finding and counting subarrays with sum K
2. **Dutch National Flag** – Sorting 0s, 1s, 2s
3. **Moore's Voting** – Finding majority element
4. **Kadane's Algorithm** – Maximum subarray sum
5. **Two Pointers** – Remove duplicates, move zeros
6. **XOR Tricks** – Missing number, single number
7. **Running Counters** – Max consecutive ones, leaders
8. **HashSet/HashMap** – Longest consecutive sequence, union/intersection
9. **Array Manipulation** – Rotate, rearrange by sign
10. **Permutation Logic** – Next permutation
11. **Validation** – Check sorted and rotated
12. **Stock Trading** – Best time to buy/sell
13. **Combinatorics** – Pascal's triangle
14. **Matrix Operations** – Set zeroes, spiral traversal, rotation

Each algorithm includes:
- Clear intuition
- Step-by-step algorithm
- Worked example with tables/visualization
- Real-world use cases

Use this as your complete reference for array algorithms!


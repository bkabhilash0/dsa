# 📘 Algorithms Explained Simply (Beginner & Kid Friendly)

This document explains several classic algorithms in a way that is friendly for beginners (and even kids), with math intuition, step-by-step examples, and real-world uses.

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


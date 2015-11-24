###Overview

* [1 A + B Problem](#1-a-+-b-problem)
* [2 Digit Counts](#2-digit-count)
* [3 Maximum Subarray](#3-maximum-subarray)
* [4 Maximum Subarray II](#4-maximum-subarray-ii)
* [5 Maximum Subarray III](#5-maxinum-subarray-iii)
* [6 Maximum Subarray Difference](#6-maximum-subarray-difference)
* [7 Majority Number III](#7-majority-number-iii)
* [8 Previous Permutation](#8-previous-permutation)
* [9 Longest Common Subsequence](#9-longest-common-subsequence)
* [10 k Sum](#10-k-sum)
* [11 k Sum II](#11-k-sum-ii)
* [12 Minimum Adjustment Cost](#12-minimum-adjustment-cost)
* [13 Backpack](#13-backpack)
* [14 Backpack II](#14-backpack-ii)
* [15 Max Tree](#15-max-tree)
* [16 Hash Function](#16-hash-function)
* [17 Rehashing](#17-rehashing)
* [18 Heapify](#18-heapify)
* [19 Subarray Sum Closest](#19-subarray-sum-closest)
* [20 Fast Power](#20-fast-power)
* [21 Route Between Two Nodes in Graph](#21-route-between-two-nodes-in-graph)
* [22 Interleaving Positive and Negative Numbers](#22-interleaving-positive-and-negative-numbers)
* [23 Binary Representation](#23-binary-representation)
* [24 Delete Digits](#-24-delete-digits)
* [25 Wood cut](#25-wood-cut)
* [26 Segment Tree Build](#26-segment-tree-build)
* [27 Segment Tree Query](#27-segment-tree-query)
* [28 Segment Tree Modify](#28-segment-tree-modify)
* [29 Interval Minimum Number](29-interval-minimum-number)
* [30 Interval Sum](#30-interval-sum)
* [31 Interval Sum II](#31-interval-sum-ii)
* [32 Segment Tree Query II](#32-segment-tree-query-ii)

###1 A + B Problem
---

http://www.lintcode.com/en/problem/a-b-problem/#

<pre>
Write a function that add two numbers A and B. You should not use + or any arithmetic operators.

Have you met this question in a real interview? Yes
Example
Given a=1 and b=2 return 3

Note
There is no need to read data from standard input stream. Both parameters are given in function aplusb, you job is to calculate the sum and return it.

Challenge
Of course you can just return a + b to get accepted. But Can you challenge not do it like that?

Clarification
Are a and b both 32-bit integers?

Yes.
Can I use bit operation?
Sure you can.
</pre>

**Idea** We can not use plus, but bit operation is allowed.

```java
public int aplusb(int a, int b) {
    // write your code here, try to do it without arithmetic operators.
    int res = 0;
    int carry = 0;
    for (int i = 0; i < 32; i++) {
        int n1 = (a >> i) & 1;
        int n2 = (b >> i) & 1;
        res |= (n1 ^ n2 ^ carry) << i;
        if (n1 == 1 && n2 == 1 || (carry == 1 && (n1 == 1 || n2 == 1))) {
            carry = 1;
        } else {
            carry = 0;
        }
    }
    return res;
}
```

Another Solution : http://www.jiuzhang.com/solutions/a-b-problem/

In the solution below:

a ^ b take care of plus (don't take care of carry)

(a & b) take care of carry.

In the while loop, it continues until b is 0. It will loop at most 32 times. Since each time

b left shift by 1.

Here is the solution:

```java
public int aplusb(int a, int b) {
    // write your code here, try to do it without arithmetic operators.
    int carry = 0;
    while (b != 0) {
        carry = a & b;
        a = a ^ b;
        b = carry << 1;
    }
    return a;
}

```
<br>
<br>

###2 Digit Counts
---

http://www.lintcode.com/en/problem/digit-counts/

<pre>
Count the number of k's between 0 and n. k can be 0 - 9.

Have you met this question in a real interview? Yes
Example
if n=12, k=1 in [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], we have FIVE 1's (1, 10, 11, 12)
</pre>

This problem is same with count digit 1 in leetcode.

**Solution 1**: brute force. Time complexity: O(n * L), L is number of digits of n.

Remember take care of case when K == 0.

```java
    public int digitCounts(int k, int n) {
        // write your code here
        int res = 0;
        if (k == 0) {
            res++;
        }
        for (int i = 0; i <= n; i++) {
            int temp = i;
            while (temp != 0) {
                int mod = temp % 10;
                if (mod == k) {
                    res++;
                }
                temp /= 10;
            }
        }
        return res;
    }
```


**Solution 2**: Try a few examples and find patterns.

We can sum the occur k at each digit(index).

For example, suppose N = 3121

It has 4 digits. Thus result = count(digit 1th) + count(digit 10th) + count(digit 100th) + count(digit 1000th)

count(digit nth) = 
<pre>
- if digit nth == k, count = (number left of nth * nth) + (number right of nth) + 1
- if digit nth < k, count = (number left of nth * nth)
- if digit nth > k, count = (number left of nth + 1) * nth.
</pre>
See details in the following example.

Suppose k is 2.

count(digit 1th) = 312 * 1. Since (digit 1th) = 1 != 2, thus count = 312 (number left of 1th) * 1(1th) =  2 12 22 32 42.....3112

count(digit 10th) = 31 * 10 + 1 + 1. Since (digit 10th) = 2 == 2, count = 31 (number left of 10th) * 10 (10th) + 1 (number right of 10th) + 1 = 120 121 122.... 3120 3121 20 21 

count(digit 100th) = 3 * 100. Since (digit 100th) = 1 != 2, count = 3 (number left of 100th) * 100(100th)

count(digit 1000th) = 1 * 1000. Since (digit 1000th) = 3 > 2, count = ( 0 (number left of 1000th) + 1) * 1000th(1000th).

result = 312 + 312 + 300 + 1000= 1924

**Note we need to take care of case when k = 0**



```java
public int digitCounts(int k, int n) {
    // write your code here
    int res = 0;
    if (k == 0) {
        res++;
        k = 10;
    }
    int digit = 1;
    int right = 0;
    while (n > 0) {
        int cur = n % 10;
        res += (n / 10) * digit;
        if (cur == k) {
            res += right + 1;
        } else if (cur > k) {
            res += digit;
        }
        right += (n % 10) * digit;
        digit *= 10;
        n /= 10;
    }
    return res;
}

```

###3 Maximum Subarray
---

http://www.lintcode.com/en/problem/maximum-subarray/

<pre>
Given an array of integers, find a contiguous subarray which has the largest sum.

Have you met this question in a real interview? Yes
Example
Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.

Note
The subarray should contain at least one number.

Challenge
Can you do it in time complexity O(n)?
</pre>

**Idea**: dp

```java
public int maxSubArray(int[] nums) {
    // write your code
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int local = nums[0];
    int res = nums[0];
    for (int i = 1; i < nums.length; i++) {
        local = Math.max(local + nums[i], nums[i]);
        res = Math.max(local, res);
    }
    return res;
}
```


###4 Maximum Subarray II
---

http://www.lintcode.com/en/problem/maximum-subarray-ii/

<pre>
Given an array of integers, find two non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Have you met this question in a real interview? Yes
Example
For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], they both have the largest sum 7.

Note
The subarray should contain at least one number

Challenge
Can you do it in time complexity O(n) ?
</pre>

**Idea**:

Walk from left to right and right to left, calculate Maxium subarray sum.

left[i] = largest sum of [0, i]. 

right[i] = largest sum of [i, arr.length - 1].

Since the array should not be over-lapping.

The largest sum would be Max(left[i] + right[i + 1])
 
```java
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int[] left = new int[nums.size()];
        int[] right = new int[nums.size()];
        int local = nums.get(0);
        left[0] = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            local = Math.max(local + nums.get(i), nums.get(i));
            left[i] = Math.max(left[i - 1], local);
        }
        local = nums.get(nums.size() - 1);
        right[nums.size() - 1] = local;
        for (int i = nums.size() - 2; i >= 0; i--) {
            local = Math.max(local + nums.get(i), nums.get(i));
            right[i] = Math.max(right[i + 1], local);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            int cur = left[i] + right[i + 1];
            res = Math.max(cur, res);
        }
        return res;
    }
```


###5 Maximum Subarray III
---

http://www.lintcode.com/en/problem/maximum-subarray-iii/

<pre>
Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Have you met this question in a real interview? Yes
Example
Given [-1,4,-2,3,-2,3], k=2, return 8

Note
The subarray should contain at least one number
</pre>

**Idea**: dp

dp[i][j] represents [0, i] of array has been selected j subarrays.

dp[i][j] = Math.max(dp[m][j - 1] + maxSubarray(m + 1, i))

```java
    public int maxSubArray(ArrayList<Integer> nums, int k) {
        // write your code
        int[][] dp = new int[nums.size() + 1][k + 1];
        for (int i = 0; i <= nums.size(); i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = Integer.MIN_VALUE;
                if (j == 0) {
                    dp[i][j] = 0;
                } else {
                    for (int m = j - 1; m < i; m++) {
                        dp[i][j] = Math.max(dp[i][j], dp[m][j - 1] + getMax(nums, m , i));
                    }
                }
            }
        }
        return dp[nums.size()][k];
    }
    private int getMax(ArrayList<Integer> nums, int start, int end) {
        if (nums == null || nums.size() == 0 || start > end || start >= nums.size()) {
            return 0;
        }
        int local = nums.get(start);
        int res = nums.get(start);
        for (int i = start + 1; i < end; i++) {
            local = Math.max(local + nums.get(i), nums.get(i));
            res = Math.max(local, res);
        }
        return res;

    }
```

###6 Maximum Subarray Difference
---

http://www.lintcode.com/en/problem/maximum-subarray-difference/

<pre>

Given an array with integers.

Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.

Return the largest difference.

Have you met this question in a real interview? Yes
Example
For [1, 2, -3, 1], return 6

Note
The subarray should contain at least one number

Challenge
O(n) time and O(n) space.

</pre>

**Idea**: Calculate the min-subarray and max-subarray at left side and right side of index i. Then get the max difference based on these four arrays.

```java
public int maxDiffSubArrays(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int[] leftMax = new int[nums.size()];
        int[] leftMin = new int[nums.size()];
        int[] rightMax = new int[nums.size()];
        int[] rightMin = new int[nums.size()];
        int localMin = nums.get(0);
        int localMax = nums.get(0);
        leftMin[0] = nums.get(0);
        leftMax[0] = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            localMax = Math.max(localMax + nums.get(i), nums.get(i));
            localMin = Math.min(localMin + nums.get(i), nums.get(i));
            leftMax[i] = Math.max(leftMax[i - 1], localMax);
            leftMin[i] = Math.min(leftMin[i - 1], localMin);
        }
        localMax = nums.get(nums.size() - 1);
        localMin = nums.get(nums.size() - 1);
        rightMax[nums.size() - 1] = localMax;
        rightMin[nums.size() - 1] = localMax;
        for (int i = nums.size() - 2; i >= 0; i--) {
            localMin = Math.min(localMin + nums.get(i), nums.get(i));
            localMax = Math.max(localMax + nums.get(i), nums.get(i));
            rightMin[i] = Math.min(rightMin[i + 1], localMin);
            rightMax[i] = Math.max(rightMax[i + 1], localMax);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            int num1 = Math.abs(leftMax[i] - rightMin[i + 1]);
            int num2 = Math.abs(leftMax[i] - rightMax[i + 1]);
            int num3 = Math.abs(leftMin[i] - rightMax[i + 1]);
            int num4 = Math.abs(leftMin[i] - rightMin[i + 1]);
            res = Math.max(num1, res);
            res = Math.max(num2, res);
            res = Math.max(num3, res);
            res = Math.max(num4, res);
        }
        return res;
    }

```

<br>
<br>

###7 Majority Number III

http://www.lintcode.com/en/problem/majority-number-iii/

<pre>
Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.

Find it.

Have you met this question in a real interview? Yes
Example
Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.

Note
There is only one majority number in the array.

Challenge
O(n) time and O(k) extra space
</pre>

See Majority Number I and Majority Number II by the following link under leetcode 

https://github.com/wishyouhappy/Algorithm/blob/master/leetcode/README.md#229-majority-element-ii


**Idea**: Same idea with Majority Number I and Majority Number II. Here we need to maintain k candidates. 
To check the candidates easily, we maintain them in a map. 

- If nums[i] exists in map, (count of nums[i])++
- if map.size() < k, put candidate nums[i] into map
- if one of candidate's count == 0, remove this candidate and add nums[i] to map
- otherwise, decrease the count of all candidates in the map.

```java
public int majorityNumber(ArrayList<Integer> nums, int k) {
        // write your code
        Map<Integer, Integer> map =  new HashMap<>();
        for (Integer num : nums) {
            Integer count = map.get(num);
            if (count != null) {
                map.put(num, count + 1);
            } else if (map.size() < k){
                map.put(num, 1);
            } else {
                if (map.containsValue(0)) {
                    removeZero(map);
                    map.put(num, 1);
                } else {
                    decreaseCount(map);
                }
            }
        }
        int majorCount = nums.size() / k + 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (getCount(entry.getKey(), nums) >= majorCount) {
                return entry.getKey();
            }
        }
        return -1;
    }
    private void removeZero(Map<Integer, Integer> map) {
        int removeKey = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                removeKey = entry.getKey();
                break;
            }
        }
        map.remove(removeKey);
    }
    private void decreaseCount(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.setValue(entry.getValue() - 1);
        }
    }
    private int getCount(int key, ArrayList<Integer> nums) {
        int count = 0;
        for (int num : nums) {
            if (num == key) {
                count++;
            }
        }
        return count;
    }

```

<br>
<br>

###8 Previous Permutation
http://www.lintcode.com/en/problem/previous-permutation/

<pre>
Given a list of integers, which denote a permutation.

Find the previous permutation in ascending order.

Have you met this question in a real interview? Yes
Example
For [1,3,2,3], the previous permutation is [1,2,3,3]

For [1,2,3,4], the previous permutation is [4,3,2,1]

Note
The list may contains duplicate integers.
</pre>

**Idea**: 

- From index = nums.size() - 2, find the first index that nums[index] > nums[index + 1]

- From i = nums.size() - 1, find the first i that nums[i] < nums[index]

- Swap nums[i] nums[index]

- reverse elements from index + 1 to the end of the array.

```java
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
		// write your code
		if (nums == null || nums.size() <= 1) {
		    return nums;
		}
		int index = nums.size() - 2;
		while (index >= 0 && nums.get(index) <= nums.get(index + 1)) {
		    index--;
		}
		if (index == -1) {
		    Collections.reverse(nums);
		    return nums;
		}
		int i = nums.size() - 1;
		while (i >= 0 && nums.get(index) <= nums.get(i)) {
		    i--;
		}
		Collections.swap(nums, i, index);
		reverse(nums, index + 1);
		return nums;
    }
    private void reverse(ArrayList<Integer> nums, int index) {
        int i = index; 
        int j = nums.size() - 1;
        while (i < j) {
            Collections.swap(nums, i++, j--);
        }
    }
```

<br>
<br>

###9 Longest Common Subsequence

http://www.lintcode.com/en/problem/longest-common-subsequence/

<pre>
Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Have you met this question in a real interview? Yes
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.
</pre>

**Idea**: DP

```java
public int longestCommonSubsequence(String A, String B) {
    // write your code here
    int[][] dp = new int[A.length() + 1][B.length() + 1];
    for (int i = 0; i < A.length(); i++) {
        for (int j = 0; j < B.length(); j++) {
            if (A.charAt(i) == B.charAt(j)) {
                dp[i + 1][j + 1] = dp[i][j] + 1;
            } else {
                dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }
    }
    return dp[A.length()][B.length()];
}
```

<br>
<br>

###10 k Sum

http://www.lintcode.com/en/problem/k-sum/

<pre>
Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.

</pre>

**Idea**: In 11 k Sum II, we solve it like combination sum because we need to know all the combinations.

Here in this problem, we only want to know the number of solutions. Thus we can solve it with dp.




<br>
<br>

###11 k Sum II

http://www.lintcode.com/en/problem/k-sum-ii/#


<pre>
Given n unique integers, number k (1<=k<=n)  and target. Find all possible k integers where their sum is target.

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k=2, target=5, [1,4] and [2,3] are possible solutions.
</pre>

**Idea**: Combination sum. Recursively add one element each until k elements is added and sum is target.

```java
public ArrayList<ArrayList<Integer>> kSumII(int A[], int k, int target) {
    // write your code here
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    sumHelper(res, A, 0, k, target, new ArrayList<Integer>());
    return res;
}
private void sumHelper(ArrayList<ArrayList<Integer>> res, int[] A, int index, int k, int target, ArrayList<Integer> cur) {
    if (cur.size() == k) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(cur));
        }
        return;
    }
    if (target <= 0) {
        return;
    }
    for (int i = index; i < A.length; i++) {
        cur.add(A[i]);
        sumHelper(res, A, i + 1, k, target - A[i], cur);
        cur.remove(cur.size() - 1);
    }
}

```


<br>
<br>

###12 Minimum Adjustment Cost

http://www.lintcode.com/en/problem/minimum-adjustment-cost/#

<pre>
Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|

Have you met this question in a real interview? Yes
Example
Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal.

Return 2.
</pre>

**Idea**: DP, min[i][j] represents when A[i] change to val, satisfy that  all neighbors in A[0-i] have difference <= target

Since there is a range, that is, all numbers after change should in [1, 100]. We can try all the numbers in each range for each number in A[i]. Find the min in all the valid adjusted sequences.

For each spot in A[i], if we adjust its value to j, then the valid A[i] is in 

```java
[Math.max(0, j - target), Math.min(maxInt, j + target]
```

Thus the induction rule of dp is:

```java
min[i + 1][j] = Math.min(min[i + 1][j], min[i][k] + Math.abs(A.get(i) - j)), k is between [Math.max(0, j - target), Math.min(maxInt, j + target]
```


```java
public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
    // min[i][j] represents when A[i] change to val, satisfy all neighbors have difference <= target
    int maxInt = 100;
    int[][] min = new int[A.size() + 1][maxInt + 1];
    for (int i = 0; i < A.size(); i++) {
        for (int j = 0; j <= maxInt; j++) {
            min[i + 1][j] = Integer.MAX_VALUE;
            int diff = Math.abs(A.get(i) - j);
            int start = Math.max(0, j - target);
            int end = Math.min(maxInt, j + target);
            for (int k = start; k <= end; k++) {
                min[i + 1][j] = Math.min(min[i + 1][j], min[i][k] + diff);
            }
        }
    }
    return getMin(min);
}
private int getMin(int[][] min) {
    int res = min[min.length - 1][0];
    for (int i = 1; i < min[0].length; i++) {
        res = Math.min(res, min[min.length - 1][i]);
    }
    return res;
}
```


<br>
<br>


###13 Backpack

http://www.lintcode.com/en/problem/backpack/#


<pre>
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

Have you met this question in a real interview? Yes
Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Note
You can not divide any item into small pieces.

Challenge
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.

</pre>

**Idea**: dp

- res[i + 1][j] represents max weight by choosing from first i items and total weight <= j. 
- iterate all items, check if we can or can not choose the current item.
  * if j < A[i], we can not choose the current item
  * otherwise, we update res[i + 1][j] with Math.max(res[i][j], res[i][j - A[i]] + A[i])


```java
    public int backPack(int m, int[] A) {
        // res[i + 1][j] represents max weight by choosing from first i items and total weight <= j. 
        int[][] res = new int[A.length + 1][m + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j <= m; j++) {
                if (j < A[i]) {
                    res[i + 1][j] = res[i][j]; // j < A[i], can not pick A[i]
                } else {
                    res[i + 1][j] = Math.max(res[i][j], res[i][j - A[i]] + A[i]);
                }
            }
        }
        return res[A.length][m];
    }
```

Change to one dimensional:

Two notes:

- Here inner loop should begin from m, then decrease. We need to avoid override values that will be used later
- We only need to loop from m to A[i], because when j < A[i], res[j] does not change.

```java
public int backPack(int m, int[] A) {
    // res[j] represents max weight by choosing from first i items and total weight <= j. 
    int[] res = new int[m + 1];
    for (int i = 0; i < A.length; i++) {
        for (int j = m; j >= A[i]; j--) {
            res[j] = Math.max(res[j], res[j - A[i]] + A[i]);
        }
    }
    return res[m];
}
```

<br>
<br>


###14 Backpack II

http://www.lintcode.com/en/problem/backpack-ii/#

<pre>
Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?

Have you met this question in a real interview? Yes
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.

Note
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Challenge
O(n x m) memory is acceptable, can you do it in O(m) memory?
</pre>
**Idea**: Change the max weight to max value in **13 Backpack**. Here res[i + 1][j] represents max value by choosing from first i items and total weight <= j. 

**O(n x m) memory**:
```java
    public int backPackII(int m, int[] A, int V[]) {
        // res[i + 1][j] represents max value by choosing from first i items and total weight <= j. 
        int[][] res = new int[A.length + 1][m + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j <= m; j++) {
                if (j < A[i]) {
                    res[i + 1][j] = res[i][j]; // j < A[i], can not pick A[i]
                } else {
                    res[i + 1][j] = Math.max(res[i][j], res[i][j - A[i]] + V[i]);
                }
            }
        }
        return res[A.length][m];
    }
```

**O(m)memory**:

```java
    public int backPackII(int m, int[] A, int V[]) {
        // res[j] represents max value by choosing from first i items and total weight <= j. 
        int[] res = new int[m + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                res[j] = Math.max(res[j], res[j - A[i]] + V[i]);
            }
        }
        return res[m];
    }
```

###15 Max Tree

http://www.lintcode.com/en/problem/max-tree/

<pre>
Given an integer array with no duplicates. A max tree building on this array is defined as follow:

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.

Have you met this question in a real interview? Yes
Example
Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:

    6
   / \
  5   3
 /   / \
2   0   1
Challenge
O(n) time and memory.
</pre>


**Recursion**: stackoverflow on the last use case

Time : if tree is balanced, O(nlgn). Worst case O(n ^ 2)

```java
    public TreeNode maxTree(int[] A) {
        return createTree(A, 0, A.length - 1);
    }
    private TreeNode createTree(int[] A, int start, int end) {
        if (start > end) {
            return null;
        }
        int index = getMax(A, start, end);
        TreeNode root = new TreeNode(A[index]);
        root.left = createTree(A, start, index - 1);
        root.right = createTree(A, index + 1, end);
        return root;
    }
    private int getMax(int[] A, int start, int end) {
        int max = start;
        for (int i = start + 1; i <= end; i++) {
            if (A[i] > A[max]) {
                max = i;
            }
        }
        return max;
    }
```

**Using stack**: O(N) time

Here, in the stack, value from bottom to top is decreasing. 

If the value keep increasing, we know all previous values are on the left subtree of the current value. If at some where it begin decreasing, we know this decreasing value must on the right subtree of the previous value.

```java
public TreeNode maxTree(int[] A) {
    Deque<TreeNode> stack = new LinkedList<>();
    for (int num : A) {
        TreeNode cur = new TreeNode(num);
        while (!stack.isEmpty() && cur.val > stack.peek().val) {
            cur.left = stack.pop();
        }
        if (!stack.isEmpty()) {
            stack.peek().right = cur;
        }
        stack.push(cur);
    }
    TreeNode res = stack.pop();
    while (!stack.isEmpty()) {
        res = stack.pop();
    }
    return res;
}

```
<br>
<br>


###16 Hash Function

http://www.lintcode.com/en/problem/hash-function/

<pre>
In data structure Hash, hash function is used to convert a string(or any other type) into an integer smaller than hash size and bigger or equal to zero. The objective of designing a hash function is to "hash" the key as unreasonable as possible. A good hash function can avoid collision as less as possible. A widely used hash function algorithm is using a magic number 33, consider any string as a 33 based big integer like follow:

hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE 

                              = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE

                              = 3595978 % HASH_SIZE

here HASH_SIZE is the capacity of the hash table (you can assume a hash table is like an array with index 0 ~ HASH_SIZE-1).

Given a string as a key and the size of hash table, return the hash value of this key.f



Have you met this question in a real interview? Yes
Example
For key="abcd" and size=100, return 78

Clarification
For this problem, you are not necessary to design your own hash algorithm or consider any collision issue, you just need to implement the algorithm as described.
</pre>

**Idea**: If we simply follow the instructions above, we might have overflow problem. Because (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) + ..... can be really large.

Luckily, there are some property of arithmetic modular:
https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-addition-and-subtraction

- **(A + B) mod C = (A mod C + B mod C) mod C**
- **(A - B) mod C = (A mod C - B mod C) mod C**
- **(A * B) mod C = (A mod C * B mod C) mod C**
- **A^B mod C = ( (A mod C)^B ) mod C**

Thus, by using **(A + B) mod C = (A mod C + B mod C) mod C**, each time after we add a new char, we do mod to avoid overflow.

```java
res = res * 33 + (int) c;
res %= HASH_SIZE;
```

```java
public int hashCode(char[] key,int HASH_SIZE) {
    long res = 0;
    for (char c : key) {
        res = res * 33 + (int) c;
        res %= HASH_SIZE;
    }
    return (int) res;
}
```
<br>
<br>

###17 Rehashing

http://www.lintcode.com/en/problem/rehashing/#

<pre>
The size of the hash table is not determinate at the very beginning. If the total size of keys is too large (e.g. size >= capacity / 10), we should double the size of the hash table and rehash every keys. Say you have a hash table looks like below:

size=3, capacity=4

[null, 21, 14, null]
       ↓    ↓
       9   null
       ↓
      null
The hash function is:

int hashcode(int key, int capacity) {
    return key % capacity;
}
here we have three numbers, 9, 14 and 21, where 21 and 9 share the same position as they all have the same hashcode 1 (21 % 4 = 9 % 4 = 1). We store them in the hash table by linked list.

rehashing this hash table, double the capacity, you will get:

size=3, capacity=8

index:   0    1    2    3     4    5    6   7
hash : [null, 9, null, null, null, 21, 14, null]
Given the original hash table, return the new hash table after rehashing .

Have you met this question in a real interview? Yes
Example
Given [null, 21->9->null, 14->null, null],

return [null, 9->null, null, null, null, 21->null, 14->null, null]

Note
For negative integer in hash table, the position can be calculated as follow:

C++/Java: if you directly calculate -4 % 3 you will get -1. You can use function: **a % b = (a % b + b) % b to make it is a non negative integer**.
Python: you can directly use -1 % 3, you will get 2 automatically.

</pre>

```java
public ListNode[] rehashing(ListNode[] hashTable) {
    ListNode[] newTable = new ListNode[hashTable.length * 2];
    for (ListNode node : hashTable) {
        hash(node, newTable);
    }
    return newTable;
}
private void hash(ListNode node, ListNode[] newTable) {
    int size = newTable.length;
    while (node != null) {
        ListNode temp = node.next;
        int index = node.val >= 0 
                ? (node.val % size) : (((node.val % size) + size) % size);
        ListNode head = newTable[index];
        if (head == null) {
            newTable[index] = node;
        } else {
            while (head.next != null) {
                head = head.next;
            }
            head.next = node;
        }
        node.next = null;
        node = temp;
    }
}

```

<br>
<br>

###18 Heapify

http://www.lintcode.com/en/problem/heapify/

<pre>
Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
Have you met this question in a real interview? Yes
Example
Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.

Challenge
O(n) time complexity

Clarification
What is heap?

Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.

What is heapify?
Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].

What if there is a lot of solutions?
Return any of them.
</pre>

**Recursive**: perform heapifydown for  index i = A.length / 2 - 1 to 0.

heapifyDown: swap parent with the smallest of left and right child. 


```java
    public void heapify(int[] A) {
        for (int i = A.length / 2 - 1; i >= 0; i--) {
            heapifyDown(i, A);
        }
    }
    
    private void heapifyDown(int index, int[] A) {
        if (isLeaf(index, A)) {
            return;
        }
        int smallest = index;
        if (index * 2 + 1 < A.length && A[index * 2 + 1] < A[index]) {
            smallest = index * 2 + 1;
        }
        if (index * 2 + 2 < A.length && A[index * 2 + 2] < A[smallest]) {
            smallest = index * 2 + 2;
        }
        if (smallest != index) {
            swap(index, smallest, A);
            heapifyDown(smallest, A);
        }
    }
    
    private void swap(int i, int j, int[] A) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    private boolean isLeaf(int i, int[] A) {
        return i * 2 + 1 >= A.length;
    }
```

**Iterative**:


```java
    public void heapify(int[] A) {
        for (int i = A.length / 2 - 1; i >= 0; i--) {
            heapifyDown(i, A);
        }
    }
    
    private void heapifyDown(int index, int[] A) {
        while (!isLeaf(index, A)) {
            int smallest = index;
            if (index * 2 + 1 < A.length && A[index * 2 + 1] < A[index]) {
                smallest = index * 2 + 1;
            }
            if (index * 2 + 2 < A.length && A[index * 2 + 2] < A[smallest]) {
                smallest = index * 2 + 2;
            }
            if (smallest != index) {
                swap(index, smallest, A);
                index = smallest;
            } else {
                break;
            }
        }
    }
    
    private void swap(int i, int j, int[] A) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    private boolean isLeaf(int i, int[] A) {
        return i * 2 + 1 >= A.length;
    }
```


###20 Subarray Sum Closest

http://www.lintcode.com/en/problem/subarray-sum-closest/

<pre>
Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.

Have you met this question in a real interview? Yes
Example
Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].


</pre>


**O(n^2) solution**:
- calcualte sums array, sums[i] = sum(arr[0] + arr[1] + .... + arr[i]). O(n)
- two for loop check each subarray. O(n^2)


```java
public ArrayList<Integer> subarraySumClosest(int[] nums) {
    int[] sums = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
        sums[i] = i == 0 ? nums[i] : sums[i - 1] + nums[i];
    }
    int start = 0;
    int end = 0;
    int diff = Integer.MAX_VALUE;
    for (int i = 0; i < sums.length; i++) {
        for (int j = i; j < sums.length; j++) {
            if (Math.abs(sums[j] - sums[i] + nums[i]) < diff) {
                diff = Math.abs(sums[j] - sums[i] + nums[i]);
                start = i;
                end = j;
            }
        }
    }
    ArrayList<Integer> res = new ArrayList<>();
    res.add(start);
    res.add(end);
    return res;
}
```

**O(nlgn) solution**:

- After we have sums array, sums[i] = sum(arr[0] + arr[1] + .... + arr[i]). O(n)
- sort sums array. O(nlgn)
- check adjacent sums[i] and sums[i + 1]. The closest to 0 subarray must in the adjacent sums[i]. O(n)
  **Here since we need to sort sums, thus the index is disordered, thus we need to record the original index for each sum element.**

Also, note that before sort sums[i] - sums[j] = {arr[j + 1], arr[j + 2] ... arr[i]}. It doesnot include arr[j]. 
Thus when calculate start index of subarray, remember to plus 1 to the index of sum.

```java
    class Pair {
        int index;
        int sum;
        Pair(int index, int sum) {
            this.index = index;
            this.sum = sum;
        }
    }
    public ArrayList<Integer> subarraySumClosest(int[] nums) {
        Pair[] sums = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sums[i] = i == 0 
                ? new Pair(i, nums[i]) : new Pair(i, sums[i - 1].sum + nums[i]);
        }
        Arrays.sort(sums, new Comparator<Pair>() {
            public int compare(Pair e1, Pair e2) {
                return e1.sum - e2.sum;
            }
        });
        return findClosest(sums);
    }
    private ArrayList<Integer> findClosest(Pair[] sums) {
        int start = -1;
        int end = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 1; i < sums.length; i++) {
            if (Math.abs(sums[i].sum - sums[i - 1].sum) < diff) {
                start = Math.min(sums[i - 1].index, sums[i].index);
                end = Math.max(sums[i - 1].index, sums[i].index);
                diff = Math.abs(sums[i].sum - sums[i - 1].sum);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(start + 1);
        res.add(end);
        return res;
    }
```

<br>
<br>

###20 Fast Power

http://www.lintcode.com/en/problem/fast-power/#

<pre>
Calculate the an % b where a, b and n are all 32bit integers.

Have you met this question in a real interview? Yes
Example
For 231 % 3 = 2

For 1001000 % 1000 = 0

Challenge
O(logn)
</pre>


**Idea**:  **(A * B) mod C = (A mod C * B mod C) mod C**

```java
public int fastPower(int a, int b, int n) {
    if (n == 0) {
        return 1 % b;
    } else if (n == 1) {
        return a % b;
    }
    long half = fastPower(a, b, n / 2) % b;
    long res = (half * half) % b; 
    if (n % 2 == 1) {
        res = (res * (a % b)) % b;
    }
    return (int) res;
}
```




<br>
<br>

###21 Route Between Two Nodes in Graph
http://www.lintcode.com/en/problem/route-between-two-nodes-in-graph/#

<pre>
Medium Route Between Two Nodes in Graph Show result 

35% Accepted
Given a directed graph, design an algorithm to find out whether there is a route between two nodes.

Have you met this question in a real interview? Yes
Example
Given graph:

A----->B----->C
 \     |
  \    |
   \   |
    \  v
     ->D----->E
for s = B and t = E, return true

for s = D and t = C, return false
</pre>


```java
public boolean hasRoute(ArrayList<DirectedGraphNode> graph, 
                        DirectedGraphNode s, DirectedGraphNode t) {
    Set<DirectedGraphNode> visited = new HashSet<>();
    return findRoute(s, visited, graph, t);
}
private boolean findRoute(DirectedGraphNode node, Set<DirectedGraphNode> visited, ArrayList<DirectedGraphNode> graph, DirectedGraphNode t) {
    if (node == t) {
        return true;
    }
    visited.add(node);
    for (DirectedGraphNode temp : node.neighbors) {
        if (temp == t) {
            return true;
        }
        if (!visited.contains(temp)) {
             if (findRoute(temp, visited, graph, t)) {
                 return true;
             }
        } 
    }
    return false;
}
```
<br>
<br>

###22 Interleaving Positive and Negative Numbers

http://www.lintcode.com/en/problem/interleaving-positive-and-negative-numbers/#

<pre>
    
Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

Have you met this question in a real interview? Yes
Example
Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.

Note
You are not necessary to keep the original order of positive integers or negative integers.  
</pre>

**Idea**: First partition the array, so that positive and negative numbers are on the left side or right side of the array.

Then interleaving positive and negative numbers by swaping.

Note: We need to check if there are more negative numbers or positive numbers.

If positive numbers > negative numbers, we should interleaving like positive negative positive .......negative positive

Otherwise, we should interleaving like  negative positive .......negative positive negative.

```java
public void rerange(int[] A) {
    int index = partition(A);
    int count = Math.min(A.length - index, index);
    int start = count == index ? 0 : 1;
    for (int i = start; i < count * 2 + start; i += 2) {
        swap(A, i, index++);
    }
}

private int partition(int[] A) {
    int l = 0;
    for (int i = 0; i < A.length; i++) {
        if (A[i] < 0) {
            swap(A, i, l++);
        }
    }
    return l;
}

private void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
}
```

<br>
<br>



###23 Binary Representation


http://www.lintcode.com/en/problem/binary-representation/


<pre>
Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in as a string. If the fractional part of the number can not be represented accurately in binary with at most 32 characters, return ERROR.

Have you met this question in a real interview? Yes
Example
For n = "3.72", return "ERROR".

For n = "3.5", return "11.1".
</pre>

Special case: 1.0 return 1

```java
    public String binaryRepresentation(String n) {
        StringBuilder res = new StringBuilder();
        int index = n.indexOf(".");
        if (index != -1) {
            res.append(Long.toBinaryString(Long.parseLong(n.substring(0, index))));
            double decimal = Double.parseDouble(n.substring(index));
            if (0.0 == decimal) {
                return res.toString();
            }
            res.append(".");
            String decimalString = decimalToBinary(decimal);
            if (decimalString.length() > 32) {
                return "ERROR";
            }
            res.append(decimalString);
        }
        return res.toString();
    }
    
    private String decimalToBinary(double f) {
        StringBuilder res = new StringBuilder();
        double i = 1;
        while (i <= 33 && f != 0) {
            double temp = Math.pow(0.5, i++);
            if (f >= temp) {
                res.append("1");
                f -= temp;
            } else {
                res.append("0");
            }
        }
        return res.toString();
    }

```

In the above code, convert integer to String we use Long.toBinaryString(). We can implement our own to binary string method.

```java
private String toBinaryString(long n) {
    if (n == 0) {
        return "0";
    }
    StringBuilder res = new StringBuilder();
    while (n > 0) {
        res.append(n % 2);
        n /= 2;
    }
    return res.reverse().toString();
}
```

<br>
<br>

###24 Delete Digits

http://www.lintcode.com/en/problem/delete-digits/#

<pre>
Given string A representative a positive integer which has N digits, remove any k digits of the number, the remaining digits are arranged according to the original order to become a new positive integer.

Find the smallest integer after remove k digits.

N <= 240 and k <= N,

Have you met this question in a real interview? Yes
Example
Given an integer A = "178542", k = 4

return a string "12"
</pre>

**Idea**: Each time we delete a digit, we need to find the first decreasing digit, then delete it. This can make sure that the number is the smallest after delete this digit.


```java
public String DeleteDigits(String A, int k) {
    for (int i = 0; i < k; i++) {
        for (int j = 0; j < A.length(); j++) {
            if (j == A.length() - 1 || A.charAt(j) > A.charAt(j + 1)) {
                A = A.substring(0, j) + A.substring(j + 1);
                break;
            }
        }
    }
    int i = 0;
    while (A.charAt(i) == '0' && i != A.length() - 1) {
        i++;
    }
    return A.substring(i);
}
```

The above method takes O(N * K). We can reduce the time to O(N) by using an increasing stack. The stack helps us avoiding find decreasing digit each time from begining.


```java
    public String DeleteDigits(String A, int k) {
        Deque<Character> deque = new LinkedList<>();
        int count = 0;
        for (int j = 0; j < A.length(); j++) {
           if (deque.isEmpty() || A.charAt(j) >= deque.peek() || count >= k) {
               deque.push(A.charAt(j));
           } else {
               deque.pop();
               j--;
               count++;
           }
        }
        while (count++ < k) {
            deque.pop();
        }
        StringBuilder res = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char c = deque.pollLast();
            if (c != '0') {
                leadingZero = false;
            }
            if (!leadingZero) {
                res.append(c);
            }
        }
        if (res.length() == 0) {
            res.append("0");
        }
        return res.toString();
    }
```
<br>
<br>

###25 Wood cut
http://www.lintcode.com/en/problem/wood-cut/

<pre>
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Have you met this question in a real interview? Yes
Example
For L=[232, 124, 456], k=7, return 114.
</pre>

**Idea**: Binary search. Find len so that getPieces(len) >= k && getPieces(len + 1) < k.

```java
    public int woodCut(int[] L, int k) {
        int max = Integer.MIN_VALUE;
        for (int len : L) {
            max = Math.max(max, len);
        }
        int l = 1;
        int r = max;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int pieces = getPieces(L, mid);
            int p2 = getPieces(L, mid + 1);
            if (pieces >= k && p2 < k) {
                return mid;
            } else if (pieces < k) {
                r = mid - 1;
            } else {
                l = mid + 1;   
            }
        }
        return 0;
    }
    
    private int getPieces(int[] L, int len) {
        int res = 0;
        for (int num : L) {
            res += num / len;
        }
        return res;
    }
```

<br>
<br>

###26 Segment Tree Build

http://www.lintcode.com/en/problem/segment-tree-build/

<pre>
The structure of Segment Tree is a binary tree which each node has two attributes start and end denote an segment / interval.

start and end are both integers, they should be assigned in following rules:

The root's start and end is given by build method.
The left child of node A has start=A.left, end=(A.left + A.right) / 2.
The right child of node A has start=(A.left + A.right) / 2 + 1, end=A.right.
if start equals to end, there will be no children for this node.
Implement a build method with two parameters start and end, so that we can create a corresponding segment tree with every node has the correct start and end value, return the root of this segment tree.

Have you met this question in a real interview? Yes
Example
Given start=0, end=3. The segment tree will be:

               [0,  3]
             /        \
      [0,  1]           [2, 3]
      /     \           /     \
   [0, 0]  [1, 1]     [2, 2]  [3, 3]
Given start=1, end=6. The segment tree will be:

               [1,  6]
             /        \
      [1,  3]           [4,  6]
      /     \           /     \
   [1, 2]  [3,3]     [4, 5]   [6,6]
   /    \           /     \
[1,1]   [2,2]     [4,4]   [5,5]
Clarification
Segment Tree (a.k.a Interval Tree) is an advanced data structure which can support queries like:

which of these intervals contain a given point
which of these points are in a given interval
See wiki:
Segment Tree
Interval Tree
</pre>
Definition of SegmentTreeNode:

```java
/**
 * 
 * public class SegmentTreeNode {
 *     public int start, end;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end) {
 *         this.start = start, this.end = end;
 *         this.left = this.right = null;
 *     }
 * }
 */
```
Solution:

```java
public SegmentTreeNode build(int start, int end) {
    if (start > end) {
        return null;
    }
    SegmentTreeNode root = new SegmentTreeNode(start, end);        
    if (start == end) {
        return root;
    }
    int mid = start + (end - start) / 2;
    root.left = build(start, mid);
    root.right = build(mid + 1, end);
    return root;
}
```

<br>

<br>


###27 Segment Tree Query

http://www.lintcode.com/en/problem/segment-tree-query/

<pre>
For an integer array (index from 0 to n-1, where n is the size of this array), in the corresponding SegmentTree, each node stores an extra attribute max to denote the maximum number in the interval of the array (index from start to end).

Design a query method with three parameters root, start and end, find the maximum number in the interval [start, end] by the given root of segment tree.

Have you met this question in a real interview? Yes
Example
For array [1, 4, 2, 3], the corresponding Segment Tree is:

                  [0, 3, max=4]
                 /             \
          [0,1,max=4]        [2,3,max=3]
          /         \        /         \
   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
query(root, 1, 1), return 4

query(root, 1, 2), return 4

query(root, 2, 3), return 3

query(root, 0, 2), return 4

Note
It is much easier to understand this problem if you finished Segment Tree Build first.
</pre>

Definition of SegmentTreeNode:
```java
/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, max;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int max) {
 *         this.start = start;
 *         this.end = end;
 *         this.max = max
 *         this.left = this.right = null;
 *     }
 * }
 */

 ```

```java
public int query(SegmentTreeNode root, int start, int end) {
    if (root == null || start > root.end || end < root.start) {
        return 0;
    }
    if (root.start >= start && root.end <= end) {
        return root.max;
    }
    return Math.max(query(root.left, start, end), query(root.right, start, end));
}
```


<br>
<br>


###28 Segment Tree Modify

http://www.lintcode.com/en/problem/segment-tree-modify/

<pre>
For a Maximum Segment Tree, which each node has an extra value max to store the maximum value in this node's interval.

Implement a modify function with three parameter root, index and value to change the node's value with [start, end] = [index, index] to the new given value. Make sure after this change, every node in segment tree still has the max attribute with the correct value.

Have you met this question in a real interview? Yes
Example
For segment tree:

                      [1, 4, max=3]
                    /                \
        [1, 2, max=2]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]
if call modify(root, 2, 4), we can get:

                      [1, 4, max=4]
                    /                \
        [1, 2, max=4]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=4], [3, 3, max=0], [4, 4, max=3]
or call modify(root, 4, 0), we can get:

                      [1, 4, max=2]
                    /                \
        [1, 2, max=2]                [3, 4, max=0]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=0]
Note
We suggest you finish problem Segment Tree Build and Segment Tree Query first.

Challenge
Do it in O(h) time, h is the height of the segment tree.
</pre>
Definition of SegmentTreeNode:
```java
/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, max;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int max) {
 *         this.start = start;
 *         this.end = end;
 *         this.max = max
 *         this.left = this.right = null;
 *     }
 * }
 */
```

```java
public void modify(SegmentTreeNode root, int index, int value) {
    if (root == null || root.start > index || root.end < index) {
        return;
    }
    if (root.start == root.end) { //root.start = root.end = index
        root.max = value;
        return;
    }
    int mid = root.start + (root.end - root.start) / 2;
    if (index <= mid) {
        modify(root.left, index, value);
    } else {
        modify(root.right, index, value);
    }
    root.max = Math.max(root.left.max, root.right.max);
}
```

<br>
<br>

###29 Interval Minimum Number

http://www.lintcode.com/en/problem/interval-minimum-number/

<pre>
Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the minimum number between index start and end in the given array, return the result list.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]

Note
We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Challenge
O(logN) time for each query

</pre>


**Idea**: segmentation tree

```java
/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */
public class Solution {
    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Integer> intervalMinNumber(int[] A, 
                                                ArrayList<Interval> queries) {
        Node root = buildTree(0, A.length - 1, A);
        ArrayList<Integer> res = new ArrayList<>();
        for (Interval i : queries) {
            res.add(queryMin(i.start, i.end, root));
        }
        return res;
    }
    
    private int queryMin(int start, int end, Node root) {
        if (root == null || start > root.end || end < root.start) {
            return Integer.MAX_VALUE;
        }
        if (root.start >= start && root.end <= end) {
            return root.min;
        }
        return Math.min(queryMin(start, end, root.left), queryMin(start, end, root.right));
    }
    
    private Node buildTree(int start, int end, int[] A) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new Node(start, end, A[start]);
        }
        Node root = new Node(start, end, 0);
        int mid = start + (end - start) / 2;
        root.left = buildTree(start, mid, A);
        root.right = buildTree(mid + 1, end, A);
        if (root.left == null) {
            root.min = root.left.min;
        } else if (root.right == null) {
            root.min = root.right.min;
        } else {
            root.min = Math.min(root.left.min, root.right.min);
        }
        return root;
    }
}

class Node {
    public int start, end, min;
    public Node left, right;
    public Node(int start, int end, int min) {
        this.start = start;
        this.end = end;
        this.min = min;
        this.left = this.right = null;
    }
}

```

<br>
<br>

###30 Interval Sum

http://www.lintcode.com/en/problem/interval-sum/

<pre>
Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given array, return the result list.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [(0,4),(1,2),(2,4)], return [23,9,20]

Note
We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Challenge
O(logN) time for each query
</pre>

**Idea**: change the min to sum in the segment tree Node class.

```java
/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */
public class Solution {
    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Long> intervalSum(int[] A, 
                                       ArrayList<Interval> queries) {
        Node root = buildTree(0, A.length - 1, A);
        ArrayList<Long> res = new ArrayList<>();
        for (Interval i : queries) {
            res.add(querySum(i.start, i.end, root));
        }
        return res;
    }
    private long querySum(int start, int end, Node root) {
        if (root == null || start > root.end || end < root.start) {
            return 0;
        }
        if (root.start >= start && root.end <= end) {
            return root.sum;
        }
        return querySum(start, end, root.left) + querySum(start, end, root.right);
    }
    
    private Node buildTree(int start, int end, int[] A) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new Node(start, end, A[start]);
        }
        Node root = new Node(start, end, 0);
        int mid = start + (end - start) / 2;
        root.left = buildTree(start, mid, A);
        root.right = buildTree(mid + 1, end, A);
        if (root.left == null) {
            root.sum = root.left.sum;
        } else if (root.right == null) {
            root.sum = root.right.sum;
        } else {
            root.sum = root.left.sum + root.right.sum;
        }
        return root;
    }
}

class Node {
    public int start, end;
    public long sum;
    public Node left, right;
    public Node(int start, int end, long sum) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        this.left = this.right = null;
    }
}

```

<br>
<br>

###31 Interval Sum II

http://www.lintcode.com/en/problem/interval-sum-ii/

<pre>
Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):

For query(start, end), return the sum from index start to index end in the given array.
For modify(index, value), modify the number in the given index to value
Have you met this question in a real interview? Yes
Example
Given array A = [1,2,7,8,5].

query(0, 2), return 10.
modify(0, 4), change A[0] from 1 to 4.
query(0, 1), return 6.
modify(2, 1), change A[2] from 7 to 1.
query(2, 4), return 14.
Note
We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Challenge
O(logN) time for query and modify.
</pre>

```java
public class Solution {
    /* you may need to use some attributes here */
    private Node root;

    /**
     * @param A: An integer array
     */
    public Solution(int[] A) {
        root = buildTree(0, A.length - 1, A);
    }
    
    /**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        return querySum(start, end, root);
    }
    
    /**
     * @param index, value: modify A[index] to value.
     */
    public void modify(int index, int value) {
        modifyValue(root, index, value);
    }
    
    private void modifyValue(Node root, int index, int value) {
        if (root == null || root.start > index || root.end < index) {
            return;
        }
        if (root.start == root.end) {
            root.sum = value;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            modifyValue(root.left, index, value);
        } else {
            modifyValue(root.right, index, value);
        }
        if (root.left == null) {
            root.sum = root.right.sum;
        } else if (root.right == null) {
            root.sum = root.left.sum;
        } else {
            root.sum = root.left.sum + root.right.sum;
        }
        
    }
    
    private long querySum(int start, int end, Node root) {
        if (root == null || start > root.end || end < root.start) {
            return 0;
        }
        if (root.start >= start && root.end <= end) {
            return root.sum;
        }
        return querySum(start, end, root.left) + querySum(start, end, root.right);
    }
    
    private Node buildTree(int start, int end, int[] A) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new Node(start, end, A[start]);
        }
        Node root = new Node(start, end, 0);
        int mid = start + (end - start) / 2;
        root.left = buildTree(start, mid, A);
        root.right = buildTree(mid + 1, end, A);
        if (root.left == null) {
            root.sum = root.left.sum;
        } else if (root.right == null) {
            root.sum = root.right.sum;
        } else {
            root.sum = root.left.sum + root.right.sum;
        }
        return root;
    }
}

class Node {
    public int start, end;
    public long sum;
    public Node left, right;
    public Node(int start, int end, long sum) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        this.left = this.right = null;
    }
}


```

<br>
<br>


###32 Segment Tree Query II


http://www.lintcode.com/en/problem/segment-tree-query-ii/

<pre>
For an array, we can build a SegmentTree for it, each node stores an extra attribute count to denote the number of elements in the the array which value is between interval start and end. (The array may not fully filled by elements)

Design a query method with three parameters root, start and end, find the number of elements in the in array's interval [start, end] by the given root of value SegmentTree.

Have you met this question in a real interview? Yes
Example
For array [0, 2, 3], the corresponding value Segment Tree is:

                     [0, 3, count=3]
                     /             \
          [0,1,count=1]             [2,3,count=2]
          /         \               /            \
   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
query(1, 1), return 0

query(1, 2), return 1

query(2, 3), return 2

query(0, 2), return 2

Note
It is much easier to understand this problem if you finished Segment Tree Buildand Segment Tree Query first.
</pre>

```java
/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, count;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int count) {
 *         this.start = start;
 *         this.end = end;
 *         this.count = count;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     *@param root, start, end: The root of segment tree and 
     *                         an segment / interval
     *@return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null || start > root.end || end < root.start) {
            return 0;
        }
        if (root.start >= start && root.end <= end) {
            return root.count;
        }
        return query(root.left, start, end) + query(root.right, start, end);
    }
}

```
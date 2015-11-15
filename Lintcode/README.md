###Overview

* [1 A + B Problem](#1-a-+-b-problem)
* [2 Digit Counts](#2-digit-count)
* [3 Maximum Subarray](#3-maximum-subarray)
* [4 Maximum Subarray II](#4-maximum-subarray-ii)
* [5 Maximum Subarray III](#5-maxinum-subarray-iii)
* [6 Maximum Subarray Difference](#6-maximum-subarray-difference)
* [7 Majority Number III](#7-majority-number-iii)
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









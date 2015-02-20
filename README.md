

# leetcode
## Overview
* [1 Two Sum](#1-two-sum)
* [8 String to Integer atoi](#8-string-to-integer-atoi)
* [15 3Sum](#15-3sum)
* [16 3Sum Closest](#16-3sum-closest)
* [18 4Sum](#18-4sum)
* [29 Divide Two Integers](#29-divide-two-integers)
* [50 Pow(x,n)](#50-pow(x,n))
* [69 Sqrt(x)](#69-sqrt(x))
* [89 Gray Code](#89-gray-code)
* [121 Best Time to Buy and Sell Stock](#121-best-time-to-buy-and-sell-stock)
* [122 Best Time to Buy and Sell Stock II](#122-best-time-to-buy-and-sell-stock-ii)
* [123 Best Time to Buy and Sell Stock III](#123-best-time-to-buy-and-sell-stock-iii)
* [156 Binary Tree Upside Down](#156-binary-tree-upside-down)
* [157 Read N Characters Given Read4](#157-read-n-characters-given-read4)
* [158 Read N Characters Given Read4 II - Call multiple times](#158-read-n-characters-given-read4-ii-call-multiple-times) 
* [159 Longest String with At Most Two Distinct Characters](#159-longest-string-with-at-most-two-distinct-characters)
* [166 Fraction to Recurring Decimal](#166-fraction-to-recurring-decimal)
* [188 Best Time to Buy and Sell Stock IV](#188-best-time-to-buy-and-sell-stock-iv)


### 1 Two Sum
>Given an array of integers, find two numbers such that they add up to a specific target number.

>The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

>You may assume that each input would have exactly one solution.

>Input: numbers={2, 7, 11, 15}, target=9

>Output: index1=1, index2=2

**Solution1**: Use hashmap to record the number and its index, each time check if map containsKey target - num[i].

**Time complexity** O(n)

**Space** O(n)


	public int[] twoSum(int[] numbers, int target) {
        //use hashmap
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        if(numbers == null || numbers.length == 0) return result;
        
        for(int i = 0; i < numbers.length; i++){
            if(map.containsKey(target - numbers[i])){
                result[0] = map.get(target - numbers[i]) + 1;
                result[1] = i + 1;
                return result;
            }
            map.put(numbers[i], i);
        }
        return result;
    }

**Solution2**: We can first sort the numbers. Then use two pointers, the first pointer points to the begining and the second points to the end. Each time compares the target and num[p1]+num[p2], if target is bigger, p1++, else p2--. But it doesn't work for this problem, because we need to return the index. If we are requested to return the nunbers, we can use the following way.

**Time**: O(nlgn)

**Spae**: O(1)

	//this function returns the numbers, not the index
    public int[] twoSum(int[] numbers, int target) {
        //First sort, then use two pointers
        int[] res = new int[2];
        if(numbers == null || numbers.length <= 1) return res;
        Arrays.sort(numbers);
        int l = 0;
        int r = numbers.length -1;
        while(l < r){
            if(numbers[l] + numbers[r] == target){
                res[0] = numbers[l];
                res[1] = numbers[r];
                return res;
            }else if(numbers[l] + numbers[r] > target)
                r--;
            else l++;
        }
        return res;
    }


Related problem: 

* [1 Two Sum](#1-two-sum)
* [15 3Sum](#15-3sum)
* [16 3Sum Closest](#16-3sum-closest)
* [18 4Sum](#18-4sum)

<br>
<br>





###8 String to Integer atoi

>Implement atoi to convert a string to an integer.

>Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

>Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.


**Some rules**: 

1) if can not convert, return 0;

2) if value if out of range, return INT_MAX or INT_MIN

3) discard any whitespace until first non-whitespace character is found

4) takes as many characters as possible to form a valid character until encounter an unvalid

	public int atoi(String str){
		if(str == null || str.length() == 0) return 0;
		str = str.trim();
		boolean positive = true;
		int result = 0;
		for(int i = 0; i < str.length(); i++){
			if(i == 0 && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
				if(str.charAt(i) == '-') positive = false;
				continue;
			}
			if(!isNum(str.charAt(i))) break;
			if(result > (Integer.MAX_VALUE - ((int)str.charAt(i) - 48))/10) return positive ? Integer.MAX_VALUE: Integer.MIN_VALUE;
			result = result * 10 + ((int)str.charAt(i) - 48);
		}
		return positive ? result : -result;
	}
	
	public boolean isNum(char c){
		if(c < '0' || c > '9') return false;
		return true;
	}


<br>
<br>

###15 3Sum

>Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

>Note:

- Elements in a triplet (a,b,c) must be in **non-descending order**. (ie, a ≤ b ≤ c)

- The solution set **must not contain duplicate triplets**.
    
    
    
  		For example, given array S = {-1 0 1 2 -1 -4},
    	A solution set is:
    	(-1, 0, 1)
    	(-1, -1, 2)
    
**Idea**: We need to find the three numbers triplets that sum to 0. We can change to this problem to two subproblems. Target + (-target) = 0, num1 + num2 = - target.
Thus we can use the method in two sum to solve this problem. For each number num1 in the array, we find the other two numbers that sum to -num1. 

We first need to sort the array, then use two pointers to find the two sum numbers sum to -num1.

**Time** O(n ^ 2)

**Space** O(n ^ 2)

**Attention**: 

1) Silly check: num is null or num's length less than 3

2) *Duplication*: Both the threeSum and twoSum helper function need to check the duplication. If just need to check if the current is equal to the number before it. 

3) Non-descending order


    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(num == null || num.length < 3) return list;
        Arrays.sort(num);
        for(int i = 0; i <= num.length - 3; i++){
            if(i != 0 && num[i] == num[i-1]) continue;
            List<List<Integer>> current = twoSum(num, i+1, -num[i]);
            if(current.size() > 0){
                for(List<Integer> l : current ){
                    l.add(0, num[i]);
                }
                list.addAll(current);
            }
        }
        return list;
    }
    
    public List<List<Integer>> twoSum(int[] num, int start, int target){
        int r = num.length -1;
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int l = start;
        while(l < r){
            if(target == num[l] + num[r]){
                List<Integer> cur = new ArrayList<Integer>();
                cur.add(num[l]);
                cur.add(num[r]);
                list.add(cur);
                l++;
                r--;
                while(l < r && num[l] == num[l-1]) l++;
                while(l < r && num[r] == num[r+1]) r--;
            }else if(target < num[l] + num[r])
                r--;
            else l++;
        }
        return list;
    }
   


Related problem: 

* [1 Two Sum](#1-two-sum)
* [15 3Sum](#15-3sum)
* [16 3Sum Closest](#16-3sum-closest)
* [18 4Sum](#18-4sum)

<br>
<br>


###16 3Sum Closest


>Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. **You may assume that each input would have exactly one solution**.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
    
**Idea**: It's much similar with the method in three sum, the only difference here is that we calculate the minimum difference use the twosum helper funtion for each item in the array. 

**Attention**: 

1) The initial difference for both the threesum function and twosum function should be Integer.MAX_VALUE

2) Each time we compare the **abs** of the difference 

**Time** O(n ^ 2)

**Space** O(1)

    public int threeSumClosest(int[] num, int target) {
	    if(num == null || num.length == 0) return 0;
	    int res = Integer.MAX_VALUE;
	    Arrays.sort(num);
	    for(int i = 0; i <= num.length -3; i++){
	        if(i != 0 && num[i] == num[i-1]) continue;
	        int dif = twoSumClosest(num, i + 1, target-num[i]);
	        if(Math.abs(dif) < Math.abs(res)) res = dif;
	    }
	    return res+target;
	}
	
	public int twoSumClosest(int[] num, int start, int target){
	    int r = num.length - 1;
	    int l = start;
	    int res = Integer.MAX_VALUE;
	    while(l < r){
	        if(Math.abs(num[l] + num[r] - target) < Math.abs(res))
	            res = num[l] + num[r] - target;
	        if(num[l] + num[r] == target)
	            return 0;
	        else if(num[l] + num[r] > target)
	            r--;
	        else 
	            l++;
	        
	    }
	    return res;
	}
	

* [1 Two Sum](#1-two-sum)
* [15 3Sum](#15-3sum)
* [16 3Sum Closest](#16-3sum-closest)
* [18 4Sum](#18-4sum)

<br>
<br>


###18 4Sum

>Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

>Note:

- Elements in a quadruplet (a,b,c,d) must be in **non-descending order**. (ie, a ≤ b ≤ c ≤ d)

- The solution set must **not contain duplicate quadruplets**.
 
 
   		For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    	A solution set is:
   	 	(-1,  0, 0, 1)
    	(-2, -1, 1, 2)
    	(-2,  0, 0, 2)
    
<br>

**Idea**: Use the 3Sum to calculate 4Sum. 

**Time** O(n ^ 3)

**Space** O(n ^ 2)

 	public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(num == null || num.length <= 3) return list;
        Arrays.sort(num);
        for(int i = 0; i <= num.length - 4; i++){
            if(i != 0 && num[i] == num[i-1]) continue;
            List<List<Integer>> current = threeSum(num, i+1, target - num[i]);
            for(List<Integer> l : current){
                l.add(0, num[i]);
            }
            list.addAll(current);
        }
        return list;
    }
    
    public List<List<Integer>> threeSum(int[] num, int start, int target){
         List<List<Integer>> list = new ArrayList<List<Integer>>();
         for(int i = start; i <= num.length - 3; i++){
             if(i != start && num[i] == num[i-1]) continue;
             List<List<Integer>> current = twoSum(num, i+1, target - num[i]);
              for(List<Integer> l : current){
                l.add(0, num[i]);
            }
            list.addAll(current);
         }
         return list;
    }
    
    public List<List<Integer>> twoSum(int[] num, int start, int target){
         List<List<Integer>> list = new ArrayList<List<Integer>>();
         int r = num.length -1;
         int l = start;
         while(l < r){
             if(num[l] + num[r] == target){
                 List<Integer> cur = new ArrayList<Integer>();
                 cur.add(num[l]);
                 cur.add(num[r]);
                 list.add(cur);
                 l++;
                 r--;
                 while(l < r && num[l] == num[l-1]) l++;
                 while(l < r && num[r] == num[r+1]) r--;
             }else if(num[l] + num[r] < target) l++;
             else r--;
         }
         return list;
    }
 
 
 
 **Other Idea**: 
 
 we can twoSum combine twoSum to calculate 4Sum. Time complexity would be O(n^2 * lgn)
 

    

* [1 Two Sum](#1-two-sum)
* [15 3Sum](#15-3sum)
* [16 3Sum Closest](#16-3sum-closest)
* [18 4Sum](#18-4sum)

<br>
<br>


###29 Divide Two Integers

>Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.


**Idea**: Each integer can be represent as binary format, so n = (0 or 1) * 2 ^ n +(0 or 1) * 2 ^ (n-1) + ......

We can respresent the result = (0 or 1) * 2 ^ n +(0 or 1) * 2 ^ (n-1) + ......

Thus [(0 or 1) * 2 ^ n +(0 or 1) * 2 ^ (n-1) + ......)] * divisor = dividend

Thus, we can calculate (0 or 1) * 2 ^ i, i from n to 0, and combine them together.


**Time complexity** : O(lgn)

**Space**: O(1)

**Attention**:

- abs (Integer.MIN_VALUE) = Integer.MIN_VALUE
- when calculate n, eg:  while(divisor <= (dividend >> 1)) we can not change to while((divisor<<1) <= dividend). It might overflow and keep loop forever.
- Take care of some corner case, eg: dividend = Integer.MIN_VALUE or divisor = Integer.MIN_VALUE


<br>



	public int divide( int dividend, int divisor){
	       if(divisor == 0) return Integer.MAX_VALUE;
	       int result = 0;
	       boolean positive = ((dividend ^ divisor) >>> 31) == 0;
	       if(divisor == Integer.MIN_VALUE){
	           if(dividend == Integer.MIN_VALUE) return 1;
	           else return 0;
	       }
	       if(dividend == Integer.MIN_VALUE){
	           if(divisor == 1) return Integer.MIN_VALUE;
	           else if(divisor == -1) return Integer.MAX_VALUE;
	           dividend += Math.abs(divisor);
	           result += 1;
	       }
	       
	       dividend = Math.abs(dividend);
	       divisor = Math.abs(divisor);
	       
	       int count = 0;
	       while(divisor <= (dividend >> 1)){
	           count++;
	           divisor <<= 1;
	       }
	       
	       while(count >= 0){
	           if(dividend >= divisor){
	               result += 1 << count;
	               dividend -= divisor;
	           }
	           divisor >>= 1;
	           count --;
	       }
	       return positive ? result : -result;
	    }








* [29 Divide Two Integers](#29-divide-two-integers)
* [50 Pow(x,n)](#50-pow(x,n))
* [69 Sqrt(x)](#69-sqrt(x))
* [166 Fraction to Recurring Decimal](#166-fraction-to-recurring-decimal)

<br>

<br>


###50 Pow(x,n)

>Implement pow(x, n).

**Idea**: Dichotomy and calculate recursively. 


	public double pow(double x, int n){
	    if (n == 0) return 1;
	    double result = pow(x, n/2);
	    if(n % 2 == 0) return result * result;
	    else if(n % 2 == 1) return result * result * x;
	    else return result * result / x;
	}



* [29 Divide Two Integers](#29-divide-two-integers)
* [50 Pow(x,n)](#50-pow(x,n))
* [69 Sqrt(x)](#69-sqrt(x))
* [166 Fraction to Recurring Decimal](#166-fraction-to-recurring-decimal)

<br>

<br>



###69 Sqrt(x)

>Implement int sqrt(int x).

>Compute and return the square root of x.


**Idea**: The result = x/result, so each time we can give a better guess result = (result + x/result)/2 until we get the correct answer.


	public int sqrt(int x){
		if(x < 1) return 0;
		double result = 1;
		while(Math.abs(x/result-result) > 0.000000001){
			result = (x/result + result)/2;
		}
		return (int)result;
	}
	
**Other Idea**: Dichotomy. l = smallest possible result, r the largest possible result. mid = (l + r)/2 , check the relationship between mid and result

**Attention**: mid <= x/mid && (mid + 1) > x/(mid+1) can not change to mid * mid <= x && (mid+1) * (mid+1) > x. Because, when **mid * mid overflows**, the result might change, also, it might lead to Time limit exceeded.


    public int sqrt(int x){
        if(x < 1) return 0;
        if(x == 1) return 1;
        int l = 1; 
        int r = x/2 + 1;
    	while(l <= r ){
    	    int mid = (l+r)/2;
    	    if(mid <= x/mid && (mid + 1) > x/(mid+1)) return mid;
    	    else if(mid > x/mid) r = mid - 1;
    	    else l = mid + 1;
    	}
    	return 0;
    }

	
 



* [29 Divide Two Integers](#29-divide-two-integers)
* [50 Pow(x,n)](#50-pow(x,n))
* [69 Sqrt(x)](#69-sqrt(x))
* [166 Fraction to Recurring Decimal](#166-fraction-to-recurring-decimal)

<br>

<br>


###89 Gray Code
>The gray code is a binary numeral system where two successive values differ in only one bit.

>Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

>For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

>00 - 0

>01 - 1

>11 - 3

>10 - 2

>Note:
>For a given n, a gray code sequence is not uniquely defined.

>For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

>For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

Let's first see a few example of gray code and find the laws to create gray code

	n = 1: 0 
		   1
	
	n = 2: 00 
		   01
		   11
		   10

	n = 3: 000
	       001
	       011
	       010
	       110
	       111
	       101
	       100
	       
	 n = 4: 0000
	 	    0001
	 	    0011
	 	    0010
	 	    0110
	 	    0111
	 	    0101
	 	    0100
	 	    1100
	 	    1101
	 	    1111
	 	    1110
	 	    1010
	 	    1011
	 	    1001
	 	    1000
		   
**Some laws from above** : we can find that the first half of  2 ^ n numbers could be create by add a 0 to 2 ^ (n-1) numbers. For the last half of 2 ^ n, we can add 1 to the reversed order number of 2 ^ (n-1). 

For example, based on 2 bit gray code, we can get the first half of 3 bit gray code by adding 0, eg: 000 001 011 010, for the last half, we first reverse the order 10, 11, 01, 00, then add 1 to each of them, we can get 110 111 101 100.

**Time Complexity**: we have 2 ^ n gray code numbers, thus the time complexity is 2 ^ n
**Space**: 2 ^ n

	    public List<Integer> grayCode(int n) {
        	List<Integer> list = new ArrayList<Integer>();
        	if(n < 0) return list;
        	list.add(0);
        	if(n == 0) return list;
        	list.add(1);
        	for(int i = 2; i <= n; i++){
            	for(int j = list.size()-1; j >= 0; j--){
                	list.add(list.get(j) + (1 <<(i-1)));
            	}
        	}
        	return list;
    	 }


**Related Questions** : check if two bytes can be put successively in a gray code sequence.

**Idea**: we know that the neighbor gray code numbers is different with 1 bit. We need to check if these two numbers have only one bit diffenence.


	public boolean isSuccesive(byte b1, byte b2){
		int count = 0;
		while(b1 > 0 || b2 > 0){
			if((b1 & 1) != (b2 & 1))
				count++;
			b1 >>>= 1;
			b2 >>>= 1;
		}
		return count == 1;
	}
	
If the numbers are unsigned numbers, we can first ^ then check if it is the power of 2
	
	temp = b1 ^ b2;
	
	return (temp > 0) && ((temp & -temp) == temp); 
	
	return (temp > 0) && ((temp & (temp -1)) == 0);
	
	
<br>
<br>
	

###121 Best Time to Buy and Sell Stock
>Say you have an array for which the ith element is the price of a given stock on day i.

> If you were only permitted to complete **at most one transaction** (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

**Idea:** We are allowed at most one transaction. Thus we only need to maintain a local  min stock price before prices[i]. Traverse prices one pass to get the max profit.

**max = Math.max(max, prices[i] - min)** 

**min = Math.min(min, prices[i])**

**Time complexity**: O(n)
 
**Space** : O(1)


	public int maxProfit(int[] prices){
		if(prices == null || prices.length == 0) return 0;
		int max = 0;
		int min = prices[0];
		for(int i = 1; i < prices.length; i++){
			max = Math.max(max, prices[i] - min);
			min = Math.min(min, prices[i]);
		}
		return max;
	}

Related problem: 

* [121 Best Time to Buy and Sell Stock](#121-best-time-to-buy-and-sell-stock)
* [122 Best Time to Buy and Sell Stock II](#122-best-time-to-buy-and-sell-stock-ii)
* [123 Best Time to Buy and Sell Stock III](#123-best-time-to-buy-and-sell-stock-iii)
* [188 Best Time to Buy and Sell Stock IV](#188-best-time-to-buy-and-sell-stock-iv)
	
<br>
<br>

###122 Best Time to Buy and Sell Stock II

>Say you have an array for which the ith element is the price of a given stock on day i.

>Design an algorithm to find the maximum profit. You may complete **as many transactions** as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

**Idea:** We are allowed as many transactions. Thus we only need to each neighbor pair, if prices[i] > prices[i-1], we add the difference to our result. We can get the max one pass. 

**dif = Math.max(0, prices[i] - prices[i-1])** 

**max = max + dif** 

**Time complexity**: O(n) 

**Space** : O(1)

	public int maxProfit(int[] prices){
		int max = 0;
		int dif = 0;
		for(int i = 1; i < prices.length; i++){
			dif = Math.max(0, prices[i] - prices[i-1]);
			max = max + dif;
		}
		return max;
	}
	
Related problem: 

* [121 Best Time to Buy and Sell Stock](#121-best-time-to-buy-and-sell-stock)
* [122 Best Time to Buy and Sell Stock II](#122-best-time-to-buy-and-sell-stock-ii)
* [123 Best Time to Buy and Sell Stock III](#123-best-time-to-buy-and-sell-stock-iii)
* [188 Best Time to Buy and Sell Stock IV](#188-best-time-to-buy-and-sell-stock-iv)

<br>
<br>

###123 Best Time to Buy and Sell Stock III

>Say you have an array for which the ith element is the price of a given stock on day i. 
> 
>Design an algorithm to find the maximum profit. You may complete **at most two transactions**.

>Note:
>You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

**Idea:** We are only allowed  at most two transactions. Thus, we can use two arrays to record the max profit before profits[i](including i), denote as l[i], and max profits after profits[i](including i), denote as r[i]. Then find the max of l[i] + r[i]. 

**Time complexity**: O(n) 

**Space** : O(n)

	public int maxProfit(int[] prices){
		if(prices == null || prices.length <= 1) return 0;
		int[] l = new int[prices.length];
		int[] r = new int[prices.length];
		int max = 0;
		int min = prices[0];
		for(int i = 1; i < prices.length; i++){
			max = Math.max(max, prices[i] - min);
			min = Math.min(min, prices[i]);
			l[i] = max;
		}
		int maxR = prices[prices.length-1];
		max = 0;
		for(int i = prices.length - 2; i >= 0; i--){
			max = Math.max(max, maxR - prices[i]);
			maxR = Math.max(maxR, prices[i]);
			r[i] = max;
		}
		max = 0;
		for(int i = 0; i < prices.length; i++){
			max = Math.max(max, l[i] + r[i]);
		}
		return max;
	}
	
**Another solution:** 

global[i][j]: denotes max profit, at most j transactions before day i: 

**global[i][j]=max(local[i][j],global[i-1][j])**

local[i][j]: denotes max profit, at most j transactions before day i, and last transaction is saled on day i: 

**local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)**

From above, we know that we can change the two dimensional array to one dimensional to save space. Because we only use two rows in the two dimensional array. 

**Time complexity**: O(n) 

**Space** : O(1) (O(k), but k is 2 in this problem)

	public int maxProfit1(int[] prices){
		return maxProfit(prices, 2);
	}
	public int maxProfit(int[] prices, int k){
		if(prices == null || prices.length <= 1) return 0;
		int[] global = new int[k+1];
		int[] local = new int[k+1];
		for(int i = 1; i < prices.length; i++){
			int dif = prices[i] - prices[i-1];
			for(int j = k; j >= 1; j--){
				local[j] = Math.max(global[j-1] + Math.max(dif, 0), local[j] + dif);
				global[j] = Math.max(local[j], global[j]);
			}
		}
		return global[k];
	}

* [121 Best Time to Buy and Sell Stock](#121-best-time-to-buy-and-sell-stock)
* [122 Best Time to Buy and Sell Stock II](#122-best-time-to-buy-and-sell-stock-ii)
* [123 Best Time to Buy and Sell Stock III](#123-best-time-to-buy-and-sell-stock-iii)
* [188 Best Time to Buy and Sell Stock IV](#188-best-time-to-buy-and-sell-stock-iv)

<br>
<br>



### 156 Binary Tree Upside Down

> Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

<pre>
For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5

return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  				   
   
</pre>

**Idea:** The structure of the tree: 

* 1) right child doesn't have children
* 2) If right child exist, left child must exist. 

Structure of result tree:

* 1) Right child turn to left child: p.left = parent.right
* 2) Parant becomes right child: p.right = parent 
* 3) Left child becomes root. 

		public TreeNode upsideDownBinaryTree(TreeNode root){
			TreeNode parent = null, rightChild = null, leftChild = null;
			while(root != null){
				leftChild = root.left;
				root.left = rightChild; //  Right child turn to left child
				rightChild = root.right;
				root.right = parent;   //Parant becomes right child
				parent = root;
				root = leftChild;  //Left child becomes root
			}
			return parent;	
		}	


<br>
<br>


### 157 Read N Characters Given Read4 

> The API: int read4(char *buf) reads 4 characters at a time from a file.
> 
> The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

> By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
> 
> Note:The read function will only be called once for each test case.

**Idea:**Iterative call read4 before end of file or each n, store and copy it to the destination buf. 

**Ambiguity:**What if n exceeds the buf's length? 

	/* read 4 characters each time, return the exact number of characters read. */
	int read(char[] buf);
	public int read(char[] buf, int n){
		char[] buffer = new char[4];
		int count = 0;
		int current = 0;
		int needAdd = 0;
		while(true){
			current = read(buffer);
			if(current == 0 || n == count) return count;
			needAdd = Math.min(n-count, current);
			for(int i = 0; i < needAdd; i++){
				buf[count++] =  buffer[i];
			}
		}
	}
	
Related problem:

* [157 Read N Characters Given Read4](#157-read-n-characters-given-read4)
* [158 Read N Characters Given Read4 II - Call multiple times](#158-read-n-characers-given-read4-ii-call-multiple-times) 

<br>
<br>

	

### 158 Read N Characters Given Read4 II - Call multiple times. 

> The API: int read4(char *buf) reads 4 characters at a time from a file.

> The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

> By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

> Note:
> The read function may be called multiple times.


**Idea:** Because the read function maybe called multiple times, so at the next call, there might be some data remain in the read4 buffer due to the up amount data of n at each readN bytes call. So we need to keep track of the state of read 4 buffer. If there remains data at read4 buffer at the call, we need start from that position instead of call read4 function. 


	/* read 4 characters each time, return the exact number of characters read. */
	int read(char[] buf);
	int read4Ptr = 0;
	int read4Count = 0;
	char[] buffer = new char[4];
	public int read(char[] buf, int n){	
		int count = 0;
		while(true){
			if(read4Ptr == 0){
				read4Count = read(buffer);
			}
			if(read4Count == 0 || n == count) return count;
			while(count < n && read4Ptr < read4Count){
				buf[count++] =  buffer[read4Ptr++];
			}
			read4Ptr = read4Ptr%read4Count;
			
		}
	}
	
Related problem:

* [157 Read N Characters Given Read4](#157-read-n-characters-given-read4)
* [158 Read N Characters Given Read4 II - Call multiple times](#158-read-n-characers-given-read4-ii-call-multiple-times) 

<br>
<br>


### 159 Longest String with At Most Two Distinct Characters
> Given a string, find longest substring T that contains at most 2 distinct characters.For example, Given s = “eceba”,T is "ece" which its length is 3.

**Idea**: Using a slide window keep track of the substring with at most 2 distinct characters. Each time we encounter the third distinct character, we first calculate the length of the substring, then we move the start position of our slide window to the position that contains only the third character, the first character or the third character, the second character. 

**Example:** Assume the string is accacccbac....., when we encounter "b" at index 7, we calculate the length of substring accaccc and do related operations, then we move the start position of slide window to index 4, then keep going... 

**Solution && time complexity:**	Time complexity: O(n), map contains only two elements. Here, we can use two variables to replace the hashmap, if we extend the problem to k characters, then hashmap maybe more convenient 


	public String subString(String s){
		int start = 0; // start of longest substring
		int end = 0;   // end of longest substring
		int j = 0;     // start of slide window
		int max = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++){
			//check: contains <= 2 characters && substring ends at the end 
			if(i == s.length()-1 && map.size() <= 2 && map.containsKey(s.charAt(i))){
				if(end == 0) return s;
				else{
					if(max < s.length() - j){
						start = j;
						end = s.length()-1;	
					}
				}
			}
			if(map.size() == 2 && !map.containsKey(s.charAt(i))){
				if(max < i - j){
					max = i - j ;
					start = j;
					end = i-1;
				}
				j = s.length();
				char needToRemove = ' ';
				for(char c : map.keySet()){
					if(j > map.get(c)){
						j = map.get(c);
						needToRemove = c;
					}
				}
				j++;
				map.remove(needToRemove);
			}
				map.put(s.charAt(i), i);
		}
		return s.substring(start, end+1);
	}
	
	
**Extention:** Find the longest substring contains at most k unique elements. Idea is much similar with k = 2. When we encounter the k+1 character, we need to calculate the length of string do related operations and move slide window, let it contains only k-1 different characters, add the k+1 character, and keep going.  

**Time Complexity:** O(k * n)


	public String subString(String s, int k){
		int start = 0; // start of longest substring
		int end = 0;   // end of longest substring
		int j = 0;     // start of slide window
		int max = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++){
			//check: contains <= 2 characters && substring ends at the end 
			if(i == s.length()-1 && map.size() <= k && map.containsKey(s.charAt(i))){
				if(end == 0) return s;
				else{
					if(max < s.length() - j){
						start = j;
						end = s.length()-1;	
					}
				}
			}
			if(map.size() == k && !map.containsKey(s.charAt(i))){
				if(max < i - j){
					max = i - j ;
					start = j;
					end = i-1;
				}
				j = s.length();
				char needToRemove = ' ';
				for(char c : map.keySet()){
					if(j > map.get(c)){
						j = map.get(c);
						needToRemove = c;
					}
				}
				j++;
				map.remove(needToRemove);
			}
				map.put(s.charAt(i), i);
		}
		return s.substring(start, end+1);
	}

<br>

<br>


###166 Fraction to Recurring Decimal

>Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
	
>If the fractional part is repeating, enclose the repeating part in parentheses.

	For example,

	Given numerator = 1, denominator = 2, return "0.5".
	Given numerator = 2, denominator = 1, return "2".
	Given numerator = 2, denominator = 3, return "0.(6)".

<br>

**Idea**: Just do the divide like what we normally do in math. 

For example, 3/7 

- quotient = 0, remainer = 3 , string = 0.
- **3 * 10/7** quotient = 4, remainder = 2; string = 0.4
- 2 * 10/ 7 quotient = 2, remainder = 6; string = 0.42
- 6 * 10/7 quotient = 8, remainder = 4; string = 0.428
- 4 * 10 /7 quotient = 5, remainder = 5; string = 0.4285
- 5 * 10/7 quotient = 7, remainder = 1, string = 0.42857
- 1 * 10/7 quotient = 1, remainder = 3, string = 0.428571
- **3 * 10/7**

Thus, we know the result is 0.(428571)

In order to know when the recuisive begins, we need to record the remainder at each iteration.

**Attention**

- 1)We need to do abs for both numbers, otherwise, there might be unnecessary "-" in the result string
- 2)abs(Integer.MIN_VALUE)
- 3)use hashmap to record the position of each remainder.


<br>

    public String fractionToDecimal(int numerator, int denominator) {
        if(denominator == 0 || numerator == 0) return "0";
        StringBuilder res = new StringBuilder();
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        boolean positive = ((numerator ^ denominator) >>> 31) == 0;
        if(!positive) res.append("-");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        res.append(num/den);
        if(num % den == 0) return res.toString();
        res.append(".");
        long mod = num % den;
        while(mod != 0){
            if(map.containsKey(mod)){
                res.insert(map.get(mod), "(");
                res.append(")");
                return res.toString();
            }
            map.put(mod, res.length());
            mod = mod * 10;
            long divide = mod/den;
            mod = mod % den;
            res.append(divide);
        }
        return res.toString();
    }


* [29 Divide Two Integers](#29-divide-two-integers)
* [50 Pow(x,n)](#50-pow(x,n))
* [69 Sqrt(x)](#69-sqrt(x))
* [166 Fraction to Recurring Decimal](#166-fraction-to-recurring-decimal)

<br>

<br>


	
### 188 Best Time to Buy and Sell Stock IV

> Say you have an array for which the ith element is the price of a given stock on day i.

>Design an algorithm to find the maximum profit. You may complete **at most k transactions**.

>Note:

>You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

**First try:** Use the idea in [Best time to buy and sale stock iii](#123-best-time-to-buy-and-sell-stock-iii).
global[i][j]: denotes max profit, at most j transactions before day i:

 **global[i][j]=max(local[i][j],global[i-1][j])**

local[i][j]: denotes max profit, at most j transactions before day i, and last transaction is saled on day i: 

**local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)**

**Time complexity**: O(k * n)

**Space** : O(k)

Looks good, right? But we'll get out of memory error. Because in one test case, k = 100000. 

	public int maxProfit(int k, int[] prices){
		if(prices == null || prices.length <= 1) return 0;
		int[] global = new int[k+1];
		int[] local = new int[k+1];
		for(int i = 1; i < prices.length; i++){
			int dif = prices[i] - prices[i-1];
			for(int j = k; j >= 1; j--){
				local[j] = Math.max(global[j-1] + Math.max(dif, 0), local[j] + dif);
				global[j] = Math.max(local[j], global[j]);
			}
		}
		return global[k];
	}

**Solution:**


* [121 Best Time to Buy and Sell Stock](#121-best-time-to-buy-and-sell-stock)
* [122 Best Time to Buy and Sell Stock II](#122-best-time-to-buy-and-sell-stock-ii)
* [123 Best Time to Buy and Sell Stock III](#123-best-time-to-buy-and-sell-stock-iii)
* [188 Best Time to Buy and Sell Stock IV](#188-best-time-to-buy-and-sell-stock-iv)

<br>
<br>



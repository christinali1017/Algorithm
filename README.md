
# leetcode
## Overview
* [121 Best Time to Buy and Sell Stock](#121-best-time-to-buy-and-sell-stock)
* [122 Best Time to Buy and Sell Stock II](#121-best-time-to-buy-and-sell-stock-ii)
* [123 Best Time to Buy and Sell Stock III](#121-best-time-to-buy-and-sell-stock-iii)
* [156 Binary Tree Upside Down](#156-binary-tree-upside-down)
* [157 Read N Characters Given Read4](#157-read-n-characters-given-read4)
* [158 Read N Characters Given Read4 II - Call multiple times](#158-read-n-characers-given-read4-ii-call-multiple-times) 
* [159 Longest String with At Most Two Distinct Characters](#159-longest-string-with-at-most-two-distinct-characters)
* [188 Best Time to Buy and Sell Stock IV](#188-best-time-to-buy-and-sell-stock-iv)

###121 Best Time to Buy and Sell Stock
>Say you have an array for which the ith element is the price of a given stock on day i.

> If you were only permitted to complete **at most one transaction** (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

**Idea:** We are allowed at most one transaction. Thus we only need to maintain a local  min stock price before prices[i]. Traverse prices one pass to get the max profit use **max = Math.max(max, prices[i] - min)** and **min = Math.min(min, prices[i])**

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


	
###122 Best Time to Buy and Sell Stock II

>Say you have an array for which the ith element is the price of a given stock on day i.

>Design an algorithm to find the maximum profit. You may complete **as many transactions** as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

**Idea:** We are allowed as many transactions. Thus we only need to each neighbor pair, if prices[i] > prices[i-1], we add the difference to our result. We can get the max one pass. **dif = Math.max(0, prices[i] - prices[i-1])** and **max = max + dif** 

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

global[i][j]: denotes max profit, at most j transactions before day i: **global[i][j]=max(local[i][j],global[i-1][j])**

local[i][j]: denotes max profit, at most j transactions before day i, and last transaction is saled on day i: **local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)**

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
	
### 188 Best Time to Buy and Sell Stock IV

> Say you have an array for which the ith element is the price of a given stock on day i.

>Design an algorithm to find the maximum profit. You may complete **at most k transactions**.

>Note:

>You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

**First try:** Use the idea in [Best time to buy and sale stock iii](#123-best-time-to-buy-and-sale-stock-iii).
global[i][j]: denotes max profit, at most j transactions before day i: **global[i][j]=max(local[i][j],global[i-1][j])**

local[i][j]: denotes max profit, at most j transactions before day i, and last transaction is saled on day i: **local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)**

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





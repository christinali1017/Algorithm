

# leetcode
## Overview
* [1 Two Sum](#1-two-sum)
* [8 String to Integer atoi](#8-string-to-integer-atoi)
* [15 3Sum](#15-3sum)
* [16 3Sum Closest](#16-3sum-closest)
* [18 4Sum](#18-4sum)
* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [29 Divide Two Integers](#29-divide-two-integers)
* [50 Pow(x,n)](#50-pow(x,n))
* [61 Rotate List](#61-rotate-list)
* [69 Sqrt(x)](#69-sqrt(x))
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [86 Partition List](#86-partition-list)
* [89 Gray Code](#89-gray-code)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [118 Pascal Triangle](#118-pascal-triangle)
* [119 Pascal Triangle II](#119-pascal-triangle-ii)
* [120 Triangle](#120-triangle)
* [121 Best Time to Buy and Sell Stock](#121-best-time-to-buy-and-sell-stock)
* [122 Best Time to Buy and Sell Stock II](#122-best-time-to-buy-and-sell-stock-ii)
* [123 Best Time to Buy and Sell Stock III](#123-best-time-to-buy-and-sell-stock-iii)
* [126 Word Ladder](#126-word-ladder)
* [127 word Ladder II](#127-word-ladder-ii)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [156 Binary Tree Upside Down](#156-binary-tree-upside-down)
* [157 Read N Characters Given Read4](#157-read-n-characters-given-read4)
* [158 Read N Characters Given Read4 II - Call multiple times](#158-read-n-characters-given-read4-ii-call-multiple-times) 
* [159 Longest String with At Most Two Distinct Characters](#159-longest-string-with-at-most-two-distinct-characters)
* [160 Intersection of Two Linked Lists](160-intersection-of-two-linked-lists)
* [166 Fraction to Recurring Decimal](#166-fraction-to-recurring-decimal)
* [167 Two Sum II Input array is sorted](#167-two-sum-ii-input-array-is-sorted)
* [170 Two Sum III Data Structure Design](#170-two-sum-iii-data-structure-design)
* [188 Best Time to Buy and Sell Stock IV](#188-best-time-to-buy-and-sell-stock-iv)
* [189 Rotate Array](#189-rotate-array)


<br>

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
* [167 Two Sum II Input array is sorted](#167-two-sum-ii-input-array-is-sorted)
* [170 Two Sum III Data Structure Design](#170-two-sum-iii-data-structure-design)

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
* [167 Two Sum II Input array is sorted](#167-two-sum-ii-input-array-is-sorted)
* [170 Two Sum III Data Structure Design](#170-two-sum-iii-data-structure-design)

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
* [167 Two Sum II Input array is sorted](#167-two-sum-ii-input-array-is-sorted)
* [170 Two Sum III Data Structure Design](#170-two-sum-iii-data-structure-design)

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
* [167 Two Sum II Input array is sorted](#167-two-sum-ii-input-array-is-sorted)
* [170 Two Sum III Data Structure Design](#170-two-sum-iii-data-structure-design)

<br>
<br>



###19 Remove Nth Node From End of List
>Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.
Try to do this in one pass.

**Idea**: Use two pointers, p1, p2. First move p1 k steps, then move p1 and p2 together until p1 change to null. At this time, p2 points to the node we need to delete. 

**Attention**: 

- 1) if after k steps, p1 is null, then return head.next
- 2) record pre

<br>

    public ListNode removeNthFromEnd(ListNode head, int n) {
    	if(head == null) return null;
    	ListNode l1 = head;
    	ListNode l2 = head;
    	while(n > 0){
    	    l1 = l1.next;
    	    n--;
    	}
    	if(l1 == null) return head.next;
    	ListNode pre = null;
    	while(l1 != null){
    	    pre = l2;
    	    l2 = l2.next;
    	    l1 = l1.next;
    	}
    	pre.next = l2.next;
    	return head;
    }


***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>


###21 Merge Two Sorted Lists

> Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

**Attention**:

- 1) Use a fake head, thus we don't need to check if head is null

<br>

<br>

       public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    if(l1 == null) return l2;
	    if(l2 == null) return l1;
	    ListNode fakeHead = new ListNode(-1);
	    ListNode temp = fakeHead;
	    while(l1 != null && l2 != null){
	        if(l1.val > l2.val){
	            temp.next = l2;
	            l2 = l2.next;
	        }else{
	            temp.next = l1;
	            l1 = l1.next;
	        }
	        temp = temp.next;
	    }
	    if(l1 != null) temp.next = l1;
	    if(l2 != null) temp.next = l2;
	    return fakeHead.next;
    }  


***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>


###23 Merge k Sorted Lists

> Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

**Idea**:

1) Solution1:

Use priorityqueue, add the head of k lists two queue, each time poll the smallest element from the queue then add the smallest.next to the queue until queue is empty. We need to to give another another parameter to priorityqueue when initialize.
<pre> 
	PriorityQueue queue = new PriorityQueue(size, new Comparator<ListNode>(){
		public int compare(ListNode arg1, ListNode arg2){
			return arg1 - arg2;
		}
	});
</pre>

**Time complexity: nklgk** (lgk for insert element, nk elements).

**Space** O(k)

2) Solution2:

Use the idea in merge sort and use the method in [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists) to merge two elements.

**Time**: O(knlgk)

**Solution1 code**:

<br>

		public static ListNode mergeKLists(List<ListNode> lists){
        if(lists == null || lists.size() == 0) return null;
        ListNode res = new ListNode(-1);
        ListNode h1 = res;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2){
                return l1.val - l2.val;
            }
        });
        for(ListNode l : lists){
            if(l != null) queue.add(l);
        }
        while(!queue.isEmpty()){
            ListNode current = queue.poll();
            h1.next = current;
            h1 = h1.next;
            if(current.next != null)
                queue.offer(current.next);
        }
        return res.next;
	}

**Solution 2 code**

	public  ListNode mergeKLists1(List<ListNode> lists){
		  if(lists == null || lists.size() == 0) return null;
		  return helper(lists, 0, lists.size()-1);
		}
		public ListNode helper(List<ListNode> lists, int start, int end){
		    if(start >= end) return lists.get(start);
		    int mid = (start + end)/2;
		    return mergeTwoLists(helper(lists, start, mid), helper(lists, mid+1, end));
		}
		
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    if(l1 == null) return l2;
	    if(l2 == null) return l1;
	    ListNode fakeHead = new ListNode(-1);
	    ListNode temp = fakeHead;
	    while(l1 != null && l2 != null){
	        if(l1.val > l2.val){
	            temp.next = l2;
	            l2 = l2.next;
	        }else{
	            temp.next = l1;
	            l1 = l1.next;
	        }
	        temp = temp.next;
	    }
	    if(l1 != null) temp.next = l1;
	    if(l2 != null) temp.next = l2;
	    return fakeHead.next;
    }


**Brute force** time limit exceeded

method1: each time find the minimum from the k nodes, and add it to the result list. Time k * n * k = O(k^2*n)

	 /* Brute force: time limit exceeded*/
		public  ListNode mergeKLists2(List<ListNode> lists){
		    if(lists == null || lists.size() == 0) return null;
		    ListNode head = new ListNode(-1);
		    ListNode temp = head;
		    List<ListNode> l = new ArrayList<ListNode>();
		    for(int i = 0; i < lists.size(); i++){
		        if(lists.get(i) != null) l.add(lists.get(i));
		    }
		    while(l.size() > 0){
		    	int min = 0;
		        for(int i = 0; i < l.size(); i++){
		            if(l.get(i) == null) l.remove(i);
		            else {
		            	if(l.get(i).val < l.get(min).val) min = i;
		            }
		        }
		        if(l.size() > 0){
		        	temp.next = l.get(min);
		        	if(l.get(min).next != null) l.set(min,l.get(min).next);
		        	else l.remove(min);
		        	temp = temp.next;
		        }
		    }
		    return head.next;
		}
	

method2: each time merge two lists, until all lists are merged.

	/*Brute force, use merge2 method */
	public  ListNode mergeKLists3(List<ListNode> lists) {
		if( lists == null || lists.size() == 0) return null;
		ListNode result = lists.get(0);
		for(int i = 1; i < lists.size(); i++){
			ListNode currentList = lists.get(i);
			result = mergeTwoLists(result, currentList);

		}
		
		return result;
	}



***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

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


###61 Rotate List

> Given a list, rotate the list to the right by k places, where k is non-negative.

<pre>
For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
</pre>

**Idea**: First calculate the length of list and record the tail, then move len - n steps. Connect tail and head, disconnect the (len-n)th and (len-n+1)th node.

**Attention**: 

- 1) we need to mode n = n%len
- 2) Take care of null pointer


code:

	    public ListNode rotateRight(ListNode head, int n) {
        if(n < 0 || head == null) return head;
        ListNode temp = head;
        int len = 0;
        ListNode tail = null;
        while(temp != null){
            len++;
            tail = temp;
            temp = temp.next;
        }
        n = n % len;
        len = len - n;
        if(n == 0 || len == 0) return head;
        temp = head;
        ListNode pre = null;
        while(len > 0){
            pre = temp;
            temp = temp.next;
            len--;
        }
        pre.next = null;
        tail.next = head;
        return temp;
    }


***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

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


###82 Remove Duplicates from Sorted List

>Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.


	 public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode pre = head;
        ListNode temp = head.next;
        while(temp != null){
            if(temp.val == pre.val) {
                pre.next = temp.next;
                temp = temp.next;
            }else{
                pre = temp;
                temp = temp.next;
            }
        }
        return head;
    }
    
***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>

###83 Remove Duplicates from Sorted List II

>Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

<pre>
For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
</pre>

**Idea**:

1) Compare each node with it's next, count++ if duplicate is found. if head.val != head.next.val, then check if count == 1. If yes, add node, else reset head to 1 and  continue.


**Attention**: Remember to check count at the end. If count == 1, add the last node. else set result's tail.next = null. 

	 public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode feakHead = new ListNode(-1);
        ListNode h1 = feakHead;
        int count = 1;
        while(head != null && head.next != null){
            if(head.val == head.next.val){
                head = head.next;
                count++;
            }else{
                if(count == 1){
                    h1.next = head;
                    head = head.next;
                    h1 = h1.next;
                }else{
                    head = head.next;
                    count = 1;
                }
            }
        }
        if(count == 1) h1.next = head;
        else h1.next = null;
        return feakHead.next;
    }

***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>

###86 Partition List

>Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should **preserve the original relative order** of the nodes in each of the two partitions.

<pre>
For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
</pre>

**Idea**: We need to preserve the original order, thus we can maintain two pointers, h1 and tail. h1 is the place we need to insert the element which smaller than x. tail is the end of the result list from which we add the elements equal or larger than x. 

**Attention**: We need to 

	  public ListNode partition(ListNode head, int x) {
        if(head == null) return null;
        ListNode feakHead = new ListNode(-1);
        ListNode h = feakHead;
        ListNode tail = feakHead;
        while(head != null){
            if(head.val >= x){
                tail.next = head;
                head = head.next;
                tail = tail.next;
                tail.next = null;
            }else{
                 ListNode saveH = h;
                 ListNode temp = h.next;
                 h.next = head;
                 head = head.next;
                 h = h.next;
                 h.next = temp;
                if(tail.equals(saveH)) tail = h;
            }
        }
        return feakHead.next;
    }


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

###92 Reverse Linked List II

>Reverse a linked list from position m to n. Do it in-place and in one-pass.

<pre>

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
	
</pre>

**Idea**: 1) Find begining node of reverse. 2) reverse m-n 3) concatenate 

**Attention**:

- 1) when concatenate, take care of null pointer. ***(When use node.next, always check if node is null)***
- 2) If m == 1, what should we return? So check the previous node of the reverse list's head, if it's null, just return the reverse head. 
- 3) Take care of the steps. We need to move ***m-1*** steps to find the beginning of the reverse. We need take another ***n-m+1*** steps to reverse the nodes. 

	  public ListNode reverseBetween1(ListNode head, int m, int n) {
        if(head == null || head.next == null || (m == n)) return head;
        ListNode saveHead = head;
        ListNode preReverse = null;
        while(m-1 > 0){
            preReverse = head;
            head = head.next;
            m--;
            n--;
        }
        ListNode reverseHead = null;
        ListNode reverseTail = null;
        ListNode next = null;
        while(n-1 >= 0){
            n--;
            if(reverseHead == null){
                reverseHead = head;
                reverseTail = head;
                head = head.next;
                continue;
            }
            next = head.next;
            head.next = reverseHead;
            reverseHead = head;
            head = next;
        }
        if(preReverse != null) preReverse.next = reverseHead;
        if(reverseTail != null) reverseTail.next = head;
        if(preReverse == null) return reverseHead;
        return saveHead;
      }
   
   <br>
   

Pretty much the save with the above, just change while to for:

  	public ListNode reverseBetween(ListNode head, int m, int n) {
    	if(head == null) return null;
    	if(m < 0) m = 0;
    	if(m > n){
    		int tempValue = m;
    		m = n;
    		n = tempValue;
    	}
    	
    	ListNode temp = head;
    	ListNode previousM = null;
    	
    	/* find node at m */
    	for(int i = 1; i < m && temp != null; i++){
    		previousM = temp;
    		temp = temp.next;
    	}
    	ListNode saveTemp = temp;
    	
    	/* reverse node between m and n */
    	ListNode pre = null;
    	ListNode next = null;
    	for(int i = m; i <= n && temp != null; i++){
    		next = temp.next;
    		temp.next = pre;
    		pre = temp;
    		temp = next;
    	}
    	
    	/* concatenate */
    	if(previousM != null) previousM.next = pre;
    	if(saveTemp != null) saveTemp.next = temp;
    	
    	if(m == 1) return pre; 
    	return head;
     }
     
 
 ***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>
    
###108 Convert Sorted Array to Binary Search Tree  

>Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

**Idea**: 

Just find the mid of the list each time and create the tree from root.
root = mid
root.left = mid of (0, mid -1)
root.right = mid of (mid + 1, end)

**Time: O(n)**, because we need to traverse all the nodes. 

**Space** O(lg(n)) for recursion. 


	public TreeNode sortedArrayToBST(int[] num) {
        if(num == null || num.length == 0) return null;
        return helper(num, 0, num.length-1);
    }
    public TreeNode helper(int[] num, int start, int end){
        if(start > end) return null;
        int mid = (start + end)/2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = helper(num, start, mid-1);
        root.right = helper(num, mid + 1, end);
        return root;
    }

***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>
 
###109 Convert Sorted List to Binary Search Tree
>Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

**Idea**: 

1) solution1: Bottom-up

The list is sorted and we need to convert it to a **balanced BST**. When visit the linkedlist, we can only visit node by node. Thus we visit the nodes in ascending order: **Inorder Traversal !**. Thus, we create the binary tree **Bottom-up**. The bottom-up approach enables us to access the list in its order while creating nodes.

**Time O(n)**

**Space**: O(n) + O(lgn) = O(n)


2) solution2 : Top-down

Just find the mid of the list each time and create the tree from root.
root = mid
root.left = mid of (0, mid -1)
root.right = mid of (mid + 1, end)

**Time: O(nlgn)**

**Space**: O(n) + O(lgn) = O(n)

3) solution3: 

Store all the nodes in an array, then use the array to create the BST. just like the method in  * [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree). However, not recommend this method. If we do in this way, then this question is meaningless. 

**Time: O(n)**

**Space**: O(n) + O(lgn) = O(n)

*Solution1 code:*

<br>

		 public TreeNode sortedListToBST(ListNode head) {
		    if(head == null) return null;
		    int len = 0;
		    ListNode temp = head;
		    while(temp != null){
		        temp = temp.next;
		        len++;
		    }
		    ListNode[] saveHead = new ListNode[1];
		    saveHead[0] = head;
		    return helper(saveHead, 0, len-1);
		 }
		 public TreeNode helper(ListNode[] saveHead, int start, int end){
		    if(start > end) return null;
		    int mid = (start + end)/2;
		    TreeNode left = helper(saveHead, start, mid-1);
		    TreeNode root = new TreeNode(saveHead[0].val);
		    root.left = left;
		    saveHead[0] = saveHead[0].next;
		    root.right = helper(saveHead, mid + 1, end);
		    return root;
		 }
		 
		 
*solution 2 code*

 		public TreeNode sortedListToBST1(ListNode head) {
			    if(head == null) return null;
			    if(head.next == null) return new TreeNode(head.val);
			    ListNode pre = getMidPre(head);
			    ListNode mid = pre.next;
			    pre.next = null;
			    TreeNode root= new TreeNode(mid.val);
			    root.left = sortedListToBST(head);
			    root.right = sortedListToBST(mid.next);
			    return root;
			 }
			 
			 public ListNode getMidPre(ListNode head){
		        ListNode fast = head;  
		        ListNode pre = head;  
		        while(fast!=null) {  
		            fast = fast.next;  
		            if(fast != null){
		                fast = fast.next;  
		                pre = head;  
		                head = head.next;  
		            } 
		        }  
		        return pre;  
			 }
			 
*solution3 code*

	 public TreeNode sortedListToBST2(ListNode head) {
		    if(head == null) return null;
		    if(head.next == null) return new TreeNode(head.val);
	        int count = 0;
	        ListNode temp = head;
	        while(temp != null){
	            count++;
	            temp = temp.next;
	        }
	        int[] num = new int[count];
	        temp = head;
	        count = 0;
	        while(temp != null){
	            num[count++] = temp.val;
	            temp = temp.next;
	        }
	        return sortedArrayToBST(num);
		 }
		 
	    public TreeNode sortedArrayToBST(int[] num) {
	        if(num == null || num.length == 0) return null;
	        return helper(num, 0, num.length-1);
	    }
	    public TreeNode helper(int[] num, int start, int end){
	        if(start > end) return null;
	        int mid = (start + end)/2;
	        TreeNode root = new TreeNode(num[mid]);
	        root.left = helper(num, start, mid-1);
	        root.right = helper(num, mid + 1, end);
	        return root;
	    }
		 
***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>

###114 Flatten Binary Tree to Linked List
>Given a binary tree, flatten it to a linked list **in-place**.

<pre>
For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

</pre>


**Idea**: From the structure of the original tree and result tree, we know that we need to traverse the tree preorder. We can solve it recursively or use a stack to record the right child. 

**Attention**: we can use a fake node pre so that each time we can add the current node to the right of pre, set pre.left = null and recursively solve this problem. Also, we need to save the right child, because the right child has changed when we visit the left child. 

*Reversion code* : 

		public void flatten(TreeNode root){
		   if(root == null) return;
		   TreeNode[] pre  = new TreeNode[1];
		   pre[0] = new TreeNode(-1);
		   helper(root, pre);
		}
		
		public void helper(TreeNode root, TreeNode[] pre){
		    if(root == null) return;
		    TreeNode right = root.right;
		    pre[0].left = null;
		    pre[0].right = root;
		    pre[0] = root;
		    helper(root.left, pre);
		    helper(right, pre);
		}


*Stack*:

	public void flatten(TreeNode root){
		   if(root == null) return;
		   TreeNode pre = new TreeNode(-1);
		   Stack<TreeNode> stack = new Stack<TreeNode>();
		   int status = 0;
		   while(!stack.isEmpty() || root != null){
		       if(status == 0){
		           if(root == null){
		               status = 1;
		               continue;
		           }
		           pre.right = root;
		           pre.left = null;
		           pre = root;
		           stack.push(root.right);
		           root = root.left;
		       }else{
		           root = stack.pop();
		           status = 0;
		       }
		   }
		}


<br>
***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>

###118 Pascal Triangle

>Given numRows, generate the first numRows of Pascal's triangle.

<pre>
For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

</pre>


**Idea**: First row : 1. Then each row is calculated based on the last row. In each row the first element and the last element is 1. Thus val = j == 0 || j == pre.size() ? 1 : pre.get(j-1)+pre.get(j);

		 public List<List<Integer>> generate(int numRows) {
	        List<List<Integer>> list = new ArrayList<List<Integer>>();
	        if(numRows <= 0) return list;
	        List<Integer> firstR = new ArrayList<Integer>();
	        firstR.add(1);
	        list.add(firstR);
	        for(int i = 1; i < numRows; i++){
	            List<Integer> current = new ArrayList<Integer>();
	            List<Integer> pre = list.get(list.size()-1);
	            for(int j = 0; j <= pre.size(); j++){
	                int val = j == 0 || j == pre.size() ? 1 : pre.get(j-1)+pre.get(j);
	                current.add(val);
	            }
	            list.add(current);
	        }
	        return list;
	    }
	    

***Related Problems***:

* [118 Pascal Triangle](#118-pascal-triangle)
* [119 Pascal Triangle II](#119-pascal-triangle-ii)
* [120 Triangle](#120-triangle)

<br>
<br>


###119 Pascal Triangle II

>Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

**Idea**: If we can only use O(k) space, then we need to store all rows info in a single array. So the current row is calculated based on the last row. Eg: the last row is 1 2 1, we need to replace it with 1 3 3 1. We can ignore the first 1. Then 3 = 1 + 2 = pre + list.get(j). We need to store the current elment before we overwrite it. 

If there is no other requirements, then we can just use the result in [118 Pascal Triangle](#118-pascal-triangle) and get the last row. 


**Attention**:

- 1) k = 3, we return the fourth row.
- 2) we need to store the current elment before we overwrite it. 

<br>

	public List<Integer> getRow(int rowIndex) {
	    List<Integer> list = new ArrayList<Integer>();
    	if(rowIndex < 0) return list;
    	list.add(1);
    	for(int i = 0; i < rowIndex; i++){
    	    int pre = 1;
    	    int current = 0;
    	    for(int j = 0; j < list.size(); j++){
    	        if(j == 0) continue;
    	        current = list.get(j);
    	        list.set(j, pre + list.get(j));
    	        pre = current;
    	    } 
    	    list.add(1);
    	}
        return list;
	}

***Related Problems***:

* [118 Pascal Triangle](#118-pascal-triangle)
* [119 Pascal Triangle II](#119-pascal-triangle-ii)
* [120 Triangle](#120-triangle)

<br>
<br>

###120 Triangle

>Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

<pre>
For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
</pre>

**Idea**: Begin from the second-last layer, calculate the min sum of the last layer and last second layer, then continue the second-last layer and third-last layer....eg: the sum of last layer and last-second layer: min(4, 1) + 6, min(1, 8) + 5, min(8, 3) + 7. If we are only allowed to use O(n) space, then we need to replace the sum each time.

For example: Assume we use int[] res = new int[triangle.get(triangle.size()-1).size()] to store the sum

<pre>

Original triangle:             

[							   
     [2],					   
    [3,4],					  
   [6,5,7],					   
  [4,1,8,3]                  
] 


res array:
11
9, 10
7, 6, 10
4, 1, 8, 3

</pre>

**Space**: O(n)


We can also calculate from the top layer, then go down. It's similar to the method above. It's easier to handle the index if we begin from the last layer.

	public int minimumTotal(List<List<Integer>> triangle) {
	    if(triangle == null || triangle.size() == 0) return 0;
	    int[] res = new int[triangle.get(triangle.size()-1).size()];
	    for(int i = 0; i < res.length; i++){
	        res[i] = triangle.get(triangle.size()-1).get(i);
	    }
	    for(int i = triangle.size()-2; i >= 0; i--){
	        List<Integer> current = triangle.get(i);
	        for(int j = 0; j < current.size(); j++){
	            res[j] = Math.min(res[j], res[j+1]) + current.get(j);
	        }
	    }
	    return res[0];
	 }

***Related Problems***:

* [118 Pascal's Triangle](#118-pascal's-triangle)
* [119 Pascal's Triangle II](#119-pascal's-triangle-ii)
* [120 Triangle](#120-triangle)

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

###126 Word Ladder

>Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:


Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:

start = "hit"

end = "cog"

dict = ["hot","dot","dog","lot","log"]

As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",

return its length 5.

Note:

Return 0 if there is no such transformation sequence.

All words have the same length.

All words contain **only lowercase alphabetic characters**.




**Idea**: This shortest transformation ladder is just like a shortest path in a graph. We teat each string as a graph node. If these two strings only have one different char, then we add an edge to these two nodes. When we come to the end string, we find the shortest path. 

If we use bfs, we can use an additional lenqueue to record the path length of each node. 


**Attention**: 

- 1)Everytime, we visit a string in dict and add an edge, we need to remove it from dict. 
- 2) char arr = start.toCharArray() should be inside the first for while loop. Otherwise, it may change more than one character of the string. 



**Solution**:

		 public static int ladderLength(String start, String end, Set<String> dict) {
	        if(dict == null || dict.size() == 0 || start == null || end == null) return 0;
	        Deque<String> queue = new LinkedList<String>();
	        Deque<Integer> qlen = new LinkedList<Integer>();
	        queue.offer(start);
	        qlen.offer(1);
	        while(!queue.isEmpty()){
	            start = queue.pollFirst();
	            int len = qlen.pollFirst();
	            if(start.equals(end)) return len;
	            for(int i = 0; i < start.length(); i++){
	                char[] arr = start.toCharArray();
	                for(arr[i] = 'a'; arr[i] <='z'; arr[i]++){
	                    String s = new String(arr);
	                    if(dict.contains(s)){
	                        queue.addLast(s);
	                        qlen.addLast(len+1);
	                        dict.remove(s);
	                    }
	                }
	            }
	        }
	        return 0;
	    }
    


* [126 Word Ladder](#126-word-ladder)
* [127 word Ladder II](#127-word-ladder-ii)

<br>
<br>

###127 Word Ladder II

>Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

<pre>
Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
</pre>


<br>



**Idea**: Becuase we need to output all the shortest paths, thus we need to record the parent node in the path. Then build paths based on these parent nodes.

**Attention**:

- 1) we need to remove end from dict if dict contains end, otherwise, we might have duplicates.
- 2) When we find all the path to end, we can break and rebuild the paths.
- 3) We can not remove element immediately when we find an neighbors in dict, because one node might have two or more parents. Thus we need to remove it after we have build the relationships between the node and all its parents. *We can remove it at the beginning of the next while loop*
- 4) when add element to queue, check if queue has alreay contains this element.



<br>

	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(dict == null || dict.size() == 0 || start == null || end == null) return res;
        /*if start equals end, we can return [[start, end]]*/
        if(start.equals(end)){
            List<String> temp = new ArrayList<String>();
            temp.add(start);
            temp.add(end);
            return res;
        }
        
        /*if dict contains end, remove end from dict, otherwise we might have duplicates */
        dict.remove(end); 
        
        Queue<String> queue = new LinkedList<String> ();
        
        /*store the parent node of each node*/
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        queue.offer(start);
        for(String s : dict){
            map.put(s, new ArrayList<String>());
        }
        map.put(end, new ArrayList<String>());
        List<String> cur = new ArrayList<String>();
        
        while(!queue.isEmpty()){
            cur.clear();
            for(int i = 0, size = queue.size(); i < size; i++){
            	String temp = queue.poll();
                cur.add(temp);
                dict.remove(temp);
            }
            for(String s : cur){
                for(int i = 0; i < s.length(); i++){
                    char[] arr = s.toCharArray();
                    for(arr[i] = 'a'; arr[i] <= 'z'; arr[i]++){
                        String temp = new String(arr);
                        /* It's ok not add this sentence. just to jump unnecessary operations*/
                        if(temp.equals(s)) continue;
                        if(temp.equals(end)){
                            map.get(end).add(s);
                        }
                        if(dict.contains(temp)){
                            if(!map.containsKey(temp)){
                                map.put(temp, new ArrayList<String>());
                            }
                            map.get(temp).add(s);
                            if(!queue.contains(temp)) queue.offer(temp);
                        }
                    }
                }
            }
            
            if(map.get(end).size()>0) break;
        }
        
        List<String> path = new ArrayList<String>();
        path.add(end);
        buildPaths(map, res, end, start, path);
        return res;
 	}
 	
 	public void buildPaths(Map<String, ArrayList<String>> map, List<List<String>> res, String end, String start, List<String> path){
 	    if(end.equals(start)){
 	        List<String> apath = new ArrayList<String>(path);
 	        Collections.reverse(apath);
 	        res.add(apath);
 	        return;
 	    }
 	    List<String> pre = map.get(end);
 	    for(String s : pre){
 	        path.add(s);
 	        buildPaths(map, res, s, start, path);
 	        path.remove(path.size()-1);
 	    }
 	}
 	


* [126 Word Ladder](#126-word-ladder)
* [127 word Ladder II](#127-word-ladder-ii)

<br>
<br>



###138 Copy List With Random Pointer

>A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null. Return a deep copy of the list.
 

**Idea** :

**Solution1:**Copy each node in the list, then divide the list into two lists. For example: 1->2->3, we copy nodes and the list turns to 1->1->2->2->3->3. Then we divide it into two 1->2->3

**Attention**: ***we should copy the random pointer seperatel after we copy all the nodes***. At the beginning, I thought that I can copy the random pointer at the same time and just let it pointer to the same node with the original node, when breaking the list, I change the copy node's random to origin's random's next. It works only when the node that random pointer points to is after to the current pointer. What if it is before the current node? we might not able to find the random's next because when breaking the list we have changed the structure of the list. 

**Solution2**: Use hashmap store old and copy, then copy the random pointer

***Solution1 code***

  	public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode temp = head;
        while(temp != null){
            RandomListNode next = temp.next;
            temp.next = new RandomListNode(temp.label);
            temp.next.next = next;
            temp = next;
        }
        temp = head;
        while(temp != null){
            if(temp.random != null){
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }
        RandomListNode fakeHead = new RandomListNode(-1);
        RandomListNode copy = fakeHead;
        while(head != null){
            RandomListNode next = head.next.next;
            copy.next = head.next;
            copy = copy.next;
            copy.next = null;
            head.next = next;
            head = next;
        }
        return fakeHead.next;
    }
    
***Solution2 code***

 	public RandomListNode copyRandomList1(RandomListNode head) {
        if(head == null) return null;
        RandomListNode copy = new RandomListNode(-1);
        RandomListNode temp = head;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        while(temp != null){
            copy.next = new RandomListNode(temp.label);
            map.put(temp, copy.next);
            copy = copy.next;
            temp = temp.next;
        }
        copy = map.get(head);
        RandomListNode saveHead = copy;
        while(head != null){
            copy.random = map.get(head.random);
            head = head.next;
            copy = copy.next;
        }
        return saveHead;
    }

***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>

###141 Linked List Cycle

>Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?

**Idea**: Use two pointers, one pointer move one step each time, the other pointer move two step each time. If they encounter, then a cycle exist.

**Attention**: when check if they encounter, if check if they are null! Otherwise, if(f.equals(s)) will have null pointer. 

    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode f = head;
        ListNode s = head;
        while(f != null){
            f = f.next;
            if(f != null) f = f.next;
            s = s.next;
            if(f == null) return false;
            if(f.equals(s)) return true;
        }
        return false;
    }

***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>    

###142 Linked List Cycle II

>Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?

**Idea**: 

- 1) Use the method in [141 Linked List Cycle](#141-linked-list-cycle) to check if cycle exist. 
- 2) when faster and slower encounters, change the faster to head, and move faster and slower one step each time until they encounter. At this time, the faster and slower point to the node where the cycle begins.

**Attention**: when check if they encounter, if check if they are null! Otherwise, if(f.equals(s)) will have null pointer. 

	 public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        ListNode f = head;
        ListNode s = head;
        while(f != null){
            f = f.next;
            if(f != null) f = f.next;
            s = s.next;
            if(f == null) return null;
            if(f.equals(s)) break;
        }
        f = head;
        while(f != null){
            if(f.equals(s)) return s;
            f = f.next;
            s = s.next;
        }
        return null;
    }

***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>

###143 Reorder List

>Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

**Idea**: We can solve this problem in two ways.
- 1) copy a reversed linked list, then insert half of the list into original list.
- 2) Reverse the last half of the list, then insert it into the first half. 

**Attention**: When reverse, remember to set the first node's next to null, otherwise their will have a loop. 


***Solution1 code ***:


	 public void reorderList(ListNode head) {
        if(head == null) return;
        ListNode copy = new ListNode(-1);
        int count = 0;
        ListNode temp = head;
        while(temp != null){
            ListNode current = new ListNode(temp.val);
            current.next = copy;
            copy = current;
            temp = temp.next;
            count++;
        }
        if(count <= 2) return;
        temp = head;
        ListNode next = null, copyNext = null, pre = null;
        for(int i = 0; i < count/2; i++){
            next = temp.next;
            copyNext = copy.next;
            temp.next = copy;
            copy.next = next;
            pre = copy;
            temp = next;
            copy = copyNext;
        }
        if(count % 2 != 0) temp.next = null;
        else pre.next = null;
    }
    
***Solution2 code ***:
    
      public void reorderList1(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode f = head;
        ListNode s = head;
        ListNode pre = null;
        while(f != null){
            f = f.next;
            if(f != null) f = f.next;
            pre = s;
            s = s.next;
        }
        pre.next = null;
        ListNode lastHalf = null;
        ListNode next = null;
        while(s != null){
        	next = s.next;
            if(lastHalf == null) {
            	lastHalf = s;
            	lastHalf.next = null;
            }else{
                s.next = lastHalf;
                lastHalf = s;
            }
            s = next;
        }
        ListNode temp = head;
        ListNode lastHalfNext = null;
        while(temp != null && lastHalf != null){
            next = temp.next;
            lastHalfNext = lastHalf.next;
            temp.next = lastHalf;
            lastHalf.next = next;
            temp = next;
            lastHalf = lastHalfNext;
        }
    }

***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>

###147 Insertion Sort List	
> Sort a linked list using insertion sort.


**Idea**: It's similar to insertion sort array. The difference is that in array, if we want to insert i into (0....i-1), we can swap form i-1 if array[i] < array[i-1]. But we can not visit a linkedlist by index. What we can do is visit from the first element.

Something we can do to simplify the insert:
 
- 1) first check if current.val >= pre.val. If true, it's already in right place. we can just go to the next element. 
- 2) then check if current.val <= head.val. If true, we can just insert it before the head.
- 3) If it's not eh two cases above, we need to find the right place to insert the element. Because we have eliminate the corner case above, thus we just need to find the insertion spot, then change the relationship of the pointers.

If we add a fakeHead pointer to avoid the null pointer cases, we can have more concise code.

**Time***: O(n^2)

<br>

	public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;
        ListNode fakeHead = new ListNode(-1);
        ListNode cur = head;
        ListNode temp = fakeHead;
        while(cur != null){
            ListNode next = cur.next;
            ListNode pre = fakeHead;
            temp = fakeHead.next;
            while(temp != null && temp.val < cur.val){
                pre = temp;
                temp = temp.next;
            }
            pre.next = cur;
            cur.next = temp;
            cur = next;
        }
        return fakeHead.next;
    }


<br>

	/* Actually, in some cases the code below is a little faster then above. 	Because when the element is alreay in the right place, we don't need to find 	from the beginning */
	
	 public ListNode insertionSortList(ListNode head) {
	        if(head == null) return null;
	        ListNode cur = head.next;
	        ListNode preCur = head;
	        while(cur != null){
	            ListNode next = cur.next;
	            if(cur.val >= preCur.val){
	                preCur = cur;
	            }else if(cur.val <= head.val){
	                cur.next = head;
	                head = cur;
	                preCur.next = next;
	            }else{
	                ListNode insertPre = head;
	                ListNode temp = head.next;
	                while(temp != null && temp.val < cur.val){
	                    insertPre = temp;
	                    temp = temp.next;
	                }
	                insertPre.next = cur;
	                cur.next = temp;
	                preCur.next = next;
	            }
	            cur = next;
	        }
	        return head;
	    }

***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>

###148 Sort List

> Sort a linked list in O(n log n) time using constant space complexity.

**Idea**: It require us to sort in O(nlgn) time. "Mergesort" and "Quicksort" is the typical O(nlgn) sort algorithm. When use merge sort, similar with mergesort an array, we fisrt need to find the mid of the list. (The typical two pointer method to find the middle). Then sort(left, mid-1), sort(mid , right) and merge the result.


<br>

	   public ListNode sortList(ListNode head){
		    if(head == null || head.next == null) return head;
		    ListNode f = head;
		    ListNode s = head;
		    ListNode pre = null;
		    while(f != null){
		        f = f.next;
		        if(f != null){
		            f = f.next;
		            pre = s;
		            s = s.next;
		        }
		    }
		    pre.next = null;
		    return merge(sortList(s), sortList(head));
		}
		
		public ListNode merge(ListNode l1, ListNode l2){
		    if(l1 == null) return l2;
		    if(l2 == null) return l1;
		    ListNode fakeHead = new ListNode(-1);
		    ListNode temp = fakeHead;
		    while(l1 != null && l2 != null){
		        if(l1.val > l2.val){
		            temp.next = l2;
		            l2 = l2.next;
		        }else{
		            temp.next = l1;
		            l1 = l1.next;
		        }
		        temp = temp.next;
		    }
		    if(l1 != null) temp.next = l1;
		    if(l2 != null) temp.next = l2;
		    return fakeHead.next;
		}
		
***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

<br>
<br>		
    
### 156 Binary Tree Upside Down

> Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares th> e same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

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

2) Parant becomes right child: p.right = parent 
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


###160 Intersection of Two Linked Lists
>Write a program to find the node at which the intersection of two singly linked lists begins.


<pre>
For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3

begin to intersect at node c1.

</pre>


Notes:

If the two linked lists have no intersection at all, return null.

The linked lists must retain their original structure after the function returns.

You may assume there are no cycles anywhere in the entire linked structure.

Your code should preferably run in O(n) time and use only O(1) memory.


<br>

**Idea**: 

Solustion1: 

We can get the length of two linkedlist, assume len1 > len2. We move head1 move len1-len2 steps, then compare each node in list1 and list2, until two lists have the same nodes. 

Solution2: We don't need to calculae the length of list1 and list2. Use two pointers p1 p2, when one pointer reaches the end, assume it's p1, we change p1  to head2. Then continues until p2 reach to the end. By this time, p1 is pointer to len2 -len1 of list2, we let p2 points to head1. So we can continue to compare until we encounter same nodes. 


**Attention**: Null pointer! We need to take care of null pointer for all linkedlist problems.

Solution1 :

	 public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	       if(headA == null || headB == null) return null;
	       int lenA = 0;
	       int lenB = 0;
	       ListNode temp = headA;
	       while(temp != null){
	           lenA++;
	           temp = temp.next;
	       }
	       temp = headB;
	       while(temp != null){
	           temp = temp.next;
	           lenB++;
	       }
	       int diff = Math.abs(lenA - lenB);
	       if(lenA > lenB){
	           while(diff > 0) {
	               headA = headA.next;
	               diff--;
	           }
	       }else{
	           while(diff > 0){
	               headB = headB.next;
	               diff--;
	           }
	       }
	       while(headA != null && headB != null && !headA.equals(headB)){
	           headA = headA.next;
	           headB = headB.next;
	       }
	       return headA;
	 }

Solution2:

	 public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
	       if(headA == null || headB == null) return null;
	       ListNode tempA = headA;
	       ListNode tempB = headB;
	       while(tempA != null && tempB != null){
	           tempA = tempA.next;
	           tempB = tempB.next;
	       }
	       tempA = tempA == null ? headB : tempA;
	       tempB = tempB == null? headA : tempB;
	       while(tempA != null && tempB != null){
	           tempA = tempA.next;
	           tempB = tempB.next;
	       }
	      tempA = tempA == null ? headB : tempA;
	      tempB = tempB == null ? headA : tempB;
	      while(tempA != null && tempB != null && !tempA.equals(tempB)){
	          tempA = tempA.next;
	          tempB = tempB.next;
	      }
	      return tempA;
	 }

***Related problems***:

* [19 Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
* [21 Merge Two Sorted Lists](#21-merge-two-sorted-lists)
* [23 Merge k Sorted Lists](#23-merge-k-sorted-lists)
* [61 Rotate List](#61-rotate-list)
* [82 Remove Duplicates from Sorted List](#82-remove-duplicates-from-sorted-list)
* [83 Remove Duplicates from Sorted List II](#83-remove-duplicates-from-sorted-list-ii)
* [92 Reverse Linked List II](#92-reverse-linked-list-ii)
* [108 Convert Sorted Array to Binary Search Tree](#108-convert-sorted-array-to-binary-search-tree)
* [109 Convert Sorted List to Binary Search Tree](#109-convert-sorted-list-to-binary-search-tree)
* [114 Flatten Binary Tree to Linked List](#114-flatten-binary-tree-to-linked-list)
* [138 Copy List With Random Pointer](#138-copy-list-with-random-pointer)
* [141 Linked List Cycle](#141-linked-list-cycle)
* [142 Linked List Cycle II](#142-linked-list-cycle-ii)
* [143 Reorder List](#143-reorder-list)
* [147 Insertion Sort List](#147-insertion-sort-list)
* [148 Sort List](#148-sort-list)
* [160 Intersection of Two Linked Lists](#160-intersection-of-two-linked-lists)

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


###167 Two Sum II Input array is sorted

>Given an array of integers that is already **sorted in ascending order**, find two numbers such that they add up to a specific target number.

>The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are **not zero-based**.
You may assume that each input would have exactly one solution.

	Input: numbers={2, 7, 11, 15}, target=9
	Output: index1=1, index2=2

<br>

**Idea**: We can solve this problem use the second method in * [1 Two Sum](#1-two-sum). Just use two pointers, one at the beginning, one at the ending. Compare num[l] + num[r] and target. If the former is larger, then r--, if the latter is larger, l++.


	public int[] twoSum(int[] numbers, int target){
		int[] res = new int[2];
		if(numbers == null || numbers.length <= 1) return res;
		int l = 0;
		int r = numbers.length -1;
		while(l < r){
			if(numbers[l] + numbers[r] == target){
				res[0] = l+1;
				res[1] = r+1;
				return res;
			}else if(numbers[l] + numbers[r] > target) r--;
			else l++;
		}
		return res;
	}


* [1 Two Sum](#1-two-sum)
* [15 3Sum](#15-3sum)
* [16 3Sum Closest](#16-3sum-closest)
* [18 4Sum](#18-4sum)
* [167 Two Sum II Input array is sorted](#167-two-sum-ii-input-array-is-sorted)
* [170 Two Sum III Data Structure Design](#170-two-sum-iii-data-structure-design)

<br>

<br>


###170 Two Sum III Data Structure Design

>Design and implement a TwoSum class. It should support the following operations: add and find.

>add - Add the number to an internal data structure.

>find - Find if there exists any pair of numbers which sum is equal to the value.

	For example,
	add(1); add(3); add(5);
	find(4) -> true
	find(7) -> false
	
<br>

**Idea**: How to design the structure is based on our needs. Remember in * [1 Two Sum](#1-two-sum) , the first method we use hashmap to record each number, and check if sum - number exist. If we do this the same way in this problem, we can use hashmap to record each number when add, and find the number use the method in * [1 Two Sum](#1-two-sum). 

**Time complexity** : add O(1) find O(n)

**Space**: O(n)

***What if we want to find sum in O(1)?***

We need to record the all possible sum when add new numbers. Then the time complexity would change to O(n). Also, we need a set to record the possible sum. So we need to increase the space. 

	public class TwoSum{
		private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		public void add(int number){
			if(map.containsKey(number)) map.put(number, map.get(number)+1);
			else map.put(number, 1);
		}
		
		public boolean find(int value){
			for(Integer i : map.keySet()){
				int remain = value -i;
				if(map.containsKey(remain)){
					if(remain == i && map.get(remain) < 2) continue;
					else return true;
				}
			}
			return false;
		}
	}
	






* [1 Two Sum](#1-two-sum)
* [15 3Sum](#15-3sum)
* [16 3Sum Closest](#16-3sum-closest)
* [18 4Sum](#18-4sum)
* [167 Two Sum II Input array is sorted](#167-two-sum-ii-input-array-is-sorted)
* [170 Two Sum III Data Structure Design](#170-two-sum-iii-data-structure-design)

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


###189Rotate Array

>Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].


Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

**Idea**

- 1)Solution1:  In place. Reverse the array three times. (0, n-1) (0, k-1), (k-n-1)
- 2)Solution2:  Use an extra array, and copy to the right place.

<br>
**Solution1**:

	 public void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) return;
        k = k % nums.length;
       reverse(nums, 0, nums.length-1);
       reverse(nums, 0, k-1);
       reverse(nums, k, nums.length-1);
    }
    
    public void reverse(int[] nums, int left, int right){
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
    
**Solution 2**:

    public void rotate1(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) return;
        int[] result = new int[nums.length];
        k = k % nums.length;
        for(int i = 0; i < k; i++){
            result[i] = nums[nums.length -k + i];
        }
        for(int i = k; i < nums.length; i++){
            result[i] = nums[i-k];
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = result[i];
        }
    }





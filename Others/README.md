###Others

* [1 Search a 2D Matrix II](#1-search-a-2d-matrix-ii)
* [2 First Bad Version](#2-first-bad-version)
* [3 Compare Strings](#3-compare-strings)
* [4 Longest Common Substring](#4-longest-common-substring)
* [5 Insert in Sorted Linked List](#5-insert-in-sorted-linked-list)
* [6 Is Bipartite](#6-is-bipartite)
* [7 Lowest Common Ancestor](#7-lowest-common-ancestor)
* [8 Median tracker](#8-median-tracker)
* [9 95 Percentile](#9-95-percentile)
* [10 Perfect shuffle](#10-perfect-shuffle)
* [11 Reservoir sample](#11-reservoir-sample)
* [12 Random7 Using Random5](#12-random7-using-random5)
* [13 Random1000 using random5](#13-random1000-using-random5)
* [14 Array deduplication](#14-array-deduplication)
* [15 Move 0s to the end](#15-move-0s-to-the-end)
* [16 Largest and Samllest](#16-largest-and-smallest)
* [17 Sort in specific order](#17-sort-in-specific-order)
* [18 Closest number in binary search tree](#18-closest-number-in-binary-search-tree)
* [19 Delete In Binary Search Tree](＃19-delete-in-binary-search-tree)
* [20 Cutting wood](#20-cutting-wood)
* [21 Merge stone](#21-merge-stone)
* [22 Binary Tree Path Sum To Target](#22-binary-tree-path-sum-to-target)
* [23 Common Elements in Three Sorted Array](#23-common-elements-in-three-sorted-array)
* [24 Kth smallest number in sorted matrix](#24-kth-smallest-number-in-sorted-matrix)
* [25 String Abbreviation Matching](#25-string-abbreviation-matching)
* [26 Permutation index](#26-permutation-index)
* [27 Permutation index II](#27-permutation-index-ii)
* [28 SellTicket](#28-sellticket)
* [29 Uneaten leaves](#29-uneaten-leaves)
* [30 Longest Chain](#30-longest-chain)
* [31 Friend Circle](#31-friend-circle)
* [32 Stock Max](#32-stock-max)
* [33 Flipping bits](#33-flipping-bits)
* [34 Hamming distance](#34-hamming-distance)
* [35 Find a pair with given sum in a Balanced BST](#35-Find-a-pair-with-given-sum-in-a-Balanced-BST)
* [36 Sliding window maximum](#36-sliding-window-maximum)
* [37 Put Chair](#37-put-chair)
* [38 Post Office Problem](#38-post-office-problem)
* [39 Upvotes](#39-upvotes)
* [40 Partition Sum](#40-partition-sum)
* [41 Lexicographic paths](#41-lexicographic-paths)
* [42 Power of 4](#42-power-of-4)
* [43 Mod five iterator](#43-mod-five-iterator)


###1 Search a 2D Matrix II

> Write an efficient algorithm that searches for a value in an n x m table (two-dimensional array). This table is sorted along the rows and columns — that is,

> Integers in each row are sorted from left to right.

> Integers in each column are sorted from up to bottom.

<pre>
eg:


1 4 7
2 5 8
3 6 9

</pre>


**Idea**:

**Solution 1**: We can use the idea in solution 1 of * [74 Search a 2D Matrix](https://github.com/wishyouhappy/leetcode#74-search-a-2d-matrix). We search from the upright, each time we can ignore one row or one column. Thus the overall time complexity if O(m+n)

**Solution 2**: Use divide and conquer to solve this problem. For each element in the matrix,if we treat it as a center element, we can divide the matrix into 4 submatrix. There are three ways to apply partition, row-based, column-based and diagonal. 

Considering the three cases int he following picture:

![matrix](https://wishyouhappy.github.io/pictures/matrix.png)

- 1) column-based: we search from the middle column,  if we need to find 10, then we first search on the hightlighted column, we find that 10 is between 6-11, then we search from the upright and bottomleft sub-matrix

- 2) row-based: we search from the middle row, if we need to find 8, then we first search on the hightlighted row, we find that 8 is between 6-10, then we search from the upright and bottomleft sub-matrix

- 3) diagonal: **note that if you want to use diagonal method, the matrix need to have a square matrix**. if we need to find 10, then we first search on the hightlighted column, we find that 10 is between 7-13, then we search from the upright and bottomleft sub-matrix


**Solution 3**: Improve the method in solution 2. We can apply binary search to three ways described above. 

** Java Solution**:

**Solution 1**: Time complexity: O(m + n)

```java 
	 public boolean searchMatrix(int[][] matrix, int target) {
		 if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			 return false;
		 }
		 int i = 0;
		 int j = matrix[0].length - 1;
		 int row = matrix.length;
		 while(i < row && j >= 0) {
			 if (matrix[i][j] == target) {
				 return true;
			 } else if (matrix[i][j] > target) {
				 j--;
			 } else {
				 i++;
			 }
		 }
		 return false;
	 }
```


**Solution 2**: Time complexity O(nlgn)

1) In the code below, we apply search on the diagonal direction.**matrix must be square**

```java

	 public boolean searchMatrix1(int[][] matrix, int target) {
		 return helper(matrix, target, 0, matrix[0].length - 1, 0, matrix.length - 1);
	 }
	 
	 /* l r u b stands for left, right, top, bottom.*/ 
	 public boolean searchMatrix1(int[][] matrix, int target, int l, int r, int t, int b) {
		 if (l > r || t > b) {
			 return false;
		 }
		 int currentRow = t;
		 int currentCol = l;
		 while(currentRow <= b && currentCol <= r && matrix[currentRow][currentCol] <= target) {
			 if (matrix[currentRow][currentCol] == target) {
				 return true;
			 }
			 currentRow++;
			 currentCol++;
		 }
		 return helper(matrix, target, l, currentCol - 1, currentRow, b) || helper(matrix, target,currentCol, r, t, currentRow - 1);
		 
	 }

```


2) row-based

```java
	 public boolean searchMatrix(int[][] matrix, int target) {
		 return helper(matrix, target, 0, matrix[0].length - 1, 0, matrix.length - 1);
	 }
	 
	 /* l r u b stands for left, right, top, bottom.*/ 
	 public boolean searchMatrix(int[][] matrix, int target, int l, int r, int t, int b) {
		 if (l > r || t > b) {
			 return false;
		 }
		 int midRow = t + (b - t) / 2;
		 int currentCol = l;
		 while(currentCol <= r && matrix[midRow][currentCol] <= target) {
			 if (matrix[midRow][currentCol] == target) {
				 return true;
			 }
			 currentCol++;
		 }
		 return helper(matrix, target, l, currentCol - 1, midRow + 1, b) || helper(matrix, target,currentCol, r, t, midRow - 1);
		 
	 }


```


**Solution 3**: Time complexity:O(n)

T(n) = 2 * T(n/2) + lgn Use master theory, T(n) = O(n)

Take row-based as an example

```java
	 public boolean searchMatrix(int[][] matrix, int target) {
		 return helper(matrix, target, 0, matrix[0].length - 1, 0, matrix.length - 1);
	 }
	 
	 /* l r u b stands for left, right, top, bottom.*/ 
	 public boolean searchMatrix(int[][] matrix, int target, int l, int r, int t, int b) {
		 if (l > r || t > b) {
			 return false;
		 }
		 int midRow = t + (b - t) / 2;
		 int currentCol = l;
		 int right = r;
		 while(currentCol <= r && matrix[midRow][currentCol] <= target) {
			 int mid = currentCol + (right - currentCol) / 2;
			 if (matrix[midRow][mid] == target) {
				 return true;
			 } else if (matrix[midRow][mid] > target) {
				 right = mid - 1;
			 } else {
				 currentCol = mid + 1;
			 }
		 }
		 return helper(matrix, target, l, currentCol - 1, midRow + 1, b) || helper(matrix, target,currentCol, r, t, midRow - 1);
		 
	 }

```



###2 First Bad Version

>*From lintcode*

> The code base version is an integer start from 1 to n. One day, someone committed a bad version in the code case, so it caused this version and the following versions are all failed in the unit tests. Find the first bad version.

>You can call isBadVersion to help you determine which version is the first bad one. The details interface can be found in the code's annotation part.

>Example
Given n=5:

>Call isBadVersion(3), get false;

>Call isBadVersion(5), get true;

>Call isBadVersion(4), get true;

>Here we are 100% sure that the 4th version is the first bad version.

>Note
Please read the annotation in code area to get the correct way to call isBadVersion in different language. For example, Java is VersionControl.isBadVersion(v)

>Challenge
You should call isBadVersion as few as possible.


**Idea**: Since the bad version causes it's later version fail to the test, thus if version i is ok, then versions after i must be ok. Thus, we can use binary search to search the first bad version. 

**Code**:

```java

/**
 * public class VersionControl {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use VersionControl.isBadVersion(k) to judge wether 
 * the kth code version is bad or not.
*/
class Solution {
    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
        if (n <= 0) {
            return -1;
        }
        
        int l = 1;
        int r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (VersionControl.isBadVersion(mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


```

<br>
<br>


###3 Compare Strings

> Compare two strings A and B, determine whether A contains all of the characters in B.

> The characters in string A and B are all Upper Case letters.

<pre>
Example
For A = "ABCD", B = "ACD", return true.

For A = "ABCD", B = "AABC", return false.

**"ABCDE", "DB" return true**

</pre>

Note
**The characters of B in A are not necessary continuous or ordered.**


**Idea**: 
- The question does not require that the appear sequence in A and B sould be the same, thus we need fisrt sort tehe string before we compare. 
- When compare, since it does not require the characters should be continuous in A, thus for each begin index in A, we need to walk through the end to check if B exists. Here is a example solution below in O(n^2) time. 


**Solution**:

```java
public class Solution {
    /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        // write your code here
        if (A == null || B == null) {
            return false;
        }
        if (B.length() == 0) {
            return true;
        }
        char[] arrA = A.toCharArray();
        char[] arrB = B.toCharArray();
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        
        for (int i = 0; i <= A.length() - B.length(); i++) {
            int temp = 0;
            int j = 0;
            while (j < B.length() && i + temp < A.length()) {
                if (arrA[i + temp] == arrB[j]) {
                    temp++;
                    j++;
                } else {
                    temp++;
                }
            }
            if (j > B.length() - 1) {
                return true;
            }
        }
        return false;
    }
}
```

<br>
<br>



###4 Longest Common Substring

>Given two strings, find the longest common substring. Return the length of it. From lintcode

>Example, Given A = "ABCD", B = "CBCE", return 2.

Note: The characters in substring should occur continuously in original string. This is different with subsequence.

Challenge O(n x m) time and memory.

**Idea**: 
The most straight forward way to solve this problem takes O(n^3) time, we compare from the begining of two string, when not equal, update the max, move the pointer of the second string to the next character. The time complexity is bad. 

Generally, there are two ways to solve the longest common substring problem. 

- Dynamic programming

- **suffix tree**

Using dynamix programming we can get the result in O(m * n) time, m, n is the length of the two strings.

Using suffix tree we can reduce the time complexity to O(m + n). 

Below is the solution of dynamic programming.

Want to know more about the solution of suffix tree, click on this two links: [longest common substring](http://algs4.cs.princeton.edu/63suffix/LongestCommonSubstring.java.html) , [suffix array](http://algs4.cs.princeton.edu/63suffix/SuffixArray.java.html)

**Solution**:

```java
public class Solution {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int maxLen = Math.max(A.length(), B.length());
        int[][] dp = new int[maxLen+1][maxLen+1];
        int res = 0;
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                    res = Math.max(res, dp[i+1][j+1]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return res;
    }
}
```

<br>
<br>



###5 Insert in Sorted Linked List

**Idea**: We need to find insert position, then add the element into the linkedlist

- 1) if  head is null, return new ListNode as head
- 2) if insert value <= head.value, add element at the beginning, return new added list element
- 3) find insert position, insert element 



```java
  public ListNode insert(ListNode head, int value) {
    ListNode temp = new ListNode(value);
    if (head == null || head.value >= value) {
      temp.next = head;
      return temp;
    }
    ListNode pre = head;
    while (pre.next != null && pre.next.value < value) {
      pre = pre.next;
    }
    temp.next = pre.next;
    pre.next = temp;
    return head;
  }
}
```


<br>
<br>


###6 Is Bipartite

>Suppose a graph is repensented as list, check if this graph is bipartite. If you are not familiar with bipartite, click on this link: [bipartite](http://en.wikipedia.org/wiki/Bipartite_graph).

**Idea**: In bipartite graph, we can divide the graph into two groups and inside each group, there is no edge between nodes in that group. 

Let's give an example, suppose group red and blue, if node 1 is in group green, if node 2 is neighbor of node 1, then node 2 must in group red. Thus we can traverse the graph to check if this condition is always true.

**Some notes**:

- 1) In graph traversal, unlike tree traversal, we need to record if a node is visited or not

- 2) In this problem, we also need to record the group information of each node. Let's use the above example: if node 1 is in group green, if node 2 is neighbor of node 1, then node 2 must in group red. And if node 2 has neighbor node 3, and node3 is red, then the graph is not a bipartite. 


**Solution**:

```java
/**
 * public class GraphNode {
 *   public int key;
 *   public List<GraphNode> neighbors;
 *   public GraphNode(int key) {
 *     this.key = key;
 *     this.neighbors = new ArrayList<GraphNode>();
 *   }
 * }
 */
public class Solution {
  public boolean isBipartite(List<GraphNode> graph) {
    if (graph.size() == 0) {
      return true;
    }
    Map<GraphNode, Integer> map = new HashMap<>();
    for (GraphNode node : graph) {
      Queue<GraphNode> queue = new LinkedList<>();
      if (!map.containsKey(node)) {
        queue.offer(node);
        map.put(node, 0);
      } else {
        continue;
      }
      while (!queue.isEmpty()) {
        GraphNode temp = queue.poll();
        int color = map.get(temp);
        for (GraphNode neib : temp.neighbors) {
          if (!map.containsKey(neib)) {
            map.put(neib, color == 0 ? 1 : 0);
            queue.offer(neib);
          } else if (map.get(neib) == color) {
            return false;
          } 
        }
      }
    }
    return true;
  }
}
```

<br>
<br>
###7 Lowest Common Ancestor

>Given two nodes in a binary tree, find their lowest common ancestor.

**Idea**: Use recursion

1) base case : root == null, root == one or root == two
2) expect from left child : left child returns one or two
   expect from right child : right child returns one or two.
3) In current level: 
    a. if left child returns one or two and right child returns one or two, indicates that we have found the lowest common ancester, returns root.
    b. if one side returns one or two, return the not null one.
 


```java
  public TreeNode lowestCommonAncestor(TreeNode root,
      TreeNode one, TreeNode two) {
    if (root == null) {
      return null;
    }
    if (root == one || root == two) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, one, two);
    TreeNode right = lowestCommonAncestor(root.right, one, two);
    if (left != null && right != null) {
      return root;
    }
    return left == null ? right : left;
  }
```

<br>

**Related**: What if the TreeNode has parant pointer?

**Idea**: Calculate the height from root to two nodes, find the difference, then use these height to find their common ancestor.

```java
  public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
    int heightOne = getHeight(one);
    int heightTwo = getHeight(two);
    int diff = heightOne - heightTwo;
    return diff < 0 ? getAncestor(one, two, -diff) : getAncestor(two, one, diff);
  }
  
  private TreeNodeP getAncestor(TreeNodeP one, TreeNodeP two, int diff) {
    while (diff > 0) {
      two = two.parent;
      diff--;
    }
    while (one != two) {
      one = one.parent;
      two = two.parent;
    }
    return one;
  }
  private int getHeight(TreeNodeP node) {
    int res = 0;
    while (node != null) {
      res++;
      node = node.parent;
    }
    return res;
  }

```

<br>

**Related**: Lowest common ancestor of k nodes.

```java
  public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
    if (root == null) {
      return null;
    }
    for (TreeNode node : nodes) {
      if (root == node) {
        return root;
      }
    }
    TreeNode left = lowestCommonAncestor(root.left, nodes);
    TreeNode right = lowestCommonAncestor(root.right, nodes);
    if (left != null && right != null) {
      return root;
    }
    return left != null ? left : right;
  }

```
<br>
<br>

###8 Median Tracker
> Unlimited flow of numbers, return median sofar.

**Idea**: minHeap and maxHeap

minHeap stores the larger half

MaxHeap stores the smaller half

size difference of minHeap and maxHeap should <= 1.

**Solution**:

```java
  private PriorityQueue<Integer> minQue;
  private PriorityQueue<Integer> maxQue;
  public Solution() {
    minQue = new PriorityQueue<Integer>(); // for larger half
    maxQue = new PriorityQueue<Integer>(10, Collections.reverseOrder()); // for smaller half
  }
  
  public void read(int value) {
    // minQue.size >= maxQue.size, minQue.size - maxQue.size <= 1
    if (minQue.isEmpty() || value >= minQue.peek()) {
      minQue.offer(value);
    } else {
      maxQue.offer(value);
    }
    //if maxQue.size > minQue.size, poll one from maxQue to minQue
    if (maxQue.size() > minQue.size()) {
      minQue.offer(maxQue.poll());
    } else if (minQue.size() - maxQue.size() > 1) {
      maxQue.offer(minQue.poll());
    }
  }
  
  public Double median() {
    int size = minQue.size() + maxQue.size();
    if (size == 0) {
      return null;
    } else if (size % 2 == 0) {
      return (minQue.peek() + maxQue.peek())/(double)2;
    } else {
      return (double)minQue.peek();
    }
  }
```


<br>

<br>
###9 95 Percentile

>Find 95 percentile. Suppose the maximum length  is 4096 and lengths is not null and not empty.

**Idea**:

Count sort, then find 95% or 5%.

**Solution**:

```java
  public int percentile95(List<Integer> lengths) {
    int[] arr = new int[4097];
    for (Integer i: lengths) {
      arr[i]++;
    }
    int percentile = (int)(0.05 * lengths.size());
    int count = 0;
    int res = 4097;
    while (count <= percentile) {
      count += arr[--res];
    }
    return res;
  }
```


<br>
<br>

###10 Perfect shuffle

> shuffle the array such that all permutations are equally likely to be generated.

**Idea**: Random chooce a index, swap it with the current element. So the probability will be the same.

**Solution**:


```java

  public void shuffle(int[] array) {
    for (int i = array.length - 1; i >= 0; i--) {
      int random = (int) (Math.random() * (i + 1));
      swap(array, i, random);
    }
  }
  
  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

```

<br>
<br>


###11 Reservoir sample
>Unlimited flow, return a random number read so far.

**Idea**: Save it at probability 1/n.

**Solution**:

```java
public class Solution {
  private Integer res;
  private int count;
  public Solution() {
    res = null;
    count = 0;
  }
  
  public void read(int value) {
    count++;
    int temp = (int) (Math.random() * count);
    if (temp == 0) {
      res = value;
    }
  }
  
  
  public Integer sample() {
    return res;
  }
}
```
<br>
<br>

###12 Random7 Using Random5

>Given random5() which generates[0, 4], implement random7().

**Idea**:

RandomFive.random5() * 5 + RandomFive.random5() generates[0, 24] in equal probability.

Then match to [0, 6]. We can use %, but the probability is not equal, thus we could only includes [0, 20]

**Solution**:

```java
  public int random7() {
    int res = 0;
    while (true) {
      res = RandomFive.random5() * 5 + RandomFive.random5() ;
      if (res <= 20) {
        break;
      } 
    }
    return res % 7;
  }
```
<br>
<br>


###13 Random1000 using random5


> Given random5() which generates[0, 4], implement random1000().


**Idea**:

Create random10() to generate [0, 9] equally, then use random10() to generate three digits of Random1000.

**Solution**:


```java
  public int random1000() {
    return random10() * 100 + random10() * 10 + random10();
  }
  
  private int random10() {
    return  random2() * 5 + RandomFive.random5();
  }
  
  private int random2() {
    return (int) (Math.random() * 2);
  }
```

<br>

<br>

###14 Array deduplication

> Deduplication in place, for each group keep only one of them.

**Solution**:

```java

  public int[] dedup(int[] array) {
    if (array == null || array.length <= 1) {
      return array;
    }
    int l = 0;
    int r = 1;
    while (r < array.length) {
      if (array[l] != array[r]) {
        array[++l] = array[r];
      }
      r++;
    }
    return Arrays.copyOfRange(array, 0, l + 1);
  }
```
<br>

**Related**: Keep two duplicates.

```java
  public int[] dedup(int[] array) {
    if (array == null || array.length <= 2) {
      return array;
    }
    int l = 1;
    int r = 2;
    while (r < array.length) {
      if (array[r] != array[l - 1]) {
        array[++l] = array[r];
      }
      r++;
    }
    return Arrays.copyOfRange(array, 0, l + 1);
  }

```

<br>

**Related**: Keep zero.

```java
  public int[] dedup(int[] array) {
    if (array == null || array.length == 0) {
      return array;
    }
    int l = 0;
    boolean isDup = false;
    for (int i = 1; i < array.length; i++) {
      if (array[i] == array[l]) {
        isDup = true;
      } else if (isDup == true) {
        array[l] = array[i];
        isDup = false;
      } else {
        array[++l] = array[i];
      }
    }
    return isDup ? Arrays.copyOfRange(array, 0, l) : Arrays.copyOfRange(array, 0, l + 1);
  }

```

**Related**: deduplication recursive.

> For example: [1, 2, 3, 3, 3, 2, 2, 1, 2, 2] , result should be []

**Solution**:

```java
  public int[] dedup(int[] array) {
    if (array == null || array.length == 0) {
      return array;
    }
    int l = -1;
   for (int r = 0; r < array.length; r++) {
      if (l == -1 || array[r] != array[l]) {
        array[++l] = array[r];
      } else {
        while (r + 1 < array.length && array[r + 1] == array[l]) {
          r++;
        }
        l--;
      }
    }
    return Arrays.copyOfRange(array, 0, l + 1);
  }

```

<br>
<br>


###15 Move 0s to the end

>Move all the 0s to the right end of the array.

> No need to keep relative order


**Solution**:

```java
  public int[] moveZero(int[] array) {
    int start = 0;
    int end = array.length - 1;
    while (start <= end) {
      if (array[start] == 0) {
        while (end >= 0 && array[end] == 0) {
          end--;
        }
        if (end < start) {
            break;
        }
        array[start] = array[end];
        array[end] = 0;
        end--;
      }
      start++;
    }
    return array;
  } 
```

<br>

**Related**:


> Need to keep the relative order


**Idea**: Traverse from left, keep the relative order of non-zero element, then fill the rest to 0s.

**Solution**:

```java
  public int[] moveZero(int[] array) {
    if (array == null || array.length == 0) {
      return array;
    }
    int l = 0;
    int r = 0;
    while (r < array.length) {
      if (array[r] != 0) {
        array[l++] = array[r];
      }
      r++;
    }
    while(l < array.length) {
      array[l++] = 0;
    }
    return array;
  }
```

<br>
<br>

###16 Largest and Samllest

>Return the largest number and the smallest number using the smallest comparison.

**Idea**:

The most straightforward way is 2n comparing times.

Compare in pair, so the compare time is 3/2 * n

**Solution**:

```java
  public int[] largestAndSmallest(int[] array) {
    int[] res = new int[2];
    res[0] = Integer.MIN_VALUE;
    res[1] = Integer.MAX_VALUE;
    int l = 0;
    int r = array.length - 1;
    while (l <= r) {
      boolean isSmaller = array[l] < array[r];
      if (isSmaller) {
        res[0] = Math.max(array[r], res[0]);
        res[1] = Math.min(array[l], res[1]);
      } else {
        res[0] = Math.max(array[l], res[0]);
        res[1] = Math.min(array[r], res[1]);
      }
      l++;
      r--;
    }
    return res;
  }
```

<br>

**Related**: Largest and second largest.

> Find the largest and second largest element in least comparison.

**Idea**: Compare two and two, record the element that smaller then that element when compare. 

After the first round of comparison, we get a binary tree, and the root is the largest, then we find the second largest in the list we record. 


**Time complexity : n + log(n). 

where n is the time complexity for two two compare, log(n) is the time complexity for find the second largest. Since the height of the tree is log(n), thus the time is log(n) to find the second largest.



**Solution**:

```java
  public int[] largestAndSecond(int[] array) {
    int[] res = new int[2];
    if (array == null || array.length < 2) {
      return res;
    }
    List<Pair> list = new ArrayList<Pair>();
    for (int i = 0; i < array.length; i++) {
      list.add(new Pair(i, array[i]));
    }
    Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    while (list.size() > 1) {
      List<Pair> next = new ArrayList<Pair>();
      for (int i = 0; i < list.size(); i += 2) {
        if (i == list.size() - 1) {
          next.add(list.get(i));
        } else {
          Pair e1 = list.get(i);
          Pair e2 = list.get(i + 1);
          if (e1.value <= e2.value) {
            next.add(e2);
            if (!map.containsKey(e2.index)) {
              map.put(e2.index, new ArrayList<Integer>());
            } 
            map.get(e2.index).add(e1.value);
          } else {
            next.add(e1);
            if (!map.containsKey(e1.index)) {
              map.put(e1.index, new ArrayList<Integer>());
            }
            map.get(e1.index).add(e2.value);
          }
        }
      }
      list = next;
    }
    res[0] = list.get(0).value;
    res[1] = getSecondLargest(map.get(list.get(0).index));
    return res;
  }

```


<br>
<br>


###17 Sort in specific order

> Given two arrays, A1 and A2, sort A1 in such a way that the relative order among the elements will be same as those are in A2.

**Idea**:

Create an comparator, sort according the rules in comparator.

- If e1, e2 both exist in A2, then we sort them according to their indexs in A2

- If only e1 in A2, then e1 < e2

- If only e2 in A2, then e2 < e1

- Otherwise, compare them in the natural order.

Preprocess: We need a hashmap t record the index of each element in A2. 

**Note**: 

- this hashmap should be **final**, because it needs to be used into the comparator

- we needs to convert int[] to Integer[], because we used the Arrays.sort(), it needs a T[].

**Time complexity: O(nlgn)

**Solution**:

```java
  public int[] sortSpecial(int[] A1, int[] A2) {
    if (A1 == null || A1.length == 0) {
      return A1;
    }
    final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < A2.length; i++) {
      if (!map.containsKey(A2[i])) {
        map.put(A2[i], i);
      }
    }
    Comparator<Integer> comp = new Comparator<Integer>() {
      public int compare(Integer arg1, Integer arg2) {
        if (map.containsKey(arg1) && map.containsKey(arg2)) {
          return map.get(arg1) - map.get(arg2);
        } else if (map.containsKey(arg1)) {
          return -1;
        } else if (map.containsKey(arg2)) {
          return 1;
        } else {
          return arg1 - arg2;
        }
      }
    };
    Integer[] arr = convertToIntegerArray(A1);
    Arrays.sort(arr, comp);
    return convertBackToIntArray(arr);
  }
  
  private Integer[] convertToIntegerArray(int[] arr) {
    Integer[] res = new Integer[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = Integer.valueOf(arr[i]);
    }
    return res;
  }
  
  private int[] convertBackToIntArray(Integer[] arr) {
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

```
<br>
<br>


###18 Closest number in binary search tree
> Given a binary search tree and a target value, return the number that is closest to target.


**Solution**:


```java
  public int closest(TreeNode root, int target) {
    if (root == null) {
      return -1;
    }
    int res = root.key;
    while (root != null) {
      if (Math.abs(target - root.key) < Math.abs(target - res)) {
        res = root.key;
      }
      if (target > root.key) {
        root = root.right;
      } else if (target == root.key) {
        return root.key;
      } else {
        root = root.left;
      }
    }
    return res;
  }

```

<br>

**Related**: Largest Number Smaller In Binary Search Tree

**Idea**: Update result only when go to the right subtree.

**Solution**:


```java
public int largestSmaller(TreeNode root, int target) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    int res = Integer.MIN_VALUE;
    while (root != null) {
      if (target > root.key) {
        res = root.key;
        root = root.right;
      } else {
        root = root.left;
      }
    }
    return res;
}

```

<br>
<br>

###19 Delete In Binary Search Tree


> Given a binary search tree, delete a node, the structure of BST should be maintianed. Suppose there is no duplicates in the BST.

**Idea**: First we need to find the node we need to delete. Then there are three cases:

- Target does not have child, just delete it.

- Target has only one child, set target.parent.left/right = target.left/right

- Target has two children, if target.right.left == null, just set target.right.left = target.left. Otherwise, find the smallest element as the new root in target's right subtree.

**Solution**:



```java
/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public TreeNode delete(TreeNode root, int key) {
    if(root == null) {
      return null;
    } else if (key < root.key) {
      root.left = delete(root.left, key);
      return root;
    } else if (key > root.key) {
      root.right = delete(root.right, key);
      return root;
    } else {
      return deleteNode(root);
    }
  }
  
  private TreeNode deleteNode(TreeNode root) {
    if (root.left == null) {
      return root.right;
    } else if (root.right == null) {
      return root.left;
    } else if (root.right.left == null) {
      root.right.left = root.left;
      return root.right;
    } else {
      TreeNode newRoot = getNewRoot(root.right);
      newRoot.left = root.left;
      newRoot.right = root.right;
      return newRoot;
    }
  }
  
  private TreeNode getNewRoot(TreeNode root) {
    TreeNode pre = null;
    while (root.left != null) {
      pre = root;
      root = root.left;
    }
    TreeNode res = root;
    pre.left = pre.left.right;
    return res;
  }
  
  
}

```


<br>
<br>

###20 Cutting wood


> Cutting wood into pieces, where the cutting positions are defined in an int array A and the cost of each cut is the length of the stick segment being cut. Determine the minimum total cost to cut.


**Idea**:

Fill cuts array with start and end. For example wood length 5, cuts[] = {1, 3}, we fill it to {0, 1, 3, 5}

then cost[i][j] = cut[j] - cut[i] + min(cut[i][k] + cut[k][j])

**Fill it from bottom to top, left to right**

Time: o(N ^ 3)


**Solution**:


```java
  public int minCost(int[] cuts, int length) {
    int[] arr = new int[cuts.length + 2];
    int[][] cost = new int[arr.length][arr.length];
    arr[0] = 0;
    for (int i = 0; i < cuts.length; i++) {
      arr[i + 1] = cuts[i];
    }
    arr[arr.length - 1] = length;
    for (int i = 1; i < arr.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (j == i - 1) {
          cost[j][i] = 0;
        } else {
          cost[j][i] = Integer.MAX_VALUE;
          for (int k = j + 1; k < i; k++) {
            cost[j][i] = Math.min(cost[j][i], cost[j][k] + cost[k][i]);
          }
          cost[j][i] += arr[i] - arr[j];
        }
      }
    }
    return cost[0][arr.length - 1];
  }

```

<br>
<br>

###21 Merge stone

> Give a list of piles of stones, each pile of stones has a certain weight represent as an integer array. Each time we can merge two adjacent piles into one larger pile, the cost is the sum of the weights of the two piles. Determine the minimum total cost to merge the piles of stones until we have only one pile left. 

eg : {4, 3, 3, 4}, the minimum cost is 28.

**Idea**:

Pretty like the cutting wood. 

induction rule:  min[j][i] = Math.min(min[j][k] + min[k + 1][i] + sum[j][i], min[j][i]);

**Solution**:

```java
public int minCost(int[] stones) {
  if (stones == null || stones.length == 0) {
      return 0;
  }
  int[][] min = new int[stones.length][stones.length];
  int[][] sum = new int[stones.length][stones.length];
  for (int i = 0; i < stones.length; i++) {
      for (int j = i; j >= 0; j--) {
          if (j == i) {
              sum[j][i] = stones[j];
              min[j][i] = 0; 
          } else {
              sum[j][i] = sum[j][i - 1] + stones[i];
              min[j][i] = Integer.MAX_VALUE;
              for (int k = j; k < i; k++) {
                  min[j][i] = Math.min(min[j][k] + min[k + 1][i] + sum[j][i], min[j][i]);
              }
          }
      }
  }
  return min[0][stones.length - 1];
}

```

<br>
<br>

###22 Binary Tree Path Sum To Target

> Binary tree, the two nodes can be the same node and they can only be on the path from root to one of the leaf nodes, from any node to any node, check if sum to target exists.

**Idea**: Record the prefix nodes from root of each node, each time visit a node, check if there is a path sum to target sum.

**Time complexity**: if tree is balanced, O(nlogn), Worst case, O(n ^ 2).


**Solution**:

```java
  public boolean exist(TreeNode root, int target) {
    return exist(root, new ArrayList<Integer>(), target);
  }
  
  private boolean exist(TreeNode root, List<Integer> prefixList, int target) {
    if (root == null) {
      return false;
    }
    prefixList.add(root.key);
    if (checkSum(prefixList, target)) {
      return true;
    }
    if (exist(root.left, prefixList, target)) {
      return true;
    }
    prefixList.remove(prefixList.size() - 1);
    prefixList.add(root.key);
    if (exist(root.right, prefixList, target)) {
      return true;
    }
    prefixList.remove(prefixList.size() - 1);
    return false;
  }
  
  private boolean checkSum(List<Integer> prefixList, int target) {
    int sum = 0;
    for (int i = prefixList.size() - 1; i >= 0; i--) {
      sum += prefixList.get(i);
      if (sum == target) {
        return true;
      }
    }
    return false;
  }
```


<br>
<br>


###23 Common Elements in Three Sorted Array

>Find all common elements in 3 sorted arrays.

```java

  public List<Integer> common(int[] a, int[] b, int[] c) {
    List<Integer> res = new ArrayList<Integer>();
    if (a == null || a.length == 0 || b == null || b.length == 0 || c == null || c.length == 0) {
      return res;
    }
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < a.length && j < b.length && k < c.length) {
      if (a[i] == b[j] && a[i] == c[k]) {
        res.add(a[i++]);
        j++;
        k++;
      } else if (a[i] < b[j]) {
        i++;
      } else if (b[j] < c[k]) {
        j++;
      } else {
        k++;
      }
    }
    return res;
  }
```

<br>
<br>

###24 Kth smallest number in sorted matrix

<pre>
int[][] matrix = { {1,  3,   5,   7},
                   {2,  4,   8,   9},
                   {3,  5,   11,  15},
                   {6,  8,   13,  18} };

</pre>
**Idea**: Dijkstra algorithm

**Time**: O(klogk)

```java
  class Node {
    int val;
    int row;
    int col;
    public Node(int row, int col, int val) {
      this.val = val;
      this.row = row;
      this.col = col;
    }
  }
  public int kthSmallest(int[][] matrix, int k) {
    PriorityQueue<Node> queue = new PriorityQueue<Node>(10, new Comparator<Node>() {
      public int compare(Node e1, Node e2) {
        return e1.val - e2.val;
      }
    });
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    queue.offer(new Node(0, 0, matrix[0][0]));
    visited[0][0] = true;
    while (true) {
      Node temp = queue.poll();
      k--;
      if (k == 0) {
        return temp.val;
      }
      if (temp.row < matrix.length - 1 && !visited[temp.row + 1][temp.col]) {
        queue.offer(new Node(temp.row + 1, temp.col, matrix[temp.row + 1][temp.col]));
        visited[temp.row + 1][temp.col] = true;
      }
      if (temp.col < matrix[0].length - 1 && !visited[temp.row][temp.col + 1]) {
        queue.offer(new Node(temp.row, temp.col + 1, matrix[temp.row][temp.col + 1]));
        visited[temp.row][temp.col + 1] = true;
      }
    }
  }
```



###25 String Abbreviation Matching

>Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, return if the string matches the abbreviation.

>Assumptions:

>The original string only contains alphabetic characters.
Examples:

>pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.

**Idea**:

Recursion: if pattern[i] is char, then compare patter[i] and pattern[j]
otherwise, recursion to match(pattern, input, i + num's length, j + num's value);

**Solution**:


```java
  public boolean match(String input, String pattern) {
    if (input == null || pattern == null) {
      return false;
    }
    return match(input.toCharArray(), pattern, 0, 0);
  }
  private boolean match(char[] input, String pattern, int ii, int pi) {
    if (ii == input.length && pi == pattern.length()) {
      return true;
    }
    if (ii >= input.length || pi >= pattern.length()) {
      return false;
    }
    if (!isNum(pattern.charAt(pi))) {
      return input[ii] == pattern.charAt(pi) ? match(input, pattern, ii + 1, pi + 1) : false;
    } else {
      int temp = pi;
      while (pi < pattern.length() && isNum(pattern.charAt(pi))) {
        pi++;
      }
      int count = Integer.parseInt(pattern.substring(temp, pi));
      return match(input, pattern, ii + count, pi);
    }
  }
  private boolean isNum(char c) {
    return c >= '0' && c <= '9';
  }

```


<br>

<br>

###26 Permutation Index

> Given a permutation which contains no repeated number, find its index in all the permutations of these numbers, which are ordered in lexicographical order. The index begins at 1.

**Idea**: Refer to 60 Permutation Sequence


**Solution**:

```java
 public long permutationIndex(int[] A) {
    if(A == null || A.length == 0) {
        return 0;
    }
    long res = 1;
    long factor = 1;
    for (int i = A.length - 1; i >= 0; i--) {
        int count = 0;
        for (int j = i + 1; j < A.length; j++) {
            if (A[i] > A[j]) {
                count++;
            }
        }
        res += count * factor;
        factor *= (A.length - i);
    }
    return res;
}
```

<br>
<br>


###27 Permutation Index II

>Given a permutation which may contain repeated numbers,
find its index in all the permutations of these numbers,
which are ordered in lexicographical order. The index begins at 1.
>
>Example
Given the permutation [1, 4, 2, 2], return 3.

**Idea**:
In this problem, we need to handle duplicates. If there is one duplicate, the factor reduces to factor/2

**Revised Solution**: Move map out of for loop. Time is better.

```java
  public long permutationIndexII(int[] A) {
    if (A == null || A.length == 0) {
        return 0;
    }
    long res = 1;
    long factor = 1;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = A.length - 1; i >= 0; i--) {
        int count = 0;
        Integer val = map.get(A[i]);
        if (val == null) {
            map.put(A[i], 1);
        } else {
            map.put(A[i], val + 1);
        }
        for (int j = i + 1; j < A.length; j++) {
            if (A[i] > A[j]) {
                count++;
            }
        }
        res += count * factor / duplicatesFactor(map);
        factor *= (A.length - i);
    }
    return res;
}
private long duplicatesFactor(Map<Integer, Integer> map) {
    long res = 1;
    for (int val : map.values()) {
        res *= getFactor(val);
    }
    return res;
}
private long getFactor(int num) {
    long res = 1;
    for (int i = 1; i <= num; i++) {
        res *= i;
    }
    return res;
}
```

**First try**:

```java
    public long permutationIndexII(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        long res = 1;
        long factor = 1;
        for (int i = A.length - 1; i >= 0; i--) {
            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            map.put(A[i], 1);
            for (int j = i + 1; j < A.length; j++) {
                Integer val = map.get(A[j]);
                if (val == null) {
                    map.put(A[j], 1);
                } else {
                    map.put(A[j], val + 1);
                }
                if (A[i] > A[j]) {
                    count++;
                }
            }
            res += count * factor / duplicatesFactor(map);
            factor *= (A.length - i);
        }
        return res;
    }
    private long duplicatesFactor(Map<Integer, Integer> map) {
        long res = 1;
        for (int val : map.values()) {
            res *= getFactor(val);
        }
        return res;
    }
    private long getFactor(int num) {
        long res = 1;
        for (int i = 1; i <= num; i++) {
            res *= i;
        }
        return res;
    }
```

Initiate factor array, thus we don't need to calculate it each time.

```java
public long permutationIndexII(int[] A) {
    if (A == null || A.length == 0) {
        return 0;
    }
    long res = 1;
    long factor = 1;
    long[] factorsArr = getFactors(A.length);
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = A.length - 1; i >= 0; i--) {
        int count = 0;
        Integer val = map.get(A[i]);
        if (val == null) {
            map.put(A[i], 1);
        } else {
            map.put(A[i], val + 1);
        }
        for (int j = i + 1; j < A.length; j++) {
            if (A[i] > A[j]) {
                count++;
            }
        }
        res += count * factor / duplicatesFactor(map, factorsArr);
        factor *= (A.length - i);
    }
    return res;
}
private long duplicatesFactor(Map<Integer, Integer> map, long[] factorsArr) {
    long res = 1;
    for (int val : map.values()) {
        res *= factorsArr[val];
    }
    return res;
}

private long[] getFactors(int num) {
    long[] res = new long[num + 1];
    long temp = 1;
    for (int i = 1; i <= num; i++) {
        temp *= i;
        res[i] = temp;
    }
    return res;
}

```

<br>
<br>
###28 SellTicket

>N ticket windows, ith window has ai tickets available.
>The price of a ticket is equal to the number of tikcets remaining in that window at that time.
>Waht is the maximum amount of money the railway station can earn from selling the first m tickets.

<pre>
Input n m
a1 a2 ...an

output 
S

sample input 
2 4
2 5
sample out
14

</pre>


**Solution 1**: Priority queue

Time : m*lgn

```java
public long sellTicket1(int[] arr, long m) {
    long res = 0;
    PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer el1, Integer el2) {
                return el2 - el1;
            }
    });
    for (int num : arr) {
        queue.offer(num);
    }
    long tickets = m;
    while (tickets > 0) {
        int count = queue.poll();
        res += count;
        queue.offer(count - 1);
        tickets--;
    }
    return res;
}
```

**Solution 2**:  Find x so that when sells all ai >= x, sum (x + 1) < m < sum(x)

```java
public  long sellTicket(int[] arr, long m) {
    long res = 0;
    int threshold = 0;
    int l = 0;
    int r = 0;
    for (int num : arr) {
        r = Math.max(num, r);
    }
    while (l <= r) {
        threshold = l + (r - l) / 2;
        long sum1 = getSum(threshold, arr);
        long sum2 = getSum(threshold + 1, arr);
        if (sum2 <= m && m <= sum1) {
            break;
        } else if (sum1 < m) {
            r = threshold;
        } else {
            l = threshold;
        }
    }
    long tickets = 0;
    for (int num : arr) {
        if (num >= threshold) {
            tickets += num - threshold + 1;
            res += ((long) (threshold + num) * (long) (num - threshold + 1)) / 2;
        }
    }
    res -= (tickets - m) * threshold;
    return res;
} 

private long getSum(int threshold, int[] arr) {
    long res = 0;
    for (int num : arr) {
        res += num - threshold >= 0 ? num - threshold + 1 : 0;
    }
    return res;
}
```


<br>

<br>

###29 Uneaten leaves


>K caterpillars are eating their way through N leaves, each caterpillar falls from leaf to leaf in a unique sequence, all caterpillars start at a twig at position 0 and falls onto the leaves at position between 1 and N. Each caterpillar j has as associated jump number Aj. A caterpillar with jump number j eats leaves at positions that are multiple of j. It will proceed in the order j, 2j, 3j…. till it reaches the end of the leaves and it stops and build its cocoon. Given a set A of K elements K<-15, N<=10^9, we need to determine the number of uneaten leaves.


**Brute force**: O(kn)

```java
public int countUneatenLeaves(int[] jumpNumber, int numberLeaves) {
    int catArraySize = jumpNumber.length;
    int countEaten = 0;
    Map<Integer,Integer> mapPosition = new HashMap<>();
    for (int i = 0; i < catArraySize; i++){
        int catervalue = jumpNumber[i];
        for (int j = 1; j * catervalue <= numberLeaves; j++){
            if (!mapPosition.containsKey(jumpNumber[i] * j)) {
                mapPosition.put(jumpNumber[i] * j, 1);
                countEaten++;
            }
        }
    }
    return numberLeaves - countEaten;
}
}
```

**Inclusive and exclusive**: k * 2 ^ k

For example if caterpillars : [2, 4, 5];

|S| = |S2| + |S4| + |S5| - |S24| - |S25| - |S45| + |S245|

http://www.iarcs.org.in/inoi/contests/sep2004/Advanced-2-solution.php


<br>
<br>

###30 Longest Chain
eg a, abcd, bcd, abd, cd, c)： each time we can delete a char to go to the next word of the chain.
longest chain:  abcd-- bcd -- cd -- c, thus we should return 4.

Revised.

```java
public int longest_chain(String[] w) {
    Set<String> dict = new HashSet<>();
    Map<String, Integer> map = new HashMap<>();
    for (String s : w) {
        dict.add(s);
    }
    int res = 0;
    for (String s : w) {
        res = Math.max(res, getChainLen(s, dict, map));
    }
    return res;
}

private int getChainLen(String s, Set<String> dict, Map<String, Integer> map) {
    if (map.containsKey(s)) {
        return map.get(s);
    }
    int res = 1;
    for (int i = 0; i < s.length(); i++) {
        StringBuilder sb = new StringBuilder(s);
        String next = sb.deleteCharAt(i).toString();
        if (dict.contains(next)) {
            res = Math.max(res, getChainLen(next, dict, map) + 1);
        }
    }
    map.put(s, res);
    return res;
}
```

First try.

```java
 public int longest_chain(String[] w) {
    Set<String> dict = new HashSet<>();
    Map<String, Integer> map = new HashMap<>();
    for (String s : w) {
        dict.add(s);
    }
    int res = 0;
    for (String s : w) {
        int length = getChainLen(s, dict, map);
        map.put(s, length);
        res = Math.max(res, length);
    }
    return res;
}

private int getChainLen(String s, Set<String> dict, Map<String, Integer> map) {
    for (int i = 0; i < s.length(); i++) {
        StringBuilder sb = new StringBuilder(s);
        sb.deleteCharAt(i);
        String next = sb.toString();
        if (dict.contains(next)) {
            if (map.containsKey(next)) {
                return map.get(next) + 1;
            }
            return getChainLen(next, dict, map) + 1;
        }
    }
    return 1;
}

```

<br>
<br>

###31 Friend Circle

>Problem Statement
>
>There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature, i.e., if A is friend of B and B is friend of C, then A is also friend of C. A friend circle is a group of students who are directly or indirectly friends.
>
>You are given a N×N−matrix M which consists of characters Y or N. If M[i][j]=Y, then ith and jth students are friends with each other, otherwise not. You have to print the total number of friend circles in the class.
>
>Input Format 
>First line of the input contains an integer N - (size of the matrix), followed by N lines each having N characters.
>
>Output Format 
>Print the maximum number of friend circles.
>
>Constraints 
>1≤N≤300 
>Each element of matrix friends will be Y or N. 
>Number of rows and columns will be equal in the matrix.

>M[i][i]=Y, where 0≤i<N 
>M[i][j] = M[j][i], where 0<=i<j<N

**Solution 1**: flood

```java
 static int friendCircles(String[] friends) {
    int res = 0;
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < friends.length; i++) {
        if (friends[i].contains("Y")) {
            res++;
            friends[i] = replaceY(friends[i], queue);
            while (!queue.isEmpty()) {
                int next = queue.poll();
                friends[next] = replaceY(friends[next], queue);
            }
        }
    }
    return res;
}

static String replaceY(String row, Queue<Integer> queue) {
    int index = 0;
    while ((index = row.indexOf("Y")) != -1) {
        queue.offer(index);
        row = row.replaceFirst("Y", "N");
    }
    return row;
}
```


**Union find set**: http://www.geeksforgeeks.org/union-find/


<br>
<br>

###32 Stock Max

[question source](https://www.hackerrank.com/challenges/stockmax)
<pre>

Your algorithms have become so good at predicting the market that you now know what the share price of Wooden Orange Toothpicks Inc. (WOT) will be for the next N days.

Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own, or not make any transaction at all. What is the maximum profit you can obtain with an optimum trading strategy?

Input

The first line contains the number of test cases T. T test cases follow:

The first line of each test case contains a number N. The next line contains N integers, denoting the predicted price of WOT shares for the next N days.

Output

Output T lines, containing the maximum profit which can be obtained for the corresponding test case.

Constraints

1 <= T <= 10 
1 <= N <= 50000

All share prices are between 1 and 100000

Sample Input

3
3
5 3 2
3
1 2 100
4
1 3 1 2
Sample Output

0
197
3
Explanation

For the first case, you cannot obtain any profit because the share price never rises. 
For the second case, you can buy one share on the first two days, and sell both of them on the third day. 
For the third case, you can buy one share on day 1, sell one on day 2, buy one share on day 3, and sell one share on day 4.

</pre>

**Idea** to get the max profit, we need to buy the stocks that has latter stocks with higher price than it.

For example: 1 3 1 5 9 : we need to buy 1 3 1 5 and sell at 9. Thus we can calculate the max profit from the end of the array.

Here is the solution:

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static long getProfit(int[] prices) {
        long res = 0;
        long max = Integer.MIN_VALUE;
        for (int i = prices.length - 1; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            res += (max - prices[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int n = in.nextInt();
            int[] prices = new int[n];
            for (int j = 0; j < n; j++) {
                prices[j] = in.nextInt();
            }
            System.out.println(getProfit(prices));
        }
    }
}

```



<br>
<br>

###33 Flipping bits

>You are given an integer array with N elements: d[0], d[1], ... d[N - 1]. 
>
>You can perform AT MOST one move on the array: choose any two integers [L, R], and flip all the elements between (and including) the L-th and R-th bits. L and R represent the left-most and right-most index of the bits marking the boundaries of the segment which you have decided to flip.
>
>What is the maximum number of '1'-bits (indicated by S) which you can obtain in the final bit-string? 

> Example: 1 0 0 1 0 0 1 0 Result : 6 . We can get a maximum of 6 ones in the given binary array by performing either of the following operations: Flip [1, 5] ==> 1 1 1 0 1 1 1 0

**Idea**: Same with maximum subarray, one dimensional dp.

```java
public int flipBits(int[] a) {
    int initial = 0; /* initial number of 1s*/
    int resIncreased = 0; /* Increased 1s */
    int temp = 0;
    for (int i : a) {
        if (i == 0) {
            temp++;
        } else {
            initial++;
            temp--;
        }
        temp = Math.max(0, temp);
        resIncreased = Math.max(temp, resIncreased);
    }
    return initial + resIncreased;
}
```

<br>
<br>

###34 Hamming distance

>Given int array, calcaulate sum of hamming distance of all pairs in O(n) time.

**Idea**:

For each digit, we find the count of 0s and 1s, then multiply. The final result is the sum of multiply result of 32 bits.


<br>
<br>

###35 Find a pair with given sum in a Balanced BST

>Given a Balanced Binary Search Tree and a target sum, write a function that returns true if there is a pair with sum equals to target sum, otherwise return false. **Expected time complexity is O(n) and only O(Logn)** extra space can be used. Any modification to Binary Search Tree is not allowed. Note that height of a Balanced BST is always O(Logn).

http://www.geeksforgeeks.org/find-a-pair-with-given-sum-in-bst/

**Idea**: traverse the binary tree both inorder and reverse-inorder. 
- If current-inorder + current-reverse == sum, return true;
- If current-inorder + current-reverse < sum, we continue visit the next element in inorder;
- otherwise we visit the next element in reverse-inorder.

```java
public boolean findPair(TreeNode root, int target) {
    Deque<TreeNode> stackInorder = new LinkedList<>();
    Deque<TreeNode> stackReverse = new LinkedList<>();
    TreeNode p1 = root;
    TreeNode p2 = root;
    boolean turn1 = true;
    boolean turn2 = true;
    int val1 = 0;
    int val2 = 0;
    while (true) {
        while (turn1 && (p1 != null || !stackInorder.isEmpty())) {
            if (p1 != null) {
                stackInorder.push(p1);
                p1 = p1.left;
            } else {
                p1 = stackInorder.pop();
                val1 = p1.key;
                p1 = p1.right;
                turn1 = false;
            }
        }
        while (turn2 && (p2 != null || !stackReverse.isEmpty())) {
            if (p2 != null) {
                stackReverse.push(p2);
                p2 = p2.right;
            } else {
                p2 = stackReverse.pop();
                val2 = p2.key;
                p2 = p2.left;
                turn2 = false;
            }
        }
        if (val1 != val2 && val1 + val2 == target) {
            return true;
        } else if (val1 + val2 < target) {
            turn1 = true;
        } else {
            turn2 = true;
        }
        if (val1 >= val2) {
            return false;
        }
    }
}
```
<br>
<br>

###36 Sliding window maximum

>Given array A, sliding window size of w moving from left to the right. Return array B, B[i] is the maximum number in the window. For example, given [1, 3, 2, 5, 8, 9, 4, 7, 3], window size k = 3, return [3, 5, 8, 9, 9, 9, 7, 7, 3]

**Idea**: Deque, when new element enters, check from the right of the deque, if it smaller than the new element, remove it from the queue. After all smaller elements are removed, we insert the new element to the deque.

Here to maintain the size of sliding window, we store index in the deque other than the real value.

```java
public int[] slidingMaximum(int[] A, int w) {
    Deque<Integer> deque = new LinkedList<>();
    for (int i = 0; i < w; i++) {
        while (!deque.isEmpty() && A[deque.peekLast()] <= A[i]) {
            deque.pollLast();
        }
        deque.offerLast(i);
    }
    int[] res = new int[A.length];
    for (int i = w; i < A.length; i++) {
        res[i - w] = A[deque.peekFirst()];
        while (!deque.isEmpty() && A[deque.peekLast()] <= A[i]) {
            deque.pollLast();
        }
        while (!deque.isEmpty() && deque.peekFirst() <= i - w) {
            deque.pollFirst();
        }
        deque.offerLast(i);
    }
    for (int i = w; i > 0; i--) {
        res[A.length - i] = A[deque.peekFirst()];
        if (deque.peekFirst() <= (A.length - i)) {
            deque.pollFirst();
        }
    }
    return res;
}
```

<br>
<br>

###37 Put Chair

>Given a gym with k pieces of equipment and some obstacles.  ‘E’ denotes a cell with equipment, ‘O’ denotes a cell with an obstacle, ' ' denotes a cell without any equipment or obstacle. Put a chair in gym such that distance sum from chair to all equipments is minimum.
>
>Assumptions
>
>There is at least one equipment in the gym
>The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
If there does not exist such place to put the chair, just return null (Java) empty vector (C++)
Examples
>
>{ {'E', 'O', ' '},
>
>  {' ', 'E', ' '},
>
>  {' ', ' ',' ' }}
>
>we should put the chair at (1, 0), so that the sum of cost from the chair to the two equipments is 1 + 1 = 2, which is minimal.

**Idea**:

- With brute force, we can try each empty cell in the gym, and find the minimum cost position. Time : O(n ^ 2 * E * log V) = O(n ^ 2 * n ^ 2 * log n)

- If the equipments is less compare with the total cells n^2, we can run dijkstra to find the cost from each equipments. Time : O(k * n ^2 * logn)


```java
public class Solution {
  class Cell {
    int x; 
    int y;
    public Cell(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  public List<Integer> putChair(char[][] gym) {
    // write your solution here
    assert(gym != null);
    int[][] cost = new int[gym.length][gym[0].length];
    for (int i = 0; i < gym.length; i++) {
      for (int j = 0; j < gym[0].length; j++) {
        if ('E' == gym[i][j]) {
          //can not find place to put chair.
          if (!bfs(gym, cost, i, j)) { 
            return null;
          }
        }
      }
    }
    return getPosition(gym, cost);
  }
  
  /*Dijkstra to calculate the cost from a equipment to other cells*/
  private boolean bfs(char[][] gym, int[][] cost, int x, int y) {
    boolean[][] visited = new boolean[gym.length][gym[0].length];
    Queue<Cell> queue = new LinkedList<>();
    queue.offer(new Cell(x, y));
    visited[x][y] = true;
    int c = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Cell current = queue.poll();
        cost[current.x][current.y] += c;
        for (Cell neighbor : getNeighbors(current, gym)) {
          if (!visited[neighbor.x][neighbor.y]) {
            queue.add(neighbor);
            visited[neighbor.x][neighbor.y] = true;
          }
        }
      }
      c++;
    }
    return checkValid(gym, visited);
  }
  
  /*Get valid neighbors of position (x, y)*/
  private List<Cell> getNeighbors(Cell current, char[][] gym) {
    List<Cell> res = new ArrayList<>();
    int[][] offsetArr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    for (int[] offset : offsetArr) {
      int x = current.x + offset[0];
      int y = current.y + offset[1];
      if (x < gym.length && x >= 0 && y < gym[0].length && y >= 0 && 'O' != gym[x][y]) {
        res.add(new Cell(x, y));
      }
    }
    return res;
  }

  /*check if we can reach to all equipments.*/
  private boolean checkValid(char[][] gym, boolean[][] visited) {
    for (int i = 0; i < gym.length; i++) {
      for (int j = 0; j < gym[0].length; j++) {
        if ('E' == gym[i][j] && !visited[i][j]) {
          return false;
        }
      }
    }
    return true;
  }
  
  /*Get position of chair with min cost*/
  private List<Integer> getPosition(char[][] gym, int[][] cost) {
    List<Integer> res = null;
    for (int i = 0; i < gym.length; i++) {
      for (int j = 0; j < gym[0].length; j++) {
        if (' ' == gym[i][j]) {
          if (null == res) {
            res = new ArrayList<>();
            res.add(i);
            res.add(j);
          } else {
            if (cost[i][j] < cost[res.get(0)][res.get(1)]) {
              res.set(0, i);
              res.set(1, j);
            }
          }
        }
      }
    }
    return res;
  }
  
}

```

###38 Post Office Problem


>Problem: [http://www.lintcode.com/en/problem/post-office-problem/](http://www.lintcode.com/en/problem/post-office-problem/)

Given n houses, pick k positions to build k post office so that the sum distance of each house to the nearest post office is the smallest.

**Idea**: Use dynamic Programming.

dp[i][j] represents the minimum cost of having i post offices in houses 0 - j

Induction rule : dp[i][j] = Math.min(dp[i][j], dp[i - 1][k - 1] + cost of having one post office between k - j)

It's easy to calculat the cost of 1 post office between [k, j]


```java
public class Solution {
    /**
     * @param A an integer array
     * @param k an integer
     * @return an integer
     */
    public int postOffice(int[] A, int k) {
        // Write your code here
        if (A.length == 0 || A.length <= k) {
            return 0;
        }
        Arrays.sort(A);
        int[][] costOne = costOfOnePostOffice(A);
        int[][] cost = new int[k + 1][A.length + 1];
        //initiate build one post office
        for (int i = 1; i <= A.length; i++) {
            cost[1][i] = costOne[1][i];
        }
        for (int i = 2; i <= k; i++) {
            for (int j = i; j <= A.length; j++) {
                cost[i][j] = Integer.MAX_VALUE;
                for (int m = 0; m < j; m++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i - 1][m] + costOne[m + 1][j]);
                }
            }
        }
        return cost[k][A.length];
    }
    private int[][] costOfOnePostOffice(int[] A) {
        int[][] res = new int[A.length + 1][A.length + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = i + 1; j <= A.length; j++) {
                int mid = i + (j - i) / 2; //build in the middle of all houses.
                for (int k = i; k <= j; k++) {
                    res[i][j] += Math.abs(A[k - 1] - A[mid - 1]);
                }
            }
        }
        return res;
    }
}

```
<br>
<br>

###39 Upvotes

>https://www.hackerrank.com/contests/quora-haqathon/challenges/upvotes/submissions/code/4149844

**Idea**: Maintain the non-decreasing and non-increasing subranges of a window size of K, then moving window by one element each time. Update the first range and last range of the non-decreasing and non-increasing list.

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 class Pair {
        int start;
        int end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
    public List<Long> upvotes(int[] arr, int k) {
        List<List<Pair>> votes = getVotes(arr, k);
        List<Pair> nonDesc = votes.get(0);
        List<Pair> nonInc = votes.get(1);
        List<Long> res = new ArrayList<>();
        if (k <= 1) {
            for (int i = 0; i < arr.length; i++) {
                res.add(0l);
            }
            return res;
        }
        long inc = 0;
        for (Pair p : nonDesc) {
            inc += getSubrange(p);
        }
        long dec = 0;
        for (Pair p : nonInc) {
            dec += getSubrange(p);
        }
        res.add(inc - dec);
        for (int i = k; i < arr.length; i++) {
            if (nonDesc.size() > 0 && nonDesc.get(0).start == i - k) {
                if (nonDesc.get(0).end - nonDesc.get(0).start > 1) {
                    inc -= nonDesc.get(0).end - nonDesc.get(0).start;
                    nonDesc.get(0).start++;
                } else {
                    nonDesc.remove(0);
                    inc -= 1;
                }
            } 
            if (nonInc.size() > 0 && nonInc.get(0).start == i - k) {
                if (nonInc.get(0).end - nonInc.get(0).start > 1) {
                    dec -= nonInc.get(0).end - nonInc.get(0).start;
                    nonInc.get(0).start = i - k + 1;
                } else {
                    nonInc.remove(0);
                    dec -= 1;
                }
            }
            if (arr[i] >= arr[i - 1]) {
                if (nonDesc.size() > 0 && nonDesc.get(nonDesc.size() - 1).end == i - 1) {
                    nonDesc.get(nonDesc.size() - 1).end++;
                    inc += nonDesc.get(nonDesc.size() - 1).end - nonDesc.get(nonDesc.size() - 1).start;
                } else {
                    nonDesc.add(new Pair(i - 1, i));
                    inc += 1;
                }
            } 
            if (arr[i] <= arr[i - 1]){
                if (nonInc.size() > 0 && nonInc.get(nonInc.size() - 1).end == i - 1) {
                    nonInc.get(nonInc.size() - 1).end++;
                    dec += nonInc.get(nonInc.size() - 1).end - nonInc.get(nonInc.size() - 1).start;
                } else {
                    nonInc.add(new Pair(i - 1, i));
                    dec += 1;
                }
            }
            res.add(inc - dec);
        }
        return res;
    }

    /**Get non-increasing and non-deceasing subranges in the first window**/
    public List<List<Pair>> getVotes(int[] arr, int k) {
        List<Pair> nonDesc = new ArrayList<>();
        List<Pair> nonInc = new ArrayList<>();
        for (int i = 0; i < k - 1; i++) {
            if (arr[i] >= arr[i + 1]) {
                if (nonInc.size() > 0 && nonInc.get(nonInc.size() - 1).end == i) {
                    nonInc.get(nonInc.size() - 1).end++;
                } else {
                    nonInc.add(new Pair(i, i + 1));
                }
            }
            if (arr[i] <= arr[i + 1]){
                 if (nonDesc.size() > 0 && nonDesc.get(nonDesc.size() - 1).end == i) {
                     nonDesc.get(nonDesc.size() - 1).end++;
                 } else {
                     nonDesc.add(new Pair(i, i + 1));
                 }
             }
        }
        List<List<Pair>> res = new ArrayList<>();
        res.add(nonDesc);
        res.add(nonInc);
        return res;
    }

    private long getSubrange(Pair p) {
        long len = p.end - p.start;
        return ((1 + len) * len) / 2;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Solution s = new Solution();
        List<Long> res = s.upvotes(arr, k);
        for (long i : res) {
            System.out.println(i);
        }
    }
}
```

<br>
<br>

###40 Partition Sum

> Check if a given set can be partitioned into two subsets such that the sum of elements in both subsets is same.


**Brute force**: Recursively check if we can find a subset with sum (total sum) / 2. 


```java
    public boolean findPartition(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        return canPartition(arr, sum / 2, 0);
    }
    private boolean canPartition(int[] arr, int sum, int index) {
        if (sum == 0) {
            return true;
        }
        if (index == arr.length && sum != 0) {
            return false;
        }
        return canPartition(arr, sum - arr[index], index + 1) ||
               canPartition(arr, sum, index + 1);
    }
```

**Dp solution**:

```java
public boolean findPartition(int[] arr) {
    int sum = 0;
    for (int i : arr) {
        sum += i;
    }
    if (sum % 2 != 0) {
        return false;
    }
    boolean[][] canPartition = new boolean[sum / 2 + 1][arr.length + 1];
    for (int i = 0; i <= arr.length; i++) {
        canPartition[0][i] = true;
    }
    for (int i = 1; i <= sum / 2; i++) {
        for (int j = 1; j <= arr.length; j++) {
            canPartition[i][j] |= canPartition[i][j - 1];
            if (i >= arr[j - 1]) {
                canPartition[i][j] |= canPartition[i - arr[j - 1]][j -1];
            }
        }
    }
    return canPartition[sum / 2][arr.length];
}
```

<br>
<br>

###41 Lexicographic paths

[https://www.hackerrank.com/contests/w9/challenges/lexicographic-steps](https://www.hackerrank.com/contests/w9/challenges/lexicographic-steps)

>Krishnakant is standing at (0,0) in the Cartesian plane. He wants to go to the point (x,y) in the same plane using only horizontal and vertical moves of 1 unit. There are many ways of doing this, and he is writing down all such ways. Each way comprises of few H moves and few V moves. i.e. moves in horizontal and vertical direction respectively. For example, if Krishnakant wants to go to point (2,2) from point (0,0), HVHV is one of the possible ways.


Given the value of K, he wants to know lexicographically Kth smallest way of going to (x,y) from (0,0).

<pre>

Input Format 
The first line contains an integer T , i.e., number of test cases. 
Next T lines will contain integers x,y and K.

Output Format 
For each test case, print lexicographically Kth smallest path.

Constraints 
1≤T≤100000 
1≤x≤10 
1≤y≤10 
0≤K< number of paths

Sample Input

2
2 2 2
2 2 3
Sample Output

HVVH
VHHV
Explanation

All the paths of going to (2,2) from (0,0) in lexicographically increasing order:


0.HHVV
1.HVHV
2.HVVH
3.VHHV
4.VHVH
5.VVHH

</pre>

**Idea**: For input (m, n), there are (m + n)! / (m! * n!) paths. 

- If k + 1 > (m - 1 + n)! / ((m - 1)! * n!), then the first letter should be V, otherwise, the first letter is H.

- Recursively adding m + n characters.


```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void kthPath(int steps, int m, int n, long k, StringBuilder s, int index) {
        if (index == steps) {
            return;
        }
        long factor = getFactor(m - 1, n);
        if (factor < k) {
            kthPath(steps, m, n - 1, k - factor, s.append("V"), index + 1);
        } else {
            if (m > 0) {
                kthPath(steps, m - 1, n, k, s.append("H"), index + 1);
            } else {
                kthPath(steps, m, n - 1, k, s.append("V"), index + 1);
            }
        }
    }

    private static long getFactor(int m, int n) {
        int small = m < n ? m : n;
        int large = small == m ? n : m;
        long num1 = 1;
        long num2 = 1;
        for (int i = 1; i <= small; i++) {
            num1 *= i;
            num2 *= (small + large - i + 1);
        }
        return num2 / num1;
    }
 
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int m = in.nextInt();
            int n = in.nextInt();
            int k = in.nextInt();
            StringBuilder s = new StringBuilder();
            kthPath(m + n, m, n, k + 1, s, 0);
            System.out.println(s);
        }
    }
}
```
<br>
<br>

###42 Power of 4

> Check if a long n is power of 4.

**Idea**:

Let's see some example of power of 4:

- ..00001 1  4^0
- ..00100 4  4^1
- 0010000 16 4^2
- 1000000 64 4^3

From the above examples, we found that num has only one set bit. Also, the set bit should appear at even index. 0, 2, 4,.....

The solution below first check if num is power of 2 by n & (n - 1). If num is power of 2 then n & (n - 1) should be 0

Then (n & 0x5555555555555555l) != 0 make sure that there is a even index set bit.


```java
public boolean powerOf4(long n) {
    if (n <= 0 || (n & (n - 1)) != 0) {
        return false;
    }
    return (n & 0x5555555555555555l) != 0;
}

```

**Another naive solution**: Iteratively check if n % 4 == 0 and change n = n /4 to next iteration.


<br>

<br>

###43 Mod five iterator

Give random number iterator, implement iterator that is multiple of 5.

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class RandomIterator {
    private Iterator<Integer> iterator;
    RandomIterator(List<Integer> lst) {
        iterator = lst.iterator();
    }
    public Integer next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
}

public class ModFiveIterator extends RandomIterator {

    private Integer next;

    private boolean updateFlag = true;

    ModFiveIterator(List<Integer> lst) {
        super(lst);
    }

    public Integer next() {
        if (hasNext()) {
            updateFlag = true;
            return next;
        }
        return null;
    }

    public boolean hasNext() {
        if (!super.hasNext()) {
            return false;
        }
        if (updateFlag) {
            next = super.next();
            while (super.hasNext() && next % 5 != 0) {
                next = super.next();
            }
            updateFlag = false;
        }
        return  next % 5 == 0;
    }
}
```

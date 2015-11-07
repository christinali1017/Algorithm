####Linear Sort algorithm:

- **Count sort** 
 - Time O(n + k), k is the maximum value of elements in array.
 - use O(n) to initianize the counting array
 - O(k) to calculate the prefix sum on the count array
 - O(n) to calculate the output array.

- **Bucket sort**
 - Best Time O(n + k), Average time O(n + k) Worst case (n ^ 2) if all elements are in the same bucket. k is the maximum value of elements in array.
 - use O(n) to initianize the buckets
 - O(k) to merge buckets


- **Radix sort **
 - worst case time complexity: O(w * n), w is the integer's word size.





####Stable Sort algorithm:

Maintain the relative order of the records with equal keys.

Stable sort:

- Merge sort
- Insertion sort
- Bubble sort
- Bucket sort

Unstable:

- Selection sort
- heap sort
- quick sort (typical in-place sort is not stable, stable versions exist)

[https://en.wikipedia.org/wiki/Sorting_algorithm](https://en.wikipedia.org/wiki/Sorting_algorithm)







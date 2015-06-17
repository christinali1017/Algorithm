package leetcode;

public class SearchA2DMatrix2 {
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
	 
	 public boolean searchMatrix1(int[][] matrix, int target) {
		 return helper(matrix, target, 0, matrix[0].length - 1, 0, matrix.length - 1);
	 }
	 
	 /* l r u b stands for left, right, top, bottom.*/ 
	 public boolean helper(int[][] matrix, int target, int l, int r, int t, int b) {
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
	 
	 
	 public boolean searchMatrix2(int[][] matrix, int target) {
		 return helper(matrix, target, 0, matrix[0].length - 1, 0, matrix.length - 1);
	 }
	 
	 /* l r u b stands for left, right, top, bottom.*/ 
	 public boolean searchMatrix2(int[][] matrix, int target, int l, int r, int t, int b) {
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
	 public static void main(String[] args) {
		int[][] matrix = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
		SearchA2DMatrix2 s = new SearchA2DMatrix2();
		System.out.println(s.searchMatrix(matrix, 6));
		System.out.println(s.searchMatrix(matrix, 2));
		System.out.println(s.searchMatrix(matrix, 10));
		System.out.println(s.searchMatrix(matrix, 0));
		System.out.println(s.searchMatrix2(matrix, 6));
		System.out.println(s.searchMatrix2(matrix, 2));
		System.out.println(s.searchMatrix2(matrix, 9));
		System.out.println(s.searchMatrix2(matrix, 0));
		System.out.println(s.searchMatrix2(matrix, 11));
		System.out.println(s.searchMatrix2(matrix, 12));
	 }
	 

}

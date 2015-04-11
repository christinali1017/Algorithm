package leetcode;

public class SearchA2DMatrix {
	/* binary search */
	public boolean searchMatrix1(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0) return false;
		int l = 0;
		int r = matrix.length -1;
		int row = 0;
		while( l <= r){
			int mid = (l+r)/2;
			if(matrix[mid][0] == target) return true;
			else if(matrix[mid][0] < target) l = mid + 1;
			else r = mid -1;
		}
		row = r;
		if(row < 0 || row > matrix.length-1) return false;
		l = 0;
		r = matrix[0].length-1;
		while(l <= r){
			int mid = (l + r)/2;
			if(matrix[row][mid] == target) return true;
			else if(matrix[row][mid] < target) l = mid + 1;
			else r = mid -1; 
		}
		return false;
	}
	/* search from the top right element */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        for(int i = 0; i < matrix.length; i++){
            for(int j = matrix[0].length-1; j >= 0; j--){
                if(matrix[i][j] == target) return true;
                else if(matrix[i][j] < target) break;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
    	SearchA2DMatrix s = new SearchA2DMatrix();
    	int[][] matrix = {
    	                  {1,   3,  5,  7},
    	                  {10, 11, 16, 20},
    	                  {23, 30, 34, 50}
    	                };
    	//int[][] matrix1 = {{1}};
    	System.out.println(s.searchMatrix1(matrix, 0));
	}
}

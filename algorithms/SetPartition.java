

public class SetPartition {
	/* Given a set, check if it can be partitioned to two equal sets */
	public boolean setPatition(int[] A){
		if(A == null || A.length == 0) return false;
		int sum = 0;
		for(int i = 0; i < A.length; i++){
			sum += A[i];
		}
		if(sum % 2 != 0) return false;
		for(int i = 0; i < A.length; i++){
			if(A[i] > sum/2) return false;
		}
		
		/* helper[i][j] = true if A[0] A[1] ... A[j-1] has sum equal to i */
		boolean[][] helper = new boolean[sum/2+1][A.length+1];
		
		for(int i = 0; i <= A.length; i++){
			helper[0][i] = true;
		}
		for(int i = 1; i <= sum/2; i++){
			helper[i][0] = false;
		}
		for(int i = 1; i <= sum/2; i++){
			for(int j = 1; j <= A.length; j++){
				if(helper[i][j-1] == true) helper[i][j] = true;
				if(i >= A[j-1]){
					helper[i][j] = helper[i][j] ||  helper[i - A[j-1]][j-1];
				}
			}
		}
		return helper[sum/2][A.length];
	}
	
	public boolean setPatition1(int[] A){
		if(A == null || A.length == 0) return false;
		int sum = 0;
		for(int i = 0; i < A.length; i++){
			sum += A[i];
		}
		if(sum % 2 != 0) return false;
		for(int i = 0; i < A.length; i++){
			if(A[i] > sum/2) return false;
		}
		boolean[] helper = new boolean[sum+1];
		for(int i = 0; i < A.length; i++){
			for(int j = sum/2; j>= 0; j--){
				if(j == A[i]) helper[j] = true;
				if(j>= A[i] && helper[j-A[i]] == true) helper[j] = true;
			}
		}
		return helper[sum/2];
	}
	
	public static void main(String[] args) {
		SetPartition s = new SetPartition();
		int[] A = {4,4, 2 , 8, 3, 3};
		System.out.println(s.setPatition(A));
		System.out.println(s.setPatition1(A));
	}

}

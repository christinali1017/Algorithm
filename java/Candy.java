package leetcode;
public class Candy {
	
    public static int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0) return 0;
        int[] left = new int[ratings.length];
        left[0] = 1;
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] > ratings[i-1]) left[i] = left[i-1] +1;
            else left[i] = 1;
        }
        int res = left[ratings.length-1];
        for(int i = ratings.length - 2; i >= 0; i--){
            int right = 1;
            if(ratings[i] > ratings[i+1]){
                right = left[i+1] + 1;
            }
            res += Math.max(right, left[i]);
            left[i] = right;
        }
        return res;
    }
    
    public static int candy1(int[] ratings) {
        if(ratings == null || ratings.length == 0) return 0;
        int[] left = new int[ratings.length];
        left[0] = 1;
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] > ratings[i-1]) left[i] = left[i-1] +1;
            else left[i] = 1;
        }
        int[] right = new int[ratings.length];
        right[ratings.length - 1] = 1;
        for(int i = ratings.length - 2; i >= 0; i--){
             if(ratings[i] > ratings[i+1]){
                right[i] = right[i+1] + 1;
            }else right[i] = 1;
        }
        int res = 0;
        for(int i = 0; i < ratings.length; i++){
            res += Math.max(left[i], right[i]);
        }
        return res;
    }
    
    public static int candy2(int[] ratings) {
    	if(ratings == null || ratings.length == 0) return 0;
    	
    	int preRating = ratings[0],
    		count = 1,
    		preCandy = 1,
    		lastIncreasingIndex = 0,
    		lastDecreasingIndex = 0,
    		lastIncreasingCandy = 1,
    		changeFlag = 0;
    	for(int i = 1, len = ratings.length; i < len; i++){
    		if(ratings[i] >= preRating){
    			if(changeFlag == 1){				
    				if(ratings[i] == preRating){
        				/*if increasing sequence is longer than deceasing sequence */
        				if(lastIncreasingCandy > lastDecreasingIndex - lastIncreasingIndex +1){
        					count += sumTools(lastDecreasingIndex - lastIncreasingIndex);
        				}else{
            				count -= lastIncreasingCandy;
            				count += sumTools(lastDecreasingIndex - lastIncreasingIndex+1);
        				}
    					count += 1;
    					preCandy = 1;
    				}else{
        				/*if increasing sequence is longer than deceasing sequence */
        				if(lastIncreasingCandy > lastDecreasingIndex - lastIncreasingIndex +1){
        					count += sumTools(lastDecreasingIndex - lastIncreasingIndex);
        				}else{
            				count -= lastIncreasingCandy;
            				count += sumTools(lastDecreasingIndex - lastIncreasingIndex +1);
        				}
    					count += 2;
        				preCandy = 2;
    				}
    				changeFlag = 0;
        			lastIncreasingIndex = i;
        			lastIncreasingCandy = preCandy;
        			preRating = ratings[i];
    				continue;
    			}else{
    				if(ratings[i] == preRating){
    					preCandy = 1;
    				}else{
    					preCandy++;
    				}
        			count += preCandy;
        			preRating = ratings[i];
        			lastIncreasingIndex = i;
        			lastIncreasingCandy = preCandy;
    			}
    			
    		}else{
    			lastDecreasingIndex = i;
    			preRating = ratings[i];
    			changeFlag = 1;
    			continue;
    		}
    	}
    	
    	if(changeFlag == 1){
    		if(lastIncreasingCandy > lastDecreasingIndex - lastIncreasingIndex +1){
				count += sumTools(lastDecreasingIndex - lastIncreasingIndex);
			}else{
				count -= lastIncreasingCandy;
				count += sumTools(lastDecreasingIndex - lastIncreasingIndex +1);
			} 
    	}
        return count;
    }
    
    public static int sumTools(int n){
    	int sum = 0;
    	for(int i = 1; i <= n; i++ ){
    		sum += i;
    	}
    	return sum;
    }
    public static void main(String[] args) {
		int[] ratings = {-1, 0, 5, 4, 3, 1, 2};
		int[] ratings1 = {1, 2, 3, 5, 4, 3, 2, 1, 0, -1, -2, 4, 6, 8, 1, 3};
		int[] ratings2 = {1, 2, 3, 3,4,5,5,1,2,2};
		int[] ratings3 = {5, 4, 3, 2, 1};
		int[] ratings4 = {5, 4, 3, 2, 1, 2};
		int[] ratings5 = {5,5, 6, 5, 5, 4};
		int[] ratings6 = {1,2, 2, 3, 3, 4, 2, 2, 1, 1};
		int[] ratings7 = {58,21,72,77,48,9,38,71,68,77,82,47,25,94,89,54,26,54,54,99,64,71,76,63,81,82,60,64,29,51,87,87,72,12,16,20,21,54,43,41,83,77,41,61,72,82,15,50,36,69,49,53,92,77,16,73,12,28,37,41,79,25,80,3,37,48,23,10,55,19,51,38,96,92,99,68,75,14,18,63,35,19,68,28,49,36,53,61,64,91,2,43,68,34,46,57,82,22,67,89};
		System.out.println(candy(ratings));
		System.out.println(candy(ratings1));
		System.out.println(candy(ratings2));
		System.out.println(candy(ratings3));
		System.out.println(candy(ratings4));
		System.out.println(candy(ratings5));
		System.out.println(candy(ratings6));
		System.out.println(candy(ratings7));
	}
}

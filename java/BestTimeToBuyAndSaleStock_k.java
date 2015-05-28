package leetcode;

public class BestTimeToBuyAndSaleStock_k {
   public int maxProfit(int[] prices) {
	   if(prices == null || prices.length == 0 || prices.length == 1) return 0;
	   return maxProfitKTransactions(prices,2);
	   
   }
   
   public int maxProfitKTransactions(int[] prices, int k){
	   int len = prices.length;
	   int[][] temp = new int[len][k+1];
	   int[][] max = new int[len][k+1];
	   for(int i = 1; i < len; i++){
		   int profit = prices[i] - prices[i-1];
		   for(int j = 1; j <= k; j++){
			   /* temp[i-1][j] + profit indicates that jth transaction sale at day i, so we can not max(0, profit)*/
			   temp[i][j] = Math.max(max[i-1][j-1] + Math.max(0, profit), temp[i-1][j] + profit);
			   max[i][j] = Math.max(max[i-1][j], temp[i][j]);
		   }
	   }
	   return max[len-1][k];
   }
   
   public static void main(String[] args) {
	 int[] prices = {2, 1, 10, 2, 20, 10, 50, -1, 20};
	 BestTimeToBuyAndSaleStock_k stock = new BestTimeToBuyAndSaleStock_k();
	 System.out.println(stock.maxProfit(prices));
   }

}

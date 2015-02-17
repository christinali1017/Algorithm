package leetcode;

public class BestTimeToBuyAndSaleStock_1 {
	/* solution 1: record a local min stock price and global max profit */
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
   
	/* solution 1: record a local max profit and a global max profit */
   public int maxProfit1(int[] prices){
		if(prices == null || prices.length == 0 || prices.length == 1) return 0;
		int local = 0;
		int global = 0;
		for(int i = 0; i < prices.length-1; i++){
			local = Math.max(local + prices[i+1] -prices[i], 0);
			global = Math.max(local,  global);
		}
		return global;
	}

   
   public static void main(String[] args) {
	 int[] prices = {1, 2};
	 BestTimeToBuyAndSaleStock_1 stock = new BestTimeToBuyAndSaleStock_1();
	 System.out.println(stock.maxProfit(prices));
	 System.out.println(stock.maxProfit1(prices));
   }

}

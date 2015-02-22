package leetcode;

public class BestTimeToBuyAndSaleStock_2 {
	public int maxProfit(int[] prices){
		if(prices == null || prices.length <= 1) return 0;
		int max = 0;
		int dif = 0;
		for(int i = 1; i < prices.length; i++){
			dif = Math.max(0, prices[i] - prices[i-1]);
			max = max + dif;
		}
		return max;
	}
   
   public static void main(String[] args) {
	 int[] prices = {2, 1, 2, 1, 3, 2,3, 4, 5, 6, 7, 8, 6, 5, 4, 3, 2, 3, 1, 1};
	 BestTimeToBuyAndSaleStock_2 stock = new BestTimeToBuyAndSaleStock_2();
	 System.out.println(stock.maxProfit(prices));
   }

}

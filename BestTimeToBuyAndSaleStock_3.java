package leetcode;
public class BestTimeToBuyAndSaleStock_3 {	
	/* Solution 1 : The same method with calculate double increasing sequence, Start at one point i, calculate the single max profit before i and after i. Use two arrays to store the max profits*/	
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
	
	
	/*solution 2 */
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
	
	
   public static void main(String[] args) {
	 int[] prices = {2, 1, 10, 2, 20, 10, 50, -1, 20};
	 BestTimeToBuyAndSaleStock_3 stock = new BestTimeToBuyAndSaleStock_3();
	 System.out.println(stock.maxProfit(prices));
	 System.out.println(stock.maxProfit1(prices));
   }

}

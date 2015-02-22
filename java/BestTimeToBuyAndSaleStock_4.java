package leetcode;

public class BestTimeToBuyAndSaleStock_4 {
	public int maxProfit(int k, int[] prices){
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
		BestTimeToBuyAndSaleStock_4 b = new BestTimeToBuyAndSaleStock_4();
		int[] prices = {106,373,495,46,359,919,906,440,783,583,784,73,238,701,972,308,165,774,990};
		System.out.println(b.maxProfit(6, prices));
	}

}

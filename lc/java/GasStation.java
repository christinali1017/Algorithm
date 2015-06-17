package leetcode;

/*
 *  There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

	You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
	
	Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 */

public class GasStation {
	/* brute force */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
    	/* initial boundary check*/
    	if(gas.length == 0) return -1;
    	int sumGas = 0,
    		sumCost = 0;
    	for(int i = 0, len = gas.length; i < len; i++) sumGas += gas[i];
    	for(int i = 0, len = cost.length; i < len; i++) sumCost += cost[i];
    	if(sumGas < sumCost) return -1;
    	
    	int preRemain = 0;
    	for(int i = 0, len = cost.length; i < len; i++){
    		if(cost[i] > gas[i] + preRemain) continue;
    		preRemain = gas[i] - cost[i] + preRemain;
    		int j = 0; 
    		for(j = i+1; j < len; j++){
    			if(cost[j] > gas[j] + preRemain) break;
    			preRemain = gas[j] + preRemain - cost[j];
    		}

    		if(j != len) continue;
    		for(j = 0; j < i; j++){
    			if(cost[j] > gas[j] + preRemain) break;
    			preRemain = gas[j] + preRemain - cost[j];
    		}

    		if(j == i) return i;
    	}
    	
        return -1;
    }
    
    public static void main(String[] args) {
		int[] cost = {1, 2, 3, 4, 5};
		int[] gas = {7, 2, 3, 2, 0};
		System.out.println(canCompleteCircuit(gas, cost));
	}
}

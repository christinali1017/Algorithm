package leetcode;

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0){
        	return 1;
        }
        int m = dungeon.length -1;
        int n = dungeon[0].length -1;
        
        /* if dungeon[m][n] < 0, then it will lose power. If dungeon >= 0, it must has at least one power.*/
        dungeon[m][n] = (1 - dungeon[m][n]) <= 0 ? 1 : (1 - dungeon[m][n]);
        
        /* initialize the last column */
        for(int i = m-1; i >= 0; i--){
        	int cost = dungeon[i+1][n] - dungeon[i][n];
        	dungeon[i][n] = cost <= 0 ? 1 : cost;
        }
        
        /*initialize the last row */
        for(int i = n-1; i >= 0; i--){
        	int cost = dungeon[m][i+1] - dungeon[m][i];
        	dungeon[m][i] = cost <= 0 ? 1 : cost;
        }
        
        for(int i = m-1; i >= 0; i--){
        	for(int j = n-1; j >= 0; j--){
        		int cost = Math.min(dungeon[i+1][j],dungeon[i][j+1])-dungeon[i][j];
        		dungeon[i][j] = cost <= 0 ? 1 : cost;
        	}
        }
        

        return dungeon[0][0];
        
    }
    
    
    
    
    public static void main(String[] args) {
		int[][] dungeon = {{-2, -3, 3},{-5, -10, 1},{10, 30, -5}};
		DungeonGame d = new DungeonGame();
		System.out.println(d.calculateMinimumHP(dungeon));
	}
}

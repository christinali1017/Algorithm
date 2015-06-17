package leetcode;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1' && visited[i][j] == false){
                    dfs(visited, grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    
    public void dfs(boolean[][] visited, char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return;
        if(visited[i][j] == true || grid[i][j] == '0')
            return;
        visited[i][j] = true;
        dfs(visited, grid, i-1, j);
        dfs(visited, grid, i, j-1);
        dfs(visited, grid, i+1, j);
        dfs(visited, grid, i, j+1);
    }
}

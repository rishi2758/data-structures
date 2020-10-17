package dp;

public class EditDistance
{
    //convert s1->s2 using opeartion insert , delete or replace a character at a time ,these operations to be done optimally to minimize the number of operations required for this transformation to occurr.
    public int editMinimumDistance(String s1, String s2) {

        int m = s1.length()+1;
        int n = s2.length()+1;
        
        int[][] dp = new int[m][n];
        
        for(int row = 0 ; row <= m;row++) {
            for (int col = 0; col <= n; col++) {
                if(row == 0)
                    dp[row][col] = col;
                if(col == 0)
                    dp[row][col] = row;
                
                if(s1.charAt(row-1) == s2.charAt(col-1)) {
                    dp[row][col] = dp[row-1][col-1];
                }else {
                    dp[row][col] = Math.min(Math.min(dp[row][col-1], dp[row-1][col]),dp[row-1][col-1]);
                }
                
            }
        }
        return dp[m][n];
    }
    
    public static void main(String[] args)
    {
        
    }
    
    
}

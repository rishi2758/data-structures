package dp;

class Pair {
    int e1;
    int e2;
    
    public Pair(int e1,int e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
}

public class LongestChain
{
    
    /**
     * 
     * {
     *          [1,2] [2,3] [3,4]
     *  [1,2]     0     1     1  
     *  [2,3]     0     1     2  
     *  [3,4]     0     1     2   
     * }
     * 
     * 
     * {
     *          [5,24] [39,60] [15,28] [27,40] [50,90]
     *  [5,24]    0       1       1       1       1  
     *  [39,60]   0       0       0       1       1 
     *  [15,28]   1           
     *  [27,40] 
     *  [50,90]
     * 
     * }
     * 
     * 
     * */
    
    public static int getLongestChainLength(int[][] pairs) {
        
        if(pairs == null || pairs.length == 0 || pairs[0].length == 0) {
            return -1;
        }
        Pair[] pairNodes = new Pair[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            pairNodes[i] = new Pair(pairs[i][0], pairs[i][1]);
        }
        int[][] dp = new int[pairNodes.length][pairNodes.length];
        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[0].length; col++) {
               if(pairNodes[row].e1 < pairNodes[col].e2) {
                  
               }      
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args)
    {
        int[][] pairs = {{1,2},{2,3},{3,4}};
        getLongestChainLength(pairs);
    }
    
    
}

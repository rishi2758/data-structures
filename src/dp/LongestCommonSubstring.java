package dp;

import java.util.Arrays;

public class LongestCommonSubstring
{

    public int getLCS(String s1, String s2)
    {
        return _getLCS(s1, s2, s1.length(), s2.length());
    }

   /**
    *    *   a   a   b   b   d
    * *  0   0   0   0   0   0   
    * b  0   0   0   1   1   0
    * b  0   0   0   1   2   0
    * <p>
    * */

    private int _getLCS(String s1, String s2, int m, int n)
    {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], 0);
        }
        int count= 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    count = Math.max(count, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        //System.out.println("Longest Common SubString"+printLCS(s1,s2,dp));
        return count;
    }
    
    
    private String printLCS(String s1, String s2,int[][] dp)
    {
        int i = s1.length();
        int j = s2.length();
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if(s1.charAt(i-1) == s2.charAt(j-1)) {
                sb.append(s1.charAt(i));
                --i;
                --j;
            }else {
                if(dp[i-1][j] > dp[i][j-1]) {
                    sb.append(s2.charAt(j-1));
                    --i;
                }else {
                    sb.append(s1.charAt(i-1));
                    --j;
                }
            }
        }
        return sb.toString();
    }

    private int _getLCS(String s1, String s2, int m, int n, int count)
    {

        // base case
        if (m == s1.length() || n == s2.length()) {
            return count;
        }
        // choices
        if (s1.charAt(m) == s2.charAt(n)) {
            count = _getLCS(s1, s2, m + 1, n + 1, count + 1);
        }
        return Math.max(count, Math.max(_getLCS(s1, s2, m + 1, n, 0), _getLCS(s1, s2, m, n + 1, 0)));
    }

    public static void main(String[] args)
    {
        int lcs = new LongestCommonSubstring().getLCS("aabbd", "aabd");
        System.out.println("LCS -> " + lcs);
    }

}

package dp;

public class LongestPalindromeSubstring
{

    /**
     * input : "babad" dabab abcdfgdcba abcdgfdcba
     * 
     *      #   c   b   b   d
     *   #  0   0   0   0   0
     *   d  0   0   0   0   1
     *   b  0   0   1   1   0
     *   b  0   0   1   2   0
     *   c  0   1   0   0   0
     * 
     **/

    public String longestPalindrome(String s)
    {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        String s1 = new StringBuilder(s).reverse().toString();
        int max = 0;
        StringBuilder res = null;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else if (s1.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if (max < dp[i][j]) {
                        String canBe = s1.substring(i - dp[i][j], dp[i][j]);
                        System.out.println(canBe);
                        StringBuilder sb = new StringBuilder(canBe);
                        if (sb.reverse().toString().equals(canBe)) {
                            max = dp[i][j];
                            res = new StringBuilder(canBe);
                        }
                    }
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args)
    {
        String s = "cbbd";
        String res = new LongestPalindromeSubstring().longestPalindrome(s);
        System.out.println("longest palindromic substring :"+res);
    }
}

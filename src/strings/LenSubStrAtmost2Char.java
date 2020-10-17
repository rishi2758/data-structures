package strings;

import java.util.HashSet;

public class LenSubStrAtmost2Char
{

    public int lengthOfLongestSubstringTwoDistinct(String s)
    {
        if (s.isEmpty()) {
            return -1;
        }
        int[] preprocess = new int[s.length()];
        preprocess[0] = 1;
        HashSet<Character> distinct = new HashSet<>();
        distinct.add(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if(distinct.add(s.charAt(i))) {
                preprocess[i] = preprocess[i-1]+1;
            }else {
                preprocess[i] = preprocess[i-1];
            }
        }
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int currLen = 1;
            for (int j = i + 1; j < s.length(); j++) {
                int distinctCount = preprocess[j]-preprocess[i];
                if(distinctCount <= 1) {
                    ++currLen;
                    maxLen = Math.max(maxLen, currLen);
                }else {
                    break;
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args)
    {
        String input = "ccaabbb";
        int longSubStr = new LenSubStrAtmost2Char().lengthOfLongestSubstringTwoDistinct(input);
        System.out.println("Longest SubString :"+longSubStr);
    }

}

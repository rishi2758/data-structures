package strings;

public class MaxVowel
{

    public int maxVowels(String s, int k)
    {
        if (s.length() == 0) {
            return -1;
        }

        int start = 0;
        int end = 0;
        int maxVowel = 0;
        int currVowel = 0;
        int resStart = 0;
        int resEnd = 0;
        while (end - start < k && end < s.length()) {
            if (isVowel(s.charAt(end))) {
                ++currVowel;
            }
            if (end - start + 1 == k) {
                if (maxVowel < currVowel) {
                    maxVowel = currVowel;
                    resStart = start;
                    resEnd = end;
                }
                if (isVowel(s.charAt(start))) {
                    --currVowel;
                }
                ++start;
            }
            ++end;
        }
        System.out.println("Max Vowel Substring "+s.substring(resStart, resEnd+1));
        return maxVowel;

    }

    private boolean isVowel(char c)
    {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    public static void main(String[] args)
    {
        String s = "abciiidef";
        int k = 3;

        int maxVowel = new MaxVowel().maxVowels(s, k);
        System.out.println(maxVowel);
    }

}

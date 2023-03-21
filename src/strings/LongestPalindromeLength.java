package strings;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindromeLength {

    public static class Palindrome {
        public static boolean is(String word) {
            return (word.charAt(0) == word.charAt(1));
        }

        public static String of(String word) {
            StringBuilder sb = new StringBuilder(word);
            return sb.reverse().toString();
        }
    }

    public void longestPalindrome(String[] words) {
        Map<String, Integer> freqWords = new HashMap<>();
        for (String word : words) {
            freqWords.put(word, freqWords.getOrDefault(word, 0) + 1);
        }
        int length = 0;
        for (String word : words) {
            if (Palindrome.is(word)) {
                length += (2 * freqWords.get(word));
                freqWords.remove(word);
            } else {
                String pWord = Palindrome.of(word);
                if (freqWords.containsKey(pWord)) {
                    int f = freqWords.get(word);
                    int fp = freqWords.get(pWord);
                    length += (4 * Math.min(f, fp));
                    if (f == fp) {
                        freqWords.remove(word);
                        freqWords.remove(pWord);
                    } else if (f > fp) {
                        freqWords.put(word, f - fp);
                    } else {
                        freqWords.put(pWord, fp - f);
                    }
                } else {
                    freqWords.remove(word);
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"lc","cl","gg"};
        new LongestPalindromeLength().longestPalindrome(words);

    }

}

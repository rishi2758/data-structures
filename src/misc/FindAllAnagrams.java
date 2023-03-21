package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagrams {

	public void findAnagrams(String s, String p) {

		int[] pmap = new int[26];
		Arrays.fill(pmap, -1);

		int size = 0;
		for (Character c : p.toCharArray()) {
			if (pmap[c - 'a'] == -1) {
				pmap[c - 'a'] = 1;
				++size;
			} else {
				pmap[c - 'a'] += 1;
			}
		}

		List<Integer> ans = new ArrayList<>();
		int start = 0;
		int matchedChars = 0;
		for (int end = 0; end < s.length(); end++) {
			int rightCharIdx = s.charAt(end) - 'a';
			if (pmap[rightCharIdx] != -1) {
				pmap[rightCharIdx] -= 1;
			}
			if (pmap[rightCharIdx] == 0) {
				++matchedChars;
			}

			if (size == matchedChars) {
				ans.add(start);
			}

			if (end - start + 1 >= p.length()) {
				int leftCharIdx = s.charAt(start++) - 'a';
				if (pmap[leftCharIdx] == 0) {
					--matchedChars;
				}
				if (pmap[leftCharIdx] != -1) {
					pmap[leftCharIdx] += 1;
				}
			}
		}
    }

	public static void main(String[] args) {

		new FindAllAnagrams().findAnagrams("cbaebabacd", "abc");
	}

}

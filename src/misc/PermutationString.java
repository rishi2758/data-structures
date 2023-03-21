package misc;

import java.util.HashMap;
import java.util.Map.Entry;

@SuppressWarnings("LoopConditionNotUpdatedInsideLoop")
public class PermutationString {

	@SuppressWarnings("LoopConditionNotUpdatedInsideLoop")
	public boolean checkInclusion(String s1, String s2) {

		HashMap<Character, Integer> s1Map = new HashMap<>();

		for (Character c : s1.toCharArray()) {

			int freq = 1;
			if (s1Map.containsKey(c)) {
				freq += s1Map.get(c);
			}

			s1Map.put(c, freq);
		}

		int windowLen = s1.length();
		int front = 0;
		int back = 0;

		while (front < s2.length()) {

			Character c = s2.charAt(front);

			HashMap<Character, Integer> s2Map = new HashMap<>();

			while (front - back + 1 < windowLen) {

				if (s1Map.containsKey(c)) {
					int freq = 1;
					if (s2Map.containsKey(c)) {
						freq += s2Map.get(c);
					}

					s2Map.put(c, freq);
				}

			}

			if (s1Map.size() == s2Map.size()) {
				for (Entry<Character, Integer> entry : s1Map.entrySet()) {
					if (!entry.getValue().equals(s2Map.get(entry.getKey()))) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

}
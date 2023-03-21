package misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MinStickers {

	public void minStickers(String[] stickers, String target) {
		Map<Character, Integer> tf = new HashMap<>();
		for (Character c : target.toCharArray()) {
			tf.put(c, tf.getOrDefault(c, 0) + 1);
		}
		int min = 0;
		for (String sticker : stickers) {
			Set<Character> set = new HashSet<>();
			for (Character c : sticker.toCharArray()) {
				set.add(c);
			}
			int max = 0;
			Iterator<Map.Entry<Character, Integer>> iterator = tf.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Character, Integer> entry = iterator.next();
				if (set.contains(entry.getKey())) {
					max = Math.max(entry.getValue(), max);
					iterator.remove();
				}
			}
			min += max;
			if (tf.isEmpty()) {
				break;
			}
		}
    }

	public static void main(String[] args) {
		String[] stickers = { "with", "example", "science" };
		new MinStickers().minStickers(stickers, "thehat");
	}

}

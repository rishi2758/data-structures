package misc;

import java.util.Arrays;
import java.util.Collections;

public class TandemCycle {

	public int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
		// Write your code here.
		// 9,5,5,3,2
		// 1,2,3,6,7

		Arrays.sort(redShirtSpeeds);
		blueShirtSpeeds = Arrays.stream(blueShirtSpeeds).boxed().sorted(Collections.reverseOrder())
				.mapToInt(Integer::intValue).toArray();
		int ans = fastest ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		for (int i = 0; i < redShirtSpeeds.length; i++) {
			if (fastest) {
				ans = Math.max(ans, Math.max(redShirtSpeeds[i], blueShirtSpeeds[i]));
			} else {
				ans = Math.min(ans, Math.min(redShirtSpeeds[i], blueShirtSpeeds[i]));
			}
		}
		return ans;
	}

	public static void main(String[] args) {

	}

}

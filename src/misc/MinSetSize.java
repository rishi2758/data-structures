package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinSetSize {

	public void minSetSize(int[] arr) {

		Map<Integer, Integer> freqMap = new HashMap<>();
		for (Integer a : arr) {
			freqMap.put(a, freqMap.getOrDefault(a, 0) + 1);
		}

		List<Integer> freq = new ArrayList<>(freqMap.values());
		freq.sort(Collections.reverseOrder());
		System.out.println(freq);
		int minSize = Integer.MAX_VALUE;
		int start = 0;
		int end = 0;
		int totalFreq = 0;
		int n = arr.length;
		for (end = 0; end < freq.size(); end++) {
			totalFreq += freq.get(end);
			while (totalFreq >= n / 2) {
				minSize = Math.min(minSize, end - start + 1);
				totalFreq -= freq.get(start++);
			}
		}
    }

	public static void main(String[] args) {

		int[] arr = { 3, 3, 3, 3, 5, 5, 5, 2, 2, 7 };
		new MinSetSize().minSetSize(arr);

	}

}

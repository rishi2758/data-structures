package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {

	private final Map<Integer, String> intToRomanMapping;
	private final ArrayList<Integer> intRomanRange;
	private final Map<Integer, Integer> cache;

	public IntegerToRoman() {
		intToRomanMapping = new HashMap<>();
		intToRomanMapping.put(1, "I");
		intToRomanMapping.put(2, "II");
		intToRomanMapping.put(3, "III");
		intToRomanMapping.put(4, "IV");
		intToRomanMapping.put(5, "V");
		intToRomanMapping.put(9, "IX");
		intToRomanMapping.put(10, "X");
		intToRomanMapping.put(40, "XL");
		intToRomanMapping.put(50, "L");
		intToRomanMapping.put(90, "XC");
		intToRomanMapping.put(100, "C");
		intToRomanMapping.put(400, "CD");
		intToRomanMapping.put(500, "D");
		intToRomanMapping.put(900, "CM");
		intToRomanMapping.put(1000, "M");
		intRomanRange = new ArrayList<>(intToRomanMapping.keySet());
		Collections.sort(intRomanRange);
		cache = new HashMap<>();
	}

	public String intToRoman(int num) {
		if (intToRomanMapping.containsKey(num)) {
			return intToRomanMapping.get(num);
		}
		StringBuilder ans = new StringBuilder();
		while (num > 0) {
			int maxIntInRoman = intRomanRange.get(getMaxLower(num));
			int remainder = num % maxIntInRoman;
			appendWhole(num - remainder, maxIntInRoman, ans);
			num = remainder;
		}
		return ans.toString();
	}

	private void appendWhole(int num, int maxIntInRoman, StringBuilder ans) {
		while (num > 0 && num % maxIntInRoman == 0) {
			ans.append(intToRomanMapping.get(maxIntInRoman));
			num = num - maxIntInRoman;
		}
	}

	private int getMaxLower(int num) {

		if (cache.containsKey(num)) {
			return cache.get(num);
		}

		int low = 0;
		int high = intRomanRange.size() - 1;

		int ans = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (intRomanRange.get(mid) == num) {
				ans = mid;
				break;
			} else if (intRomanRange.get(mid) < num) {
				ans = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		cache.put(num, ans);
		return ans;
	}

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		System.out.println(" startTime " + startTime);

		IntegerToRoman itr = new IntegerToRoman();
		String[] res = new String[4000];
		int r = 0;
		for (int num = 1; num <= 3999; num++) {
			res[r++] = itr.intToRoman(num);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(" endTime " + endTime);
		System.out.println(" TotalTime " + (endTime - startTime));

		startTime = System.currentTimeMillis();
		for (int i = 0; i < 4000; i++) {
			System.out.println(res[i]);
		}
		endTime = System.currentTimeMillis();
		System.out.println(" endTime " + endTime);
		System.out.println("  Printing out result took  " + (endTime - startTime));
	}

}

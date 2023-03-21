package misc;

public class CountAndSay {

	public String countAndSay(int n) {
		String prev = "1";
		for (int i = 2; i <= n; i++) {
			prev = say(prev);
		}
		return prev;
	}

	private String say(String n) {
		int start = 0;
		StringBuilder ans = new StringBuilder();
		while (start < n.length()) {
			int count = 1;
			int end = start + 1;
			while (end < n.length()) {
				if (n.charAt(start) == n.charAt(end)) {
					++count;
					++end;
				} else {
					break;
				}
			}
			ans.append(count).append(n.charAt(start));
			start = end;
		}
		return ans.toString();
	}

	public static void main(String[] args) {
		CountAndSay cas = new CountAndSay();
		long startTimeInMs = System.currentTimeMillis();
		System.out.println("StartTime in ms " + startTimeInMs);
		for (int i = 1; i <= 30; i++) {
			System.out.println(cas.countAndSay(i));
		}
		long endTimeInMs = System.currentTimeMillis();
		System.out.println("EndTime in ms " + endTimeInMs);
		System.out.println("TotalTime taken in ms " + (endTimeInMs - startTimeInMs));
	}

}

package strings;

public class FindNeedleInHayStack {

	public static int strStr(String haystack, String needle) {
		//PatternFinder finder = new PatternFinder.FiniteAutomationFinder(needle);
		PatternFinder kmpFinder = new PatternFinder.KMPFinder(needle);
		return kmpFinder.indexOfPattern(haystack);
	}
	
	
	public static int strStrNaive(String haystack, String needle) {

		int windowLen = needle.length();
		int n = haystack.length();

		for (int start = 0; start < n; start++) {
			if (needle.charAt(0) == haystack.charAt(start)) {
				int end = start;
				int matchIdx = 0;
				while (matchIdx < windowLen && end < n) {
					if (needle.charAt(matchIdx) != haystack.charAt(end)) {
						break;
					}
					++matchIdx;
					++end;
				}
				if (end - start == windowLen) {
					return start;
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(FindNeedleInHayStack.strStr("mississipi", "issip"));
		System.out.println(FindNeedleInHayStack.strStr("leetcode", "leeto"));
		System.out.println(FindNeedleInHayStack.strStr("mississipi", "issi"));
		System.out.println(FindNeedleInHayStack.strStr("mississipi", "issp"));
		System.out.println(FindNeedleInHayStack.strStr("leetcode", "leet"));
		System.out.println(FindNeedleInHayStack.strStr("mississipi", "ipi"));
		System.out.println(FindNeedleInHayStack.strStr("leetcode", "lee"));
		System.out.println(FindNeedleInHayStack.strStr("leetcode", "ode"));
		System.out.println(FindNeedleInHayStack.strStr("leetcode", "e"));
	}

}

package strings;

import lombok.Getter;

@Getter
public abstract class PatternFinder {

	private final String pattern;
	private final int m;

	public PatternFinder(String pattern) {
		this.pattern = pattern;
		this.m = pattern.length();
	}

	public abstract int indexOfPattern(String text);

	public static class KMPFinder extends PatternFinder {

		public KMPFinder(String pattern) {
			super(pattern);
			initializelps();
		}

		private void initializelps() {
			String pattern = getPattern();
			int m = getM();
			// contains length of longest proper prefix which is also a suffix
			int[] lps = new int[m];
			lps[0] = 0;
			for (int i = 1, k = lps[i - 1]; i < m; i++) {
				while (k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
					k = lps[k - 1];
				}
				if (pattern.charAt(i) == pattern.charAt(k)) {
					lps[i] = k + 1;
					k = lps[i];
				}else {
					lps[i] = 0;
				}
			}
			for (int l : lps) {
				System.out.print(l + " ");
			}
			System.out.println();
		}

		@Override
		public int indexOfPattern(String text) {
			return 0;
		}
	}

	public static class FiniteAutomationFinder extends PatternFinder {

		private int[][] dfa;

		public FiniteAutomationFinder(String pattern) {
			super(pattern);
			initializeDfa();
		}

		private void initializeDfa() {
			int m = getPattern().length();
			dfa = new int[256][m];
			dfa[getPattern().charAt(0)][0] = 1;
			for (int x = 0, j = 1; j < m; j++) {
				for (int c = 0; c < 256; c++)
					dfa[c][j] = dfa[c][x];
				dfa[getPattern().charAt(j)][j] = j + 1;
				x = dfa[getPattern().charAt(j)][x];
			}
		}

		@Override
		public int indexOfPattern(String text) {
			if (text == null || text.isEmpty() || text.length() < getPattern().length()) {
				return -1;
			}
			int n = text.length();
			int i, j;
			for (i = 0, j = 0; i < n && j < getM(); i++) {
				j = dfa[text.charAt(i)][j];
			}
			if (j == getM())
				return i - getM(); // found
			return n;
		}
	}

}

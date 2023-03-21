package misc;

public class MinKnightStep {

	/**
     * (x,y) -> (x+1,y+2) -> (x-1,y+2) -> (x+1,y-2) -> (x-1,y-2)
     **/

	public void minStepToReachTarget(int[] knightPos, int[] targetPos, int n) {

		if (knightPos == null || targetPos == null || knightPos.length != 2 || targetPos.length != 2
				|| knightPos.length != targetPos.length) {
			return;
		}

		boolean[][] visited = new boolean[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				visited[i][j] = false;

		visited[knightPos[0]][knightPos[1]] = true;

        minStepToReachTargetUtil(knightPos[0], knightPos[1], targetPos[0], targetPos[1], n, visited);

    }

	private int minStepToReachTargetUtil(int srci, int srcj, int desi, int desj, int n, boolean[][] visited) {

		if (srci < 0 || srcj < 0 || srci >= n || srcj >= n) {
			return 0;
		}

		if (!visited[srci][srcj]) {
			visited[srci][srcj] = true;
		}

		if (srci == desi && srcj == desj) {
			return 1;
		}

		int minStep1 = minStepToReachTargetUtil(srci + 1, srcj + 2, desi, desj, n, visited);
		int minStep2 = minStepToReachTargetUtil(srci - 1, srcj + 2, desi, desj, n, visited);
		int minStep3 = minStepToReachTargetUtil(srci + 1, srcj - 2, desi, desj, n, visited);
		int minStep4 = minStepToReachTargetUtil(srci - 1, srcj - 2, desi, desj, n, visited);

		int minStep5 = minStepToReachTargetUtil(srci + 2, srcj - 1, desi, desj, n, visited);
		int minStep6 = minStepToReachTargetUtil(srci + 2, srcj + 1, desi, desj, n, visited);
		int minStep7 = minStepToReachTargetUtil(srci - 2, srcj + 1, desi, desj, n, visited);
		int minStep8 = minStepToReachTargetUtil(srci - 2, srcj - 1, desi, desj, n, visited);

		return Math.min(minStep1, Math.min(minStep2, Math.min(minStep3,
				Math.min(minStep4, Math.min(minStep5, Math.min(minStep6, Math.min(minStep7, minStep8)))))));

	}

	public static void main(String[] args) {
		int N = 30;
		int[] knightPos = { 1, 1 };
		int[] targetPos = { 30, 30 };
		new MinKnightStep().minStepToReachTarget(knightPos, targetPos, N);
	}

}

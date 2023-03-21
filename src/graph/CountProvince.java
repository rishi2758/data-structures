package graph;

import java.util.Arrays;

public class CountProvince {

	public void findCircleNum(int[][] isConnected) {
		int circle = 0;
		boolean[] visited = new boolean[isConnected.length];
		Arrays.fill(visited, false);
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				dfs(isConnected, visited, i);
				++circle;
			}
		}
    }

	private void dfs(int[][] isConnected, boolean[] visited, int source) {

		if (isConnected.length == 0) {
			return;
		}

		for (int i = 0; i < isConnected.length; i++) {
			if (isConnected[source][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(isConnected, visited, i);
			}
		}

	}

	public static void main(String[] args) {
		int[][] isConnected = { { 1, 1, 0, 0, 0 }, { 1, 1, 1, 0, 0 }, { 0, 1, 1, 0, 0 }, { 0, 0, 0, 1, 1 },
				{ 0, 0, 0, 1, 1 } };
		new CountProvince().findCircleNum(isConnected);
	}

}

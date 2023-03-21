package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BiPartiteGraph {

	public void isBipartite(int[][] graph) {

		if (graph == null || graph.length == 0) {
			return;
		}

		// TODO : Convert graph to adj Matrix then use adjMatrix for furthur processing.

		boolean[][] adjMatrix = new boolean[graph.length][];
		for (int i = 0; i < graph.length; i++) {
			adjMatrix[i] = new boolean[graph[i].length];
			Arrays.fill(adjMatrix[i], false);
			for (int j = 0; j < graph[i].length; j++) {
				for (int k = j + 1; k < graph[i].length - 1; k++) {
					adjMatrix[graph[i][j]][graph[i][k]] = true;
					adjMatrix[graph[i][k]][graph[i][j]] = true;
				}
			}
		}

		int[] inSet = new int[graph.length];
		Arrays.fill(inSet, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);

		while (!queue.isEmpty()) {
			int source = queue.poll();

			if (graph[source][source] == 1) {
				return;
			}

			for (int i = 0; i < graph.length; i++) {
				if (adjMatrix[source][i] && inSet[i] != -1) {
					inSet[i] = 1 - inSet[source];
					queue.add(i);
				} else if (adjMatrix[source][i] && inSet[i] == inSet[source]) {
					return;
				}
			}
		}
    }

	public static void main(String[] args) {

		int[][] matrix = { { 1, 2, 3 }, { 0, 2 }, { 0, 1, 3 }, { 0, 2 } };

		new BiPartiteGraph().isBipartite(matrix);

	}

}

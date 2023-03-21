package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import trees.SerializeandDesrializeTree.TreeNode;

public class BTreeColoringGame {

	private Map<Integer, List<Integer>> graph;
	private int reachableByY = 0;

	public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
		graph = new HashMap<>();
		createGraph(root);
		Set<Integer> visited = new HashSet<>();
		visited.add(x);
		for (Integer adjacentNode : graph.get(x)) {
			System.out.println("Adjacent Node " + adjacentNode);
			markVisited(adjacentNode, visited);
			++reachableByY;
			System.out.println("Count Reachable By Y " + reachableByY);
			if (reachableByY > 0 && n - reachableByY < reachableByY) {
				return true;
			}
			reachableByY = 0;
		}
		return false;
	}

	private void markVisited(int start, Set<Integer> visited) {
		visited.add(start);
		for (Integer adjacentNode : graph.get(start)) {
			if (!visited.contains(adjacentNode)) {
				++reachableByY;
				markVisited(adjacentNode, visited);
			}
		}
	}

	private void createGraph(TreeNode root) {
		if (root != null) {
			graph.put(root.val, graph.getOrDefault(root.val, new ArrayList<>()));
			if (root.left != null) {
				List<Integer> child = graph.getOrDefault(root.left.val, new ArrayList<>());
				child.add(root.val);
				graph.put(root.left.val, child);
				child = graph.get(root.val);
				child.add(root.left.val);
				graph.put(root.val, child);
			}
			if (root.right != null) {
				List<Integer> child = graph.getOrDefault(root.right.val, new ArrayList<>());
				child.add(root.val);
				graph.put(root.right.val, child);
				child = graph.get(root.val);
				child.add(root.right.val);
				graph.put(root.val, child);
			}
			createGraph(root.left);
			createGraph(root.right);
		}
	}

	public static void main(String[] args) {

		TreeNode root = new TreeNode(1);

		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		System.out.println(new BTreeColoringGame().btreeGameWinningMove(root, 3, 2));
	}

}

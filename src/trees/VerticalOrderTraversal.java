package trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import trees.SerializeandDesrializeTree.TreeNode;

public class VerticalOrderTraversal {

	private static class Node {
		final int level;
		final int value;

		public Node(int level, int value) {
			this.level = level;
			this.value = value;
		}
	}

	private final Comparator<Node> comparator = (n1, n2) -> {
		if (n1.level != n2.level) {
			return Integer.compare(n1.level, n2.level);
		} else {
			return Integer.compare(n1.value, n2.value);
		}
	};

	private Map<Integer, PriorityQueue<Node>> groupByDistance;

	public void verticalTraversal(TreeNode root) {
		groupByDistance = new TreeMap<>();
		groupByHorizontalDistance(root, 0, 0);
		List<List<Integer>> ans = new ArrayList<>();
		for (PriorityQueue<Node> group : groupByDistance.values()) {
			List<Integer> toAdd = new ArrayList<>();
			for (Node nodeInGroup : group) {
				toAdd.add(nodeInGroup.value);
			}
			ans.add(toAdd);
		}
	}

	private void groupByHorizontalDistance(TreeNode node, int level, int distance) {
		if (node == null) {
			return;
		}
		PriorityQueue<Node> group = groupByDistance.getOrDefault(distance, new PriorityQueue<>(comparator.reversed()));
		group.add(new Node(level, node.val));
		groupByDistance.put(distance, group);
		groupByHorizontalDistance(node.left, level + 1, distance - 1);
		groupByHorizontalDistance(node.right, level + 1, distance + 1);
	}

	public static void main(String[] args) {
		SerializeandDesrializeTree sdt = new SerializeandDesrializeTree();
		new VerticalOrderTraversal()
				.verticalTraversal(sdt.deserialize("0,10,1,#,#,2,4,3,5,#,#,6,#,7,9,8,#,#,#,#,11,#,#,12"));
	}
}

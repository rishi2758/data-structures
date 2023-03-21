package trees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import dsalgo.trees.SerializeandDesrializeTree.TreeNode;

public class InfectedBinaryTree {

	private Map<Integer, TreeNode> parentMapping;

	public void amountOfTime(TreeNode root, int start) {

		parentMapping = new HashMap<>();

		Queue<TreeNode> q = new LinkedList<>();
		TreeNode infectedNode = new TreeNode();
		populateParent(root, start, infectedNode);
		System.out.println(infectedNode.left);
		q.offer(infectedNode.left);
		int min = 0;
		Set<Integer> set = new HashSet<>();
		set.add(infectedNode.left.val);
		while (!q.isEmpty()) {
			int size = q.size();
			System.out.println("Size @" + size);
			for (int s = 0; s < size; s++) {
				TreeNode t = q.poll();
				TreeNode parent = parentMapping.getOrDefault(t.val, null);
				if (parent != null && !set.contains(parent.val)) {
					set.add(parent.val);
					q.offer(parent);
				}
				if (t.left != null && !set.contains(t.left.val)) {
					set.add(t.left.val);
					q.offer(t.left);
				}
				if (t.right != null && !set.contains(t.right.val)) {
					set.add(t.right.val);
					q.offer(t.right);
				}
			}
			++min;
		}
    }

	private TreeNode populateParent(TreeNode node, int start, TreeNode infectedNode) {

		if (node == null) {
			return node;
		}

		TreeNode lnode = populateParent(node.left, start, infectedNode);
		TreeNode rnode = populateParent(node.right, start, infectedNode);

		if (lnode != null) {
			parentMapping.put(lnode.val, node);
			if (lnode.val == start) {
				infectedNode.left = lnode;
			}

		}
		if (rnode != null) {
			parentMapping.put(rnode.val, node);
			if (rnode.val == start) {
				infectedNode.left = rnode;
			}
		}

		return node;

	}

	public static void main(String[] args) {

		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.right = new TreeNode(1);

		new InfectedBinaryTree().amountOfTime(root, 4);
	}

}

package trees;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDesrializeTree {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode() {

		}

		public TreeNode(int val) {
			this.val = val;
		}
	}

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		q.add(root);
		while (!q.isEmpty()) {
			int count = q.size();
			for (int i = 0; i < count; i++) {
				TreeNode node = q.poll();
				if (node == null) {
					sb.append("#");
				} else {
					sb.append(node.val);
					q.add(node.left);
					q.add(node.right);
				}
				sb.append(",");
			}
		}
		int c = sb.length();
		while (c-- >= 0 && (sb.charAt(c) == '#' || sb.charAt(c) == ','))
			;
		return sb.substring(0, c + 1);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] values = data.split(",");
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode root = new TreeNode(Integer.parseInt(values[0]));
		q.add(root);
		for (int i = 1; i < values.length; i++) {
			TreeNode parent = q.poll();
			if (!values[i].equals("#")) {
				TreeNode left = new TreeNode(Integer.parseInt(values[i]));
				parent.left = left;
				q.add(left);
			}
			if (!values[++i].equals("#")) {
				TreeNode right = new TreeNode(Integer.parseInt(values[i]));
				parent.right = right;
				q.add(right);
			}
		}
		return root;
	}

	/*
	 * private TreeNode _deserialize(String[] nodeValues, int currIdx) { if (currIdx
	 * >= nodeValues.length) { return null; } if (nodeValues[currIdx].equals("#")) {
	 * return null; } TreeNode currNode = new
	 * TreeNode(Integer.parseInt(nodeValues[currIdx])); currNode.left =
	 * _deserialize(nodeValues, 2 * currIdx + 1); currNode.right =
	 * _deserialize(nodeValues, 2 * currIdx + 2); return currNode;
	 *
	 * }
	 */

	public static void main(String[] args) {
		// [1,2,3,null,null,4,5]
		// [1,2,3,null,null,4,5,6,7]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		root.right.left.left = new TreeNode(6);
		root.right.left.right = new TreeNode(7);

		String serialisedTree = new SerializeandDesrializeTree().serialize(root);
		System.out.println(serialisedTree);
		TreeNode ans = new SerializeandDesrializeTree().deserialize("10,5,-3,3,2,#,11,3,-2,#,1");
		System.out.println(new SerializeandDesrializeTree().serialize(ans));
		System.out.println(ans);

	}

}

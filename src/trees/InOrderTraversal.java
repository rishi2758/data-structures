package trees;

import java.util.ArrayList;
import java.util.List;

import dsalgo.trees.SerializeandDesrializeTree.TreeNode;

public class InOrderTraversal {

	public List<Integer> inorderTraversal(TreeNode root) {

		if (root == null) {
			return new ArrayList<>();
		}

		List<Integer> ans = new ArrayList<>();
		List<Integer> leftInOrder = inorderTraversal(root.left);
		if (leftInOrder.size() != 0) {
			ans.addAll(leftInOrder);
		}
		ans.add(root.val);
		List<Integer> rightInOrder = inorderTraversal(root.right);
		if (rightInOrder.size() != 0) {
			ans.addAll(rightInOrder);
		}
		return ans;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);

		// root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(2);

		new InOrderTraversal().inorderTraversal(root).forEach(System.out::print);
	}

}

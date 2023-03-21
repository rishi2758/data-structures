package trees;

import dsalgo.trees.SerializeandDesrializeTree.TreeNode;

public class PathSumII {

	static class Counter {
		int count = 0;
	}

	public int pathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return 0;
		}
		return pathSumContainingRoot(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
	}

	private int pathSumContainingRoot(TreeNode root, int targetSum) {
		int count = 0;

		if (root == null) {
			return count;
		}

		if (targetSum == root.val) {
			++count;
		}

		count += pathSumContainingRoot(root.left, targetSum - root.val);
		count += pathSumContainingRoot(root.right, targetSum - root.val);

		return count;
	}

	public static void main(String[] args) {

		SerializeandDesrializeTree sdt = new SerializeandDesrializeTree();

		System.out.println(new PathSumII().pathSum(sdt.deserialize("10,5,-3,3,2,#,11,3,-2,#,1"), 8));
		System.out.println(new PathSumII().pathSum(sdt.deserialize("5,4,8,11,#,13,4,7,2,#,#,5,1"), 22));
	}

}

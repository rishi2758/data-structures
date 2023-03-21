package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartesianTree
{

    public TreeNode getCartesianTree(ArrayList<Integer> inorder)
    {
        if (inorder.isEmpty())
            return null;
        return construct(inorder, 0, inorder.size() - 1);
    }

    private TreeNode construct(ArrayList<Integer> inorder, int start, int end)
    {
        if (start == end) {
            return new TreeNode(inorder.get(start));
        }

        if (start < end) {
            int maxIndex = getMaxIndex(inorder, start, end); //logn
            TreeNode root = new TreeNode();
            root.data = inorder.get(maxIndex);
            root.left = construct(inorder, start, maxIndex - 1); //
            root.right = construct(inorder, maxIndex + 1, end);
            return root;
        }
        return null;
    }

    private int getMaxIndex(ArrayList<Integer> inorder, int start, int end)
    {
        if (start >= 0 && end < inorder.size()) {
            int maxIndex = start;
            int max = inorder.get(start);
            for (int integer = start + 1; integer <= end; integer++) {
                if (max < inorder.get(integer)) {
                    maxIndex = integer;
                    max = inorder.get(integer);
                }
            }
            return maxIndex;
        }

        return -1;
    }

    /**
     *          6
     *      5        4 
     *     3 2      1 0 
     * <p>
     *    [3 5 2 6 1 4 0]
     *
     */

    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(5);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(0);
        List<Integer> inorder = Arrays.asList(3, 5, 2, 6, 1, 4, 0);
        TreeNode rootAns = new CartesianTree().getCartesianTree(new ArrayList<>(inorder));
        System.out.println(rootAns);
    }

}

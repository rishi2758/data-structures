package trees;

public class LCARecursive implements LCA
{

    boolean aExists = false;

    boolean bExists = false;

    @Override
    public TreeNode getLCA(TreeNode root, int a, int b)
    {

        if (root == null)
            return root;
        TreeNode temp = null;
        if (root.data == a) {
            aExists = true;
            temp = root;
        }
        if (root.data == b) {
            bExists = true;
            temp = root;
        }

        TreeNode leftLca = getLCA(root.left, a, b);
        TreeNode rightLca = getLCA(root.right, a, b);

        if (temp != null) {
            return temp;
        }

        if (leftLca != null && rightLca != null) {
            return root;
        }

        return leftLca != null ?leftLca : rightLca;
    }

    public static void main(String[] args)
    {

    }

}

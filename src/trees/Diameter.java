package trees;

class TreeNode
{
    int data;

    TreeNode left;

    TreeNode right;

    public TreeNode()
    {

    }

    public TreeNode(int data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "TreeNode [data=" + data + ", left=" + left + ", right=" + right + "]";
    }
    
    
    
}

public class Diameter
{

    public int getDiameter(TreeNode node)
    {
        if (node == null) {
            return -1;
        }

        if (node.left == null && node.right == null) {
            return 0;
        }

        int leftH = getHeight(node.left);
        int rightH = getHeight(node.right);

        return leftH + rightH;
    }

    private int getHeight(TreeNode node)
    {
        if (node == null)
            return 0;

        int leftH = getHeight(node.left);
        int rightH = getHeight(node.right);

        return 1 + Math.max(leftH, rightH);
    }

    public static void main(String[] args)
    {
        TreeNode root = new TreeNode();
        root.left = new TreeNode();
        root.right = new TreeNode();
        root.left.left = new TreeNode();
        root.left.right = new TreeNode();

        System.out.println("Diameter :" + new Diameter().getDiameter(root));

    }

}

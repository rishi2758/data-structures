package trees;

public class SABst
{

    @SuppressWarnings("InnerClassMayBeStatic")
    static class TreeNodeAVL  {
        
        final int data;
        int height;
        TreeNodeAVL left;
        TreeNodeAVL right;
        
        public TreeNodeAVL (int data) {
            this.data = data;
            this.height = 0;
        }
    }
    
    public TreeNodeAVL sortedArrayToBST(final int[] a)
    {
        TreeNodeAVL root = null;
        for (int j : a) {
            insert(root, j);
        }
        return root;
    }

    private void insert(TreeNodeAVL root, int data)
    {
        if(root == null) {
            root = new TreeNodeAVL(data);
            return;
        }
        
        if(data < root.data) {
            insert(root.left, data);   
        }else if (data > root.data) {
            insert(root.right,data);
        }else {
            return;
        }
        
        root.height = 1+Math.max(getHeight(root.left), getHeight(root.right));
        int balance = getBalance(root);
        
        if(balance > 1) {
            
        }
            
        if(balance < -1) {
            
        }
        
    }

    private int getBalance (TreeNodeAVL node) {
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }
    
    private int getHeight (TreeNodeAVL node) {
        if(node == null)
            return 0;
        return node.height;
    }
    
    public static void main(String[] args)
    {

    }

}

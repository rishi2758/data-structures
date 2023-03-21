package trees;

import java.util.LinkedList;
import java.util.Queue;

class Node
{
    public int val;

    public Node left;

    public Node right;

    public Node next;

    public Node()
    {
    }

    public Node(int _val)
    {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next)
    {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    @Override
    public String toString()
    {
        return "Node [val=" + val + ", left=" + left + ", right=" + right + ", next=" + next + "]";
    }
    
}

public class NextPointers
{

    public void connect(Node root)
    {
        Queue<Node> enque = new LinkedList<>();
        enque.add(root);
        Node node = null;
        while (!enque.isEmpty()) {
            int n = enque.size();
            for (int count = 0; count < n; count++) {
                Node prev = node;
                node = enque.poll();
                if (count > 0) {
                    prev.next =  node;
                }
                if (node.left != null) {
                    enque.add(node.left);
                }
                if (node.right != null) {
                    enque.add(node.right);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right= new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right =  new Node(7);
        new NextPointers().connect(root);
        System.out.println(root);
    }

}

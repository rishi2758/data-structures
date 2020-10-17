package linkedlist;

class LLNode
{
    int data;

    LLNode next;
}

public class DeleteNNodeFromEndLL
{

    // 1 - > 2 -> 3 - > 4 -> null
    public void deleteEndNode(LLNode head, int n)
    {
        if (head == null) {
            return;
        }
        LLNode slowPtr = head;
        LLNode fastPtr = slowPtr;
        while (fastPtr != null && n > 0) {
            if(fastPtr.next == null && n+1 == 0) {
                return;
            }
            fastPtr = fastPtr.next;
            --n;
        }
        while (fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next;
        }
        slowPtr.next = slowPtr.next.next;
    }

    public static void main(String[] args)
    {

    }

}

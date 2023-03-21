package linkedlist;

import dsalgo.misc.PalindromeList;

public class sumDuplicatesSortedList {

    public static class ListNode {
        int val;
        ListNode next;
    }

    public ListNode sumup(ListNode head) {
        ListNode b = head;
        ListNode f = head.next;

        while (f != null) {

            while (f != null && b.val != f.val) {
                b = b.next;
                f = f.next;
            }

            int count = 1;
            while (f != null && b.val == f.val) {
                ++count;
                if (f.next != null && f.next.val != b.val) {
                    break;
                }
                f = f.next;
            }

            b.val = b.val * count;
            b.next = f.next;
        }

        return head;
    }


    public static void main(String[] args) {

    }

}

package linkedlist;

import linkedlist.sumDuplicatesSortedList.ListNode;

import java.util.PriorityQueue;

public class MergeKSortedLists {

    public ListNode merge(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>((n1, n2)->n1.val - n2.val);
        int k = lists.length;
        for(ListNode node : lists) {
            minHeap.offer(node);
        }
        ListNode head = null;
        ListNode tail = null;
        while(!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            if(head == null) {
                head = minNode;
                tail = minNode;
            }else {
                tail.next = minNode;
            }
            ListNode minNextNode = minNode.next;
            if(minNextNode != null) {
                minHeap.offer(minNextNode);
            }
        }
        return head;
    }

}

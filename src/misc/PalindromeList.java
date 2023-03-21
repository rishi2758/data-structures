package misc;

public class PalindromeList {

	static public class ListNode {
		final int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}
	}

	public void isPalindrome(ListNode head) {
		checkPalindrome(head, head);

	}

	private boolean checkPalindrome(ListNode head, ListNode node) {

		if (node == null) {
			return false;
		}
		checkPalindrome(head, node.next);
		head = head.next;
		return head.val == node.val;
	}

	public static void main(String[] args) {

		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(1);

		new PalindromeList().isPalindrome(head);

	}

}

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // Dummy node to handle deleting head nodes
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next; // remove node
            } else {
                current = current.next; // move forward
            }
        }

        return dummy.next;
    }
}
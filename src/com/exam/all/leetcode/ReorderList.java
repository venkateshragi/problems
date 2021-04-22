package com.exam.all.leetcode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * <p>You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * <p>Example 1:
 *
 * <p>Given 1->2->3->4, reorder it to 1->4->2->3.
 *
 * <p>Example 2:
 *
 * <p>Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class ReorderList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null)
            return;
        ListNode sptr = head;
        ListNode fptr = head;
        while(fptr != null && fptr.next != null) {
            sptr = sptr.next;
            fptr = fptr.next.next;
        }

        ListNode prev = null;
        ListNode curr = sptr.next;
        sptr.next = null;
        ListNode next = null;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        curr = prev;
        next = null;
        ListNode temp = head;
        ListNode tempNext = null;
        while(curr != null) {
            next = curr.next;
            tempNext = temp.next;

            temp.next = curr;
            curr.next = tempNext;

            temp = tempNext;
            curr = next;
        }
    }
}

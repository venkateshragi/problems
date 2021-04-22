package com.exam.all.leetcode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * You are given two non-empty linked lists representing two non-negative integers. The digits are
 * stored in reverse order and each of their nodes contain a single digit. Add the two numbers and
 * return it as a linked list.
 *
 * <p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * <p>Example:
 *
 * <p>Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class Add2ReverseListNumbers {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode result = new ListNode(-1);
        ListNode dummy = result;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int sum = l1Val + l2Val + carry;
            carry = sum >= 10 ? 1 : 0;
            ListNode temp = new ListNode(sum % 10);
            dummy.next = temp;
            dummy = dummy.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if(carry == 1)
            dummy.next = new ListNode(1);
        return result.next;
    }
}

package com.exam.all.leetcode;

import java.util.List;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * Merge two sorted linked lists and return it as a new list. The new list should be made by
 * splicing together the nodes of the first two lists.
 *
 * <p>Example:
 *
 * <p>Input: 1->2->4, 1->3->4 Output: 1->1->2->3->4->4
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MergeTwoSortedLinkedLists {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //using recursion
        mergeRecursion(l1, l2);

        //using iteration
        return mergeIteration(l1, l2);
    }

    private ListNode leetCodeMerge(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    private ListNode mergeIteration(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        // start with the linked list
        // whose head data is the least
        if(l1.val <= l2.val)
            return mergeIterationUtil(l1, l2);
        else
            return mergeIterationUtil(l2, l1);
    }

    private ListNode mergeIterationUtil(ListNode l1, ListNode l2) {
        // if only one node in first list
        // simply point its head to second list
        if(l1.next == null) {
            l1.next = l2;
            return l1;
        }

        ListNode curr1 = l1;
        ListNode next1 = curr1.next;
        ListNode curr2 = l2;
        ListNode next2 = curr2.next;

        while(curr1 != null && curr2 != null) {
            // if curr2 lies in between curr1 and next1
            // then do curr1->curr2->next1
            if(curr1.val <= curr2.val && curr2.val <= next1.val) {
                next2 = curr2.next;
                curr1.next = curr2;
                curr2.next = next1;

                // now let curr1 and curr2 to point
                // to their immediate next pointers
                curr1 = curr2;
                curr2 = next2;
            } else {
                // if more nodes in first list
                if(next1.next != null) {
                    curr1 = curr1.next;
                    next1 = next1.next;
                }
                // else point the last node of first list
                // to the remaining nodes of second list
                else {
                    next1.next = curr2;
                    return l1;
                }
            }
        }
        return l1;
    }

    public ListNode mergeRecursion(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        if(l1.val < l2.val) {
            l1.next = mergeRecursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeRecursion(l1, l2.next);
            return l2;
        }
    }

}

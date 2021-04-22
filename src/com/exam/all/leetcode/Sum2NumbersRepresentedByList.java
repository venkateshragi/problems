package com.exam.all.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are
 * stored in reverse order and each of their nodes contain a single digit. Add the two numbers and
 * return it as a linked list.
 *
 * <p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * <p>Example:
 *
 * <p>Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * <p>Output: 7 -> 0 -> 8
 * <p>Explanation: 342 + 465 = 807.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class Sum2NumbersRepresentedByList {

    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;
        ListNode result = null;
        ListNode prev = null;
        int carry = 0;
        while(node1 != null || node2 != null) {
            int node1Value = node1 == null ? 0 : node1.val;
            int node2Value = node2 == null ? 0 : node2.val;

            int sum = node1Value + node2Value + carry;
            carry = sum >= 10 ? 1 : 0;
            ListNode tempNode = new ListNode(sum % 10);

            if(result == null)
                result = tempNode;
            else
                prev.next = tempNode;
            prev = tempNode;
            if(node1 != null)
                node1 = node1.next;
            if(node2 != null)
                node2 = node2.next;
        }
        if(carry > 0)
            prev.next = new ListNode(carry);
        return result;
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);

            ListNode ret = new Sum2NumbersRepresentedByList().addTwoNumbers(l1, l2);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}

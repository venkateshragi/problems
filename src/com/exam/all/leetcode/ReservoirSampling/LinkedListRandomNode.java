package com.exam.all.leetcode.ReservoirSampling;

import java.util.Random;

/**
 * https://leetcode.com/problems/linked-list-random-node/
 *
 * <p>Given a singly linked list, return a random node's value from the linked list. Each node must
 * have the same probability of being chosen.
 *
 * <p>Example 1:
 *
 * <p>Input
 * <p>["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * <p>[[[1, 2, 3]], [], [], [], [], []] Output [null, 1, 3, 2, 2, 3]
 *
 * <p>Explanation
 * <p> Solution solution = new Solution([1, 2, 3]);
 * <p>solution.getRandom(); // return 1
 * <p>solution.getRandom(); // return 3
 * <p>solution.getRandom(); // return 2
 * <p>solution.getRandom(); // return 2
 * <p>solution.getRandom(); // return 3
 * <p>// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 *
 * <p>Constraints:
 *
 * <p>The number of nodes in the linked list will be in the range [1, 104]. -104 <= Node.val <= 104
 * At most 104 calls will be made to getRandom.
 *
 * <p>Follow up:
 *
 * <p>What if the linked list is extremely large and its length is unknown to you? Could you solve
 * this efficiently without using extra space?
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class LinkedListRandomNode {

  public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
          this.val = val;
        }

        ListNode(int val, ListNode next) {
          this.val = val;
          this.next = next;
        }
    }

    ListNode head;
    Random random;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        ListNode curr = head;
        int count = 0;
        ListNode res = head;
        while(curr != null) {
            int nextRan =  random.nextInt(count + 1);
            if(nextRan == count) {
                res = curr;
            }
            count++;
            curr = curr.next;
        }
        return res.val;
    }

    /*
    "Math.random() < 1.0 / scope" I will use an example. Lets say your at your 3 element with in that infinite list.
    Which gives you a propability of 1.0 / 3 ~ 0.33. Math.Random produces a value between 0.0 and 1.0.
    if Math.Random produces a value less than 0.33 that means the new number read from that infinite list has been picked
     hence you update chosenValue to your current element.
     Any value above 0.33 indicates that the other numbers before it have been picked.
     */
    public int getRandomBetterPerformance() {
        int scope = 1, chosenValue = 0;
        ListNode curr = this.head;
        while (curr != null) {
            // decide whether to include the element in reservoir
            if (Math.random() < 1.0 / scope)
                chosenValue = curr.val;
            // move on to the next node
            scope += 1;
            curr = curr.next;
        }
        return chosenValue;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        LinkedListRandomNode sol = new LinkedListRandomNode(head);
        System.out.println(sol.getRandom());
    }

}

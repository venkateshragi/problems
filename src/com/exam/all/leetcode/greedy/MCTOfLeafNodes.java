package com.exam.all.leetcode.greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
 *
 * <p>Given an array arr of positive integers, consider all binary trees such that:
 *
 * <p>Each node has either 0 or 2 children; The values of arr correspond to the values of each leaf
 * in an in-order traversal of the tree. (Recall that a node is a leaf if and only if it has 0
 * children.) The value of each non-leaf node is equal to the product of the largest leaf value in
 * its left and right subtree respectively. Among all possible binary trees considered, return the
 * smallest possible sum of the values of each non-leaf node. It is guaranteed this sum fits into a
 * 32-bit integer.
 *
 * <p>Example 1:
 *
 * <p>Input: arr = [6,2,4] Output: 32 Explanation: There are two possible trees. The first has
 * non-leaf node sum 36, and the second has non-leaf node sum 32.
 *
 * <p>24 24 / \ / \ 12 4 6 8 / \ / \ 6 2 2 4
 *
 * <p>Constraints:
 *
 * <p>2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MCTOfLeafNodes {
    //https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/478708/RZ-Summary-of-all-the-solutions-I-have-learned-from-Discuss-in-Python
    //https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/478708/RZ-Summary-of-all-the-solutions-I-have-learned-from-Discuss-in-Python/831933
    public int mctFromLeafValues(int[] arr) {
        return 0;
    }

    public static void main(String[] args) {
        int c = '1' - '0';
        Deque<Character> queue = new ArrayDeque<>();
        Character[] cw = new Character[queue.size()];
        queue.toArray(cw);
        for(char cx : queue)  {

        }

        System.out.println(c);

    }
}

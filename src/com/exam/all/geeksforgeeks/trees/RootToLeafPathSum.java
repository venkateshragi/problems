package com.exam.all.geeksforgeeks.trees;

import java.util.*;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up
 * all the values along the path equals the given sum.
 *
 * <p>Example:
 *            5
 *          /  \
 *         4    8
 *        /    / \
 *      11   13   4
 *     / \        \
 *    7   2        1
 *
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class RootToLeafPathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null && sum != 0)
            return false;
        if(root != null) {
            if(root.val == sum && root.left == null && root.right == null)
                return true; //here even if val n sum are same and either of them is not null, we need to check rest of the path as rest of path may stil become zero.
            boolean hasPath = false;
            int remainingSum = sum - root.val;
            if(root.left != null)
                hasPath = hasPathSum(root.left, remainingSum);
            return hasPath ? hasPath : hasPathSum(root.right, remainingSum);

        }
        Map<Character, Integer> x = new HashMap<>();
        x.put('a', 1);
        Set<Character> set1 = new TreeSet<>(new CharComparator());

        String s = "";
        return false;
    }
    class CharComparator implements Comparator<Character> {
        public int compare(Character c1, Character c2) {
            return c1 - c2;
        }
    }

}

package com.exam.all.leetcode.dfs;

import java.util.Stack;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 *
 * <p>You are given the root of a binary search tree (BST), where exactly two nodes of the tree were
 * swapped by mistake. Recover the tree without changing its structure.
 *
 * <p>Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant
 * space solution?
 *
 * <p>Example 1:
 *
 * <p>Input: root = [1,3,null,null,2] Output: [3,1,null,null,2] Explanation: 3 cannot be a left
 * child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid. Example 2:
 *
 * <p>Input: root = [3,1,4,null,null,2] Output: [2,1,4,null,null,3] Explanation: 2 cannot be in the
 * right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 * <p>Constraints:
 *
 * <p>The number of nodes in the tree is in the range [2, 1000]. -2 pow  31 <= Node.val <= 2 pow 31 - 1
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class RecoverBST {

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    TreeNode x = null, y = null, pred = null;

    public void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    public void findTwoSwapped(TreeNode root) {
        if (root == null) return;
        findTwoSwapped(root.left);
        if (pred != null && root.val < pred.val) {
            y = root;
            //we break after seeing second incorrect order which would actually set the correct x and y
            if (x == null) x = pred;
            else return;
        }
        pred = root;
        findTwoSwapped(root.right);
    }

    public void recoverTree(TreeNode root) {
//        findTwoSwapped(root);
        recoverIterative(root);
        swap(x, y);

    }

    public static void main(String[] args) {
        TreeNode root = new  TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        new RecoverBST().recoverTree(root);

        root = new  TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        new RecoverBST().recoverTree(root);
    }

    public void recoverIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

//        while (!stack.isEmpty() || root != null) {
//            while (root != null) {
//                stack.add(root);
//                root = root.left;
//            }
//            root = stack.removeLast();
//            if (pred != null && root.val < pred.val) {
//                y = root;
//                if (x == null) x = pred;
//                else break;
//            }
//            pred = root;
//            root = root.right;
//        }

        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pred != null && root.val < pred.val) {
                y = root;
                if(x == null)
                    x = pred;
                else
                    break;
            }
            pred  = root;
            root = root.right;
        }
    }

}

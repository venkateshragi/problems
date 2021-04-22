package com.exam.all.geeksforgeeks.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Check if the binary tree is symmetric
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class SymmetricBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null)
            return true;
        if(node1 != null && node2 != null && node1.val == node2.val)
            return isSymmetric(node1.left, node2.right) &&
                    isSymmetric(node1.right, node2.left);
        return false;
    }

    public boolean isSymmetricIterative(TreeNode root) {
        if(root == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ((LinkedList<TreeNode>) queue).add(root.left);
        ((LinkedList<TreeNode>) queue).add(root.right);
        while(!queue.isEmpty()) {
            TreeNode temp1 = queue.remove();
            TreeNode temp2 = queue.remove();
            if(temp1 == null && temp2 == null)
                continue;
            if(temp1 != null && temp2 != null && temp1.val == temp2.val) {
                ((LinkedList<TreeNode>) queue).add(temp1.left);
                ((LinkedList<TreeNode>) queue).add(temp2.right);
                ((LinkedList<TreeNode>) queue).add(temp1.right);
                ((LinkedList<TreeNode>) queue).add(temp2.left);
            } else {
                return false;
            }
        }
        return true;
    }



}

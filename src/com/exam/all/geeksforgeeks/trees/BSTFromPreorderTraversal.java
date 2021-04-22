package com.exam.all.geeksforgeeks.trees;

/**
 * Given preorder traversal of a binary search tree, construct the BST.
 * https://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
 *
 * <p>Note: You may assume that duplicates do not exist in the tree.
 *
 * <p>For example, if the given traversal is {10, 5, 1, 7, 40, 50}, then the output should be root
 * of following tree.
 *
 * <p>10 / \ 5 40 / \ \ 1 7 50
 *
 * @author vragi
 */
public class BSTFromPreorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return null;
    }

}

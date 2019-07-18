package com.exam.all.geeksforgeeks.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * This uses 2 stacks.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class BinaryTreePostOrderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        Stack<TreeNode> firstStack = new Stack<>();
        Stack<TreeNode> secondStack = new Stack<>();
        firstStack.push(root);
        while(!firstStack.isEmpty()) {
            TreeNode temp = firstStack.pop();
            secondStack.push(temp);
            if(temp.left != null) {
                firstStack.push(temp.left);
            }
            if(temp.right != null) {
                firstStack.push(temp.right);
            }
        }
        List<Integer> result = new ArrayList<>();
        while(!secondStack.isEmpty()) {
            result.add(secondStack.pop().val);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}

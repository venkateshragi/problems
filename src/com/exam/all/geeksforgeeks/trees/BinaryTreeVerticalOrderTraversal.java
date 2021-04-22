package com.exam.all.geeksforgeeks.trees;

import java.util.*;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to
 * bottom, column by column).
 *
 * <p>If two nodes are in the same row and column, the order should be from left to right.
 *
 * <p>Examples 1: Input: [3,9,20,null,null,15,7]
 *
 * <p>3 /\ / \ 9 20 /\ / \ 15 7
 *
 * <p>Output:
 *
 * <p>[ [9], [3,15], [20], [7] ]
 *
 * <p>Examples 2:
 *
 * <p>Input: [3,9,8,4,0,1,7]
 *
 * <p>3 /\ / \ 9 8 /\ /\ / \/ \ 4 01 7
 *
 * <p>Output:
 *
 * <p>[ [4], [9], [3,0,1], [8], [7] ]
 *
 * <p>Examples 3:
 *
 * <p>Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 *
 * <p>3 /\ / \ 9 8 /\ /\ / \/ \ 4 01 7 /\ / \ 5 2
 *
 * <p>Output:
 *
 * <p>[ [4], [9,5], [3,0,1], [8,2], [7] ]
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class BinaryTreeVerticalOrderTraversal {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    private class CustomNode {
        TreeNode node;
        int offset;

        public CustomNode(TreeNode node, int offset) {
            this.node = node;
            this.offset = offset;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null)
            return Collections.emptyList();

        Map<Integer, List<Integer>> result = new TreeMap<Integer, List<Integer>>();
        Queue<CustomNode> bfs = new LinkedList<CustomNode>();
        bfs.add(new CustomNode(root, 0));
        while(!bfs.isEmpty()) {
            CustomNode customNode = bfs.poll();
            result.putIfAbsent(customNode.offset, new ArrayList<>());
            result.get(customNode.offset).add(customNode.node.val);

            if(customNode.node.left != null)
                bfs.add(new CustomNode(customNode.node.left, customNode.offset - 1));
            if(customNode.node.right != null)
                bfs.add(new CustomNode(customNode.node.right, customNode.offset + 1));
        }
        return new ArrayList<>(result.values());
    }

    public List<List<Integer>> verticalOrder1(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        Map<Integer, List<Integer>> order = new TreeMap<Integer, List<Integer>>();
        traverse(root, order, 0);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(Map.Entry<Integer, List<Integer>> entry : order.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private void traverse(TreeNode root, Map<Integer, List<Integer>> order, int offset) {
        if(root == null)
            return;
        List<Integer> container = order.get(offset);
        if(container == null) {
            container = new ArrayList<Integer>();
            order.put(offset, container);
        }
        container.add(root.val);
        traverse(root.left, order, offset - 1);
        traverse(root.right, order, offset + 1);
    }
}

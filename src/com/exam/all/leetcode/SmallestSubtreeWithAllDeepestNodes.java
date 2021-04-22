package com.exam.all.leetcode;

/**
 * https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 *
 * <p>A node is deepest if it has the largest depth possible among any node in the entire tree.
 *
 * <p>The subtree of a node is that node, plus the set of all descendants of that node.
 *
 * <p>Return the node with the largest depth such that it contains all the deepest nodes in its
 * subtree.
 *
 * <p>Example 1:
 *
 * <p>Input: [3,5,1,6,2,0,8,null,null,7,4] Output: [2,7,4] Explanation:
 *
 * <p>We return the node with value 2, colored in yellow in the diagram. The nodes colored in blue
 * are the deepest nodes of the tree. The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a
 * serialization of the given tree. The output "[2, 7, 4]" is a serialization of the subtree rooted
 * at the node with value 2. Both the input and output have TreeNode type.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class SmallestSubtreeWithAllDeepestNodes {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
     }

     private class SubtreeWithDeepestNodes {
         TreeNode node;
         int depth;
         SubtreeWithDeepestNodes(TreeNode node, int depth) {
             this.node = node;
             this.depth = depth;
         }
     }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private SubtreeWithDeepestNodes dfs(TreeNode node) {
        if(node == null) return new SubtreeWithDeepestNodes(null, 0);
        SubtreeWithDeepestNodes left = dfs(node.left);
        SubtreeWithDeepestNodes right = dfs(node.right);
        if(left.depth > right.depth)
            return new SubtreeWithDeepestNodes(left.node, left.depth + 1);
        else if(right.depth > left.depth)
            return new SubtreeWithDeepestNodes(right.node, right.depth + 1);
        return new SubtreeWithDeepestNodes(node, left.depth + 1);

    }
}

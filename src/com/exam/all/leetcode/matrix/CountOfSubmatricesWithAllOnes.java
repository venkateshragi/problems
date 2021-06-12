package com.exam.all.leetcode.matrix;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/count-submatrices-with-all-ones/
 *
 * <p>Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all
 * ones.
 *
 * <p>Example 1:
 *
 * <p>Input: mat = [[1,0,1], [1,1,0], [1,1,0]] Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2.
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 *
 * Example 2:
 *
 * <p>Input: mat = [[0,1,1,0], [0,1,1,1], [1,1,1,0]] Output: 24
 * Explanation:
 * There are 8 rectangles of side 1x1.
 * There are 5 rectangles of side 1x2.
 * There are 2 rectangles of side 1x3.
 * There are 4 rectangles of side 2x1.
 * There are 2 rectangles of side 2x2.
 * There are 2 rectangles of side 3x1.
 * There is 1 rectangle of side 3x2.
 * Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
 *
 * Example 3:
 *
 * <p>Input: mat = [[1,1,1,1,1,1]] Output: 21
 *
 * Example 4:
 *
 * <p>Input: mat = [[1,0,1],[0,1,0],[1,0,1]] Output: 5
 *
 * <p>Constraints:
 *
 * <p>1 <= rows <= 150
 * 1 <= columns <= 150
 * 0 <= mat[i][j] <= 1
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class CountOfSubmatricesWithAllOnes {
    //detailed explanation https://leetcode.com/problems/count-submatrices-with-all-ones/discuss/720265/Java-Detailed-Explanation-From-O(MNM)-to-O(MN)-by-using-Stack
    public static int numSubmat(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int res = 0;
        for(int i = 0; i < rows; i++) {
            int[] heights = new int[cols];
            Arrays.fill(heights, 1);
            // we are considering from current row to bottom most row. ie., from ith row to last row
            for(int j = i; j < rows; j++) {
                for(int k = 0; k < cols; k++) {
                    heights[k] = heights[k] & mat[j][k];
                }
                //from ith row, we calculate res by adding one row at a time. ie., we calculate for following matrices
                //[i, i+1] matrix, [i, i+1, i+2], ..., [i, i+1, i+2, ..lasst-row]
                res += bruteForceHelper(heights);
            }
        }
        return res;
    }

    /**
     * for matrix
     * [1, 1] = 1(1x2) + 2 (1x1) = 3 matrices are possible
     * [1, 1, 1] = 1(1x3) + 2 (1x2) + 3(1x1) = matrices for [1,1] + 3
     * [1, 1, 1, 1] = 1(1x4) + 2 (1x3) + 3(1x2) + 4(1x1) = matrices for [1,1, 1] + 4
     * so total = prev_total(total) + currMatLength (consecutiveOnes)
     * @param heights
     * @return
     */
    private static int bruteForceHelper(int[] heights) {
        int total = 0, consecutiveOnes = 0;
        for(int i = 0; i < heights.length; i++) {
            consecutiveOnes = heights[i] == 0 ? 0 : consecutiveOnes + 1;
            total = total + consecutiveOnes;
        }
        return total;
    }

    //using stack or histogram(not really) approach
    public static int numSubmatStack(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int res = 0;
        int[] heights = new int[cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
            }
            res += stackHelper(heights);
        }
        return res;
    }

    private static int stackHelper(int[] heights) {
        int cols = heights.length;
        int[] sum = new int[cols];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < cols; i++) {

            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            if(stack.isEmpty()) {
                /**
                 * This is of following matrix
                 * 0, 0
                 * 1, 1
                 * 1, 1
                 * and heights = [2,2] => each elements makes that many number of matrices.
                 * say we are calculating how many ADDITIIONAL matrices mat[2][1] makes
                 * which is nothing but height times no of columns. as col are 0 indexed add 1
                 * in the above mat[2][1] adds matrices [1], [{1,1}], [{1,1}{1,1}] and [{1}{1}] (2x1 matrix)
                 * */
                sum[i] = heights[i] * (i+1); // i is zero based
            } else {
                int top = stack.peek(); //not removing just peek
                sum[i] = sum[top];
                sum[i] += heights[i] * (i - top);
            }
            stack.push(i);

        }
        int total = 0;
        for(int t : sum)
            total += t;
        return total;

    }

    public static void main(String args[]) {
        int x = numSubmat(new int[][] {{1,0,1}, {1,1,0}, {1,1,0}});
        System.out.println(x);

        x = numSubmat(new int[][] {{0,1,1,0}, {0,1,1,1}, {1,1,1,0}});
        System.out.println(x);

        x = numSubmat(new int[][] {{1,1,1,1,1,1}});
        System.out.println(x);

        x = numSubmat(new int[][] {{1,0,1}, {0,1,0}, {1,0,1}});
        System.out.println(x);

        x = numSubmatStack(new int[][] {{1,0,1}, {1,1,0}, {1,1,0}});
        System.out.println(x);

        x = numSubmatStack(new int[][] {{0,1,1,0}, {0,1,1,1}, {1,1,1,0}});
        System.out.println(x);

        x = numSubmatStack(new int[][] {{1,1,1,1,1,1}});
        System.out.println(x);

        x = numSubmatStack(new int[][] {{1,0,1}, {0,1,0}, {1,0,1}});
        System.out.println(x);

    }
}

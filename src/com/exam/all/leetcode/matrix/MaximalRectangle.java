package com.exam.all.leetcode.matrix;

import java.util.Stack;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 *
 * <p>Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 *
 * <p>Example 1:
 *
 * <p>Input: matrix = [["1","0","1","0","0"], ["1","0","1","1","1"], ["1","1","1","1","1"],
 * ["1","0","0","1","0"]] Output: 6 Explanation: The maximal rectangle is shown in the above
 * picture.
 *
 * <p>Example 2:
 *
 * <p>Input: matrix = [] Output: 0
 *
 * <p>Example 3:
 *
 * <p>Input: matrix = [["0"]] Output: 0
 *
 * <p>Example 4:
 *
 * <p>Input: matrix = [["1"]] Output: 1
 *
 * <p>Example 5:
 *
 * <p>Input: matrix = [["0","0"]] Output: 0
 *
 * <p>Constraints:
 *
 * <p>rows == matrix.length cols == matrix[i].length 0 <= row, cols <= 200 matrix[i][j] is '0' or
 * '1'.
 *
 * <p>https://leetcode.com/problems/largest-rectangle-in-histogram/solution/
 * https://www.geeksforgeeks.org/maximum-size-rectangle-binary-sub-matrix-1s/
 * https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MaximalRectangle {
  // uses the max area in a histogram approach.
  public static int maximalRectangle(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return 0;
    int max = 0;

    int rows = matrix.length;
    int cols = matrix[0].length;

    int[] heights = new int[cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == 1)
          heights[j] +=
              matrix[i][j]; // heights[i] contains height of column j from row 0 to current i row.
        else heights[j] = 0;
      }
      int area = helper(heights);
      max = Math.max(max, area);
    }
    return max;
  }

  private static int helper(int[] heights) {
    int max = 0;
    int area;
    Stack<Integer> stack = new Stack<>();
    int i = 0;
    while (i < heights.length) {
      if (stack.isEmpty() || heights[stack.peek()] < heights[i]) // it can be <= also
      stack.push(i++); // increment i only after pushing to stack and not in each while loop.
      else {
        int top = stack.pop();
        // calculate the area of rectangle formed with stack top and not including current i.
        if (!stack.isEmpty()) {
          area = heights[top] * (i - 1 - stack.peek()); // i-1 implies not including current i.
          // as this implies heights[top] is > heights[i].
        } else {
          /*calculate area including current one. This occurs when top is the last element and
          curr heights[i] < heights[top]. In this case we are including curr i in rect area.
          */
          area = heights[top] * i;
        }
        max = Math.max(area, max);
      }
    }
    // this occurs when all remaining stack elements(bars in histogram) are in increasing order
    while (!stack.isEmpty()) {
      int top = stack.pop();
      if (stack.isEmpty()) {
        area = heights[top] * i;
      } else {
        area = heights[top] * (heights.length - 1 - stack.peek());
      }
      max = Math.max(area, max);
    }
    return max;
  }

  public static int leetcode84(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    stack.push(-1);
    int maxarea = 0;
    for (int i = 0; i < heights.length; ++i) {
      while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) // it can be >= also
      maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
      stack.push(i);
    }
    while (stack.peek() != -1)
      maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
    return maxarea;
  }

  public static void main(String args[]) {
    int x =
        maximalRectangle(
            new int[][] {{1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}});
    System.out.println(x);

    x = maximalRectangle(new int[][] {});
    System.out.println(x);

    x = maximalRectangle(new int[][] {{0}});
    System.out.println(x);

    x = maximalRectangle(new int[][] {{1}});
    System.out.println(x);

    x = maximalRectangle(new int[][] {{0, 0}});
    System.out.println(x);

    // ans is 8
    x = maximalRectangle(new int[][] {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 0}});
    System.out.println(x);

    x = helper(new int[] {5, 4, 2, 1});
    System.out.println(x);

  }
}
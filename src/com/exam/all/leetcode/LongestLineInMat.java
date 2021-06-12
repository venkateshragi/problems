package com.exam.all.leetcode;

/**
 * https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
 *
 * <p>Given an m x n binary matrix mat, return the length of the longest line of consecutive one in
 * the matrix.
 *
 * <p>The line could be horizontal, vertical, diagonal, or anti-diagonal.
 *
 * <p>Example 1:
 *
 * <p>Input: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]] Output: 3 Example 2:
 *
 * <p>Input: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]] Output: 4
 *
 * <p>Constraints:
 *
 * <p>m == mat.length n == mat[i].length
 * 1 <= m, n <= 10 pow 4
 * 1 <= m * n <= 10 pow 4
 * mat[i][j] is either 0 or 1.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class LongestLineInMat {

  public static int longestLine(int[][] mat) {

    int m = mat.length;
    int n = mat[0].length;

    int[] lm = new int[(m * n) + 1];
    int[] tm = new int[(m * n) + 1];
    int[] dm = new int[(m * n) + 1];
    int[] adm = new int[(m * n) + 1];

    int max = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (mat[i][j] == 0)
            continue;

        int left = 0;
        if (j > 0)
            left = lm[getIndex(i, j - 1, n)];

        int top = 0;
        if (i > 0)
            top = tm[getIndex(i - 1, j, n)];

        int diagonal = 0;
        if (i > 0 && j > 0)
            diagonal = dm[getIndex(i - 1, j - 1, n)];

        int adiagonal = 0;
        if(i > 0 && j < n - 1)
            adiagonal = adm[getIndex(i - 1, j + 1, n)];


        int pos = getIndex(i, j, n);
        lm[pos] = left + 1;
        tm[pos] = top + 1;
        dm[pos] = diagonal + 1;
        adm[pos] = adiagonal + 1;

        int posMax = 0;
        if (lm[pos] < tm[pos])
            posMax = Math.max(tm[pos], dm[pos]);
        else
            posMax = Math.max(lm[pos], dm[pos]);
        posMax = Math.max(posMax, adm[pos]);
        max = Math.max(max, posMax);
      }
    }
    return max;
  }

  public static int getIndex(int row, int col, int n) {
    return (row * n) + col + 1;
  }

  public static void main(String[] args) {
    int[][] intervals = new int[][] {{0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}};
    System.out.println(longestLine(intervals));

    intervals = new int[][] {{1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 0, 1}};
    System.out.println(longestLine(intervals));

    intervals = new int[][] {{0, 1, 1, 0}, {0, 1, 1, 0}, {1, 0, 0, 0}};
    System.out.println(longestLine(intervals));
  }
}

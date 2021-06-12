package com.exam.all.leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-arithmetic-sequence/
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 *
 * <p>Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ...
 * < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same
 * value (for 0 <= i < B.length - 1).
 *
 * <p>Example 1:
 *
 * <p>Input: [3,6,9,12] Output: 4 Explanation: The whole array is an arithmetic sequence with steps
 * of length = 3. Example 2:
 *
 * <p>Input: [9,4,7,2,10] Output: 3 Explanation: The longest arithmetic subsequence is [4,7,10].
 * Example 3:
 *
 * <p>Input: [20,1,15,3,10,5,8] Output: 4 Explanation: The longest arithmetic subsequence is
 * [20,15,10,5].
 *
 * <p>Note:
 *
 * <p>2 <= A.length <= 2000 0 <= A[i] <= 10000
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class LongestArithmeticSubSequence {

  /**
   * The main idea is to maintain a 2-D array that store diffs that are seen before.
   * The trick without using a HashMap is from the note: 0 <= a[i] <= 10000. So we can initiate
   * dp[a.length][diff] where 0 <= diff <= 20001 because Java array doesn't support negative index.
   *
   * <p>Definition: dp[i][j] is the longest arithmetic sequence from a[0] to a[i] that has j - 10000
   * differentiate.
   * base case: dp[0][diff] = 0
   * induction rule: dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1) where 0 <= j < i.
   *
   * @param a
   * @return
   */


  public int longestArithSeqLength(int[] a) {
        if (a == null) return 0;
        int[][] dp = new int[a.length][20001];

        int res = 1;
        for (int i = 1; i < a.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int diff = a[i] - a[j] + 10000;
                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1);   // DON'T forget to compare

                res = Math.max(res, dp[i][diff]);
            }
        }

        return res + 1;
    }


    public int longestArithSeqLengthUsingHashMap(int[] a) {
        if (a == null) return 0;

        HashMap<Integer, Integer>[] dp = new HashMap[a.length];

        int res = 0;
        for (int i = 0; i < a.length; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int diff = a[i] - a[j];
                //get default 1 as we will add 1 in next step which makes length for this diff 2 in case this is first
                //we are seeing the diff.
                int prev = dp[j].getOrDefault(diff, 1);

                dp[i].put(diff, prev+1);

                res = Math.max(res, dp[i].get(diff));
            }
        }

        return res;
    }
}

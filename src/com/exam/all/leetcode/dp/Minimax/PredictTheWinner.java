package com.exam.all.leetcode.dp.Minimax;

/**
 * https://leetcode.com/problems/predict-the-winner/
 *
 * <p>You are given an integer array nums. Two players are playing a game with this array: player 1
 * and player 2.
 *
 * <p>Player 1 and player 2 take turns, with player 1 starting first. Both players start the game
 * with a score of 0. At each turn, the player takes one of the numbers from either end of the array
 * (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1. The player
 * adds the chosen number to their score. The game ends when there are no more elements in the
 * array.
 *
 * <p>Return true if Player 1 can win the game. If the scores of both players are equal, then player
 * 1 is still the winner, and you should also return true. You may assume that both players are
 * playing optimally.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [1,5,2] Output: false Explanation: Initially, player 1 can choose between 1 and
 * 2. If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5,
 * then player 1 will be left with 1 (or 2). So, final score of player 1 is 1 + 2 = 3, and player 2
 * is 5. Hence, player 1 will never be the winner and you need to return false. Example 2:
 *
 * <p>Input: nums = [1,5,233,7] Output: true Explanation: Player 1 first chooses 1. Then player 2
 * has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True
 * representing player1 can win.
 *
 * <p>Constraints:
 *
 * <p>1 <= nums.length <= 20
 * 0 <= nums[i] <= 10 ^ 7
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class PredictTheWinner {
    public boolean predict(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for(int s = len-1; s >= 0; s--) {
            dp[s][s]  = nums[s];
            for(int e = s + 1; e < len; e++) {
                int leftPick = nums[s] - dp[s+1][e];
                int rightPick = nums[e] - dp[s][e-1];
                dp[s][e] = Math.max(leftPick, rightPick);
            }
        }
        return dp[0][len - 1] >= 0;
    }

    public boolean predictUsingSingleRow(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        for(int s = len-1; s >= 0; s--) {
            dp[s]  = nums[s];
            for(int e = s + 1; e < len; e++) {
                int leftPick = nums[s] - dp[e];
                int rightPick = nums[e] - dp[e-1];
                dp[e] = Math.max(leftPick, rightPick);
            }
        }
        return dp[len - 1] >= 0;
    }

    public boolean predictSimpleRecurse(int[] nums) {
        return getMaxScore(nums,0,nums.length-1) >= 0;
    }
    private int getMaxScore(int[] nums, int s, int e){
        if (s == e)
            return nums[s];
        int first = nums[s] - getMaxScore(nums,s+1,e);
        int last = nums[e] - getMaxScore(nums,s,e-1);
        return Math.max(first,last);
    }

    public static void main(String[] args) {
        PredictTheWinner predictTheWinner = new PredictTheWinner();
        System.out.println(predictTheWinner.predict(new int[]{1,5,2,4,6})); //should return true.
        System.out.println(predictTheWinner.predict(new int[]{0})); //should return true.
        System.out.println(predictTheWinner.predictUsingSingleRow(new int[]{1,5,2,4,6}));
        System.out.println(predictTheWinner.predictSimpleRecurse(new int[]{1,5,2,4,6}));
    }
}

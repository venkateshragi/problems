package com.exam.all.leetcode.dp.Minimax;

/**
 * https://leetcode.com/problems/stone-game/
 *
 * <p>Alex and Lee play a game with piles of stones. There are an even number of piles arranged in a
 * row, and each pile has a positive integer number of stones piles[i].
 *
 * <p>The objective of the game is to end with the most stones. The total number of stones is odd,
 * so there are no ties.
 *
 * <p>Alex and Lee take turns, with Alex starting first. Each turn, a player takes the entire pile
 * of stones from either the beginning or the end of the row. This continues until there are no more
 * piles left, at which point the person with the most stones wins.
 *
 * <p>Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 *
 * <p>Example 1:
 *
 * <p>Input: piles = [5,3,4,5] Output: true Explanation: Alex starts first, and can only take the
 * first 5 or the last 5. Say he takes the first 5, so that the row becomes [3, 4, 5]. If Lee takes
 * 3, then the board is [4, 5], and Alex takes 5 to win with 10 points. If Lee takes the last 5,
 * then the board is [3, 4], and Alex takes 4 to win with 9 points. This demonstrated that taking
 * the first 5 was a winning move for Alex, so we return true.
 *
 * <p>Constraints:
 *
 * <p>2 <= piles.length <= 500
 * piles.length is even.
 * 1 <= piles[i] <= 500
 * sum(piles) is odd.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class StonePileGame {

    //another approach https://leetcode.com/problems/stone-game/discuss/154610/DP-or-Just-return-true
    /*
    dp[i][j] means the biggest number of stones you can get more than opponent picking piles in piles[i] ~ piles[j].
    You can first pick piles[i] or piles[j].

    If you pick piles[i], your result will be piles[i] - dp[i + 1][j]
    If you pick piles[j], your result will be piles[j] - dp[i][j - 1]
    So we get:
    dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
     */
    public boolean stoneGameDPSimple(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++)
            dp[i][i] = piles[i];
        for(int k = 1; k < len; k++) {
            for(int i = 0; i + k < len; i++) {
                int j = i + k;
                dp[i][j] = Math.max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1]);
            }
        }
        return dp[0][len - 1] > 0;
    }

    //https://leetcode.com/problems/predict-the-winner/solution/ - Approach 3
    public boolean stoneGameDPBottomUp(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        for(int s = len - 2; s >= 0; s--) {
            dp[s][s]  = piles[s];
            for(int e = s + 1; e < len; e++) {
                int leftPick = piles[s] - dp[s+1][e];
                int rightPick = piles[e] - dp[s][e-1];
                dp[s][e] = Math.max(leftPick, rightPick);
            }
        }
        return dp[0][len - 1] > 0;
    }

    public boolean stoneGameDP(int[] piles) {
        int len = piles.length;
        //[i][j][0] => alex score alex score and [i][j][1] => lee score
        int[][][] dp = new int[len][len][];
        for(int i = 0; i < len; i++) {
            //implies if only i element is the input, alex score is piles[i] and lee score is obviously 0.
            dp[i][i] = new int[]{piles[i], 0};
        }

        for(int k = 2; k <= len; k++) {
            for(int i = 0; i + k <= len; i++) {
                int j = i + k - 1;

                //if alex picks left most
                //as alex took left, dp[i+1][j][1] => his score for i+1,j interval and dp[i+1][j][0] => leescore for i+1,j
                int leftPickScore = piles[i] + dp[i+1][j][1];

                //if alex picks right most
                int rightPickScore = piles[j] + dp[i][j-1][1];

                if(leftPickScore > rightPickScore) {
                    int leeScore = dp[i+1][j][0];
                    dp[i][j] = new int[]{leftPickScore, leeScore};
                } else {
                    int leeScore = dp[i][j-1][0];
                    dp[i][j] = new int[]{rightPickScore, leeScore};
                }
            }
        }
        //return if alex score is > leescore
        return dp[0][len - 1][0] > dp[0][len - 1][1];
    }


    //leetcode solution
    public boolean stoneGame(int[] piles) {
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }

        return dp[1][N] > 0;
    }

    public static void main(String[] args) {
        StonePileGame stonePileGame = new StonePileGame();
        System.out.println(stonePileGame.stoneGame(new int[]{5,3,4,5}));
        System.out.println(stonePileGame.stoneGameDP(new int[]{5,3,4,5}));
        System.out.println(stonePileGame.stoneGameDPSimple(new int[]{5,3,4,5}));
        System.out.println(stonePileGame.stoneGameDPBottomUp(new int[]{5,3,4,5}));
        System.out.println(stonePileGame.stoneGameDPBottomUp(new int[]{1,5,2,4,6})); //should return true.
    }
}

package com.exam.all.leetcode.dp.Minimax;

/**
 * https://leetcode.com/problems/can-i-win/
 *
 * <p>In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10.
 * The player who first causes the running total to reach or exceed 100 wins.
 *
 * <p>What if we change the game so that players cannot re-use integers?
 *
 * <p>For example, two players might take turns drawing from a common pool of numbers from 1 to 15
 * without replacement until they reach a total >= 100.
 *
 * <p>Given two integers maxChoosableInteger and desiredTotal, return true if the first player to
 * move can force a win, otherwise, return false. Assume both players play optimally.
 *
 * <p>Example 1:
 *
 * <p>Input: maxChoosableInteger = 10, desiredTotal = 11 Output: false Explanation: No matter which
 * integer the first player choose, the first player will lose. The first player can choose an
 * integer from 1 up to 10. If the first player choose 1, the second player can only choose integers
 * from 2 up to 10. The second player will win by choosing 10 and get a total = 11, which is >=
 * desiredTotal. Same with other integers chosen by the first player, the second player will always
 * win. Example 2:
 *
 * <p>Input: maxChoosableInteger = 10, desiredTotal = 0 Output: true Example 3:
 *
 * <p>Input: maxChoosableInteger = 10, desiredTotal = 1 Output: true
 *
 * <p>Constraints:
 *
 * <p>1 <= maxChoosableInteger <= 20 0 <= desiredTotal <= 300
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class CanIWin {

//    https://leetcode.com/problems/can-i-win/discuss/404665/Java-DP-solution-with-detailed-explanation-very-easy-to-understand
public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    if(desiredTotal == 0)
        return true;
    int sum = (maxChoosableInteger * (maxChoosableInteger + 1))  /2;
    if(desiredTotal > sum)
        return false;

    //we have 2 ^ maxChoosableInteger possibilities or unique numbers formed by representing nums till maxChoosableInteger
    //as binary digits in an integer.
    // we are adding 1 as we do not use 0 in choosing
    int[] dp = new int[1 << maxChoosableInteger + 1];
    return helper(dp, 0, desiredTotal, maxChoosableInteger);
}

    private boolean helper(int[] dp, int state, int desiredTotal, int max) {
        //this time we are checking if opponent  has crossed the desiredTotal. So we loose if desiredTotal <= 0
        if(desiredTotal <= 0)
            return false;
        if(dp[state] != 0)
            return dp[state] == 1;

        boolean win = false;
        for(int i = 1; i <= max; i++) {
            //here == 0 => the number is not used as we are doing & with current state which includes if num pos(i-1) is set or not
            //i-1 = 0 => 1
            boolean isNotUsed = (state & (1 << (i-1))) == 0;
            if(isNotUsed) {
                //1 << i-1 => num at pos i-1 is set. we already know that i-1 is not used now ^ is used to set that pos of i-1.
                if(!helper(dp, state ^ (1 << (i-1)), desiredTotal - i, max)) {
                    win = true;
                    break;
                }
            }
        }
        dp[state] = win ? 1 : -1;
        return win;
    }
}

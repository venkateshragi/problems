package com.exam.all.leetcode.dp.Minimax;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/nim-game/
 *
 * <p>You are playing the following Nim Game with your friend:
 *
 * <p>Initially, there is a heap of stones on the table. You and your friend will alternate taking
 * turns, and you go first. On each turn, the person whose turn it is will remove 1 to 3 stones from
 * the heap. The one who removes the last stone is the winner. Given n, the number of stones in the
 * heap, return true if you can win the game assuming both you and your friend play optimally,
 * otherwise return false.
 *
 * <p>Example 1:
 *
 * <p>Input: n = 4 Output: false Explanation: These are the possible outcomes: 1. You remove 1
 * stone. Your friend removes 3 stones, including the last stone. Your friend wins. 2. You remove 2
 * stones. Your friend removes 2 stones, including the last stone. Your friend wins. 3. You remove 3
 * stones. Your friend removes the last stone. Your friend wins. In all outcomes, your friend wins.
 * Example 2:
 *
 * <p>Input: n = 1 Output: true Example 3:
 *
 * <p>Input: n = 2 Output: true
 *
 * <p>Constraints:
 *
 * <p>1 <= n <= 2 ^ 31 - 1
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class NimGame {

    /*
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }
    */

    /*
    bottom-up
    public boolean canWinNim(int n) {
    if(n <= 0)
        throw new IllegalArgumentException();
    if(n < 4)
        return true;
    boolean[] res = new boolean[n + 1];
    res[0] = true;
    res[1] = true;
    res[2] = true;
    res[3] = true;
    for(int i = 4 ; i <= n ; i++)
        res[i] = !(res[i - 1] && res[i - 2] && res[i - 3]);
    return res[n];
    }
     */

    public static boolean canWinNim(int n) {
        if(n <= 3)
            return true;
        Map<Integer, Boolean> dp = new HashMap<Integer, Boolean>();
        dp.put(1, true);
        dp.put(2, true);
        dp.put(3, true);
        return helper(n, dp);
    }

    private static boolean helper(int n, Map<Integer, Boolean> dp) {
        Boolean hasSeenearlier = dp.get(n);
        if(hasSeenearlier != null)
            return hasSeenearlier;
        boolean res = false;
        for(int i = 1; i <= 3; i++) {
            res |= !helper(n - i, dp);
        }
        dp.put(n, res);
        return dp.get(n);
    }

    public static void main(String[] args)  {
        for(int i = 0; i <= 12; i++) {
            System.out.println(canWinNim(i));
        }
    }
}

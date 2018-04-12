package com.exam.all.leap1d;

import java.util.Scanner;

/**
 * A hacker rank problem https://www.hackerrank.com/challenges/java-1d-array/problem
 * Move Backward: If cell i-1 exists and contains a zero, you can walk back to cell i-1.
 * Move Forward:
 * If cell i+1 contains a zero, you can walk to cell i+1.
 * If cell i+leap contains a zero, you can jump to cell i+leap.
 * If you're standing in cell n-1 or the value of i+leap >= n, you can walk or jump off the end of the array and win the game.
 *
 * In other words, you can move from index i to index i+1, i-1 or i+leap jump as long as the destination index is a cell containing a zero.
 * If the destination index is greater than n-1, you win the game.
 * <p>
 * Given leap and game(array), complete the function in the editor below so that it returns true if you can win the game (or false if you cannot).
 * <p>
 * Input Format
 * <p>
 * The first line contains an integer, q, denoting the number of queries (i.e., function calls).
 * The 2.q subsequent lines describe each query over two lines:
 * <p>
 * The first line contains two space-separated integers describing the respective values of n(size of array/game) and leap.
 * The second line contains  space-separated binary integers (i.e., zeroes and ones) describing the respective values of cells.
 *
 * It is guaranteed that the value of game[0] is always 0.
 */

public class Solution {
    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        return canWin(leap, game, 0);
    }

    public static boolean canWin(int leap, int[] game, int position) {
        if (position < 0 || game[position] == 1)
            return false;
        if (position == game.length - 1 || position + leap >= game.length)
            return true;
        //mark the cells already visited to avoid looping infinitely.
        game[position] = 1;
        return canWin(leap, game, leap + position) ||
                canWin(leap, game, position - 1) || canWin(leap, game, position + 1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println((canWin(leap, game)) ? "YES" : "NO");
        }
        scan.close();
    }
}

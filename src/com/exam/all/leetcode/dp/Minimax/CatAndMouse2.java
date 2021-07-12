package com.exam.all.leetcode.dp.Minimax;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/cat-and-mouse-ii/
 *
 * <p>A game is played by a cat and a mouse named Cat and Mouse.
 *
 * <p>The environment is represented by a grid of size rows x cols, where each element is a wall,
 * floor, player (Cat, Mouse), or food.
 *
 * <p>Players are represented by the characters 'C'(Cat),'M'(Mouse). Floors are represented by the
 * character '.' and can be walked on. Walls are represented by the character '#' and cannot be
 * walked on. Food is represented by the character 'F' and can be walked on. There is only one of
 * each character 'C', 'M', and 'F' in grid. Mouse and Cat play according to the following rules:
 *
 * <p>Mouse moves first, then they take turns to move. During each turn, Cat and Mouse can jump in
 * one of the four directions (left, right, up, down). They cannot jump over the wall nor outside of
 * the grid. catJump, mouseJump are the maximum lengths Cat and Mouse can jump at a time,
 * respectively. Cat and Mouse can jump less than the maximum length. Staying in the same position
 * is allowed. Mouse can jump over Cat. The game can end in 4 ways:
 *
 * <p>If Cat occupies the same position as Mouse, Cat wins. If Cat reaches the food first, Cat wins.
 * If Mouse reaches the food first, Mouse wins. If Mouse cannot get to the food within 1000 turns,
 * Cat wins. Given a rows x cols matrix grid and two integers catJump and mouseJump, return true if
 * Mouse can win the game if both Cat and Mouse play optimally, otherwise return false.
 *
 * <p>Example 1:
 *
 * <p>Input: grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
 * Output: true
 * Explanation:
 * Cat cannot catch Mouse on its turn nor can it get the food before Mouse. Example 2:
 *
 * <p>Input: grid = ["M.C...F"], catJump = 1, mouseJump = 4 Output: true Example 3:
 *
 * <p>Input: grid = ["M.C...F"], catJump = 1, mouseJump = 3 Output: false Example 4:
 *
 * <p>Input: grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5 Output: false
 * Example 5:
 *
 * <p>Input: grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 1 Output:
 * true
 *
 * <p>Constraints:
 *
 * <p>rows == grid.length cols = grid[i].length 1 <= rows, cols <= 8 grid[i][j] consist only of
 * characters 'C', 'M', 'F', '.', and '#'. There is only one of each character 'C', 'M', and 'F' in
 * grid. 1 <= catJump, mouseJump <= 8
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class CatAndMouse2 {
    Boolean[][][][][] dp;
    int cjump;
    int mjump;
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    char[][] gr;
    int available;
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int r = grid.length;
        int c = grid[0].length();
        dp = new Boolean[2000][r][c][r][c];

        this.cjump = catJump;
        this.mjump = mouseJump;
        int[] cp = new int[2];
        int[] mp = new int[2];
        gr = new char[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                gr[i][j] = grid[i].charAt(j);
                if (grid[i].charAt(j) != '#') {
                    available++;
                }
                if(grid[i].charAt(j) == 'C') {
                    cp = new int[]{i, j};
                } else if(grid[i].charAt(j) == 'M') {
                    mp = new int[]{i, j};
                }
            }
        }
        return helper(gr, 0, mp, cp);
    }

    private boolean helper(char[][] grid, int t, int[] mp, int[] cp) {
        int mj = mp[1];
        int mi = mp[0];
        int cj = cp[1];
        int ci = cp[0];
        if(mi == ci && mj == cj)
            return false;
        //if all positions are checked and still the game is on, it means mouse cannot get food and we can stop
        //this can be  checked with 2000 (1000 turns each) but it gives TLE.
        if(t >= available * 2)
            return false;
        if(grid[mi][mj] == 'F')
            return true;
        if(grid[ci][cj] == 'F')
            return false;

        if(dp[t][mi][mj][ci][cj] != null)
            return dp[t][mp[0]][mj][ci][cj];

        int who = t % 2;
        boolean win;
        if(who == 0) {
            win = false;
            List<int[]> next = moves(mi, mj, mjump);
            for(int[] move : next) {
                if(helper(grid, t+1, move, cp)) {
                    win = true;
                    break;
                }
            }
        } else {
            //we want mouse to win so we break for next move where cat wins(false is returned) or mouse fails.
            //else we will set dp[t][mi][mj][ci][cj] to true which is cat win as t is cat turn.
            win = true;
            List<int[]> next = moves(mi, cj, cjump);
            for(int[] move : next) {
                if(!helper(grid, t+1, mp, move)) {
                    win = false;
                    break;
                }
            }
        }
        dp[t][mi][mj][ci][cj] = win;
        return win;
    }

    private List<int[]> moves(int i, int j, int jump) {
        List<int[]> res = new ArrayList<>();
        res.add(new int[]{i, j});
        for(int[] dir : dirs) {
            for(int k = 1; k <= jump; j++) {
                int ni = i + k * dir[0];
                int nj = j + k * dir[1];

                if(ni < 0 || ni >= gr.length || nj < 0 || nj >= gr[0].length)
                    continue;

                if (gr[ni][nj] == '#') break;

                res.add(new int[]{ni, nj});
            }
        }
        return res;
    }
}

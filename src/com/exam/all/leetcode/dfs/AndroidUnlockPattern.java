package com.exam.all.leetcode.dfs;

/**
 * https://leetcode.com/problems/android-unlock-patterns/
 *
 * <p>Android devices have a special lock screen with a 3 x 3 grid of dots. Users can set an "unlock
 * pattern" by connecting the dots in a specific sequence, forming a series of joined line segments
 * where each segment's endpoints are two consecutive dots in the sequence. A sequence of k dots is
 * a valid unlock pattern if both of the following are true:
 *
 * <p>All the dots in the sequence are distinct. If the line segment connecting two consecutive dots
 * in the sequence passes through any other dot, the other dot must have previously appeared in the
 * sequence. No jumps through non-selected dots are allowed. Here are some example valid and invalid
 * unlock patterns:
 *
 * <p>The 1st pattern [4,1,3,6] is invalid because the line connecting dots 1 and 3 pass through dot
 * 2, but dot 2 did not previously appear in the sequence. The 2nd pattern [4,1,9,2] is invalid
 * because the line connecting dots 1 and 9 pass through dot 5, but dot 5 did not previously appear
 * in the sequence. The 3rd pattern [2,4,1,3,6] is valid because it follows the conditions. The line
 * connecting dots 1 and 3 meets the condition because dot 2 previously appeared in the sequence.
 * The 4th pattern [6,5,4,1,9,2] is valid because it follows the conditions. The line connecting
 * dots 1 and 9 meets the condition because dot 5 previously appeared in the sequence. Given two
 * integers m and n, return the number of unique and valid unlock patterns of the Android grid lock
 * screen that consist of at least m keys and at most n keys.
 *
 * <p>Two unlock patterns are considered unique if there is a dot in one sequence that is not in the
 * other, or the order of the dots is different.
 *
 * <p>Example 1:
 *
 * <p>Input: m = 1, n = 1 Output: 9 Example 2:
 *
 * <p>Input: m = 1, n = 2 Output: 65
 *
 * <p>Constraints:
 *
 * <p>1 <= m, n <= 9
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class AndroidUnlockPattern {
    int[][] skip;
    boolean[] vis;
    public int numberOfPatterns(int m, int n) {
        skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] =
                skip[2][8] = skip[8][2] = 5;

        vis = new boolean[10];

        int res = 0;
        res += dfs(1, m, n, 1) * 4;
        res += dfs(2, m, n, 1) * 4;
        res += dfs(5, m, n, 1);
        return res;
    }

    int dfs(int st, int m, int n, int cur) {
        if(cur == n)
            return 1;
        int res = 0;
        vis[st] = true;
        for(int i = 1; i <= 9; i++) {
            if(!vis[i] && (skip[st][i] == 0 || vis[skip[st][i]]))
                res += dfs(i, m, n, cur+1);
        }
        vis[st] = false;
        return res + (cur >= m ? 1 : 0);
    }

    public static void main(String[] args) {
        AndroidUnlockPattern aup = new AndroidUnlockPattern();
        System.out.println(aup.numberOfPatterns(1, 1));//res : 9
        System.out.println(aup.numberOfPatterns(1, 2));//res : 65
    }
}

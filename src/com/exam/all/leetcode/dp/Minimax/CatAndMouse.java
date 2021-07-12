package com.exam.all.leetcode.dp.Minimax;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/cat-and-mouse/
 *
 * <p>A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.
 *
 * <p>The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of
 * the graph.
 *
 * <p>The mouse starts at node 1 and goes first, the cat starts at node 2 and goes second, and there
 * is a hole at node 0.
 *
 * <p>During each player's turn, they must travel along one edge of the graph that meets where they
 * are. For example, if the Mouse is at node 1, it must travel to any node in graph[1].
 *
 * <p>Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)
 *
 * <p>Then, the game can end in three ways:
 *
 * <p>If ever the Cat occupies the same node as the Mouse, the Cat wins. If ever the Mouse reaches
 * the Hole, the Mouse wins. If ever a position is repeated (i.e., the players are in the same
 * position as a previous turn, and it is the same player's turn to move), the game is a draw. Given
 * a graph, and assuming both players play optimally, return
 *
 * <p>1 if the mouse wins the game, 2 if the cat wins the game, or 0 if the game is a draw.
 *
 * <p>Example 1:
 *
 * <p>Input: graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * Output: 0
 *
 * Example 2:
 *
 * <p>Input: graph = [[1,3],[0],[3],[0,2]]
 * Output: 1
 *
 * <p>Constraints:
 *
 * <p>3 <= graph.length <= 50 1 <= graph[i].length < graph.length 0 <= graph[i][j] < graph.length
 * graph[i][j] != i graph[i] is unique. The mouse and the cat can always move.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class CatAndMouse {
    //graph[0] =>  nodes connected  to 0 and so on.
//    https://leetcode.com/problems/cat-and-mouse/discuss/298937/DP-memory-status-search-search-strait-forward-and-easy-to-understand
    //additional resource ; https://cp-algorithms.com/game_theory/games_on_graphs.html
    //time complexity : O(n^3) actually O(n^2*maxOutDegree)
    int[][][] dp;
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        //first dimension => steps
        //2n because if cat and mouse make n moves each and did not meet, there is a cycle and cat cannot catch mouse so it is a draw
        dp = new int[2*n][n][n];
        for(int i  = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return helper(graph, 0, 1, 2);

    }

    /**
     *
     * @param graph
     * @param t move number
     * @param m mouse pos
     * @param c cat pos
     * @return
     */
    private int helper(int[][] graph, int t, int m, int c) {
        //draw as 2n moves have been made
        if(t == graph.length * 2) return 0;

        //cat wins as both cat and mouse are on same pos
        if(m == c)
            return dp[t][m][c] = 2;

        //mouse wins as it reached node 0
        if(m == 0)
            return dp[t][m][c] = 1;

        if(dp[t][m][c] != -1)
            return dp[t][m][c];

        int who = t % 2;
        boolean flag;
        //mouse start first, so if even turn it is mouse turn.
        if(who == 0) {
            flag = true; //consider cat wins by default ie., unless curr pos makes mouse win, cat is winner.
            for(int pos : graph[m]) {
                int nxt = helper(graph, t + 1, pos, c);
                if(nxt == 1)
                    return dp[t][m][c] = 1;
                //if it is draw, then flag is false;
                else if(nxt != 2)
                    flag = false;
            }
            //if cat wins with mouse at m and cat at c.
            if(flag)
                return dp[t][m][c] = 2;
            //if this line is reached it is draw, as if mouse wins, this call would return above only.
            else
                return dp[t][m][c] = 0;
        } else {
            flag = true; //consider mouse wins by default ie., unless curr pos makes cat win, mouse is winner
            for(int pos : graph[c]) {
                //cat cannot go to node 0.
                if(pos != 0) {
                    int nxt  = helper(graph, t + 1, m, pos);
                    if(nxt == 2)
                        return dp[t][m][c] = 2;
                    else if(nxt != 1)
                        flag = false;
                }
            }
            if(flag)
                return dp[t][m][c] = 1;
            else
                return dp[t][m][c] = 0;
        }
    }

    public static void main(String[] args) {
        CatAndMouse cm = new CatAndMouse();
        int[][] graph = new int[][]{{2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}};
        System.out.println(cm.catMouseGame(graph));

        graph = new int[][]{{1,3},{0},{3},{0,2}};
        System.out.println(cm.catMouseGame(graph));
    }
}

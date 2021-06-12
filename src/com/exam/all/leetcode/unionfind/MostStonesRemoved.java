package com.exam.all.leetcode.unionfind;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 *
 * <p>On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may
 * have at most one stone.
 *
 * <p>A stone can be removed if it shares either the same row or the same column as another stone
 * that has not been removed.
 *
 * <p>Given an array stones of length n where stones[i] = [xi, yi] represents the location of the
 * ith stone, return the largest possible number of stones that can be removed.
 *
 * <p>Example 1:
 *
 * <p>Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0]. Stone [0,0] cannot be removed since
 * it does not share a row/column with another stone still on the plane.
 *
 * Example 2:
 *
 * <p>Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]] Output: 3 Explanation: One way to make 3 moves
 * is as follows: 1. Remove stone [2,2] because it shares the same row as [2,0]. 2. Remove stone
 * [2,0] because it shares the same column as [0,0]. 3. Remove stone [0,2] because it shares the
 * same row as [0,0]. Stones [0,0] and [1,1] cannot be removed since they do not share a row/column
 * with another stone still on the plane. Example 3:
 *
 * <p>Input: stones = [[0,0]] Output: 0 Explanation: [0,0] is the only stone on the plane, so you
 * cannot remove it.
 *
 * <p>Constraints:
 *
 * <p>1 <= stones.length <= 1000 0 <= xi, yi <= 104 No two stones are at the same coordinate point.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MostStonesRemoved {
    /*
    uses union-find.
    The solution is, we can keep only one stone in each connected component and remove others
    So total stones removed is totalStones - connectedComponents
     */
    public static int removeStones(int[][] stones) {
        if(stones == null || stones.length == 0)
            return 0;

        int len = stones.length;
        int[] parent = new int[len];
        for(int i = 0; i < len; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < len - 1; i++) {
            for(int j = i+1; j < len; j++) {
                int[] s1 = stones[i];
                int[] s2 = stones[j];
                if(s1[0] == s2[0] || s1[1] == s2[1]) {
                    int p1 = findParent(i, parent);
                    int p2 = findParent(j, parent);
                    parent[p2] =  p1;
                }
            }
        }

        int connected = 0;
        for(int i = 0; i < len; i++) {
            if(parent[i] == i)
                connected++;
        }
        return len - connected;

    }

    static int findParent(int index, int[] parent) {
        int i = index;
        while(i != parent[i]) {
            i  = parent[i];
        }
        parent[index] = i;
        return i;
    }

    public static void main(String[] args) {
        int x = removeStones(new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}});
        System.out.println(x);

        x = removeStones(new int[][] {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}});
        System.out.println(x);

        x = removeStones(new int[][] {{0, 0}});
        System.out.println(x);
    }
}

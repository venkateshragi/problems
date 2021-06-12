package com.exam.all.leetcode.greedy;

import java.util.*;

/**
 * https://leetcode.com/problems/flower-planting-with-no-adjacent/
 *
 * <p>You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi]
 * describes a bidirectional path between garden xi to garden yi. In each garden, you want to plant
 * one of 4 types of flowers.
 *
 * <p>All gardens have at most 3 paths coming into or leaving it.
 *
 * <p>Your task is to choose a flower type for each garden such that, for any two gardens connected
 * by a path, they have different types of flowers.
 *
 * <p>Return any such a choice as an array answer, where answer[i] is the type of flower planted in
 * the (i+1)th garden. The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer
 * exists.
 *
 * <p>Example 1:
 *
 * <p>Input: n = 3, paths = [[1,2],[2,3],[3,1]]
 * Output: [1,2,3]
 * Explanation: Gardens 1 and 2 have
 * different types. Gardens 2 and 3 have different types. Gardens 3 and 1 have different types.
 * Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].
 *
 * Example 2:
 *
 * <p>Input: n = 4, paths = [[1,2],[3,4]] Output: [1,2,1,2]
 *
 * Example 3:
 *
 * <p>Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]] Output: [1,2,3,4]
 *
 * <p>Constraints:
 *
 * <p>1 <= n <= 104 0 <= paths.length <= 2 * 104 paths[i].length == 2 1 <= xi, yi <= n xi != yi
 * Every garden has at most 3 paths coming into or leaving it.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class FlowerPlantingWithNoAdj {

    public static int[] gardenNoAdj(int n, int[][] paths) {
        Map<Integer, Set<Integer>> G = new HashMap<>();
        for (int i = 0; i < n; i++)
            G.put(i, new HashSet<>());
        for (int[] p : paths) {
            //making 0 based indexes for gardens
            G.get(p[0] - 1).add(p[1] - 1);
            G.get(p[1] - 1).add(p[0] - 1);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int[] colors = new int[5];
            for (int j : G.get(i))
                //mark all colors that are occupied by adj gardens
                colors[res[j]] = 1;
            for (int c = 1; c <= 4; c++)
                if (colors[c] == 0) {
                    //pick one color that is not picked yet
                    res[i] = c;
                    break;
                }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] x = gardenNoAdj(3, new int[][]{{1, 2}, {2, 3}, {3, 1}});
        System.out.println(Arrays.toString(x));

        x = gardenNoAdj(4, new int[][]{{1, 2}, {3, 4}});
        System.out.println(Arrays.toString(x));

        x = gardenNoAdj(4, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 3}, {2, 4}});
        System.out.println(Arrays.toString(x));
    }

}

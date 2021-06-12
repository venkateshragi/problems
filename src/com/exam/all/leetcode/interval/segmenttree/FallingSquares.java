package com.exam.all.leetcode.interval.segmenttree;

import java.util.*;

/**
 * https://leetcode.com/problems/falling-squares/ There are several squares being dropped onto the
 * X-axis of a 2D plane.
 *
 * <p>You are given a 2D integer array positions where positions[i] = [lefti, sideLengthi]
 * represents the ith square with a side length of sideLengthi that is dropped with its left edge
 * aligned with X-coordinate lefti.
 *
 * <p>Each square is dropped one at a time from a height above any landed squares. It then falls
 * downward (negative Y direction) until it either lands on the top side of another square or on the
 * X-axis. A square brushing the left/right side of another square does not count as landing on it.
 * Once it lands, it freezes in place and cannot be moved.
 *
 * <p>After each square is dropped, you must record the height of the current tallest stack of
 * squares.
 *
 * <p>Return an integer array ans where ans[i] represents the height described above after dropping
 * the ith square.
 *
 * <p>Example 1:
 *
 * <p>Input: positions = [[1,2],[2,3],[6,1]]
 * Output: [2,5,5]
 * Explanation: After the first drop, the
 * tallest stack is square 1 with a height of 2. After the second drop, the tallest stack is squares
 * 1 and 2 with a height of 5. After the third drop, the tallest stack is still squares 1 and 2 with
 * a height of 5. Thus, we return an answer of [2, 5, 5]. Example 2:
 *
 * <p>Input: positions = [[100,100],[200,100]] Output: [100,100] Explanation: After the first drop,
 * the tallest stack is square 1 with a height of 100. After the second drop, the tallest stack is
 * either square 1 or square 2, both with heights of 100. Thus, we return an answer of [100, 100].
 * Note that square 2 only brushes the right side of square 1, which does not count as landing on
 * it.
 *
 * <p>Constraints:
 *
 * <p>1 <= positions.length <= 1000
 * 1 <= lefti <= 108
 * 1 <= sideLengthi <= 106
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class FallingSquares {

    public static Map<Integer, Integer> coordCompress(int[][] positions) {
        Set<Integer> pos = new HashSet<>();
        for(int[] position : positions) {
            pos.add(position[0]);
            // we are considering 1 less than complete square as brushing the left/right side of another
            // square does not count as landing on it
            pos.add(position[0] + position[1] - 1);
        }
        List<Integer> uniquePos = new ArrayList<>(pos);
        Collections.sort(uniquePos);
        Map<Integer, Integer>  posMap = new HashMap<>();
        int t = 0;
        for(int coord : uniquePos) {
            posMap.put(coord, t++);
        }
        return posMap;
    }

    /**
     * returns
     * n if it is power of 2 ie., 1, 2, 4, 8 or
     * immediate power of 2 that is greater than n
     * ex: if n = 5 returns 8
     * if n = 10 returns 16
     * @param n
     * @return
     */

    private static int nextPowOf2(int n) {
        //this implies even number
        if(n > 0 && (n & (n - 1)) == 0) {
            return n;
        }
        int count = 0;
        while(n != 0) {
            n >>= 1; //right shift by 1 => divide by 2
            count++;
        }
        //left shift 1 by count times here count => log n base 2
        return 1 << count;
    }

    static class SegmentTree {
        int[] tree;
        int[] lazy;
        int n;

        SegmentTree(int n) {
            this.n = n;
            /* here length can be 2*nxtPowof2 or 2*nxtPowof2 - 1
            if 2*nxtPowof2 is taken children of i will be at 2i and 2i + 1 and root is at 1
            if 2*nxtPowof2 - 1 is taken children of i will be at 2i+1 and 2i + 2 and root is at 0.
            Also, in case of n power of 2 no wastage of nodes.
             */
            int len = 2 * nextPowOf2(n) - 1;
            tree = new int[len];
            lazy = new int[len];
        }

        public int query(int L, int H) {
            return queryUtil(L, H, 0, n - 1, 0);
        }

        private int queryUtil(int L, int H, int start, int end, int index) {
            if(start > end)
                return 0;

            if(lazy[index] != 0) {
                updateLazyValue(start, end, index, lazy[index]);
                lazy[index] = 0;
            }

            //[start, end] does not overlap
            if(end < L || H < start) {
                return 0;
            }
            //complete overlap
            if(L <= start && end <= H) {
                return tree[index];
            }

            //partial overlap
            int mid = (start + end) / 2;
            return Math.max(queryUtil(L, H, start, mid, index * 2 + 1),
                    queryUtil(L, H, mid + 1, end, index * 2 + 2));
        }

        public void update(int L, int H, int newVal) {
            updateUtil(L, H, 0, n - 1, 0, newVal);
        }

        private void updateUtil(int L, int H, int start, int end, int index, int newVal) {
            if(start > end)
                return;

            if(lazy[index] != 0) {
                updateLazyValue(start, end, index, lazy[index]);
                lazy[index] = 0;
            }

            //[start, end] does not overlap
            if(end < L && H < start) {
                return;
            }

            //complete overlap
            if(L <= start && end <= H) {
                updateLazyValue(start, end, index, newVal);
                return;
            }

            //partial overlap
            int mid = (end + start) / 2;
            updateUtil(L, H, start, mid, index * 2 + 1, newVal);
            updateUtil(L, H, mid + 1, end, index * 2 + 2, newVal);
            tree[index] = Math.max(tree[index * 2 + 1], tree[index * 2 + 2]);
        }

        private void updateLazyValue(int start, int end, int index, int update) {
            tree[index] = Math.max(update, tree[index]);
            //implies this is not leaf node
            if(start != end) {
                lazy[(2*index) + 1] = Math.max(lazy[(2*index) + 1], update);
                lazy[(2*index) + 2] = Math.max(lazy[(2*index) + 2], update);
            }
        }
    }

    public static List<Integer> fallingSquares(int[][] positions) {
        Map<Integer, Integer> coords = coordCompress(positions);

        SegmentTree st = new SegmentTree(coords.size());
        List<Integer> result = new ArrayList<>();

        int max = 0;
        for(int[] position : positions) {
            int start = coords.get(position[0]);
            int end = coords.get(position[0] + position[1] - 1);
            int height = st.query(start, end) + position[1];
            st.update(start, end, height);

            result.add(Math.max(max, height));
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] positions = new int[][]{{1,2},{2,3},{6,1}};
        List<Integer> result = fallingSquares(positions);
        System.out.println(result);
        for(int i : result) {
            System.out.print(i + "  ");
        }


    }
}

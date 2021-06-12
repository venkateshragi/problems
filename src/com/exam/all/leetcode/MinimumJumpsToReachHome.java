package com.exam.all.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-jumps-to-reach-home/
 * A certain bug's home is on the x-axis at
 * position x. Help them get there from position 0.
 *
 * <p>The bug jumps according to the following rules:
 *
 * <p>It can jump exactly a positions forward (to the right). It can jump exactly b positions
 * backward (to the left). It cannot jump backward twice in a row. It cannot jump to any forbidden
 * positions. The bug may jump forward beyond its home, but it cannot jump to positions numbered
 * with negative integers.
 *
 * <p>Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the
 * position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for
 * the bug to reach its home. If there is no possible sequence of jumps that lands the bug on
 * position x, return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9 Output: 3 Explanation: 3 jumps forward
 * (0 -> 3 -> 6 -> 9) will get the bug home. Example 2:
 *
 * <p>Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11 Output: -1 Example 3:
 *
 * <p>Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7 Output: 2 Explanation: One jump
 * forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.
 *
 * <p>Constraints:
 *
 * <p>1 <= forbidden.length <= 1000 1 <= a, b, forbidden[i] <= 2000 0 <= x <= 2000 All the elements
 * in forbidden are distinct. Position x is not forbidden.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MinimumJumpsToReachHome {

    //BFS solution
    public static int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbiddenPlaces = new HashSet<>();
        for (int place : forbidden) {
            forbiddenPlaces.add(place);
        }

        /*each path entry in path is [steps, index, direction].
         * where direction = 0 => forward and 1 => backward movement
         */
        Queue<int[]> path = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        
        path.offer(new int[]{0, 0, 0});
        Set<String> visited  = new HashSet<>();
        /*the max values for a,b and x are 2000. So if bug jumps to 2000 + 2b position and over,
         as only one backward movement is allowed it will not be able to reach x.
         consider [1998], 1999, 2000, 2000 params
         */
        int furthestPosition = 2000 + b + b;

        while(!path.isEmpty()) {
            int[] index = path.poll();
            int steps = index[0];
            if(index[1] == x)
                return steps;
            int position = index[1];
            //moving forward
            if(position + a <= furthestPosition && !forbiddenPlaces.contains(position + a) &&
                    !visited.contains((position+a)+","+0)) {
                visited.add((position+a)+","+0);
                path.offer(new int[]{steps + 1, position + a, 0});
            }

            //moving backward
            if(index[2] != 1 && position - b >= 0 && !forbiddenPlaces.contains(position  - b) && !visited.contains((position - b) + ","+"1")) {
                visited.add((position - b)+","+1);
                path.offer(new int[]{steps + 1, position - b, 1});
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] forbidden = new int[]{14,4,18,1,15};
        int a = 3, b = 15, x = 9;
        System.out.println(minimumJumps(forbidden, a, b, x));

        forbidden = new int[]{8,3,16,6,12,20};
        a = 15;
        b = 13;
        x = 11;
        System.out.println(minimumJumps(forbidden, a, b, x));

        forbidden = new int[]{1,6,2,14,5,17,4};
        a = 16;
        b = 9;
        x = 7;
        System.out.println(minimumJumps(forbidden, a, b, x));

        forbidden = new int[]{1998};
        a = 1999;
        b = 2000;
        x = 2000;
        System.out.println(minimumJumps(forbidden, a, b, x));
    }
}

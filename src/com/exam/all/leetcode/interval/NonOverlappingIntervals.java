package com.exam.all.leetcode.interval;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * Given an array of intervals intervals
 * where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to
 * make the rest of the intervals non-overlapping.
 *
 * <p>Example 1:
 *
 * <p>Input:
 * intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and
 * the rest of the intervals are non-overlapping. Example 2:
 *
 * <p>Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to
 * make the rest of the intervals non-overlapping. Example 3:
 *
 * <p>Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the
 * intervals since they're already non-overlapping.
 *
 * <p>Constraints:
 *
 * <p>1 <= intervals.length <= 2 * 10 pow 4
 * intervals[i].length == 2
 * -2 * 10 pow 4 <= starti < endi <= 2 * 10 pow 4
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class NonOverlappingIntervals {

    //we are counting number of intervals(non overlapping) to be in the final set
    public static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;

        //sort based on end positions
        Arrays.sort(intervals, (i1, i2) -> i1[1] - i2[1]);

        //we are counting the intervals that are non overlapping
        //so finalResult = totalElements -  count => min number of intervals to be removed

        int countOfIncludedIntervals = 1;

        //we have to compare the 'end' that is farther so that it overlaps with many and removing
        //this one interval with this 'end' might make many non overlapping intervals.
        //in other words, the 'end' that is farther will have more chances of overlapping with many.
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= end) {
                end = intervals[i][1];
                countOfIncludedIntervals++;
            }
        }
        return intervals.length - countOfIncludedIntervals;
    }

    //we are counting number of intervals to be removed so that others are non overlapping
    public static int eraseOverlapIntervalsSortStarts(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;

        //sort based on start positions
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        int count = 0;
        int prev = 0;
        for(int i = 1; i < intervals.length; i++) {
            //if curr.start >= prev.end we move prev pointer ie., curr and pre are not overlapping
            if(intervals[i][0] >= intervals[prev][1]) {
                prev = i;
            } else {
                //in this case we are removing prev so moving prev to curr
                if(intervals[prev][1] > intervals[i][1]) {
                    prev = i;
                }
                //in case curr.end > prev.end we remove curr and do not change prev
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,2},{2,3},{3,4},{1,3}};
        System.out.println(eraseOverlapIntervals(intervals));

        intervals = new int[][]{{1,100},{11,22},{1,11},{2,12}};
        System.out.println(eraseOverlapIntervals(intervals));

        intervals = new int[][]{{1,2},{2,3},{3,4},{1,3}};
        System.out.println(eraseOverlapIntervalsSortStarts(intervals));

        intervals = new int[][]{{1,100},{11,22},{1,11},{2,12}};
        System.out.println(eraseOverlapIntervalsSortStarts(intervals));

    }
}

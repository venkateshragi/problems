package com.exam.all.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule-iii/
 *
 * <p>There are n different online courses numbered from 1 to n. You are given an array courses
 * where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken
 * continuously for durationi days and must be finished before or on lastDayi.
 *
 * <p>You will start on the 1st day and you cannot take two or more courses simultaneously.
 *
 * <p>Return the maximum number of courses that you can take.
 *
 * <p>Example 1:
 *
 * <p>Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]] Output: 3 Explanation: There
 * are totally 4 courses, but you can take 3 courses at most: First, take the 1st course, it costs
 * 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st
 * day. Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and
 * ready to take the next course on the 1101st day. Third, take the 2nd course, it costs 200 days so
 * you will finish it on the 1300th day. The 4th course cannot be taken now, since you will finish
 * it on the 3300th day, which exceeds the closed date. Example 2:
 *
 * <p>Input: courses = [[1,2]] Output: 1 Example 3:
 *
 * <p>Input: courses = [[3,2],[4,3]] Output: 0
 *
 * <p>Constraints:
 *
 * <p>1 <= courses.length <= 10 pow 4
 * 1 <= durationi, lastDayi <= 10 pow 4
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class CourseDurationSchedule {
    public static int scheduleCourse(int[][] courses) {
        //sort courses by endDate
        Arrays.sort(courses, (a,b) -> a[1]  - b[1]);

        //max heap storing duration of courses taken
        Queue<Integer> maxDurationQueue = new PriorityQueue<>((a,b) -> b -a);
        int time = 0;
        for(int[] currCourse  : courses) {
            time += currCourse[0];
            maxDurationQueue.add(currCourse[0]);
            //if time exceeds the actual end date of curr course
            if(time > currCourse[1])  {
                time -= maxDurationQueue.poll();
            }
        }
        return maxDurationQueue.size();
    }

  public static void main(String[] args) {
      int x = scheduleCourse(new int[][] {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}});
      System.out.println(x);

      x = scheduleCourse(new int[][] {{100, 200}});
      System.out.println(x);

      x = scheduleCourse(new int[][] {{3,2}, {4,3}});
      System.out.println(x);
    }
}

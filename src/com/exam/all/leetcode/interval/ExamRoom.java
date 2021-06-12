package com.exam.all.leetcode.interval;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/exam-room/
 *
 * <p>In an exam room, there are n seats in a single row, numbered 0, 1, 2, ..., n-1.
 *
 * <p>When a student enters the room, they must sit in the seat that maximizes the distance to the
 * closest person. If there are multiple such seats, they sit in the seat with the lowest number.
 * (Also, if no one is in the room, then the student sits at seat number 0.)
 *
 * <p>Return a class ExamRoom(int n) that exposes two functions: ExamRoom.seat() returning an int
 * representing what seat the student sat in, and ExamRoom.leave(int p) representing that the
 * student in seat number p now leaves the room. It is guaranteed that any calls to
 * ExamRoom.leave(p) have a student sitting in seat p.
 *
 * <p>Example 1:
 *
 * <p>Input:
 * ["ExamRoom","seat","seat","seat","seat","leave","seat"],
 * [[10],[],[],[],[],[4],[]]
 * Output: [null,0,9,4,2,null,5]
 * Explanation:
 * ExamRoom(10) -> null
 * seat() -> 0, no one is in the room, then the student sits at seat number 0.
 * seat() -> 9, the student sits at the last seat number 9.
 * seat() -> 4, the student sits at the last seat number 4.
 * seat() -> 2, the student sits at the last seat number 2.
 * leave(4) -> null
 * seat() -> 5, the student sits at the last seat number 5.
 *
 * <p>Note:
 *
 * <p>1 <= n <= 10^9
 * ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
 * Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class ExamRoom {

    PriorityQueue<int[]> queue;
    int n;

    //to sort the ranges by distances.
    private int distance(int[] range) {
        if(range[0] == -1)
            return range[1];
        if(range[1] == n)
            return n - 1 - range[0];
        return (range[1] - range[0]) / 2;
    }

    public ExamRoom(int n) {
        queue = new PriorityQueue<int[]>((a1, a2) -> {
            int dist1 = distance(a1);
            int dist2 = distance(a2);
            if(dist1 == dist2)
                return a1[0] - a2[0];
            return dist2 - dist1;
        });
        this.n = n;
        //to handle edge cases simply adding -1 and n
        queue.offer(new int[]{-1, n});
    }

    public int seat() {
        int[] range = queue.poll();
        int seat = 0;
        if(range[0] == -1)
            seat = 0;
        else if(range[1] == n)
            seat = n - 1;
        else
            seat = range[0] + (range[1] - range[0]) / 2;

        queue.offer(new int[]{range[0], seat});
        queue.offer(new int[]{seat, range[1]});
        return seat;
    }

    public void leave(int p) {
        int[] left = null;
        int[] right = null;
        for(int[] range : queue) {
            if(range[0] == p)
                right = range;
            if(range[1] == p)
                left = range;
        }
        queue.remove(left);
        queue.remove(right);
        queue.offer(new int[]{left[0], right[1]});

    }

    public static void main(String[] args) {
        ExamRoom ex = new ExamRoom(10);
        System.out.println(ex.seat());
        System.out.println(ex.seat());
        System.out.println(ex.seat());
        System.out.println(ex.seat());
        ex.leave(4);
        System.out.println(ex.seat());
    }
}

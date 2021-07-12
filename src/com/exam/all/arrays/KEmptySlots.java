package com.exam.all.arrays;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/k-empty-slots/
 *
 * <p>You have n bulbs in a row numbered from 1 to n. Initially, all the bulbs are turned off. We
 * turn on exactly one bulb every day until all bulbs are on after n days.
 *
 * <p>You are given an array bulbs of length n where bulbs[i] = x means that on the (i+1)th day, we
 * will turn on the bulb at position x where i is 0-indexed and x is 1-indexed.
 *
 * <p>Given an integer k, return the minimum day number such that there exists two turned on bulbs
 * that have exactly k bulbs between them that are all turned off. If there isn't such day, return
 * -1.
 *
 * <p>Example 1:
 *
 * <p>Input: bulbs = [1,3,2], k = 1
 * <p>Output: 2
 * <p>Explanation:
 * <p>On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
 * <p>On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
 * <p>On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
 * <p> We return 2 because on the second day, there were two on bulbs with one off bulb between them. Example 2:
 *
 * <p>Input: bulbs = [1,2,3], k = 1 Output: -1
 *
 * <p>Constraints:
 *
 * <p>n == bulbs.length 1 <= n <= 2 * 104 1 <= bulbs[i] <= n bulbs is a permutation of numbers from
 * 1 to n. 0 <= k <= 2 * 104
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class KEmptySlots {
    public static int kEmptySlots(int[] bulbs, int k) {
        TreeSet<Integer> days = new TreeSet<>();
        int d = 0;
        for(int i : bulbs) {
            days.add(i);
            d++;
            Integer next = days.higher(i);
            if(next != null && next - i == k + 1)
                return d;
            Integer lower = days.lower(i);
            if(lower != null && i - lower == k + 1)
                return d;
        }
        return -1;
    }

    public static void main (String[] args) {
        int arr[] = {6,5,8,9,7,1,10,2,3,4};
        System.out.println(kEmptySlots(arr, 2));

        arr = new int[]{3,9,2,8,1,6,10,5,4,7};
        System.out.println(kEmptySlots(arr, 1));
    }
}

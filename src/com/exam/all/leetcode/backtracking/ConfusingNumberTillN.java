package com.exam.all.leetcode.backtracking;

import java.util.HashMap;
import java.util.Map;

/**
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180
 * degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees,
 * they become invalid.
 *
 * <p>A confusing number is a number that when rotated 180 degrees becomes a different number with
 * each digit valid.(Note that the rotated number can be greater than the original number.)
 *
 * <p>Given a positive integer n, return the number of confusing numbers between 1 and n inclusive.
 *
 * <p>Example 1:
 *
 * <p>Input: n = 20 Output: 6 Explanation: The confusing numbers are [6,9,10,16,18,19]. 6 converts
 * to 9. 9 converts to 6. 10 converts to 01 which is just 1. 16 converts to 91. 18 converts to 81.
 * 19 converts to 61. Example 2:
 *
 * <p>Input: n = 100 Output: 19 Explanation: The confusing numbers are
 * [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 *
 * <p>Note:
 *
 * <p>1 <= n <= 109
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class ConfusingNumberTillN {

    Map<Integer, Integer> map = new HashMap<>();
    int res = 0;
    public int confusingNumberII(int N) {
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        helper(N, 0);
        return res;
    }
    private void helper(int N, long cur) {
        if (isConfusingNumber(cur)) {
            res++;
        }
        for (Integer i : map.keySet()) {
            if (cur * 10 + i <= N && cur * 10 + i != 0) {
                helper(N, cur * 10 + i);
            }
        }
    }
    private boolean isConfusingNumber(long n) {
        long src = n;
        long res = 0;
        while (n > 0) {
            res = res * 10 + map.get((int) n % 10);
            n /= 10;
        }
        return res != src;
    }

    public static void main(String[] args) {
        System.out.println(new ConfusingNumberTillN().confusingNumberII(1000000000));
    }


}

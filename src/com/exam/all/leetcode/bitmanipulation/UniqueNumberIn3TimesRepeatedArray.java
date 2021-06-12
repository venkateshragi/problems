package com.exam.all.leetcode.bitmanipulation;

/**
 * Given an array of integers, every element appears thrice except for one which occurs once.
 *
 * <p>Find that element which does not appear thrice.
 *
 * <p>Note: Your algorithm should have a linear runtime complexity.
 *
 * <p>Could you implement it without using extra memory?
 *
 * <p>Input Format:
 *
 * <p>First and only argument of input contains an integer array A Output Format:
 *
 * <p>return a single integer. Constraints:
 *
 * <p>2 <= N <= 5 000 000 0 <= A[i] <= INT_MAX For Examples :
 *
 * <p>Example Input 1: A = [1, 2, 4, 3, 3, 2, 2, 3, 1, 1] Example Output 1: 4 Explanation: 4 occur
 * exactly once
 *
 * <p>Example Input 2: A = [0, 0, 0, 1] Example Output 2: 1
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class UniqueNumberIn3TimesRepeatedArray {
    public int singleNumber(final int[] A) {
        int bits = 32;
        int res = 0;
        for(int i = 0; i < bits; i++) {
            int x = 1 << i;
            int sum = 0;
            for(int j = 0; j < A.length; j++) {
                if((A[j] & x) != 0)
                    sum++;
            }
            if(sum % 3 != 0)
                res |= x;
        }
        return res;
    }
}

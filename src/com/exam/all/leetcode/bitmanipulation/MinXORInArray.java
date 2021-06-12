package com.exam.all.leetcode.bitmanipulation;

import java.util.Arrays;

/**
 * Given an integer array A of N integers, find the pair of integers in the array which have minimum
 * XOR value. Report the minimum XOR value.
 *
 * <p>Input Format:
 *
 * <p>First and only argument of input contains an integer array A Output Format:
 *
 * <p>return a single integer denoting minimum xor value Constraints:
 *
 * <p>2 <= N <= 100 000 0 <= A[i] <= 1 000 000 000 For Examples :
 *
 * <p>Example Input 1: A = [0, 2, 5, 7] Example Output 1: 2 Explanation: 0 xor 2 = 2
 *
 * <p>Example Input 2: A = [0, 4, 7, 9] Example Output 2: 3
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MinXORInArray {

    //xor is min for nearest elements.
    public int findMinXor(int[] A) {
        Arrays.sort(A);
        int res = A[0] ^ A[1];
        for(int i = 2; i < A.length; i++) {
            res = Math.min(res, A[i] ^ A[i-1]);
        }
        return res;
    }
}

package com.exam.all.leetcode.bitmanipulation;

/**
 * Problem Description
 *
 * <p>Given a positive integer A, the task is to count the total number of set bits in the binary
 * representation of all the numbers from 1 to A.
 *
 * <p>Return the count modulo 109 + 7.
 *
 * <p>Problem Constraints 1 <= A <= 109
 *
 * <p>Input Format First and only argument is an integer A.
 *
 * <p>Output Format Return an integer denoting the ( Total number of set bits in the binary
 * representation of all the numbers from 1 to A )modulo 109 + 7.
 *
 * <p>Example Input Input 1:
 *
 * <p>A = 3 Input 2:
 *
 * <p>A = 1
 *
 * <p>Example Output Output 1:
 *
 * <p>4 Output 2:
 *
 * <p>1
 *
 * <p>Example Explanation Explanation 1:
 *
 * <p>DECIMAL BINARY SET BIT COUNT 1 01 1 2 10 1 3 11 2 1 + 1 + 2 = 4 Answer = 4 % 1000000007 = 4
 * Explanation 2:
 *
 * <p>A = 1 DECIMAL BINARY SET BIT COUNT 1 01 1 Answer = 1 % 1000000007 = 1
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class NumberOfSetBits {

    public int solve(int A) {
        int res = 0;
        int mod = 1000000007;
        int i = 1;
        while(i <= A) {
            res = res + (Integer.bitCount(i++)) % mod;
        }
        return res % mod;
    }
}
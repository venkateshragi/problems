package com.exam.all.leetcode.bitmanipulation;

/**
 * Problem Description
 *
 * <p>Reverse the bits of an 32 bit unsigned integer A.
 *
 * <p>Problem Constraints 0 <= A <= 232
 *
 * <p>Input Format First and only argument of input contains an integer A.
 *
 * <p>Output Format Return a single unsigned integer denoting the decimal value of reversed bits.
 *
 * <p>Example Input Input 1:
 *
 * <p>0 Input 2:
 *
 * <p>3
 *
 * <p>Example Output Output 1:
 *
 * <p>0 Output 2:
 *
 * <p>3221225472
 *
 * <p>Example Explanation Explanation 1:
 *
 * <p>00000000000000000000000000000000
 *
 * <p>=> 00000000000000000000000000000000 Explanation 2:
 *
 * <p>00000000000000000000000000000011 => 11000000000000000000000000000000
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class ReverseBitOfInteger {

    //complecity log (a) as we are right shifting by 1 in each iteration.
    public long reverse(long a) {
        long res = 0;
        int bits = 31;
        while(a > 0) {
            if((a & 1) == 1) {
                //when we right shift, if it is 1, we left shift 1 by its position in a and do | with res.
                res = res | (1L << bits);
            }
            bits--;
            a = a >> 1;
        }
        return res;
    }

    public long reverseAnother(long A) {
        long rev = 0;

        for (int i = 0; i < 32; i++) {
            rev <<= 1;
            if ((A & (1 << i)) != 0)
                rev |= 1;
        }

        return rev;

    }
}


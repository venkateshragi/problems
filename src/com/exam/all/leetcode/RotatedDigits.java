package com.exam.all.leetcode;

/**
 * https://leetcode.com/problems/rotated-digits/
 *
 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid
 * number that is different from X. Each digit must be rotated - we cannot choose to leave it alone.
 *
 * <p>A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to
 * themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the
 * numbers do not rotate to any other number and become invalid.
 *
 * <p>Now given a positive number N, how many numbers X from 1 to N are good?
 *
 * <p>Example:
 * Input: 10
 * Output: 4
 * Explanation: There are four good numbers in the range [1, 10] :
 * 2, 5, 6, 9. Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class RotatedDigits {

    public int rotatedDigits(int N) {
        int count = 0;
        for(int i = 1; i <= N; i++) {
            if(isValid(i, false))
                count++;
        }
        return count;
    }

    private boolean isValid(int x, boolean flag) {
        if(x == 0)
            return flag;
        int d = x % 10;
        if(d == 3 || d == 4 || d == 7)
            return false;
        if(d == 0 || d == 1 || d == 8)
            return isValid(x / 10, flag);
        return isValid(x/10, true);
    }
}

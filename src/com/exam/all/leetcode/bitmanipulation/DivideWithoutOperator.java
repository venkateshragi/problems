package com.exam.all.leetcode.bitmanipulation;

/**
 * Divide two integers without using multiplication, division and mod operator.
 *
 * <p>Return the floor of the result of the division.
 *
 * <p>Example:
 *
 * <p>5 / 2 = 2
 * <p> Also, consider if there can be overflow cases. For overflow case, return INT_MAX.
 *
 * <p>Note: INT_MAX = 2^31 - 1
 *
 * <p>Input 1:
 *
 * <p>-2147483648 1 Output : -2147483648
 *
 * <p>Input 2:
 *
 * <p>-2147483648 -1 Output : 2147483647
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class DivideWithoutOperator {

    //Explanation: https://www.youtube.com/watch?v=bdxJHWIyyqI
    public int divide(int A, int B) {
        int sign = (A<0 ^ B<0)?-1:1;
        long m = Math.abs((long)A);
        long n = Math.abs((long)B);
        long t=0;
        long q=0;
        for(long i=31;i>=0;i--) {
            //we are multiplyin divisor by 2 pow i.
            //as we are decreasing from 31 to 0 this give i such that divisor * (2 pow i) is just < dividend.
            if((t+(n<<i))<=m) {
                t+=(n<<i);
                q = q|(1L<<i);
            }
        }
        if(sign<0)q=-q;
        if(q>=Integer.MAX_VALUE || q<Integer.MIN_VALUE)return Integer.MAX_VALUE;
        return (int)q;
    }

    public int divideBySub(int A, int B) {
        int sign = 1;
        if((A < 0) ^ (B < 0)) {
            sign = -1;
        }
        long quotient = 0;
        long a = Math.abs((long)A);
        long b = Math.abs((long)B);
        while(a >= b) {
            a = a - b;
            quotient++;
        }
        if(sign == 1 && quotient > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return ((int)quotient) * sign;
    }
}

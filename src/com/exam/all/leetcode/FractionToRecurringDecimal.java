package com.exam.all.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 * Given two integers representing the numerator and denominator of a fraction, return the fraction
 * in string format.
 *
 * <p>If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * <p>Example 1:
 *
 * <p>Input: numerator = 1, denominator = 2 Output: "0.5" Example 2:
 *
 * <p>Input: numerator = 2, denominator = 1 Output: "2" Example 3:
 *
 * <p>Input: numerator = 2, denominator = 3 Output: "0.(6)"
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class FractionToRecurringDecimal {
     String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }

    public static void main(String[] args) {
        System.out.println(new FractionToRecurringDecimal().fractionToDecimal(22,7));
    }
}

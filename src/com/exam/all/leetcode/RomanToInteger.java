package com.exam.all.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * <p>Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000
 *
 * <p>For example, two is written as II in Roman numeral, just two one's added together. Twelve is
 * written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX
 * + V + II.
 *
 * <p>Roman numerals are usually written largest to smallest from left to right. However, the
 * numeral for four is not IIII. Instead, the number four is written as IV. Because the one is
 * before the five we subtract it making four. The same principle applies to the number nine, which
 * is written as IX. There are six instances where subtraction is used:
 *
 * <p>I can be placed before V (5) and X (10) to make 4 and 9. X can be placed before L (50) and C
 * (100) to make 40 and 90. C can be placed before D (500) and M (1000) to make 400 and 900. Given a
 * roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to
 * 3999.
 *
 * <p>Example 1:
 *
 * <p>Input: "III" Output: 3 Example 2:
 *
 * <p>Input: "IV" Output: 4 Example 3:
 *
 * <p>Input: "IX" Output: 9 Example 4:
 *
 * <p>Input: "LVIII" Output: 58 Explanation: L = 50, V= 5, III = 3. Example 5:
 *
 * <p>Input: "MCMXCIV" Output: 1994 Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class RomanToInteger {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0)
            return 0;

        Map<Character, Integer> charValuesMap = new HashMap<Character, Integer>();
        charValuesMap.put('I', 1);
        charValuesMap.put('V', 5);
        charValuesMap.put('X', 10);
        charValuesMap.put('L', 50);
        charValuesMap.put('C', 100);
        charValuesMap.put('D', 500);
        charValuesMap.put('M', 1000);

        int i = 0;
        int result = 0;

        while(i < s.length()) {
            char x = s.charAt(i);
            int curVal = charValuesMap.get(x);
            if(i+1 < s.length()) {
                char next = s.charAt(i+1);
                int nextVal = charValuesMap.get(next);
                if (nextVal > curVal) {
                    // Value of current symbol is
                    // less than the next symbol
                    result = result + nextVal - curVal;
                    i++;
                } else {
                    // Value of current symbol is greater
                    // or equal to the next symbol
                    result += curVal;
                }
            } else {
                result += curVal;
            }
            i++;
        }
        return result;
    }

    /*
    public int romanToInt(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int i = 0;
        int result = 0;
        while(i < s.length()) {
            char x = s.charAt(i);
            if(x == 'M')
                result += 1000;
            else if(x == 'D')
                result += 500;
            else if(x == 'L')
                result += 50;
            else if(x == 'V')
                result += 5;
            else if(x == 'C') {
                if(i+1 >= s.length()) {
                    result += 100;
                    break;
                }
                char next = s.charAt(i + 1);
                if(next == 'D') {
                    result += 400;
                    i++;
                }
                else if(next == 'M') {
                    result += 900;
                    i++;
                }
                else
                    result += 100;
            }
            else if(x == 'X') {
                if(i+1 >= s.length()) {
                    result += 10;
                    break;
                }
                char next = s.charAt(i + 1);
                if(next == 'L') {
                    result += 40;
                    i++;
                }
                else if(next == 'C') {
                    result += 90;
                    i++;
                }
                else
                    result += 10;
            }
            else if(x == 'I') {
                if(i+1 >= s.length()) {
                    result += 1;
                    break;
                }
                char next = s.charAt(i + 1);
                if(next == 'V') {
                    result += 4;
                    i++;
                }
                else if(next == 'X') {
                    result += 9;
                    i++;
                }
                else
                    result += 1;
            }
            i++;
        }
        return result;
    }
     */
}

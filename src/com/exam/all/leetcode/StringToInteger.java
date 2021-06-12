package com.exam.all.leetcode;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * <p>The algorithm for myAtoi(string s) is as follows:
 *
 * <p>Read in and ignore any leading whitespace. Check if the next character (if not already at the
 * end of the string) is '-' or '+'. Read this character in if it is either. This determines if the
 * final result is negative or positive respectively. Assume the result is positive if neither is
 * present. Read in next the characters until the next non-digit charcter or the end of the input is
 * reached. The rest of the string is ignored. Convert these digits into an integer (i.e. "123" ->
 * 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary
 * (from step 2). If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then
 * clamp the integer so that it remains in the range. Specifically, integers less than -231 should
 * be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1. Return the
 * integer as the final result. Note:
 *
 * <p>Only the space character ' ' is considered a whitespace character. Do not ignore any
 * characters other than the leading whitespace or the rest of the string after the digits.
 *
 * <p>Constraints:
 *
 * <p>0 <= s.length <= 200 s consists of English letters (lower-case and upper-case), digits (0-9),
 * ' ', '+', '-', and '.'.
 *
 * <p>Input: s = "-91283472332" Output: -2147483648 Explanation: Step 1: "-91283472332" (no
 * characters read because there is no leading whitespace) ^ Step 2: "-91283472332" ('-' is read, so
 * the result should be negative) ^ Step 3: "-91283472332" ("91283472332" is read in) ^ The parsed
 * integer is -91283472332. Since -91283472332 is less than the lower bound of the range [-231, 231
 * - 1], the final result is clamped to -231 = -2147483648.
 *
 * <p>Input: s = "words and 987" Output: 0
 *
 * <p>Input: s = "4193 with words" Output: 4193
 *
 * <p>Input: s = " -42" Output: -42
 *
 * <p>Input: s = "42" Output: 42
 *
 * <p>Input: s = "+-42" Output: 0
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class StringToInteger {

    public int myAtoi(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int i = 0;
        while(i < s.length() && s.charAt(i) == ' ')
            i++;

        int sign = 1;
        if(i < s.length()) {
            if(s.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if(s.charAt(i) == '+') {
                sign = 1;
                i++;
            }
        }

        int result = 0;
        while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            if(result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && s.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + (s.charAt(i) - '0');
            i++;
        }
        return result * sign;
    }

}
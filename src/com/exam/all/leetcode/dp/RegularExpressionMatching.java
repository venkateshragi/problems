package com.exam.all.leetcode.dp;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * <p>Given an input string (s) and a pattern (p), implement regular expression matching with
 * support for '.' and '*' where:
 *
 * <p>'.' Matches any single character.​​​​ '*' Matches zero or more of the preceding element. The
 * matching should cover the entire input string (not partial).
 *
 * <p>Example 1:
 *
 * <p>Input: s = "aa", p = "a" Output: false Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * <p>Input: s = "aa", p = "a*" Output: true Explanation: '*' means zero or more of the preceding
 * element, 'a'. Therefore, by repeating 'a' once, it becomes "aa". Example 3:
 *
 * <p>Input: s = "ab", p = ".*" Output: true Explanation: ".*" means "zero or more (*) of any
 * character (.)". Example 4:
 *
 * <p>Input: s = "aab", p = "c*a*b" Output: true Explanation: c can be repeated 0 times, a can be
 * repeated 1 time. Therefore, it matches "aab". Example 5:
 *
 * <p>Input: s = "mississippi", p = "mis*is*p*." Output: false
 *
 * <p>Constraints:
 *
 * <p>0 <= s.length <= 20 0 <= p.length <= 30 s contains only lowercase English letters. p contains
 * only lowercase English letters, '.', and '*'. It is guaranteed for each appearance of the
 * character '*', there will be a previous valid character to match.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class RegularExpressionMatching {

    //recursion
    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    private static boolean[][] topDowndp;
    public static boolean isMatchTopDownDp(String text, String pattern) {
        topDowndp = new boolean[text.length()][pattern.length()];
        return false;

    }

    private static boolean topDownDp(int i, int j, String text, String patter) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "aab"));
    }
}

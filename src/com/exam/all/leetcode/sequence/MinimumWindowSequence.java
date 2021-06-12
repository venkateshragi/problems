package com.exam.all.leetcode.sequence;

/**
 * https://leetcode.com/problems/minimum-window-subsequence/
 *
 * <p>Given strings s1 and s2, find the minimum (contiguous) substring part of s1, so that s2 is a
 * subsequence of part.
 *
 * <p>If there is no such window in s1 that covers all characters in s2, return the empty string "".
 * If there are multiple such minimum-length windows, return the one with the left-most starting
 * index.
 *
 * <p>Example 1:
 *
 * <p>Input: s1 = "abcdebdde", s2 = "bde"
 * Output: "bcde"
 * Explanation: "bcde" is the answer because
 * it occurs before "bdde" which has the same length. "deb" is not a smaller window because the
 * elements of s2 in the window must occur in order.
 *
 * <p>Note:
 *
 * <p>All the strings in the input will only contain lowercase letters.
 * The length of s1 will be in the range [1, 20000].
 * The length of s2 will be in the range [1, 100].
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MinimumWindowSequence {
    public static String minWindow(String s1, String s2) {
        String result = "";
        int minlen = s1.length()+1;

        int lft = -1, rgt = 0;
        while(true)  {
            for(char c: s2.toCharArray()) {
                lft = s1.indexOf(c, lft+1);
                if(lft == -1)
                    return result;
            }

            rgt = ++lft;
            for(int j = s2.length() - 1; j >= 0; j--) {
                char c = s2.charAt(j);
                lft = s1.lastIndexOf(c, lft - 1);
            }
            int len = rgt - lft;
            if(len < minlen) {
                minlen = len;
                result = s1.substring(lft, rgt);
            }
            lft = lft + 1;
        }
    }

    public static void main(String[] args) {
        String s = minWindow("ffynmlzesdshlvugsigobutgaetsnjlizvqjdpccdylclqcbghhixpjihximvhapymfkjxyyxfwvsfyctmhwmfjyjidnfryiyajmtakisaxwglwpqaxaicuprrvxybzdxunypzofhpclqiybgniqzsdeqwrdsfjyfkgmejxfqjkmukvgygafwokeoeglanevavyrpduigitmrimtaslzboauwbluvlfqquocxrzrbvvplsivujojscytmeyjolvvyzwizpuhejsdzkfwgqdbwinkxqypaphktonqwwanapouqyjdbptqfowhemsnsl",
                "ntimcimzah");
        System.out.println(s);
    }
}

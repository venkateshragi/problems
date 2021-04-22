package com.exam.all.leetcode;

/**
 * https://leetcode.com/problems/custom-sort-string/ S and T are strings composed of lowercase
 * letters. In S, no letter occurs more than once.
 *
 * <p>S was sorted in some custom order previously. We want to permute the characters of T so that
 * they match the order that S was sorted. More specifically, if x occurs before y in S, then x
 * should occur before y in the returned string.
 *
 * <p>Return any permutation of T (as a string) that satisfies this property.
 *
 * <p>Example :
 *
 * <p>Input: S = "cba" T = "abcd"
 *
 * <p>Output: "cbad"
 *
 * <p>Explanation: "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and
 * "a". Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are
 * also valid outputs.
 *
 * <p>Note:
 *
 * <p>S has length at most 26, and no character is repeated in S.
 *
 * <p>T has length at most 200.
 *
 * <p>S and T consist of lowercase letters only.
 *
 * <p>Complexity Analysis
 *
 * <p>Time Complexity: O(S.\text{length} + T.\text{length})O(S.length+T.length), the time it takes
 * to iterate through S and T
 *
 * <p>Space Complexity: O(T.\text{length})O(T.length). We count at most 26 different lowercase
 * letters, but the final answer has the same length as T.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class CustomSortString {

    public String customSortString(String S, String T) {
        // count[char] = the number of occurrences of 'char' in T.
        // This is offset so that count[0] = occurrences of 'a', etc.
        // 'count' represents the current state of characters
        // (with multiplicity) we need to write to our answer.
        int[] count = new int[26];
        for(char c : T.toCharArray()) {
            count[c - 'a']++;
        }

        // ans will be our final answer.  We use StringBuilder to join
        // the answer so that we more efficiently calculate a
        // concatenation of strings.
        StringBuilder result = new StringBuilder();

        // Write all characters that occur in S, in the order of S.
        for(char c : S.toCharArray()) {
            int temp = count[c - 'a'];
            while(temp > 0) {
                result.append(c);
                temp--;
            }
            count[c - 'a'] = 0;
        }

        // Write all remaining characters that don't occur in S.
        // That information is specified by 'count'.
        for(char c : T.toCharArray()) {
            int temp = count[c - 'a'];
            count[c - 'a'] = 0;
            while(temp > 0) {
                result.append(c);
                temp--;
            }
        }
        return result.toString();

    }

    public static void main(String[] args) {
//        System.out.println(new CustomSortString().customSortString("cba", "abcd"));
//        System.out.println(new CustomSortString().customSortString("kqep", "pekeq")); //ans: kqeep
        //ans: "hhhhhuucccwaaaaaaaaabbdddddeffffggggiijjjjkkkkllllmmmmnnnoooopppqqqqqqqqqqqrsssttttttttvxxxxxyyzzzzz"
        System.out.println(new CustomSortString().customSortString("hucw",
                "utzoampdgkalexslxoqfkdjoczajxtuhqyxvlfatmptqdsochtdzgypsfkgqwbgqbcamdqnqztaqhqanirikahtmalzqjjxtqfnh"));

    }

}

package com.exam.all.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/sentence-screen-fitting/
 *
 * <p>Given a rows x cols screen and a sentence represented as a list of strings, return the number
 * of times the given sentence can be fitted on the screen.
 *
 * <p>The order of words in the sentence must remain unchanged, and a word cannot be split into two
 * lines. A single space must separate two consecutive words in a line.
 *
 * <p>Example 1:
 *
 * <p>Input: sentence = ["hello","world"], rows = 2, cols = 8 Output:1
 * Explanation:
 * hello---
 * world---
 * The character '-' signifies an empty space on the screen. Example 2:
 *
 * <p>Input: sentence = ["a", "bcd", "e"], rows = 3, cols = 6 Output: 2
 * Explanation:
 * a-bcd-
 * e-a---
 * bcd-e-
 * The character '-' signifies an empty space on the screen. Example 3:
 *
 * <p>Input: sentence = ["i","had","apple","pie"], rows = 4, cols = 5 Output: 1
 * Explanation:
 * i-had
 * apple
 * pie-i
 * had--
 * The character '-' signifies an empty space on the screen.
 *
 * <p>Constraints:
 *
 * <p>1 <= sentemce.length <= 100
 * 1 <= sentence[i].length <= 10
 * sentence[i] consists of lowercase English letters.
 * 1 <= rows, cols <= 2 * 104
 *
 * One well documented approach https://leetcode.com/problems/sentence-screen-fitting/discuss/1051671/Java-well-documented-DP-solution-that-runs-in-4ms
 *
 * @author vragi
 */
public class SentenceScreenFitting {

    /* https://leetcode.com/problems/sentence-screen-fitting/discuss/90845/21ms-18-lines-Java-solution
     We calculate the number of valid characters from complete sentence that fit in the  matrix.
     valid character is count of length of all words properly set in each row ie., no overflow of a word starting in one
     row and continuing to next row.
     https://leetcode.com/problems/sentence-screen-fitting/discuss/90845/21ms-18-lines-Java-solution
    */
    public static int wordsTyping(String[] sentence, int rows, int cols) {

        //we have to find how many times the  following fits in matrix without any overflowing word.
        String completeSentence = String.join(" ", sentence) + " ";
        int length = completeSentence.length();

        int validCharsCount = 0;
        for(int i = 0; i < rows; i++) {
            validCharsCount += cols;
            //'%' helps when validCharsCount overflows ie., prev completeSentence ends and few chars from beginning
            // (next occurrence of completeSentence starts) occurs in same row.
            if(completeSentence.charAt(validCharsCount % length) == ' ') {
                //move to next position
                validCharsCount++;
            } else {
                while (validCharsCount > 0 && completeSentence.charAt((validCharsCount - 1) % length) != ' ') {
                    validCharsCount--;
                }
            }
        }
        return validCharsCount / completeSentence.length();
    }

    public static void main(String[] args) {
        args = new String[]{"hello","world"};
        System.out.println(wordsTyping(args, 2, 8));

        args = new String[]{"a", "bcd", "e"};
        System.out.println(wordsTyping(args, 3, 6));

        args = new String[]{"i","had","apple","pie"};
        System.out.println(wordsTyping(args, 4, 5));
    }
}

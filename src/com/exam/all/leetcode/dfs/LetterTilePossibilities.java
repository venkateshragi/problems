package com.exam.all.leetcode.dfs;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/letter-tile-possibilities/
 *
 * <p>You have n tiles, where each tile has one letter tiles[i] printed on it.
 *
 * <p>Return the number of possible non-empty sequences of letters you can make using the letters
 * printed on those tiles.
 *
 * <p>Example 1:
 *
 * <p>Input: tiles = "AAB" Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 *
 * Example 2:
 *
 * <p>Input: tiles = "AAABBC" Output: 188 Example 3:
 *
 * <p>Input: tiles = "V" Output: 1
 *
 * <p>Constraints:
 *
 * <p>1 <= tiles.length <= 7 tiles consists of uppercase English letters.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class LetterTilePossibilities {

    public static int numTilePossibilities(String tiles) {
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        boolean[] visited = new boolean[chars.length];
        return dfs(chars,visited);
    }

    private static int dfs(char[] chars, boolean[] visited){
        int count = 0;
        for(int i = 0; i < chars.length; i++){
            if(visited[i]) continue;
            /*
            A1 => first A, A2 => second  A
            Take the input tile of "A1A2B" for example. Say we are building the current path as "A1".
            When we go to visit A2, according to if(i - 1 >= 0 && chars[i] == chars[i - 1] && visited[i - 1]) continue;,
            we should decide to abandon A2 cause A1 is marked as visited, right? But actually "A1A2" should be a valid answer.
            The purpose of if(i - 1 >= 0 && chars[i] == chars[i - 1] && !visited[i - 1]) is to avoid adding same char at the
            same position like "BA1" and "BA2".
             */
            if(i - 1 >= 0 && chars[i] == chars[i - 1] && !visited[i - 1]) continue;
            //the resulting string contains the chars in the order in which visited[i] is set true;
            visited[i] = true;
            count++;
            count+=dfs(chars, visited);
            visited[i] = false;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numTilePossibilities("AAAB"));

    }

}

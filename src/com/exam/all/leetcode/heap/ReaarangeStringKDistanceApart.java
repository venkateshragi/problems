package com.exam.all.leetcode.heap;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/
 *
 * <p>Given a string s and an integer k, rearrange s such that the same characters are at least
 * distance k from each other. If it is not possible to rearrange the string, return an empty string
 * "".
 *
 * <p>Example 1:
 *
 * <p>Input: s = "aabbcc", k = 3 Output: "abcabc" Explanation: The same letters are at least a
 * distance of 3 from each other. Example 2:
 *
 * <p>Input: s = "aaabc", k = 3 Output: "" Explanation: It is not possible to rearrange the string.
 * Example 3:
 *
 * <p>Input: s = "aaadbbcc", k = 2 Output: "abacabcd" Explanation: The same letters are at least a
 * distance of 2 from each other.
 *
 * <p>Constraints:
 *
 * <p>1 <= s.length <= 3 * 105 s consists of only lowercase English letters. 0 <= k <= s.length
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class ReaarangeStringKDistanceApart {
    public String rearrangeString(String s, int k) {
        if(k == 0)
            return s;

        int[] freq = new int[26];
        for(char c : s.toCharArray())
            freq[c - 'a']++;

        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for(int i = 0; i < 26; i++) {
            if(freq[i] > 0)
                pq.add(new int[]{i, freq[i]});
        }

        Queue<int[]> temp = new LinkedList<>();
        while(!pq.isEmpty()) {
            int[] nxt = pq.poll();
            nxt[1]--;
            sb.append((char)(nxt[0] + 'a'));

            temp.add(nxt);
            if(temp.size() == k) {
                int[] ch = temp.poll();
                if(ch[1] > 0)
                    pq.add(ch);
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        ReaarangeStringKDistanceApart ob  = new ReaarangeStringKDistanceApart();
        System.out.println(ob.rearrangeString("aabbcc", 3));  //abcabc
        System.out.println(ob.rearrangeString("aaabc", 3)); //""
    }
}

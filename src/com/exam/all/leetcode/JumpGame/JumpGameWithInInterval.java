package com.exam.all.leetcode.JumpGame;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/jump-game-vii/
 *
 * <p>You are given a 0-indexed binary string s and two integers minJump and maxJump. In the
 * beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index
 * j if the following conditions are fulfilled:
 *
 * <p>i + minJump <= j <= min(i + maxJump, s.length - 1), and s[j] == '0'. Return true if you can
 * reach index s.length - 1 in s, or false otherwise.
 *
 * <p>Example 1:
 *
 * <p>Input: s = "011010", minJump = 2, maxJump = 3 Output: true Explanation: In the first step,
 * move from index 0 to index 3. In the second step, move from index 3 to index 5. Example 2:
 *
 * <p>Input: s = "01101110", minJump = 2, maxJump = 3 Output: false
 *
 * <p>Constraints:
 *
 * <p>2 <= s.length <= 10 pow 5
 * s[i] is either '0' or '1'.
 * s[0] == '0'
 * 1 <= minJump <= maxJump < s.length
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class JumpGameWithInInterval {
    //O(n)
    public static boolean canReach(String s, int minJump, int maxJump) {
        int count = 0;
        boolean[] dp = new boolean[s.length()];
        dp[0] = true;
        int i = 1;
        while(i < s.length()) {
            //pos + maxJump = i. So we are checking 1 place before pos => subtract 1(so -1).
            //also, if we can reach pos - 1, it means we cannot reach this i from pos-1. so decr count.
            //but if there is any way of reaching i then count is incr in next if loop.
            if(i > maxJump && dp[i - maxJump - 1]) {
                count--;
            }
            //i can be in [pos+minJump, pos+maxJump] => i is reachable from pos. So check if we can reach pos(ie., i-minJump)
            //here i >= minJump as we have to consider minJump as well. This will be checked for all positions >= minJump
            //even > pos+maxJump but count would be decr anyways above.
            if(i >= minJump && dp[i - minJump]) {
                count++;
            }
            dp[i] = count > 0 &&  s.charAt(i) == '0';
            i++;
        }
        return dp[s.length() - 1];
    }

    //treeSet sorts pos that are reachable. And thus O(nlogn)
    public static boolean canReachUsingTreeSet(String s, int minJump, int maxJump) {
        TreeSet<Integer> reachables = new TreeSet<>();
        reachables.add(0);
        int i = 1;
        while(i < s.length()) {
            if(s.charAt(i) == '0') {
                //we are checking if there is a pos reachable before i.
                //and if i is <= pos + maxJump.
                Integer lower = reachables.floor(i - minJump);
                if(lower != null && lower + maxJump >= i) {
                    reachables.add(i);
                }
            }
            i++;
        }
        return reachables.last() == s.length() - 1;
    }

    //same as treeset, but we are removing entries < i - maxJump so that queue has entries in the range
    //pos-minJump and pos-maxJump.
    public static boolean canReachUsingQueue(String s, int minJump, int maxJump) {
        int i = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        while(++i < s.length()){
            while(!q.isEmpty() && q.peek() < i-maxJump){
                q.poll();
            }
            if(s.charAt(i) == '0' && !q.isEmpty() && q.peek() <= i-minJump){
                q.add(i);
            }
        }
        return !q.isEmpty() && q.peekLast() == s.length()-1;
    }

    public static void main (String[] args) {
        System.out.println(canReach("011010", 2, 3));
        System.out.println(canReach("01101110", 2, 3));
        System.out.println(canReach("011101101110", 3, 4));

        System.out.println(canReachUsingTreeSet("011010", 2, 3));
        System.out.println(canReachUsingTreeSet("01101110", 2, 3));
        System.out.println(canReachUsingTreeSet("011101101110", 3, 4));

        System.out.println(canReachUsingQueue("011010", 2, 3));
        System.out.println(canReachUsingQueue("01101110", 2, 3));
        System.out.println(canReachUsingQueue("011101101110", 3, 4));

    }
}

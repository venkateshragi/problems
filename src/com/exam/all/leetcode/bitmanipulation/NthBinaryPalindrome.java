package com.exam.all.leetcode.bitmanipulation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/find-n-th-number-whose-binary-representation-palindrome/
 * @author vragi
 * @since <<buildnumber>>
 */
public class NthBinaryPalindrome {

    // utility function which is used to
    // convert binary string into integer
    public static int convertStringToInt(String s)
    {
        int ans = 0;

        // convert binary string into integer
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1')
                ans += 1 << i;
        }
        return ans;
    }

    // function to find nth binary palindrome number
    public static int getNthNumber(int n)
    {
        // stores the binary palindrome string
        Queue<String> q = new LinkedList<>();

        // base case
        if (n == 1)
            return 1;
        n = n - 1;

        // add 2nd binary palindrome string
        q.add("11");

        // runs till the nth binary palindrome number
        while (n-- > 0) {

            // remove curr binary palindrome string from
            // queue
            String curr = q.remove();

            // if n==0 then we find the n'th binary
            // palindrome so we return our answer
            if (n == 0)
                return convertStringToInt(curr);

            // calculate length of curr binary palindrome
            // string
            int len = curr.length();

            // if length is even .so it is our first case
            // we have two choices
            if (len % 2 == 0) {
                q.add(curr.substring(0, len / 2) + "0"
                        + curr.substring(len / 2));
                q.add(curr.substring(0, len / 2) + "1"
                        + curr.substring(len / 2));
            }

            // if length is odd .so it is our second case
            // we have only one choice
            else {
                char midChar = curr.charAt(len / 2);
                q.add(curr.substring(0, len / 2) + midChar
                        + curr.substring(len / 2));
            }
        }
        return -1;
    }

    // Driver code
    public static void main(String[] args)
    {
        int n = 9;

        // Function Call
        System.out.println(getNthNumber(n));
    }
}

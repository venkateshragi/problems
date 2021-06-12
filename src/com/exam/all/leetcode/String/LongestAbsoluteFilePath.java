package com.exam.all.leetcode.String;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-absolute-file-path/ Suppose we have a file system that
 * stores both files and directories. An example of one system is represented in the following
 * picture:
 *
 * <p>Here, we have dir as the only directory in the root. dir contains two subdirectories, subdir1
 * and subdir2. subdir1 contains a file file1.ext and subdirectory subsubdir1. subdir2 contains a
 * subdirectory subsubdir2, which contains a file file2.ext.
 *
 * <p>In text form, it looks like this (with ⟶ representing the tab character):
 *
 * <p>dir
 * ⟶ subdir1
 * ⟶ ⟶ file1.ext
 * ⟶ ⟶ subsubdir1
 * ⟶ subdir2
 * ⟶ ⟶ subsubdir2
 * ⟶ ⟶ ⟶ file2.ext
 *
 * If we were
 * to write this representation in code, it will look like this:
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext". Note
 * that the '\n' and '\t' are the new-line and tab characters.
 *
 * <p>Every file and directory has a unique absolute path in the file system, which is the order of
 * directories that must be opened to reach the file/directory itself, all concatenated by '/'s.
 * Using the above example, the absolute path to file2.ext is "dir/subdir2/subsubdir2/file2.ext".
 * Each directory name consists of letters, digits, and/or spaces. Each file name is of the form
 * name.extension, where name and extension consist of letters, digits, and/or spaces.
 *
 * <p>Given a string input representing the file system in the explained format, return the length
 * of the longest absolute path to a file in the abstracted file system. If there is no file in the
 * system, return 0.
 *
 * <p>Example 1:
 *
 * <p>Input: input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
 * Output: 20
 * Explanation: We have only
 * one file, and the absolute path is "dir/subdir2/file.ext" of length 20. Example 2:
 *
 * <p>Input:
 * input =
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 * Output: 32
 * Explanation: We have two files: "dir/subdir1/file1.ext" of length 21
 * "dir/subdir2/subsubdir2/file2.ext" of length 32. We return 32 since it is the longest absolute
 * path to a file. Example 3:
 *
 * <p>Input: input = "a" Output: 0
 * Explanation: We do not have any files, just a single directory
 * named "a". Example 4:
 *
 * <p>Input: input = "file1.txt\nfile2.txt\nlongfile.txt" Output: 12
 * Explanation: There are 3 files
 * at the root directory. Since the absolute path for anything at the root directory is just the
 * name itself, the answer is "longfile.txt" with length 12.
 *
 * <p>Constraints:
 *
 * <p>1 <= input.length <= 10 pow 4 input may contain lowercase or uppercase English letters, a new line
 * character '\n', a tab character '\t', a dot '.', a space ' ', and digits.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class LongestAbsoluteFilePath {

    //this also needs some fixes.. problem with escape characters
    public static int lengthLongestPath(String input) {
        String[] paths = input.split("\\\\n");
        int maxLen = 0;
        int currLen = 0;
        Stack<Integer> stack = new Stack<>();

        for(String s : paths) {
            //"\t" is one char not 2.
            String temp = s.replaceAll("\t", "");
            int level = s.lastIndexOf("\t");

            while(stack.size() > level) {
                currLen -= stack.pop();
            }

            // 1 for the last \ in dir path.
            int len = temp.length() + 1;
            currLen += len;
            stack.push(len);

            if(temp.contains(".") && currLen - 1 > maxLen)
                maxLen = currLen - 1;//removing 1 for the last \ added above as this is not dir
        }
        return maxLen;
    }

    //this also needs some fixes.. problem with escape characters
    public static int lengthLongestPathEff(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for(String s:input.split("\n")){
            int lev = s.lastIndexOf("\t")+1; // number of "\t"
            while(lev+1<stack.size()) stack.pop(); // find parent
            int len = stack.peek()+s.length()-lev+1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if(s.contains(".")) maxLen = Math.max(maxLen, len-1);
        }
        return maxLen;
    }


    public static void main(String[] args) {
        System.out.println(lengthLongestPath("dir\\n\\tsubdir1\\n\\tsubdir2\\n\\t\\tfile.ext"));
    }
}

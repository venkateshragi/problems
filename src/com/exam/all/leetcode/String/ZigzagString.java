package com.exam.all.leetcode.String;

/**
 * << descriptive comments>>
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class ZigzagString {
    public static String convert(String A, int B) {
        if(B < 2)
            return A;
        StringBuilder[] str = new StringBuilder[B];
        for(int i = 0; i < B; i++) {
            str[i] = new StringBuilder();
        }
        boolean topDown = true;
        int j = -1;
        for(int i = 0; i < A.length(); i++){
            if(topDown) {
                str[++j].append(A.charAt(i));
            } else {
                str[--j].append(A.charAt(i));
            }
            if(j == B-1)
                topDown = false;
            else if(j == 0)
                topDown = true;
        }
        for(int i = 1; i < B; i++) {
            str[0].append(str[i]);
        }
        return str.toString();
    }

    public static void main(String ars[]) {
        convert("PAYPALISHIRING", 3);
    }
}

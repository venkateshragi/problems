package com.exam.all;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Solution {

//    void printPostOrder(int in[], int pre[], int n) {
//        // The first element in pre[] is always root, search it
//        // in in[] to find left and right subtrees
//        int root = search(in, pre[0], n);
//
//        // If left subtree is not empty, print left subtree
//        if (root != 0)
////            printPostOrder(in, pre + 1, root);
//
//        // If right subtree is not empty, print right subtree
//        if (root != n - 1)
////            printPostOrder(in + root + 1, pre + root + 1, n - root - 1);
//
//        // Print root
//        System.out.println(pre[0]);
//    }
//
//
//    int search(int arr[], int x, int n) {
//        for (int i = 0; i < n; i++)
//            if (arr[i] == x)
//                return i;
//
//    }

    public static void main(String[] args) {
        /*SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yy hh:mm:ss aa zzz", Locale.ENGLISH);
        sf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date curDate = new Date();
        String curformatDate = sf.format(curDate);
        System.out.println(curformatDate);
        try {
//            System.out.println(sf.parse("06/26/19 08:00 AM GMT").getTime());
            Date curFormatdate = sf.parse(curformatDate);
            System.out.println(curDate.getTime());
            System.out.println(curFormatdate.getTime());
            System.out.println((sf.parse(curformatDate).getTime() - sf.parse("06/26/19 08:00:00 AM GMT").getTime())/1000);
            System.out.println((curDate.getTime() - sf.parse("06/26/21 08:00:00 AM GMT").getTime())/1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        String s = "  a  b  c  ";
        String[] a = s.split("\\ +");
        for(String d : a) {
            System.out.println(d);
        }


    }

}

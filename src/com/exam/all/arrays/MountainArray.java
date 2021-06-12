package com.exam.all.arrays;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * << descriptive comments>>
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MountainArray {

    public static int peakIndexInMountainArray(int[] arr, int target) {
        int l = 0;
        int h = arr.length;

        while(l < h) {
            int mid = (l + h )/2;
            if(arr[mid] < arr[mid+1]) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }
        int pivot = arr[l];

        if(pivot == target)
            return l;
        if(target > pivot)
            return -1;
        int res = helper(target, arr, 0, l-1, true);
        if(res == -1)
            res = helper(target, arr, l+1, h-1, false);
        return res;

    }

    private static int helper(int target, int[] ar, int l, int h, boolean less) {
        if(l >= h) {
            if(ar[l] == target)
                return l;
            else
                return -1;
        }
        int mid = (l+h)/2;
        int ele = ar[mid];
        if(ele == target)
            return mid;
        if(less) {
            if(target < ele)
                return helper(target, ar, l, mid-1, less);
            else
                return helper(target, ar, mid+1, h, less);
        } else {
            if(target < ele)
                return helper(target, ar, mid+1, h, less);
            else
                return helper(target, ar, l, mid-1, less);
        }

    }

    public static void main (String[] args) {
        int arr[] = {0,5,3,1};
        System.out.println(peakIndexInMountainArray(arr, 1));
    }


}

package com.exam.all.arrays;

/**
 * Program to rotate a random array left or right by 'd' number  of digits
 *
 * https://www.geeksforgeeks.org/program-for-array-rotation-continued-reversal-algorithm/
 * @author vragi
 */
public class RotateArray {

    // Print array after d rotations
    public static void leftRotateLinear(int arr[], int d) {
        for (int i = d; i < d + arr.length; i++)
            System.out.print(arr[i % arr.length] + " ");
    }

    public static void rightRotateLinear(int arr[], int d) {
        int  n = arr.length;
        for (int i = n - d; i < n - d + n; i++)
            System.out.print(arr[i % arr.length] + " ");
    }

    public static void leftRotate(int arr[], int d, int n) {
        if(d == 0 || d == n)
            return;
        // in case d is > n
        d = d % n;
        //reverse 0..d-1
        reverseArray(arr, 0, d - 1);
        //reverse d..n-1
        reverseArray(arr, d, n - 1);
        //reverse whole array
        reverseArray(arr, 0, n - 1);
    }

    public static void rightRotate(int arr[], int d, int n) {
        if(d == 0 || d == n)
            return;
        // in case d is > n
        d = d % n;
        //reverse 0..n-d-1
        reverseArray(arr, 0, n - d - 1);
        //reverse n-d..n-1
        reverseArray(arr, n  - d, n - 1);
        //reverse whole array
        reverseArray(arr, 0, n - 1);
    }

    public static void reverseArray(int[] arr, int start, int end) {
        int temp;
        while(start < end) {
            temp = arr[end];
            arr[end]  = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }
    }

    //returns the index which has the largest element and next element is smallest
    // 3, 4, 5, 1, 2 returns 2(index of 5)
    public static int findPivot(int[] arr, int low, int high) {
        if(low > high)
            return  high;
        //only one element in sub-array under consideration
        if(high == low)
            return low;
        int mid = (low + high) / 2;
        //if mid is the largest
        if(mid < high && arr[mid] > arr[mid + 1])
            return mid;
        //if mid itself is lowest
        if(mid > low && arr[mid] < arr[mid - 1])
            return mid - 1;
        if(arr[low] <= arr[mid])
            return findPivot(arr, mid + 1, high);
        return findPivot(arr, low, mid  - 1);
    }

    public static void main (String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        leftRotate(arr, 3, arr.length);
        Util.printArray(arr);

        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rightRotate(arr, 9, arr.length);
        Util.printArray(arr);

        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        rightRotateLinear(arr, 9);
    }
}

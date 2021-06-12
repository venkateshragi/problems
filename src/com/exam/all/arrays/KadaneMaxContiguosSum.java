package com.exam.all.arrays;

/**
 * Find max contiguous sum in an array
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class KadaneMaxContiguosSum {

    public static int maxSum(int arr[]) {
        if(arr == null || arr.length == 0)
            return 0;

        //if both are zeros when all numbers in array are -ve, result would be zero which is not desirable
        //as (-2, -1, -3) should return max sum as -1
        //so start with first element
        int maxSum = arr[0];
        int maxEndingHere = arr[0];

        for(int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere+arr[i]);
            maxSum = Math.max(maxEndingHere, maxSum);

            /* default kadane approach does not work when all are -ve
            if(maxEndingHere < 0)
                maxEndingHere = 0;
            else if(maxEndingHere > maxSum)
                maxSum = maxEndingHere;
                */
        }
        return  maxSum;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-22, -3, -4, -10, -2, -11, -5, -3};
        System.out.println(maxSum(arr));
        printIndexes(arr);

        arr = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(maxSum(arr));
        printIndexes(arr);

        arr = new int[] {-9,13,4,-16,-12,-16,3,-7,5,-16,16,8,-1,-13,15,3};
        System.out.println(maxSum(arr));
        printIndexes(arr);
    }

    //prints start and end index of max contiguous array
    public static void printIndexes(int[] arr) {
        int maxEndingHere = arr[0];
        int maxSum =  arr[0];
        int start = 0, end = 0, s = 0;

        for(int i = 1; i < arr.length; i++) {
            int sum = maxEndingHere + arr[i];
            if(arr[i] > sum) {
                maxEndingHere  = arr[i];
                s = i;
            } else {
                maxEndingHere = sum;
            }
            if(maxSum < maxEndingHere) {
                maxSum = maxEndingHere;
                start = s;
                end = i;
            }
        }
        System.out.println("Starting index " + start);
        System.out.println("Ending index " + end);
    }
}

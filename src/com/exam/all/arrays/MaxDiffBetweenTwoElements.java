package com.exam.all.arrays;

/**
 *
 * Maximum difference between two elements such that larger element appears after the smaller number
 *
 * // valley-peak approach
 *                            80
 *                           /
 *             30            /
 *           /  \           25
 *          /    15       /
 *         /      \      /
 *        2        10   /
 *                   \ /
 *                    8
 * @author vragi
 */
public class MaxDiffBetweenTwoElements {

    //MinSoFar and maxSofar
    public static int findMaxDiff(int arr[]) {
        int maxDiff = Integer.MIN_VALUE;
        int minSoFar = arr[0];
        for(int i = 1; i < arr.length; i++) {
            int diff = arr[i] - minSoFar;
            if(diff > maxDiff)
                maxDiff = diff; // if needed store index as max element index
            if(arr[i] < minSoFar)
                minSoFar = arr[i]; // if needed store index as min element index
        }
        return  maxDiff;
    }

    /**
     * in above ex, 80 - 8 = (25 - 8) + (80 -  25)
     * or in arr [a, b, c, d, e] all +ve in increasing order if a is min and e is max
     * max diff (e - a) = max contiguous sum in diff array [(b - a) + (c  - b) + (d - c) + (e -d)]
     * similarly we can use this maxSum in for valley-peak ie., when elements are in any order
     * @param arr
     * @return
     */
    public static int findMaxDiffUsingMaxSubArray(int[] arr) {
        int diff = arr[1] - arr[0];
        int sumTillHere = diff;
        int maxSumSoFar = sumTillHere;

        for(int i = 1; i < arr.length - 1; i++) {
            diff = arr[i+1] - arr[i];

            if(sumTillHere < 0) //consider steep down in valley (30 .. 8 in above ex, at 8 the diffs sum becomes -ve  and we  want to start fresh)
                sumTillHere = 0;
            else
                sumTillHere += diff;

            if(maxSumSoFar < sumTillHere)
                maxSumSoFar =  sumTillHere;
        }
        return maxSumSoFar;
    }



    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 10, 6, 4, 8, 1};
        System.out.println(findMaxDiff(arr));

        arr = new int[]{7, 9, 5, 6, 3, 2};
        System.out.println(findMaxDiff(arr));

        arr = new int[]{1, 2, 90, 10, 110};
        System.out.println(findMaxDiff(arr));

        arr = new int[]{80, 2, 6, 3, 100};
        System.out.println(findMaxDiff(arr));

        arr = new int[]{2, 3, 10, 6, 4, 8, 1};
        System.out.println(findMaxDiffUsingMaxSubArray(arr));

        arr = new int[]{7, 9, 5, 6, 3, 2};
        System.out.println(findMaxDiffUsingMaxSubArray(arr));

        arr = new int[]{1, 2, 90, 10, 110};
        System.out.println(findMaxDiffUsingMaxSubArray(arr));

        arr = new int[]{80, 2, 6, 3, 100};
        System.out.println(findMaxDiffUsingMaxSubArray(arr));
    }
}


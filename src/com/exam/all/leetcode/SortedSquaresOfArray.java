package com.exam.all.leetcode;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of
 * each number, also in sorted non-decreasing order.
 *
 * <p>Example 1:
 *
 * <p>Input: [-4,-1,0,3,10] Output: [0,1,9,16,100]
 *
 * <p>Example 2:
 *
 * <p>Input: [-7,-3,2,3,11] Output: [4,9,9,49,121]
 *
 * <p>Note:
 * 1 <= A.length <= 10000.
 * -10000 <= A[i] <= 10000.
 * A is sorted in non-decreasing order.
 *
 * @author vragi
 */
public class SortedSquaresOfArray {

    public int[] sortedSquares(int[] A) {
        //We will find position of first positive number in the array.
        int i = 0;
        while(i < A.length && A[i] <= 0) {
            i++;
        }
        int j = i - 1; //last index of negative numbers

        int[] result = new int[A.length];
        int k = 0;

        while(j >= 0 && i < A.length) {
            int positiveSquare = A[i] * A[i];
            int negativeSquare = A[j] * A[j];
            if(negativeSquare < positiveSquare) {
                result[k++] = negativeSquare;
                j--;
            } else {
                result[k++] = positiveSquare;
                i++;
            }
        }
        while(j >= 0) {
            result[k++] = A[j] * A[j];
            j--;
        }
        while(i < A.length) {
            result[k++] = A[i] * A[i];
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = new SortedSquaresOfArray().sortedSquares(new int[]{-4,-1,0,3,10});
        for(int i : result) {
            System.out.print(i + " ");
        }
    }

}

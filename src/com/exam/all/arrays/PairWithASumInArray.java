package com.exam.all.arrays;

/**
 * << descriptive comments>>
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class PairWithASumInArray {

    //returns total count  of pairs that match the sum
    public static int findInSortedRotatedArray(int[] arr, int k) {
        int pivotIndex = RotateArray.findPivot(arr, 0, arr.length - 1);
        System.out.println("pivotIndex:" + pivotIndex);

        int n = arr.length;
        int left = (pivotIndex + 1) % n;
        int right = pivotIndex;

        int count = 0;
        while (left != right) {
            int sum = arr[left] + arr[right];
            if(sum == k) {
                count++;

                //when they are edges if this is not checked they cross each other n loop runs infinitely
                if(left == (right - 1 + n) % n) {
                    return count;
                }
                left = (left + 1) % n;
                right = (right - 1 + n)  % n;
            } else if(sum < k) {
                left = (left + 1) % n;
            } else {
                right = (right - 1 + n) % n;
            }
        }
        return count;

    }

    public static void main (String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        RotateArray.rightRotate(arr, 5, arr.length);
        System.out.println(findInSortedRotatedArray(arr, 3));
        Util.printArray(arr);
    }
}

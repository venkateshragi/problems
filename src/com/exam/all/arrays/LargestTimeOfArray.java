package com.exam.all.arrays;

/**
 * https://leetcode.com/problems/largest-time-for-given-digits/
 *
 * Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit
 * exactly once.
 *
 * <p>24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00
 * and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.
 *
 * <p>Return the latest 24-hour time in "HH:MM" format. If no valid time can be made, return an
 * empty string.
 *
 * <p>Example 1:
 *
 * <p>Input: arr = [1,2,3,4] Output: "23:41" Explanation: The valid 24-hour times are "12:34",
 * "12:43", "13:24", "13:42", "14:23", "14:32", "21:34", "21:43", "23:14", and "23:41". Of these
 * times, "23:41" is the latest. Example 2:
 *
 * <p>Input: arr = [5,5,5,5] Output: "" Explanation: There are no valid 24-hour times as "55:55" is
 * not valid. Example 3:
 *
 * <p>Input: arr = [0,0,0,0] Output: "00:00" Example 4:
 *
 * <p>Input: arr = [0,0,1,0] Output: "10:00"
 *
 * <p>Constraints:
 *
 * <p>arr.length == 4 0 <= arr[i] <= 9
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class LargestTimeOfArray {
    int maxTime = -1;

    public String largestTimeFromDigits(int[] arr) {
        permutate(arr, 0);
        if(maxTime == -1)
            return "";

        return String.format("%02d:%02d", maxTime / 60, maxTime % 60);
    }

    //this fixes a digit in each position in each iteration (DFS). Thus will have permuations 4! only and no repetitions.
    private void permutate(int[] arr, int start) {
        if(start == arr.length) {
            buildTime(arr);
            return;
        }

        for(int i = start; i < arr.length; i++)  {
            swap(arr, start, i);
            permutate(arr, start + 1);
            swap(arr, i, start);
        }
    }

    private void buildTime(int[] a) {
        int hr = (a[0] *10 ) + a[1];
        int mi = (a[2] * 10) + a[3];
        if(hr < 24 && mi < 60) {
            maxTime = Math.max(maxTime, (hr * 60) + mi);
        }
    }

    private void swap(int[] arr, int i, int j) {
        if(i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}

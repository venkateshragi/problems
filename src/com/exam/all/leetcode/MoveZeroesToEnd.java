package com.exam.all.leetcode;

/**
 * https://leetcode.com/problems/move-zeroes/
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the
 * relative order of the non-zero elements.
 *
 * <p>Example:
 *
 * <p>Input: [0,1,0,3,12] Output: [1,3,12,0,0]
 * <p>Input: [1] Output: [1]
 * <p>Input: [0] Output:[0]
 * <p>Input: [2, 1]  Output: [2, 1]
 * <p>Input: [1, 0] Output: [0, 1]
 *
 * <p>Note: You must do this in-place without making a copy of the array. Minimize the total number of
 * operations.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MoveZeroesToEnd {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        int temp;
        for(int lastNonZeroFoundAt = 0, curr = 0; curr < nums.length; curr++) {
            if(nums[curr] != 0) {
                temp = nums[lastNonZeroFoundAt];
                nums[lastNonZeroFoundAt] = nums[curr];
                nums[curr] = temp;
                lastNonZeroFoundAt++;
            }
        }
    }
}

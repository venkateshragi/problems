package com.exam.all.leetcode.sequence;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * <p>Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * <p>A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of
 * the array [0,3,1,6,2,2,7].
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence
 * is [2,3,7,101], therefore the length is 4.
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * <p>Example 3:
 *
 * <p>Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * <p>Constraints:
 *
 * <p>1 <= nums.length <= 2500
 * -10 pow 4 <= nums[i] <= 10 pow 4
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class LongestIncreasingSubsequence {
    /**
     * This uses Patience Sort (kind of solving solitaire game)
     * https://www.youtube.com/watch?v=22s1xxRvy28&t=2s  - stable sort
     * https://www.youtube.com/watch?v=S9oUiVYEq7E - Tushar Roy
     */
    private static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] groups = new int[len];
        int[] positions = new int[len];
        for(int i = 0; i < len; i++) {
            positions[i] = -1;
        }

        groups[0] = 0;
        int j = 0;
        for(int i = 1; i < len; i++) {
            if(nums[i] > nums[groups[j]]) {
                groups[j+1] = i;
                positions[i] = groups[j];
                j++;
            } else {
                int index = findPosition(groups, nums, j, nums[i]);
                groups[index] = i;
                positions[groups[index]] = index == 0 ? -1 : groups[index - 1];
            }
        }
        printSequence(groups, j, nums, positions);
        return j+1;
    }

    private static int findPosition(int[] groups, int[] nums, int len, int value) {
        int start = 0;
        int end  = len;

        while(start <= end) {
            int mid = (start + end) / 2;
            if(mid < len && (nums[groups[mid]] < value && value <= nums[groups[mid + 1]])) {
                return mid + 1;
            }
            if(value <= nums[groups[mid]])
                end = mid - 1;
            else
                start = mid + 1;
        }
        return 0;
    }

    private static void printSequence(int[] groups, int end, int[] nums, int[] positions) {
        int pos = groups[end];
        System.out.println();
        while(pos != -1) {
            System.out.print(nums[pos] + " ");
            pos = positions[pos];
        }
        System.out.println();
    }


  public static void main(String[] args) {
    int[] nums = new int[] {10,9,2,5,3,7,101,18};
    System.out.println(lengthOfLIS(nums));

      nums = new int[] {2,5,7,101,108};
      System.out.println(lengthOfLIS(nums));

      nums = new int[] {5,1,0};
      System.out.println(lengthOfLIS(nums));

      nums = new int[] {10,5,8,2,3,9,4,12,11};
      System.out.println(lengthOfLIS(nums));

    }
}

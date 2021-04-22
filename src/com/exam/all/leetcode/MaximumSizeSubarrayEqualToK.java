package com.exam.all.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
 * If there isn't one, return 0 instead.
 *
 * <p>Note: The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer
 * range.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [1, -1, 5, -2, 3], k = 3 Output: 4 Explanation: The subarray [1, -1, 5, -2] sums
 * to 3 and is the longest. Example 2:
 *
 * <p>Input: nums = [-2, -1, 2, 1], k = 1 Output: 2 Explanation: The subarray [-1, 2] sums to 1 and
 * is the longest. Follow Up: Can you do it in O(n) time?
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MaximumSizeSubarrayEqualToK {

    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> sumMap = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        for(int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];

            if(sum == k) {
                maxLength = i + 1;
            }

            // make an entry for 'sum' if it is
            // not present in 'map' '
            if(!sumMap.containsKey(sum)) {
                sumMap.put(sum, i);
            }

            // check if 'sum-k' is present in 'map'
            // or not
            int diffValue = sum - k;
            if(sumMap.containsKey(diffValue)) {
                if(maxLength < i - sumMap.get(diffValue)) {
                    maxLength = i - sumMap.get(diffValue);
                }
            }
        }
        return maxLength;
    }
}

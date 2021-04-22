package com.exam.all.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous
 * subarrays whose sum equals to k.
 *
 * <p>Example 1:
 * Input:nums = [1,1,1], k = 2 Output: 2
 *
 * <p>Inout: nums=[0,0,0,0,0,0,0,0,0,0], k=0, Output: 55
 *
 * <p>Inout: nums=[2], k=2, Output: 1
 *
 * Note: The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class NumberOfSubarraySumEqualsK {

    public int subarraySum1(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return 0;
        int sum = 0;
        int count = 0;
        Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];

            if(sum == k)
                count++;

            int diff = sum - k;
            if(sumMap.containsKey(diff)) {
                int numberOfTimes = sumMap.get(diff);
                count += numberOfTimes;
            }
            if(sumMap.containsKey(sum)) {
                sumMap.put(sum, sumMap.get(sum) + 1);
            } else {
                sumMap.put(sum, 1);
            }

        }
        return count;
    }
}

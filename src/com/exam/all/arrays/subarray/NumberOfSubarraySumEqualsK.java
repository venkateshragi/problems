package com.exam.all.arrays.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * https://leetcode.com/problems/subarray-sum-equals-k/
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

    public static int subarraySum1(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        //need this when sum == k => diff = 0. if this is not added we have to incr count by 1 explicitly as done
        //in below subarraySum method
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static int subarraySum(int[] nums, int k) {
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
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main (String[] args) {
        int arr[] = {0,0,0,0,0,0,0,0,0,0};
        System.out.println(subarraySum(arr, 0));
    }
}

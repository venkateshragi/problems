package com.exam.all.arrays.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/continuous-subarray-sum/
 *
 * <p>Given an integer array nums and an integer k, return true if nums has a continuous subarray of
 * size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * <p>An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always
 * a multiple of k.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [23,2,4,6,7], k = 6 Output: true Explanation: [2, 4] is a continuous subarray of
 * size 2 whose elements sum up to 6.
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [23,2,6,4,7], k = 6 Output: true Explanation: [23, 2, 6, 4, 7] is an continuous
 * subarray of size 5 whose elements sum up to 42. 42 is a multiple of 6 because 42 = 7 * 6 and 7 is
 * an integer.
 *
 * <p>Example 3:
 *
 * <p>Input: nums = [23,2,6,4,7], k = 13 Output: false
 *
 * <p>Example 4:
 *
 * <p>Input: nums = [5,0,0], k = 3 Output: true Explanation: 0 is a multiple of 3 and [0,0]
 * satisfies min length condition of 2
 *
 * <p>Example 4:
 *
 * <p>Input: nums = [5,0], k = 3 * Output: false Explanation: though 0 is multiple of 3, [0] does not satisfy
 * minimum length condition of 2.
 *
 *
 *
 * <p>solution explanation at
 * https://leetcode.com/problems/continuous-subarray-sum/discuss/99499/Java-O(n)-time-O(k)-space/103585
 *
 * <p>quoted here
 *
 * <p>(a+(n*x))%x is same as (a%x)
 *
 * <p>For e.g. in case of the array [23,2,6,4,7] the running sum is [23,25,31,35,42] and the
 * remainders are [5,1,1,5,0]. We got remainder 5 at index 0 and at index 3. That means, in between
 * these two indexes we must have added a number which is multiple of the k.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class ContinuousSubArrayMultipleOfk {

    public static boolean checkSubarraySum(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            Integer prev = map.get(sum % k);
            if(prev != null) {
                if(i - prev > 1)
                    return true;
            } else {
                map.put(sum % k, i);
            }

        }
        return false;
    }

    public static void main (String[] args) {
        int arr[] = {23,2,4,6,7};
        System.out.println(checkSubarraySum(arr, 6));

        arr = new int[]{5,0};
        System.out.println(checkSubarraySum(arr, 3));

        arr = new int[]{5,0,0};
        System.out.println(checkSubarraySum(arr, 3));

        //return false as [3] does not satisfy min length of 2
        arr = new int[]{5,3};
        System.out.println(checkSubarraySum(arr, 3));

        //returns true as [0,3] satisfies min length of 2
        arr = new int[]{5,0,3};
        System.out.println(checkSubarraySum(arr, 3));
    }
}

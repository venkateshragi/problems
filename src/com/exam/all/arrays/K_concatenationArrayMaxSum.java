package com.exam.all.arrays;

/**
 * https://leetcode.com/problems/k-concatenation-maximum-sum/
 *
 * <p>Given an integer array arr and an integer k, modify the array by repeating it k times.
 *
 * <p>For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 *
 * <p>Return the maximum sub-array sum in the modified array. Note that the length of the sub-array
 * can be 0 and its sum in that case is 0.
 *
 * <p>As the answer can be very large, return the answer modulo 109 + 7.
 *
 * <p>Example 1:
 *
 * <p>Input: arr = [1,2], k = 3 Output: 9
 *
 * <p>Example 2:
 *
 * <p>Input: arr = [1,-2,1], k = 5 Output: 2
 *
 * <p>Example 3:
 *
 * <p>Input: arr = [-1,-2], k = 7 Output: 0
 *
 * <p>Constraints:
 *
 * <p>1 <= arr.length <= 10 pow 5 1 <= k <= 10 pow 5 -10 pow 4 <= arr[i] <= 10 pow 4
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class K_concatenationArrayMaxSum {
  public long kadane(int arr[]) {
    long curr = arr[0];
    long max = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (curr >= 0) curr += arr[i];
      else curr = arr[i];
      if (curr > max) max = curr;
    }
    return max;
  }

  public long kadaneTwo(int arr[]) {
    int narr[] = new int[arr.length * 2];
    for (int i = 0; i < arr.length; i++) narr[i] = arr[i];
    for (int i = 0; i < arr.length; i++) narr[i + arr.length] = arr[i];
    return kadane(narr);
  }

  public long sum(int arr[]) {
    long s = 0;
    for (int i = 0; i < arr.length; i++) s += arr[i];
    return s;
  }

  public int kConcatenationMaxSum(int[] arr, int k) {
//    long sum = sum(arr);
//    long res = 0;
//    long x = 1000000007;
//    if (k == 1)
//        res = kadane(arr);
//    else if (sum < 0)
//        res = kadaneTwo(arr);
//    else
//        res = kadaneTwo(arr) + (k - 2) * sum;
//    if (res < 0)
//        return 0;
//    return (int) (res % x);

      int mod = (int) 1e9+7;

      long max = kadane(arr);
      if(k==1) return (int) (max % mod);

      int n = arr.length;
      long prefix = 0, suffix = 0, sum = 0;
      for(int num: arr) sum += num;

      long ps = 0;
      for(int i=0; i<n; i++){
          ps += arr[i];
          prefix = Math.max(ps, prefix);
          suffix = Math.max(sum-ps+arr[i], suffix);
      }

      if(sum >= 0){
          max = Math.max(max, suffix+prefix+(k-2)*sum);
      }else{
          max = Math.max(max, suffix+prefix);
      }

      return (int) (max % mod);
  }

  public static void main(String[] args) {
    K_concatenationArrayMaxSum obj = new K_concatenationArrayMaxSum();
    int[] arr = new int[] {1, -2, 1};
    System.out.println(obj.kConcatenationMaxSum(arr, 5));

    arr = new int[] {-1, -2};
    System.out.println(obj.kConcatenationMaxSum(arr, 7));

    arr = new int[] {1, 2};
    System.out.println(obj.kConcatenationMaxSum(arr, 3));

    arr = new int[] {-9, 13, 4, -16, -12, -16, 3, -7, 5, -16, 16, 8, -1, -13, 15, 3};
    System.out.println(obj.kConcatenationMaxSum(arr, 6));
  }
}

package com.exam.all.leetcode.dp;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
 *
 * <p>We have two integer sequences nums1 and nums2 of the same non-zero length.
 *
 * <p>We are allowed to swap elements nums1[i] and nums2[i]. Note that both elements are in the same
 * index position in their respective sequences.
 *
 * <p>At the end of some number of swaps, nums1 and nums2 are both strictly increasing. (An array A
 * is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 *
 * <p>Given nums1 and nums2, return the minimum number of swaps to make both sequences strictly
 * increasing. It is guaranteed that the given input always makes it possible.
 *
 * <p>Example: Input: nums1 = [1,3,5,4], nums2 = [1,2,3,7] Output: 1 Explanation: Swap nums1[3] and
 * nums2[3]. Then the sequences are: nums1 = [1, 3, 5, 7] and nums2 = [1, 2, 3, 4] which are both
 * strictly increasing. Note:
 *
 * <p>nums1, nums2 are arrays with the same length, and that length will be in the range [1, 1000].
 * nums1[i], nums2[i] are integer values in the range [0, 2000].
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class MinSwapsToMakeIncreasingSeq {
    public int minSwap(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[] swap = new int[len];
        int[] noSwap = new int[len];
        swap[0] = 1;
        for(int i = 1; i < len; i++) {
            swap[i] = noSwap[i] = len;
            int x = nums1[i];
            int y = nums2[i];
            int prevx = nums1[i-1];
            int prevy = nums2[i-1];

            if(prevx < x && prevy < y) {
                noSwap[i] = noSwap[i-1];
                swap[i] = swap[i-1] + 1;
            }
            if(prevx < y && prevy < x) {
                swap[i] = Math.min(noSwap[i-1]+1, swap[i]);
                noSwap[i] = Math.min(swap[i-1], noSwap[i]);
            }
        }

        return Math.min(swap[len-1], noSwap[len-1]);

    }

    public static void main(String[] args) {
        MinSwapsToMakeIncreasingSeq minSwapsToMakeIncreasingSeq = new MinSwapsToMakeIncreasingSeq();
        //ans 1.
        System.out.println(minSwapsToMakeIncreasingSeq.minSwap(new int[]{0,4,4,5,9}, new int[]{0,1,6,8,10}));
    }
}

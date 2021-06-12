package com.exam.all.leetcode;

/**
 *
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 * @author vragi
 * @since <<buildnumber>>
 */
public class MaxPointsFromCards {

    public static int maxScore(int[] cardPoints, int k) {
        if(cardPoints == null || cardPoints.length == 0)
            return 0;
        return maxScore(cardPoints, k, 0, cardPoints.length - 1);

    }

    public static int maxScore(int[] cardPoints, int k, int left, int right) {
        if(k == 0 || left > right)
            return 0;

        return Math.max(cardPoints[left] + maxScore(cardPoints, k - 1, left + 1, right),
                cardPoints[right] + maxScore(cardPoints, k - 1, left, right - 1));
    }

    public static int maxScoreWithPreProcess(int[] cardPoints, int k) {
        if(cardPoints == null || cardPoints.length == 0 || k == 0)
            return 0;

        int[] sumFromFront = new int[k +1];
        sumFromFront[0] = 0; // implies 0 elements taken from front.
        int sum = 0;
        for(int i = 0; i < k; i++) {
            sum += cardPoints[i];
            sumFromFront[i+1] = sum;
        }

        int length = cardPoints.length;
        if(k == length)
            return sum;

        int[] sumFromBack = new int[k +1];
        sum = 0;
        sumFromBack[0] = 0; // implies 0 elements taken from front.
        for(int i = 0; i < k; i++) {
            sum += cardPoints[length - 1 - i];
            sumFromBack[i+1] = sum;
        }

        int result = Integer.MIN_VALUE;
        for(int i = 0; i <= k; i++) {
            //implies sum of i number of ele from front and k - i number of elements from back
            sum = sumFromFront[i] + sumFromBack[k - i];
            result = Math.max(result, sum);
        }
        return result;
    }

    public static int maxScoreSlidingWindow(int[] cardPoints, int k) {
        if(cardPoints == null || cardPoints.length == 0 || k == 0)
            return 0;

        //sum of last k elements
        int sumOfKEle = 0;
        for(int i = cardPoints.length - 1; i > cardPoints.length - 1 - k; i--) {
            sumOfKEle += cardPoints[i];
        }

        if(k == cardPoints.length)
            return sumOfKEle;

        int result = sumOfKEle;
        int length  = cardPoints.length;
        for(int i = 0; i < k; i++) {
            //remove kth element from last and add first element
            sumOfKEle = sumOfKEle - cardPoints[length - k + i] + cardPoints[i];
            result = Math.max(result, sumOfKEle);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] cards = new int[]{1,2,3,4,5,6,1};
        System.out.println(maxScore(cards, 3));

        cards = new int[]{9,7,7,9,7,7,9};
        System.out.println(maxScore(cards, 7));

        cards = new int[]{1,79,80,1,1,1,200,1};
        System.out.println(maxScore(cards, 3));

        cards = new int[]{1,2,3,4,5,6,1};
        System.out.println(maxScoreSlidingWindow(cards, 3));

        cards = new int[]{9,7,7,9,7,7,9};
        System.out.println(maxScoreSlidingWindow(cards, 7));

        cards = new int[]{1,79,80,1,1,1,200,1};
        System.out.println(maxScoreSlidingWindow(cards, 3));
    }
}

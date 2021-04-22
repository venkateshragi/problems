package com.exam.all.zalando;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * << descriptive comments>>
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class Solution {

    public static void main(String[] args) {
//        new Solution().solution(new int[] {3, 5, 6, 3, 3, 5});
        new Solution().solution(3,2,new int[]{2,1,1,0,1});
//        new Solution().solution(2,3,new int[]{0,0,1,1,2});
//        new Solution().solution(new int[]{2,-2,-3,3}, new int[]{0,0,4,-4});
//        new Solution().solution(new int[]{4,-1,0,3}, new int[]{-2,5,0,3});
    }

    public String solution(int U, int L, int[] C) {
        int[][] matrix = new int[2][C.length];
        int tempU = U;
        int tempL = L;
        List<Integer> fixedColumns = new ArrayList<>();
        for(int i = 0; i < C.length; i++) {
            if(C[i] == 0) {
                matrix[0][i] = 0;
                matrix[1][i] = 0;
                fixedColumns.add(i);
            } else if(C[i] == 2) {
                matrix[0][i] = 1;
                matrix[1][i] = 1;
                tempU--;
                tempL--;
                fixedColumns.add(i);
            }
        }
        if(fixedColumns.size() == C.length) {
            return getMatrixInNeededResponse(matrix);
        }
        if(findSolution(matrix, fixedColumns, 0, tempU, tempL))
            return getMatrixInNeededResponse(matrix);
        return "IMPOSSIBLE";
    }

    private boolean findSolution(int[][] matrix, List<Integer> fixedColumns, int currentColumn, int tempU, int tempL) {
        for(int i = currentColumn; i < matrix[0].length; i++) {
            if(!fixedColumns.contains(i)) {
                boolean result = false;
                if(tempU > 0) {
                    matrix[0][i] = 1;
                    matrix[1][i] = 0;
                    result = findSolution(matrix, fixedColumns, i + 1, tempU-1, tempL);
                }
                if(!result) {
                    if (tempL > 0) {
                        matrix[0][i] = 0;
                        matrix[1][i] = 1;
                        result = findSolution(matrix, fixedColumns, i + 1, tempU, tempL - 1);
                    }
                }
                if(result)
                    return result;
            }
        }
        if(tempU == 0 && tempL == 0)
            return true;
        return false;
    }

    private String getMatrixInNeededResponse(int[][] matrix) {
        StringBuilder s = new StringBuilder("");
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                s.append(matrix[i][j]);
            }
            if(i == 0)
                s.append(",");
        }
        return s.toString();
    }

    public int solution10(int[] A, int[] B) {
        int[] prefixSumA = calculatePrefixSum(A);
        int[] suffixSumA = calculateSuffixSum(A);
        int[] prefixSumB = calculatePrefixSum(B);
        int[] suffixSumB = calculateSuffixSum(B);

        int res = 0;
        for(int i = 0; i < A.length; i++) {
            if(i < A.length - 1 && prefixSumA[i] == suffixSumA[i+1]) {
                if(prefixSumB[i] == suffixSumB[i+1] && prefixSumA[i] == prefixSumB[i])
                    res++;
            }
        }
        return res;
    }


    private int[] calculatePrefixSum(int[] arr) {
        int[] res = new int[arr.length];
        res[0] = arr[0];
        for(int i = 1; i < arr.length; i++) {
            res[i] = res[i-1] + arr[i];
        }
        return res;
    }

    private int[] calculateSuffixSum(int[] arr) {
        int[] res = new int[arr.length];
        res[arr.length - 1] = arr[arr.length - 1];
        for(int i = arr.length - 2; i >= 0; i--) {
            res[i] = res[i + 1] + arr[i];
        }
        return res;
    }

    public int solution5(int N) {
        int sumOfDigitsInN = getSumOfDigits(N);
        int temp = N + 1;
        while(true) {
            int sumOfDigits = getSumOfDigits(temp);
            if(sumOfDigits == sumOfDigitsInN)
                return temp;
            temp++;
        }
    }

    private int getSumOfDigits(int n) {
        int temp = n;
        int sum = 0;
        while(temp > 0) {
            sum = sum + (temp % 10);
            temp = temp / 10;
        }
        return sum;
    }

    public int solution4(int[] A) {
        if(A == null || A.length == 0 || A.length == 1)
            return 0;
        Arrays.sort(A);
        int i = 1;
        int k = 0;
        int totalPairs = 0;
        while(i < A.length) {
            if(A[i-1] == A[i]) {
                i++;
            } else {
                if(k != i) {
                    totalPairs += calculatePairs(i - 1 - k);
                    if(totalPairs > 1000000000)
                        return 1000000000;
                }
                k = i;
                i++;
            }
        }
        if (k != A.length - 1) {
          totalPairs += calculatePairs(i - 1 - k);
        }
        if(totalPairs > 1000000000)
            return 1000000000;
        return totalPairs;
    }

    private int calculatePairs(int n) {
        return (n * (n+1)) / 2;
    }



    public int solution1(String S) {
        // write your code in Java SE 8
        if(S == null || S.length() == 0)
            return 0;
        int value = Integer.parseInt(S, 2);
        int steps = 0;
        while(value != 0) {
            if(value % 2 == 0) {
                value = value / 2;
            } else {
                value = value - 1;
            }
            steps++;
        }
        return steps;
    }

    public int solution2(int[] A) {
        if(A == null || A.length == 0)
            return 0;
        boolean[] hasSmallerOnRight = buildHasSmallerOnRight(A);
        int totalMomentsAllTurnedOnBulbsShine = 0;
        for(int i = 0; i < A.length; i++) {
            if(!hasSmallerOnRight[i])
                totalMomentsAllTurnedOnBulbsShine++;
        }
        return totalMomentsAllTurnedOnBulbsShine;
    }

    public int solution3(int[] A) {
        boolean[] switched = new boolean[A.length + 1];
        int maxSofar = -1;
        int totalMomentsAllTurnedOnBulbsShine = 0;
        for(int i = 0; i < A.length; i++) {
            if(A[i] > maxSofar)
                maxSofar = A[i];
            switched[i] = true;
            boolean areAllTurnedOn = true;
            for(int j = 1; j < maxSofar; j++) {
                if(!switched[j]) {
                    areAllTurnedOn = false;
                    break;
                }
            }
            if(areAllTurnedOn)
                totalMomentsAllTurnedOnBulbsShine++;
        }
        return totalMomentsAllTurnedOnBulbsShine;
    }

    private boolean[] buildHasSmallerOnRight(int[] A) {
        boolean[] hasSmallerOnRight = new boolean[A.length + 1];
        for(int i = 0; i < A.length; i++) {
            for(int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    hasSmallerOnRight[i] = true;
                    break;
                }
            }
        }
        return hasSmallerOnRight;
    }
}

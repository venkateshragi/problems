package com.exam.all.arrays;

/**
 * In this problem, you are given an integer N, and a permutation, P of the integers from 1 to N,
 * denoted as (a_1, a_2, ..., a_N). You want to rearrange the elements of the permutation into
 * increasing order, repeatedly making the following operation:
 *
 * <p> Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
 *
 * <p> Your goal is to compute the minimum number of such operations required to return the permutation to increasing order.
 *
 * <p> Signature
 * <p> int minOperations(int[] arr)
 *
 * <p>Input
 * <p> Array arr is a permutation of all integers from 1 to N, N is between 1 and 8
 *
 * <p> Output
 * <p> An integer denoting the minimum number of operations required to arrange
 * the permutation in increasing order
 *
 * <p> Example If N = 3, and P = (3, 1, 2), we can do the following operations:
 * <p> Select (1, 2) and reverse it: P = (3, 2, 1).
 * <p> Select (3, 2, 1) and reverse it: P = (1, 2, 3).
 * <p> output = 2
 *
 * @author vragi
 * @since <<buildnumber>>
 */
//a bit similar to https://leetcode.com/problems/pancake-sorting/
public class MinimizingPermutations {
    // Add any helper functions you may need here
    private void reverseSubArray(int[] arr, int begin, int end) {
        for (int i = 0; i < (end - begin + 1) / 2; i++) {
            int temp = arr[begin + i];
            arr[begin + i] = arr[end - i];
            arr[end - i] = temp;
        }
    }

    private int findTarget(int[] arr, int end, int target) {
        for (int i = 0; i <= end; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        throw new IllegalArgumentException("The input is invalid");
    }

    /**
     * To put the biggest number at the end of the array, O(N) to findTarget, O(N) to reverse. To put
     * the second biggest number at the right place, O(N - 1) to findTarget, O(N - 1) to reverse.
     *
     * Time Complexity: O(N + N - 1 + N -2 + ... + 1) = O(N^2)
     *
     * @param arr
     * @param index
     * @return
     */
    private int minOperations(int[] arr, int index) {
        if (index < 0) {
            return 0;
        }

        if (arr[index] == index + 1) {
            // this element is already in its final position
            return minOperations(arr, index - 1);
        } else {
            int begin = findTarget(arr, index, index + 1);
            reverseSubArray(arr, begin, index);
            return 1 + minOperations(arr, index - 1);
        }
    }

    int minOperations(int[] arr) {
        // Write your code here
        return minOperations(arr, arr.length - 1);
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {

        int n_1 = 5;
        int[] arr_1 = { 1, 2, 5, 4, 3 };
        int expected_1 = 1;
        int output_1 = minOperations(arr_1);
        check(expected_1, output_1);

        int n_2 = 3;
        int[] arr_2 = { 3, 1, 2 };
        int expected_2 = 2;
        int output_2 = minOperations(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

        int n_3 = 5;
        int[] arr_3 = { 1, 2, 3, 4, 5 };
        int expected_3 = 0;
        int output_3 = minOperations(arr_3);
        check(expected_3, output_3);

        int n_4 = 5;
        int[] arr_4 = { 5, 4, 3, 2, 1 };
        int expected_4 = 1;
        int output_4 = minOperations(arr_4);
        check(expected_4, output_4);

        int n_5 = 5;
        int[] arr_5 = { 5, 3, 1, 2, 4 };
        int expected_5 = 4;
        int output_5 = minOperations(arr_5);
        check(expected_5, output_5);

        int n_6 = 6;
        int[] arr_6 = { 1, 2, 5, 4, 6, 3 };
        int expected_6 = 2;
        int output_6 = minOperations(arr_6);
        check(expected_6, output_6);

    }

    public static void main(String[] args) {
        new MinimizingPermutations().run();
    }
}

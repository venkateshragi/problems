package com.exam.all.microsoft;

/**
 * The task is to find all occurences of a given number in a row wise and column wise sorted matrix
 * Input :
 * <blockquote><pre>
 *      mat[4][4] = {
 *          {10, 20, 30, 40},
 *          {15, 25, 29, 45},
 *          {27, 29, 37, 48},
 *          {32, 33, 39, 50}
 *          };
 * x = 29
 * Ans: Found at (1,2), (2,1)
 * </pre></blockquote>
 * <p>
 * The logic is as follows as explained at https://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/
 * <blockquote><pre>
 * 1) Start with top right element
 * 2) Loop: compare this element e with x
 *      i) if they are equal then return its position
 *      ii) e < x then move it to down (if out of bound of matrix then break return false)
 *      iii) e > x then move it to left (if out of bound of matrix then break return false)
 *  3) repeat the i), ii) and iii) till you find element or returned false
 *  </pre></blockquote>
 */
public class FindAllOccurencesInRowNColSortedMatrix {

    public static void main(String[] args) {
        int mat[][] = {
                {10, 20, 28, 40},
                {15, 25, 29, 45},
                {27, 29, 29, 48},
                {29, 33, 39, 50},
                {29, 34, 40, 51}};

        search(mat, 29);
    }

    private static void search(int[][] mat, int x) {

        int noOfRows = mat.length;
        int noOfColumns = mat[0].length;
        int row = 0, column = noOfColumns - 1;

        while (row < noOfRows && column >= 0) {
            int ele = mat[row][column];
            if (x == ele) {
                System.out.println("(" + row + "," + column + ")");
                binarySearchAndPrint(mat[row], row, column - 1, x);
                row++;
            } else if (ele < x) {
                row++;
            } else {
                column--;
            }
        }
    }

    public static void binarySearchAndPrint(int[] row, int rowNum, int col_high, int x) {
        if (col_high < 0)
            return;
        if (col_high == 0) {
            if (row[col_high] == x) {
                System.out.println("(" + rowNum + "," + col_high + ")");
                return;
            }
        }
        binarySearchAndPrint(row, rowNum, 0, col_high, x);
    }

    public static void binarySearchAndPrint(int[] row, int rowNum, int col_low, int col_high, int x) {
        if (col_low > col_high)
            return;

        int mid = (col_low + col_high) / 2;
        if (row[mid] == x) {
            System.out.println("(" + rowNum + "," + mid + ")");
            int temp = mid + 1;
            while (temp <= col_high && row[temp] == x) {
                System.out.println("(" + rowNum + "," + temp + ")");
                temp++;
            }
            temp = mid - 1;
            while (temp >= 0 && row[temp] == x) {
                System.out.println("(" + rowNum + "," + temp + ")");
                temp--;
            }
        } else if (row[mid] < x) {
            binarySearchAndPrint(row, rowNum, mid + 1, col_high, x);
        } else {
            binarySearchAndPrint(row, rowNum, 0, mid - 1, x);
        }
    }
}

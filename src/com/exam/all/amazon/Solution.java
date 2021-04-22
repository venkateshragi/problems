package com.exam.all.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * << descriptive comments>>
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class Solution {

  int numberAmazonGoStores(int rows, int column, List<List<Integer>> grid) {
    if (rows == 0 || column == 0 || grid == null) return 0;
    int[][] blockGrid = new int[rows][column];
    for (int i = 0; i < rows; i++) {
      List<Integer> row = grid.get(i);
      for (int j = 0; j < column; j++) {
        blockGrid[i][j] = row.get(j);
      }
    }
    int[][] visitedGrid = new int[rows][column];
    int numberOfClustersNeeded = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < column; j++) {
        int block = blockGrid[i][j];
        if (block == 1 && visitedGrid[i][j] == 0) {
          blockGrid[i][j] = 0;
          visitedGrid[i][j] = 1;
          visitContinuesBlocks(i, j, blockGrid, visitedGrid, rows, column);
          numberOfClustersNeeded++;
        }
      }
    }
    return numberOfClustersNeeded;
  }

  void visitContinuesBlocks(int i, int j, int[][] grid, int[][] visitedGrid, int row, int column) {
    if (i + 1 < row && grid[i + 1][j] == 1) {
      visitedGrid[i + 1][j] = 1;
      visitContinuesBlocks(i + 1, j, grid, visitedGrid, row, column);
      grid[i + 1][j] = 0;
    }
    if (j + 1 < column && grid[i][j + 1] == 1) {
      visitedGrid[i][j + 1] = 1;
      visitContinuesBlocks(i, j + 1, grid, visitedGrid, row, column);
      grid[i][j + 1] = 0;
    }
  }

  int minimumHours(int rows, int columns, List<List<Integer>> grid) {
    if (rows == 0 || columns == 0 || grid == null) return 0;
    int[][] blockGrid = new int[rows][columns];
    int synchedServers = 0;
    for (int i = 0; i < rows; i++) {
      List<Integer> row = grid.get(i);
      for (int j = 0; j < columns; j++) {
        blockGrid[i][j] = row.get(j);
        if (blockGrid[i][j] == 1) {
          synchedServers++;
        }
      }
    }
    if (synchedServers == rows * columns) return 0;

    int numberOfHoursNeeded = 0;
    int[][] temp;
    do {
      synchedServers = 0;
      temp = new int[rows][columns];
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          if (blockGrid[i][j] == 1) {
            if (temp[i][j] != 1) synchedServers++;
            temp[i][j] = 1;
            if (i - 1 >= 0 && temp[i - 1][j] != 1) {
              temp[i - 1][j] = 1;
              synchedServers++;
            }
            if (i + 1 < rows && temp[i + 1][j] != 1) {
              temp[i + 1][j] = 1;
              synchedServers++;
            }
            if (j - 1 >= 0 && temp[i][j - 1] != 1) {
              temp[i][j - 1] = 1;
              synchedServers++;
            }
            if (j + 1 < columns && temp[i][j + 1] != 1) {
              temp[i][j + 1] = 1;
              synchedServers++;
            }
          }
        }
      }
      numberOfHoursNeeded++;
      blockGrid = temp;
    } while (synchedServers != rows * columns);
    return numberOfHoursNeeded;
  }

    public static void main(String[] args) {
        int[][] gridInt =
                new int[][] {
                        {1, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 1}
                };
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            grid.add(new ArrayList<Integer>());
            List<Integer> row = grid.get(i);
            for (int j = 0; j < 5; j++) {
                row.add(gridInt[i][j]);
            }
        }
        new Solution().minimumHours(5, 5, grid);
    }

  public static void main1(String[] args) {
    int[][] gridInt =
        new int[][] {
          {1, 1, 0, 0},
          {0, 0, 0, 0},
          {0, 0, 1, 1},
          {0, 0, 0, 0}
        };
    List<List<Integer>> grid = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      grid.add(new ArrayList<Integer>());
      List<Integer> row = grid.get(i);
      for (int j = 0; j < 4; j++) {
        row.add(gridInt[i][j]);
      }
    }
    new Solution().numberAmazonGoStores(4, 4, grid);
  }
}

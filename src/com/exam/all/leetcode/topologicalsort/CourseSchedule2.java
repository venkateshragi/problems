package com.exam.all.leetcode.topologicalsort;

import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * <p>There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
 * take course bi first if you want to take course ai.
 *
 * <p>For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid
 * answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * <p>Example 1:
 *
 * <p>Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of
 * 2 courses to take. To take course 1 you should have finished course 0. So the correct course
 * order is [0,1]. Example 2:
 *
 * <p>Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]] Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished
 * both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one
 * correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3]. Example 3:
 *
 * <p>Input: numCourses = 1, prerequisites = [] Output: [0]
 *
 * <p>Constraints:
 *
 * <p>1 <= numCourses <= 2000 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2 0 <= ai, bi < numCourses ai != bi All the pairs [ai, bi] are
 * distinct.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class CourseSchedule2 {
    //kahn's algorithm - process nodes with indegree 0 in each iteration
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for(int i = 0; i < prerequisites.length; i++) {
            int src = prerequisites[i][1];
            int dest = prerequisites[i][0];
            indegree[dest]++;
            List<Integer> dependentNodes = adjList.getOrDefault(src, new ArrayList<>());
            dependentNodes.add(dest);
            adjList.put(src, dependentNodes);
        }

        Queue<Integer> traversor = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0)
                traversor.offer(i);
        }

        int[] result = new int[numCourses];
        int j = 0;
        while (!traversor.isEmpty()) {
            int node = traversor.remove();
            List<Integer> adjNodes = adjList.get(node);
            if (adjNodes != null) {
                for (int i : adjNodes) {
                indegree[i]--;
                if (indegree[i] == 0)
                    traversor.offer(i);
                }
            }
            result[j++] = node;
        }
        if(j == numCourses)
            return result;

        return new int[0];
    }

    static int WHITE = 1;
    static int GRAY = 2;
    static int BLACK = 3;

    boolean isPossible;
    Map<Integer, Integer> color;
    Map<Integer, List<Integer>> adjList;
    List<Integer> topologicalOrder;

    private void init(int numCourses) {
        this.isPossible = true;
        this.color = new HashMap<Integer, Integer>();
        this.adjList = new HashMap<Integer, List<Integer>>();
        this.topologicalOrder = new ArrayList<Integer>();

        // By default all vertces are WHITE
        for (int i = 0; i < numCourses; i++) {
            this.color.put(i, WHITE);
        }
    }

    private void dfs(int node) {

        // Don't recurse further if we found a cycle already
        if (!this.isPossible) {
            return;
        }

        // Start the recursion
        this.color.put(node, GRAY);

        // Traverse on neighboring vertices
        for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
            if (this.color.get(neighbor) == WHITE) {
                this.dfs(neighbor);
            } else if (this.color.get(neighbor) == GRAY) {
                // An edge to a GRAY vertex represents a cycle
                this.isPossible = false;
            }
        }

        // Recursion ends. We mark it as black
        this.color.put(node, BLACK);
        this.topologicalOrder.add(node);
    }

    //using dfs, we use stack, so the order is reverse.
    //ie., say the stack is from bottom to top [3, 2, 1] we print output top to bottom 1, 2, 3
    //this implies 3 might be dependent on 2, 2 on 1. (may be).
    //but the idea is, bottom nodes are dependent on nodes somewhere up in the stack
    public int[] findOrderdfs(int numCourses, int[][] prerequisites) {
        this.init(numCourses);

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);
        }

        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) {
            if (this.color.get(i) == WHITE) {
                this.dfs(i);
            }
        }

        int[] order;
        if (this.isPossible) {
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                order[i] = this.topologicalOrder.get(numCourses - i - 1);
            }
        } else {
            order = new int[0];
        }

        return order;

    }

    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        print(findOrder(4, prerequisites));

        prerequisites = new int[][]{{1,0}};
        print(findOrder(2, prerequisites));

        prerequisites = new int[][]{};
        print(findOrder(1, prerequisites));

        CourseSchedule2 cs = new CourseSchedule2();
        prerequisites = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        print(cs.findOrderdfs(4, prerequisites));

        prerequisites = new int[][]{{1,0},{0,2}};
        print(cs.findOrderdfs(3, prerequisites));

        prerequisites = new int[][]{};
        print(cs.findOrderdfs(1, prerequisites));

    }

    public static void print(int[] arr) {
        System.out.println();
        for(int i : arr) {
            System.out.print(i + " ");
        }
    }

}

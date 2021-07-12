package com.exam.all.leetcode.graph;

import java.util.*;

/**
 * Let's say you are planning for a road trip with a car, the goal is to find the shortest time for
 * you to get from point A to point B. Given location A location B a list of location pairs that
 * tells you which two locations are connected.
 *
 * <p>Let's Assume that it takes 1 unit of time to travel between a pair of connected locations
 *
 * <p>Example: Input: 1, 4, [[1,2], [1,3], [2,3], [3,4]] Output: 2
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class ShortestPathBetween2Nodes {
    public int calculateSP(int src, int dest, List<List<Integer>> edges) {
        if(edges == null || edges.size() == 0)
            return -1;

        if(src == dest)
            return 0;

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(List<Integer> edge : edges) {
            adjList.computeIfAbsent(edge.get(0), s -> new ArrayList<Integer>()).add(edge.get(1));
            adjList.computeIfAbsent(edge.get(1), s -> new ArrayList<Integer>()).add(edge.get(0));
        }

        Map<Integer, Boolean> visited = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited.put(src, true);
        int dist = 0;

        while(queue.size() != 0) {
            int size = queue.size();
            dist++;

            for(int i = 0; i < size; i++) {
                int curr = queue.poll();
                List<Integer> adjNodes = adjList.get(curr);
                if(adjNodes == null)
                    continue;
                for(int next : adjNodes) {
                    if(next == dest) {
                        return dist;
                    }
                    if(visited.get(next) == null) {
                        visited.put(next, true);
                        queue.add(next);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> edge1 = Arrays.asList(1, 2);
//        List<Integer> edge2 = Arrays.asList(1, 3);
//        List<Integer> edge3 = Arrays.asList(2, 3);
        List<Integer> edge4 = Arrays.asList(3, 4);
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(edge1);
//        edges.add(edge2);
//        edges.add(edge3);
        edges.add(edge4);
        ShortestPathBetween2Nodes shortestPathBetween2Nodes = new ShortestPathBetween2Nodes();
        System.out.println(shortestPathBetween2Nodes.calculateSP(1, 4, edges));
        System.out.println(shortestPathBetween2Nodes.calculateSP(1, 5, edges));
    }
}

package com.exam.all.leetcode.graph;

import java.util.*;

/**
 * https://leetcode.com/problems/network-delay-time/
 *
 * <p>You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of
 * travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the
 * target node, and wi is the time it takes for a signal to travel from source to target.
 *
 * <p>We will send a signal from a given node k. Return the time it takes for all the n nodes to
 * receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2 Output: 2 Example 2:
 *
 * <p>Input: times = [[1,2,1]], n = 2, k = 1 Output: 1 Example 3:
 *
 * <p>Input: times = [[1,2,1]], n = 2, k = 2 Output: -1
 *
 * <p>Constraints:
 *
 * <p>1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 *
 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class NetworkDelayTime {
    //Dijkstra's algorithm usin heap
    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int i = 0; i < times.length; i++) {
            int[] edge = times[i];
            graph.computeIfAbsent(edge[0], s -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        //we will be insserting distance to src and node in heap
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        heap.add(new int[]{0, k});

        Map<Integer, Integer> res = new HashMap<>();

        while(!heap.isEmpty()) {
            int[] next = heap.poll();
            int dis = next[0], node = next[1];
            if(res.containsKey(node))
                continue;
            res.put(node, dis);
            //this check is needed for nodes that do not have edges.
            if(graph.containsKey(node)) {
                for(int[] i : graph.get(node)) {
                    int nei = i[0], d2 = i[1];
                    if(!res.containsKey(nei)) {
                        //we are comparing with distance already in heap as it would be  >= dis+d2.
                        //it cannot be less as it would have been processed by this time if it is less.
                        heap.offer(new int[]{dis + d2, nei});
                    }
                }
            }
        }

        if(res.size() != n)
            return -1;

        int ans = 0;
        for(int val : res.values()) {
            ans = Math.max(ans, val);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][] {{2,1,1},{2,3,1},{3,4,1}}, 4, 2));
        System.out.println(networkDelayTime(new int[][] {{1,2,1}}, 2, 1));
        System.out.println(networkDelayTime(new int[][] {{1,2,1}}, 2, 2));
    }
}

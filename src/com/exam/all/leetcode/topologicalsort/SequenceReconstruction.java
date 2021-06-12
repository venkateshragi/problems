package com.exam.all.leetcode.topologicalsort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/sequence-reconstruction/
 *
 * <p>Check whether the original sequence org can be uniquely reconstructed from the sequences in
 * seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104.
 * Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a
 * shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there
 * is only one sequence that can be reconstructed from seqs and it is the org sequence.
 *
 * <p>Example 1:
 *
 * <p>Input: org = [1,2,3], seqs = [[1,2],[1,3]] Output: false Explanation: [1,2,3] is not the only
 * one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be
 * reconstructed. Example 2:
 *
 * <p>Input: org = [1,2,3], seqs = [[1,2]] Output: false Explanation: The reconstructed sequence can
 * only be [1,2]. Example 3:
 *
 * <p>Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]] Output: true Explanation: The sequences
 * [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3]. Example 4:
 *
 * <p>Input: org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]] Output: true
 *
 * <p>Constraints:
 *
 * <p>1 <= n <= 10^4 org is a permutation of {1,2,...,n}. 1 <= segs[i].length <= 10^5 seqs[i][j]
 * fits in a 32-bit signed integer.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class SequenceReconstruction {
    public static boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        // Find is there is 1 and only 1 topological sorting.

        if (seqs.isEmpty()) return false; // yes this is google question, there will be non-obvious corner cases

        int N = org.length;
        int[] indegree = new int[N + 1];
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (List<Integer> seq : seqs) {
            if (seq.get(0) < 1 || seq.get(0) > N) return false; // yes google
            for (int i = 1; i < seq.size(); i++) {
                if (seq.get(i) < 1 || seq.get(i) > N) return false; // yes google
                indegree[seq.get(i)]++;
                adjList.computeIfAbsent(seq.get(i - 1), k -> new ArrayList<>()).add(seq.get(i));
            }
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) list.add(i);
        }

        int p = 0;

        while (!list.isEmpty()) {
            if (list.size() > 1) return false; // multiple choices so far
            if (list.get(0) != org[p]) return false; // diff topo order

            List<Integer> next = new ArrayList<>();

            for (int nei : adjList.getOrDefault(list.get(0), new ArrayList<>())) {
                indegree[nei]--;
                if (indegree[nei] == 0) next.add(nei);
            }

            list = next;
            p++;
        }

        return p == N;
    }

    public static void main(String[] args) {
        int[] org = new int[]{1,2,3};
        int[][] prerequisites = new int[][] {{1, 2}};
        System.out.println(sequenceReconstruction(org, convertToList(prerequisites)));

        org = new int[]{1,2,3};
        prerequisites = new int[][] {{1, 2},{1, 3}, {2,3}};
        System.out.println(sequenceReconstruction(org, convertToList(prerequisites)));

        org = new int[]{1,2,3};
        prerequisites = new int[][] {{1, 2}};
        System.out.println(sequenceReconstruction(org, convertToList(prerequisites)));

        org = new int[]{4,1,5,2,6,3};
        prerequisites = new int[][] {{5,2,6,3},{4,1,5,2}};
        System.out.println(sequenceReconstruction(org, convertToList(prerequisites)));
    }

    private static List<List<Integer>> convertToList(int[][] ar) {
        List<List<Integer>> result = new ArrayList<>();
        for(int[] arr : ar) {
            result.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        }
        return result;
    }
}

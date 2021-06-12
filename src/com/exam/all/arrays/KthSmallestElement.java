package com.exam.all.arrays;

import java.util.PriorityQueue;

/**
 * << descriptive comments>>
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class KthSmallestElement {

    public static int kthsmallest(final int[] A, int B) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b-a);
        for(int i = 0; i < B; i++) {
            heap.add(A[i]);
        }
        for(int i = B; i < A.length; i++) {
            if(heap.peek() > A[i]) {
                heap.poll();
                heap.add(A[i]);
            }
        }
        return heap.poll();
    }

    public static void main(String[] args) {
        System.out.println(kthsmallest(new int[]{2, 1, 4, 3, 2}, 3));
    }
}

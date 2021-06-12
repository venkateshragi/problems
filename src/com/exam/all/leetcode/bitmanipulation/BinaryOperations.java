package com.exam.all.leetcode.bitmanipulation;

/**
 * https://www.interviewbit.com/tutorial/tricks-with-bits/#tricks-with-bits
 *
 * <p>x & (x-1) will clear the lowest set bit of
 * <p>x x & ~(x-1) extracts the lowest set bit of x (all others are clear). Pretty patterns when applied to a linear sequence.
 * <p>x & (x + (1 << n)) = x, with the run of set bits (possibly length 0) starting at bit n cleared.
 * <p>x & ~(x + (1 << n)) = the run of set bits (possibly length 0) in x, starting at bit n.
 * <p>x | (x + 1) = x with the lowest cleared bit set.
 * <p> x | ~(x + 1) = extracts the lowest cleared bit of x (all others are set).
 * <p>x | (x- (1 << n)) = x, with the run of cleared bits (possibly length 0) starting at bit n set.
 * <p>x | ~(x- (1 << n)) = the lowest run of cleared bits (possibly length 0) in x, starting at bit n are the only clear bits.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class BinaryOperations {}

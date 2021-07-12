package com.exam.all.leetcode.trie;

import java.util.*;

/**
 * https://leetcode.com/problems/design-search-autocomplete-system/
 *
 * <p>Design a search autocomplete system for a search engine. Users may input a sentence (at least
 * one word and end with a special character '#').
 *
 * <p>You are given a string array sentences and an integer array times both of length n where
 * sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the
 * sentence was typed. For each input character except '#', return the top 3 historical hot
 * sentences that have the same prefix as the part of the sentence already typed.
 *
 * <p>Here are the specific rules:
 *
 * <p>The hot degree for a sentence is defined as the number of times a user typed the exactly same
 * sentence before. The returned top 3 hot sentences should be sorted by hot degree (The first is
 * the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller
 * one appears first). If less than 3 hot sentences exist, return as many as you can. When the input
 * is a special character, it means the sentence ends, and in this case, you need to return an empty
 * list. Implement the AutocompleteSystem class:
 *
 * <p>AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences
 * and times arrays. List<String> input(char c) This indicates that the user typed the character c.
 * Returns an empty array [] if c == '#' and stores the inputted sentence in the system. Returns the
 * top 3 historical hot sentences that have the same prefix as the part of the sentence already
 * typed. If there are fewer than 3 matches, return them all.
 *
 * <p>Example 1:
 *
 * <p>Input ["AutocompleteSystem", "input", "input", "input", "input"] [[["i love you", "island",
 * "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]] Output [null, ["i love
 * you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]
 *
 * <p>Explanation AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman",
 * "i love leetcode"], [5, 3, 2, 2]); obj.input("i"); // return ["i love you", "island", "i love
 * leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love
 * leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love
 * leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so
 * "ironman" will be ignored. obj.input(" "); // return ["i love you", "i love leetcode"]. There are
 * only two sentences that have prefix "i ". obj.input("a"); // return []. There are no sentences
 * that have prefix "i a". obj.input("#"); // return []. The user finished the input, the sentence
 * "i a" should be saved as a historical sentence in system. And the following input will be counted
 * as a new search.
 *
 * <p>Constraints:
 *
 * <p>n == sentences.length n == times.length 1 <= n <= 100 1 <= sentences[i].length <= 100 1 <=
 * times[i] <= 50 c is a lowercase English letter, a hash '#', or space ' '. Each tested sentence
 * will be a sequence of characters c that end with the character '#'. Each tested sentence will
 * have a length in the range [1, 200]. The words in each input sentence are separated by single
 * spaces. At most 5000 calls will be made to input.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class SearchAutoCompleteSystem {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
    }

    TrieNode root;
    String sb;

    public SearchAutoCompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        sb = new String();

        for(int i = 0; i < sentences.length; i++) {
            addToTrie(sentences[i], root, times[i]);
        }
    }

    private TrieNode addToTrie(String s, TrieNode root, int count) {
        TrieNode cur = root;
        for(char c : s.toCharArray()) {
            TrieNode nxt = cur.children.get(c);
            if(nxt == null) {
                nxt = new TrieNode();
                cur.children.put(c, nxt);
            }
            cur = nxt;
            cur.counts.put(s, cur.counts.getOrDefault(s, 0) + count);
        }
        return cur;
    }

    public List<String> input(char c) {
        if(c == '#') {
            addToTrie(sb.toString(), root, 1);
            sb = new String();
            return Collections.emptyList();
        }
        sb = sb + c;
        TrieNode cur = root;
        for(char cc : sb.toCharArray()) {
            if(!cur.children.containsKey(cc))
                return Collections.emptyList();
            cur = cur.children.get(cc);
        }

        PriorityQueue<Map.Entry<String, Integer>> que =
                new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey())
                        : b.getValue() - a.getValue());
        que.addAll(cur.counts.entrySet());

        List<String> res = new ArrayList<>();
        for(int i = 0; i < 3 && !que.isEmpty(); i++) {
            res.add((String) que.poll().getKey());
        }
        return res;
    }
}

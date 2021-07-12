package com.exam.all.leetcode.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/sentence-similarity-ii/
 *
 * <p>We can represent a sentence as an array of words, for example, the sentence "I am happy with
 * leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].
 *
 * <p>Given two sentences sentence1 and sentence2 each represented as a string array and given an
 * array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words
 * xi and yi are similar.
 *
 * <p>Return true if sentence1 and sentence2 are similar, or false if they are not similar.
 *
 * <p>Two sentences are similar if:
 *
 * <p>They have the same length (i.e., the same number of words) sentence1[i] and sentence2[i] are
 * similar. Notice that a word is always similar to itself, also notice that the similarity relation
 * is transitive. For example, if the words a and b are similar, and the words b and c are similar,
 * then a and c are similar.
 *
 * <p>Example 1:
 *
 * <p>Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"],
 * similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]] Output:
 * true Explanation: The two sentences have the same length and each word i of sentence1 is also
 * similar to the corresponding word in sentence2. Example 2:
 *
 * <p>Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs
 * = [["manga","onepiece"],["platform","anime"],["leetcode","platform"],["anime","manga"]] Output:
 * true
 * Explanation: "leetcode" --> "platform" --> "anime" --> "manga" --> "onepiece". Since
 * "leetcode is similar to "onepiece" and the first two words are the same, the two sentences are
 * similar. Example 3:
 *
 * <p>Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs
 * = [["manga","hunterXhunter"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
 * Output: false Explanation: "leetcode" is not similar to "onepiece".
 *
 * <p>Constraints:
 *
 * <p>1 <= sentence1.length, sentence2.length <= 1000 1 <= sentence1[i].length, sentence2[i].length
 * <= 20 sentence1[i] and sentence2[i] consist of lower-case and upper-case English letters. 0 <=
 * similarPairs.length <= 2000 similarPairs[i].length == 2 1 <= xi.length, yi.length <= 20 xi and yi
 * consist of English letters.
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class SentenseSimilarity {
    int[] parent;
    int[] rank;

    private int find(int i) {
        if(parent[i] != i)
            parent[i] = find(parent[i]);
        return parent[i];
    }

    private void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);
        if(p1 == p2)
            return;
        if(rank[p1] < rank[p2]) {
            parent[p1] = p2;
            rank[p1]++;
        }
        else if(rank[p2] < rank[p1]) {
            parent[p2] = p1;
            rank[p2]++;
        }
        else {
            parent[p2] = p1;
            rank[p2]++;
        }
    }

    /*
    using the union find to create connected components of similar words
     */
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        int len1 = sentence1.length;
        int len2 = sentence2.length;

        if(len1 != len2)
            return false;

        Map<String, Integer> map = new HashMap<>();
        int count = 0;

        parent = new int[2 * similarPairs.size()];
        rank = new int[2 * similarPairs.size()];

        for(int i = 0; i < parent.length; i++)
            parent[i] = i;

        for(List<String> pair : similarPairs) {
            for(String s : pair)  {
                if(!map.containsKey(s))
                    map.put(s, count++);
            }
            union(map.get(pair.get(0)), map.get(pair.get(1)));
        }

        for(int i = 0; i < len1; i++) {
            if(sentence1[i].equals(sentence2[i]))
                continue;
            if(!map.containsKey(sentence1[i]) || !map.containsKey(sentence2[i]) ||
                    find(map.get(sentence1[i])) != find(map.get(sentence2[i])))
                return false;
        }
        return true;

    }
}

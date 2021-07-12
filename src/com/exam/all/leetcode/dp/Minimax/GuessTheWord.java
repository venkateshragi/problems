package com.exam.all.leetcode.dp.Minimax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * https://leetcode.com/problems/guess-the-word/
 *
 * <p>This is an interactive problem.
 *
 * <p>You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one
 * word in this list is chosen as secret.
 *
 * <p>You may call Master.guess(word) to guess a word. The guessed word should have type string and
 * must be from the original list with 6 lowercase letters.
 *
 * <p>This function returns an integer type, representing the number of exact matches (value and
 * position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it
 * will return -1 instead.
 *
 * <p>For each test case, you have exactly 10 guesses to guess the word. At the end of any number of
 * calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was
 * secret, then you pass the test case.
 *
 * <p>Example 1:
 *
 * <p>Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
 * Output: You guessed the secret word correctly. Explanation: master.guess("aaaaaa") returns -1,
 * because "aaaaaa" is not in wordlist. master.guess("acckzz") returns 6, because "acckzz" is secret
 * and has all 6 matches. master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches. master.guess("abcczz") returns
 * 4, because "abcczz" has 4 matches. We made 5 calls to master.guess and one of them was the
 * secret, so we pass the test case. Example 2:
 *
 * <p>Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10 Output: You guessed
 * the secret word correctly.
 *
 * <p>Constraints:
 *
 * <p>1 <= wordlist.length <= 100 wordlist[i].length == 6 wordlist[i] consist of lowercase English
 * letters. All the strings of wordlist are unique. secret exists in wordlist. numguesses == 10
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class GuessTheWord {

  /**
   * // This is the Master's API interface. // You should not implement it, or speculate about its
   * implementation interface Master { public int guess(String word) {} }
   */
  static class Master {

    String secret;

    public Master(String secret) {
      this.secret = secret;
    }
    // returns no. of char matches b/w secrete and passed s => count of matches at respective pos
    // and char
    // ie., s[i] == secret[i]
    int guess(String s) {
      return this.match(s, secret);
    }

    public int match(String a, String b) {
      int matches = 0;
      for (int i = 0; i < a.length(); ++i) if (a.charAt(i) == b.charAt(i)) matches++;
      return matches;
    }
  }

  // https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
  public void findSecretWord(String[] wordlist, Master master) {

    for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
      HashMap<String, Integer> count = new HashMap<>();
      for (String w1 : wordlist)
        for (String w2 : wordlist)
          if (match(w1, w2) == 0) count.put(w1, count.getOrDefault(w1, 0) + 1);
      String guess = "";
      int min0 = 100;
      for (String w : wordlist)
        if (count.getOrDefault(w, 0) < min0) {
          guess = w;
          min0 = count.getOrDefault(w, 0);
        }
      x = master.guess(guess);
      List<String> wordlist2 = new ArrayList<String>();
      for (String w : wordlist) if (match(guess, w) == x) wordlist2.add(w);
      wordlist = wordlist2.toArray(new String[0]);
    }
  }

  public int match(String a, String b) {
    int matches = 0;
    for (int i = 0; i < a.length(); ++i) if (a.charAt(i) == b.charAt(i)) matches++;
    return matches;
  }

  public void findSecretWordRandomGuess(String[] wordlist, Master master) {
    for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
      String guess = wordlist[new Random().nextInt(wordlist.length)];
      x = master.guess(guess);
      List<String> wordlist2 = new ArrayList<>();
      for (String w : wordlist) if (match(guess, w) == x) wordlist2.add(w);
      wordlist = wordlist2.toArray(new String[wordlist2.size()]);
    }
  }

  /**
   * We just need to count the occurrence for each character on each position.
   *
   * <p>If we can guess the word that not in the wordlist, we can guess the word based on the most
   * frequent character on the position.
   *
   * <p>Here we have to guess a word from the list, we still can calculate a score of similarity for
   * each word, and guess the word with highest score.
   *
   * <p>Time complexity O(N) Space complexity O(N)
   *
   * @param wordlist
   * @param master
   */
  public void findSecretWordLinear(String[] wordlist, Master master) {
    for (int t = 0, x = 0; t < 10 && x < 6; ++t) {
      int count[][] = new int[6][26], best = 0;
      for (String w : wordlist) for (int i = 0; i < 6; ++i) count[i][w.charAt(i) - 'a']++;

      String guess = wordlist[0];
      for (String w : wordlist) {
        int score = 0;
        for (int i = 0; i < 6; ++i) score += count[i][w.charAt(i) - 'a'];
        if (score > best) {
          guess = w;
          best = score;
        }
      }
      x = master.guess(guess);
      List<String> wordlist2 = new ArrayList<String>();
      for (String w : wordlist) if (match(guess, w) == x) wordlist2.add(w);
      wordlist = wordlist2.toArray(new String[0]);
    }
  }

  public static void main(String[] args) {
    GuessTheWord guessTheWord = new GuessTheWord();

    args = new String[] {"ccbazz", "acckzz", "eiowzz", "abcczz"};
    guessTheWord.findSecretWord(args, new Master("acckzz"));

    args = new String[] {"hamada", "khaled"};
    guessTheWord.findSecretWord(args, new Master("hamada"));
  }
}

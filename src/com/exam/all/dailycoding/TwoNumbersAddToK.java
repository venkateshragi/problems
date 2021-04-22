package com.exam.all.dailycoding;

import java.util.*;

/**
 * << descriptive comments>>
 *
 * @author vragi
 * @since <<buildnumber>>
 *
 *     Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 *     For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 *
 *     Brute force: O(n 2) by checking each pair of numbers using 2 for loops
 *
 *     Solution implemented below: O(n) - by traversing through list twice,
 *          once to fill a map with all elements and
 *          now traverse given list again to check if a element exists in map that adds up to given sum
 *
 *
 */
public class TwoNumbersAddToK {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sum:");
        int sum = Integer.parseInt(scanner.nextLine());
        System.out.println("Eneter numbers");
        List<Integer> listOfNumbers = new ArrayList<>();
        Map<Integer, Boolean> map = new HashMap<>();

        while(scanner.hasNextInt()) {
            int element = scanner.nextInt();
            listOfNumbers.add(element);
            map.put(element, true);
        }
        for(int element : listOfNumbers) {
            if(map.containsKey(sum - element)) {
                System.out.println("yes");
                return;
            }
        }
        System.out.println("No");
    }

}

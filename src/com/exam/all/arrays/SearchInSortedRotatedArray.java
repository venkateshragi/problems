package com.exam.all.arrays;

/**
 * https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
 * @author vragi
 */
public class SearchInSortedRotatedArray {

    public static int find(int arr[], int low, int high, int key) {

        if (low > high)
            return -1;
        int mid = (low + high) / 2;
        if(arr[mid] == key)
            return mid;

        if(arr[low] <= arr[mid]){
            if(arr[low] <= key && key < arr[mid]) {
                return find(arr, low, mid - 1, key);
            }
            return find(arr, mid + 1, high, key);
        }
        if(arr[mid] < key && key <= arr[high])
            return find(arr, mid + 1, high, key);
        return find(arr, low, mid - 1, key);
    }

    public static void main (String[] args) {
        int arr[] = {4, 5, 6, 7, 8, 9, 10, 1, 2, 3};
        System.out.println(find(arr, 0, arr.length - 1, 0));
    }


}

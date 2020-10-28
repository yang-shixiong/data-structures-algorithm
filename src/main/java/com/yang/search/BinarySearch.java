package com.yang.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/23
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234, 2222};
        List<Integer> list = new ArrayList<>();
        binarySearch(0, arr.length, 1000, arr, list);
        System.out.println(list);
        System.out.println(binarySearchNoRecursion(arr, 10));
    }

    public static void binarySearch(int left, int right, int target, int[] arr, List<Integer> indexes) {
        if (left > right) {
            System.out.println("none");
        } else {
            int pivot = (left + right) / 2;
            if (arr[pivot] == target) {
                indexes.add(pivot);
                int m = pivot;
                while (m > 0 && arr[m - 1] == target) {
                    indexes.add(m - 1);
                    m--;
                }
                while (pivot < arr.length - 1 && arr[pivot + 1] == target) {
                    indexes.add(pivot + 1);
                    pivot++;
                }

            } else if (arr[pivot] > target) {
                binarySearch(left, pivot - 1, target, arr, indexes);
            } else if (arr[pivot] < target) {
                binarySearch(pivot + 1, right, target, arr, indexes);
            }
        }

    }

    public static int binarySearchNoRecursion(int[] arr, int target) {
        int left = 0;
        int right = arr.length -1;
        while (left <= right){
            int pivot = (left + right) / 2;

            if(arr[pivot] == target){
                return pivot;
            }else {
                if(arr[pivot] > target){
                    right = pivot - 1;
                }else {
                    left = pivot +1;
                }
            }
        }
        return -1;
    }
}

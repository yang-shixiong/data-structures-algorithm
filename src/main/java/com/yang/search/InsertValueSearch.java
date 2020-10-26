package com.yang.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/23
 */
public class InsertValueSearch {

    public static int[] arr;

    public static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        arr = new int[]{1, 8, 10, 89, 1000, 1000, 1234, 2222};
        insertValueSearch(0, arr.length-1, 2222);
        System.out.println(list);
    }

    public static void insertValueSearch(int left, int right, int target){
        if(left > right || arr[left] > target || arr[right] < target){
            System.out.println("none");
        }else {
            int pivot = (right - left) * (target - arr[left]) / (arr[right] - arr[left]) + left;
            if(arr[pivot] > target){
                insertValueSearch(left, pivot -1, target);
            }else if(arr[pivot] < target){
                insertValueSearch(pivot + 1, right, target);
            }else {
                list.add(pivot);

                int m = pivot;
                while (m > 0 && arr[m-1] == target){
                    list.add(--m);
                }
                while (pivot < arr.length-1 && arr[pivot + 1] == target){
                    list.add(++pivot);
                }
            }
        }
    }
}

package com.yang.sort;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/23
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {2, 7, 9, 3, 8, -1, 34, -9};
        sort(0, array.length-1, array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int left, int right, int[] arr) {
        int l = left;
        int r = right;
        int pivot = arr[(l + r) / 2];

        while (l < r){
            while (arr[l] < pivot){
                l ++;
            }
            while (arr[r] > pivot){
                r --;
            }
            if(l >=r){
                break;
            }
            int tem = arr[l];
            arr[l] = arr[r];
            arr[r] = tem;
            if(arr[l] == pivot){
                r--;
            }
            if(arr[r] == pivot){
                l++;
            }
        }
        if(l == r){
            l++;
            r--;
        }
        if(left < r){
            sort(left, r, arr);
        }
        if(l < right){
            sort(l, right, arr);
        }

    }
}

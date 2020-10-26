package com.yang.sort;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/23
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = {2, 7, 9, 3, 8, -1, 34, -9};
        int[] ints = new int[array.length];
        sort(0, array.length - 1, array, ints);
        System.out.println(Arrays.toString(ints)); // [-9, -1, 2, 3, 7, 8, 9, 34]
    }

    public static void sort(int left, int right, int[] arr, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;

            sort(left, mid, arr, temp);
            sort(mid + 1, right, arr, temp);
            merge(left, mid, right, arr, temp);
        }
    }

    public static void merge(int left, int mid, int right, int[] arr, int[] temp) {
        int l = left;
        int r = mid + 1;
        int index = 0;

//        while (l <= mid && r <= right) {
//            if (arr[l] <= arr[r]) {
//                temp[index] = arr[l];
//                l++;
//            } else {
//                temp[index] = arr[r];
//                r++;
//            }
//            index++;
//        }
//        while (l <= mid) {
//            temp[index] = arr[l];
//            l++;
//            index++;
//        }
//        while (r <= right) {
//            temp[index] = arr[r];
//            r++;
//            index++;
//        }

        while (l <= mid || r <= right) {

            if (l > mid) {
                temp[index] = arr[r];
                r++;
            } else if (r > right) {
                temp[index] = arr[l];
                l++;

            } else {
                if (arr[l] >= arr[r]) {
                    temp[index] = arr[r];
                    r++;
                } else {
                    temp[index] = arr[l];
                    l++;
                }
            }
            index++;
        }

        int i = 0;
        int ll = left;
        while (ll <= right) {
            arr[ll] = temp[i];
            ll++;
            i++;
        }

    }
}

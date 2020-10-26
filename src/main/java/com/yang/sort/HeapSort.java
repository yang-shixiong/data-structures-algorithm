package com.yang.sort;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/26
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = {2, 7, 9, 3, 8, -1, 34};

        for (int i = array.length / 2 - 1; i >= 0; i--) {
            sort(array, i, array.length);
        }
        for(int j = array.length -1; j >0 ; j --){
            int temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            sort(array, 0, j);

        }
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[i] < arr[k]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;

    }


}

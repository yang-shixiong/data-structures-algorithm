package com.yang.sort;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/23
 */
public class RadixSort {

    public static void main(String[] args) {

        int[] array = {2, 7, 9, 3, 8, 1, 34, 10, 34};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] arr) {
        int temp = arr[0];
        for (int i : arr) {
            if(temp < i){
                temp = i;
            }
        }
        int length = String.valueOf(temp).length();
        int[] indexes = new int[10];
        int[][] buckets = new int[10][arr.length];
        for(int i = 0, n = 1; i < length; i ++, n *= 10){
            for (int j = 0; j < arr.length; j ++){
//                int value = i == 0 ? arr[j] % 10 : arr[j] / (i * 10) % 10;
                int value =  arr[j] / n % 10;
                buckets[value][indexes[value]] = arr[j];
                indexes[value] ++;
            }
            int index = 0;
            for (int k = 0; k < buckets.length; k ++){
                for (int m = 0; m < indexes[k]; m ++){
                    arr[index] = buckets[k][m];
                    index++;
                }
                indexes[k] = 0;
            }
        }

    }
}

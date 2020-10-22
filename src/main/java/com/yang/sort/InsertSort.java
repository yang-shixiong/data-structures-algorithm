package com.yang.sort;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/22
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = {2, 7, 9, 3, 8, -1, 34, -9};

        for (int i = 1; i < array.length ; i++){
            int temp = array[i];
            for (int j = 0; j < i; j ++){
                if(array[j] > temp){
                    int i1 = array[j];
                    array[j] = temp;
                    temp = i1;
                }
            }
            array[i] = temp;
        }
        System.out.println(Arrays.toString(array));
    }
}

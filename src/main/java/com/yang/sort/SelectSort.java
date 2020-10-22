package com.yang.sort;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/22
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {2, 7, 9, 3, 8, -1, 34};
        int temp;
        for (int i = 0; i < array.length; i++) {
            temp = array[i];
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < temp) {
                    temp = array[j];
                    index = j;
                }
            }
            if (index == i) {
                System.out.println("no change");
                break;
            } else {
                array[index] = array[i];
                array[i] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}

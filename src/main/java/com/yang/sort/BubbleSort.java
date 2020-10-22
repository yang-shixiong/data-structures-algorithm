package com.yang.sort;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/22
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {2,7, 9, 3, 8, -1, 34};

        boolean flag = false;

        int count = 0;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                count ++;
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                System.out.println("not change");
                break;
            }else {
                flag = false;
            }
        }
        System.out.println(Arrays.toString(array));
        System.out.println("count: " + count);
    }
}

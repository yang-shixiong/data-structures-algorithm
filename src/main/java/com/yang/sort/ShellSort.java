package com.yang.sort;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/22
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 800000);
        }

        long l = System.currentTimeMillis();
        sort2(array); // 1705
        System.out.println("sort2 use time: " + (System.currentTimeMillis() - l));
    }

    public static void sort(int[] array) {
        for (int grep = array.length / 2; grep > 0; grep /= 2) {
            for (int i = grep; i < array.length; i++) {
                for (int j = i - grep; j >= 0; j -= grep) {
                    if (array[j] > array[j + grep]) {
                        int temp = array[j];
                        array[j] = array[j + grep];
                        array[j + grep] = temp;
                    }
                }
            }
        }
    }

    public static void sort2(int[] array) {
        for (int grep = array.length / 2; grep > 0; grep /= 2) {
            for (int i = grep; i < array.length; i++) {

                if(array[i] < array[i-grep]){
                    int temp = array[i];
                    int index = i;
                    while (index >= grep && temp < array[index - grep]) {
                        array[index] = array[index - grep];
                        index -= grep;
                    }
                    array[index] = temp;
                }
            }
        }
    }
}

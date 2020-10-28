package com.yang.algorithm;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/28
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        int[] weight = {1, 4, 3};
        int[] price = {1500, 3000, 2000};
        int knapsack = 6;
        int[][] arr = new int[weight.length+1][knapsack+1];
        for (int i = 0; i <= weight.length; i++) {
            arr[i][0] = 0;
        }
        for (int i = 0; i <= knapsack; i++) {
            arr[0][i] = 0;
        }
        for (int i = 1; i<= weight.length; i++){
            for (int j = 1; j <= knapsack; j++){
                if(weight[i-1] > j){
                    arr[i][j] = arr[i-1][j];
                }else {
                    arr[i][j] = Math.max(arr[i-1][j], price[i-1] + arr[i-1][j-weight[i-1]]);
                }
            }
        }
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        int i = 0;
        int j = 0;
        int temp = 0;
        for (int m = 0; m < arr.length; m ++){
            for (int n = 0; n < arr[m].length; n ++){
                if(arr[m][n] > temp){
                    i = m;
                    j = n;
                }
            }
        }
        while (arr[i][j] != 0){
            if(j >= weight[i-1]){
                int i2 = price[i - 1] + arr[i-1][j-weight[i-1]];
                int i1 = arr[i - 1][j];
                if(i2 >= i1){
                    System.out.println("knapsack has " + weight[i-1]);
                    j = j - weight[i-1];
                }
            }
            i = i-1;
        }
    }
}

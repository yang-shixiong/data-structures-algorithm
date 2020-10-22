package com.yang.algorithm;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/22
 */
public class EightQueue {

    public static int count = 0;

    public static void main(String[] args) {
        start(new int[8], 0);
        System.out.println("count: " + count);
    }

    public static void start(int[] arr, int start){
        if(start == 8){
            count++;
            System.out.println("find one way");
            System.out.println(Arrays.toString(arr));
            return;
        }
        for(int j = 0; j< arr.length; j++){
            arr[start] = j;
            if(check(start, arr)){
                start(arr, start + 1);
            }
        }
    }

    public static boolean check(int n, int[] arr){
        for(int k = 0; k < n; k++){
            if(Math.abs(arr[k] - arr[n]) == Math.abs(k - n) || arr[k] == arr[n]){
                return false;
            }
        }
        return true;
    }
}

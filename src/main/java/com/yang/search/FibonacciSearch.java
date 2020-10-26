package com.yang.search;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/23
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234, 2222};
        int[] fibonacci = makeFibonacci(8);
        search(arr, fibonacci, 2222);
    }

    public static int[] makeFibonacci(int n){
        int[] fibonacci = new int[n];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for(int i = 2; i < n; i++){
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        return fibonacci;
    }

    public static void search(int[] arr, int[] fibonacci, int target){
        if(arr[0] > target || arr[arr.length-1] < target){
            System.out.println("not find");
            return;
        }
        int left = 0;
        int right = arr.length -1;
        int k = 0;
        while (right > fibonacci[k]-1){
            k ++;
        }

        int[] temp = Arrays.copyOf(arr, fibonacci[k]);
        for(int i = right +1; i < temp.length; i++){
            temp[i] = arr[right];
        }

        while (left <= right){
            int mid = left + fibonacci[k -1] -1;
            if(target > temp[mid]){
                left = mid + 1;
                k -=2;
            }else if(target < temp[mid]){
                right = mid - 1;
                k --;
            }else {
                if(mid >= arr.length -1){
                    System.out.println(arr.length -1);
                }else {
                    System.out.println(mid);
                }
                break;
            }
        }

        System.out.println("non");

    }
}

package com.yang.search;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/23
 */
public class LineSearch {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 22, 33, 44};
        int target = 22;
        for(int i =0; i < arr.length; i++){
            if(arr[i] == target){
                System.out.println("find it, index is: " + i);
                break;
            }
        }
    }
}

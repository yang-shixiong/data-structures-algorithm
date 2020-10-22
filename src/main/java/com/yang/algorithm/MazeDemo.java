package com.yang.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/22
 */
public class MazeDemo {

    public static void main(String[] args) {
        int[][] array = createArr(8, 9, 6);
        int endRow = 6;
        int endColumn = 7;
        array[endRow][endColumn] = 0;
        int startRow = 1;
        int startColumn = 1;
        array[startRow][startColumn] = 0;
        start(startRow, startColumn, endRow, endColumn, array, 0);
        System.out.println("after-----------------");
        for (int[] ints : array) {
            System.out.println(Arrays.toString(ints));
        }

    }

    public static int[][] createArr(int row, int column, int obstacleCount){
        int[][] arr = new int[row][column];
        for (int i = 0; i < row; i++){
            arr[i][0] = 1;
            arr[i][column-1] = 1;
        }
        for (int i = 0; i < column; i++){
            arr[0][i] = 1;
            arr[row-1][i] = 1;
        }
        Random random = new Random();
        for(int i = 0; i < obstacleCount; i++){
            arr[random.nextInt(row -1)][random.nextInt(column - 1)] = 1;
        }

        System.out.println("maze is: -------------------------");
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        return arr;
    }

    public static boolean start(int startRow, int startColumn, int endRow, int endColumn, int[][] arr, int count){
        if(arr[endRow][endColumn] == 2){
            System.out.println("total count: " + count);
            return true;
        }else {
            if(arr[startRow][startColumn] == 0){
                arr[startRow][startColumn] = 2;
                if(start(startRow + 1, startColumn, endRow, endColumn, arr, count + 1)){
                    return true;
                }else if(start(startRow, startColumn + 1, endRow, endColumn, arr, count + 1)){
                    return true;
                }else if(start(startRow -1, startColumn, endRow, endColumn, arr, count + 1)){
                    return true;
                }else if(start(startRow, startColumn-1, endRow, endColumn, arr, count + 1)){
                    return true;
                }else {
                    arr[startRow][startColumn] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }


    }
}

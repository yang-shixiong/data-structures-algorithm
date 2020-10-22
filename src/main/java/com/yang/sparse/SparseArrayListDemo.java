package com.yang.sparse;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/21
 */
public class SparseArrayListDemo {

    public static void main(String[] args) {
        int[][] arr = new int[8][8];
        arr[2][1] = 6;
        arr[4][3] = 2;
        print(arr);
        int sum = 0;
        for (int[] value : arr) {
            for (int i : value) {
                if (i != 0) {
                    sum++;
                }
            }
        }
        int[][] sparse = new int[sum + 1][3];
        sparse[0][0] = arr.length;
        sparse[0][1] = arr[0].length;
        sparse[0][2] = sum;
        int index = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sparse[index][0] = i;
                    sparse[index][1] = j;
                    sparse[index][2] = arr[i][j];
                    index++;
                }
            }
        }
        print(sparse);

        // 恢复
        int[][] backSparse = new int[sparse[0][0]][sparse[0][1]];
        for(int i = 0; i < sparse[0][2]; i ++){
            backSparse[sparse[i+1][0]][sparse[i+1][1]] = sparse[i+1][2];
        }
        print(backSparse);
    }

    public static void print(int[][] arr){
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.printf("%d \t", anInt);
            }
            System.out.println();
        }
    }
}
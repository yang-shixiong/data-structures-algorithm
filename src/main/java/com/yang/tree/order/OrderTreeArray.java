package com.yang.tree.order;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/26
 */
public class OrderTreeArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        System.out.println("pre order");
        preOrder(arr, 0);
        System.out.println("\nmiddle order");
        middleOrder(arr, 0);
        System.out.println("\npost order");
        postOrder(arr, 0);
    }

    public static void preOrder(int[] arr, int n) {
        if (n > (arr.length - 1) || n < 0) {
            return;
        }
        System.out.print(arr[n] + "\t");
        if ((2 * n + 1) < arr.length) {
            preOrder(arr, 2 * n + 1);
        }
        if ((2 * n + 2) < arr.length) {
            preOrder(arr, 2 * n + 2);
        }
    }

    public static void middleOrder(int[] arr, int n) {
        if (n > arr.length - 1) {
            return;
        }
        if ((2 * n + 1) < arr.length) {
            middleOrder(arr, 2 * n + 1);
        }
        System.out.print(arr[n] + "\t");
        if ((2 * n + 2) < arr.length) {
            middleOrder(arr, 2 * n + 2);
        }
    }

    public static void postOrder(int[] arr, int n) {
        if (n > arr.length - 1) {
           return;
        }
        postOrder(arr, 2 * n + 1);
        postOrder(arr, 2 * n + 2);
        System.out.print(arr[n] + "\t");
    }
}

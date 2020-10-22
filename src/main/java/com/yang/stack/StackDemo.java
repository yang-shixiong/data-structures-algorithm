package com.yang.stack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/21
 */
public class StackDemo {
    public static void main(String[] args) {
        Stack stack = new Stack(8);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("a: add");
            System.out.println("d: pop");
            System.out.println("e: exists");
            System.out.println("s: show");
            String next = scanner.next();
            switch (next) {
                case "a":
                    System.out.println("输入value:");
                    int i = scanner.nextInt();
                    stack.add(i);
                    break;
                case "p":
                    System.out.println(stack.pop());
                    break;
                case "e":
                    flag = false;
                    scanner.close();
                    break;
                case "s":
                    stack.show();
                    break;
            }

        }
    }
}


class Stack {

    protected int maxSize;

    protected int[] arr;

    protected int top = -1;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void add(int value) {
        if (isFull()) {
            System.out.println("full");
            return;
        }
        arr[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("empty");
            return -1;
        }
        return arr[top--];
    }

    public void show() {
        System.out.println(Arrays.toString(arr));
        System.out.println("top " + top);
    }
}
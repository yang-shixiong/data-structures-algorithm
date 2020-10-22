package com.yang.queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/21
 */
public class QueueDemo {

    public static void main(String[] args) {

        CircleQueue queue = new CircleQueue(3);

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("a: add data");
            System.out.println("p: pop data");
            System.out.println("e: exists");
            System.out.println("s: show current arr");
            String next = scanner.next();
            switch (next){
                case "a":
                    System.out.println("输入数据:");
                    int i = scanner.nextInt();
                    queue.add(i);
                    break;
                case "p":
                    System.out.println(queue.pop());
                    break;
                case "e":
                    flag = false;
                    scanner.close();
                    break;
                case "s":
                    queue.showArr();
                    break;
            }
        }
    }
}

class Queue{
    protected int maxSize;

    private final int defaultSize = 8;

    protected int[] arr;

    protected int rear = -1;

    protected int front = -1;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public void add(int value){
        if(isFull()){
            System.out.println("已满");
            return;
        }
        arr[++rear] = value;
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("空的");
            return -1;
        }
        return arr[++front];
    }

    public void showArr(){
        System.out.println(Arrays.toString(arr));
    }
}

class CircleQueue extends Queue{

    public CircleQueue(int maxSize) {
        super(maxSize);
    }

    public boolean isFull(){
        if(front == -1) {
            return rear == maxSize - 1;
        }

        return rear - front >  maxSize -1;
    }

    public void add(int value){
        if(isFull()){
            System.out.println("已满");
            return;
        }
        arr[(++rear)% maxSize ] = value;
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("空的");
            return -1;
        }
        return arr[(++front)% maxSize ];
    }
}

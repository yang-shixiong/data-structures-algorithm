package com.yang.tree.huffmantree;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/27
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        ArrayList<Node> list = new ArrayList<>();
        for (int i : arr) {
            Node node = new Node(i);
            list.add(node);
        }
        while (list.size() > 1){
            sort(list);
            Node second = list.remove(1);
            Node first = list.remove(0);
            Node node = new Node(first.id + second.id);
            node.left = first;
            node.right = second;
            list.add(node);
        }
        Node node = list.get(0);
//        System.out.println(node); // 67
        node.preOrder();
    }

    public static void sort(ArrayList<Node> list) {
        int size = list.size();
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapSort(list, i, size);
        }

        for (int j = size - 1; j > 0; j--) {
            Node end = list.get(j);
            Node head = list.get(0);
            list.set(0, end);
            list.set(j, head);
            heapSort(list, 0, j);
        }
    }

    public static void heapSort(ArrayList<Node> list, int n, int length) {
        Node temp = list.get(n);
        for (int k = n * 2 + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && list.get(k + 1).id > list.get(k).id) {
                k++;
            }
            if (temp.id < list.get(k).id) {
                list.set(n, list.get(k));
                n = k;
            } else {
                break;
            }
        }
        list.set(n, temp);
    }
}

class Node {
    int id;
    Node left;
    Node right;

    public Node(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}

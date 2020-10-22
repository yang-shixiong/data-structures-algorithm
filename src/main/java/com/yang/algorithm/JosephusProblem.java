package com.yang.algorithm;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/21
 */
public class JosephusProblem {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList(8);
        linkedList.start(2, 3);
        linkedList.show();
    }
}


class LinkedList {

    private Child head;

    public LinkedList(int num) {
        head = new Child(1);
        Child tem = head;
        for (int i = 1; i < num; i++) {
            Child child = new Child(i + 1);
            tem.next = child;
            tem = child;
        }
        tem.next = head;
    }

    public void start(int start, int count) {
        if (head == null || start < 1) {
            return;
        }
        Child helper = head;
        while (true) {
            if (helper.next == head) {
                break;
            }
            helper = helper.next;
        }

        for (int i = 1; i < start; i ++){
            helper = helper.next;
            head = head.next;
        }
        System.out.println("start: " + head.id);
        while (true){
            if(helper == head){
                System.out.println("the last student is: " + helper.id);
                break;
            }
            for (int i = 1; i < count; i ++){
                helper = helper.next;
                head = head.next;
            }
            System.out.println("remove student + " + head.id);
            head = head.next;
            helper.next = head;
        }
    }

    public void show() {
        Child tem = head;
        while (tem.next != head) {
            System.out.println("child: " + tem.id);
            tem = tem.next;
        }
    }
}

class Child {
    int id;

    Child next;

    public Child(int id) {
        this.id = id;
    }
}

package com.yang.hash;

import java.util.Scanner;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/23
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(10);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("a: add");
            System.out.println("d: delete");
            System.out.println("f: find");
            System.out.println("s: show");
            System.out.println("e: exit");
            String next = scanner.next();
            switch (next) {
                case "a":
                    System.out.println("输入id:");
                    int id = scanner.nextInt();
                    System.out.println("输入name:");
                    String name = scanner.next();
                    hashTab.add(new Student(id, name));
                    break;
                case "d":
                    System.out.println("输入id:");
                    hashTab.delete(scanner.nextInt());
                    break;
                case "f":
                    System.out.println("输入id:");
                    hashTab.find(scanner.nextInt());
                    break;
                case "s":
                    hashTab.show();
                    break;
                case "e":
                    flag = false;
                    scanner.close();
            }

        }
    }
}

class HashTab {

    private StudentLinkedList[] studentLinkedListArr;

    private int size;

    public HashTab(int size) {
        this.size = size;
        studentLinkedListArr = new StudentLinkedList[size];
        for (int i = 0; i <studentLinkedListArr.length; i++) {
            studentLinkedListArr[i] = new StudentLinkedList();
        }
    }

    public void add(Student student) {
        int hash = hash(student.id);
        studentLinkedListArr[hash].add(student);
    }

    public void delete(int id) {
        studentLinkedListArr[hash(id)].delete(id);
    }

    public void find(int id) {
        studentLinkedListArr[hash(id)].find(id);
    }

    public void show() {
        for (StudentLinkedList studentLinkedList : studentLinkedListArr) {
            studentLinkedList.show();
            System.out.println("----------------------");
        }
    }

    private int hash(int id) {
        return id % size;
    }
}

class StudentLinkedList {

    private Student head;

    public void add(Student student) {
        if (head == null) {
            head = student;
        } else {
            Student temp = head;
            if(head.id > student.id){
                student.next = temp;
                head = student;
                return;
            }
            while (temp.next != null && temp.next.id < student.id) {
                temp = temp.next;
            }

            Student next = temp.next;
            temp.next = student;
            student.next = next;
        }
    }

    public void delete(int id) {
        if (head == null) {
            System.out.println("none");
        } else {
            Student temp = this.head;
            if (head.id == id) {
                head = temp.next;
                System.out.println("delete");
                return;
            }
            while (temp != null) {
                if (temp.next != null && temp.next.id == id) {
                    temp.next = temp.next.next;
                    System.out.println("delete");
                    return;
                }
                temp = temp.next;
            }
        }
        System.out.println("could not find");
    }

    public void find(int id) {
        Student temp = head;
        while (temp != null) {
            if (temp.id == id) {
                System.out.println(temp);
                return;
            }
        }
        System.out.println("could not find");
    }

    public void show() {
        Student temp = this.head;
        while (temp != null) {
            System.out.print(temp);
            temp = temp.next;
        }
    }
}

class Student {

    int id;

    String name;

    Student next;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}' + "==>";
    }
}

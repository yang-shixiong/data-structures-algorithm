package com.yang.linked;

import java.util.Scanner;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/21
 */
public class LinkedArrayDemo {
    public static void main(String[] args) {
        LinkedArr linkedArr = new LinkedArr();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("i: init");
            System.out.println("a: add student");
            System.out.println("d: delete student");
            System.out.println("f: find student");
            System.out.println("e: exists");
            System.out.println("s: show students");
            String next = scanner.next();
            switch (next) {
                case "i":
                    Student s = new Student(1, "s");
                    linkedArr.setHead(s);
                    break;
                case "a":
                    System.out.println("输入id:");
                    int i = scanner.nextInt();
                    System.out.println("输入name:");
                    String name = scanner.next();
                    linkedArr.add(i, name);
                    break;
                case "d":
                    System.out.println("输入id:");
                    int id = scanner.nextInt();
                    linkedArr.delete(id);
                    break;
                case "f":
                    System.out.println("输入id:");
                    linkedArr.find(scanner.nextInt());
                    break;
                case "e":
                    flag = false;
                    scanner.close();
                    break;
                case "s":
                    linkedArr.show();
                    break;
            }

        }
    }
}

class LinkedArr {
    private Student head;

    public void setHead(Student head) {
        this.head = head;
    }

    public void add(int id, String name) {
        if (head == null) {
            System.out.println("the header is empty");
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.id < id) {
            temp = temp.next;
        }
        if (temp.next == null && temp.id == id) {
            System.out.println("id exist");
            return;
        }
        Student next = temp.next;
        Student student = new Student(id, name);
        temp.next = student;
        student.next = next;

    }

    public void find(int id) {
        if (head == null) {
            System.out.println("the header is empty");
            return;
        }
        Student temp = head;
        while (temp.id != id) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        if (temp.id == id) {
            System.out.println(temp.toString());
        } else {
            System.out.println("could not find the student: " + id);
        }
    }

    public void delete(int id) {
        if (head == null) {
            System.out.println("the header is empty");
            return;
        }
        if (head.id == id) {
            head = null;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.id != id) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        } else {
            System.out.println("could not find the student: " + id);
        }
    }

    public void show() {
        if (head == null) {
            System.out.println("the header is empty");
            return;
        }
        Student temp = head;
        while (temp != null) {
            System.out.println(temp);
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
                '}';
    }
}

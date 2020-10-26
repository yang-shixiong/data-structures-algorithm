package com.yang.tree;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/23
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        Student student1 = new Student(1, "1");
        Student student2 = new Student(2, "2");
        Student student3 = new Student(3, "3");
        Student student4 = new Student(4, "4");
        Student student5 = new Student(5, "5");
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = student1;
        student1.left = student2;
        student1.right = student3;
        student3.right = student4;
        student3.left = student5;
        System.out.println("front");
        binaryTree.front();
//        System.out.println("\nmiddle");
//        binaryTree.middle();
//        System.out.println("\nend");
//        binaryTree.end();
//        binaryTree.preSearch(5);
//        binaryTree.middleSearch(5);
//        binaryTree.postSearch(5);
        binaryTree.delete(3);
        System.out.println();
        binaryTree.front();
    }
}

class BinaryTree {
    Student root;

    public void front() {
        if (root == null) {
            System.out.println("none");
            return;
        }
        root.front();
    }

    public void middle() {
        if (root == null) {
            System.out.println("none");
            return;
        }
        root.middle();
    }

    public void end() {
        if (root == null) {
            System.out.println("none");
            return;
        }
        root.end();
    }

    public void preSearch(int id) {
        if (root == null) {
            System.out.println("none");
            return;
        }
        root.preSearch(id, 0);
    }

    public void middleSearch(int id) {
        if (root == null) {
            System.out.println("none");
            return;
        }
        root.middleSearch(id, 0);
    }

    public void postSearch(int id) {
        if (root == null) {
            System.out.println("none");
            return;
        }
        root.postSearch(id, 0);
    }

    public void delete(int id) {
        if (root == null) {
            System.out.println("none");
            return;
        }
        if (root.id == id) {
            root = null;
        } else {
            Student root = this.root;
            deletes(id, root);
        }
    }

    public void deletes(int id, Student root){
        if(root ==null){
            return;
        }
        if(root.left != null && root.left.id == id){
            root.left = null;
        }
        if(root.right !=null && root.right.id == id){
            root.right = null;
        }
        deletes(id, root.left);
        deletes(id, root.right);

    }
}

class Student {
    int id;
    String name;
    Student left;
    Student right;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void front() {
        System.out.print(this + "==>");
        if (this.left != null) {
            this.left.front();
        }
        if (this.right != null) {
            this.right.front();
        }
    }

    public void middle() {
        if (this.left != null) {
            this.left.middle();
        }
        System.out.print(this + "==>");
        if (this.right != null) {
            this.right.middle();
        }
    }

    public void end() {
        if (this.left != null) {
            this.left.end();
        }
        if (this.right != null) {
            this.right.end();
        }
        System.out.print(this + "==>");
    }

    public void preSearch(int id, int count) {
        count++;
        if (this.id == id) {
            System.out.println("\npreSearch find it " + count);
        }
        if (this.left != null) {
            this.left.preSearch(id, count);
        }

        if (this.right != null) {
            this.right.preSearch(id, count);
        }
    }

    public void middleSearch(int id, int count) {

        if (this.left != null) {
            this.left.middleSearch(id, count);
        }
        count++;

        if (this.id == id) {
            System.out.println("middleSearch find it " + count);
        }

        if (this.right != null) {
            this.right.middleSearch(id, count);
        }
    }

    public void postSearch(int id, int count) {
        if (this.left != null) {
            this.left.postSearch(id, count);
        }
        if (this.right != null) {
            this.right.postSearch(id, count);
        }
        count++;

        if (this.id == id) {
            System.out.println("postSearch find it " + count);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

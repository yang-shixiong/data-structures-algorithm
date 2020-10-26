package com.yang.tree.threaded;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/26
 */
public class ThreadBinaryTree {

    public static void main(String[] args) {
        Node node1 = new Node(1, "1");
        Node node2 = new Node(2, "2");
        Node node3 = new Node(3, "3");
        Node node4 = new Node(4, "4");
        Node node5 = new Node(5, "5");
        Node node6 = new Node(6, "6");
        Node node7 = new Node(7, "7");
        ThreadedTree threadedTree = new ThreadedTree(node1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        threadedTree.postOrder();
        threadedTree.buildPostThreadedTree();
        System.out.println();
//        System.out.println(node1);
//        System.out.println(node2.left);
//        System.out.println(node2.right);
//        System.out.println(node3);
//        System.out.println(node4.left);
//        System.out.println(node4.right);
//        System.out.println(node5.left);
//        System.out.println(node5.right);
//        System.out.println(node6);
//        System.out.println(node7);
    }
}

class ThreadedTree {
    Node root;

    Node pre;

    public ThreadedTree(Node root) {
        this.root = root;
    }

    public void threadPreOrder() {
        if (root == null) {
            return;
        }
        Node temp = this.root;
        while (temp != null) {
            System.out.println(temp);
            while (temp.leftType == 0) {
                temp = temp.left;
                System.out.println(temp);
            }
            temp = temp.right;
        }
    }

    public void threadInfixOrder() {
        if (root == null) {
            return;
        }
        Node temp = this.root;
        while (temp != null) {
            while (temp.leftType == 0) {
                temp = temp.left;
            }
            System.out.println(temp);
            while (temp.rightType == 1){
                temp = temp.right;
                System.out.println(temp);
            }
            temp = temp.right;
        }
    }

    public void infixOrder(){
        root.infixOrder();
    }

    public void preOrder(){
        root.preOrder();
    }

    public void postOrder(){
        root.postOrder();
    }

    public void buildPreThreadedTree() {
        if (root == null) {
            return;
        }
        buildPreThreadedTree(root);
    }

    public void buildPreThreadedTree(Node node) {
        if (node == null) {
            return;
        }
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        if (node.leftType == 0)
            buildPreThreadedTree(node.left);
        if (node.rightType == 0)
            buildPreThreadedTree(node.right);
    }

    public void buildInfixThreadedTree() {
        if (root == null) {
            return;
        }
        buildInfixThreadedTree(root);
    }

    public void buildInfixThreadedTree(Node node) {
        if (node == null) {
            return;
        }
        buildInfixThreadedTree(node.left);
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        buildInfixThreadedTree(node.right);
    }

    public void buildPostThreadedTree() {
        if (root == null) {
            return;
        }
        buildPostThreadedTree(root);
    }

    public void buildPostThreadedTree(Node node) {
        if (node == null) {
            return;
        }
        if (node.leftType == 0)
            buildPostThreadedTree(node.left);
        if (node.rightType == 0)
            buildPostThreadedTree(node.right);
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
    }


}

class Node {
    int id;
    String name;
    int leftType;
    int rightType;
    Node left;
    Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
        this.leftType = 0;
        this.rightType = 0;
    }

    public void infixOrder(){
        if(this.left !=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right !=null){
            this.right.infixOrder();
        }
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left !=null){
            this.left.preOrder();
        }
        if(this.right !=null){
            this.right.preOrder();
        }
    }

    public void postOrder(){
        if(this.left !=null){
            this.left.postOrder();
        }
        if(this.right !=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }


    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }
}

package com.yang.tree.binary.order;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/27
 */
public class OrderBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        OrderBinaryTree orderBinaryTree = new OrderBinaryTree();
        for (int i : arr) {
            Node node = new Node(i);
            orderBinaryTree.add(node);
        }
        Node node = new Node(2);
        orderBinaryTree.add(node);
        orderBinaryTree.delete(7);
        orderBinaryTree.infixOrder();
    }
}

class OrderBinaryTree {
    Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            Node temp = root;
            while (true) {
                if (temp.id >= node.id) {
                    if (temp.left == null) {
                        temp.left = node;
                        break;
                    }
                    temp = temp.left;
                }
                if (temp.id < node.id) {
                    if (temp.right == null) {
                        temp.right = node;
                        break;
                    }
                    temp = temp.right;
                }

            }
        }
    }

    public void preOrder() {
        if (root == null) {
            return;
        }
        root.preOrder();
    }

    public void infixOrder() {
        if (root == null) {
            return;
        }
        root.infixOrder();
    }

    public void delete(int id) {
        if (root == null) {
            System.out.println("root is null");
            return;
        }
        Node current = this.root;
        Node parent = null;
        while (current != null) {
            if (current.id == id) {
                break;
            }
            parent = current;
            if (current.id > id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) {
            System.out.println("not find");
            return;
        }
        if (current.left == null && current.right == null) {
            if (parent == null) {
                root = null;
                return;
            }
            if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.left != null && current.right != null) {
            Node temp = current.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            delete(temp.id);
            current.id = temp.id;
        } else {
            if (parent == null) {
                root = null;
                return;
            }
            if (parent.left == current) {
                parent.left = current.left == null ? current.right : current.left;
            } else {
                parent.right = current.left == null ? current.right : current.left;
            }
        }
    }
}

class Node {
    int id;
    Node left;
    Node right;

    public Node(int id) {
        this.id = id;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();

        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }
}

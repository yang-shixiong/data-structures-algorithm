package com.yang.tree.binary.avl;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/28
 */
public class AvlTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 8, 9};
        AlvTree alvTree = new AlvTree();
        for (int i : arr) {
            AvlNode node = new AvlNode(i);
            alvTree.add(node);
        }
        alvTree.preOrder();
        for(int i =0; i < 80000; i++){
            AvlNode node = new AvlNode(i);
            alvTree.add(node);
        }
    }
}

class AlvTree {
    AvlNode root;

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

    public void postOrder() {
        if (root == null) {
            return;
        }
        root.postOrder();
    }

    public void add(AvlNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void delete(int id) {
        AvlNode currentNode = searchNode(id);
        if (currentNode == null) {
            System.out.println("could not find");
            return;
        }
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        AvlNode parentNode = searchParentNode(id);
        if (currentNode.left == null && currentNode.right == null) {
            if (parentNode != null) {
                if (parentNode.left == currentNode) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            }
        } else if (currentNode.right != null && currentNode.left != null) {
            AvlNode maxLeftTreeNode = findMaxLeftTreeNode(currentNode.left);
            delete(maxLeftTreeNode.id);
            currentNode.id = maxLeftTreeNode.id;
        } else {
            if (parentNode != null) {
                if (parentNode.left == currentNode) {
                    parentNode.left = currentNode.left != null ? currentNode.left : currentNode.right;
                } else {
                    parentNode.right = currentNode.left != null ? currentNode.left : currentNode.right;
                }
            } else {
                root = currentNode.left != null ? currentNode.left : currentNode.right;
            }
        }

    }

    public AvlNode findMaxLeftTreeNode(AvlNode node) {
        AvlNode temp = node;
        while (true) {
            if (temp.right != null) {
                temp = temp.right;
            } else {
                break;
            }
        }
        return temp;
    }

    public AvlNode searchNode(int id) {
        if (root == null) {
            return null;
        }
        return root.searchNode(id);
    }

    public AvlNode searchParentNode(int id) {
        if (root == null) {
            return null;
        }
        return root.searchParentNode(id);
    }
}

class AvlNode {
    int id;

    AvlNode left;

    AvlNode right;

    public AvlNode(int id) {
        this.id = id;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public void add(AvlNode node) {
        if (node == null) return;
        if (this.id > node.id) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        if(this.rightHeight() - this.leftHeight() > 1){
            if(this.right.leftHeight() > this.right.rightHeight()){
                this.right.rightRotate();
            }
            this.leftRotate();
            return;
        }
        if(this.leftHeight() - this.rightHeight() > 1){
            if(this.left.rightHeight() > this.left.leftHeight()){
                this.left.leftRotate();
            }
            this.rightRotate();
        }
    }

    public void leftRotate() {
        AvlNode newNode = new AvlNode(this.id);
        newNode.left = this.left;
        newNode.right = this.right.left;
        this.id = this.right.id;
        this.right = this.right.right;
        this.left = newNode;
    }

    public void rightRotate() {
        AvlNode newNode = new AvlNode(this.id);
        newNode.left = this.left.right;
        newNode.right = this.right;
        this.id = this.left.id;
        this.left = this.left.left;
        this.right = newNode;
    }

    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height() + 1, this.right == null ? 0 : this.right.height() + 1);
    }

    public AvlNode searchNode(int id) {
        if (this.id == id) {
            return this;
        } else {
            if (this.id > id) {
                return this.left != null ? this.left.searchNode(id) : null;
            } else {
                return this.right != null ? this.right.searchNode(id) : null;
            }
        }
    }

    public AvlNode searchParentNode(int id) {
        if ((this.left != null && this.left.id == id) || (this.right != null && this.right.id == id)) {
            return this;
        } else {
            if (this.id > id && this.left != null) {
                return this.left.searchParentNode(id);
            } else if (this.id <= id && this.right != null) {
                return this.right.searchParentNode(id);
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "AvlNode{" +
                "id=" + id +
                '}';
    }
}

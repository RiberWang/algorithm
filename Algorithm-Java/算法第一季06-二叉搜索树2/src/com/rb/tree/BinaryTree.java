package com.rb.tree;

import com.rb.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected BST.Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
            else { // node.right == null
                leaf = true;
            }
        }

        return true;
    }

    public boolean isComplete1() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null && node.right != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
            else if (node.left == null && node.right != null) {
                return false;
            }
            else { // 后面遍历的节点都必须是叶子节点
                leaf = true;
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }

        return true;
    }

    public int height() {
        if (root == null) return 0;
        // 树的高度
        int height = 0;
        // 存储每一层的元素数量
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            // 意味着即将要访问下一层
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }

        return height;
    }

    public int height2() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 前序遍历
     */
    public void preorderTraversalWithVisitor(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversalWithVisitor(root, visitor);
    }

    private void preorderTraversalWithVisitor(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        visitor.stop = visitor.visit(node.element);
        preorderTraversalWithVisitor(node.left, visitor);
        preorderTraversalWithVisitor(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversalWithVisitor(Visitor<E> visitor) {
        if (visitor == null) return;

        inorderTraversalWithVisitor(root, visitor);
    }

    private void inorderTraversalWithVisitor(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        inorderTraversalWithVisitor(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderTraversalWithVisitor(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postorderTraversalWithVisitor(Visitor<E> visitor) {
        if (visitor == null) return;

        postorderTraversalWithVisitor(root, visitor);
    }

    private void postorderTraversalWithVisitor(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        postorderTraversalWithVisitor(node.left, visitor);
        postorderTraversalWithVisitor(node.right, visitor); // visitor.stop = true
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public static abstract class Visitor<E> {
        boolean stop;
        /**
         *
         * @param element
         * @return 返回true停止遍历
         */
        abstract boolean visit(E element);
    }

    protected Node<E> predesessor(Node<E> node) {
        if (node == null) return null;

        // 前去节点在左子树当中(left.right.right.right...)
        Node<E> tempNode = node.left;
        if (tempNode != null) {
            while (tempNode.right != null) {
                tempNode = tempNode.right;
            }
            return tempNode;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (tempNode.parent != null && node == node.parent.left) {
            tempNode = tempNode.parent;
        }

        return tempNode.parent;
    }

    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;

        // 前去节点在左子树当中(right.left.left...)
        Node<E> tempNode = node.right;
        if (tempNode != null) {
            while (tempNode.left != null) {
                tempNode = tempNode.left;
            }
            return tempNode;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (tempNode.parent != null && node == node.parent.right) {
            tempNode = tempNode.parent;
        }

        return tempNode.parent;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;

        String parentStr = "";
        if (myNode.parent != null) {
            parentStr = myNode.parent.element.toString();

            return ((Node<E>)node).element + "_" + "p(" + parentStr + ")";
        }
        else  {
            return ((Node<E>)node).element;
        }
    }

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}

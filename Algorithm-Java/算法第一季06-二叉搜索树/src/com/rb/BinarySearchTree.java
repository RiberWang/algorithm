package com.rb;
import com.rb.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.lang.Comparable;
import java.util.LinkedList;
import java.util.Queue;

// 二叉搜索树
public class BinarySearchTree<E> implements BinaryTreeInfo { // extends Comparable
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            }
            else if (cmp < 0) {
                node = node.left;
            }
            else { // 等于
                node.element = element; // 覆盖
                return;
            }
        }

        // 看看插入到父节点的哪个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        }
        else {
            parent.left = newNode;
        }
        size++;
    }

    /**
     * 返回值等于0代表e1和e2相等 >0e1>e2 <0e1<e2
     * @param e1
     * @param e2
     * @return
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2); // e1.compareTo(e2)
        }
        return ((Comparable<E>)e1).compareTo(e2); // 强制转换
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<E> node) {
        if (node == null) return;
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

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
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    private void inorderTraversal2(Node<E> node) {
        if (node == null) return;
        inorderTraversal2(node.right);
        System.out.println(node.element);
        inorderTraversal2(node.left);
    }

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
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node<E> node) {
        if (node == null) return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

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
    public void levelOrderTraversal() {
        if (root == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not null");
        }
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

    /**
     * 接口改为抽象类  interface -> abstract
     * 抽象类可以存储成员变量
     * @param <E>
     */
    public static abstract class Visitor<E> {
        boolean stop;
        /**
         *
         * @param element
         * @return 返回true停止遍历
         */
        abstract boolean visit(E element);
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}

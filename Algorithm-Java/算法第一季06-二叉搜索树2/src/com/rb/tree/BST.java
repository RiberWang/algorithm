package com.rb.tree;

import java.util.Comparator;
import java.lang.Comparable;

// 二叉搜索树
public class BST<E> extends BinaryTree { // extends Comparable
    private Comparator<E> comparator;

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BST() {
        this(null);
    }

    public boolean contains(E element) {
        return node(element) != null;
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

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else { // cmp > 0
                node = node.left;
            }
        }

        return null;
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;
        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            Node<E> s = successor(node); // 找到后己节点
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然为1或0）
        Node<E> replacementNode = node.left != null ? node.left : node.right;
        if (replacementNode != null) { // node是度为1的节点
            // 更改parent
            replacementNode.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacementNode;
//                replacementNode.parent = null;
            }
            else if (node == node.parent.left) {
                node.parent.left = replacementNode;
            } else { // node == node.parent.right
                node.parent.right = replacementNode;
            }
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else { // node是叶子节点但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not null");
        }
    }
}

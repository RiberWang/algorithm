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
        root = null;
        size = 0;
    }

    public boolean contains(E element) {
        return node(element) != null;
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

    private Node<E> predesessor(Node<E> node) {
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

    private Node<E> successor(Node<E> node) {
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;
        toString(node.left, sb, prefix + "[L]");
        sb.append(prefix).append(node.element).append("\n");
        toString(node.right, sb,prefix + "[R]");
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

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}

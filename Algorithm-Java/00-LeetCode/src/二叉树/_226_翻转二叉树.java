package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/invert-binary-tree/
public class _226_翻转二叉树 {
    // 前序
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    // 后序
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;

        invertTree2(root.left);
        invertTree2(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    // 中序
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return root;

        invertTree3(root.left);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree3(root.left);

        return root;
    }

    // 层序
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return root;
    }
}

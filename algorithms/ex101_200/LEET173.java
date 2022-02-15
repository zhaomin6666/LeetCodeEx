package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 173. 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * <p>
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * 输出
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 * <p>
 * 解释
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // 返回 3
 * bSTIterator.next();    // 返回 7
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 9
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 15
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 20
 * bSTIterator.hasNext(); // 返回 False
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 105] 内
 * 0 <= Node.val <= 106
 * 最多调用 105 次 hasNext 和 next 操作
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h 是树的高度。
 *
 * @author zm
 */
public class LEET173 {
    public static void main(String[] args) {
        LEET173 l169 = new LEET173();
        BSTIterator bSTIterator = l169.new BSTIterator(CommonFunctions.stringToTreeNode("[7, 3, 15, null, null, 9, 20]"));
        System.out.println(bSTIterator.next());
        // 返回 3
        System.out.println(bSTIterator.next());
        // 返回 7
        System.out.println(bSTIterator.hasNext());
        // 返回 True
        System.out.println(bSTIterator.next());
        // 返回 9
        System.out.println(bSTIterator.hasNext());
        // 返回 True
        System.out.println(bSTIterator.next());
        // 返回 15
        System.out.println(bSTIterator.hasNext());
        // 返回 True
        System.out.println(bSTIterator.next());
        // 返回 20
        System.out.println(bSTIterator.hasNext());
        // 返回 False

    }

    /**
     * 用栈保存有左子树的结点
     */
    class BSTIterator {
        TreeNode next;
        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            TreeNode cur = root;
            stack = new Stack<>();
            while (cur.left != null) {
                stack.push(cur);
                cur = cur.left;
            }
            next = cur;
        }

        public int next() {
            if (next == null) {
                return -1;
            }
            int retInt = next.val;
            if (next.right != null) {
                TreeNode cur = next.right;
                while (cur.left != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                next = cur;
            } else {
                next = stack.isEmpty() ? null : stack.pop();
            }
            return retInt;
        }

        public boolean hasNext() {
            return next != null;
        }
    }

    /**
     * 官方题解
     */
    class BSTIterator2 {
        private TreeNode cur;
        private Deque<TreeNode> stack;

        public BSTIterator2(TreeNode root) {
            cur = root;
            stack = new LinkedList<>();
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int ret = cur.val;
            cur = cur.right;
            return ret;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }
    }
}

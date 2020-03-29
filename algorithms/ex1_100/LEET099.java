package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 99. 恢复二叉搜索树
 * <p>
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,null,null,2]
 * <p>
 * ·   1
 * ·  /
 * · 3
 * ·  \
 * ·   2
 * <p>
 * 输出: [3,1,null,null,2]
 * <p>
 * ·   3
 * ·  /
 * · 1
 * ·  \
 * ·   2
 * 示例 2:
 * <p>
 * 输入: [3,1,4,null,null,2]
 * <p>
 * ·  3
 * · / \
 * ·1   4
 * ·   /
 * ·  2
 * <p>
 * 输出: [2,1,4,null,null,3]
 * <p>
 * ·  2
 * · / \
 * ·1   4
 * ·   /
 * ·  3
 * 进阶:
 * <p>
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 *
 * @author zm
 */
public class LEET099 {
    public static void main(String[] args) {
        LEET099 l099 = new LEET099();
        TreeNode t1 = CommonFunctions.stringToTreeNode("[1,3,null,null,2]");
        l099.new Solution2().recoverTree(t1);
        System.out.println(CommonFunctions.treeNodeToString(t1));
        TreeNode t2 = CommonFunctions.stringToTreeNode("[3,1,4,null,null,2]");
        l099.new Solution2().recoverTree(t2);
        System.out.println(CommonFunctions.treeNodeToString(t2));
    }

    /**
     * 把现有树转化成数组，然后在数组中寻找非有序的两个数，再到树中去交换这两个结点
     */
    class Solution {
        public void recoverTree(TreeNode root) {
            List<Integer> nums = new ArrayList();
            inorder(root, nums);
            int[] swapped = findTwoSwapped(nums);
            recover(root, 2, swapped[0], swapped[1]);
        }

        public void inorder(TreeNode<Integer> root, List<Integer> nums) {
            if (root == null) {
                return;
            }
            inorder(root.left, nums);
            nums.add(root.val);
            inorder(root.right, nums);
        }

        public int[] findTwoSwapped(List<Integer> nums) {
            int n = nums.size();
            int x = -1, y = -1;
            for (int i = 0; i < n - 1; ++i) {
                if (nums.get(i + 1) < nums.get(i)) {
                    y = nums.get(i + 1);
                    // first swap occurence
                    if (x == -1) {
                        x = nums.get(i);
                        // second swap occurence
                    } else {
                        break;
                    }
                }
            }
            return new int[]{x, y};
        }

        public void recover(TreeNode<Integer> r, int count, int x, int y) {
            if (r != null) {
                if (r.val == x || r.val == y) {
                    r.val = r.val == x ? y : x;
                    if (--count == 0) {
                        return;
                    }
                }
                recover(r.left, count, x, y);
                recover(r.right, count, x, y);
            }
        }
    }

    /**
     * 官方题解
     * Morris 中序遍历
     * <p>
     * 网上找到的资料 https://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
     */
    class Solution2 {
        public void swap(TreeNode<Integer> a, TreeNode<Integer> b) {
            int tmp = a.val;
            a.val = b.val;
            b.val = tmp;
        }

        public void recoverTree(TreeNode<Integer> root) {
            // predecessor is a Morris predecessor.
            // In the 'loop' cases it could be equal to the node itself predecessor == root.
            // pred is a 'true' predecessor,
            // the previous node in the inorder traversal.
            TreeNode<Integer> x = null, y = null, pred = null, predecessor = null;

            while (root != null) {
                // If there is a left child
                // then compute the predecessor.
                // If there is no link predecessor.right = root --> set it.
                // If there is a link predecessor.right = root --> break it.
                if (root.left != null) {
                    // Predecessor node is one step left
                    // and then right till you can.
                    predecessor = root.left;
                    while (predecessor.right != null && predecessor.right != root) {
                        predecessor = predecessor.right;
                    }

                    // set link predecessor.right = root
                    // and go to explore left subtree
                    if (predecessor.right == null) {
                        predecessor.right = root;
                        root = root.left;
                    }
                    // break link predecessor.right = root
                    // link is broken : time to change subtree and go right
                    else {
                        // check for the swapped nodes
                        if (pred != null && root.val < pred.val) {
                            y = root;
                            if (x == null) {
                                x = pred;
                            }
                        }
                        pred = root;

                        predecessor.right = null;
                        root = root.right;
                    }
                }
                // If there is no left child
                // then just go right.
                else {
                    // check for the swapped nodes
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = root;

                    root = root.right;
                }
            }
            swap(x, y);
        }
    }
}

package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 114. 二叉树展开为链表
 * <p>
 * 给定一个二叉树，原地将它展开为链表。
 * <p>
 * 例如，给定二叉树
 * <p>
 * ....1
 * .../.\
 * ..2...5
 * ./.\...\
 * 3...4...6
 * 将其展开为：
 * <p>
 * 1
 * .\
 * ..2
 * ...\
 * ....3
 * .....\
 * ......4
 * .......\
 * ........5
 * .........\
 * ..........6
 *
 * @author zm
 */
public class LEET114 {
    public static void main(String[] args) {
        LEET114 l114 = new LEET114();
        TreeNode root = CommonFunctions.stringToTreeNode("[1,2,5,3,4,null,6]");
        l114.new Solution().flatten(root);
        System.out.println(CommonFunctions.treeNodeToString(root));
    }

    class Solution {
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            helper(root);
        }

        private TreeNode helper(TreeNode node) {
            if (node.left == null) {
                if (node.right == null) {
                    return node;
                }
                return helper(node.right);
            }
            if (node.right == null) {
                node.right = node.left;
                node.left = null;
                return helper(node.right);
            }
            TreeNode ret = helper(node.right);
            TreeNode temp = helper(node.left);
            temp.right = node.right;
            node.right = node.left;
            node.left = null;
            return ret;
        }
    }

    /**
     * 把上面方法中的获取左树结点的最后一个节点直接用while循环获取
     * （因为先进行了左节点的flatten递归调用。所以左节点肯定是已经完成了展开的）
     */
    class Solution2 {
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            flatten(root.left);
            flatten(root.right);
            if (root.left == null) {
                TreeNode leftLast = root.left;
                while (leftLast.right != null) {
                    leftLast = leftLast.right;
                }
                leftLast.right = root.right;
                root.right = root.left;
                root.left = null;
            }
        }
    }
}

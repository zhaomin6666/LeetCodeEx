package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.Stack;

/**
 * 129. 求根到叶子节点数字之和
 * <p>
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径1->2->3代表数字123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例1:
 * <p>
 * 输入:[1,2,3]
 * ....1
 * .../.\
 * ..2...3
 * 输出:.25
 * 解释:
 * 从根到叶子节点路径1->2代表数字12
 * 从根到叶子节点路径1->3代表数字13
 * 因此，数字总和=12+13=25.
 * 示例2:
 * <p>
 * 输入:[4,9,0,5,1]
 * ....4
 * .../.\
 * ..9...0
 *  /.\
 * 5...1
 * 输出:1026
 * 解释:
 * 从根到叶子节点路径4->9->5代表数字495
 * 从根到叶子节点路径4->9->1代表数字491
 * 从根到叶子节点路径4->0代表数字40
 * 因此，数字总和=495+491+40=1026
 *
 * @author zm
 */
public class LEET129 {
    public static void main(String[] args) {
        LEET129 l129 = new LEET129();
        System.out.println(l129.new Solution().sumNumbers(CommonFunctions.stringToTreeNode("[1,2,3]")));
        System.out.println(l129.new Solution().sumNumbers(CommonFunctions.stringToTreeNode("[4,9,0,5,1]")));
    }

    class Solution {
        public int sumNumbers(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int sum = 0;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                if (cur.left == null && cur.right == null) {
                    sum += cur.val;
                } else {
                    if (cur.left != null) {
                        cur.left.val += cur.val * 10;
                        stack.push(cur.left);
                    }
                    if (cur.right != null) {
                        cur.right.val += cur.val * 10;
                        stack.push(cur.right);
                    }
                }
            }
            return sum;
        }
    }

    /**
     * 递归
     */
    class Solution2 {
        private int sum = 0;

        public int sumNumbers(TreeNode root) {
            if (root == null) {
                return 0;
            }
            sumNumbers(root, 0);
            return sum;
        }

        private void sumNumbers(TreeNode cur, int curSum) {
            curSum = cur.val + curSum * 10;
            if (cur.right == null && cur.left == null) {
                sum += curSum;
                return;
            }
            if (cur.right != null) {
                sumNumbers(cur.right, curSum);
            }
            if (cur.left != null) {
                sumNumbers(cur.left, curSum);
            }
        }
    }
}

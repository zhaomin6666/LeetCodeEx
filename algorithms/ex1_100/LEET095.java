package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * <p>
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1        3     3      2      1
 * \       /     /      / \      \
 * 3      2     1      1  3      2
 * /     /      \                 \
 * 2    1       2                 3
 *
 * @author zm
 */
public class LEET095 {
    public static void main(String[] args) {
        LEET095 l095 = new LEET095();
        l095.new Solution().generateTrees(3).forEach(
                treeNode -> System.out.println(CommonFunctions.treeNodeToString(treeNode)));
    }

    class Solution {
        public List<TreeNode<Integer>> generateTrees(int n) {
            if (n == 0) {
                return new LinkedList<>();
            }
            return helper(1, n);
        }

        private List<TreeNode<Integer>> helper(int l, int r) {
            List<TreeNode<Integer>> list = new LinkedList<>();
            if (l > r) {
                list.add(null);
                return list;
            }
            for (int i = l; i <= r; i++) {
                List<TreeNode<Integer>> leftTrees = helper(l, i - 1);
                List<TreeNode<Integer>> rightTrees = helper(i + 1, r);
                // 将左右树的所有可能进行组合
                for (TreeNode<Integer> left : leftTrees) {
                    for (TreeNode<Integer> right : rightTrees) {
                        TreeNode<Integer> cur = new TreeNode<>(i);
                        cur.left = left;
                        cur.right = right;
                        list.add(cur);
                    }
                }
            }
            return list;
        }
    }
}

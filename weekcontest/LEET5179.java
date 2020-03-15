package com.zm.LeetCodeEx.weekcontest;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 周赛 2020年3月15日 5179. 将二叉搜索树变平衡
 * <p>
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 * <p>
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 * <p>
 * 如果有多种构造方法，请你返回任意一种。
 * <p>
 * 示例：
 * 输入：root = [1,null,2,null,3,null,4,null,null]
 * 输出：[2,1,3,null,null,null,4]
 * 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
 *  
 * <p>
 * 提示：
 * <p>
 * 树节点的数目在 1 到 10^4 之间。
 * 树节点的值互不相同，且在 1 到 10^5 之间。
 *
 * @author zm
 */
public class LEET5179 {
    public static void main(String[] args) {
        LEET5179 l5179 = new LEET5179();
        System.out.println(CommonFunctions.treeNodeToString(l5179.new Solution().balanceBST(
                CommonFunctions.stringToTreeNode("[1,null,2,null,3,null,4,null,null]"))));
    }

    /**
     * 直接把所有数取出来之后重新生成一棵平衡二叉树
     */
    class Solution {
        private List<Integer> nums;

        public TreeNode balanceBST(TreeNode<Integer> root) {
            nums = new ArrayList<>();
            getNum(root);
            Collections.sort(nums);
            return dfs(0, nums.size() - 1);
        }

        private void getNum(TreeNode<Integer> node) {
            if (node == null) {
                return;
            }
            nums.add(node.val);
            getNum(node.left);
            getNum(node.right);
        }

        private TreeNode<Integer> dfs(int l, int r) {
            if (l > r) {
                return null;
            }
            int m = (l + r) >>> 1;
            TreeNode<Integer> res = new TreeNode<>(nums.get(m));
            res.left = dfs(l, m - 1);
            res.right = dfs(m + 1, r);
            return res;
        }
    }
}

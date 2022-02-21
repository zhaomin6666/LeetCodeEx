package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. 路径总和 II
 * <p>
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * ..............5
 * ............./.\
 * ............4...8
 * .........../.../.\
 * ..........11..13..4
 * ........./..\..../.\
 * ........7....2..5...1
 * 返回:
 * <p>
 * [
 * ...[5,4,11,2],
 * ...[5,8,4,5]
 * ]
 *
 * @author zm
 */
public class LEET113 {
    public static void main(String[] args) {
        LEET113 l113 = new LEET113();
        System.out.println(
                l113.new Solution().pathSum(CommonFunctions.stringToTreeNode(
                        "[5,4,8,11,null,13,4,7,2,null,null,null,1]"), 25));
        System.out.println(
                l113.new Solution().pathSum(CommonFunctions.stringToTreeNode(
                        "[5,4,8,11,null,13,4,7,2,null,null,null,1]"), 26));
        System.out.println(
                l113.new Solution().pathSum(CommonFunctions.stringToTreeNode(
                        "[5,4,8,11,null,13,4,7,2,null,null,null,1]"), 27));
        System.out.println(
                l113.new Solution().pathSum(CommonFunctions.stringToTreeNode(
                        "[]"), 0));
        System.out.println(
                l113.new Solution().pathSum(CommonFunctions.stringToTreeNode(
                        "[0]"), 0));
        System.out.println(
                l113.new Solution().pathSum(CommonFunctions.stringToTreeNode(
                        "[1,2]"), 1));
        System.out.println(
                l113.new Solution().pathSum(CommonFunctions.stringToTreeNode(
                        "[1,2]"), 3));
        System.out.println(
                l113.new Solution().pathSum(CommonFunctions.stringToTreeNode(
                        "[1,null,2]"), 3));
        System.out.println(
                l113.new Solution().pathSum(CommonFunctions.stringToTreeNode(
                        "[5,4,8,11,null,13,4,7,2,null,null,5,1]"), 22));


    }

    class Solution {
        private List<List<Integer>> retList;

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            retList = new LinkedList<>();
            helper(root, sum, new ArrayList<>());
            return retList;
        }

        private void helper(TreeNode node, int sum, List<Integer> preList) {
            if (node == null) {
                return;
            }
            // 复制原list
            List<Integer> curList = new ArrayList<>(preList);
            curList.add(node.val);
            // root节点的左右孩子都为null说明该节点是叶子节点
            if (node.left == null && node.right == null && node.val == sum) {
                retList.add(curList);
                return;
            }
            helper(node.left, sum - node.val, curList);
            helper(node.right, sum - node.val, curList);
        }
    }

    class Solution2 {
        private List<List<Integer>> retList;

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            retList = new LinkedList<>();
            helper(root, sum, new LinkedList<>());
            return retList;
        }

        private void helper(TreeNode node, int sum, LinkedList<Integer> preList) {
            if (node == null) {
                return;
            }
            preList.add(node.val);
            // root节点的左右孩子都为null说明该节点是叶子节点
            if (node.left == null && node.right == null && node.val == sum) {
                retList.add(new LinkedList<>(preList));
                return;
            }
            helper(node.left, sum - node.val, preList);
            helper(node.right, sum - node.val, preList);
            preList.removeLast();
        }
    }
}

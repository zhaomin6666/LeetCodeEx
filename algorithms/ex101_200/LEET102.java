package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * <p>
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 *  
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * .....3
 * ..../.\
 * ...9..20
 * ...../..\
 * ....15...7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *
 * @author zm
 */
public class LEET102 {
    public static void main(String[] args) {
        LEET102 l102 = new LEET102();
        System.out.println(JSON.toJSONString(l102.new Solution().levelOrder(
                CommonFunctions.stringToTreeNode("[]"))));
        System.out.println(JSON.toJSONString(l102.new Solution().levelOrder(
                CommonFunctions.stringToTreeNode("[1,2]"))));
        System.out.println(JSON.toJSONString(l102.new Solution().levelOrder(
                CommonFunctions.stringToTreeNode("[1,2,2,null,3,null,3]"))));
        System.out.println(JSON.toJSONString(l102.new Solution().levelOrder(
                CommonFunctions.stringToTreeNode("[3,9,20,null,null,15,7]"))));

    }

    /**
     * 用null来分割每一层
     */
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<>();
            if (root == null) {
                return ret;
            }
            List<Integer> temp = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null && queue.isEmpty()) {
                    ret.add(temp);
                    break;
                }
                if (node == null) {
                    ret.add(temp);
                    temp = new ArrayList<>();
                    queue.add(null);
                } else {
                    temp.add((Integer) node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return ret;
        }
    }


    /**
     * 每次循环当前层数的所有节点
     */
    class Solution2 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<>();
            if (root == null) {
                return ret;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            List<Integer> temp = new ArrayList<>();
            while (!queue.isEmpty()) {
                int levelCnt = queue.size();
                for (int i = 0; i < levelCnt; ++i) {
                    TreeNode node = queue.remove();
                    temp.add((Integer) node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                ret.add(temp);
            }
            return ret;
        }
    }

}

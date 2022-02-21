package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层次遍历
 * <p>
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
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
 * [20,9],
 * [15,7]
 * ]
 *
 * @author zm
 */
public class LEET103 {
    public static void main(String[] args) {
        LEET103 l103 = new LEET103();
        System.out.println(JSON.toJSONString(l103.new Solution().zigzagLevelOrder(
                CommonFunctions.stringToTreeNode("[]"))));
        System.out.println(JSON.toJSONString(l103.new Solution().zigzagLevelOrder(
                CommonFunctions.stringToTreeNode("[1,2]"))));
        System.out.println(JSON.toJSONString(l103.new Solution().zigzagLevelOrder(
                CommonFunctions.stringToTreeNode("[1,2,3,4,5,6,7,8,9,10,11,12]"))));
        System.out.println(JSON.toJSONString(l103.new Solution().zigzagLevelOrder(
                CommonFunctions.stringToTreeNode("[3,9,20,null,null,15,7]"))));

    }

    /**
     * 正序：
     * pollFirst并且先addLast左结点再addLast右结点
     * 逆序：
     * pollLast并且先addFirst右结点再addFirst左结点
     * 交替执行
     */
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<>();
            if (root == null) {
                return ret;
            }
            LinkedList<TreeNode> linkedList = new LinkedList<>();
            linkedList.add(root);
            boolean di = true;
            while (!linkedList.isEmpty()) {
                List<Integer> temp = new ArrayList<>();
                int levelCnt = linkedList.size();
                for (int i = 0; i < levelCnt; i++) {
                    if (di) {
                        TreeNode node = linkedList.poll();
                        temp.add((Integer) node.val);
                        if (node.left != null) {
                            linkedList.add(node.left);
                        }
                        if (node.right != null) {
                            linkedList.add(node.right);
                        }
                    } else {
                        TreeNode node = linkedList.pollLast();
                        temp.add((Integer) node.val);
                        if (node.right != null) {
                            linkedList.addFirst(node.right);
                        }
                        if (node.left != null) {
                            linkedList.addFirst(node.left);
                        }
                    }
                }
                di = !di;
                ret.add(temp);
            }
            return ret;
        }
    }

    /**
     * 官方的DFS
     */
    class Solution2 {
        protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
            if (level >= results.size()) {
                LinkedList<Integer> newLevel = new LinkedList<>();
                newLevel.add(node.val);
                results.add(newLevel);
            }
            else {
                // 如果是奇数行数字加在前面，偶数行加在后面
                if (level % 2 == 0) {
                    results.get(level).add(node.val);
                }
                else {
                    results.get(level).add(0, node.val);
                }
            }

            if (node.left != null) {
                DFS(node.left, level + 1, results);
            }
            if (node.right != null) {
                DFS(node.right, level + 1, results);
            }
        }

        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<List<Integer>> results = new ArrayList<>();
            DFS(root, 0, results);
            return results;
        }
    }

}

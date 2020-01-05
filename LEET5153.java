package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 双周赛 2019年12月28日  5153. 层数最深叶子节点的和
 * <p>
 * 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 *
 * @author zm
 */
public class LEET5153 {
    public static void main(String[] args) {
        LEET5153 l5153 = new LEET5153();
        TreeNode root = CommonFunctions.stringToTreeNode("[1,2,3,4,5,null,6,7,null,null,null,null,8]");
        System.out.println(l5153.deepestLeavesSum(root));

    }

    public int deepestLeavesSum(TreeNode root) {
        JSONObject rootJo = new JSONObject();
        rootJo.put("Node", root);
        rootJo.put("Deep", 0);
        Queue<JSONObject> nodeQueue = new LinkedList<>();
        nodeQueue.add(rootJo);
        int leavesSum = 0;
        int curDeep = 0;
        while (!nodeQueue.isEmpty()) {
            JSONObject jo = nodeQueue.remove();
            TreeNode node = (TreeNode) jo.get("Node");
            int deepLvl = (int) jo.get("Deep");
            if (node == null) {
                continue;
            }
            if (curDeep == deepLvl) {
                leavesSum += node.val;
            } else {
                curDeep++;
                leavesSum = node.val;
            }
            JSONObject lJo = new JSONObject();
            lJo.put("Node", node.left);
            lJo.put("Deep", deepLvl + 1);
            JSONObject rJo = new JSONObject();
            rJo.put("Node", node.right);
            rJo.put("Deep", deepLvl + 1);
            nodeQueue.add(lJo);
            nodeQueue.add(rJo);
        }
        return leavesSum;
    }

    public int deepestLeavesSum2(TreeNode root) {
        HashMap<String, Object> rootJo = new HashMap<>();
        rootJo.put("Node", root);
        rootJo.put("Deep", 0);
        Queue<HashMap> nodeQueue = new LinkedList<>();
        nodeQueue.add(rootJo);
        int leavesSum = 0;
        int curDeep = 0;
        while (!nodeQueue.isEmpty()) {
            HashMap jo = nodeQueue.remove();
            TreeNode node = (TreeNode) jo.get("Node");
            int deepLvl = (int) jo.get("Deep");
            if (node == null) {
                continue;
            }
            if (curDeep == deepLvl) {
                leavesSum += node.val;
            } else {
                curDeep++;
                leavesSum = node.val;
            }
            HashMap<String, Object> lJo = new HashMap<>();
            lJo.put("Node", node.left);
            lJo.put("Deep", deepLvl + 1);
            HashMap<String, Object> rJo = new HashMap<>();
            rJo.put("Node", node.right);
            rJo.put("Deep", deepLvl + 1);
            nodeQueue.add(lJo);
            nodeQueue.add(rJo);
        }
        return leavesSum;
    }
}

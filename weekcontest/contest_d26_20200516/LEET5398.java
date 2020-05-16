package com.zm.LeetCodeEx.weekcontest.contest_d26_20200516;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 双周赛 2020年5月16日
 * <p>
 * 5398. 统计二叉树中好节点的数目
 * <p>
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * <p>
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [3,1,4,3,null,1,5]
 * 输出：4
 * 解释：图中蓝色节点为好节点。
 * 根节点 (3) 永远是个好节点。
 * 节点 4 -> (3,4) 是路径中的最大值。
 * 节点 5 -> (3,4,5) 是路径中的最大值。
 * 节点 3 -> (3,1,3) 是路径中的最大值。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [3,3,null,4,2]
 * 输出：3
 * 解释：节点 2 -> (3, 3, 2) 不是好节点，因为 "3" 比它大。
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：1
 * 解释：根节点是好节点。
 * <p>
 * 提示：
 * <p>
 * 二叉树中节点数目范围是 [1, 10^5] 。
 * 每个节点权值的范围是 [-10^4, 10^4] 。
 *
 * @author zm
 */
public class LEET5398 {
    public static void main(String[] args) {
        LEET5398 l5398 = new LEET5398();
        System.out.println(JSON.toJSONString(l5398.new Solution().goodNodes(CommonFunctions.stringToTreeNode("[3,1,4,3,null,1,5]"))));
    }

    class Solution {
        private int ret = 0;

        public int goodNodes(TreeNode<Integer> root) {
            int curMax = root.val;
            dfs(root, curMax);
            return ret;
        }

        public void dfs(TreeNode<Integer> node, int curMax) {
            if (curMax <= node.val) {
                ret++;
                curMax = node.val;
            }
            if (node.left != null) {
                dfs(node.left, curMax);
            }
            if (node.right != null) {
                dfs(node.right, curMax);
            }
        }
    }
}



package com.zm.LeetCodeEx.weekcontest.before20200426;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 双周赛 2020年3月7日 5339. 二叉搜索子树的最大键值和
 * <p>
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 * <p>
 * 二叉搜索树的定义如下：
 * <p>
 * 任意节点的左子树中的键值都 小于 此节点的键值。
 * 任意节点的右子树中的键值都 大于 此节点的键值。
 * 任意节点的左子树和右子树都是二叉搜索树。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * 输出：20
 * 解释：键值为 3 的子树是和最大的二叉搜索树。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [4,3,null,1,2]
 * 输出：2
 * 解释：键值为 2 的单节点子树是和最大的二叉搜索树。
 * 示例 3：
 * <p>
 * 输入：root = [-4,-2,-5]
 * 输出：0
 * 解释：所有节点键值都为负数，和最大的二叉搜索树为空。
 * 示例 4：
 * <p>
 * 输入：root = [2,1,3]
 * 输出：6
 * 示例 5：
 * <p>
 * 输入：root = [5,4,8,3,null,6,3]
 * 输出：7
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每棵树最多有 40000 个节点。
 * 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
 *
 * @author zm
 */
public class LEET5339 {
    public static void main(String[] args) {
        LEET5339 l5339 = new LEET5339();
        System.out.println(l5339.new Solution().maxSumBST(CommonFunctions.stringToTreeNode("[1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]")));
        System.out.println(l5339.new Solution().maxSumBST(CommonFunctions.stringToTreeNode("[4,3,null,1,2]")));
        System.out.println(l5339.new Solution().maxSumBST(CommonFunctions.stringToTreeNode("[-4,-2,-5]")));
        System.out.println(l5339.new Solution().maxSumBST(CommonFunctions.stringToTreeNode("[2,1,3]")));
        System.out.println(l5339.new Solution().maxSumBST(CommonFunctions.stringToTreeNode("[5,4,8,3,null,6,3]")));
        System.out.println(l5339.new Solution().maxSumBST(CommonFunctions.stringToTreeNode("[8,9,8,null,9,null,1,null,null,-3,5,null,-2,null,6]")));
        System.out.println(l5339.new Solution().maxSumBST(CommonFunctions.stringToTreeNode("[0,9,-8,6,-6,9,3,-5,1,7,1,0,null,-6,null,-4,1,null,3,2,null,null,null,null,null,null,null,null,null,null,null,4,null,10,8,null,null,null,null,1,13,-1,2,10,16,null,null,null,6,null,12,null,17]")));
        System.out.println(l5339.new Solution().maxSumBST(CommonFunctions.stringToTreeNode("[4,0,2,-20,7,11,null,7,2,null,5,-3,-13,11,-9,4,-20,-9,9,null,16,8,-17,-11,-3,null,null,-18,-15,-20,-3,20,null,4,20,null,null,-2,null,null,2,-3,2,null,14,null,-1,null,11,12,5,null,null,null,-4,-7,6,12,23,null,null,null,12,9,8,9,-5,-6,null,null,null,-15,null,-9,null,null,-10,-6,5,-8,-6,5,7,10,13,21,24,null,null,null,null,null,null,null,null,null,null,null,5,null,null,null,null,null,null,null,null,null,-3,null,null,null,2,null,null,null,8,null,null,null,14,null,22,null,26]")));
    }

    class Solution {
        private int ret = 0;

        public int maxSumBST(TreeNode<Integer> root) {
            if (root != null) {
                Integer[] leftRet = helper(root.left, Integer.MIN_VALUE, root.val, Integer.MIN_VALUE, Integer.MAX_VALUE);
                Integer[] rightRet = helper(root.right, root.val, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (leftRet[0] == 1 && rightRet[0] == 1) {
                    int sum = leftRet[2] + rightRet[2] + root.val;
                    ret = sum > ret ? sum : ret;
                }
            }
            return ret;
        }

        private Integer[] helper(TreeNode<Integer> node, int LastLevelmin, int LastLevelmax, int Last2Levelmin, int Last2Levelmax) {
            if (node == null) {
                return new Integer[]{1, 1, 0};
            }
            if (node.val <= LastLevelmin || node.val >= LastLevelmax) {
                Integer[] leftRet = helper(node.left, Integer.MIN_VALUE, node.val, Integer.MIN_VALUE, Integer.MAX_VALUE);
                Integer[] rightRet = helper(node.right, node.val, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (leftRet[0] == 1 && rightRet[0] == 1) {
                    int sum = leftRet[2] + rightRet[2] + node.val;
                    ret = sum > ret ? sum : ret;
                }
                return new Integer[]{0, 0, 0};
            } else if (node.val <= Last2Levelmin || node.val >= Last2Levelmax) {
                Integer[] leftRet = helper(node.left, Integer.MIN_VALUE, node.val, Integer.MIN_VALUE, Integer.MAX_VALUE);
                Integer[] rightRet = helper(node.right, node.val, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (leftRet[0] == 1 && rightRet[0] == 1) {
                    int sum = leftRet[2] + rightRet[2] + node.val;
                    ret = sum > ret ? sum : ret;
                    return new Integer[]{1, 0, sum};
                }
                return new Integer[]{0, 0, 0};
            } else {
                Integer[] leftRet = helper(node.left, Integer.MIN_VALUE, node.val, LastLevelmin, LastLevelmax);
                Integer[] rightRet = helper(node.right, node.val, Integer.MAX_VALUE, LastLevelmin, LastLevelmax);
                if (leftRet[1] == 1 && rightRet[1] == 1) {
                    int sum = leftRet[2] + rightRet[2] + node.val;
                    ret = sum > ret ? sum : ret;
                    return new Integer[]{1, 1, sum};
                } else if (leftRet[0] == 1 && rightRet[0] == 1) {
                    int sum = leftRet[2] + rightRet[2] + node.val;
                    ret = sum > ret ? sum : ret;
                    return new Integer[]{1, 0, sum};
                } else {
                    return new Integer[]{0, 0, 0};
                }
            }
        }
    }
}

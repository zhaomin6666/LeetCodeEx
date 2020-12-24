package com.zm.LeetCodeEx.algorithms.ex1001_1100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.Stack;

/**
 * 1028. 从先序遍历还原二叉树
 * <p>
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * <p>
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * <p>
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * <p>
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * .....1
 * .../  \
 * ..2    5
 * ./ \  / \
 * 3  4 6  7
 * <p>
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * 示例 2：
 * <p>
 * .......1
 * ...../  \
 * ....2    5
 * .../    /
 * ..3    6
 * ./    /
 * 4    7
 * <p>
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * 示例 3：
 * <p>
 * .......1
 * ....../
 * ....401
 * .../   \
 * ..349  88
 * ./
 * 90
 * <p>
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *  
 * <p>
 * 提示：
 * <p>
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 *
 * @author zm
 */
public class LEET1028 {
    public static void main(String[] args) {
        LEET1028 l1014 = new LEET1028();
        System.out.println(CommonFunctions.treeNodeToString(
                l1014.new Solution().recoverFromPreorder("1-401--349---90--88")));
    }

    /**
     * 官方题解
     * 用一个栈来维护结点，每个结点前面'-'的数量表示结点的深度，保证在栈中每一层只会最多有一个结点同时存在。
     * 比如：[1,2,3,4]左树有2层，右树有1层 1-2--3-4
     * 当迭代到4的时候，栈中有1,2,3。此时由于4的层数为1，所以需要删除其他结点直到和4同层的删除，这样右节点就可以正确的放置。
     */
    class Solution {
        public TreeNode recoverFromPreorder(String S) {
            Stack<TreeNode> path = new Stack<>();
            int pos = 0;
            while (pos < S.length()) {
                int level = 0;
                while (S.charAt(pos) == '-') {
                    ++level;
                    ++pos;
                }
                int value = 0;
                while (pos < S.length() && Character.isDigit(S.charAt(pos))) {
                    value = value * 10 + (S.charAt(pos) - '0');
                    ++pos;
                }
                TreeNode node = new TreeNode(value);
                if (level == path.size()) {
                    if (!path.isEmpty()) {
                        path.peek().left = node;
                    }
                } else {
                    while (level != path.size()) {
                        path.pop();
                    }
                    path.peek().right = node;
                }
                path.push(node);
            }
            while (path.size() > 1) {
                path.pop();
            }
            return path.peek();
        }
    }

    /**
     * 使用递归完成，但是需要用全局变量记录当前位置。
     * 递归中使用depth记录层数。
     * 如果当前层数比预计层数小（只有可能小，不可能大），那说明不再是左节点。
     * 注：题中保证了“如果节点只有一个子节点，那么保证该子节点为左子节点。”
     */
    class Solution2 {
        int index;

        public TreeNode recoverFromPreorder(String S) {
            index = 0;
            return helper(S, 0);
        }

        public TreeNode<Integer> helper(String S, int depth) {
            if (index >= S.length()) {
                return null;
            }
            int curDepth = 0;
            int k = index;
            while (k < S.length() && S.charAt(k) == '-') {
                curDepth++;
                k++;
            }
            if (curDepth != depth) {
                return null;
            }
            index = k;
            int val = 0;
            while (index < S.length() && Character.isDigit(S.charAt(index))) {
                val = val * 10 + (S.charAt(index) - '0');
                index++;
            }
            TreeNode<Integer> node = new TreeNode<>(val);
            node.left = helper(S, depth + 1);
            node.right = helper(S, depth + 1);
            return node;
        }
    }
}

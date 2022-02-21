package com.zm.LeetCodeEx.algorithms.ex201_300;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 236. 二叉树的最近公共祖先
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *  
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * @author zm
 */
public class LEET236 {
    public static void main(String[] args) {
        LEET236 l236 = new LEET236();
        System.out.println(l236.new Solution().lowestCommonAncestor(
                CommonFunctions.stringToTreeNode("[3,5,1,6,2,0,8,null,null,7,4]"),
                new TreeNode(5), new TreeNode(1)).val);
        System.out.println(l236.new Solution().lowestCommonAncestor(
                CommonFunctions.stringToTreeNode("[3,5,1,6,2,0,8,null,null,7,4]"),
                new TreeNode(5), new TreeNode(4)).val);
        System.out.println(l236.new Solution().lowestCommonAncestor(
                CommonFunctions.stringToTreeNode("[3,5,1,6,2,0,8,null,null,7,4]"),
                new TreeNode(7), new TreeNode(0)).val);
        System.out.println(l236.new Solution().lowestCommonAncestor(
                CommonFunctions.stringToTreeNode("[3,5,1,6,2,0,8,null,null,7,4]"),
                new TreeNode(1), new TreeNode(0)).val);
    }

    class Solution {
        private int pVal;
        private int qVal;
        private TreeNode retNode;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            pVal = p.val;
            qVal = q.val;
            // 查找左右树中是否包含符合的结点，如果都在左边那就是左子树内（右边相同），一个左一个右那么就这个这个根节点。
            // 如果本身就是然后左边一个或者右边一个那就也是这个根节点
            dfs(root);
            return retNode;
        }

        private boolean dfs(TreeNode node) {
            if (node == null) {
                return false;
            }
            boolean findLeft = dfs(node.left);
            boolean findRight = dfs(node.right);
            if ((findLeft && findRight) || ((node.val == pVal || node.val == qVal) && (findLeft || findRight))) {
                retNode = node;
            }
            return findLeft || findRight || (node.val == pVal || node.val == qVal);
        }

    }

    class Solution2 {
        Map<Integer, TreeNode> parent = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        public void dfs(TreeNode root) {
            if (root.left != null) {
                parent.put(root.left.val, root);
                dfs(root.left);
            }
            if (root.right != null) {
                parent.put(root.right.val, root);
                dfs(root.right);
            }
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root);
            while (p != null) {
                visited.add(p.val);
                p = parent.get(p.val);
            }
            while (q != null) {
                if (visited.contains(q.val)) {
                    return q;
                }
                q = parent.get(q.val);
            }
            return null;
        }
    }
}

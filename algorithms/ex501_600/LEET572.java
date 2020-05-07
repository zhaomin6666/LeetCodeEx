package com.zm.LeetCodeEx.algorithms.ex501_600;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 572 另一个树的子树
 * 给定两个非空二叉树.s.和.t，检验.s.中是否包含和.t.具有相同结构和节点值的子树。s.的一个子树包括.s.的一个节点和这个节点的所有子孙。s.也可以看做它自身的一棵子树。
 * <p>
 * 示例.1:
 * 给定的树.s:
 * <p>
 * .....3
 * ..../.\
 * ...4...5
 * ../.\
 * .1...2
 * 给定的树.t：
 * <p>
 * ...4.
 * ../.\
 * .1...2
 * 返回.true，因为.t.与.s.的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例.2:
 * 给定的树.s：
 * <p>
 * .....3
 * ..../.\
 * ...4...5
 * ../.\
 * .1...2
 * ..../
 * ...0
 * 给定的树.t：
 * <p>
 * ...4
 * ../.\
 * .1...2
 * 返回.false。
 *
 * @author zm
 */
public class LEET572 {
    public static void main(String[] args) {
        LEET572 l572 = new LEET572();
        System.out.println(l572.new Solution().isSubtree(CommonFunctions.stringToTreeNode("[3,4,5,1,2,null,null,null,null,0]"),
                CommonFunctions.stringToTreeNode("[4,1,2]")));
        System.out.println(l572.new Solution().isSubtree(CommonFunctions.stringToTreeNode("[3,4,5,1,2]"),
                CommonFunctions.stringToTreeNode("[4,1,2]")));
    }

    class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            if (s.val == t.val) {
                if (isSameTree(s, t)) {
                    return true;
                }
            }
            if (s.right != null) {
                if (isSubtree(s.right, t)) {
                    return true;
                }
            }
            if (s.left != null) {
                if (isSubtree(s.left, t)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isSameTree(TreeNode<Integer> p, TreeNode<Integer> q) {
            if (p != null) {
                if (q == null) {
                    return false;
                }
                return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
            if (q != null) {
                return false;
            }
            return true;
        }
    }
}

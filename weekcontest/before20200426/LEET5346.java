package com.zm.LeetCodeEx.weekcontest.before20200426;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 周赛 2020年3月1日 5346. 二叉树中的列表
 * <p>
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * <p>
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * <p>
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 解释：树中蓝色的节点构成了与链表对应的子路径。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：false
 * 解释：二叉树中不存在一一对应链表的路径。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。
 * 链表包含的节点数目在 1 到 100 之间。
 * 二叉树包含的节点数目在 1 到 2500 之间。
 *
 * @author zm
 */
public class LEET5346 {
    public static void main(String[] args) {
        LEET5346 l5346 = new LEET5346();
        System.out.println(l5346.new Solution().isSubPath(CommonFunctions.stringToListNode("[4,2,8]"),
                CommonFunctions.stringToTreeNode("[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]")));
        System.out.println(l5346.new Solution().isSubPath(CommonFunctions.stringToListNode("[1,4,2,6]"),
                CommonFunctions.stringToTreeNode("[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]")));
        System.out.println(l5346.new Solution().isSubPath(CommonFunctions.stringToListNode("[1,4,2,6,8]"),
                CommonFunctions.stringToTreeNode("[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]")));
        System.out.println(l5346.new Solution().isSubPath(CommonFunctions.stringToListNode("[1,9]"),
                CommonFunctions.stringToTreeNode("[1,null,1,10,1,9,8]")));

    }

    class Solution {
        public boolean isSubPath(ListNode head, TreeNode root) {
            if (head == null) {
                return true;
            }
            if (root == null) {
                return false;
            }
            return isSubHelper(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
        }

        private boolean isSubHelper(ListNode head, TreeNode node) {
            if (head == null) {
                return true;
            }
            if (node == null) {
                return false;
            }
            if (head.val != node.val) {
                return false;
            }
            return isSubHelper(head.next, node.left) || isSubHelper(head.next, node.right);
        }
    }
}

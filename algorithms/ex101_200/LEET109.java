package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayList;

/**
 * 109. 有序链表转换二叉搜索树
 * <p>
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过.1。 <br>
 *
 * 示例: <br>
 * 
 * 给定的有序链表： [-10, -3, 0, 5, 9], <br>
 * 
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树： <br>
 * 
 * ......0 <br>
 * ...../.\ <br>
 * ...-3...9 <br>
 * .../.../ <br>
 * .-10..5 <br>
 * 
 * 
 * @author zm
 */
public class LEET109 {
	public static void main(String[] args) {
		LEET109 l109 = new LEET109();
		System.out.println(CommonFunctions.treeNodeToString(
				l109.new Solution().sortedListToBST(CommonFunctions.stringToListNode("[-10, -3, 0, 5, 9]"))));
	}

	class Solution {
		public TreeNode sortedListToBST(ListNode head) {
			while (head != null) {
				nums.add(head.val);
				head = head.next;
			}
			return helper(0, nums.size() - 1);
		}

		private ArrayList<Integer> nums = new ArrayList<>();

		private TreeNode helper(int l, int r) {
			if (l > r) {
				return null;
			}
			int mid = (l + r + 1) >>> 1;
			TreeNode node = new TreeNode(nums.get(mid));
			node.left = helper(l, mid - 1);
			node.right = helper(mid + 1, r);
			return node;
		}
	}
}

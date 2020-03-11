package com.zm.LeetCodeEx.algorithms;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 * <p>
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 
 * 示例 1: <br>
 * 输入: 1->2->3->3->4->4->5 <br>
 * 输出: 1->2->5
 * <p>
 * 示例 2: <br>
 * 输入: 1->1->1->2->3 <br>
 * 输出: 2->3 <br>
 * 
 * 
 * @author zm
 */
public class LEET082 {
	public static void main(String[] args) {
		LEET082 l082 = new LEET082();
		//System.out.println(CommonFunctions.listNodeToString(
		//		l082.new Solution().deleteDuplicates(CommonFunctions.stringToListNode("[1,2,3,3,4,4,5]"))));
		//System.out.println(CommonFunctions.listNodeToString(
		//		l082.new Solution().deleteDuplicates(CommonFunctions.stringToListNode("[1,1,1,2,3]"))));
		//System.out.println(CommonFunctions.listNodeToString(
		//		l082.new Solution().deleteDuplicates(CommonFunctions.stringToListNode("[1,1,1,2,2,3,3]"))));
		System.out.println(CommonFunctions.listNodeToString(
				l082.new Solution().deleteDuplicates(CommonFunctions.stringToListNode("[1,1,2,2,2,2,2,3,4,4,5]"))));
	}

	class Solution {
		public ListNode<Integer> deleteDuplicates(ListNode<Integer> head) {
			if (head == null) {
				return null;
			}
			ListNode<Integer> dummyHead = new ListNode<Integer>(-1);
			dummyHead.next = head;
			ListNode<Integer> temp = dummyHead;
			int cur = head.val;
			boolean isDuplicate = false;
			while (temp.next != null) {
				if (isDuplicate) {
					if (temp.next.val == cur) {
						temp.next = temp.next.next;
					} else {
						isDuplicate = false;
					}
				} else {
					if (temp.next.next != null && temp.next.val.equals(temp.next.next.val)) {
						cur = temp.next.val;
						isDuplicate = true;
						temp.next = temp.next.next.next;
					} else {
						isDuplicate = false;
						temp = temp.next;
					}
				}
			}
			return dummyHead.next;
		}
	}
}

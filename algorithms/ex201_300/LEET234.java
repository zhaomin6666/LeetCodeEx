package com.zm.LeetCodeEx.algorithms.ex201_300;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 请判断一个链表是否为回文链表。
 * 
 * 示例 1:
 * 
 * 输入: 1->2 输出: false </br>
 * 
 * 示例 2:
 * 
 * 输入: 1->2->2->1 输出: true 进阶： 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * 
 * @author zm
 *
 */
public class LEET234 {
	public static void main(String[] args) {
		LEET234 l234 = new LEET234();
		String s = "[1,2,1,3,1,2,1]";
		ListNode node = CommonFunctions.stringToListNode(s);
		// System.out.println(CommonFunctions.listNodeToString(node));
		System.out.println(l234.isPalindrome3(node));
	}

	public boolean isPalindrome(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		if (head == null || head.next == null) {
			return true;
		}
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode rev = reverse(slow.next);
		while (rev != null) {
			if (rev.val != head.val) {
				return false;
			}
			rev = rev.next;
			head = head.next;
		}
		return true;
	}

	public ListNode reverse(ListNode node) {
		if (node.next == null) {
			return node;
		}
		ListNode nodehead = reverse(node.next);
		ListNode node2 = nodehead;
		while (node2.next != null) {
			node2 = node2.next;
		}
		node2.next = node;
		node.next = null;
		return nodehead;
	}

	public boolean isPalindrome2(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		if (head == null || head.next == null) {
			return true;
		}
		ListNode nodeRevTemp = new ListNode(slow.val);
		while (fast.next != null && fast.next.next != null) {
			// 前半部分翻转
			ListNode nodeRev = new ListNode(slow.next.val);
			nodeRev.next = nodeRevTemp;
			nodeRevTemp = nodeRev;
			// 移动指针
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast.next != null && fast.next.next == null) {
			slow = slow.next;
		}
		System.out.println(CommonFunctions.listNodeToString(nodeRevTemp));
		System.out.println(CommonFunctions.listNodeToString(slow));
		while (nodeRevTemp != null) {
			if (nodeRevTemp.val != slow.val) {
				return false;
			}
			nodeRevTemp = nodeRevTemp.next;
			slow = slow.next;
		}
		return true;
	}

	public boolean isPalindrome3(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		if (head.next.next == null) {
			return head.val == head.next.val;
		}
		ListNode slow = head;
		ListNode fast = head.next;

		while (fast.next != null) {
			if (slow.val == fast.next.val) {
				if (fast.next.next != null) {
					return false;
				}
				fast.next = null;
				slow = slow.next;
				fast = slow.next;
				if (fast == null || slow.val == fast.val) {
					return true;
				}
			} else {
				fast = fast.next;
			}
		}
		return false;

	}
}

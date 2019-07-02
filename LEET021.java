package com.zm.LeetCodeEx;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 
 * 示例：
 * 
 * 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
 * 
 * 
 * @author zm
 *
 */
public class LEET021 {
	public static void main(String[] args) {
		LEET021 l021 = new LEET021();
		ListNode l1 = CommonFunctions.stringToListNode("[1,2,4]");
		ListNode l2 = CommonFunctions.stringToListNode("[1,3,4]");
		System.out.println(CommonFunctions.listNodeToString(l021.mergeTwoLists(l1, l2)));
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode node = head;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				node.next = l2;
				l2 = l2.next;
			} else if (l2 == null) {
				node.next = l1;
				l1 = l1.next;
			} else {
				if (l1.val > l2.val) {
					node.next = l2;
					l2 = l2.next;
				} else {
					node.next = l1;
					l1 = l1.next;
				}

			}
			node = node.next;
		}
		return head.next;
	}

}

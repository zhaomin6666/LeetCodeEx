package com.zm.LeetCodeEx.algorithms;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 反转一个单链表。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * 
 * @author zm
 *
 */
public class LEET206 {
	public static void main(String[] args) {
		LEET206 l206 = new LEET206();
		String s = "[1,2,3,3,4,2,6]";
		ListNode node = CommonFunctions.stringToListNode(s);
        System.out.println(CommonFunctions.listNodeToString(l206.reverseList3(node)));
	}

	/**
	 * 递归
	 * 
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode nodehead = reverseList(head.next);
		ListNode node2 = nodehead;
		while (node2.next != null) {
			node2 = node2.next;
		}
		node2.next = head;
		head.next = null;
		return nodehead;
	}

	/**
     * 循环 新建节点
	 * 
	 * @param head
	 * @return
	 */
	public ListNode reverseList2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode slow = head;
		ListNode nodeRevTemp = new ListNode(slow.val);
		while (slow.next != null) {
			// 前半部分翻转
			ListNode nodeRev = new ListNode(slow.next.val);
			nodeRev.next = nodeRevTemp;
			nodeRevTemp = nodeRev;
			// 移动指针
			slow = slow.next;
		}
		return nodeRevTemp;
	}

    /**
     * 循环 对原节点进行交换
     *
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}

package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 92. 反转链表 II
 * <p>
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:<br>
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4<br>
 * 输出: 1->4->3->2->5->NULL
 * 
 * 
 * @author zm
 */
public class LEET092 {
	public static void main(String[] args) {
		LEET092 l091 = new LEET092();
		System.out.println(CommonFunctions.listNodeToString(
				(l091.new Solution().reverseBetween(CommonFunctions.stringToListNode("[1,2,3,4,5]"), 2, 4))));

	}

	class Solution {
		public ListNode<Integer> reverseBetween(ListNode<Integer> head, int m, int n) {
			if (head == null) {
				return null;
			}
			// 用两个指针记录，一个记录当前位置，一个记录前一个位置。
			ListNode<Integer> cur = head, prev = null;
			while (m > 1) {
				prev = cur;
				cur = cur.next;
				m--;
				n--;
			}

			// 当当前位置为翻转的开始时，前一结点需要保存下来，用con保存，最后需要把这个结点的后续指针指向翻转部分的最后一个结点
			// 当前节点也需要保存，用tail保存，最后需要把这个结点的后续指针指向反转部分后面的结点
			ListNode<Integer> con = prev, tail = cur;

			// 翻转时需要用到第三个节点用来辅助记录下一个节点，因为会把当前节点的下一个指针指向上一个节点
			ListNode<Integer> third = null;
			while (n > 0) {
				third = cur.next;
				cur.next = prev;
				prev = cur;
				cur = third;
				n--;
			}

			// 将前面部分和翻转链表及后面部分连接在一起
			if (con != null) {
				con.next = prev;
			} else {
				head = prev;
			}
			tail.next = cur;

			return head;
		}
	}
}

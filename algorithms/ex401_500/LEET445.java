package com.zm.LeetCodeEx.algorithms.ex401_500;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

import java.util.Stack;

/**
 * 445. 两数相加 II
 * <p>
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。<br>
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶：
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例：
 * <p>
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)<br>
 * 输出：7 -> 8 -> 0 -> 7 <br>
 * 
 * @author zm
 *
 */
public class LEET445 {
	public static void main(String[] args) {
		LEET445 l445 = new LEET445();
		System.out.println(CommonFunctions.listNodeToString(l445.new Solution2().addTwoNumbers(
				CommonFunctions.stringToListNode("[7,2,4,3]"), CommonFunctions.stringToListNode("[5,6,4]"))));
	}

	/**
	 * 直接利用{@link com.zm.LeetCodeEx.algorithms.ex1_100.LEET002}
	 *
	 * @author zm
	 */
	class Solution {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			// 翻转链表
			l1 = reverse(l1);
			l2 = reverse(l2);
			// 各位相加
			ListNode addResult = addTwoNumbersReverse(l1, l2);
			return reverse(addResult);
		}

		public ListNode reverse(ListNode cur) {
			ListNode pre = null;
			while (cur != null) {
				ListNode temp = cur.next;
				cur.next = pre;
				pre = cur;
				cur = temp;
			}
			return pre;
		}

		private ListNode addTwoNumbersReverse(ListNode l1, ListNode l2) {
			ListNode dummyHead = new ListNode(0);
			ListNode p = l1, q = l2, curr = dummyHead;
			int carry = 0;
			while (p != null || q != null) {
				int x = (p != null) ? p.val : 0;
				int y = (q != null) ? q.val : 0;
				int sum = carry + x + y;
				carry = sum / 10;
				curr.next = new ListNode(sum % 10);
				curr = curr.next;
				if (p != null) {
					p = p.next;
				}
				if (q != null) {
					q = q.next;
				}
			}
			if (carry > 0) {
				curr.next = new ListNode(carry);
			}
			return dummyHead.next;
		}
	}

	/**
	 * 不修改原链表即不翻转链表的话就要使用到栈
	 *
	 * @author zm
	 */
	class Solution2 {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			Stack<Integer> stack1 = listToStack(l1);
			Stack<Integer> stack2 = listToStack(l2);
			ListNode curr = null;
			int carry = 0;
			while (!stack1.isEmpty() || !stack2.isEmpty()) {
				int x = stack1.isEmpty() ? 0 : stack1.pop();
				int y = stack2.isEmpty() ? 0 : stack2.pop();
				int sum = carry + x + y;
				carry = sum / 10;
				ListNode temp = new ListNode(sum % 10);
				temp.next = curr;
				curr = temp;
			}
			if (carry > 0) {
				ListNode temp = new ListNode(carry);
				temp.next = curr;
				curr = temp;
			}
			return curr;
		}

		private Stack<Integer> listToStack(ListNode node) {
			Stack<Integer> stack = new Stack<>();
			while (node != null) {
				stack.add(node.val);
				node = node.next;
			}
			return stack;
		}
	}

}

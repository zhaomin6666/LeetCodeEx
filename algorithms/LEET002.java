package com.zm.LeetCodeEx.algorithms;

import java.io.IOException;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 方法的区别就在于判断，减少无用的逻辑
 * 
 * @author zm
 *
 */
public class LEET002 {
	public static void main(String[] args) throws IOException {
		LEET002 l002 = new LEET002();
        ListNode l1 = CommonFunctions.stringToListNode("[2,4,3]");
        ListNode l2 = CommonFunctions.stringToListNode("[5,6]");
		ListNode ret = l002.addTwoNumbers2(l1, l2);
        String out = CommonFunctions.listNodeToString(ret);
		System.out.print(out);
	}

	/**
	 * 循环末尾判断，两数都取完并没有进位
     *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = null;
		ListNode resulttemp = null;
		ListNode temp1 = l1;
		ListNode temp2 = l2;
		int temp = 0;
		while (true) {
			int temp1val = temp1 == null ? 0 : temp1.val;
			int temp2val = temp2 == null ? 0 : temp2.val;
			int sum = temp1val + temp2val + temp;
			temp = sum / 10;
			if (result == null) {
				result = new ListNode(sum % 10);
				resulttemp = result;
			} else {
				resulttemp.val = sum % 10;
			}
			temp1 = temp1 == null ? null : temp1.next;
			temp2 = temp2 == null ? null : temp2.next;
			if (temp == 0 && temp1 == null && temp2 == null) {
				break;
			} else {
				resulttemp.next = new ListNode(0);
				resulttemp = resulttemp.next;
			}
		}
		return result;
	}

	/**
	 * 只要两个数有一个没有取完，则循环继续，结束之后判断是否有进位，加上
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
		int sum = 0;
        ListNode cur = dummyHead;
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			cur.next = new ListNode(sum % 10);
			sum /= 10;
			cur = cur.next;
		}
		if (sum == 1) {
			cur.next = new ListNode(sum);
		}
        return dummyHead.next;
	}
}

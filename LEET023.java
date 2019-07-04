package com.zm.LeetCodeEx;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 
 * 示例:
 * 
 * 输入: [   1->4->5,   1->3->4,   2->6 ] 输出: 1->1->2->3->4->4->5->6
 * 
 * @author zm
 *
 */
public class LEET023 {
	public static void main(String[] args) {
		LEET023 l023 = new LEET023();
		List<ListNode> list = new ArrayList<>();
		list.add(CommonFunctions.stringToListNode("[1,4,5]"));
		list.add(CommonFunctions.stringToListNode("[1,3,4]"));
		list.add(CommonFunctions.stringToListNode("[2,6]"));
		ListNode[] lists = new ListNode[3];
		list.toArray(lists);
		System.out.println(CommonFunctions.listNodeToString(l023.mergeKLists2(lists)));
	}

	/**
	 * 使用{@link LEET021}的解答，显然会把遍历过的链表重新遍历，影响效率 </br>
	 * 时间复杂度O(kN)
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		ListNode nodeMain = lists[0];
		for (int i = 1; i < lists.length; i++) {
			nodeMain = new LEET021().mergeTwoLists(nodeMain, lists[i]);
		}
		return nodeMain;
	}

	/**
	 * 每次取最小的，类似于{@link LEET021}的思路，扩展为多个链表 </br>
	 * 时间复杂度O(kN)
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists2(ListNode[] lists) {
		ListNode nodeMain = new ListNode(0);
		ListNode nodeHead = nodeMain;
		while (true) {
			boolean subFlag = false;
			int min = Integer.MAX_VALUE;
			for (ListNode node : lists) {
				if (node != null) {
					min = Math.min(node.val, min);
					subFlag = true;
				}
			}
			if (!subFlag) {
				// 如果全是空那么久直接结束循环
				break;
			}
			for (int i = 0; i < lists.length; i++) {
				if (lists[i] != null) {
					if (lists[i].val == min) {
						nodeMain.next = lists[i];
						nodeMain = nodeMain.next;
						lists[i] = lists[i].next;
					}
				}
			}
		}
		return nodeHead.next;
	}
}

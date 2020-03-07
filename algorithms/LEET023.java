package com.zm.LeetCodeEx.algorithms;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
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
		System.out.println(CommonFunctions.listNodeToString(l023.mergeKLists3(lists)));
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
	public ListNode mergeKLists2(ListNode<Integer>[] lists) {
		ListNode<Integer> nodeMain = new ListNode<>(0);
		ListNode<Integer> nodeHead = nodeMain;
		while (true) {
			boolean subFlag = false;
			int min = Integer.MAX_VALUE;
			for (ListNode<Integer> node : lists) {
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

	/**
	 * 优化第一个方法，采用分治的思想逐一合并
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists3(ListNode[] lists) {
		int amount = lists.length;
		int interval = 1;
		while (interval < amount) {
			for (int i = 0; i < amount - interval; i = i + interval * 2) {
				lists[i] = merge2Lists(lists[i], lists[i + interval]);
			}
			interval *= 2;
		}
		return amount > 0 ? lists[0] : null;
	}

	public ListNode merge2Lists(ListNode<Integer> l1, ListNode<Integer> l2) {
		ListNode<Integer> dummyNode = new ListNode(0);
		ListNode<Integer> point = dummyNode;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				point.next = l1;
				l1 = l1.next;
			} else {
				point.next = l2;
				l2 = l2.next;
			}
			point = point.next;
		}
		if (l1 == null) {
			point.next = l2;
		} else {
			point.next = l1;
		}
		return dummyNode.next;
	}

	/**
	 * 使用优先队列处理取最小的那个节点 时间复杂度： O(Nlogk)
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists4(ListNode<Integer>[] lists) {
		if (lists.length == 0) {
			return null;
		}
		// 创建优先队列
		PriorityQueue<ListNode<Integer>> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(list -> list.val));
		for (ListNode<Integer> listNode : lists) {
			// 如果list非空才加入队列
			if (listNode != null) {
				queue.add(listNode);
			}
		}
		ListNode<Integer> dummyNode = new ListNode<>(0);
		ListNode<Integer> curNode = dummyNode;
		while (!queue.isEmpty()) {
			// 优先队列非空才能出队
			ListNode<Integer> node = queue.poll();
			// 当前节点的 next 指针指向出队元素
			curNode.next = node;
			// 当前指针向前移动一个元素，指向了刚刚出队的那个元素
			curNode = curNode.next;
			if (curNode.next != null) {
				// 只有非空节点才能加入到优先队列中，这样到最后队列中就没有链表了，结束循环
				queue.add(curNode.next);
			}
		}
		return dummyNode.next;
	}
}

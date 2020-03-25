package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 
 * 示例:
 * 
 * 输入: head = 1->4->3->2->5->2, x = 3 输出: 1->2->2->4->3->5
 * 
 * @author zm
 *
 */
public class LEET086 {
	public static void main(String[] args) {
		LEET086 l086 = new LEET086();
		ListNode node = CommonFunctions.stringToListNode("[1,4,3,2,5,2]");
		ListNode node2 = CommonFunctions.stringToListNode("[1]");
		System.out.println(CommonFunctions.listNodeToString(l086.partition2(node, 0)));
		System.out.println(CommonFunctions.listNodeToString(l086.partition2(node2, 0)));
	}

	/**
	 * 很简单的想法，遍历之后存为两个链表，最后合在一起
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition(ListNode<Integer> head, int x) {
		ListNode<Integer> part1 = null;
		ListNode<Integer> part2 = null;
		ListNode<Integer> part1head = null;
		ListNode<Integer> part2head = null;
		while (head != null) {
			if (head.val < x) {
				if (part1 == null) {
					part1 = new ListNode<>(head.val);
					part1head = part1;
				} else {
					part1.next = new ListNode<>(head.val);
					part1 = part1.next;
				}
			} else {
				if (part2 == null) {
					part2 = new ListNode<>(head.val);
					part2head = part2;
				} else {
					part2.next = new ListNode<>(head.val);
					part2 = part2.next;
				}
			}
			head = head.next;
		}
		if (part1 == null) {
			return part2head;
		}
		part1.next = part2head;
		return part1head;
	}

	/**
	 * 使用虚拟头结点
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition2(ListNode<Integer> head, int x) {
		ListNode<Integer> part1 = new ListNode<>(0);
		ListNode<Integer> part2 = new ListNode<>(0);
		ListNode<Integer> part1head = part1;
		ListNode<Integer> part2head = part2;
		while (head != null) {
			if (head.val < x) {
				part1.next = head;
				part1 = part1.next;
			} else {
				part2.next = head;
				part2 = part2.next;
			}
			head = head.next;
		}
		part2.next = null;
		part1.next = part2head.next;
		return part1head.next;
	}

}

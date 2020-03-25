package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.HashSet;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 给定一个链表，判断链表中是否有环。
 * 
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：head = [3,2,0,-4], pos = 1 输出：true 解释：链表中有一个环，其尾部连接到第二个节点。
 * 
 * 
 * 
 * @author zm
 *
 */
public class LEET141 {
	public static void main(String[] args) {
		LEET141 l141 = new LEET141();
		String head = "[3,2,0,-4]";
		// String head1 = "[3]";
		ListNode node1 = CommonFunctions.stringToListNodeWithCircle(head, 1);
		System.out.println(l141.hasCycle2(node1));
	}

	/**
	 * 使用Hashset记录所有的node 空间复杂度 O(N)
	 * 
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}
		HashSet<ListNode> set = new HashSet<>();
		while (head.next != null) {
			if (set.contains(head)) {
				return true;
			} else {
				set.add(head);
				head = head.next;
			}
		}
		return false;
	}

	/**
	 * 使用双指针，一个指针一次移动两步，另一个移动一步，如果是环形列表那么快的最终会追上慢的 时间复杂度 O(N+K) k为环长度 所以即O(N) 空间复杂度
	 * O(1)
	 * 
	 * @param head
	 * @return
	 */
	public boolean hasCycle2(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode head2 = head;
		while (head2.next != null && head2.next.next != null) {
			head = head.next;
			head2 = head2.next.next;
			if (head == head2) {
				return true;
			}
		}
		return false;
	}
}

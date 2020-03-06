package com.zm.LeetCodeEx.algorithms;

import java.util.HashSet;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 
 * 说明：不允许修改给定的链表。
 * 
 * 
 * 
 * @author zm
 *
 */
public class LEET142 {
	public static void main(String[] args) {
		LEET142 l142 = new LEET142();
		String head = "[3,2,0,-4]";
		// String head1 = "[3]";
		ListNode node1 = CommonFunctions.stringToListNodeWithCircle(head, 1);
		ListNode nodeans = l142.detectCycle2(node1);
		System.out.println(CommonFunctions.findIndexOfNode(node1, nodeans));
	}

	/**
	 * 最容易想到的利用集合保存每个节点
	 * 
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {
		if (head == null) {
			return null;
		}
		HashSet<ListNode> set = new HashSet<>();
		while (head.next != null) {
			if (set.contains(head)) {
				return head;
			} else {
				set.add(head);
				head = head.next;
			}
		}
		return null;
	}

	/**
	 * 从142题的方法可以得知快指针和慢指针最终会相遇在某一点 </br>
	 * 如 1->2->3->4->2 </br>
	 * 当快慢指针相遇时，快指针比慢指针多走的路程就是环的长度，假设在走了n步，即慢指针n步，快指针2n步的时候相遇，可知环的大小为q=2n-n=n，则从1-->4可以拆分为1-->2和环2-3-4两个部分。</br>
	 * 设前面一个部分为k，那个从1-->4的长度就为k+q；同时，慢指针走了n步之后要走到最后一个节点所需的步数设为i，则1-->4的长度为n+i。所以k+q=n+i===>k=i</br>
	 * 所以从相遇的那个点开始和从头部开始，会在相同的步数后相遇在环的开始点。</br>
	 * 此例中 相遇在4，4走一步就到2,1也是走一步到2</br>
	 * 如 1->2->3->4->5->3 </br>
	 * 此例中 相遇在4，4走两步就到3,1也是走两步到3</br>
	 * 
	 * @param head
	 * @return
	 */
	public ListNode detectCycle2(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode head3 = head;
		ListNode head2 = head;
		while (head2.next != null && head2.next.next != null) {
			head = head.next;
			head2 = head2.next.next;
			if (head == head2) {
				// 获得相遇的点
				while (head3 != head) {
					head3 = head3.next;
					head = head.next;

				}
				return head;
			}
		}
		return null;
	}
}

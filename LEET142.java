package LeetCode;

import java.util.HashSet;

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
		ListNode node1 = CommonFunctions.stringToListNodeWithCircle(head, 2);
		ListNode nodeans = l142.detectCycle(node1);
		System.out.println(CommonFunctions.findIndexOfNode(node1, nodeans));
	}

	/**
	 * 最容易想到的利用集合保存每个节点
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
	
	public ListNode detectCycle2(ListNode head) {
		
	}
}

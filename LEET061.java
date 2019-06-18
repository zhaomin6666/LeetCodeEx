package com.zm.LeetCodeEx;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 
 * 示例 1:
 * 
 * 输入: 1->2->3->4->5->NULL, k = 2 输出: 4->5->1->2->3->NULL 解释: 向右旋转 1 步:
 * 5->1->2->3->4->NULL 向右旋转 2 步: 4->5->1->2->3->NULL
 * 
 * @author zm
 *
 */
public class LEET061 {
	public static void main(String[] args) {
		LEET061 l061 = new LEET061();
		ListNode node1 = CommonFunctions.stringToListNode("[1,2,3]");
		System.out.println(CommonFunctions.listNodeToString(l061.rotateRight(node1, 3)));
	}

	/**
	 * 先求出链表长度，这样可以用k除长度取余来获得真正的移动步数<br>
	 * 形成循环链表<br>
	 * 根据移动的步数获取移动后的尾节点，然后断开链表<br>
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null || k == 0)
			return head;
		int len = 1;
		ListNode pre = head;
		while (pre.next != null) {
			len++;
			pre = pre.next;
		}
		pre.next = head;
		k = k % len;
		for (int i = 0; i < len - k; i++) {
			pre = pre.next;
		}
		ListNode ret = pre.next;// 新的头节点
		pre.next = null;// 断开环形链表
		return ret;
	}
}

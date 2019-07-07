package com.zm.LeetCodeEx;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * @author zm
 */
public class LEET024 {
    public static void main(String[] args) {
        LEET024 l024 = new LEET024();
        ListNode node = CommonFunctions.stringToListNode("[1,2,3,4,5]");
        System.out.println(CommonFunctions.listNodeToString(l024.swapPairs2(node)));
    }

    /**
     * 维护4个标识，用来记录前一个节点，需要交换的两个节点和后面一个节点，然后进行交换操作
     *
     * @param head
     * @return
     */
    private ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode fast = dummyNode;
        while (fast.next != null && fast.next.next != null) {
            ListNode preNode = fast;
            ListNode slow = fast.next;
            fast = slow.next;
            ListNode nextNode = fast.next;
            preNode.next = fast;
            fast.next = slow;
            slow.next = nextNode;
            fast = slow;
        }
        return dummyNode.next;
    }

    /**
     * 减少局部变量的使用，节约空间
     *
     * @param head
     * @return
     */
    private ListNode swapPairs2(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode fast = dummyNode;
        while (fast.next != null && fast.next.next != null) {
            ListNode preNode = fast;
            ListNode slow = fast.next;
            fast = slow.next;
            slow.next = fast.next;
            preNode.next = fast;
            fast.next = slow;
            fast = slow;
        }
        return dummyNode.next;
    }
}

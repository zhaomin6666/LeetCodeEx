package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.List;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author zm
 */
public class LEET025 {
    public static void main(String[] args) {
        LEET025 l025 = new LEET025();
        ListNode node = CommonFunctions.stringToListNode("[1,2,3,4,5,6]");
        System.out.println(CommonFunctions.listNodeToString(l025.reverseKGroup2(node, 2)));
    }

    /**
     * 先检查是否有k各节点，如果存在则翻转
     *
     * @param head
     * @param k
     * @return
     */
    private ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode nodeHead = dummyNode;
        int cnt = 1;
        while (nodeHead.next != null) {
            ListNode preNode = nodeHead;
            ListNode lastNode = preNode.next;
            ListNode checkNode = nodeHead.next;
            while (checkNode.next != null) {
                checkNode = checkNode.next;
                if (++cnt == k) {
                    break;
                }
            }
            if (cnt < k) {
                break;
            } else {
                cnt = 1;
            }
            ListNode nextNode = checkNode.next;
            checkNode.next = null;
            ListNode firstNode = reverse(preNode.next);
            preNode.next = firstNode;
            lastNode.next = nextNode;
            nodeHead = lastNode;
        }
        return dummyNode.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 优化
     *
     * @param head
     * @param k
     * @return
     */
    private ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode preNode = dummyNode;
        ListNode lastNode = dummyNode;
        while (lastNode.next != null) {
            // 同样是检测是否符合有k个结点
            for (int i = 0; i < k && lastNode != null; i++) {
                lastNode = lastNode.next;
            }
            if (lastNode == null) {
                break;
            }
            // 记录下一个结点
            ListNode nextNode = lastNode.next;
            // 获取第一个结点
            ListNode startNode = preNode.next;
            // 截取需要翻转的链表
            lastNode.next = null;
            // 执行翻转并拼接到上一个节点上
            preNode.next = reverse(startNode);
            // 原来的第一个节点被翻转之后是最后一个节点，把后面的链表接上
            startNode.next = nextNode;
            // 初始化下一次循环需要的结点，这一部分的最后一个节点即是下一部分的上一个节点
            lastNode = startNode;
            preNode = startNode;
        }
        return dummyNode.next;
    }
}

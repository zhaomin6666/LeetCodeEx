package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
 *
 * @author zm
 */
public class LEET021 {
    public static void main(String[] args) {
        LEET021 l021 = new LEET021();
        ListNode<Integer> l1 = CommonFunctions.stringToListNode("[1,2,4]");
        ListNode<Integer> l2 = CommonFunctions.stringToListNode("[1,3,4]");
        System.out.println(CommonFunctions.listNodeToString(l021.new Solution().mergeTwoLists(l1, l2)));
    }

    class Solution {
        /**
         * 取两个链表中比较小的那个节点，然后该链表向后移一位
         *
         * @param l1
         * @param l2
         * @return
         */
        public ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
            ListNode<Integer> head = new ListNode<>(0);
            ListNode<Integer> node = head;
            while (l1 != null || l2 != null) {
                if (l1 == null) {
                    node.next = l2;
                    l2 = l2.next;
                } else if (l2 == null) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    if (l1.val > l2.val) {
                        node.next = l2;
                        l2 = l2.next;
                    } else {
                        node.next = l1;
                        l1 = l1.next;
                    }

                }
                node = node.next;
            }
            return head.next;
        }
    }

    /**
     * 换一种写法
     */
    class Solution2 {
        public ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
            ListNode<Integer> dummyHead = new ListNode<>(0);
            ListNode<Integer> node = dummyHead;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    l2 = l2.next;
                }
                node = node.next;
            }
            node.next = l1 == null ? l2 : l1;
            return dummyHead.next;
        }
    }

    /**
     * 使用递归
     */
    class Solution3 {
        public ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
            if (null == l1) {
                return l2;
            }
            if (null == l2) {
                return l1;
            }
            ListNode<Integer> node;
            if (l1.val <= l2.val) {
                node = l1;
                node.next = mergeTwoLists(l1.next, l2);
            } else {
                node = l2;
                node.next = mergeTwoLists(l1, l2.next);
            }
            return node;
        }
    }
}

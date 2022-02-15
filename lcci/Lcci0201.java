package com.zm.LeetCodeEx.lcci;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

import java.util.HashSet;

/**
 * 面试题 02.01. 移除重复节点
 * <p>
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * <p>
 * 示例1:
 * <p>
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 * 示例2:
 * <p>
 * 输入：[1, 1, 1, 1, 2]
 * 输出：[1, 2]
 * 提示：
 * <p>
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * <p>
 * 如果不得使用临时缓冲区，该怎么解决？
 *
 * @author zm
 */
public class Lcci0201 {
    public static void main(String[] args) {
        System.out.println(CommonFunctions.listNodeToString(new Solution2().removeDuplicateNodes(
                CommonFunctions.stringToListNode("[1, 2, 3, 3, 2, 1]")
        )));
    }

    static class Solution {
        /**
         * 用Hash表记录出现过的节点。记录前驱节点，用于删除节点
         *
         * @param head 头结点
         * @return 处理完成的链表
         */
        public ListNode removeDuplicateNodes(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            HashSet<Integer> intSet = new HashSet<>();
            intSet.add(head.val);
            ListNode preNode = head;
            ListNode cursor = head.next;
            while (cursor != null) {
                if (!intSet.contains(cursor.val)) {
                    intSet.add(cursor.val);
                    preNode = cursor;
                }
                else {
                    preNode.next = cursor.next;
                }
                cursor = cursor.next;
            }
            return head;
        }
    }

    static class Solution2 {
        /**
         * 不用缓冲区，那只能用双循环
         *
         * @param head 头结点
         * @return 处理完成的链表
         */
        public ListNode removeDuplicateNodes(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode cursor = head;
            while (cursor != null) {
                ListNode preNode = cursor;
                ListNode cursorInside = cursor.next;
                while (cursorInside != null) {
                    if (cursorInside.val == cursor.val) {
                        preNode.next = cursorInside.next;
                    }
                    else {
                        preNode = cursorInside;
                    }
                    cursorInside = cursorInside.next;
                }
                cursor = cursor.next;
            }
            return head;
        }
    }
}

package com.zm.LeetCodeEx.algorithms.ex801_900;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 876. 链表的中间结点
 * <p>
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * 示例 2：
 * <p>
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *  
 * <p>
 * 提示：
 * <p>
 * 给定链表的结点数介于 1 和 100 之间。
 *
 * @author zm
 */
public class LEET876 {
    public static void main(String[] args) {
        LEET876 l876 = new LEET876();
        System.out.println(CommonFunctions.listNodeToString(l876.new Solution().middleNode(
                CommonFunctions.stringToListNode("[1,2,3,4,5]"))));
        System.out.println(CommonFunctions.listNodeToString(l876.new Solution().middleNode(
                CommonFunctions.stringToListNode("[1,2,3,4,5,6]"))));
    }

    class Solution {
        public ListNode<Integer> middleNode(ListNode<Integer> head) {
            ListNode<Integer> fast = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                head = head.next;
            }
            return head;
        }
    }
}

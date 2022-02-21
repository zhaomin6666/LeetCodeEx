package com.zm.LeetCodeEx.weekcontest.contest_281_20220220;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 6013. 合并零之间的节点
 * <p>
 * 给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。链表的 开端 和 末尾 的节点都满足 Node.val == 0 。
 * <p>
 * 对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。然后将所有 0 移除，修改后的链表不应该含有任何 0 。
 * <p>
 * 返回修改后链表的头节点 head 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [0,3,1,0,4,5,2,0]
 * 输出：[4,11]
 * 解释：
 * 上图表示输入的链表。修改后的链表包含：
 * - 标记为绿色的节点之和：3 + 1 = 4
 * - 标记为红色的节点之和：4 + 5 + 2 = 11
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [0,1,0,3,0,2,2,0]
 * 输出：[1,3,4]
 * 解释：
 * 上图表示输入的链表。修改后的链表包含：
 * - 标记为绿色的节点之和：1 = 1
 * - 标记为红色的节点之和：3 = 3
 * - 标记为黄色的节点之和：2 + 2 = 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 列表中的节点数目在范围 [3, 2 * 105] 内
 * 0 <= Node.val <= 1000
 * 不 存在连续两个 Node.val == 0 的节点
 * 链表的 开端 和 末尾 节点都满足 Node.val == 0
 */
public class LEET6013 {
    public static void main(String[] args) {
        System.out.println(CommonFunctions.listNodeToString(
                new Solution().mergeNodes(CommonFunctions.stringToListNode("[0,3,1,0,4,5,2,0]"))));
        System.out.println(CommonFunctions.listNodeToString(
                new Solution().mergeNodes(CommonFunctions.stringToListNode("[0,1,0,3,0,2,2,0]"))));
    }

    /**
     * 遍历节点
     */
    static class Solution {
        public ListNode mergeNodes(ListNode head) {
            // 因为head最后会被删掉，用一个虚拟节点存一下
            ListNode dummyHead = new ListNode();
            dummyHead.next = head;
            // 记录0前面的一个节点，用于删除0
            ListNode nodeBeforeLastZero = dummyHead;
            // ListNode lastZero = head;
            ListNode currentNode = head;
            int currentSum = 0;
            while (currentNode.next != null) {
                // 如果下一个是0，那么生成一个新节点，并删除上一个0节点
                if (currentNode.next.val == 0) {
                    // 生成一个新节点
                    ListNode newNode = new ListNode(currentSum);
                    // 删除上一个0节点，和中间的所有节点
                    nodeBeforeLastZero.next = newNode;
                    // 把新节点加入链表
                    newNode.next = currentNode.next;
                    // 更新值
                    nodeBeforeLastZero = newNode;
                    currentSum = 0;
                    currentNode = newNode;
                }
                else {
                    currentSum += currentNode.next.val;
                }
                // 如果下一个节点的下一个节点是空，那么直接删除下一个节点0并返回结果。
                if (currentNode.next.next == null) {
                    currentNode.next = null;
                    break;
                }
                currentNode = currentNode.next;
            }
            return dummyHead.next;
        }
    }
}

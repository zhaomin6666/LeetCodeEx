package com.zm.LeetCodeEx;

/**
 * 周赛 2019年12月15日  5283. 二进制链表转整数
 * <p>
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 * <p>
 * 请你返回该链表所表示数字的 十进制值 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,0,1]
 * 输出：5
 * 解释：二进制数 (101) 转化为十进制数 (5)
 * 示例 2：
 * <p>
 * 输入：head = [0]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：head = [1]
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * 输出：18880
 * 示例 5：
 * <p>
 * 输入：head = [0,0]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表不为空。
 * 链表的结点总数不超过 30。
 * 每个结点的值不是 0 就是 1。
 *
 * @author zm
 */
public class LEET5283 {
    public static void main(String[] args) {
        LEET5283 l5283 = new LEET5283();
        String headStr = "[1,0,1]";
        ListNode head = CommonFunctions.stringToListNode(headStr);
        System.out.println(l5283.getDecimalValue(head));

    }

    public int getDecimalValue(ListNode head) {
        int ret = 0;
        ListNode cur = head;
        while (cur != null) {
            ret = (ret << 1) + cur.val;
            cur = cur.next;
        }
        return ret;
    }

}

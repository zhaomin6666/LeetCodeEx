package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 * <p>
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1: <br>
 * 输入: 1->1->2 <br>
 * 输出: 1->2
 * <p>
 * 示例 2: <br>
 * 输入: 1->1->2->3->3 <br>
 * 输出: 1->2->3
 * 
 * @author zm
 */
public class LEET083 {
	public static void main(String[] args) {
		LEET083 l082 = new LEET083();
		System.out.println(CommonFunctions.listNodeToString(
				l082.new Solution().deleteDuplicates(CommonFunctions.stringToListNode("[1,2,3,3,4,4,5]"))));
		System.out.println(CommonFunctions.listNodeToString(
				l082.new Solution().deleteDuplicates(CommonFunctions.stringToListNode("[1,1,1,2,3]"))));
		System.out.println(CommonFunctions.listNodeToString(
				l082.new Solution().deleteDuplicates(CommonFunctions.stringToListNode("[1,1,1,2,2,3,3]"))));
		System.out.println(CommonFunctions.listNodeToString(
				l082.new Solution().deleteDuplicates(CommonFunctions.stringToListNode("[1,1,2,2,2,2,2,3,4,4,5]"))));
	}

	class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode temp = head;
            while (temp.next != null) {
                if (temp.next.val == temp.val) {
                    temp.next = temp.next.next;
                }
                else {
                    temp = temp.next;
                }
            }
            return head;
        }
    }
}

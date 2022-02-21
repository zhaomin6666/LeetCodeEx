package com.zm.LeetCodeEx.weekcontest.contest_267_20211114;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 *
 */
public class LEET5927 {
    public static void main(String[] args) {
        LEET5927 leet5927 = new LEET5927();
        System.out.println(CommonFunctions.listNodeToString(leet5927.new Solution()
                .reverseEvenLengthGroups(CommonFunctions.stringToListNode("[1,1,0,6]"))));
    }


    class Solution {
        public ListNode reverseEvenLengthGroups(ListNode head) {
            ListNode curNode = head;
            int curMaxLength = 2;
            int curLength = 0;
            ListNode curBeforeNode = null;
            while(curNode != null){
                if(curBeforeNode == null){
                    // 第一个节点
                    curBeforeNode = curNode;
                    curNode = curNode.next;
                }
                else {
                    if (++curLength == curMaxLength){
                        ListNode willBeLastNode = curBeforeNode.next;
                        reverseSubNode(curBeforeNode, curNode);
                        curLength = 0;
                        curMaxLength++;
                        curBeforeNode = willBeLastNode;
                        curNode = curBeforeNode.next;
                        System.out.println(CommonFunctions.listNodeToString(head));
                    }
                    else {
                        if (curNode.next == null && curLength % 2 == 0){
                            reverseSubNode(curBeforeNode, curNode);
                            return head;
                        }
                        curNode = curNode.next;
                    }
                }
            }
            return head;
        }

        public void reverseSubNode(ListNode beforeNode, ListNode lastNode){
            System.out.println("翻转["+beforeNode.next.val+","+lastNode.val+"]");
            ListNode afterNode = lastNode.next;
            ListNode firstNode = beforeNode.next;
            ListNode prev = null;
            ListNode curr = beforeNode.next;
            while (curr != null && curr != lastNode) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            lastNode.next = prev;
            firstNode.next = afterNode;
            beforeNode.next = lastNode;
        }
    }
}

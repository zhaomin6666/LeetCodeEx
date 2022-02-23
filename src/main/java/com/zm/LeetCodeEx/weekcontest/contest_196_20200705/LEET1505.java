package com.zm.LeetCodeEx.weekcontest.contest_196_20200705;

import java.util.PriorityQueue;

/**
 * 1505. 最多 K 次交换相邻数位后得到的最小整数
 * 给你一个字符串 num 和一个整数 k 。其中，num 表示一个很大的整数，字符串中的每个字符依次对应整数上的各个 数位 。
 * <p>
 * 你可以交换这个整数相邻数位的数字 最多 k 次。
 * <p>
 * 请你返回你能得到的最小整数，并以字符串形式返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：num = "4321", k = 4
 * 输出："1342"
 * 解释：4321 通过 4 次交换相邻数位得到最小整数的步骤如上图所示。
 * 示例 2：
 * <p>
 * 输入：num = "100", k = 1
 * 输出："010"
 * 解释：输出可以包含前导 0 ，但输入保证不会有前导 0 。
 * 示例 3：
 * <p>
 * 输入：num = "36789", k = 1000
 * 输出："36789"
 * 解释：不需要做任何交换。
 * 示例 4：
 * <p>
 * 输入：num = "22", k = 22
 * 输出："22"
 * 示例 5：
 * <p>
 * 输入：num = "9438957234785635408", k = 23
 * 输出："0345989723478563548"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= num.length <= 30000
 * num 只包含 数字 且不含有 前导 0 。
 * 1 <= k <= 10^9
 */
public class LEET1505 {

    public static void main(String[] args) {
        LEET1505 leet1504 = new LEET1505();
        System.out.println(leet1504.new Solution().minInteger("4321", 4));
    }

    /**
     * 找到最小数放到最前面代价最小的那个
     * 如"4321"
     * 把1放到第1个需要换3次。而总数是4，满足条件。
     * 把1移到第1个，其他3个数字向后移。
     * k-=3 --> k=1
     * "4321" --> "1432"
     * 把2放到第2个需要换2次。而总数是1，不满足条件。
     * 把3放到第2个需要换1次。而总数是1，满足条件。
     * k-=1 --> k=0
     * "4321" --> "1342"
     *
     * 超时
     * 好像直接暴力就是O(n^2)
     */
    class Solution {
        public String minInteger(String num, int k) {
            char[] cs = num.toCharArray();
            int index = 0;
            while (k > 0) {
                PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                    if (o1[0] == o2[0]) {
                        return o1[1] - o2[1];
                    }
                    return o1[0] - o2[0];
                });
                for (int i = index; i < cs.length; i++) {
                    pq.add(new int[]{cs[i], i});
                }
                if (pq.isEmpty()) {
                    break;
                }
                int[] t = pq.poll();
                while (t[1] - index > k) {
                    t = pq.poll();
                }
                k -= t[1] - index;
                for (int i = t[1]; i > index; i--) {
                    cs[i] = cs[i - 1];
                }
                cs[index] = (char) t[0];
                index++;
            }
            return new String(cs);
        }
    }

    /**
     * 官方题解  树状数组
     */
    class Solution2 {

    }
}

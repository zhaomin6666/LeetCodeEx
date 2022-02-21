package com.zm.LeetCodeEx.weekcontest.contest_281_20220220;

import java.util.PriorityQueue;

/**
 * 6014. 构造限制重复的字符串
 * 给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，使任何字母 连续 出现的次数都不超过 repeatLimit 次。你不必使用 s 中的全部字符。
 * <p>
 * 返回 字典序最大的 repeatLimitedString 。
 * <p>
 * 如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，则认为字符串 a 比字符串 b 字典序更大 。如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "cczazcc", repeatLimit = 3
 * 输出："zzcccac"
 * 解释：使用 s 中的所有字符来构造 repeatLimitedString "zzcccac"。
 * 字母 'a' 连续出现至多 1 次。
 * 字母 'c' 连续出现至多 3 次。
 * 字母 'z' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "zzcccac" 。
 * 注意，尽管 "zzcccca" 字典序更大，但字母 'c' 连续出现超过 3 次，所以它不是一个有效的 repeatLimitedString 。
 * 示例 2：
 * <p>
 * 输入：s = "aababab", repeatLimit = 2
 * 输出："bbabaa"
 * 解释：
 * 使用 s 中的一些字符来构造 repeatLimitedString "bbabaa"。
 * 字母 'a' 连续出现至多 2 次。
 * 字母 'b' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "bbabaa" 。
 * 注意，尽管 "bbabaaa" 字典序更大，但字母 'a' 连续出现超过 2 次，所以它不是一个有效的 repeatLimitedString 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= repeatLimit <= s.length <= 105
 * s 由小写英文字母组成
 */
public class LEET6014 {
    public static void main(String[] args) {
        System.out.println(new Solution().repeatLimitedString("zzcccac", 3));
        System.out.println(new Solution().repeatLimitedString("bbabaa", 2));
        System.out.println(new Solution().repeatLimitedString("robnsdvpuxbapuqgopqvxdrchivlifeepy", 2));
    }

    /**
     * 优先队列优先字典序较大的字母排在前面，使用变量保存需要等待一轮的字母
     */
    static class Solution {
        public String repeatLimitedString(String s, int repeatLimit) {
            // 保存每个字母出现次数
            int[] cnt = new int[26];
            char[] cs = s.toCharArray();
            for (char c : cs) {
                cnt[c - 'a']++;
            }
            // 优先队列保存用于拼接字符串的顺序
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int i = 0; i < 26; i++) {
                if (cnt[i] > 0) {
                    pq.add(i);
                }
            }
            // 需要等待一轮的字母
            int wait = -1;
            StringBuilder sb = new StringBuilder();
            while (!pq.isEmpty()) {
                int poll = pq.poll();
                boolean needWait = false;
                if (wait < poll) {
                    // 当没有等待字母的时候，或者等待字母的值也比当前取出来的值小，可以直接把当前值最大可能的拼到字符串中
                    for (int i = 1; i <= Math.min(cnt[poll], repeatLimit); i++) {
                        sb.append((char) (poll + 'a'));
                    }
                    // 如果超过限制个数，则加入等待
                    if (cnt[poll] > repeatLimit) {
                        cnt[poll] -= repeatLimit;
                        needWait = true;
                    }
                }
                else {
                    // 等待的值字典序把当前大的时候，当前值就放一个，只是起到分割作用
                    sb.append((char) (poll + 'a'));
                    // 剩下没用完的话，加入等待
                    if (--cnt[poll] > 0) {
                        needWait = true;
                    }
                }
                // 如果上一轮有等待的，将等待字母加入队列
                if (wait != -1) {
                    pq.add(wait);
                }
                // 当前如果需要等待则赋值，否则还原等待变量
                wait = needWait ? poll : -1;
            }
            return sb.toString();
        }
    }
}

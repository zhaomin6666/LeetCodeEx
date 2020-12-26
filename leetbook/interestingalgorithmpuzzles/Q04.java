package com.zm.LeetCodeEx.leetbook.interestingalgorithmpuzzles;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ：zm
 * @date ：Created in 2020-12-24 14:50
 * <p>
 * 假设要把长度为 n 厘米的木棒切分为 1 厘米长的小段，但是 1 根木棒只能由 1 人切分，当木棒被切分为 3 段后，可以同时由 3 个人分别切分木棒（图 2）。
 * <p>
 * 图 2　n ＝ 8，m ＝ 3 的时候
 * <p>
 * 求最多有 m 个人时，最少要切分几次。譬如 n ＝ 8，m＝ 3 时如图所示，切分 4 次就可以了。
 * <p>
 * 作者：图灵教育
 * 链接：https://leetcode-cn.com/leetbook/read/interesting-algorithm-puzzles-for-programmers/90ach5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Q04 {
    public static void main(String[] args) {
        Q04 q04 = new Q04();
        System.out.println(q04.new Solution().cutBar(8, 3));
        System.out.println(q04.new Solution().cutBar(20, 3));
        System.out.println(q04.new Solution().cutBar(100, 5));
        System.out.println(q04.new Solution2().cutBar(8, 3));
        System.out.println(q04.new Solution2().cutBar(20, 3));
        System.out.println(q04.new Solution2().cutBar(100, 5));

    }

    /**
     * 每一轮切分切一半，把长度超过1的木棍放回待处理的木棍中
     */
    class Solution {
        public int cutBar(int lengthOfBar, int numberOfPeople) {
            int ret = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(lengthOfBar);
            while (!queue.isEmpty()) {
                int queueSize = queue.size();
                ret++;
                for (int i = 0; i < queueSize && i < numberOfPeople; i++) {
                    int cur = queue.poll();
                    int div1 = cur / 2;
                    int div2 = cur - div1;
                    if (div1 > 1) {
                        queue.add(div1);
                    }
                    if (div2 > 1) {
                        queue.add(div2);
                    }
                }
            }
            return ret;
        }
    }

    /**
     * 递归调用
     * 1.在人员足够的情况下，木棒每一轮会被分成两份，也就是数量*2
     * 2.在人员不够的情况下，木棒每一轮会新增等同人员数量的数量
     */
    class Solution2 {
        public int cutBar(int lengthOfBar, int numberOfPeople) {
            return cutBar(lengthOfBar, numberOfPeople, 1);
        }

        private int cutBar(int lengthOfBar, int numberOfPeople, int curNumberOfBar) {
            if (curNumberOfBar >= lengthOfBar) {
                // 由于可能会有长度奇数情况，如3分成1和2，理论上1是不能再分的，但是在做木棒数量*2的时候2根木棒被分成了4根
                // 这时候就会有>的可能
                return 0;
            }
            if (curNumberOfBar <= numberOfPeople) {
                return 1 + cutBar(lengthOfBar, numberOfPeople, curNumberOfBar * 2);
            }
            return 1 + cutBar(lengthOfBar, numberOfPeople, curNumberOfBar + numberOfPeople);
        }
    }
}

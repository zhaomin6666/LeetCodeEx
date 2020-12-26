package com.zm.LeetCodeEx.leetbook.interestingalgorithmpuzzles;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ：zm
 * @date ：Created in 2020-12-24 14:50
 * Q18 水果酥饼日
 * <p>
 * 日本每月的 22 日是水果酥饼日。因为看日历的时候，22 日的上方刚好是 15 日，也就是“‘22’ 这个数字上面点缀着草莓”[1]
 * <p>
 * 切分酥饼的时候，要求切分后每一块上面的草莓个数都不相同。假设切分出来的 N 块酥饼上要各有“1~N 个（共 N(N + 1)÷2 个草莓）”。
 * <p>
 * 但这里要追加一个条件，那就是“一定要使相邻的两块酥饼上的数字之和是平方数”。
 * <p>
 * 举个例子，假设 N ＝ 4 时采用如 图 4 的切法。这时，虽然 1 + 3 ＝ 4 得到的是平方数，但“1 和 4” “2 和 3” “2 和 4”的部分都不满足条件（图 4）。
 * <p>
 * 单选题
 * 求可以使切法满足条件的最小的 N（N ＞ 1）。
 * <p>
 * 作者：图灵教育
 * 链接：https://leetcode-cn.com/leetbook/read/interesting-algorithm-puzzles-for-programmers/97r135/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Q18 {
    public static void main(String[] args) {
        Q18 q18 = new Q18();
        System.out.println(q18.new Solution().minimalFruitPie().size());
    }

    /**
     *
     */
    class Solution {
        // 记录无向图
        List<Integer>[] edges;
        // 记录搜索过程中每个数是否被使用过
        boolean[] used;
        // 记录搜索过程中的序列
        List<Integer> ans = new ArrayList<Integer>();
        // 是否找到答案
        boolean found = false;

        public List<Integer> minimalFruitPie() {
            // 从小到大枚举 n
            for (int n = 2;; ++n) {
                edges = new List[n + 1];
                for (int i = 0; i <= n; ++i) {
                    edges[i] = new ArrayList<Integer>();
                }
                // 建立无向图
                for (int i = 1; i <= n; ++i) {
                    // 枚举平方数 root^2
                    for (int root = 1; root * root <= i + n; ++root) {
                        int j = root * root - i;
                        if (j > i) {
                            edges[i].add(j);
                            edges[j].add(i);
                        }
                    }
                }
                used = new boolean[n + 1];
                dfs(0, n);
                if (found) {
                    return ans;
                }
            }
        }

        public void dfs(int pos, int n) {
            if (pos == n) {
                // 搜索完成，还需要判断首尾之和是否为完全平方数
                int sum = ans.get(0) + ans.get(ans.size() - 1);
                int root = (int) Math.sqrt(sum);
                if (root * root == sum) {
                    found = true;
                }
            } else if (pos == 0) {
                // 由于酥饼是圆形，因此可以指定第一个数选择 1
                used[1] = true;
                ans.add(1);
                dfs(pos + 1, n);
                if (found) {
                    return;
                }
                used[1] = false;
                ans.remove(ans.size() - 1);
            } else {
                // 只能选与上一个数形成完全平方数，并且还未被选择的数
                // 上一个数：ans.get(ans.size() - 1)
                for (int num : edges[ans.get(ans.size() - 1)]) {
                    if (!used[num]) {
                        used[num] = true;
                        ans.add(num);
                        dfs(pos + 1, n);
                        if (found) {
                            return;
                        }
                        used[num] = false;
                        ans.remove(ans.size() - 1);
                    }
                }
            }
        }
    }
}

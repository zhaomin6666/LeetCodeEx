package com.zm.LeetCodeEx.algorithms.ex201_300;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @author zm
 */
public class LEET216 {
    public static void main(String[] args) {
        LEET216 l210 = new LEET216();
        System.out.println(JSON.toJSONString(l210.new Solution().combinationSum3(3, 7)));
        System.out.println(JSON.toJSONString(l210.new Solution().combinationSum3(3, 9)));

    }

    class Solution {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> ret = new ArrayList<>();
            dfs(k, n, 1, ret, new ArrayList<>());
            return ret;
        }

        private void dfs(int k, int n, int start, List<List<Integer>> ret, List<Integer> cur) {
            for (int i = start; i <= 10 - k && i <= n; i++) {
                int left = n - i;
                if (left == 0 && k == 1) {
                    List<Integer> item = new ArrayList<>(cur);
                    item.add(i);
                    ret.add(item);
                }
                if (left != 0 && k > 1) {
                    cur.add(i);
                    dfs(k - 1, left, i + 1, ret, cur);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }
}

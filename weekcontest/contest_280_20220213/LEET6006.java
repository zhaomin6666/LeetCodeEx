package com.zm.LeetCodeEx.weekcontest.contest_280_20220213;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LEET6006 {
    public static void main(String[] args) {
        System.out.println(new Solution2().minimumRemoval(new int[]{4, 1, 6, 5}));
        System.out.println(new Solution2().minimumRemoval(new int[]{2, 10, 3, 2}));
        System.out.println(new Solution2().minimumRemoval(new int[]{Integer.MAX_VALUE - 10, 11}));
    }

    /**
     * 遍历出现的数字，对于某一数字再遍历其他数字，如果是比该数字大则减到这个数字，比这个数字小则删到0。
     * O(n^2)超时
     */
    static class Solution {
        public long minimumRemoval(int[] beans) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int bean : beans) {
                map.put(bean, map.getOrDefault(bean, 0) + 1);
            }
            int min = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int cnt = 0;
                for (Map.Entry<Integer, Integer> entry2 : map.entrySet()) {
                    if (entry2.getKey() > entry.getKey()) {
                        cnt += entry2.getValue() * (entry2.getKey() - entry.getKey());
                    }
                    else if (entry2.getKey() < entry.getKey()) {
                        cnt += entry2.getValue() * entry2.getKey();
                    }
                }
                min = Math.min(min, cnt);
            }
            return min;
        }
    }

    /**
     * 先进行排序，这样对于某个数字遍历其他数字的时候前面遍历过的直接就删除，不用再去遍历了，后面的可以用总数来减，也不用遍历。
     */
    static class Solution2 {
        public long minimumRemoval(int[] beans) {
            // 获取总数
            long sum = 0;
            for (int bean : beans) {
                sum += bean;
            }

            // 排序
            Arrays.sort(beans);

            long min = Long.MAX_VALUE;
            // 开始遍历数字
            for (int i = 0; i < beans.length; i++) {
                int bean = beans[i];
                min = Math.min(min, sum - ((long) (beans.length - i)) * bean);
            }
            return min;
        }
    }
}

package com.zm.LeetCodeEx.weekcontest.contest_193_20200614;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 周赛 2020年6月14日
 * <p>
 * 5437. 不同整数的最少数目
 * <p>
 * 给你一个整数数组 arr 和一个整数 k 。现需要从数组中恰好移除 k 个元素，请找出移除后数组中不同整数的最少数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [5,5,4], k = 1
 * 输出：1
 * 解释：移除 1 个 4 ，数组中只剩下 5 一种整数。
 * 示例 2：
 * <p>
 * 输入：arr = [4,3,1,1,3,3,2], k = 3
 * 输出：2
 * 解释：先移除 4、2 ，然后再移除两个 1 中的任意 1 个或者三个 3 中的任意 1 个，最后剩下 1 和 3 两种整数。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 *
 * @author zm
 */
public class LEET5437 {
    public static void main(String[] args) {
        LEET5437 l5437 = new LEET5437();
        System.out.println(l5437.new Solution().findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 3));
    }

    class Solution {
        public int findLeastNumOfUniqueInts(int[] arr, int k) {
            if (k >= arr.length) {
                return 0;
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                int key = arr[i];
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                pq.add(new int[]{entry.getKey(), entry.getValue()});
            }
            int ret = map.size();
            while (k > 0) {
                if (pq.isEmpty()) {
                    return 0;
                }
                int[] ele = pq.poll();
                k -= ele[1];
                if (k >= 0) {
                    ret--;
                }
            }
            return ret;
        }
    }
}

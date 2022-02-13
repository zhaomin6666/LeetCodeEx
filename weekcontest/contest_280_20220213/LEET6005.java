package com.zm.LeetCodeEx.weekcontest.contest_280_20220213;

import java.util.*;

/**
 * 6005. 使数组变成交替数组的最少操作数
 * 给你一个下标从 0 开始的数组 nums ，该数组由 n 个正整数组成。
 * <p>
 * 如果满足下述条件，则数组 nums 是一个 交替数组 ：
 * <p>
 * nums[i - 2] == nums[i] ，其中 2 <= i <= n - 1 。
 * nums[i - 1] != nums[i] ，其中 1 <= i <= n - 1 。
 * 在一步 操作 中，你可以选择下标 i 并将 nums[i] 更改 为 任一 正整数。
 * <p>
 * 返回使数组变成交替数组的 最少操作数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,1,3,2,4,3]
 * 输出：3
 * 解释：
 * 使数组变成交替数组的方法之一是将该数组转换为 [3,1,3,1,3,1] 。
 * 在这种情况下，操作数为 3 。
 * 可以证明，操作数少于 3 的情况下，无法使数组变成交替数组。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,2,2,2]
 * 输出：2
 * 解释：
 * 使数组变成交替数组的方法之一是将该数组转换为 [1,2,1,2,1].
 * 在这种情况下，操作数为 2 。
 * 注意，数组不能转换成 [2,2,2,2,2] 。因为在这种情况下，nums[0] == nums[1]，不满足交替数组的条件。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class LEET6005 {
    public static void main(String[] args) {
        System.out.println(new Solution().minimumOperations(new int[]{2, 2, 2, 2, 2}));
    }

    /**
     * 分奇偶统计数字出现次数。尽量保留出现次数最多的数字。由于可能出现奇偶数字相同的情况，所以还需要统计次数出现第二多的用于替补。
     */
    static class Solution {
        public int minimumOperations(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }
            HashMap<Integer, Integer> map1 = new HashMap<>();
            HashMap<Integer, Integer> map2 = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (i % 2 == 0) {
                    map2.put(nums[i], map2.getOrDefault(nums[i], 0) + 1);
                }
                else {
                    map1.put(nums[i], map1.getOrDefault(nums[i], 0) + 1);
                }
            }

            List<Map.Entry<Integer, Integer>> map1Sets = new ArrayList<>(map1.entrySet());
            List<Map.Entry<Integer, Integer>> map2Sets = new ArrayList<>(map2.entrySet());

            map1Sets.sort(Comparator.comparingInt(Map.Entry::getValue));
            map2Sets.sort(Comparator.comparingInt(Map.Entry::getValue));

            // System.out.println(JSON.toJSONString(map1Sets));
            // System.out.println(JSON.toJSONString(map2Sets));

            int map1max1 = map1Sets.get(map1Sets.size() - 1).getValue();
            Integer map1max2 = map1Sets.size() == 1 ? null : map1Sets.get(map1Sets.size() - 2).getValue();
            int map1max1K = map1Sets.get(map1Sets.size() - 1).getKey();

            int map2max1 = map2Sets.get(map2Sets.size() - 1).getValue();
            Integer map2max2 = map2Sets.size() == 1 ? null : map2Sets.get(map2Sets.size() - 2).getValue();
            int map2max1K = map2Sets.get(map2Sets.size() - 1).getKey();

            if (map1max1K == map2max1K) {
                if (map2max2 == null && map1max2 == null) {
                    return nums.length - Math.max(map2max1, map1max1);
                }
                if (map2max2 == null) {
                    return nums.length - map1max2 - map2max1;
                }
                if (map1max2 == null) {
                    return nums.length - map1max1 - map2max2;
                }
                return nums.length - Math.max(map1max1 + map2max2, map1max2 + map2max1);
            }
            else {
                return nums.length - map1max1 - map2max1;
            }
        }
    }
}

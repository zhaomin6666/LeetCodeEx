package com.zm.LeetCodeEx.weekcontest.before20200426;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 周赛 2020年3月1日 5344. 有多少小于当前数字的数字
 * <p>
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * <p>
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * <p>
 * 以数组形式返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 * <p>
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 *
 * @author zm
 */
public class LEET5344 {
    public static void main(String[] args) {
        LEET5344 l5344 = new LEET5344();
        System.out.println(JSON.toJSONString(l5344.new Solution().smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
        System.out.println(JSON.toJSONString(l5344.new Solution().smallerNumbersThanCurrent(new int[]{6, 5, 4, 8})));
        System.out.println(JSON.toJSONString(l5344.new Solution().smallerNumbersThanCurrent(new int[]{7, 7, 7, 7})));
    }

    class Solution {
        public int[] smallerNumbersThanCurrent(int[] nums) {
            if (nums.length == 0) return nums;
            int[] copy = Arrays.copyOf(nums, nums.length);
            Arrays.sort(copy);
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(copy[0], 0);
            for (int i = 1; i < copy.length; i++) {
                if (copy[i] == copy[i - 1]) {
                    map.put(copy[i], map.get(copy[i - 1]));
                } else {
                    map.put(copy[i], i);
                }
            }
            for (int i = 0; i < copy.length; i++) {
                copy[i] = map.get(nums[i]);
            }
            return copy;
        }
    }
}

package com.zm.LeetCodeEx.algorithms.ex501_600;

import java.util.HashMap;

/**
 * 560. 和为K的子数组
 * <p>
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * @author zm
 */
public class LEET560 {
    public static void main(String[] args) {
        LEET560 l560 = new LEET560();
        System.out.println(l560.new Solution2().subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(l560.new Solution2().subarraySum(new int[]{1, -1, 0, 1, -1, 1}, 0));
    }

    class Solution {
        public int subarraySum(int[] nums, int k) {
            int ret = 0;
            int sum;
            for (int i = 0; i < nums.length; i++) {
                sum = nums[i];
                if (sum == k) {
                    ret++;
                }
                for (int j = i + 1; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum == k) {
                        ret++;
                    }
                }
            }
            return ret;
        }
    }

    /**
     * 使用前缀和 pre[i] = sum{num[j]},j=0,1,...,i
     * 这样的话子序列 sum(num[m]),m=m1,m2,...,mn就可以用pre[mn]-pre[m1-1]表示
     * 当pre[mn]-pre[m1-1]==k时得到想要解，所以需要在pre[mn]时找到有多少个m1满足pre[m1-1]=pre[mn]-k
     */
    class Solution2 {
        public int subarraySum(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>(nums.length + 1);
            map.put(0, 1);
            int sum = 0;
            int ret = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (map.containsKey(sum - k)) {
                    ret += map.get(sum - k);
                }
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
            return ret;
        }
    }
}

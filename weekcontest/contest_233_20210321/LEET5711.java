package com.zm.LeetCodeEx.weekcontest.contest_233_20210321;

/**
 * 5711. 有界数组中指定下标处的最大值
 * <p>
 * <p>
 * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 * <p>
 * nums.length == n
 * nums[i] 是 正整数 ，其中 0 <= i < n
 * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * nums 中所有元素之和不超过 maxSum
 * nums[index] 的值被 最大化
 * 返回你所构造的数组中的 nums[index] 。
 * <p>
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, index = 2,  maxSum = 6
 * 输出：2
 * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
 * 示例 2：
 * <p>
 * 输入：n = 6, index = 1,  maxSum = 10
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= maxSum <= 109
 * 0 <= index < n
 */
public class LEET5711 {
    public static void main(String[] args) {
        LEET5711 leet5711 = new LEET5711();
        System.out.println(leet5711.new Solution().maxValue(4, 2, 6));
        System.out.println(leet5711.new Solution().maxValue(6, 1, 10));
        System.out.println(leet5711.new Solution().maxValue(4, 0, 4));
        System.out.println(leet5711.new Solution().maxValue(3, 0, 815094800));
    }


    /**
     * 二分法
     * 计算左边+计算右边 判断是否满足
     */
    class Solution {
        public int maxValue(int n, int index, int maxSum) {
            int left = 1;
            int right = maxSum;
            int suc = -1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (check(mid, n, index, maxSum)) {
                    suc = Math.max(suc, mid);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return suc;
        }

        public boolean check(int k, int n, int index, int maxSum) {
            long left = 0;
            if (k > index + 1) {
                left = ((long) ((k - 1) + (k - 1 - index))) * (index + 1) / 2;
            } else {
                left = ((long) ((k - 1) + 1)) * (k - 1) / 2;
            }
            System.out.println("left:" + left);
            long right = 0;
            if (k > n - index) {
                right = ((long) ((k - 1) + (k - 1 - (n - index - 1)))) * (n - index) / 2;
            } else {
                right = ((long) ((k - 1) + 1)) * (k - 1) / 2;
            }
            System.out.println("right:" + right);
            System.out.println("all:" + (n + left + right - (k - 1)));
            return n + left + right - (k - 1) <= maxSum;
        }
    }
}

package com.zm.LeetCodeEx.weekcontest.before20200426;

/**
 * 周赛 2020年3月29日 5369. 统计作战单位数
 * <p>
 * n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
 * <p>
 * 每 3 个士兵可以组成一个作战单位，分组规则如下：
 * <p>
 * 从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
 * 作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中  0 <= i < j < k < n
 * 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：rating = [2,5,3,4,1]
 * 输出：3
 * 解释：我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
 * 示例 2：
 * <p>
 * 输入：rating = [2,1,3]
 * 输出：0
 * 解释：根据题目条件，我们无法组建作战单位。
 * 示例 3：
 * <p>
 * 输入：rating = [1,2,3,4]
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == rating.length
 * 1 <= n <= 200
 * 1 <= rating[i] <= 10^5
 *
 * @author zm
 */
public class LEET5369 {
    public static void main(String[] args) {
        LEET5369 l5369 = new LEET5369();
        System.out.println(l5369.new Solution().numTeams(new int[]{2, 5, 3, 4, 1}));
        System.out.println(l5369.new Solution().numTeams(new int[]{2, 1, 3}));
        System.out.println(l5369.new Solution().numTeams(new int[]{1, 2, 3, 4}));
    }

    class Solution {
        int ret = 0;

        public int numTeams(int[] rating) {
            for (int i = 0; i < rating.length - 2; i++) {
                pickSecond(i + 1, rating[i], rating);
            }
            return ret;
        }

        private void pickSecond(int start, int first, int[] rating) {
            for (int i = start; i < rating.length - 1; i++) {
                pickThird(i + 1, rating[i], rating[i] > first, rating);
            }
        }

        private void pickThird(int start, int second, boolean up, int[] rating) {
            for (int i = start; i < rating.length; i++) {
                if ((up && rating[i] > second) || (!up && rating[i] < second)) {
                    ret++;
                }
            }
        }
    }
}

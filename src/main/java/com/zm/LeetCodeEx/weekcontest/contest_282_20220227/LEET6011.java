package com.zm.LeetCodeEx.weekcontest.contest_282_20220227;

import java.util.Arrays;

/**
 * 6011. 完成比赛的最少时间
 * 给你一个下标从 0 开始的二维整数数组 tires ，其中 tires[i] = [fi, ri] 表示第 i 种轮胎如果连续使用，第 x 圈需要耗时 fi * ri(x-1) 秒。
 * <p>
 * 比方说，如果 fi = 3 且 ri = 2 ，且一直使用这种类型的同一条轮胎，那么该轮胎完成第 1 圈赛道耗时 3 秒，完成第 2 圈耗时 3 * 2 = 6 秒，完成第 3 圈耗时 3 * 22 = 12 秒，依次类推。
 * 同时给你一个整数 changeTime 和一个整数 numLaps 。
 * <p>
 * 比赛总共包含 numLaps 圈，你可以选择 任意 一种轮胎开始比赛。每一种轮胎都有 无数条 。每一圈后，你可以选择耗费 changeTime 秒 换成 任意一种轮胎（也可以换成当前种类的新轮胎）。
 * <p>
 * 请你返回完成比赛需要耗费的 最少 时间。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
 * 输出：21
 * 解释：
 * 第 1 圈：使用轮胎 0 ，耗时 2 秒。
 * 第 2 圈：继续使用轮胎 0 ，耗时 2 * 3 = 6 秒。
 * 第 3 圈：耗费 5 秒换一条新的轮胎 0 ，然后耗时 2 秒完成这一圈。
 * 第 4 圈：继续使用轮胎 0 ，耗时 2 * 3 = 6 秒。
 * 总耗时 = 2 + 6 + 5 + 2 + 6 = 21 秒。
 * 完成比赛的最少时间为 21 秒。
 * 示例 2：
 * <p>
 * 输入：tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
 * 输出：25
 * 解释：
 * 第 1 圈：使用轮胎 1 ，耗时 2 秒。
 * 第 2 圈：继续使用轮胎 1 ，耗时 2 * 2 = 4 秒。
 * 第 3 圈：耗时 6 秒换一条新的轮胎 1 ，然后耗时 2 秒完成这一圈。
 * 第 4 圈：继续使用轮胎 1 ，耗时 2 * 2 = 4 秒。
 * 第 5 圈：耗时 6 秒换成轮胎 0 ，然后耗时 1 秒完成这一圈。
 * 总耗时 = 2 + 4 + 6 + 2 + 4 + 6 + 1 = 25 秒。
 * 完成比赛的最少时间为 25 秒。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= tires.length <= 10^5
 * tires[i].length == 2
 * 1 <= fi, changeTime <= 10^5
 * 2 <= ri <= 10^5
 * 1 <= numLaps <= 1000
 */
public class LEET6011 {
    public static void main(String[] args) {
        //System.out.println(
        //        new Solution().minimumFinishTime(new int[][]{{2, 3}, {3, 4}}, 5, 4));
        System.out.println(
                new Solution().minimumFinishTime(new int[][]{{1, 10}, {2, 2}, {3, 4}}, 6, 5));
    }

    static class Solution {
        public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
            // 计算同一轮胎连续使用的最大值，当再跑一圈的时间>换胎时间，那么肯定是选择换胎。2^17>10^5
            // minSec保存在连续使用某一套胎i圈之后最小时间
            int[] minSec = new int[18];
            Arrays.fill(minSec, Integer.MAX_VALUE / 2);
            for (int[] tire : tires) {
                // time: 每圈用时
                long time = tire[0];
                for (int i = 1, sum = 0; time <= changeTime + tire[0]; ++i) {
                    sum += time;
                    minSec[i] = Math.min(minSec[i], sum);
                    time *= tire[1];
                }
            }
            // dp[i] = min(dp[i-j] + minSec[j]) 1<j<=i
            int[] dp = new int[numLaps + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = -changeTime;
            for (int i = 1; i <= numLaps; ++i) {
                for (int j = 1; j <= Math.min(17, i); ++j) {
                    dp[i] = Math.min(dp[i], dp[i - j] + minSec[j]);
                }
                dp[i] += changeTime;
            }
            return dp[numLaps];
        }
    }
}

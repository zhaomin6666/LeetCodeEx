package com.zm.LeetCodeEx.weekcontest.contest_282_20220227;

import java.util.Arrays;

/**
 * 6010. 完成旅途的最少时间
 * 给你一个数组 time ，其中 time[i] 表示第 i 辆公交车完成 一趟旅途 所需要花费的时间。
 * <p>
 * 每辆公交车可以 连续 完成多趟旅途，也就是说，一辆公交车当前旅途完成后，可以 立马开始 下一趟旅途。每辆公交车 独立 运行，也就是说可以同时有多辆公交车在运行且互不影响。
 * <p>
 * 给你一个整数 totalTrips ，表示所有公交车 总共 需要完成的旅途数目。请你返回完成 至少 totalTrips 趟旅途需要花费的 最少 时间。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：time = [1,2,3], totalTrips = 5
 * 输出：3
 * 解释：
 * - 时刻 t = 1 ，每辆公交车完成的旅途数分别为 [1,0,0] 。
 * 已完成的总旅途数为 1 + 0 + 0 = 1 。
 * - 时刻 t = 2 ，每辆公交车完成的旅途数分别为 [2,1,0] 。
 * 已完成的总旅途数为 2 + 1 + 0 = 3 。
 * - 时刻 t = 3 ，每辆公交车完成的旅途数分别为 [3,1,1] 。
 * 已完成的总旅途数为 3 + 1 + 1 = 5 。
 * 所以总共完成至少 5 趟旅途的最少时间为 3 。
 * 示例 2：
 * <p>
 * 输入：time = [2], totalTrips = 1
 * 输出：2
 * 解释：
 * 只有一辆公交车，它将在时刻 t = 2 完成第一趟旅途。
 * 所以完成 1 趟旅途的最少时间为 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= time.length <= 10^5
 * 1 <= time[i], totalTrips <= 10^7
 */
public class LEET6010 {
    public static void main(String[] args) {
        System.out.println(
                new Solution().minimumTime(new int[]{1, 2, 3}, 5));
        System.out.println(
                new Solution().minimumTime(new int[]{2, 3}, 7));
    }

    /**
     * 二分法寻找有效的时间
     * r取时长最少的一班车开n次所需时间，由于班次>=1，可能会有别的班次来完成多余的轮次，所以最终结果<=r
     */
    static class Solution {
        public long minimumTime(int[] time, int totalTrips) {
            Arrays.sort(time);
            long maxTimeByFirst = (long) totalTrips * time[0];
            long l = 1;
            long r = maxTimeByFirst;
            while (l <= r) {
                long mid = (l + r) >>> 1;
                long currentTime = 0;
                for (int t : time) {
                    currentTime += mid / t;
                }
                if (currentTime >= totalTrips) {
                    r = mid - 1;
                }
                else {
                    l = mid + 1;
                }
            }
            return l;
        }
    }
}

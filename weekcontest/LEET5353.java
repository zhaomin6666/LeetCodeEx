package com.zm.LeetCodeEx.weekcontest;

import java.util.HashSet;

/**
 * 周赛 2020年3月8日 5353. 灯泡开关 III
 * <p>
 * 房间中有 n 枚灯泡，编号从 1 到 n，自左向右排成一排。最初，所有的灯都是关着的。
 * <p>
 * 在 k  时刻（ k 的取值范围是 0 到 n - 1），我们打开 light[k] 这个灯。
 * <p>
 * 灯的颜色要想 变成蓝色 就必须同时满足下面两个条件：
 * <p>
 * 灯处于打开状态。
 * 排在它之前（左侧）的所有灯也都处于打开状态。
 * 请返回能够让 所有开着的 灯都 变成蓝色 的时刻 数目 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：light = [2,1,3,5,4]
 * 输出：3
 * 解释：所有开着的灯都变蓝的时刻分别是 1，2 和 4 。
 * 示例 2：
 * <p>
 * 输入：light = [3,2,4,1,5]
 * 输出：2
 * 解释：所有开着的灯都变蓝的时刻分别是 3 和 4（index-0）。
 * 示例 3：
 * <p>
 * 输入：light = [4,1,2,3]
 * 输出：1
 * 解释：所有开着的灯都变蓝的时刻是 3（index-0）。
 * 第 4 个灯在时刻 3 变蓝。
 * 示例 4：
 * <p>
 * 输入：light = [2,1,4,3,6,5]
 * 输出：3
 * 示例 5：
 * <p>
 * 输入：light = [1,2,3,4,5,6]
 * 输出：6
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == light.length
 * 1 <= n <= 5 * 10^4
 * light 是 [1, 2, ..., n] 的一个排列。
 *
 * @author zm
 */
public class LEET5353 {
    public static void main(String[] args) {
        LEET5353 l5344 = new LEET5353();
        System.out.println(l5344.new Solution().numTimesAllBlue(new int[]{2, 1, 3, 5, 4}));
        System.out.println(l5344.new Solution().numTimesAllBlue(new int[]{3, 2, 4, 1, 5}));
        System.out.println(l5344.new Solution().numTimesAllBlue(new int[]{4, 1, 2, 3}));
        System.out.println(l5344.new Solution().numTimesAllBlue(new int[]{2, 1, 4, 3, 6, 5}));
        System.out.println(l5344.new Solution().numTimesAllBlue(new int[]{1, 2, 3, 4, 5, 6}));
    }

    class Solution {
        public int numTimesAllBlue(int[] light) {
            int times = 0;
            HashSet<Integer> lightedBulbs = new HashSet<>();
            HashSet<Integer> blueBulbs = new HashSet<>();
            for (int i = 0; i < light.length; i++) {
                int lightIndex = light[i];
                if (lightIndex == 1) {
                    blueBulbs.add(lightIndex);
                } else {
                    if (blueBulbs.contains(lightIndex - 1) || lightedBulbs.contains(lightIndex - 1)) {
                        blueBulbs.add(lightIndex);
                    } else {
                        lightedBulbs.add(lightIndex);
                    }
                }
                while (lightedBulbs.contains(lightIndex + 1)) {
                    lightedBulbs.remove(lightIndex + 1);
                    blueBulbs.add(lightIndex++ + 1);
                }
                if (lightedBulbs.isEmpty() && !blueBulbs.isEmpty()) {
                    times++;
                }
            }
            return times;
        }
    }
}

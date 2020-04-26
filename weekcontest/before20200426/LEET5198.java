package com.zm.LeetCodeEx.weekcontest.before20200426;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 周赛 2019-9-22
 * 丑数 III
 * 请你帮忙设计一个程序，用来找出第 n 个丑数。
 * <p>
 * 丑数是可以被 a 或 b 或 c 整除的正整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, a = 2, b = 3, c = 5
 * 输出：4
 * 解释：The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
 * 示例 2：
 * <p>
 * 输入：n = 4, a = 2, b = 3, c = 4
 * 输出：6
 * 解释：丑数序列为 2, 3, 4, 6, 8, 9, 12... 其中第 4 个是 6。
 * 示例 3：
 * <p>
 * 输入：n = 5, a = 2, b = 11, c = 13
 * 输出：10
 * 解释：丑数序列为 2, 4, 6, 8, 10, 11, 12, 13... 其中第 5 个是 10。
 * 示例 4：
 * <p>
 * 输入：n = 1000000000, a = 2, b = 217983653, c = 336916467
 * 输出：1999999984
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * 本题结果在 [1, 2 * 10^9] 的范围内
 *
 * @author zm
 */
public class LEET5198 {
    public static void main(String[] args) {
        LEET5198 l5198 = new LEET5198();
        int n = 10, a = 2, b = 3, c = 3; // 2,3,4,6,8,9,10,12,14,15
        int ret = l5198.nthUglyNumber(n, a, b, c);
        System.out.println(JSON.toJSONString(ret));
    }

    public int nthUglyNumber(int n, int a, int b, int c) {
        int[] input = {a, b, c};
        Arrays.sort(input);
        long minn = input[0] * n;
        if (minn < input[1]) {
            return (int) minn;
        } else if (minn < input[2]) {
            int ai = 1;
            int bi = 1;
            int num = -1;
            for (int i = 0; i < n; i++) {
                if (input[0] * ai > input[1] * bi) {
                    num = input[1] * bi;
                    bi++;

                } else if (input[0] * ai < input[1] * bi) {
                    num = input[0] * ai;
                    ai++;

                } else {
                    num = input[0] * ai;
                    ai++;
                    bi++;

                }
            }
            return num;
        }
        int[] cnt = {1, 1, 1};
        long num = -1;
        for (int i = 0; i < n; i++) {
            long[] input2 = {input[0] * cnt[0], input[1] * cnt[1], input[2] * cnt[2]};
            long min = Math.min(Math.min(input[0] * cnt[0], input[1] * cnt[1]), input[2] * cnt[2]);
            boolean isAdd = false;
            for (int j = 0; j < 3; j++) {
                if (min == input2[j]) {
                    if (!isAdd) {
                        num = input2[j];
                        cnt[j]++;
                        isAdd = true;
                    } else {
                        cnt[j]++;
                    }

                }
            }

        }
        return (int) num;
    }
}

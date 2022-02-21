package com.zm.LeetCodeEx.algorithms.ex701_800;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 739. 每日温度
 * <p>
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 *
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 *
 * @author zm
 */
public class LEET739 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().dailyTemperatures(
                new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(new Solution2().dailyTemperatures(
                new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70})));
    }


    static class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] ret = new int[temperatures.length];
            for (int i = 0; i < temperatures.length; i++) {
                for (int j = i; j < temperatures.length; j++) {
                    if (temperatures[j] > temperatures[i]) {
                        ret[i] = j - i;
                        break;
                    }
                }
            }
            return ret;
        }
    }

    static class Solution2 {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] ret = new int[temperatures.length];
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = temperatures.length - 1; i >= 0; i--) {
                int currentTemperature = temperatures[i];
                while (!stack.isEmpty() && temperatures[stack.peekLast()] <= currentTemperature) {
                    stack.pollLast();
                }
                if (!stack.isEmpty()) {
                    ret[i] = stack.peekLast() - i;
                }
                else {
                    ret[i] = 0;
                }
                stack.addLast(i);
            }
            return ret;
        }
    }
}

package com.zm.LeetCodeEx.weekcontest.before20200426;

import java.util.HashMap;

/**
 * 双周赛 2019年12月14日  5126. 有序数组中出现次数超过25%的元素
 * 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 * <p>
 * 请你找到并返回这个整数
 * <p>
 * 示例：
 * <p>
 * 输入：arr = [1,2,2,6,6,6,6,7,10]
 * 输出：6
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 *
 * @author zm
 */
public class LEET5126 {
    public static void main(String[] args) {
        LEET5126 l5126 = new LEET5126();
        int[] arr = {1, 1};
        System.out.println(l5126.findSpecialInteger(arr));

    }

    public int findSpecialInteger(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int cnt = (arr.length >> 2) + 1;
        for (Integer i : arr) {
            if (map.containsKey(i)) {
                if (cnt == map.get(i) + 1) {
                    return i;
                }
                map.put(i, map.get(i) + 1);
            } else {
                if (cnt == 1) {
                    return i;
                }
                map.put(i, 1);
            }
        }
        return 0;
    }
}

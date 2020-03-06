package com.zm.LeetCodeEx.weekcontest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 周赛 2019年12月15日  5124. 顺次数
 * <p>
 * 我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。
 * <p>
 * 请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输出：low = 100, high = 300
 * 输出：[123,234]
 * 示例 2：
 * <p>
 * 输出：low = 1000, high = 13000
 * 输出：[1234,2345,3456,4567,5678,6789,12345]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 10 <= low <= high <= 10^9
 *
 * @author zm
 */
public class LEET5124 {
    public static void main(String[] args) {
        LEET5124 l5124 = new LEET5124();
        System.out.println(l5124.sequentialDigits(8, 9));

    }

    public List<Integer> sequentialDigits(int low, int high) {
        String highStr = String.valueOf(high);
        List<Integer> retList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            int temp = i;
            for (int j = 0; j < highStr.length() && temp < 10; j++) {
                sb.append(temp++);
                int val = Integer.valueOf(sb.toString());
                if (val >= low && val <= high) {
                    retList.add(val);
                }
            }
        }
        Collections.sort(retList);
        return retList;
    }

}

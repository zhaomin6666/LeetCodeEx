package com.zm.LeetCodeEx.weekcontest.contest_d72_20220219;

import com.alibaba.fastjson.JSON;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 5998. 拆分成最多数目的偶整数之和
 * 给你一个整数 finalSum 。请你将它拆分成若干个 互不相同 的偶整数之和，且拆分出来的偶整数数目 最多 。
 * <p>
 * 比方说，给你 finalSum = 12 ，那么这些拆分是 符合要求 的（互不相同的偶整数且和为 finalSum）：(2 + 10) ，(2 + 4 + 6) 和 (4 + 8) 。它们中，(2 + 4 + 6) 包含最多数目的整数。注意 finalSum 不能拆分成 (2 + 2 + 4 + 4) ，因为拆分出来的整数必须互不相同。
 * 请你返回一个整数数组，表示将整数拆分成 最多 数目的偶整数数组。如果没有办法将 finalSum 进行拆分，请你返回一个 空 数组。你可以按 任意 顺序返回这些整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：finalSum = 12
 * 输出：[2,4,6]
 * 解释：以下是一些符合要求的拆分：(2 + 10)，(2 + 4 + 6) 和 (4 + 8) 。
 * (2 + 4 + 6) 为最多数目的整数，数目为 3 ，所以我们返回 [2,4,6] 。
 * [2,6,4] ，[6,2,4] 等等也都是可行的解。
 * 示例 2：
 * <p>
 * 输入：finalSum = 7
 * 输出：[]
 * 解释：没有办法将 finalSum 进行拆分。
 * 所以返回空数组。
 * 示例 3：
 * <p>
 * 输入：finalSum = 28
 * 输出：[6,8,2,12]
 * 解释：以下是一些符合要求的拆分：(2 + 26)，(6 + 8 + 2 + 12) 和 (4 + 24) 。
 * (6 + 8 + 2 + 12) 有最多数目的整数，数目为 4 ，所以我们返回 [6,8,2,12] 。
 * [10,2,4,12] ，[6,2,4,16] 等等也都是可行的解。
 */
public class LEET5998 {
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution().maximumEvenSplit(28)));
    }

    /**
     * 回溯法
     */
    static class Solution {
        public List<Long> maximumEvenSplit(long finalSum) {
            if (finalSum % 2 != 0) {
                return Collections.emptyList();
            }
            LinkedList<Long> result = new LinkedList<>();
            doBack(result, finalSum, 1);
            return result;
        }

        private boolean doBack(LinkedList<Long> result, long finalSum, long multiply) {
            boolean success = false;
            while (!success) {
                long minus = multiply * 2;
                finalSum -= minus;
                if (finalSum < 0) {
                    return false;
                }
                result.addLast(minus);
                if (finalSum == 0) {
                    return true;
                }
                success = doBack(result, finalSum, ++multiply);
                if (!success) {
                    finalSum += minus;
                    result.removeLast();
                }
                else {
                    return true;
                }
            }
            return success;
        }
    }
}

package com.zm.LeetCodeEx.weekcontest.contest_d26_20200516;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 双周赛 2020年5月16日
 * <p>
 * 5397. 最简分数
 * <p>
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 * 示例 2：
 * <p>
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 * 示例 3：
 * <p>
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 * 示例 4：
 * <p>
 * 输入：n = 1
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 *
 * @author zm
 */
public class LEET5397 {
    public static void main(String[] args) {
        LEET5397 l5397 = new LEET5397();
        System.out.println(JSON.toJSONString(l5397.new Solution().simplifiedFractions(4)));
    }

    class Solution {
        public List<String> simplifiedFractions(int n) {
            List<String> retList = new LinkedList<>();
            HashSet<Double> set = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    double div = i * 1.0 / j;
                    if (!set.contains(div)) {
                        set.add(div);
                        retList.add(j + "/" + i);
                    }
                }
            }
            return retList;
        }
    }
}



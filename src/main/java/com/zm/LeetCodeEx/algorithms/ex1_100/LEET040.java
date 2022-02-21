package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 40. 组合总和 II
 * <p>
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * @author zm
 */
public class LEET040 {
    public static void main(String[] args) {
        LEET040 l040 = new LEET040();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(JSON.toJSONString(l040.combinationSum2(candidates, target)));

    }

    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidatesMethod1;
    private int len;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        candidatesMethod1 = candidates;
        len = candidates.length;
        findCombinationSum(target, 0, new Stack<>());
        return res;
    }

    private void findCombinationSum(int target, int start, Stack<Integer> pre) {
        if (target == 0) {
            res.add(new ArrayList<>(pre));
            return;
        }
        for (int i = start; i < len && target - candidatesMethod1[i] >= 0; i++) {
            pre.add(candidatesMethod1[i]);
            if (i < len) {
                findCombinationSum(target - candidatesMethod1[i], i + 1, pre);
            }
            int popInt = pre.pop();
            for (int j = i + 1; j < len; j++) {
                if (popInt == candidatesMethod1[j]) {
                    i++;
                }
            }
        }
    }
}

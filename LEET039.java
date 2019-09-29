package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 39. 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * @author zm
 */
public class LEET039 {
    public static void main(String[] args) {
        LEET039 l038 = new LEET039();
        int[] candidates = {2, 3, 5};
        int target = 8;
        System.out.println(JSON.toJSONString(l038.combinationSum2(candidates, target)));

    }

    /**
     * 自己一开始想的，感觉自己就是先把和用一个数填满，然后再减去。其实和官方解答感觉差不多。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // ----举例 [2,3,5] 8
        // 先排序
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            // 如果可以整除那么直接返回元素全是某个值的数组
            // ----可以整除的话这里就直接会在ret中加入[2,2,2,2]
            if (target / candidates[i] > 0 && target % candidates[i] == 0) {
                List<Integer> ans = new ArrayList<>();
                for (int j = 0; j < target / candidates[i]; j++) {
                    ans.add(candidates[i]);
                }
                ret.add(ans);
            }
            // 从全部都是某个元素开始不断减去这个元素的数量，获取剩余元素可以组成剩下的和的集合
            // ----由于排过序，2后面的数字3肯定大于2，所以从最多的[2,2,2,2]可以去掉两个2变成[2,2]，余下和为4，需要用[3,5]来组成，递归调用
            // ----显然这里递归调用返回为空，所以不会添加结果。然后继续循环减去一个2变成[2]，余下和为6，需要用[3,5]来组成，递归调用
            for (int j = target / candidates[i] - 1; j > 0; j--) {
                List<List<Integer>> behindRet = combinationSum(Arrays.copyOfRange(candidates, i + 1, candidates.length), target - j * candidates[i]);
                for (List<Integer> item : behindRet) {
                    // 每个获取到的后面集合需要加上前面的那些相同的元素
                    // ----这里用[3,3]可以组成一个6，取到这个结果之后，在前面加上基础上有的[2]，就可以把[2,3,3]加入结果集
                    List<Integer> ans = new ArrayList<>();
                    for (int k = 0; k < j; k++) {
                        ans.add(candidates[i]);
                    }
                    for (Integer intItem : item) {
                        ans.add(intItem);
                    }
                    ret.add(ans);
                }
            }
            // ----由于j>0，所以2的个数减到0的时候就开始以3为基础的循环
        }
        return ret;
    }


    /**
     * 评论区解答，回溯算法+剪枝，看了下思路自己还原下
     *
     * @param candidates
     * @param target
     * @return
     */
    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates2;
    private int len;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        len = candidates.length;
        if (len == 0) {
            return res;
        }
        Arrays.sort(candidates); // 排序后可使剪枝工作更彻底
        candidates2 = candidates;
        findCombinationSum(target, 0, new Stack<>());
        return res;
    }

    private void findCombinationSum(int target, int start, Stack<Integer> pre) {
        if (target == 0) {
            res.add(new ArrayList<>(pre));
            return;
        }
        for (int i = start; i < len && target - candidates2[i] >= 0; i++) {
            pre.add(candidates2[i]);
            findCombinationSum(target - candidates2[i], i, pre);
            pre.pop();
        }
    }
}

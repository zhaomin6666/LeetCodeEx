package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author zm
 */
public class LEET046 {
    public static void main(String[] args) {
        LEET046 l046 = new LEET046();
        int[] nums = {1, 2, 3};
        System.out.println(l046.permute(nums));
    }

    /**
     * 递归法
     * 当nums只有一个时返回那唯一一个数
     * 例如[1,2,3]
     * 1.把[1]作为第一个，然后递归调用[2,3]，
     * 2.把[2]作为第一个，递归调用[3]返回[3]，这样得到[2,3]，再把[3]作为第一个递归调用[2]返回[2]，这样得到[3,2]
     * 3.调用[2,3]返回[[2,3][3,2]]，和1拼起来就形成[[1,2,3][1,3,2]]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        } else if (nums.length == 1) {
            List<List<Integer>> ret = new ArrayList<>();
            List<Integer> retItem = new ArrayList<>();
            retItem.add(nums[0]);
            ret.add(retItem);
            return ret;
        }
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int[] subNums = new int[nums.length - 1];
            for (int j = 0, k = 0; j < nums.length; j++, k++) {
                if (j == i) {
                    k--;
                    continue;
                }
                subNums[k] = nums[j];
            }
            List<List<Integer>> retTemp = permute(subNums);
            for (List<Integer> item : retTemp) {
                List<Integer> itemRet = new ArrayList<>();
                itemRet.add(nums[i]);
                itemRet.addAll(item);
                ret.add(itemRet);
            }
        }
        return ret;
    }


    /**
     * 回溯法
     * <p>
     * 使用了visited数组来维护哪些被选过了，而不是对原来数组进行截取
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<>(), visited);
        return res;

    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }
}

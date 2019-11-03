package com.zm.LeetCodeEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 *
 * @author zm
 */
public class LEET047 {
    public static void main(String[] args) {
        LEET047 l047 = new LEET047();
        int[] nums = {1, 1, 1};
        System.out.println(l047.permuteUnique(nums));
    }

    List<List<Integer>> res;
    int[] visited;
    int[] input;

    /**
     * 回溯法
     * <p>
     * 1.对上一题的代码进行了优化，使用全局变量
     * 2.回溯里面增加判断，如果满足
     * (1)和上一个数字相同(input[i] == input[i - 1])
     * (2)并且上一个数字刚刚完成他的所有情况的排列visited[i-1] == 0
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        visited = new int[nums.length];
        Arrays.sort(nums);
        input = nums;
        backtrack(new ArrayList<>());
        return res;
    }

    private void backtrack(ArrayList<Integer> tmp) {
        if (tmp.size() == input.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if (visited[i] == 1) continue;
            if (i > 0 && input[i] == input[i - 1] && visited[i - 1] == 0) continue;
            visited[i] = 1;
            tmp.add(input[i]);
            backtrack(tmp);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }
}

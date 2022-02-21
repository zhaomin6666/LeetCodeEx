package com.zm.LeetCodeEx.weekcontest.before20200426;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 周赛 2019-9-22
 * 最小绝对差  显示英文描述
 * 用户通过次数 0
 * 用户尝试次数 0
 * 通过次数 0
 * 提交次数 0
 * 题目难度 Easy
 * 给你个整数数组 arr，其中每个元素都 不相同。
 * <p>
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 * 示例 2：
 * <p>
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 * 示例 3：
 * <p>
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 *
 * @author zm
 */
public class LEET5197 {
    public static void main(String[] args) {
        LEET5197 l5197 = new LEET5197();
        int[] array = {1};
        List<List<Integer>> ret = l5197.minimumAbsDifference(array);
        System.out.println(JSON.toJSONString(ret));
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> ret = new LinkedList<>();
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int thisInt = arr[i];
            int nextInt = arr[i + 1];
            if (nextInt - thisInt <= min) {
                if (nextInt - thisInt < min) {
                    min = nextInt - thisInt;
                    ret.clear();
                }
                List<Integer> item = new ArrayList<>();
                item.add(thisInt);
                item.add(nextInt);
                ret.add(item);
            }
        }
        return ret;
    }
}

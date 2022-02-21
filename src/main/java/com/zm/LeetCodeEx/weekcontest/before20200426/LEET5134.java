package com.zm.LeetCodeEx.weekcontest.before20200426;

import com.alibaba.fastjson.JSON;

/**
 * 双周赛 2019年12月28日  5134. 将每个元素替换为右侧最大元素
 * <p>
 * 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
 * <p>
 * 完成所有替换操作后，请你返回这个数组。
 * <p>
 * 示例：
 * <p>
 * 输入：arr = [17,18,5,4,6,1]
 * 输出：[18,6,6,6,1,-1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i] <= 10^5
 *
 * @author zm
 */
public class LEET5134 {
    public static void main(String[] args) {
        LEET5134 l5134 = new LEET5134();
        int[] arr = {17, 18, 5, 4, 6, 1};
        System.out.println(JSON.toJSONString(l5134.replaceElements(arr)));

    }

    public int[] replaceElements(int[] arr) {
        int[] rtnArr = new int[arr.length];
        rtnArr[arr.length - 1] = -1;
        int max = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            max = Math.max(max, arr[i + 1]);
            rtnArr[i] = max;
        }
        return rtnArr;
    }
}

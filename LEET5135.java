package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 双周赛 2019年12月28日  5135. 转变数组后最接近目标值的数组和
 * <p>
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 * <p>
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 * <p>
 * 请注意，答案不一定是 arr 中的数字。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * <p>
 * 示例 2：
 * <p>
 * 输入：arr = [2,3,5], target = 10
 * 输出：5
 * <p>
 * 示例 3：
 * <p>
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 * 输出：11361
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 *
 * @author zm
 */
public class LEET5135 {
    public static void main(String[] args) {
        LEET5135 l5135 = new LEET5135();
        int[] arr1 = {4, 9, 3};
        int[] arr2 = {2, 3, 5};
        int[] arr3 = {60864, 25176, 27249, 21296, 20204};
        int[] arr4 = {1547, 83230, 57084, 93444, 70879};
        System.out.println(JSON.toJSONString(l5135.findBestValue(arr1, 10)));
        System.out.println(JSON.toJSONString(l5135.findBestValue(arr2, 10)));
        System.out.println(JSON.toJSONString(l5135.findBestValue(arr3, 56803)));
        System.out.println(JSON.toJSONString(l5135.findBestValue(arr4, 71237)));

    }

    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            // int averageInt = BigDecimal.valueOf(target * 1.0 / (arr.length - i)).setScale(0, BigDecimal.ROUND_HALF_DOWN).intValue();
            int averageInt = (int) Math.floor(target * 1.0 / (arr.length - i) + 0.4999999999);
            if (arr[i] >= averageInt) {
                return averageInt;
            }
            target -= arr[i];
        }
        return arr[arr.length - 1];
    }
}

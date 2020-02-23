package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

/**
 * 周赛 2020年2月23日 5171. 最接近的因数
 * <p>
 * 给你一个整数 num，请你找出同时满足下面全部要求的两个整数：
 * <p>
 * 两数乘积等于  num + 1 或 num + 2
 * 以绝对差进行度量，两数大小最接近
 * 你可以按任意顺序返回这两个整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 8
 * 输出：[3,3]
 * 解释：对于 num + 1 = 9，最接近的两个因数是 3 & 3；对于 num + 2 = 10, 最接近的两个因数是 2 & 5，因此返回 3 & 3 。
 * 示例 2：
 * <p>
 * 输入：num = 123
 * 输出：[5,25]
 * 示例 3：
 * <p>
 * 输入：num = 999
 * 输出：[40,25]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= num <= 10^9
 *
 * @author zm
 */
public class LEET5171 {
    public static void main(String[] args) {
        LEET5171 l5171 = new LEET5171();
        System.out.println(JSON.toJSONString(l5171.closestDivisors(8)));
        System.out.println(JSON.toJSONString(l5171.closestDivisors(123)));
        System.out.println(JSON.toJSONString(l5171.closestDivisors(999)));
    }

    public int[] closestDivisors(int num) {
        int num1 = num + 1;
        int num2 = num + 2;
        int[] nums1 = getClosestDivisors(num1);
        int[] nums2 = getClosestDivisors(num2);
        return (nums1[1] - nums1[0]) > (nums2[1] - nums2[0]) ? nums2 : nums1;
    }

    private int[] getClosestDivisors(int num) {
        int[] ret = new int[2];
        int sqartNum = (int) Math.pow(num, 0.5);
        while (sqartNum > 0) {
            double di = ((double) num) / sqartNum;
            if ((double) ((int) di) == di) {
                ret[0] = sqartNum;
                ret[1] = (int) di;
                return ret;
            } else {
                sqartNum--;
            }
        }
        return ret;
    }
}

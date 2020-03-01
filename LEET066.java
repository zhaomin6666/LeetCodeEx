package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

/**
 * 66. 加一
 * <p>
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 * <p>
 *
 * @author zm
 */
public class LEET066 {
    public static void main(String[] args) {
        LEET066 l066 = new LEET066();
        //System.out.println(JSON.toJSONString(l066.new Solution().plusOne(new int[]{1,2,3})));
        System.out.println(JSON.toJSONString(l066.new Solution().plusOne(new int[]{9, 9, 9})));
    }


    private class Solution {
        public int[] plusOne(int[] digits) {
            int k = 1;
            for (int i = 0; i < digits.length; i++) {
                digits[digits.length - i - 1] += k;
                if (digits[digits.length - i - 1] < 10) {
                    return digits;
                }
                digits[digits.length - i - 1] = 0;
            }
            int[] ret = new int[digits.length + 1];
            ret[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                ret[i + 1] = digits[i];
            }
            return ret;
        }
    }
}

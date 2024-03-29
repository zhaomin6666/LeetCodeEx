package com.zm.LeetCodeEx.algorithms.ex1701_1800;

import com.alibaba.fastjson.JSON;

/**
 * 1720. 解码异或后的数组
 * 未知 整数数组 arr 由 n 个非负整数组成。
 * <p>
 * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
 * <p>
 * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
 * <p>
 * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：encoded = [1,2,3], first = 1
 * 输出：[1,0,2,1]
 * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 * 示例 2：
 * <p>
 * 输入：encoded = [6,2,7,3], first = 4
 * 输出：[4,2,0,7,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 104
 * encoded.length == n - 1
 * 0 <= encoded[i] <= 105
 * 0 <= first <= 105
 *
 * @author zm
 */
public class LEET1720 {
    public static void main(String[] args) {
        LEET1720 l1720 = new LEET1720();
        System.out.println(JSON.toJSONString(l1720.new Solution().decode(new int[]{1, 2, 3}, 1)));
        System.out.println(JSON.toJSONString(l1720.new Solution().decode(new int[]{6, 2, 7, 3}, 4)));

    }

    class Solution {
        public int[] decode(int[] encoded, int first) {
            int[] raw = new int[encoded.length + 1];
            raw[0] = first;
            for (int i = 1; i < encoded.length + 1; i++) {
                raw[i] = raw[i-1] ^ encoded[i-1];
            }
            return raw;
        }
    }
}

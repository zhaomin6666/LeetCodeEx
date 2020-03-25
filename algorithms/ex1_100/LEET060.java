package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.ArrayList;

/**
 * 60. 第k个排列
 * <p>
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 * @author zm
 */
public class LEET060 {
    public static void main(String[] args) {
        LEET060 l060 = new LEET060();
        System.out.println(l060.getPermutation(3, 1));
        System.out.println(l060.getPermutation(3, 2));
        System.out.println(l060.getPermutation(3, 3));
        System.out.println(l060.getPermutation(3, 4));
        System.out.println(l060.getPermutation(3, 5));
        System.out.println(l060.getPermutation(3, 6));
        System.out.println(l060.getPermutation(4, 9));
        System.out.println(l060.getPermutation(1, 1));
    }

    public String getPermutation(int n, int k) {
        int[] factorialInt = {0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> arrayList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arrayList.add(i + 1);
        }
        while (k >= 0 && n > 1) {
            int over = (k - 1) / factorialInt[n - 1];
            sb.append(arrayList.remove(over));
            k -= factorialInt[n-- - 1] * over;
        }
        sb.append(arrayList.get(0));
        return sb.toString();
    }
}

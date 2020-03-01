package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * 周赛 2020年1月5日  5304. 子数组异或查询
 * <p>
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 * <p>
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 * <p>
 * 并返回一个包含给定查询 queries 所有结果的数组。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * 输出：[2,7,14,8]
 * 解释：
 * 数组中元素的二进制表示形式是：
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * 查询的 XOR 值为：
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 * 示例 2：
 * <p>
 * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * 输出：[8,0,4,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 10^9
 * 1 <= queries.length <= 3 * 10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] < arr.length
 *
 * @author zm
 */
public class LEET5304 {
    public static void main(String[] args) {
        LEET5304 l5304 = new LEET5304();
        int[] arr = {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
        int[] arr2 = {4, 8, 2, 10};
        int[][] queries2 = {{2, 3}, {1, 3}, {0, 0}, {0, 3}};
        System.out.println(JSON.toJSONString(l5304.xorQueries(arr, queries)));


    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        ArrayList<Integer> retList = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == queries[i][1]) {
                retList.add(arr[queries[i][0]]);
            } else {
                int temp = arr[queries[i][0]];
                for (int j = queries[i][0] + 1; j <= queries[i][1]; j++) {
                    temp ^= arr[j];
                }
                retList.add(temp);
            }
        }
        int[] retArray = new int[retList.size()];
        for (int i = 0; i < retList.size(); i++) {
            retArray[i] = retList.get(i);
        }
        return retArray;
    }
}

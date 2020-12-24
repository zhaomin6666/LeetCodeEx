package com.zm.LeetCodeEx.algorithms.ex301_400;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 378. 有序矩阵中第K小的元素
 * <p>
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * 返回 13。
 *  
 * <p>
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
 *
 * @author zm
 */
public class LEET378 {
    public static void main(String[] args) {
        LEET378 l378 = new LEET378();
        System.out.println((l378.new Solution().kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8)));
    }

    /**
     * 使用优先队列
     * 类似于多个链表，取n的链表的第n小的数
     */
    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            if (matrix.length == 0 || matrix[0].length == 0 || matrix.length * matrix[0].length < k || k < 1) {
                return -1;
            }
            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            int[] index = new int[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                priorityQueue.add(new int[]{matrix[i][0], i});
                index[i]++;
            }
            int cur = 0;
            int ret = -1;
            while (cur < k) {
                int[] curMinPair = priorityQueue.poll();
                cur++;
                ret = curMinPair[0];
                int curIndex = curMinPair[1];
                if (index[curIndex] < matrix[0].length) {
                    priorityQueue.add(new int[]{matrix[curIndex][index[curIndex]++], curIndex});
                }
            }
            return ret;
        }
    }


}

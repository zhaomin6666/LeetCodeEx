package com.zm.LeetCodeEx.weekcontest;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * 周赛 2020年3月15日 5356. 矩阵中的幸运数
 * <p>
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 * <p>
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * <p>
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 3：
 * <p>
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5
 * 矩阵中的所有元素都是不同的
 *
 * @author zm
 */
public class LEET5356 {
    public static void main(String[] args) {
        LEET5356 l5355 = new LEET5356();
        System.out.println(JSON.toJSONString(l5355.new Solution().luckyNumbers(new int[][]{
                {3, 7, 8},
                {9, 11, 13},
                {15, 16, 17}})));
        System.out.println(JSON.toJSONString(l5355.new Solution().luckyNumbers(new int[][]{
                {1, 10, 4, 2},
                {9, 3, 8, 7},
                {15, 16, 17, 12}})));


    }


    /**
     * 查找行最小值，在查找列最大值，如果列最大值就是行最小值则找到该数
     */
    class Solution {
        public List<Integer> luckyNumbers(int[][] matrix) {
            List<Integer> list = new LinkedList<>();
            if (matrix.length == 0 || matrix[0].length == 0) {
                return list;
            }
            boolean[][] matrixMinInRow = new boolean[matrix.length][matrix[0].length];
            //boolean[][] matrixMaxInCol = new boolean[matrix.length][matrix[0].length];
            int[][] temp = new int[2][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                int min = Integer.MAX_VALUE;
                int col = 0;
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] < min) {
                        min = matrix[i][j];
                        col = j;
                    }
                    if (matrix[i][j] > temp[0][j]) {
                        temp[0][j] = matrix[i][j];
                        temp[1][j] = i;
                    }
                }
                matrixMinInRow[i][col] = true;
            }
            //System.out.println(JSON.toJSONString(temp));
            //System.out.println(JSON.toJSONString(matrixMinInRow));
            for (int i = 0; i < temp[0].length; i++) {
                //System.out.println(matrixMinInRow[temp[1][i]][i]);
                if (matrixMinInRow[temp[1][i]][i]) {
                    list.add(temp[0][i]);
                }
            }
            return list;
        }
    }
}

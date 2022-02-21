package com.zm.LeetCodeEx.weekcontest.contest_196_20200705;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1504. 统计全 1 子矩形
 * 给你一个只包含 0 和 1 的 rows * columns 矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：mat = [[1,0,1],
 * [1,1,0],
 * [1,1,0]]
 * 输出：13
 * 解释：
 * 有 6 个 1x1 的矩形。
 * 有 2 个 1x2 的矩形。
 * 有 3 个 2x1 的矩形。
 * 有 1 个 2x2 的矩形。
 * 有 1 个 3x1 的矩形。
 * 矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。
 * 示例 2：
 * <p>
 * 输入：mat = [[0,1,1,0],
 * [0,1,1,1],
 * [1,1,1,0]]
 * 输出：24
 * 解释：
 * 有 8 个 1x1 的子矩形。
 * 有 5 个 1x2 的子矩形。
 * 有 2 个 1x3 的子矩形。
 * 有 4 个 2x1 的子矩形。
 * 有 2 个 2x2 的子矩形。
 * 有 2 个 3x1 的子矩形。
 * 有 1 个 3x2 的子矩形。
 * 矩形数目总共 = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24 。
 * 示例 3：
 * <p>
 * 输入：mat = [[1,1,1,1,1,1]]
 * 输出：21
 * 示例 4：
 * <p>
 * 输入：mat = [[1,0,1],[0,1,0],[1,0,1]]
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= rows <= 150
 * 1 <= columns <= 150
 * 0 <= mat[i][j] <= 1
 */
public class LEET1504 {

    public static void main(String[] args) {
        LEET1504 leet1504 = new LEET1504();
        System.out.println(leet1504.new Solution().numSubmat(
                new int[][]{
                        {1, 0, 1},
                        {1, 1, 0},
                        {1, 1, 0}}));
    }

    /**
     * 预处理矩阵，记录某一点向左边连续有1的最长长度（包括他自己）
     * 如：
     * [1,0,1]
     * [1,1,0]
     * [1,1,0]
     * 处理完成后为：
     * [1,0,1]
     * [1,2,0]
     * [1,2,0]
     * 遍历每个点，从该点开始向上遍历就可以获得以该点为右下角的矩形数量。
     */
    class Solution {
        public int numSubmat(int[][] mat) {
            int[][] helper = new int[mat.length][mat[0].length];
            for (int i = 0; i < mat.length; i++) {
                int temp = 0;
                for (int j = 0; j < mat[0].length; j++) {
                    if (mat[i][j] == 1) {
                        temp++;
                    } else {
                        temp = 0;
                    }
                    helper[i][j] = temp;
                }
            }
            int ret = 0;
            for (int i = 0; i < helper.length; i++) {
                for (int j = 0; j < helper[0].length; j++) {
                    if (helper[i][j] != 0) {
                        ret += helper[i][j];
                        int min = helper[i][j];
                        for (int k = i - 1; k >= 0; k--) {
                            if (helper[k][j] == 0) {
                                break;
                            }
                            min = Math.min(min, helper[k][j]);
                            ret += min;
                        }
                    }
                }
            }
            return ret;
        }
    }

    /**
     * 官方题解
     * 优化了在枚举到每一个点上的时候需要向上遍历的问题。
     * 使用单调栈来保存从上到下的不大于当前值的所有行，并且还要保留弹出行的高度。
     * 栈元素使用(helper[i][j], height)，height初始为1，每弹出一个加上被弹出的height，
     * 这样才能准确记录高度。
     */
    class Solution2 {
        public int numSubmat(int[][] mat) {
            int n = mat.length;
            int m = mat[0].length;
            int[][] row = new int[n][m];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (j == 0) {
                        row[i][j] = mat[i][j];
                    } else if (mat[i][j] != 0) {
                        row[i][j] = row[i][j - 1] + 1;
                    } else {
                        row[i][j] = 0;
                    }
                }
            }
            int ans = 0;
            for (int j = 0; j < m; ++j) {
                int i = 0;
                Deque<int[]> Q = new LinkedList<int[]>();
                int sum = 0;
                while (i <= n - 1) {
                    int height = 1;
                    while (!Q.isEmpty() && Q.peekFirst()[0] > row[i][j]) {
                        // 弹出的时候要减去多于的答案
                        sum -= Q.peekFirst()[1] * (Q.peekFirst()[0] - row[i][j]);
                        height += Q.peekFirst()[1];
                        Q.pollFirst();
                    }
                    sum += row[i][j];
                    ans += sum;
                    Q.offerFirst(new int[]{row[i][j], height});
                    i++;
                }
            }
            return ans;
        }
    }
}

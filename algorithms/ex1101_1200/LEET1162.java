package com.zm.LeetCodeEx.algorithms.ex1101_1200;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1162. 地图分析
 * <p>
 * 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
 * <p>
 * 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
 * <p>
 * 如果我们的地图上只有陆地或者海洋，请返回 -1。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
 * 示例 2：
 * <p>
 * 输入：[[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] 不是 0 就是 1
 *
 * @author zm
 */
public class LEET1162 {
    public static void main(String[] args) {
        LEET1162 l1160 = new LEET1162();
        System.out.println(l1160.new Solution2().maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
        System.out.println(l1160.new Solution2().maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 1, 0}}));
        System.out.println(l1160.new Solution2().maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(l1160.new Solution2().maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {1, 0, 1}}));
        System.out.println(l1160.new Solution2().maxDistance(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 1, 0}, {0, 0, 0, 1}}));
        System.out.println(l1160.new Solution2().maxDistance(new int[][]{{0, 0}, {0, 0}}));
        System.out.println(l1160.new Solution2().maxDistance(new int[][]{{1, 1}, {1, 1}}));

    }

    /**
     * 思路1：陆地不断扩散，直到最后一片海洋被覆盖
     * 思路2：Dijkstra算法/最短路问题：其实就是从各个陆地到各个海洋的最短路径中的最大值。
     * 想象一个s0连接各个陆地，s0到各个陆地的距离为0，求s0到各个海洋的最短路问题，中间各连接均为1。
     */
    class Solution {
        public int maxDistance(int[][] grid) {
            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            Queue<int[]> queue = new ArrayDeque<>();
            int m = grid.length, n = grid[0].length;
            // 先把所有的陆地都入队。
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        queue.offer(new int[]{i, j});
                    }
                }
            }

            // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
            boolean hasOcean = false;
            int[] point = null;
            while (!queue.isEmpty()) {
                point = queue.poll();
                int x = point[0], y = point[1];
                // 取出队列的元素，将其四周的海洋入队。
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                        continue;
                    }
                    grid[newX][newY] = grid[x][y] + 1; // 这里我直接修改了原数组，因此就不需要额外的数组来标志是否访问
                    hasOcean = true;
                    queue.offer(new int[]{newX, newY});
                }
            }

            // 没有陆地或者没有海洋，返回-1。
            if (point == null || !hasOcean) {
                return -1;
            }

            // 返回最后一次遍历到的海洋的距离。
            return grid[point[0]][point[1]] - 1;

        }
    }

    /**
     * 两次动态规划，左上到右下，右下到左上
     */
    class Solution2 {
        public int maxDistance(int[][] grid) {
            // 初始化
            boolean hasLand = false;
            boolean hasOcean = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 0) {
                        grid[i][j] = 256;
                        hasOcean = true;
                    } else {
                        hasLand = true;
                    }
                }
            }
            if (!hasLand || !hasOcean) {
                return -1;
            }

            // 左上到右下
            for (int i = 1; i < grid.length; i++) {
                if (grid[i - 1][0] != 0) {
                    grid[i][0] = Math.min(grid[i][0], grid[i - 1][0] + 1);
                }
            }
            for (int i = 1; i < grid[0].length; i++) {
                if (grid[0][i - 1] != 0) {
                    grid[0][i] = Math.min(grid[0][i], grid[0][i - 1] + 1);
                }
            }
            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[0].length; j++) {
                    int m = Math.min(grid[i - 1][j], grid[i][j - 1]);
                    if (m != 0) {
                        grid[i][j] = Math.min(grid[i][j], m + 1);
                    }
                }
            }
            // 右下到左上
            int max = grid[grid.length - 1][grid[0].length - 1];
            for (int i = grid.length - 2; i >= 0; i--) {
                if (grid[i + 1][grid[0].length - 1] != 0) {
                    int min = Math.min(grid[i][grid[0].length - 1], grid[i + 1][grid[0].length - 1] + 1);
                    grid[i][grid[0].length - 1] = min;
                    max = Math.max(max, min);
                }
            }
            for (int i = grid[0].length - 2; i >= 0; i--) {
                if (grid[grid.length - 1][i + 1] != 0) {
                    int min = Math.min(grid[grid.length - 1][i], grid[grid.length - 1][i + 1] + 1);
                    grid[grid.length - 1][i] = min;
                    max = Math.max(max, min);
                }
            }
            for (int i = grid.length - 2; i >= 0; i--) {
                for (int j = grid[0].length - 2; j >= 0; j--) {
                    int m = Math.min(grid[i + 1][j], grid[i][j + 1]);
                    if (m != 0) {
                        int min = Math.min(grid[i][j], m + 1);
                        grid[i][j] = min;
                        max = Math.max(max, min);
                    }
                }
            }
            //System.out.println(JSON.toJSONString(grid));
            return max - 1;
        }
    }
}

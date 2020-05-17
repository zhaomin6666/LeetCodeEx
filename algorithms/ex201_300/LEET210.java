package com.zm.LeetCodeEx.algorithms.ex201_300;

import java.util.*;

/**
 * 210. 课程表 II
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * <p>
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 * <p>
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 * <p>
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 * <p>
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 *
 * @author zm
 */
public class LEET210 {
    public static void main(String[] args) {
        LEET210 l210 = new LEET210();
        System.out.println(Arrays.toString(l210.new Solution().findOrder(4, new int[][]{{3, 0}, {0, 1}})));
        System.out.println(Arrays.toString(l210.new Solution().findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        System.out.println(Arrays.toString(l210.new Solution().findOrder(4, new int[][]{{1, 0}, {2, 0}, {2, 3}, {3, 2}})));
    }

    class Solution {
        private int[] status;
        private HashMap<Integer, List<Integer>> preMap;
        // 判断是否有循环
        private boolean valid = true;

        private int[] res;
        private int index = 0;

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 0:未搜索 1:搜索中 2:已搜索
            status = new int[numCourses];
            preMap = new HashMap<>();
            res = new int[numCourses];
            for (int[] pre : prerequisites) {
                if (preMap.containsKey(pre[0])) {
                    preMap.get(pre[0]).add(pre[1]);
                } else {
                    List<Integer> tempList = new LinkedList<>();
                    tempList.add(pre[1]);
                    preMap.put(pre[0], tempList);
                }
            }
            for (int i = 0; i < numCourses && valid; i++) {
                if (status[i] == 0) {
                    dfs(i);
                }
            }
            if (!valid) {
                return new int[0];
            }
            return res;
        }

        private void dfs(int i) {
            if (preMap.containsKey(i)) {
                for (Integer t : preMap.get(i)) {
                    status[i] = 1;
                    if (status[t] == 1) {
                        valid = false;
                        return;
                    } else if (status[t] == 0) {
                        dfs(t);
                    }
                }
            }
            status[i] = 2;
            res[index++] = i;
        }
    }

    /**
     * BFS
     */
    class Solution2 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] input = new int[numCourses];
            int[] res = new int[numCourses];
            Queue<Integer> queue = new LinkedList<>();
            // 统计节点的入度
            for (int[] edge : prerequisites) {
                input[edge[0]]++;
            }
            // 将入度为0的点入队
            for (int i = 0; i < numCourses; i++) {
                if (input[i] == 0) {
                    queue.offer(i);
                }
            }
            int idx = 0;
            while (!queue.isEmpty()) {
                int temp = queue.poll();
                res[idx++] = temp;
                // 修改节点入度
                for (int[] edge : prerequisites) {
                    if (edge[1] == temp) {
                        input[edge[0]]--;
                        if (input[edge[0]] == 0) {
                            queue.offer(edge[0]);
                        }
                    }
                }
            }
            // 出现环了(res中没包括所有点, idx没走完)
            if (idx != numCourses) {
                return new int[]{};
            }
            return res;
        }
    }
}

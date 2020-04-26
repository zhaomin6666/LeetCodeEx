package com.zm.LeetCodeEx.weekcontest.before20200426;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 周赛 2020年3月8日 5355. T 秒后青蛙的位置
 * <p>
 * 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
 * <p>
 * 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
 * 青蛙无法跳回已经访问过的顶点。
 * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 * 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 无向树的边用数组 edges 描述，其中 edges[i] = [fromi, toi] 意味着存在一条直接连通 fromi 和 toi 两个顶点的边。
 * <p>
 * 返回青蛙在 t 秒后位于目标顶点 target 上的概率。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
 * 输出：0.16666666666666666
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
 * 输出：0.3333333333333333
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。
 * 示例 3：
 * <p>
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
 * 输出：0.16666666666666666
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * edges.length == n-1
 * edges[i].length == 2
 * 1 <= edges[i][0], edges[i][1] <= n
 * 1 <= t <= 50
 * 1 <= target <= n
 * 与准确值误差在 10^-5 之内的结果将被判定为正确。
 *
 * @author zm
 */
public class LEET5355 {
    public static void main(String[] args) {
        LEET5355 l5355 = new LEET5355();
        System.out.println(l5355.new Solution().frogPosition(7, new int[][]{{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}}, 2, 4));
        System.out.println(l5355.new Solution().frogPosition(7, new int[][]{{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}}, 1, 7));
        System.out.println(l5355.new Solution().frogPosition(7, new int[][]{{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}}, 20, 6));
        System.out.println(l5355.new Solution().frogPosition(3, new int[][]{{2, 1}, {3, 2}}, 1, 2));

    }

    class Solution {
        private HashMap<Integer, LinkedList<Integer>> canJumpTo;

        public double frogPosition(int n, int[][] edges, int t, int target) {
            canJumpTo = new HashMap<>();
            for (int i = 0; i < edges.length; i++) {
                int a = 0, b = 0;
                if (edges[i][0] > edges[i][1]) {
                    a = edges[i][1];
                    b = edges[i][0];
                } else {
                    a = edges[i][0];
                    b = edges[i][1];
                }
                LinkedList<Integer> list = canJumpTo.getOrDefault(a, new LinkedList<>());
                list.add(b);
                canJumpTo.put(a, list);
            }
            for (int i = 1; i < n + 1; i++) {
                if (!canJumpTo.containsKey(i)) {
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(i);
                    canJumpTo.put(i, list);
                }
            }

            HashMap<Integer, Double> k = new HashMap<>();
            k.put(1, 1.0);
            for (int i = 0; i < t; i++) {
                HashMap<Integer, Double> kTemp = new HashMap<>();
                for (Map.Entry<Integer, Double> entry : k.entrySet()) {
                    LinkedList<Integer> canJumpList = canJumpTo.get(entry.getKey());
                    for (Integer c : canJumpList) {
                        kTemp.put(c, entry.getValue() / canJumpList.size());
                    }
                }
                k = kTemp;
            }
            return k.getOrDefault(target, 0.0);
        }
    }
}

package com.zm.LeetCodeEx.weekcontest.contest_191_20200531;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 周赛 2020年5月31日
 * <p>
 * 1466. 重新规划路线
 * <p>
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 * <p>
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 * <p>
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 * <p>
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 * <p>
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * 输出：3
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * 输出：2
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 3：
 * <p>
 * 输入：n = 3, connections = [[1,0],[2,0]]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 5 * 10^4
 * connections.length == n-1
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] <= n-1
 * connections[i][0] != connections[i][1]
 *
 * @author zm
 */
public class LEET1466 {
    public static void main(String[] args) {
        LEET1466 l1466 = new LEET1466();
        System.out.println(l1466.new Solution2().minReorder(6, new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}}));
        //50
        //15
        //[37,40,41,30,27,10,31]
        //[2,1,9,5,4,12,6,13,11]
    }

    /**
     * 用一个正向的map和一个反向的map记录路线，从0开始dfs。
     * 如果遇到反向的ret++
     */
    class Solution {
        public int minReorder(int n, int[][] connections) {
            int ret = 0;
            HashMap<Integer, List<Integer>> fromMap = new HashMap<>();
            HashMap<Integer, List<Integer>> toMap = new HashMap<>();
            for (int[] con : connections) {
                if (!fromMap.containsKey(con[0])) {
                    fromMap.put(con[0], new ArrayList<>());
                }
                fromMap.get(con[0]).add(con[1]);
                if (!toMap.containsKey(con[1])) {
                    toMap.put(con[1], new ArrayList<>());
                }
                toMap.get(con[1]).add(con[0]);
            }
            System.out.println(JSON.toJSONString(fromMap));
            System.out.println(JSON.toJSONString(toMap));
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            boolean[] seen = new boolean[n];
            seen[0] = true;
            while (!stack.isEmpty()) {
                int cur = stack.pop();
                if (fromMap.containsKey(cur)) {
                    for (int i : fromMap.get(cur)) {
                        if (!seen[i]) {
                            ret++;
                            stack.push(i);
                            seen[i] = true;
                        }
                    }
                }
                if (toMap.containsKey(cur)) {
                    for (int i : toMap.get(cur)) {
                        if (!seen[i]) {
                            stack.push(i);
                            seen[i] = true;
                        }
                    }
                }
            }
            return ret;
        }
    }

    /**
     * 使用一个map保存，负数代表反向
     *
     * @author Surine https://leetcode-cn.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/solution/ru-guo-ke-yi-zai-gei-wo-yi-ge-bfsde-ji-hui-by-suri/
     */
    class Solution2 {
        public int minReorder(int n, int[][] connections) {
            //遍历整个数组,构建一个可达图，这里的可达的定义为联通即可，不在乎方向（但是这里我用负数代表了反方向，方便后续统计）
            HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
            for (int[] val : connections) {
                HashSet<Integer> set = map.containsKey(val[0]) ? map.get(val[0]) : new HashSet<>();
                set.add(val[1]);
                map.put(val[0], set);
                HashSet<Integer> setReverse = map.containsKey(val[1]) ? map.get(val[1]) : new HashSet<>();
                setReverse.add(val[0] * -1);
                map.put(val[1], setReverse);
            }
            return mybfs(map, n);
        }

        private int mybfs(HashMap<Integer, HashSet<Integer>> map, int n) {
            boolean[] tBool = new boolean[n];
            LinkedList<Integer> queue = new LinkedList<>();
            int ans = 0;
            queue.offerLast(0);
            tBool[0] = true;
            while (!queue.isEmpty()) {
                for (Integer i : map.get(queue.pollFirst())) {
                    //访问之后禁止再访问
                    if (tBool[Math.abs(i)]) {
                        continue;
                    }
                    //前面说到，我们用负数代表了反方向，这里就用作统计了，如果是正数，就说明方向需要调整，建议仔细品味下
                    if (i > 0) {
                        ans++;
                    }
                    tBool[Math.abs(i)] = true;
                    queue.offerLast(Math.abs(i));
                }
            }
            return ans;
        }
    }

    /**
     * 如果保证输入的connection为正确的顺序的话，就可以直接用一个set来做
     */
    class Solution3 {
        public int minReorder(int n, int[][] connections) {
            Set<Integer> available = new HashSet<>();
            available.add(0);
            int change = 0;
            for (int[] line : connections) {
                if (available.contains(line[1])) {
                    available.add(line[0]);
                } else {
                    change++;
                    available.add(line[1]);
                }
            }
            return change;
        }
    }
}

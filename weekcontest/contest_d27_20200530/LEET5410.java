package com.zm.LeetCodeEx.weekcontest.contest_d27_20200530;

import java.util.*;

/**
 * 周赛 2020年5月30日
 * <p>
 * 1462. 课程安排 IV
 * <p>
 * 你总共需要上 n 门课，课程编号依次为 0 到 n-1 。
 * <p>
 * 有的课会有直接的先修课程，比如如果想上课程 0 ，你必须先上课程 1 ，那么会以 [1,0] 数对的形式给出先修课程数对。
 * <p>
 * 给你课程总数 n 和一个直接先修课程数对列表 prerequisite 和一个查询对列表 queries 。
 * <p>
 * 对于每个查询对 queries[i] ，请判断 queries[i][0] 是否是 queries[i][1] 的先修课程。
 * <p>
 * 请返回一个布尔值列表，列表中每个元素依次分别对应 queries 每个查询对的判断结果。
 * <p>
 * 注意：如果课程 a 是课程 b 的先修课程且课程 b 是课程 c 的先修课程，那么课程 a 也是课程 c 的先修课程。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * 输出：[false,true]
 * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
 * 示例 2：
 * <p>
 * 输入：n = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * 输出：[false,false]
 * 解释：没有先修课程对，所以每门课程之间是独立的。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * 输出：[true,true]
 * 示例 4：
 * <p>
 * 输入：n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
 * 输出：[false,true]
 * 示例 5：
 * <p>
 * 输入：n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
 * 输出：[true,false,true,false]
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 100
 * 0 <= prerequisite.length <= (n * (n - 1) / 2)
 * 0 <= prerequisite[i][0], prerequisite[i][1] < n
 * prerequisite[i][0] != prerequisite[i][1]
 * 先修课程图中没有环。
 * 先修课程图中没有重复的边。
 * 1 <= queries.length <= 10^4
 * queries[i][0] != queries[i][1]
 *
 * @author zm
 */
public class LEET5410 {
    public static void main(String[] args) {
        LEET5410 l5410 = new LEET5410();
        System.out.println(l5410.new Solution2().checkIfPrerequisite(4, new int[][]{{1, 0}, {2, 0}, {0, 3}},
                new int[][]{{0, 1}, {2, 0}}));
    }

    class Solution {
        private HashMap<Integer, HashSet<Integer>> hashSets;
        private int n;

        public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
            ArrayList<Boolean> retList = new ArrayList<>(queries.length);
            this.n = n;
            hashSets = new HashMap<>(n);
            for (int[] pre : prerequisites) {
                if (hashSets.containsKey(pre[0])) {
                    hashSets.get(pre[0]).add(pre[1]);
                } else {
                    HashSet<Integer> set = new HashSet<>();
                    set.add(pre[1]);
                    hashSets.put(pre[0], set);
                }
            }
            for (int[] query : queries) {
                boolean[] seen = new boolean[n];
                retList.add(query(query, seen));
            }
            return retList;
        }

        private Boolean query(int[] query, boolean[] seen) {
            int needToFind = query[0];
            if (!seen[query[0]]) {
                seen[query[0]] = true;
                if (hashSets.containsKey(needToFind)) {
                    HashSet<Integer> set = hashSets.get(needToFind);
                    if (set.contains(query[1])) {
                        return true;
                    } else {
                        for (Integer i : set) {
                            boolean temp = query(new int[]{i, query[1]}, seen);
                            if (temp) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }


    /**
     * 用一个set来记录所有可以到达的点
     * 比如检查1->4的时候，1->2->3->4。记录1_2,1_3,1_4,2_3,2_4,3_4
     * 再检查2->4的时候，直接判断发现有2_4，所以就可以到达。
     *
     * @author Surine https://leetcode-cn.com/u/surine/
     */
    class Solution2 {
        public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
            //构建一个可达的map表，留着一会bfs用
            HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
            for (int[] val : prerequisites) {
                HashSet<Integer> set = map.containsKey(val[0]) ? map.get(val[0]) : new HashSet<>();
                set.add(val[1]);
                map.put(val[0], set);
            }

            //创建一个结果集，里面存放所有可达关系
            HashSet<String> res = new HashSet<>();
            for (int i = 0; i < n; i++) {
                bfs(map, i, n, res);
            }

            //统计答案
            List<Boolean> ans = new ArrayList<>();
            for (int[] val : queries) {
                ans.add(res.contains(val[0] + "_" + val[1]));
            }
            return ans;
        }

        private void bfs(HashMap<Integer, HashSet<Integer>> map, int i, int n, HashSet<String> res) {
            LinkedList<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[n];
            //如果这个值在map中不存在，说明他到其他课程没有可达关系
            if (!map.containsKey(i)) {
                return;
            }
            queue.offerLast(i);
            //我们的目标就是求root到其他点的可达关系，所以在bfs过程中，记录下这个可达关系，放在set里，这里
            //我用 a_b 这种字符串形式来记录了。
            visited[i] = true;
            while (!queue.isEmpty()) {
                HashSet<Integer> data = map.get(queue.pollFirst());
                if (data != null) {
                    for (Integer d : data) {
                        if (!visited[d]) {
                            res.add(i + "_" + d);
                            queue.offerLast(d);
                            visited[d] = true;
                        }
                    }
                }
            }
            //遍历结束后，所有可达的线都可以记录下来，求完所有的课程即可形成一个最终的统计集合，就可以遍历一遍查询数据
            //来返回结果了。
        }
    }
}

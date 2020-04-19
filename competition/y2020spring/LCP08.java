package com.zm.LeetCodeEx.competition.y2020spring;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LCP08 {

    public static void main(String[] args) {
        LCP08 LCP08 = new LCP08();
        System.out.println(Arrays.toString(LCP08.new Solution().getTriggerTime(
                new int[][]{{2, 8, 4}, {2, 5, 0}, {10, 9, 8}}, new int[][]{{2, 11, 3}, {15, 10, 7}, {9, 17, 12}, {8, 1, 14}}
        )));

        System.out.println(Arrays.toString(LCP08.new Solution().getTriggerTime(
                new int[][]{{0, 4, 5}, {4, 8, 8}, {8, 6, 1}, {10, 10, 0}},
                new int[][]{{12, 11, 16}, {20, 2, 6}, {9, 2, 6}, {10, 18, 3}, {8, 14, 9}}
        )));
    }

    /**
     * 暴力算法超时
     */
    class Solution {
        public int[] getTriggerTime(int[][] increase, int[][] requirements) {
            int reqCnt = requirements.length;
            int attrCnt = requirements[0].length;
            boolean[][] reqChecked = new boolean[reqCnt][attrCnt];
            int[] ret = new int[reqCnt];
            Arrays.fill(ret, -1);
            int[] attr = new int[attrCnt];
            for (int day = 0; day <= increase.length; day++) {
                for (int i = 0; i < reqCnt; i++) {
                    if (ret[i] == -1) {
                        boolean check = true;
                        int[] req = requirements[i];
                        for (int j = 0; j < attrCnt; j++) {
                            if (!reqChecked[i][j]) {
                                if (attr[j] < req[j]) {
                                    check = false;
                                    break;
                                } else {
                                    reqChecked[i][j] = true;
                                }
                            }
                        }
                        if (check) {
                            ret[i] = day;
                        }
                    }
                }
                if (day < increase.length) {
                    for (int i = 0; i < increase[0].length; i++) {
                        attr[i] += increase[day][i];
                    }
                }

            }
            return ret;
        }
    }

    /**
     * 排名靠前的大佬解法
     * <p>
     * 相当于把3种属性分别存在3个优先队列中，每个队列中第一个为最小值，这样每次state变化的时候优先与最小的判断
     */
    class Solution2 {
        public int[] getTriggerTime(int[][] inc, int[][] req) {
            int m = req.length;
            Req[] reqs = new Req[m];
            for (int i = 0; i < m; i++) {
                reqs[i] = new Req();
                reqs[i].vals = req[i];
            }

            PriorityQueue<Req>[] pq = new PriorityQueue[3];
            for (int i = 0; i < 3; i++) {
                final int finalI = i;
                pq[i] = new PriorityQueue<Req>(m, (a, b) -> Integer.compare(a.vals[finalI], b.vals[finalI]));
                pq[i].addAll(Arrays.asList(reqs));
            }

            int time = 0;
            int[] state = new int[3];
            while (time <= inc.length) {
                for (int i = 0; i < 3; i++) {
                    while (!pq[i].isEmpty() && pq[i].peek().vals[i] <= state[i]) {
                        Req r = pq[i].remove();
                        r.used++;
                        if (r.used == 3) {
                            r.ans = time;
                        }
                    }
                }

                if (time == inc.length) {
                    break;
                }

                for (int i = 0; i < 3; i++) {
                    state[i] += inc[time][i];
                }
                time++;
            }

            int[] ans = new int[m];
            for (int i = 0; i < m; i++) {
                ans[i] = reqs[i].ans;
            }

            return ans;
        }

        class Req {
            int[] vals;
            int used;
            int ans = -1;
        }
    }

    /**
     * 把increase叠加成具体每天的属性值，然后对于每个requirements的各属性可以进行二分查找
     */
    class Solution3 {
        public int[] getTriggerTime(int[][] increase, int[][] requirements) {
            int day = 0;
            int[] ans = new int[requirements.length];
            //将increase中的三元组的含义变为每一天的属性值
            for (int i = 1; i < increase.length; i++) {
                increase[i][0] += increase[i - 1][0];
                increase[i][1] += increase[i - 1][1];
                increase[i][2] += increase[i - 1][2];
            }
            for (int i = 0; i < requirements.length; i++) {
                if (requirements[i][0] == 0 && requirements[i][1] == 0 && requirements[i][2] == 0) {
                    ans[i] = 0;
                } else {
                    int left = 0;
                    int right = increase.length - 1;
                    //如果最后一天仍不满足，设为-1
                    if (increase[right][0] < requirements[i][0] || increase[right][1] < requirements[i][1]
                            || increase[right][2] < requirements[i][2]) {
                        ans[i] = -1;
                        continue;
                    }
                    //二分查找
                    while (left < right) {
                        int mid = (left + right) / 2;
                        if (increase[mid][0] < requirements[i][0] || increase[mid][1] < requirements[i][1]
                                || increase[mid][2] < requirements[i][2]) {
                            left = mid + 1;
                        } else {
                            right = mid;
                        }
                    }
                    ans[i] = left + 1;
                }
            }
            return ans;
        }
    }

}

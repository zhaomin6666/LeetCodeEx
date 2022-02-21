package com.zm.LeetCodeEx.competition.y2020fall;

import java.util.ArrayList;
import java.util.Arrays;

public class LCP03 {

    public static void main(String[] args) {
        LCP03 LCP06 = new LCP03();
        System.out.println(LCP06.new Solution2().minimumOperations("rrryyyrryyyrr"));
        System.out.println(LCP06.new Solution2().minimumOperations("yry"));
        System.out.println(LCP06.new Solution2().minimumOperations("rrryyyrryyyrryyyrr"));
        System.out.println(LCP06.new Solution2().minimumOperations("yyrry"));
        System.out.println(LCP06.new Solution2().minimumOperations("rryyyyyyrrrrrrryyyrryyrr"));
    }

    /**
     * 动态规划
     * <p>
     * 树叶的集合一共最后为3个
     * dp[i][0]表示在第i片树叶的时候还是在第一个集合的r
     * dp[i][1]表示在第i片树叶的时候是在第二个集合的y
     * dp[i][2]表示在第i片树叶的时候已经在第三个集合的r
     * <p>
     * dp[i][0] = dp[i-1,0] + 当前树叶是否为r ? 0 : 1
     * dp[i][1] = min{dp[i-1,0],dp[i-1,1]} + 当前树叶是否为y ? 0 : 1
     * dp[i][2] = min{dp[i-1,1],dp[i-1,2]} + 当前树叶是否为r ? 0 : 1
     */
    class Solution2 {
        public int minimumOperations(String leaves) {
            char[] cs = leaves.toCharArray();
            int n = cs.length;
            int[][] dp = new int[n][3];
            dp[0][0] = cost(cs[0], 'r');
            for (int i = 1; i < n; ++i) {
                dp[i][0] = dp[i - 1][0] + cost(cs[i], 'r');
                dp[i][1] = dp[i - 1][0] + cost(cs[i], 'y');
                if (i > 1) {
                    // dp[x][1]在i=1之后才会有值
                    dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + cost(cs[i], 'y'));
                    dp[i][2] = dp[i - 1][1] + cost(cs[i], 'r');
                }
                if (i > 2) {
                    // dp[x][2]在i=2之后才会有值
                    dp[i][2] = Math.min(dp[i][2], dp[i - 1][2] + cost(cs[i], 'r'));
                }
            }
            return dp[n - 1][2];
        }

        private int cost(char a, char b) {
            return a == b ? 0 : 1;
        }
    }

    /**
     * 进一步缩小了dp的空间复杂度
     * https://leetcode-cn.com/u/tian-tang-6/
     */
    class Solution3 {
        public int cost(char a, char b) {
            return a == b ? 0 : 1;
        }

        public int minimumOperations(String leaves) {
            int[] last = new int[4];
            int[] next = new int[4];
            int inf = (int) 1e8;
            Arrays.fill(last, inf);
            last[0] = 0;
            for (char c : leaves.toCharArray()) {
                Arrays.fill(next, inf);
                next[1] = Math.min(last[1] + cost('r', c), last[0] + cost('r', c));
                next[2] = Math.min(last[2] + cost('y', c), last[1] + cost('y', c));
                next[3] = Math.min(last[3] + cost('r', c), last[2] + cost('r', c));
                int[] tmp = last;
                last = next;
                next = tmp;
            }
            return last[3];
        }
    }

    /**
     * 超时
     * O(n^2)
     */
    class Solution {
        public int minimumOperations(String leaves) {
            int ret = 0;
            char[] cs = leaves.toCharArray();
            ArrayList<Integer> list = new ArrayList<>();
            if (cs[0] == 'y') {
                cs[0] = 'r';
                ret++;
            }
            if (cs[cs.length - 1] == 'y') {
                cs[cs.length - 1] = 'r';
                ret++;
            }

            char temp = 'k';
            int cntTemp = 0;
            for (int i = 0; i < cs.length; i++) {
                if (cs[i] != temp && cntTemp != 0) {
                    list.add(cntTemp);
                    cntTemp = 0;
                }
                temp = cs[i];
                cntTemp++;
            }
            if (cntTemp != 0) {
                list.add(cntTemp);
            }


            int[] helper = new int[list.size()];
            int it = 0;
            for (int item : list) {
                helper[it++] = item;
            }

            if (helper.length == 3) {
                return ret;
            }
            if (helper.length == 1) {
                return ret + 1;
            }

            int[] maxSumFromEndJump2 = new int[helper.length];
            int sum = 0;
            for (int i = helper.length - 2; i > 0; i -= 2) {
                sum += helper[i];
                maxSumFromEndJump2[i] = sum;
            }

            int min = Integer.MAX_VALUE;
            int base = 0;
            for (int i = 1; i < helper.length - 1; base += helper[i], i += 2) {
                if (base >= min) {
                    break;
                }
                int base2 = 0;
                for (int j = i + 1; j < helper.length; base2 += helper[j], j += 2) {
                    if (base + base2 >= min) {
                        break;
                    }
                    int base3 = j + 1 >= helper.length ? 0 : maxSumFromEndJump2[j + 1];
                    min = Math.min(base + base2 + base3, min);
                }
            }
            return ret + min;
        }
    }
}

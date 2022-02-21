package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * <p>
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * .....[2],
 * ....[3,4],
 * ...[6,5,7],
 * ..[4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 =.11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用.O(n) 的额外空间（n.为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * @author zm
 */
public class LEET120 {
    public static void main(String[] args) {
        LEET120 l120 = new LEET120();
        System.out.println(l120.new Solution5().minimumTotal(CommonFunctions.stringToIntegerArrayList("[[2],[3,4],[6,5,7],[4,1,8,3]]")));
        System.out.println(l120.new Solution5().minimumTotal(CommonFunctions.stringToIntegerArrayList("[[-10]]")));
    }

    /**
     * 二维数组
     */
    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            int l = triangle.size();
            if (l == 0) {
                return 0;
            }
            if (l == 1) {
                return triangle.get(0).get(0);
            }
            int[][] dp = new int[l][l];
            dp[0][0] = triangle.get(0).get(0);
            int ret = Integer.MAX_VALUE;
            for (int i = 1; i < l; i++) {
                for (int j = 0; j <= i; j++) {
                    int res;
                    if (j == 0) {
                        res = dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
                    } else if (j == i) {
                        res = dp[i - 1][i - 1] + triangle.get(i).get(i);
                    } else {
                        res = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                    }
                    dp[i][j] = res;
                    if (i == l - 1) {
                        ret = Math.min(res, ret);
                    }
                }
            }
            return ret;
        }
    }

    /**
     * 一维数组
     * 还可以自下而上
     */
    class Solution2 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int l = triangle.size();
            if (l == 0) {
                return 0;
            }
            if (l == 1) {
                return triangle.get(0).get(0);
            }
            int[] dp = new int[l];
            dp[0] = triangle.get(0).get(0);
            int ret = Integer.MAX_VALUE;
            for (int i = 1; i < l; i++) {
                for (int j = i; j >= 0; j--) {
                    int cur = triangle.get(i).get(j);
                    int res;
                    if (j == 0) {
                        res = dp[0] + cur;
                    } else if (j == i) {
                        res = dp[i - 1] + cur;
                    } else {
                        res = Math.min(dp[j], dp[j - 1]) + cur;
                    }
                    dp[j] = res;
                    if (i == l - 1) {
                        ret = Math.min(res, ret);
                    }
                }
            }
            return ret;
        }
    }

    /**
     * 试试用forlist
     */
    class Solution3 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int l = triangle.size();
            if (l == 0) {
                return 0;
            }
            if (l == 1) {
                return triangle.get(0).get(0);
            }
            int[] dp = new int[l];
            dp[0] = triangle.get(0).get(0);
            int ret = Integer.MAX_VALUE;
            int i = 0;
            for (List<Integer> tempList : triangle) {
                if (i == 0) {
                    i++;
                    continue;
                }
                for (int j = i; j >= 0; j--) {
                    int cur = tempList.get(j);
                    int res;
                    if (j == 0) {
                        res = dp[0] + cur;
                    } else if (j == i) {
                        res = dp[i - 1] + cur;
                    } else {
                        res = Math.min(dp[j], dp[j - 1]) + cur;
                    }
                    dp[j] = res;
                    if (i == l - 1) {
                        ret = Math.min(res, ret);
                    }
                }
                i++;
            }
            return ret;
        }
    }

    /**
     * 二维数组forlist
     */
    class Solution4 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int l = triangle.size();
            if (l == 0) {
                return 0;
            }
            if (l == 1) {
                return triangle.get(0).get(0);
            }
            int[][] dp = new int[l][l];
            dp[0][0] = triangle.get(0).get(0);
            int ret = Integer.MAX_VALUE;
            int i = 0;
            for (List<Integer> tempList : triangle) {
                if (i == 0) {
                    i++;
                    continue;
                }
                int j = 0;
                for (int cur : tempList) {
                    int res;
                    if (j == 0) {
                        res = dp[i][0] = dp[i - 1][0] + cur;
                    } else if (j == i) {
                        res = dp[i - 1][i - 1] + cur;
                    } else {
                        res = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + cur;
                    }
                    dp[i][j] = res;
                    if (i == l - 1) {
                        ret = Math.min(res, ret);
                    }
                    j++;
                }
                i++;
            }
            return ret;
        }
    }

    class Solution5 {
        private int row;
        private Integer[][] temp;
        private List<List<Integer>> triangle;

        public int minimumTotal(List<List<Integer>> triangle) {
            //自顶向下，不剪枝
            row = triangle.size();
            temp = new Integer[row][row];
            this.triangle = triangle;
            return helper(0, 0);
        }

        private int helper(int level, int c) {

            if (temp[level][c] != null) {
                return temp[level][c];
            }

            if (level == row - 1) {
                return temp[level][c] = triangle.get(level).get(c);
            }

            int left = helper(level + 1, c);
            int right = helper(level + 1, c + 1);

            return temp[level][c] = Math.min(left, right) + triangle.get(level).get(c);
        }

    }
}

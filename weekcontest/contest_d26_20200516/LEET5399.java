package com.zm.LeetCodeEx.weekcontest.contest_d26_20200516;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 双周赛 2020年5月16日
 * <p>
 * 5399. 数位成本和为目标值的最大数字
 * <p>
 * 给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
 * <p>
 * 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
 * 总成本必须恰好等于 target 。
 * 添加的数位中没有数字 0 。
 * 由于答案可能会很大，请你以字符串形式返回。
 * <p>
 * 如果按照上述要求无法得到任何整数，请你返回 "0" 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
 * 输出："7772"
 * 解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "997" 也是满足要求的数字，但 "7772" 是较大的数字。
 * 数字     成本
 * 1  ->   4
 * 2  ->   3
 * 3  ->   2
 * 4  ->   5
 * 5  ->   6
 * 6  ->   7
 * 7  ->   2
 * 8  ->   5
 * 9  ->   5
 * 示例 2：
 * <p>
 * 输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
 * 输出："85"
 * 解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
 * 示例 3：
 * <p>
 * 输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
 * 输出："0"
 * 解释：总成本是 target 的条件下，无法生成任何整数。
 * 示例 4：
 * <p>
 * 输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
 * 输出："32211"
 * <p>
 * <p>
 * 提示：
 * <p>
 * cost.length == 9
 * 1 <= cost[i] <= 5000
 * 1 <= target <= 5000
 *
 * @author zm
 */
public class LEET5399 {
    public static void main(String[] args) {
        LEET5399 l5384 = new LEET5399();
        System.out.println(JSON.toJSONString(l5384.new Solution().largestNumber(new int[]{4, 3, 2, 5, 6, 7, 2, 5, 5}, 9)));
        System.out.println(JSON.toJSONString(l5384.new Solution().largestNumber(new int[]{7, 6, 5, 5, 5, 6, 8, 7, 8}, 12)));
        System.out.println(JSON.toJSONString(l5384.new Solution().largestNumber(new int[]{2, 4, 6, 2, 4, 6, 4, 4, 4}, 5)));
        System.out.println(JSON.toJSONString(l5384.new Solution().largestNumber(new int[]{6, 10, 15, 40, 40, 40, 40, 40, 40}, 47)));
        System.out.println(JSON.toJSONString(l5384.new Solution().largestNumber(new int[]{5, 6, 7, 3, 4, 6, 7, 4, 8}, 29)));
    }

    /**
     * 超时了
     */
    class Solution {
        private HashMap<Integer, PriorityQueue<Integer>> map;
        private int[] sortedCost;
        private ArrayList<Integer> retList;
        private int maxLength = 0;

        public String largestNumber(int[] cost, int target) {
            map = new HashMap<>(cost.length);
            for (int i = 0; i < cost.length; i++) {
                if (map.containsKey(cost[i])) {
                    map.get(cost[i]).add(i);
                } else {
                    PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
                    pq.add(i);
                    map.put(cost[i], pq);
                }
            }
            Arrays.sort(cost);
            sortedCost = cost;
            ArrayList<Integer> curUsed = new ArrayList<>();
            back(curUsed, target);
            if (retList == null || retList.isEmpty()) {
                return "0";
            }
            retList.sort((o1, o2) -> o2 - o1);
            StringBuilder sb = new StringBuilder();
            for (Integer i : retList) {
                sb.append(i + 1);
            }
            return sb.toString();
        }

        private boolean back(ArrayList<Integer> curUsed, int target) {
            Integer pre = null;
            for (int i = 0; i < sortedCost.length; i++) {
                if (pre != null && sortedCost[i] == pre) {
                    continue;
                } else {
                    pre = sortedCost[i];
                }
                if (target < sortedCost[i]) {
                    return false;
                }
                curUsed.add(map.get(sortedCost[i]).peek());
                int t = target - sortedCost[i];
                if (t == 0) {
                    if (curUsed.size() < maxLength) {

                    } else if (curUsed.size() > maxLength) {
                        retList = new ArrayList<>(curUsed);
                        maxLength = curUsed.size();
                    } else {
                        if (!compare(retList, curUsed)) {
                            retList = new ArrayList<>(curUsed);
                        }
                    }
                    curUsed.remove(curUsed.size() - 1);
                    return true;
                }
                back(curUsed, t);
                curUsed.remove(curUsed.size() - 1);
            }
            return false;
        }

        private boolean compare(ArrayList<Integer> retList, ArrayList<Integer> curUsed) {
            int l = retList.size();
            for (int i = 0; i < l; i++) {
                if (retList.get(i) > curUsed.get(i)) {
                    return true;
                } else if (retList.get(i) < curUsed.get(i)) {
                    return false;
                }
            }
            return true;
        }
    }
}



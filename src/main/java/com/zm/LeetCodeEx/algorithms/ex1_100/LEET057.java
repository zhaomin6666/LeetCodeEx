package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 57. 插入区间
 * <p>
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]<br>
 * 输出: [[1,5],[6,9]]
 * <p>
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]<br>
 * 输出: [[1,2],[3,10],[12,16]]<br>
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * @author zm
 */
public class LEET057 {
    public static void main(String[] args) {
        LEET057 l057 = new LEET057();
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newIntervals = {4, 8};
        int[][] intervals2 = {{1, 3}, {6, 9}};
        int[] newIntervals2 = {2, 5};
        int[][] intervals3 = {{1, 2}};
        int[] newIntervals3 = {4, 8};
        System.out.println(JSON.toJSONString(l057.insert2(intervals, newIntervals)));
        System.out.println(JSON.toJSONString(l057.insert2(intervals2, newIntervals2)));
        System.out.println(JSON.toJSONString(l057.insert2(intervals3, newIntervals3)));
    }

    /**
     * 转成list之后做插入操作
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> list = new LinkedList<>();
        Collections.addAll(list, intervals);
        Iterator<int[]> iterator = list.iterator();
        int i = 0;
        boolean leftIsCheck = false;
        boolean isAdd = false;
        while (iterator.hasNext() && !isAdd) {
            int[] cur = iterator.next();
            if (leftIsCheck) {
                if (cur[1] < newInterval[1]) {
                    iterator.remove();
                    i--;
                } else if (cur[0] <= newInterval[1] && newInterval[1] <= cur[1]) {
                    cur[0] = newInterval[0];
                    isAdd = true;
                } else {
                    list.add(i, newInterval);
                    isAdd = true;
                }
            } else {
                if (newInterval[0] < cur[0]) {
                    if (newInterval[1] < cur[0]) {
                        list.add(i, newInterval);
                        isAdd = true;
                    } else if (newInterval[1] >= cur[0] && newInterval[1] <= cur[1]) {
                        cur[0] = newInterval[0];
                        isAdd = true;
                    } else {
                        iterator.remove();
                        leftIsCheck = true;
                        i--;
                    }

                } else if (cur[0] <= newInterval[0] && newInterval[0] <= cur[1]) {
                    if (newInterval[1] < cur[1]) {
                        isAdd = true;
                    } else {
                        iterator.remove();
                        newInterval[0] = cur[0];
                        leftIsCheck = true;
                        i--;
                    }
                }
            }
            i++;
        }
        if (!isAdd) {
            list.add(newInterval);
        }
        return list.toArray(new int[list.size()][2]);
    }

    /**
     * 完全可以用一个新的List，遍历原来的数组然后一个个插入进去，不需要先变成list再对这个list进行操作
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> retList = new LinkedList<>();
        boolean isAdd = false;
        for (int i = 0; i < intervals.length; i++) {
            if (!isAdd) {
                if (newInterval[1] < intervals[i][0]) {
                    retList.add(newInterval);
                    retList.add(intervals[i]);
                    isAdd = true;
                } else if (intervals[i][0] <= newInterval[1] && newInterval[1] <= intervals[i][1]) {
                    intervals[i][0] = Math.min(intervals[i][0], newInterval[0]);
                    retList.add(intervals[i]);
                    isAdd = true;
                } else if (newInterval[0] <= intervals[i][1] && intervals[i][1] <= newInterval[1]) {
                    newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                } else {
                    retList.add(intervals[i]);
                }
            } else {
                retList.add(intervals[i]);
            }
        }
        if (!isAdd) {
            retList.add(newInterval);
        }
        return retList.toArray(new int[retList.size()][2]);
    }

}

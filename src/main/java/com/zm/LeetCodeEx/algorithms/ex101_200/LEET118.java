package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 118. 杨辉三角
 * <p>
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * .....[1],
 * ....[1,1],
 * ...[1,2,1],
 * ..[1,3,3,1],
 * .[1,4,6,4,1]
 * ]
 *
 * @author zm
 */
public class LEET118 {
    public static void main(String[] args) {
        LEET118 l118 = new LEET118();
        System.out.println(l118.new Solution().generate(5));
        System.out.println(l118.new Solution().generate(10));
    }

    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ret = new LinkedList<>();
            if (numRows == 0) {
                return ret;
            }
            LinkedList<Integer> pre = new LinkedList<>();
            pre.add(1);
            ret.add(pre);
            for (int i = 1; i < numRows; i++) {
                LinkedList<Integer> temp = new LinkedList<>();
                temp.add(1);
                int preInt = pre.getFirst();
                int index = 0;
                for (Integer tempInt : pre) {
                    if (index++ == 0) {
                        continue;
                    }
                    temp.add(tempInt + preInt);
                    preInt = tempInt;
                }
                temp.add(1);
                ret.add(temp);
                pre = temp;
            }
            return ret;
        }
    }

    /**
     * 改用ArrayList实现
     */
    class Solution2 {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ret = new ArrayList<>();
            if (numRows == 0) {
                return ret;
            }
            List<Integer> prevRow = new ArrayList<>();
            prevRow.add(1);
            ret.add(prevRow);
            for (int rowNum = 1; rowNum < numRows; rowNum++) {
                List<Integer> row = new ArrayList<>();
                row.add(1);
                for (int j = 1; j < rowNum; j++) {
                    row.add(prevRow.get(j - 1) + prevRow.get(j));

                }
                row.add(1);
                ret.add(row);
                prevRow = row;
            }
            return ret;
        }
    }
}

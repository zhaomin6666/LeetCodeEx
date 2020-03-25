package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 * <p>
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * @author zm
 */
public class LEET077 {
    public static void main(String[] args) {
        LEET077 l077 = new LEET077();
        System.out.println(l077.new Solution2().combine(4, 2));
    }

    /**
     * 回溯法
     */
    class Solution {
        private List<List<Integer>> ret = new LinkedList<>();
        private int n;
        private int k;

        public List<List<Integer>> combine(int n, int k) {
            this.n = n;
            this.k = k;
            for (int i = 1; i <= n; i++) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                helper(list, i);
            }
            return ret;
        }

        private void helper(LinkedList<Integer> list, int i) {
            if (list.size() == k) {
                ret.add(new LinkedList<>(list));
            } else {
                for (int j = i + 1; j <= n; j++) {
                    list.add(j);
                    helper(list, j);
                    list.removeLast();
                }
            }
        }
    }

    /**
     * 官方题解，字典序（二进制序）
     * 4,3,2,1
     * 0,0,1,1 ==>  2,1
     * 4,3,2,1
     * 0,1,0,1 ==>  3,1
     * 4,3,2,1
     * 0,1,1,0 ==>  3,2
     * 4,3,2,1
     * 1,0,0,1 ==>  4,1
     * 4,3,2,1
     * 1,0,1,0 ==>  4,2
     * 4,3,2,1
     * 1,1,0,0 ==>  4,3
     * <p>
     * 实现方法：初始化[1,2,5]
     * <p>
     * j=0 取出[1,2]
     * {这里是while循环部分}此时1,2相连则把相连的第一个数设为j+1，变为[1,2,5]，并且j++
     * 跳出循环，把j下表的+1  变为[1,3,5]
     * j=0 取出[1,3]，此时没有数字相连，略过循环，把j下表的+1  变为[2,3,5]
     * j=0 取出[2,3]
     * {这里是while循环部分}此时2,3相连则把相连的第一个数设为j+1，变为[1,3,5]，并且j++
     * 跳出循环，把j下表的+1  变为[1,4,5]
     * j=0 取出[1,4]，此时没有数字相连，略过循环，把j下表的+1  变为[2,4,5]
     * j=0 取出[2,4]，此时没有数字相连，略过循环，把j下表的+1  变为[3,4,5]
     * j=0 取出[3,4]
     * {这里是while循环部分}此时3,4相连则把相连的第一个数设为j+1，变为[1,4,5]，并且j++
     * {这里是while循环部分}此时4,5相连则把相连的第一个数设为j+1，变为[1,2,5]，并且j++
     * 跳出循环，把j下表的+1  变为[1,2,6]
     * 此时j=2 等于k结束
     */
    class Solution2 {
        public List<List<Integer>> combine(int n, int k) {
            // init first combination
            LinkedList<Integer> nums = new LinkedList<>();
            for (int i = 1; i < k + 1; ++i)
                nums.add(i);
            nums.add(n + 1);

            List<List<Integer>> output = new ArrayList<>();
            int j = 0;
            while (j < k) {
                // add current combination
                output.add(new LinkedList<>(nums.subList(0, k)));
                // increase first nums[j] by one
                // if nums[j] + 1 != nums[j + 1]
                j = 0;
                while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
                    nums.set(j, j++ + 1);
                nums.set(j, nums.get(j) + 1);
            }
            return output;
        }
    }
}

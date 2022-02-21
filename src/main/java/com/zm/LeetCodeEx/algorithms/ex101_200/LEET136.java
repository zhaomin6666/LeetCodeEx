package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * 136. 只出现一次的数字
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author zm
 */
public class LEET136 {
    public static void main(String[] args) {
        LEET136 l136 = new LEET136();
        System.out.println(l136.new Solution2().singleNumber(new int[]{2, 2, 1}));
        System.out.println(l136.new Solution2().singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    class Solution {
        public int singleNumber(int[] nums) {
            HashSet<Integer> set1 = new HashSet<>();
            for (int num : nums) {
                if (set1.contains(num)) {
                    set1.remove(num);
                } else {
                    set1.add(num);
                }
            }
            for (int ret : set1) {
                return ret;
            }
            return 0;
        }
    }

    class Solution2 {
        public int singleNumber(int[] nums) {
            int ret = 0;
            for (int num : nums) {
                ret ^= num;
            }
            return ret;
        }
    }
}

package com.zm.LeetCodeEx.algorithms.ex401_500;

import java.util.*;

/**
 * 456. 132 模式
 * <p>
 * 给定一个含有正整数和负整数的环形数组 nums。 如果某个索引中的数 k 为正数，则向前移动 k 个索引。相反，如果是负数 (-k)，则向后移动
 * k 个索引。因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素。
 * <p>
 * 确定 nums 中是否存在循环（或周期）。循环必须在相同的索引处开始和结束并且循环长度 >
 * 1。此外，一个循环中的所有运动都必须沿着同一方向进行。换句话说，一个循环中不能同时包括向前的运动和向后的运动。  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,-1,1,2,2] 输出：true 解释：存在循环，按索引 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：[-1,2] 输出：false 解释：按索引 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1
 * 。根据定义，循环的长度必须大于 1 。
 * <p>
 * 示例 3:
 * <p>
 * 输入：[-2,1,-1,-2,-2] 输出：false 解释：按索引 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为按索引 1 -> 2
 * 的运动是向前的运动，而按索引 2 -> 1 的运动是向后的运动。一个循环中的所有运动都必须沿着同一方向进行。
 *
 * @author zm
 */
public class LEET456 {
    public static void main(String[] args) {
        LEET456 l456 = new LEET456();
        int[] nums1 = {1, 2, 3, 4};
        System.out.println(l456.new Solution3().find132pattern(nums1));
        // false

        int[] nums2 = {3, 1, 4, 2};
        System.out.println(l456.new Solution3().find132pattern(nums2));
        // true

        int[] nums3 = {-1, 3, 2, 0};
        System.out.println(l456.new Solution3().find132pattern(nums3));
        // true

        int[] nums4 = {1, 0, 1, -4, -3};
        System.out.println(l456.new Solution3().find132pattern(nums4));
        // false

        int[] nums5 = {1, 3, 2, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(l456.new Solution3().find132pattern(nums5));
        // true

        int[] nums6 = {3, 5, 0, 3, 4};
        System.out.println(l456.new Solution3().find132pattern(nums6));
        // true

        int[] nums7 = {-2, 1, 1, -2, 1, 1};
        System.out.println(l456.new Solution3().find132pattern(nums7));
        // false

    }

    /**
     * 枚举3，使用树结构保存右侧所有值和出现次数，左侧仅保存最小值。
     * 每遍历一个3，在右侧找比左侧1最小值大的最小值和3比较，如果比3小即找到目标值。
     * 没有找到则从右侧树中删除该3，并更新左侧1的最小值。
     */
    class Solution {
        public boolean find132pattern(int[] nums) {
            int len = nums.length;
            if (len < 3) {
                return false;
            }
            // left
            int minLeft = nums[0];

            // right 保存右侧所有数据，值+出现次数
            // O(nlogn)
            TreeMap<Integer, Integer> rightAll = new TreeMap<>();
            for (int i = 2; i < len; i++) {
                rightAll.put(nums[i], rightAll.getOrDefault(nums[i], 0) + 1);
            }

            // O(nlogn)
            for (int i = 1; i < len - 1; i++) {
                if (minLeft < nums[i]) {
                    // 获取比minLeft大的最小值
                    // ceilingKey -> ceilingEntry:returns the entry for the least key greater than the specified key
                    Integer next = rightAll.ceilingKey(minLeft + 1);
                    if (next != null && next < nums[i]) {
                        return true;
                    }
                }
                // 维护左侧最小值
                minLeft = Math.min(minLeft, nums[i]);
                // 右侧的Map中去掉当前值
                rightAll.put(nums[i + 1], rightAll.get(nums[i + 1]) - 1);
                if (rightAll.get(nums[i + 1]) == 0) {
                    rightAll.remove(nums[i + 1]);
                }
            }
            return false;
        }
    }

    /**
     * 遍历132中的1，从后往前遍历。我们在遍历过程需要保存遍历过的2和3的值，而且2和3的值越大越好，这样才能更可能的找到1。
     * 同时由于2<3，所以在找到一个更大的3之后，所有遍历过的数里面小于3的值都可以做2，保存其中最大的一个2即可。
     * 使用单调栈保存3，保证栈中元素递减，每遍历到一个更大的3，从栈中踢出小于当前3的值，并更新2的最大值。
     *
     * Deque会比Stack效率高
     */
    class Solution2 {
        public boolean find132pattern(int[] nums) {
            int len = nums.length;
            if (len < 3) {
                return false;
            }

            // 单调栈保存3
            Deque<Integer> stack = new ArrayDeque<>();
            // 变量保存最大的2
            int maxTwo = Integer.MIN_VALUE;

            for (int i = len - 1; i >= 0; i--) {
                int cur = nums[i];
                if (cur < maxTwo) {
                    return true;
                }
                while (!stack.isEmpty() && stack.peekLast() < cur) {
                    maxTwo = Math.max(maxTwo, stack.pollLast());
                }
                stack.addLast(cur);
            }
            return false;
        }
    }

    /**
     * 遍历132中的2。由于2<3，所以对于3来说直接用一个单调递减的栈保存即可。
     * 只要遍历到一个比较大的2，那么他就有资格做更大的3（除非是最后一个）。
     * 那么如果在单调栈中存在有比当前2大的3，那么就要从这个3的下标开始往前找最小的1是否小于当前2。
     * 所以需要用一个列表来保存从0到n中的最小值，用于快速找1。
     */
    class Solution3 {
        public boolean find132pattern(int[] nums) {
            int len = nums.length;
            if (len < 3) {
                return false;
            }

            Deque<Integer> stackThree = new ArrayDeque<>();
            ArrayList<Integer> minOne = new ArrayList<>();

            // 在遍历2的时候，获取到3的下标i检查的1最小值是[0,i-1]，所以这边list补一位。
            // 当需要取到List.get(0)的时候，3下标为0，此时根本没有1的位置，肯定不符合。
            // 当然这边也可以不加，然后在后面if (!stackThree.isEmpty() && minOne.get(stackThree.peek()) < cur) {中进行判断。
            minOne.add(Integer.MAX_VALUE);

            for (int i = 0; i < len; i++) {
                int cur = nums[i];
                while (!stackThree.isEmpty() && nums[stackThree.peekLast()] <= cur) {
                    stackThree.pollLast();
                }
                if (!stackThree.isEmpty() && minOne.get(stackThree.peekLast()) < cur) {
                    return true;
                }
                stackThree.addLast(i);
                if (i == 0) {
                    minOne.add(cur);
                }
                else {
                    minOne.add(Math.min(minOne.get(minOne.size() - 1), cur));
                }
            }
            return false;
        }
    }

}

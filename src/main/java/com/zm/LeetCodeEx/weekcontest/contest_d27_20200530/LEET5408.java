package com.zm.LeetCodeEx.weekcontest.contest_d27_20200530;

import java.util.HashMap;
import java.util.Map;

/**
 * 双周赛 2020年5月30日
 * <p>
 * 5408. 通过翻转子数组使两个数组相等
 * <p>
 * 给你两个长度相同的整数数组 target 和 arr 。
 * <p>
 * 每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
 * <p>
 * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = [1,2,3,4], arr = [2,4,1,3]
 * 输出：true
 * 解释：你可以按照如下步骤使 arr 变成 target：
 * 1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
 * 2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
 * 3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
 * 上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
 * 示例 2：
 * <p>
 * 输入：target = [7], arr = [7]
 * 输出：true
 * 解释：arr 不需要做任何翻转已经与 target 相等。
 * 示例 3：
 * <p>
 * 输入：target = [1,12], arr = [12,1]
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：target = [3,7,9], arr = [3,7,11]
 * 输出：false
 * 解释：arr 没有数字 9 ，所以无论如何也无法变成 target 。
 * 示例 5：
 * <p>
 * 输入：target = [1,1,1,1,1], arr = [1,1,1,1,1]
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * target.length == arr.length
 * 1 <= target.length <= 1000
 * 1 <= target[i] <= 1000
 * 1 <= arr[i] <= 1000
 *
 * @author zm
 */
public class LEET5408 {
    public static void main(String[] args) {
        LEET5408 l5408 = new LEET5408();
        System.out.println(l5408.new Solution().canBeEqual(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3}));
    }

    class Solution {
        public boolean canBeEqual(int[] target, int[] arr) {
            HashMap<Integer, Integer> mapArr = cal(arr);
            HashMap<Integer, Integer> mapTarget = cal(target);
            for (Map.Entry<Integer, Integer> entry : mapArr.entrySet()) {
                if (!mapTarget.containsKey(entry.getKey()) || mapTarget.get(entry.getKey()) != entry.getValue()) {
                    return false;
                }
            }
            return true;
        }

        public HashMap<Integer, Integer> cal(int[] target) {
            HashMap<Integer, Integer> ret = new HashMap<>(target.length);
            for (int i = 0; i < target.length; i++) {
                int num = target[i];
                if (ret.containsKey(num)) {
                    ret.put(num, ret.get(num) + 1);
                } else {
                    ret.put(num, 1);
                }
            }
            return ret;
        }
    }
}

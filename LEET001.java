package com.zm.LeetCodeEx;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * @author zm
 */
public class LEET001 {
    public static void main(String[] args) {
        LEET001 l001 = new LEET001();
        int[] nums = {2, 7, 11, 15};
        int[] result = l001.twoSum(nums, 9);
        System.out.println(JSON.toJSONString(result));
    }

    /**
     * 一遍哈希表
     * 在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。如果它存在，那我们已经找到了对应解，并立即将其返回。
     * 时间复杂度：O(n)， 我们只遍历了包含有 n个元素的列表一次。在表中进行的每次查找只花费 O(1)的时间。 空间复杂度：O(n)，
     * 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 n个元素
     *
     * @param nums 传入数组
     * @param target 目标和
     * @return
     */
    private int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num2 = target - nums[i];
            if (hashMap.containsKey(num2)) {
                result[1] = i;
                result[0] = hashMap.get(num2);
                break;
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return result;
    }
}
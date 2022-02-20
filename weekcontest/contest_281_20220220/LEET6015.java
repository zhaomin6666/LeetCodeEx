package com.zm.LeetCodeEx.weekcontest.contest_281_20220220;

import java.util.HashMap;
import java.util.Map;

/**
 * 6015. 统计可以被 K 整除的下标对数目
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums 和一个整数 k ，返回满足下述条件的下标对 (i, j) 的数目：
 * <p>
 * 0 <= i < j <= n - 1 且
 * nums[i] * nums[j] 能被 k 整除。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4,5], k = 2
 * 输出：7
 * 解释：
 * 共有 7 对下标的对应积可以被 2 整除：
 * (0, 1)、(0, 3)、(1, 2)、(1, 3)、(1, 4)、(2, 3) 和 (3, 4)
 * 它们的积分别是 2、4、6、8、10、12 和 20 。
 * 其他下标对，例如 (0, 2) 和 (2, 4) 的乘积分别是 3 和 15 ，都无法被 2 整除。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4], k = 5
 * 输出：0
 * 解释：不存在对应积可以被 5 整除的下标对。
 */
public class LEET6015 {
    public static void main(String[] args) {
        //System.out.println(new Solution2().coutPairs(new int[]{1, 2, 3, 4, 5}, 2));
        //System.out.println(new Solution2().coutPairs(new int[]{1, 2, 3, 4}, 5));
        System.out.println(new Solution2().coutPairs(new int[]{8, 10, 2, 5, 9, 6, 3, 8, 2}, 6));
    }

    /**
     * 暴力解法
     */
    static class Solution {
        public long coutPairs(int[] nums, int k) {
            long result = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] * nums[j] % k == 0) {
                        result++;
                    }
                }
            }
            return result;
        }
    }

    /**
     * 使用最大公约数
     * 所有数分为两种情况：情况一，本身就可以被k整除；情况二，两个数相乘（自身平方）才可以被k整除。
     * 情况一比较好理解，对于本身就可以被整除的数，直接用数量乘以其他数数量+本身相乘的可能性
     * 情况二重点理解。如果a*b可以被k整除，那么k做了因式分解之后，部分因子在a中，部分因子在b中。
     * 所以取a和k的最大公约数，k中除了该最大公约数中的因子都应该从b中产出。
     * 记录情况二下（1<gcd<k）所有的最大公约数并计数。当gcd(a,k) * gcd(b,k)可以被k整除的时候，说明b中因子满足了构成k的部分。
     */
    static class Solution2 {
        public long coutPairs(int[] nums, int k) {
            long result = 0;
            int cnt = 0;
            Map<Integer, Integer> gcdCntMap = new HashMap<>();
            for (int num : nums) {
                // 记录本身就可以整除的
                if (num % k == 0) {
                    cnt++;
                }
                // 本身不能整除的记录num和k的最大公约数
                int gcd = gcd(num, k);
                if (gcd > 1 && gcd < k) {
                    gcdCntMap.compute(gcd, (key, value) -> value == null ? 1 : value + 1);
                }
            }
            result += (long) cnt * (nums.length - cnt);
            result += (long) (1 + cnt - 1) * (cnt - 1) / 2;

            // 判断两个（或自己和自己）公约数相乘能否被k整除
            // 其中两个相乘a*b和b*a会计算两遍，最后需要除以2
            long temp = 0;
            for (Map.Entry<Integer, Integer> entry1 : gcdCntMap.entrySet()) {
                for (Map.Entry<Integer, Integer> entry2 : gcdCntMap.entrySet()) {
                    if ((entry1.getKey() * entry2.getKey()) % k == 0) {
                        if (entry1.getKey().equals(entry2.getKey())) {
                            result += (long) entry1.getValue() * (entry1.getValue() - 1) / 2;
                        }
                        else {
                            temp += (long) entry1.getValue() * entry2.getValue();
                        }
                    }
                }
            }
            result += temp / 2;
            return result;
        }

        private int gcd(int i, int j) {
            return j != 0 ? gcd(j, i % j) : i;
        }
    }
}

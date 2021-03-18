package com.zm.LeetCodeEx.algorithms.ex301_400;

import com.alibaba.fastjson.JSON;

/**
 * 338. 比特位计数
 * <p>
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * @author zm
 */
public class LEET338 {
    public static void main(String[] args) {
        LEET338 l338 = new LEET338();
        System.out.println(JSON.toJSONString(l338.new Solution().countBits(2)));
        System.out.println(JSON.toJSONString(l338.new Solution().countBits(3)));
        System.out.println(JSON.toJSONString(l338.new Solution().countBits(4)));

    }

    /**
     * 每2^n的时候，值均为1。 cnt[2^n+i] = cnt[i] + 1
     */
    class Solution {
        public int[] countBits(int num) {
            int[] result = new int[num + 1];
            if (num == 0) {
                return result;
            }
            result[1] = 1;
            if (num == 1) {
                return result;
            }
            int offset = 0;
            int threshold = 2 << offset;
            int index = 0;
            for (int i = 2; i <= num; i++) {
                if (i == threshold) {
                    threshold = 2 << ++offset;
                    result[i] = 1;
                    index = 0;
                } else {
                    result[i] = 1 + result[++index];
                }
            }
            return result;
        }
    }

    /**
     * 官方题解：dp 最高有效位
     * 对于满足等于2^n的数 充分必要条件 x & (x-1) = 0  如 1000 & (0)111 = 0
     */
    class Solution2 {
        public int[] countBits(int num) {
            int[] bits = new int[num + 1];
            int highBit = 0;
            for (int i = 1; i <= num; i++) {
                if ((i & (i - 1)) == 0) {
                    highBit = i;
                }
                bits[i] = bits[i - highBit] + 1;
            }
            return bits;
        }
    }

    /**
     * 官方题解：dp 最低有效位
     * <p>
     * 如果x是偶数，则 bits[x]=bits[x/2]  如6->110 = 3->11 = 共2个1
     * <p>
     * 如果x是奇数，则 bits[x]=bits[x/2]+1  如7->111 = 3->11 = 共2个1再加1等于3
     */
    class Solution3 {
        public int[] countBits(int num) {
            int[] bits = new int[num + 1];
            for (int i = 1; i <= num; i++) {
                bits[i] = bits[i >> 1] + (i & 1);
            }
            return bits;
        }
    }

    /**
     * 官方题解：dp 最低设置位
     * <p>
     * 定义正整数 x 的「最低设置位」为 x 的二进制表示中的最低的 1 所在位。
     * 例如，10 的二进制表示是 1010，其最低设置位为 2，对应的二进制表示是 10
     * <p>
     * 令 y=x&(x-1)，则 y 为将 x 的最低设置位从 1 变成 0 之后的数，显然 0≤y<x，bits[x]=bits[y]+1。
     * 因此对任意正整数 x，都有 bits[x]=bits[x&(x−1)]+1。
     * <p>
     * 遍历从 1 到 num 的每个正整数 ii，计算 bits 的值。最终得到的数组 bits 即为答案。
     */
    class Solution4 {
        public int[] countBits(int num) {
            int[] bits = new int[num + 1];
            for (int i = 1; i <= num; i++) {
                bits[i] = bits[i >> 1] + (i & 1);
            }
            return bits;
        }
    }

}

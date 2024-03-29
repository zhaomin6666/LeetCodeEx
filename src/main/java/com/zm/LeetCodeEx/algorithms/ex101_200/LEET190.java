package com.zm.LeetCodeEx.algorithms.ex101_200;

/**
 * 190. 颠倒二进制位
 * 颠倒给定的 32 位无符号整数的二进制位。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 * <p>
 * <p>
 * 进阶:
 * 如果多次调用这个函数，你将如何优化你的算法？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: 00000010100101000001111010011100
 * 输出: 00111001011110000010100101000000
 * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 * 因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * 示例 2：
 * <p>
 * 输入：11111111111111111111111111111101
 * 输出：10111111111111111111111111111111
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 * 因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
 * 示例 1：
 * <p>
 * 输入：n = 00000010100101000001111010011100
 * 输出：964176192 (00111001011110000010100101000000)
 * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 * 因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * 示例 2：
 * <p>
 * 输入：n = 11111111111111111111111111111101
 * 输出：3221225471 (10111111111111111111111111111111)
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 * 因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 输入是一个长度为 32 的二进制字符串
 *
 * @author zm
 */
public class LEET190 {
    public static void main(String[] args) {
        LEET190 l190 = new LEET190();
        System.out.println(l190.new Solution2().reverseBits(0b00000010100101000001111010011100));
        System.out.println(l190.new Solution2().reverseBits(0b11111111111111111111111111111101));
    }

    /**
     * 循环从最后一位开始
     * 由于java中int是带符号的，所以需要使用逻辑右移>>>
     */
    public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int ret = n & 1;
            for (int i = 0; i < 31; i++) {
                ret = ret << 1;
                n = n >>> 1;
                if ((n & 1) == 1) {
                    ret += 1;
                }
            }
            return ret;
        }
    }

    /**
     * 官方题解
     * <p>
     * 采用分治法
     * 对于一个32长度的二进制数，拆分成0~15记为a1，16~31记为b1
     * 把0~15做颠倒为15~0记为a2，把16~31做颠倒为31~16记为b2，然后交换a2和b2位置，将b2和a2接到一起就是31~0
     * 同理，0~15可以继续拆分为0~7和8~15。最小计算单元为交换0~1
     * 交换的代码就是n >>> 1 & M1 | (n & M1) << 1;
     */
    public class Solution2 {
        private static final int M1 = 0x55555555; // 01010101010101010101010101010101
        private static final int M2 = 0x33333333; // 00110011001100110011001100110011
        private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
        private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

        public int reverseBits(int n) {
            n = n >>> 1 & M1 | (n & M1) << 1;
            n = n >>> 2 & M2 | (n & M2) << 2;
            n = n >>> 4 & M4 | (n & M4) << 4;
            n = n >>> 8 & M8 | (n & M8) << 8;
            return n >>> 16 | n << 16;
        }
    }

}

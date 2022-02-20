package com.zm.LeetCodeEx.algorithms.ex701_800;

/**
 * 717. 1比特与2比特字符
 * 有两种特殊字符：
 * <p>
 * 第一种字符可以用一个比特 0 来表示
 * 第二种字符可以用两个比特(10 或 11)来表示、
 * 给定一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一位字符，则返回 true 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: bits = [1, 0, 0]
 * 输出: true
 * 解释: 唯一的编码方式是一个两比特字符和一个一比特字符。
 * 所以最后一个字符是一比特字符。
 * 示例 2:
 * <p>
 * 输入: bits = [1, 1, 1, 0]
 * 输出: false
 * 解释: 唯一的编码方式是两比特字符和两比特字符。
 * 所以最后一个字符不是一比特字符。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= bits.length <= 1000
 * bits[i] == 0 or 1
 *
 * @author zm
 */
public class LEET717 {
    public static void main(String[] args) {
        System.out.println(new Solution().isOneBitCharacter(
                new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        System.out.println(new Solution().isOneBitCharacter(
                new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70}));
    }


    /**
     * 正序遍历，遇到0说明是第一种，往后走一位，遇到1说明是第二种（10/11），往后走两位。该步骤可以简化写为 i += bits[i] + 1;
     * 如果最后是第二种，那么往后走两位就出界了，i==n，只有最后是第一种的时候，走一位可以让 i==n-1。
     */
    static class Solution {
        public boolean isOneBitCharacter(int[] bits) {
            int n = bits.length, i = 0;
            while (i < n - 1) {
                i += bits[i] + 1;
            }
            return i == n - 1;
        }
    }

    /**
     * 倒序遍历，判断最后一个0和倒数第二个0之间有多少个1即可。
     * 01110 如果1的个数为奇数，那么必然分割为 0 11 10，为第二种情况
     * 011110 如果1的个数为偶数，那么必然分割成 0 11 11 0，为第一种情况
     */
    static class Solution2 {
        public boolean isOneBitCharacter(int[] bits) {
            int n = bits.length, i = n - 2;
            while (i >= 0 && bits[i] == 1) {
                i--;
            }
            return (n - i) % 2 == 0;
        }
    }
}

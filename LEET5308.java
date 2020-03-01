package com.zm.LeetCodeEx;

/**
 * 周赛 2020年1月12日 5308. 或运算的最小翻转次数
 * <p>
 * 给你三个正整数 a、b 和 c。
 * <p>
 * 你可以对 a 和 b 的二进制表示进行位翻转操作，返回能够使按位或运算   a OR b == c  成立的最小翻转次数。
 * <p>
 * 「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 2, b = 6, c = 5
 * 输出：3
 * 解释：翻转后 a = 1 , b = 4 , c = 5 使得 a OR b == c
 * 示例 2：
 * <p>
 * 输入：a = 4, b = 2, c = 7
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：a = 1, b = 2, c = 3
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 1 <= a <= 10^9
 * 1 <= b <= 10^9
 * 1 <= c <= 10^9
 *
 * @author zm
 */
public class LEET5308 {
    public static void main(String[] args) {
        LEET5308 l5308 = new LEET5308();
        System.out.println(l5308.minFlips(2, 6, 5));
        System.out.println(l5308.minFlips(4, 2, 7));
        System.out.println(l5308.minFlips(1, 2, 3));
    }

    public int minFlips(int a, int b, int c) {
        int cnt = 0;
        while (a > 0 || b > 0 || c > 0) {
            int aLast = a % 2;
            int bLast = b % 2;
            int cLast = c % 2;
            if (aLast + bLast > 0) {
                if (cLast == 0) {
                    cnt += aLast + bLast;
                }
            } else {
                if (cLast != 0) {
                    cnt++;
                }
            }
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        return cnt;
    }
}

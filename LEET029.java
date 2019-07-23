package com.zm.LeetCodeEx;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3 输出: 3
 * <p>
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3 输出: -2
 * <p>
 * 说明: 被除数和除数均为 32 位有符号整数。 除数不为 0。 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 −
 * 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 * @author zm
 */
public class LEET029 {
    public static void main(String[] args) {
        LEET029 l029 = new LEET029();
        int dividend1 = 10;
        int divisor1 = 3;
        int dividend2 = 2147483647;
        int divisor2 = 1;

        System.out.println(l029.divide(dividend1, divisor1));
        System.out.println(l029.divide(dividend2, divisor2));
    }

    /**
     * 由于不能超过int大小限制，做减法又需要统一符号，所以统一换成负数。然后循环不断减去除数，得出答案
     * @param dividend 被除数
     * @param divisor 除数
     * @return int 商
     */
    public int divide(int dividend, int divisor) {
        boolean isPositive = true;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            isPositive = false;
        }
        int ans = 0;
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }
        while (dividend <= divisor) {
            if (dividend == 0) {
                break;
            }
            dividend -= divisor;
            ans++;
        }
        if (isPositive) {
            // 如果本应是正数，但是得到的是负数，那么ans=2^32，超过了限制条件，所以返回2^32-1
            if (ans < 0) {
                return ans - 1;
            }
            return ans;
        } else {
            return -ans;
        }
    }
}

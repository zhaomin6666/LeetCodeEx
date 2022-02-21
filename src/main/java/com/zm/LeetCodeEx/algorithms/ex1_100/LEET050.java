package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

/**
 * 50. Pow(x, n)
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: 2.00000, 10
 * <p>
 * 输出: 1024.00000
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: 2.10000, 3
 * <p>
 * 输出: 9.26100
 * <p>
 * 示例 3:
 * <p>
 * <p>
 * 输入: 2.00000, -2
 * <p>
 * 输出: 0.25000
 * <p>
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * <p>
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * <p>
 *
 * @author zm
 */
public class LEET050 {
    public static void main(String[] args) {
        LEET050 l050 = new LEET050();
        double x1 = 2.0;
        int n1 = 10;
        double x2 = 2.1;
        int n2 = 3;
        System.out.println(JSON.toJSONString(l050.myPow(x1, n1)));
        System.out.println(JSON.toJSONString(l050.myPow2(x2, n2)));
        System.out.println(JSON.toJSONString(l050.myPow2(-1, -2147483648)));
    }

    /**
     * 最容易想到的循环n次 输入0.00001 2147483647超时
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double ret;
        if (n == 0) {
            ret = 1;
        } else {
            int nTemp = Math.abs(n);
            ret = x;
            for (int i = 1; i < nTemp; i++) {
                ret *= x;
            }
        }
        if (n < 0) {
            ret = 1 / ret;
        }
        return ret;
    }

    /**
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        double ret;
        if (n == 0) {
            ret = 1;
        } else {
            int nTemp;
            if (n != Integer.MIN_VALUE) {
                nTemp = Math.abs(n);
            } else {
                nTemp = n;
            }
            if (nTemp == 1) {
                ret = x;
            } else {
                int nSplit1 = nTemp >> 1;
                double ret1;
                ret1 = myPow2(x, nSplit1);
                ret = ret1 * ret1;
                if (nTemp % 2 == 1) {
                    ret *= x;
                }
            }
        }
        if (n < 0 && n != Integer.MIN_VALUE) {
            ret = 1 / ret;
        }
        return ret;
    }
}

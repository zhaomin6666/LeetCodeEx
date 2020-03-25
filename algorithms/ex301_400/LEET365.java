package com.zm.LeetCodeEx.algorithms.ex301_400;

/**
 * 365. 水壶问题
 * <p>
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * <p>
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * <p>
 * 你允许：
 * <p>
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * <p>
 * 示例 1: (From the famous "Die Hard" example)
 * <p>
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * <p>
 * 示例 2:
 * <p>
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 *
 * @author zm
 */
public class LEET365 {
    public static void main(String[] args) {
        LEET365 l091 = new LEET365();
        System.out.println((l091.new Solution().canMeasureWater(3, 5, 4)));
        System.out.println((l091.new Solution().canMeasureWater(2, 6, 5)));
    }

    class Solution {
        public boolean canMeasureWater(int x, int y, int z) {
            if (z == 0) {
                return true;
            }
            if (x + y < z) {
                return false;
            }
            if (x > y) {
                x = x + y;
                y = x - y;
                x = x - y;
            }
            if (x == 0) {
                return y == z;
            }
            while (y % x != 0) {
                int temp = x;
                x = y % x;
                y = temp;
            }
            return z % x == 0;
        }
    }
}

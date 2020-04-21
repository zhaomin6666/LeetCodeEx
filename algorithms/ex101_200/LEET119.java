package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * <p>
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * <p>
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * @author zm
 */
public class LEET119 {
    public static void main(String[] args) {
        LEET119 l119 = new LEET119();
        System.out.println(l119.new Solution3().getRow(0));
        System.out.println(l119.new Solution3().getRow(1));
        System.out.println(l119.new Solution3().getRow(2));
        System.out.println(l119.new Solution3().getRow(3));
        System.out.println(l119.new Solution3().getRow(4));
        System.out.println(l119.new Solution3().getRow(5));
        System.out.println(l119.new Solution3().getRow(7));
        System.out.println(l119.new Solution3().getRow(10));
        System.out.println(l119.new Solution3().getRow(31));

    }

    class Solution {
        public List<Integer> getRow(int rowIndex) {
            List<Integer> ret = new ArrayList<>();
            for (int i = 0; i <= rowIndex; i++) {
                ret.add(1);
                for (int j = i; j > 1; j--) {
                    ret.set(j - 1, ret.get(j - 1) + ret.get(j - 2));
                }
            }
            return ret;
        }
    }

    /**
     * 用数组实现
     */
    class Solution2 {
        public List<Integer> getRow(int rowIndex) {
            Integer[] ret = new Integer[rowIndex + 1];
            for (int i = 0; i <= rowIndex; i++) {
                ret[i] = 1;
                for (int j = i; j > 1; j--) {
                    ret[j - 1] = ret[j - 1] + ret[j - 2];
                }
            }
            return Arrays.asList(ret);
        }
    }

    /**
     * 数学方法
     * 第n行m列元素通项公式为：
     * C(n-1,m-1)=(n-1)!/[(m-1)!(n-m)!]
     */
    class Solution3 {
        private long[] cache = new long[18];

        public List<Integer> getRow(int rowIndex) {
            cache[0] = 1;
            cache[1] = 1;
            int hh = (rowIndex + 1) >> 1;
            int lh = rowIndex >> 1;
            for (int i = 2; i <= hh; i++) {
                cache[i] = cache[i - 1] * i;
            }
            List<Integer> ret = new LinkedList<>();
            for (int i = 0; i <= lh; i++) {
                ret.add(f(rowIndex, i));
            }
            for (int i = 0; i < hh; i++) {
                ret.add(ret.get(hh - i - 1));
            }
            return ret;
        }

        private int f(int n, int m) {
            if (m < (n >> 1)) {
                m = n - m;
            }
            double mul = 1;
            for (int i = m + 1; i <= n; i++) {
                mul *= i;
            }
            long di = cache[n - m];
            return (int) Math.round(mul / di);
        }
    }


    /**
     * 优化的数学方法，利用好与上一个数的关系
     * 作者：qi-yu-6
     * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii/solution/shi-jian-100nei-cun-366mbshu-xue-fang-fa-by-qi-yu-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution4 {
        private Integer fun(Integer aa, int n, int m) {
            long sum = aa;
            sum = sum * n / m;
            return (int) sum;
        }

        public List<Integer> getRow(int rowIndex) {
            int j = rowIndex / 2;
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int i = 1; i <= rowIndex; i++, rowIndex--) {
                list.add(fun(list.get(i - 1), rowIndex, i));
            }
            for (int i = 0; i < j; i++) {
                list.add(list.get(j - i - 1));
            }
            return list;
        }
    }
}

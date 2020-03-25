package com.zm.LeetCodeEx.algorithms.ex1001_1101;

/**
 * 1071. 字符串的最大公因子
 * <p>
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 * <p>
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * 示例 2：
 * <p>
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * 示例 3：
 * <p>
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 *
 * @author zm
 */
public class LEET1071 {
    public static void main(String[] args) {
        LEET1071 l1071 = new LEET1071();
        System.out.println(l1071.new Solution3().gcdOfStrings("ABCABC", "ABC"));
        System.out.println(l1071.new Solution3().gcdOfStrings("ABAB", "AB"));
        System.out.println(l1071.new Solution3().gcdOfStrings("LEET", "CODE"));
        System.out.println(l1071.new Solution3().gcdOfStrings("LEET", "LEEC"));
        System.out.println(l1071.new Solution3().gcdOfStrings("ABAABAABAABA", "ABAABA"));
    }

    /**
     * 每个公约数做判断
     */
    class Solution {
        public String gcdOfStrings(String str1, String str2) {
            int l1 = str1.length();
            int l2 = str2.length();
            String ret = "";
            for (int i = 1; i <= Math.min(l1, l2); i++) {
                if (l1 % i == 0 && l2 % i == 0) {
                    String x = str1.substring(0, i);
                    if (check(x, str1) && check(x, str2)) {
                        ret = x;
                    }
                }
            }
            return ret;
        }

        private boolean check(String x, String str) {
            double cnt = str.length() * 1.0 / x.length();
            if (Double.valueOf((int) cnt).equals(cnt)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < cnt; i++) {
                    stringBuilder.append(x);
                }
                return stringBuilder.toString().equals(str);
            }
            return false;
        }
    }

    /**
     * 优化方法一，每次的i如果满足条件则翻倍
     */
    class Solution2 {
        public String gcdOfStrings(String str1, String str2) {
            int l1 = str1.length();
            int l2 = str2.length();
            String ret = "";
            for (int i = 1, k = 1; i <= Math.min(l1, l2); i += k) {
                if (l1 % i == 0 && l2 % i == 0) {
                    String x = str1.substring(0, i);
                    if (check(x, str1) && check(x, str2)) {
                        ret = x;
                        k = i;
                    }
                }
            }
            return ret;
        }

        private boolean check(String x, String str) {
            double cnt = str.length() * 1.0 / x.length();
            if (Double.valueOf((int) cnt).equals(cnt)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < cnt; i++) {
                    stringBuilder.append(x);
                }
                return stringBuilder.toString().equals(str);
            }
            return false;
        }
    }

    /**
     * 数学方法证明
     * 1.如果存在符合条件的X，那么str1+str2=str2+str1
     * 2.X为两个字符串长度的最大公约数
     * <p>
     * 所以先判断str1+str2是否等于str2+str1，如果相等，那么再去取两个字符串长度的最大公约数
     */
    class Solution3 {
        public String gcdOfStrings(String str1, String str2) {
            if (!(str1 + str2).equals(str2 + str1)) {
                return "";
            }
            int l1 = str1.length();
            int l2 = str2.length();
            int max = gys(l1, l2);
            return str1.substring(0, max);
        }

        /**
         * 求最大公约数
         *
         * @param a
         * @param b
         * @return
         */
        private int gys(int a, int b) {
            return b == 0 ? a : gys(b, a % b);
        }
    }
}

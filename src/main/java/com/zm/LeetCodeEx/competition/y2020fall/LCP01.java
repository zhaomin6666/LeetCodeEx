package com.zm.LeetCodeEx.competition.y2020fall;

public class LCP01 {

    public static void main(String[] args) {
        LCP01 LCP06 = new LCP01();
        System.out.println(LCP06.new Solution().calculate("AB"));
    }

    class Solution {
        int x = 1;
        int y = 0;

        public int calculate(String s) {
            char[] cs = s.toCharArray();
            if (cs.length == 0) {
                return x + y;
            }
            if (cs[0] == 'A') {
                x = 2 * x + y;
            } else {
                y = 2 * y + x;
            }
            return calculate(s.substring(1, cs.length));
        }
    }
}

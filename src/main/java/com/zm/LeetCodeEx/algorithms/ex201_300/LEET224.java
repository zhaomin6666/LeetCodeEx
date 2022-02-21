package com.zm.LeetCodeEx.algorithms.ex201_300;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 224. 基本计算器
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 *
 * @author zm
 */
public class LEET224 {
    public static void main(String[] args) {
        LEET224 l224 = new LEET224();
        System.out.println(l224.new Solution().calculate("1 + 1"));
        System.out.println(l224.new Solution().calculate(" 2-1 + 2 "));
        System.out.println(l224.new Solution().calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    class Solution {
        public int calculate(String s) {
            char[] cs = s.toCharArray();
            Stack<Brackets> stack = new Stack<>();
            int temp = 0;
            int sign = 1;
            int n = cs.length;
            for (int i = 0; i < n; i++) {
                if (cs[i] == ' ') {
                    continue;
                }
                if (Character.isDigit(cs[i])) {
                    long num = cs[i] - '0';
                    while (i +1< n && Character.isDigit(s.charAt(i+1))) {
                        num = num * 10 + s.charAt(i+1) - '0';
                        i++;
                    }
                    temp += sign * num;
                } else if (cs[i] == '+') {
                    sign = 1;
                } else if (cs[i] == '-') {
                    sign = -1;
                } else if (cs[i] == '(') {
                    Brackets brackets = new Brackets(sign, temp);
                    stack.push(brackets);
                    temp = 0;
                    sign = 1;
                } else if (cs[i] == ')') {
                    Brackets brackets = stack.pop();
                    temp = brackets.cur + brackets.sign * temp;
                    sign = 1;
                }
            }
            return temp;
        }

        private class Brackets {
            int sign = 1;
            int cur = 0;

            Brackets(int sign, int cur) {
                this.sign = sign;
                this.cur = cur;
            }
        }
    }

    /**
     * 官方题解
     * 用栈记录符号的嵌套结果，对于每一个数字遇到了就计算前面的符号并参与计算结果，而不是算好括号内的再和前面的计算。
     *
     * 如1-(1-2),遍历到左括号的时候入栈，此时符号为-1。在遇到2前面的负号时，负号变成-(-1)=1，所以也就是+2。
     */
    class Solution2 {
        public int calculate(String s) {
            Deque<Integer> ops = new LinkedList<Integer>();
            ops.push(1);
            int sign = 1;

            int ret = 0;
            int n = s.length();
            int i = 0;
            while (i < n) {
                if (s.charAt(i) == ' ') {
                    i++;
                } else if (s.charAt(i) == '+') {
                    sign = ops.peek();
                    i++;
                } else if (s.charAt(i) == '-') {
                    sign = -ops.peek();
                    i++;
                } else if (s.charAt(i) == '(') {
                    ops.push(sign);
                    i++;
                } else if (s.charAt(i) == ')') {
                    ops.pop();
                    i++;
                } else {
                    long num = 0;
                    while (i < n && Character.isDigit(s.charAt(i))) {
                        num = num * 10 + s.charAt(i) - '0';
                        i++;
                    }
                    ret += sign * num;
                }
            }
            return ret;
        }
    }

}

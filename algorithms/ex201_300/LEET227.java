package com.zm.LeetCodeEx.algorithms.ex201_300;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 227. 基本计算器 II
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 *
 * @author zm
 */
public class LEET227 {
    public static void main(String[] args) {
        LEET227 l227 = new LEET227();
        System.out.println(l227.new Solution().calculate("3+2*2"));
        System.out.println(l227.new Solution().calculate(" 3/2 "));
        System.out.println(l227.new Solution().calculate(" 3+5 / 2 "));
        System.out.println(l227.new Solution().calculate(" 3*2*2/3/4 + 3*2*2/3/4"));
    }


    class Solution {
        public int calculate(String s) {
            int result = 0;
            long temp = 1;
            int tempsign = 1;
            char[] cs = s.toCharArray();
            int n = cs.length;
            int sign = 1;
            for (int i = 0; i < n; i++) {
                if (cs[i] == ' ') {
                    continue;
                }
                if (Character.isDigit(cs[i])) {
                    long num = cs[i] - '0';
                    while (i + 1 < n && Character.isDigit(cs[i + 1])) {
                        num = num * 10 + cs[i + 1] - '0';
                        i++;
                    }
                    while (i + 1 < n && cs[i + 1] == ' ') {
                        i++;
                    }

                    if (i + 1 >= n || isPlusOrMinus(cs[i + 1])) {
                        if (sign < 2) {
                            result += sign * num;
                        } else if (sign < 3) {
                            result += tempsign * temp * num;
                        } else {
                            result += tempsign * temp / num;
                        }
                        tempsign = 1;
                        temp = 1;
                    } else {
                        if (sign < 2) {
                            tempsign = sign;
                            temp = num;
                        } else if (sign < 3) {
                            temp *= num;
                        } else {
                            temp /= num;
                        }
                    }

                } else if (cs[i] == '+') {
                    sign = 1;
                } else if (cs[i] == '-') {
                    sign = -1;
                } else if (cs[i] == '*') {
                    sign = 2;
                } else if (cs[i] == '/') {
                    sign = 3;
                }
            }
            return result;
        }

        public boolean isPlusOrMinus(char c) {
            return c == '+' || c == '-';
        }
    }

    /**
     * 官方解法，把所有乘除法单独计算，只留最终只需要加减的值入栈
     */
    class Solution2 {
        public int calculate(String s) {
            Deque<Integer> stack = new LinkedList<Integer>();
            char preSign = '+';
            int num = 0;
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                if (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                }
                if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                    switch (preSign) {
                        case '+':
                            stack.push(num);
                            break;
                        case '-':
                            stack.push(-num);
                            break;
                        case '*':
                            stack.push(stack.pop() * num);
                            break;
                        default:
                            stack.push(stack.pop() / num);
                    }
                    preSign = s.charAt(i);
                    num = 0;
                }
            }
            int ans = 0;
            while (!stack.isEmpty()) {
                ans += stack.pop();
            }
            return ans;
        }
    }
}

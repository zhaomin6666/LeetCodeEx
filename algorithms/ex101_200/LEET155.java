package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.Arrays;
import java.util.Stack;

/**
 * 155. 最小栈
 * <p>
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * @author zm
 */
public class LEET155 {
    public static void main(String[] args) {
        LEET155 leet155 = new LEET155();
        System.out.println(Arrays.toString(
                leet155.process(new String[]{"MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"},
                        new int[][]{{}, {-2}, {0}, {-3}, {}, {}, {}, {}}
                )));
        System.out.println(Arrays.toString(
                leet155.process(new String[]{"MinStack", "push", "push", "push", "push", "getMin", "pop", "getMin", "pop", "getMin", "pop", "getMin"},
                        new int[][]{{}, {2}, {1}, {1}, {2}, {}, {}, {}, {}, {}, {}, {}}
                )));
    }

    private Integer[] process(String[] operation, int[][] param) {
        Integer[] ret = new Integer[operation.length];
        MinStack2 obj = new MinStack2();
        for (int i = 1; i < operation.length; i++) {
            if ("push".equals(operation[i])) {
                obj.push(param[i][0]);
            } else if ("pop".equals(operation[i])) {
                obj.pop();
            } else if ("top".equals(operation[i])) {
                ret[i] = obj.top();
            } else if ("getMin".equals(operation[i])) {
                ret[i] = obj.getMin();
            }
        }
        return ret;
    }

    class MinStack {
        private Stack<Integer> eleContainer = new Stack<>();
        private Stack<Integer> minContainer = new Stack<>();

        /**
         * initialize your data structure here.
         */
        public MinStack() {
        }

        public void push(int x) {
            eleContainer.push(x);
            if (minContainer.isEmpty()) {
                minContainer.push(x);
            } else {
                int min = getMin();
                if (x <= min) {
                    minContainer.push(x);
                }
            }
        }

        public void pop() {
            int x = eleContainer.pop();
            if (getMin() == x) {
                minContainer.pop();
            }
        }

        public int top() {
            return eleContainer.peek();
        }

        public int getMin() {
            return minContainer.peek();
        }
    }

    class MinStack2 {
        private Stack<Integer> eleContainer;
        private Stack<Integer> minContainer;

        /**
         * initialize your data structure here.
         */
        public MinStack2() {
            eleContainer = new Stack<>();
            minContainer = new Stack<>();
        }

        public void push(int x) {
            eleContainer.push(x);
            int min = Math.min(minContainer.isEmpty() ? Integer.MAX_VALUE : getMin(), x);
            minContainer.push(min);
        }

        public void pop() {
            eleContainer.pop();
            minContainer.pop();
        }

        public int top() {
            return eleContainer.peek();
        }

        public int getMin() {
            return minContainer.peek();
        }
    }
}

package com.zm.LeetCodeEx.algorithms.ex301_400;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 341. 扁平化嵌套列表迭代器
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * <p>
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2:
 * <p>
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 *
 * @author zm
 */
public class LEET341 {
    public static void main(String[] args) {
        LEET341 l341 = new LEET341();


    }

    public class NestedIterator implements Iterator<Integer> {

        private final Stack<Iterator<NestedInteger>> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return stack.pop().next().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                Iterator<NestedInteger> it = stack.peek();
                // 如果当前迭代器没有下一个值则出栈，进入下一个循环，获取上一个迭代器
                if (!it.hasNext()) {
                    stack.pop();
                    continue;
                }
                // 判断next是数字，那么取出这个数字并再次加入栈
                NestedInteger next = it.next();
                if (next.isInteger()) {
                    List<NestedInteger> temp = new ArrayList<>();
                    temp.add(next);
                    stack.push(temp.iterator());
                    return true;
                }
                // 判断next是List，则把list的iterator入栈
                stack.push(next.getList().iterator());
            }
            return false;
        }
    }

    public interface NestedInteger {

        /**
         * 是整数
         *
         * @return 是否
         */
        boolean isInteger();

        /**
         * 获取整数
         *
         * @return 整数
         */
        Integer getInteger();

        /**
         * 获取列表
         *
         * @return 列表
         */
        List<NestedInteger> getList();
    }
}

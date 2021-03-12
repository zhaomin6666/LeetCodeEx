package com.zm.LeetCodeEx.algorithms.ex301_400;

import java.util.Arrays;
import java.util.Stack;

/**
 * 331. 验证二叉树的前序序列化
 * <p>
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * <p>
 * _9_
 * /   \
 * 3     2
 * / \   / \
 * 4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 * <p>
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * <p>
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * <p>
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "1,#"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: "9,#,#,1"
 * 输出: false
 *
 * @author zm
 */
public class LEET331 {
    public static void main(String[] args) {
        LEET331 l331 = new LEET331();
        //true
        System.out.println(l331.new Solution3().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        //false
        System.out.println(l331.new Solution3().isValidSerialization("1,#"));
        //true
        System.out.println(l331.new Solution3().isValidSerialization("1,#,#"));
        //true
        System.out.println(l331.new Solution3().isValidSerialization("1,1,#,#,#"));
        //false
        System.out.println(l331.new Solution3().isValidSerialization("9,#,#,1"));
        //false
        System.out.println(l331.new Solution3().isValidSerialization("1,#,#,#,#"));
        //false
        System.out.println(l331.new Solution3().isValidSerialization("#,#,#,#,#"));
        //true
        System.out.println(l331.new Solution3().isValidSerialization("#"));
        //false
        System.out.println(l331.new Solution3().isValidSerialization("1"));
        //false
        System.out.println(l331.new Solution3().isValidSerialization("7,2,#,2,#,#,#,6,#"));
    }

    class Solution {
        final static String SPLIT = "#";

        public boolean isValidSerialization(String preorder) {
            String[] nodes = preorder.split(",");
            if (SPLIT.equals(nodes[0])) {
                return nodes.length == 1;
            }

            Stack<Integer> stack = new Stack<>();
            stack.push(2);
            for (int i = 1; i < nodes.length; i++) {
                String node = nodes[i];
                if (stack.isEmpty()) {
                    return false;
                }
                if (!SPLIT.equals(node)) {
                    stack.push(2);
                } else {
                    while (!stack.isEmpty() && stack.peek() == 1) {
                        stack.pop();
                    }
                    if (!stack.isEmpty() && stack.pop() == 2) {
                        stack.push(1);
                    }
                }
            }
            return stack.isEmpty();
        }
    }


    /**
     * 优化，不使用栈，只使用计数器
     */
    class Solution2 {
        final static String SPLIT = "#";

        public boolean isValidSerialization(String preorder) {
            String[] nodes = preorder.split(",");
            if (SPLIT.equals(nodes[0])) {
                return nodes.length == 1;
            }
            int remaining = 2;
            for (int i = 1; i < nodes.length; i++) {
                String node = nodes[i];
                if (remaining == 0) {
                    return false;
                }
                if (!SPLIT.equals(node)) {
                    remaining++;
                } else {
                    remaining--;
                }
            }
            return remaining == 0;
        }
    }

    /**
     * 优化只遍历一次
     */
    class Solution3 {
        final static char SPLIT = '#';

        public boolean isValidSerialization(String preorder) {
            char[] cs = preorder.toCharArray();
            int size = cs.length;
            if (size == 0) {
                return false;
            }
            if (cs[0] == SPLIT) {
                return size == 1;
            }
            int remaining = 0;
            char lastChar = ' ';
            boolean first = true;
            for (int i = 0; i < size; i++) {
                if (!first && remaining == 0) {
                    return false;
                }
                if (cs[i] == ',') {
                    if (lastChar != SPLIT) {
                        if (first) {
                            first = false;
                            remaining += 2;
                        } else {
                            remaining++;
                        }
                    } else {
                        remaining--;
                    }
                } else {
                    lastChar = cs[i];
                }
            }
            if (lastChar != SPLIT) {
                if (first) {
                    remaining += 2;
                } else {
                    remaining++;
                }
            } else {
                remaining--;
            }
            return remaining == 0;
        }
    }

    /**
     * 官方题解，类似方法3
     */
    class Solution4 {
        public boolean isValidSerialization(String preorder) {
            int n = preorder.length();
            int i = 0;
            int slots = 1;
            while (i < n) {
                if (slots == 0) {
                    return false;
                }
                if (preorder.charAt(i) == ',') {
                    i++;
                } else if (preorder.charAt(i) == '#') {
                    slots--;
                    i++;
                } else {
                    // 读一个数字
                    while (i < n && preorder.charAt(i) != ',') {
                        i++;
                    }
                    slots++; // slots = slots - 1 + 2
                }
            }
            return slots == 0;
        }
    }
}

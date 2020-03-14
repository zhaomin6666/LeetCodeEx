package com.zm.LeetCodeEx.algorithms;

import com.alibaba.fastjson.JSON;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * <p>
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 * @author zm
 */
public class LEET084 {
    public static void main(String[] args) {
        LEET084 l084 = new LEET084();
        System.out.println(l084.new Solution4().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    /**
     * 暴力法
     * 获得两个柱子之间的最低柱子计算出面积
     */
    public class Solution {
        public int largestRectangleArea(int[] heights) {
            int maxarea = 0;
            for (int i = 0; i < heights.length; i++) {
                int minheight = Integer.MAX_VALUE;
                for (int j = i; j < heights.length; j++) {
                    minheight = Math.min(minheight, heights[j]);
                    maxarea = Math.max(maxarea, minheight * (j - i + 1));
                }
            }
            return maxarea;
        }
    }

    /**
     * 分治法
     */
    public class Solution2 {
        public int largestRectangleArea(int[] heights) {
            return largestRectangleArea(heights, 0, heights.length - 1);
        }

        public int largestRectangleArea(int[] heights, int l, int r) {
            if (l > r) {
                return 0;
            } else if (l == r) {
                return heights[l];
            }
            int minIndex = l;
            int min = heights[l];
            for (int i = l + 1; i <= r; i++) {
                if (heights[i] < min) {
                    min = heights[i];
                    minIndex = i;
                }
            }
            int flatSquare = min * (r - l + 1);
            int left = largestRectangleArea(heights, l, minIndex - 1);
            int right = largestRectangleArea(heights, minIndex + 1, r);
            return Math.max(Math.max(left, right), flatSquare);
        }
    }

    /**
     * 构造线段树使每次分治中的寻找最小结点的时间复杂度从O(n)减少到O(log2n)
     */
    public class Solution3 {
        int[] segTree;
        int[] heights;

        public int largestRectangleArea(int[] heights) {
            segTree = new int[1 << ((int) Math.ceil(Math.log(heights.length) / Math.log(2)) + 1)];
            this.heights = heights;
            generateTree(1, 0, heights.length - 1);
            System.out.println(JSON.toJSONString(segTree));
            return 0;//largestRectangleArea(heights, 0, heights.length - 1);
        }

        private void generateTree(int node, int l, int r) {
            if (l == r) {
                segTree[node] = l;
            } else {
                int m = (l + r) >>> 1;
                generateTree(node << 1, l, m);
                generateTree(node << 1 | 1, m + 1, r);
                pushUp(node);
            }
        }

        /**
         * 树节点储存最小值的索引
         *
         * @param node
         */
        private void pushUp(int node) {
            int leftIndex = segTree[node << 1];
            int rightIndex = segTree[node << 1 | 1];
            segTree[node] = heights[leftIndex] > heights[rightIndex] ? rightIndex : leftIndex;
        }

        /**
         * 结点查询方法
         *
         * @param L 要查询区间的下界
         * @param R 要查询区间的上界
         * @param l 结点区间的上界
         * @param r 结点区间的下界
         * @param k 结点下标
         * @return
         */
        private int query(int L, int R, int l, int r, int k) {
            //如果当前结点的区间真包含于要查询的区间内，则返回结点信息且不需要往下递归
            if (L <= l && r <= R) {
                return segTree[k];
            } else {
                int resHeight = Integer.MAX_VALUE;    //返回值变量，根据具体线段树查询的什么而自定义
                int res = 0;
                int mid = (l + r) >>> 1;    //m则为中间点，左儿子的结点区间为[l,m],右儿子的结点区间为[m+1,r]
                if (L <= mid) {   //如果左子树和需要查询的区间交集非空
                    int leftMinIndex = query(L, R, l, mid, k << 1);
                    resHeight = heights[leftMinIndex];
                    res = leftMinIndex;
                }
                if (R > mid) {  //如果右子树和需要查询的区间交集非空，注意这里不是else if，因为查询区间可能同时和左右区间都有交集
                    int rightMinIndex = query(L, R, mid + 1, r, k << 1 | 1);
                    if (heights[rightMinIndex] <= resHeight) {
                        res = rightMinIndex;
                    }
                }
                return res;    //返回当前结点得到的信息
            }
        }

        public int largestRectangleArea(int[] heights, int l, int r) {
            if (l > r) {
                return 0;
            } else if (l == r) {
                return heights[l];
            }
            int minIndex = query(l + 1, r + 1, 1, heights.length, 1);
            int min = heights[minIndex];
            int flatSquare = min * (r - l + 1);
            int left = largestRectangleArea(heights, l, minIndex - 1);
            int right = largestRectangleArea(heights, minIndex + 1, r);
            return Math.max(Math.max(left, right), flatSquare);
        }
    }

    /**
     * 使用栈
     */
    public class Solution4 {
        public int largestRectangleArea(int[] heights) {
            int res = 0;
            Stack<Integer> stack = new Stack<>();
            int[] newHeights = new int[heights.length + 2];
            System.arraycopy(heights, 0, newHeights, 1, heights.length);
            //System.out.println(Arrays.toString(newHeights));
            for (int i = 0; i < newHeights.length; i++) {
                //System.out.println(stack.toString());
                while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
                    int cur = stack.pop();
                    res = Math.max(res, (i - stack.peek() - 1) * newHeights[cur]);
                }
                stack.push(i);
            }
            return res;
        }
    }
}

package com.zm.LeetCodeEx.competition.y2021spring;

/**
 * 2. 乐团站位
 * <p>
 * 某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，每个位置站有一位成员。乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。
 * <p>
 * 为保证声乐混合效果，成员站位规则为：自 grid 左上角开始顺时针螺旋形向内循环以 1，2，...，9 循环重复排列。例如当 num = 5 时，站位如图所示
 * <p>
 * image.png
 * <p>
 * 请返回位于场地坐标 [Xpos,Ypos] 的成员所持乐器编号。
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 3, Xpos = 0, Ypos = 2
 * <p>
 * 输出：3
 * <p>
 * 解释：
 * image.png
 * <p>
 * 示例 2：
 * <p>
 * 输入：num = 4, Xpos = 1, Ypos = 2
 * <p>
 * 输出：5
 * <p>
 * 解释：
 * image.png
 * <p>
 * 提示：
 * <p>
 * 1 <= num <= 10^9
 * 0 <= Xpos, Ypos < num
 */
public class LCP02 {

    public static void main(String[] args) {
        LCP02 LCP02 = new LCP02();
        //System.out.println(LCP02.new Solution().orchestraLayout(3, 0, 2));
        //System.out.println(LCP02.new Solution().orchestraLayout(4, 1, 1));
        System.out.println(LCP02.new Solution().orchestraLayout(3, 0, 0));
        System.out.println(LCP02.new Solution().orchestraLayout(3, 0, 1));
        System.out.println(LCP02.new Solution().orchestraLayout(3, 0, 2));
        System.out.println(LCP02.new Solution().orchestraLayout(3, 1, 2));
        System.out.println(LCP02.new Solution().orchestraLayout(3, 2, 2));
        System.out.println(LCP02.new Solution().orchestraLayout(3, 2, 1));
        System.out.println(LCP02.new Solution().orchestraLayout(3, 2, 0));
        System.out.println(LCP02.new Solution().orchestraLayout(3, 1, 0));
        System.out.println(LCP02.new Solution().orchestraLayout(3, 1, 1));

        // 4
        System.out.println(LCP02.new Solution().orchestraLayout(4, 0, 0));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 0, 1));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 0, 2));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 0, 3));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 1, 3));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 2, 3));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 3, 3));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 3, 2));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 3, 1));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 3, 0));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 2, 0));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 1, 0));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 1, 1));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 1, 2));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 2, 2));
        System.out.println(LCP02.new Solution().orchestraLayout(4, 2, 1));
    }

    class Solution {
        public int orchestraLayout(int num, int xPos, int yPos) {
            int minx = Math.min(xPos, num - xPos - 1);
            int miny = Math.min(yPos, num - yPos - 1);
            int min = Math.min(minx, miny);
            int outside = mod9(mod9(mod9(num) * mod9(num)) - mod9(mod9(num - min * 2) * mod9(num - min * 2)) + 9);
            int inside = 0;
            if (xPos >= yPos && xPos != min) {
                if (yPos == min) {
                    inside = mod9(num - 2 * yPos - 1) * 3 + (num - min - xPos);
                } else {
                    inside = mod9(num - 2 * (num - xPos - 1) - 1) * 2 + num - min - yPos;
                }
            } else {
                if (num - yPos - 1 == min) {
                    inside = mod9(num - 2 * (num - yPos - 1) - 1) + xPos - min + 1;
                } else {
                    inside = yPos - min + 1;
                }
            }
            int ret = mod9(outside + inside);
            return ret == 0 ? 9 : ret;
        }

        public int mod9(int num) {
            return num % 9;
        }
    }
}

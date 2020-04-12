package com.zm.LeetCodeEx.lcci;

import java.util.Arrays;

/**
 * 面试题 16.03. 交点
 * <p>
 * 给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
 * <p>
 * 要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回 X 值最小的点，X 坐标相同则返回 Y 值最小的点。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * line1 = {0, 0}, {1, 0}
 * line2 = {1, 1}, {0, -1}
 * 输出： {0.5, 0}
 * 示例 2：
 * <p>
 * 输入：
 * line1 = {0, 0}, {3, 3}
 * line2 = {1, 1}, {2, 2}
 * 输出： {1, 1}
 * 示例 3：
 * <p>
 * 输入：
 * line1 = {0, 0}, {1, 1}
 * line2 = {1, 0}, {2, 1}
 * 输出： {}，两条线段没有交点
 *
 * @author zm
 */
public class Lcci1603 {
    public static void main(String[] args) {
        Lcci1603 lcci1603 = new Lcci1603();
        System.out.println(Arrays.toString(lcci1603.new Solution().intersection(new int[]{0, 0}, new int[]{0, 6}, new int[]{0, 0}, new int[]{1, 0})));
        System.out.println(Arrays.toString(lcci1603.new Solution().intersection(new int[]{0, 3}, new int[]{0, 6}, new int[]{0, 5}, new int[]{0, 1})));
        System.out.println(Arrays.toString(lcci1603.new Solution().intersection(new int[]{0, 0}, new int[]{1, 0}, new int[]{1, 1}, new int[]{0, -1})));
        System.out.println(Arrays.toString(lcci1603.new Solution().intersection(new int[]{0, 0}, new int[]{3, 3}, new int[]{1, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(lcci1603.new Solution().intersection(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{2, 1})));
        System.out.println(Arrays.toString(lcci1603.new Solution().intersection(new int[]{0, 0}, new int[]{1, 1}, new int[]{0, 3}, new int[]{2, 0})));

    }

    class Solution {
        public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
            if (start1[0] > end1[0]) {
                int[] temp = start1;
                start1 = end1;
                end1 = temp;
            }
            if (start2[0] > end2[0]) {
                int[] temp = start2;
                start2 = end2;
                end2 = temp;
            }
            // 如果第一条直线平行于y轴，取四个端点关于y=x的对称点
            if (start1[0] == end1[0]) {
                int[] newStart1 = new int[]{start1[1], start1[0]},
                        newEnd1 = new int[]{end1[1], end1[0]},
                        newStart2 = new int[]{start2[1], start2[0]},
                        newEnd2 = new int[]{end2[1], end2[0]};
                double[] tmp = intersection(newStart1, newEnd1, newStart2, newEnd2);
                if (tmp.length == 0) {
                    return tmp;
                }
                return new double[]{tmp[1], tmp[0]};
            }// 第二条直线平行于 Y 轴
            else if (start2[0] == end2[0]) {
                // 判断一下第一条直线是不是平行于X轴，不然会死循环
                if (start1[1] == end1[1]) {
                    if (start1[1] >= start2[1] && start1[1] <= end2[1]
                            && start2[0] >= start1[0] && start2[0] <= end1[0]) {
                        return new double[]{start2[0], start1[1]};
                    } else {
                        return new double[0];
                    }
                }
                return intersection(start2, end2, start1, end1);
            }
            double[] ab1 = getAb(start1, end1);
            double[] ab2 = getAb(start2, end2);
            if (ab1[0] == ab2[0]) {
                if (ab1[1] != ab2[1]) {
                    return new double[0];
                } else {
                    if (start1[0] >= start2[0] && start1[0] <= end2[0]) {
                        return new double[]{start1[0], start1[1]};
                    } else if (start2[0] >= start1[0] && start2[0] <= end1[0]) {
                        return new double[]{start2[0], start2[1]};
                    } else {
                        return new double[0];
                    }
                }
            }
            double[] joinPoint = new double[2];
            joinPoint[0] = (ab1[1] - ab2[1]) * 1.0 / (ab2[0] - ab1[0]);
            //System.out.println(joinPoint[0]);
            if (joinPoint[0] < start1[0] || joinPoint[0] > end1[0] || joinPoint[0] < start2[0] || joinPoint[0] > end2[0]) {
                return new double[0];
            }
            joinPoint[1] = joinPoint[0] * ab1[0] + ab1[1];
            return joinPoint;
        }

        private double[] getAb(int[] start1, int[] end1) {
            double[] ret = new double[2];
            ret[0] = (end1[1] - start1[1]) * 1.0 / (end1[0] - start1[0]);
            ret[1] = start1[1] - ret[0] * start1[0];
            return ret;
        }
    }
}

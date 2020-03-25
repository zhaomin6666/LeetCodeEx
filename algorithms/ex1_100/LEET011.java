package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.HashSet;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
 * ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 
 * @author zm
 *
 */
public class LEET011 {
	public static void main(String[] args) {
		LEET011 L011 = new LEET011();
		int[] test = { 2, 3, 10, 5, 7, 8, 9 };
		System.out.println(L011.maxArea3(test));
	}

	/**
	 * 一条线从0的高度网上提，能否穿过两个柱体 取决于最高柱体和长度 最坏情况 Max*n 比如 {2, 3, 10, 5, 7, 8, 9} 0： 0 1：
	 * 6 2： 12 3： 15 4： 16 5： 15 6： 12 7： 14 8： 8
	 *
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int sum = Math.min(height[0], height[height.length - 1]) * (height.length - 1);
		int maxheight = 0;
		while (true) {
			int l = 0;
			int r = height.length - 1;
			boolean f1 = false;
			for (int i = 0; i < height.length; i++) {
				if (height[i] >= maxheight) {
					l = i;
					f1 = true;
					break;
				}
			}
			boolean f2 = false;
			for (int i = 0; i < height.length; i++) {
				if (height[height.length - i - 1] >= maxheight) {
					r = height.length - i - 1;
					f2 = true;
					break;
				}
			}
			if (l == r || (!f1 && !f2)) {
				break;
			}
			System.out.println("h:" + maxheight + "   ====>   " + l + "----" + r + "  ===>  " + (r - l) * maxheight);
			int sumtemp = (r - l) * maxheight;
			if (sumtemp > sum) {
				sum = sumtemp;
			}
			maxheight++;
		}
		return sum;
	}

	/**
	 * 设法优化上面的算法 然而。。
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea2(int[] height) {
		int sum = Math.min(height[0], height[height.length - 1]) * (height.length - 1);
		HashSet<Integer> hset = new HashSet<>();
		for (int item : height) {
			if (hset.contains(item)) {
				continue;
			}
			hset.add(item);

			int l = 0;
			int r = height.length - 1;
			for (int i = 0; i < height.length; i++) {
				if (height[i] >= item) {
					l = i;
					break;
				}
			}
			for (int i = 0; i < height.length; i++) {
				if (height[height.length - i - 1] >= item) {
					r = height.length - i - 1;
					break;
				}
			}
			System.out.println("h:" + item + "   ====>   " + l + "----" + r + "  ===>  " + (r - l) * item);
			int sumtemp = (r - l) * item;
			if (sumtemp > sum) {
				sum = sumtemp;
			}
		}
		return sum;
	}

	/**
	 * 官方解法：双指针法 算法
	 * 
	 * 这种方法背后的思路在于，两线段之间形成的区域总是会受到其中较短那条长度的限制。此外，两线段距离越远，得到的面积就越大。
	 * 
	 * 我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。 此外，我们会使用变量 maxareamaxarea
	 * 来持续存储到目前为止所获得的最大面积。 在每一步中，我们会找出指针所指向的两条线段形成的区域，更新
	 * maxareamaxarea，并将指向较短线段的指针向较长线段那端移动一步。
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea3(int[] height) {
		int max = 0;
		int l = 0;
		int r = height.length - 1;
		for (; l < r;) {
			max = Math.max(Math.min(height[l], height[r]) * (r - l), max);
			if (height[l] > height[r])
				r--;
			else
				l++;
		}
		return max;
	}
}

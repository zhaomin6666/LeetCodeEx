package LeetCode;

import java.util.HashSet;

public class LEET011 {
	public int maxArea(int[] height) {
		int sum = Math.min(height[0], height[height.length - 1])
				* (height.length - 1);
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
			System.out.println("h:" + maxheight + "   ====>   " + l + "----" + r
					+ "  ===>  " + (r - l) * maxheight);
			int sumtemp = (r - l) * maxheight;
			if (sumtemp > sum) {
				sum = sumtemp;
			}
			maxheight++;
		}
		return sum;
	}

	public static void main(String[] args) {
		LEET011 L011 = new LEET011();
		int[] test = {2, 3, 10, 5, 7, 8, 9};
		System.out.println(L011.maxArea3(test));
	}

	public int maxArea2(int[] height) {
		int sum = Math.min(height[0], height[height.length - 1])
				* (height.length - 1);
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
			System.out.println("h:" + item + "   ====>   " + l + "----" + r
					+ "  ===>  " + (r - l) * item);
			int sumtemp = (r - l) * item;
			if (sumtemp > sum) {
				sum = sumtemp;
			}
		}
		return sum;
	}

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

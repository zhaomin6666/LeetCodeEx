package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 
 * 注意: 不能使用代码库中的排序函数来解决这道题。
 * 
 * 示例:
 * 
 * 输入: [2,0,2,1,1,0] 输出: [0,0,1,1,2,2]
 * 
 * @author zm
 *
 */
public class LEET075 {
	public static void main(String[] args) {
		LEET075 l075 = new LEET075();
		int[] a1 = { 2, 0, 2, 1, 1, 0 };
		l075.sortColors3(a1);
		System.out.println(JSON.toJSONString(a1));
		int[] a2 = { 2 };
		l075.sortColors3(a2);
		System.out.println(JSON.toJSONString(a2));
	}

	/**
	 * 循环两次 a1 = [2, 0, 2, 1, 1, 0]
	 * 
	 * @param nums
	 */
	public void sortColors(int[] nums) {
		int cnt0 = 0;
		int cnt1 = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				cnt0++;
			} else if (nums[i] == 1) {
				cnt1++;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (i < cnt0) {
				nums[i] = 0;
			} else if (i < cnt0 + cnt1) {
				nums[i] = 1;
			} else {
				nums[i] = 2;
			}
		}
	}

	/**
	 * 尝试循环一次 a1 = [2, 0, 2, 1, 1, 0]
	 * 
	 * @param nums
	 */
	public void sortColors2(int[] nums) {
		int cnt0 = 0;
		int cnt2 = 0;
		int[] nums2 = nums.clone();
		for (int i = 0; i < nums.length; i++) {
			if (nums2[i] == 0) {
				nums[cnt0] = 0;
				cnt0++;
			} else if (nums2[i] == 2) {
				nums[nums.length - cnt2 - 1] = 2;
				cnt2++;
			}
		}
		for (int i = cnt0; i < nums.length - cnt2; i++) {
			nums[i] = 1;
		}
	}

	/**
	 * 真正循环一次
	 * 
	 * @param nums
	 */
	public void sortColors3(int[] nums) {
		int i = 0, j = 0;
		int k = nums.length - 1;
		while (j <= k) {
			switch (nums[j]) {
			case 0:
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i++;
				j++;
				break;
			case 2:
				int temp2 = nums[k];
				nums[k] = nums[j];
				nums[j] = temp2;
				k--;
				break;
			default:
				j++;
				break;
			}
		}
	}
}

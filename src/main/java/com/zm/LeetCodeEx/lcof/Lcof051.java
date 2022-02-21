package com.zm.LeetCodeEx.lcof;

/**
 * 面试题51. 数组中的逆序对
 * <p>
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,5,6,4] 输出: 5
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 50000
 *
 * @author zm
 */
public class Lcof051 {
	public static void main(String[] args) {
		Lcof051 l051 = new Lcof051();
		System.out.println(l051.new Solution2().reversePairs(new int[] { 7, 5, 6, 4 }));

	}

	/**
	 * 归并排序 O(nlogn)
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int reversePairs(int[] nums) {
			if (nums.length == 0) {
				return 0;
			}
			return mergeSort(nums, 0, nums.length - 1);
		}

		private int mergeSort(int[] nums, int l, int r) {
			if (l >= r) {
				return 0;
			}
			int mid = (l + r) >>> 1;
			return mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r) + merge(nums, l, mid, r);
		}

		private int merge(int[] nums, int l, int mid, int r) {
			int ll = l;
			int lr = mid;
			int rl = mid + 1;
			int rr = r;
			int ans = 0;
			int newArrayIndex = 0;
			int[] newArray = new int[r - l + 1];
			while (ll <= lr && rl <= rr) {
				// 这里新增一个计数
                if(nums[ll] > nums[rl]){
                    ans += rr - rl + 1;
                    newArray[newArrayIndex++] = nums[ll++];
                }else {
                    newArray[newArrayIndex++] = nums[rl++];
                }
			}
			while (ll <= lr) {
				newArray[newArrayIndex++] = nums[ll++];
			}
			while (rl <= rr) {
				newArray[newArrayIndex++] = nums[rl++];
			}
			for (int i = 0; i < newArray.length; i++) {
				nums[l + i] = newArray[i];
			}
			return ans;
		}
	}

	/**
	 * 双循环暴力 O(n2)
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int reversePairs(int[] nums) {
			int ret = 0;
			for (int i = 1; i < nums.length; i++) {
				for (int j = 0; j < i; j++) {
					if (nums[j] > nums[i]) {
						ret++;
					}
				}
			}
			return ret;
		}
	}
}

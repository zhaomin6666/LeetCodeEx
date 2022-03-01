package com.zm.LeetCodeEx.algorithms.ex1001_1100;

import com.zm.LeetCodeEx.MountainArray;
import com.zm.LeetCodeEx.MountainArrayImpl;

/**
 * 1095. 山脉数组中查找目标值
 * <p>
 * （这是一个 交互式问题 ）
 * <p>
 * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
 * <p>
 * 如果不存在这样的下标 index，就请返回 -1。
 * <p>
 * <p>
 * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
 * <p>
 * 首先，A.length >= 3
 * <p>
 * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
 * <p>
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * <p>
 * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
 * <p>
 * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
 * MountainArray.length() - 会返回该数组的长度
 * <p>
 * 注意：
 * <p>
 * 对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
 * <p>
 * 为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：array = [1,2,3,4,5,3,1], target = 3
 * 输出：2
 * 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
 * 示例 2：
 * <p>
 * 输入：array = [0,1,2,4,2,1], target = 3
 * 输出：-1
 * 解释：3 在数组中没有出现，返回 -1。
 * <p>
 * 提示：
 * <p>
 * 3 <= mountain_arr.length() <= 10000
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 *
 * @author zm
 */
public class LEET1095 {
	public static void main(String[] args) {
		MountainArrayImpl mountainArray1 = new MountainArrayImpl(new int[]{1, 2, 3, 4, 5, 3, 1});
		System.out.println(new Solution().findInMountainArray(3, mountainArray1));
		MountainArrayImpl mountainArray2 = new MountainArrayImpl(new int[]{0, 1, 2, 4, 2, 1});
		System.out.println(new Solution().findInMountainArray(3, mountainArray2));
		MountainArrayImpl mountainArray3 = new MountainArrayImpl(new int[]{0, 1, 0});
		System.out.println(new Solution().findInMountainArray(3, mountainArray3));
	}

	/**
	 * 三次二分查找，先寻找最高点，再从两个有序数组中二分找到要求的数字
	 */
	static class Solution {
		public int findInMountainArray(int target, MountainArray mountainArr) {
			int highestIndex = findHighestIndex(mountainArr);
			int leftRet = binarySearch(target, mountainArr, 0, highestIndex, true);
			if (leftRet != -1) {
				return leftRet;
			}
			return binarySearch(target, mountainArr, highestIndex + 1, mountainArr.length() - 1, false);
		}

		private int binarySearch(int target, MountainArray mountainArr, int l, int r, boolean isIncrease) {
			while (l <= r) {
				int mid = (l + r) >>> 1;
				int midValue = mountainArr.get(mid);
				if (midValue == target) {
					return mid;
				}
				if (midValue > target) {
					if (isIncrease) {
						r = mid - 1;
					}
					else {
						l = mid + 1;
					}
				}
				else {
					if (!isIncrease) {
						r = mid - 1;
					}
					else {
						l = mid + 1;
					}
				}
			}
			return -1;
		}

		private int findHighestIndex(MountainArray mountainArr) {
			int l = 0;
			int r = mountainArr.length() - 1;
			while (l < r) {
				int mid = (l + r) >>> 1;
				int midValue = mountainArr.get(mid);
				int nextValue = mountainArr.get(mid + 1);
				if (midValue > nextValue) {
					r = mid;
				}
				else {
					l = mid + 1;
				}
			}
			return l;
		}
	}
}

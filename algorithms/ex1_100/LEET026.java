package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

/**
 * 26.删除排序数组中的重复项
 *
 * <b>给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。</b>
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。</br>
 * </br>
 *
 * 示例 1: 给定数组 nums = [1,1,2], 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。 </br>
 * 示例 2: 给定 nums = [0,0,1,1,1,2,2,3,3,4], 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0,
 * 1, 2, 3, 4。 你不需要考虑数组中超出新长度后面的元素。 </br>
 * 说明: 为什么返回数值是整数，但输出的答案是数组呢?</br>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。 你可以想象内部操作如下: </br>
 * nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝 int len = removeDuplicates(nums); </br>
 * 在函数里修改输入数组对于调用者是可见的。 </br>
 * 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。 </br>
 * for (int i = 0; i < len; i++) { </br>
 * &nbsp&nbspprint(nums[i]); </br>
 * }
 * 
 * @author zm
 *
 */
public class LEET026 {
	public static void main(String[] args) {
		LEET026 l026 = new LEET026();
		int[] nums1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
		System.out.println(l026.new Solution().removeDuplicates(nums1));
		System.out.println(JSON.toJSONString(nums1));
	}

	/**
	 * 使用for循环去遍历数据查看某一位置的数与上一位置的数是否一样。
	 * 同时题中还要求在原数组上进行操作，所以需要一个慢指针去标识非重复数保存的位置
	 */
	class Solution {
		public int removeDuplicates(int[] nums) {
			if (nums.length == 0) {
				return 0;
			}
			int l = 1;
			for (int i = 1; i < nums.length; i++) {
				if (nums[i] != nums[l - 1]) {
					nums[l++] = nums[i];
				}
			}
			return l;
		}
	}

	/**
	 * 使用双指针写法
	 */
	class Solution2 {
		public int removeDuplicates(int[] nums) {
			int n = nums.length;
			if (n == 0) {
				return 0;
			}
			int pre = 0;
			int cur = 0;
			while (cur < n) {
				if (nums[pre] == nums[cur]) {
					cur++;
				} else {
					nums[pre++] = nums[cur++];
				}
			}
			return pre + 1;
		}
	}
}

package com.zm.LeetCodeEx;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和
 * n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 
 * 示例 1: </br>
 * 输入: [1,3,4,2,2] 输出: 2 </br>
 * 
 * 示例 2:</br>
 * 输入: [3,1,3,4,2] 输出: 3 </br>
 * 
 * 
 * 说明：
 * 
 * 不能更改原数组（假设数组是只读的）。 只能使用额外的 O(1) 的空间。 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * 
 * @author zm
 *
 */
public class LEET287 {
	public static void main(String[] args) {
		LEET287 l287 = new LEET287();
		int[] nums = { 3, 1, 3, 4, 2 };
		System.out.println(l287.findDuplicate4(nums));
	}

	/**
	 * 不满足额外的 O(1) 的空间
	 * 
	 * @param nums
	 * @return
	 */
	public int findDuplicate(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.get(nums[i]) != null) {
				return nums[i];
			} else {
				map.put(nums[i], 1);
			}
		}
		return -1;
	}

	/**
	 * 不满足不能改变原来数组
	 * 
	 * @param nums
	 * @return
	 */
	public int findDuplicate2(int[] nums) {
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == nums[i + 1]) {
				return nums[i];
			}
		}
		return -1;
	}

	/**
	 * 不满足不能改变原来数组
	 * 
	 * @param nums
	 * @return
	 */
	public int findDuplicate3(int[] nums) {
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == nums[i + 1]) {
				return nums[i];
			}
		}
		return -1;
	}

	/**
	 * 快慢指针 </br>
	 * <strong>重点:给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和
	 * n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。</strong></br>
	 * 有了上面这个条件，可以发现nums中的某个元素是可以作为nums[i]的下标i的 </br>
	 * 如果有n维数组有n个不同数字填充，那么用某一个元素来作为下标指向另一个元素从0-->开始可以得到没有环的链表 </br>
	 * 如123:0-->1,1-->2,2-->3 ==> 0-->1-->2-->3 </br>
	 * 如113:0-->1,1-->1,2-->3 ==> 0-->1-->1-->1... </br>
	 * 如213:0-->2,1-->1,2-->3 ==> 0-->2-->3 </br>
	 * 如果n位数字有n+1个数组填充且数字在1到n之前那么肯定会有一个重复的会组成环，所以这里的问题又变成了{@link LEET142}的问题
	 * 
	 * @param nums
	 * @return
	 */
	public int findDuplicate4(int[] nums) {
		if (nums.length < 2) {
			return -1;
		}
		int slow = nums[0];
		int fast = nums[nums[0]];
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		int thd = 0;
		while (thd != slow) {
			thd = nums[thd];
			slow = nums[slow];
		}
		return thd;
	}
}

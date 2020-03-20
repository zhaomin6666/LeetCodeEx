package com.zm.LeetCodeEx.lcof;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import com.alibaba.fastjson.JSON;

/**
 * 面试题40. 最小的k个数
 * <p>
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * 
 * 示例 1：<br>
 * 输入：arr = [3,2,1], k = 2<br>
 * 输出：[1,2] 或者 [2,1]
 * <p>
 * 示例 2：<br>
 * 输入：arr = [0,1,2,1], k = 1<br>
 * 输出：[0]<br>
 * 
 * 限制：<br>
 * 0 <= k <= arr.length <= 10000 <br>
 * 0 <= arr[i] <= 10000 <br>
 * 
 * @author zm
 *
 */
public class Lcof040 {
	public static void main(String[] args) {
		Lcof040 l057 = new Lcof040();
		System.out.println(JSON.toJSONString(l057.new Solution3().getLeastNumbers(new int[] { 1, 2, 3 }, 2)));
		System.out.println(JSON.toJSONString(l057.new Solution3().getLeastNumbers(new int[] { 0, 1, 2, 1 }, 3)));
		System.out
				.println(JSON.toJSONString(l057.new Solution3().getLeastNumbers(new int[] { 1, 2, 3, 2, 1, 2, 3 }, 3)));
	}

	/**
	 * 排序
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int[] getLeastNumbers(int[] arr, int k) {
			Arrays.sort(arr);
			return Arrays.copyOf(arr, Math.min(k, arr.length));
		}
	}

	/**
	 * 参考快排思想
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int[] getLeastNumbers(int[] arr, int k) {
			return randomSelected(arr, 0, arr.length - 1, k - 1);
		}

		private int[] randomSelected(int[] arr, int left, int right, int k) {
			int j = partition(arr, left, right);
			if (j == k) {
				return Arrays.copyOf(arr, j + 1);
			}
			if (j > k) {
				return randomSelected(arr, left, j - 1, k);
			}
			return randomSelected(arr, j + 1, right, k);
		}

		// 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
		// 这边取出的哨兵默认就是第一个，还可以随机取，或者随机取3个后取中间的
		private int partition(int[] nums, int lo, int hi) {
			int v = nums[lo];
			int i = lo, j = hi + 1;
			while (true) {
				while (--j >= lo && nums[j] > v) {
				}
				while (++i <= hi && nums[i] < v) {
				}
				if (i >= j) {
					break;
				}
				int t = nums[j];
				nums[j] = nums[i];
				nums[i] = t;
			}
			nums[lo] = nums[j];
			nums[j] = v;
			return j;
		}
	}

	/**
	 * 使用大根堆，java实现使用优先队列，自定义排序
	 * 堆返回的数据并不是排序的，因为堆并不是有序的，只满足了堆顶的最大值（最小值）
	 * 如果需要使用有序的，还可以用树结构实现
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		public int[] getLeastNumbers(int[] arr, int k) {
			if (k == 0 || arr.length == 0) {
				return new int[0];
			}
			// 默认是小根堆，实现大根堆需要重写一下比较器。
			Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
			for (int num : arr) {
				if (pq.size() < k) {
					pq.offer(num);
				} else if (num < pq.peek()) {
					pq.poll();
					pq.offer(num);
				}
			}

			// 返回堆中的元素
			int[] res = new int[pq.size()];
			int idx = 0;
			for (int num : pq) {
				res[idx++] = num;
			}
			return res;
		}
	}
}

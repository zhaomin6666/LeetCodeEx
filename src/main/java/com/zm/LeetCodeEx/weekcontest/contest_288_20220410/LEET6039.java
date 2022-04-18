package com.zm.LeetCodeEx.weekcontest.contest_288_20220410;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 6039. K 次增加后的最大乘积
 * 给你一个非负整数数组 nums 和一个整数 k 。每次操作，你可以选择 nums 中 任一 元素并将它 增加 1 。
 * <p>
 * 请你返回 至多 k 次操作后，能得到的 nums的 最大乘积 。由于答案可能很大，请你将答案对 109 + 7 取余后返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,4], k = 5
 * 输出：20
 * 解释：将第一个数增加 5 次。
 * 得到 nums = [5, 4] ，乘积为 5 * 4 = 20 。
 * 可以证明 20 是能得到的最大乘积，所以我们返回 20 。
 * 存在其他增加 nums 的方法，也能得到最大乘积。
 * 示例 2：
 * <p>
 * 输入：nums = [6,3,3,2], k = 2
 * 输出：216
 * 解释：将第二个数增加 1 次，将第四个数增加 1 次。
 * 得到 nums = [6, 4, 3, 3] ，乘积为 6 * 4 * 3 * 3 = 216 。
 * 可以证明 216 是能得到的最大乘积，所以我们返回 216 。
 * 存在其他增加 nums 的方法，也能得到最大乘积。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length, k <= 105
 * 0 <= nums[i] <= 106
 */
public class LEET6039 {
	public static void main(String[] args) {
		System.out.println(new Solution().maximumProduct(new int[]{0, 4}, 5));
		System.out.println(new Solution().maximumProduct(new int[]{6, 3, 3, 2}, 2));
		System.out.println(new Solution().maximumProduct(new int[]{24, 5, 64, 53, 26, 38}, 54));
	}

	/**
	 * 先排序，从最小的开始加。用map统计每个数字的数量和k比较。
	 */
	static class Solution {
		public int maximumProduct(int[] nums, int k) {
			HashMap<Integer, Integer> cntMap = new HashMap<>();
			for (int num : nums) {
				cntMap.compute(num, (key, value) -> (value == null ? 0 : value) + 1);
			}
			int[] deDuplicationNum = new int[cntMap.size()];
			int deDuplicationNumIndex = 0;
			for (int key : cntMap.keySet()) {
				deDuplicationNum[deDuplicationNumIndex++] = key;
			}
			Arrays.sort(deDuplicationNum);
			int i = 0;
			int raw = 0;
			for (; i < deDuplicationNum.length; i++) {
				int curCnt = cntMap.get(deDuplicationNum[i]);
				if (curCnt <= k) {
					cntMap.compute(deDuplicationNum[i] + 1, (key, value) -> (value == null ? 0 : value) + curCnt);
					if (i + 1 == deDuplicationNum.length || deDuplicationNum[i] + 1 != deDuplicationNum[i + 1]) {
						++deDuplicationNum[i];
						i--;
					}
					k -= curCnt;
				}
				else {
					int finalK = k;
					cntMap.compute(deDuplicationNum[i], (key, value) -> value == null ? 0 : (value - finalK));
					raw = deDuplicationNum[i];
					cntMap.compute(++deDuplicationNum[i], (key, value) -> (value == null ? 0 : value) + finalK);
					if (i + 1 != deDuplicationNum.length && deDuplicationNum[i] == deDuplicationNum[i + 1]) {
						i++;
					}
					break;
				}
			}
			long result = 1;
			for (int j = 0; j < cntMap.get(raw); j++) {
				result *= raw;
				result %= 1000000007;
			}
			for (int j = i; j < deDuplicationNum.length; j++) {
				int cur = deDuplicationNum[j];
				for (int l = 0; l < cntMap.get(cur); l++) {
					result *= cur;
					result %= 1000000007;
				}
			}
			return (int) result;
		}
	}

	/**
	 * 直接用优先队列，k每次减一
	 * 优先队列每次操作为O(log(n)),总共为O(nlog(n))
	 */
	static class Solution2 {
		public int maximumProduct(int[] nums, int k) {
			int mod = 1000000007;
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int num : nums) {
				pq.offer(num);
			}
			while (k > 0) {
				k--;
				pq.offer(pq.poll() + 1);
			}
			long ans = 1;
			while (!pq.isEmpty()) {
				ans = (ans * pq.poll()) % mod;
			}
			return (int) ans;
		}
	}
}

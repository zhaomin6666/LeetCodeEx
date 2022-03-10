package com.zm.LeetCodeEx.algorithms.ex701_800;

/**
 * 798. 得分最高的最小轮调
 * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为
 * [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。
 * 此后，任何值小于或等于其索引的项都可以记作一分。
 * <p>
 * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,4,0]
 * 输出：3
 * 解释：
 * 下面列出了每个 k 的得分：
 * k = 0,  nums = [2,3,1,4,0],    score 2
 * k = 1,  nums = [3,1,4,0,2],    score 3
 * k = 2,  nums = [1,4,0,2,3],    score 3
 * k = 3,  nums = [4,0,2,3,1],    score 4
 * k = 4,  nums = [0,2,3,1,4],    score 3
 * 所以我们应当选择 k = 3，得分最高。
 * 示例 2：
 * <p>
 * 输入：nums = [1,3,0,2,4]
 * 输出：0
 * 解释：
 * nums 无论怎么变化总是有 3 分。
 * 所以我们将选择最小的 k，即 0。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] < nums.length
 *
 * @author zm
 */
public class LEET798 {
	public static void main(String[] args) {
		System.out.println(new Solution3().bestRotation(new int[]{2, 3, 1, 4, 0}));
		System.out.println(new Solution3().bestRotation(new int[]{1, 3, 0, 2, 4}));
		System.out.println(new Solution3().bestRotation(new int[]{0, 3, 1, 3, 1}));
		// k=0 03131 4
		// k=1 31310 3
		// k=2 13103 3
		// k=3 31031 4
		// k=4 10313 3
	}

	/**
	 * 对于每一个数每一个k进行遍历，记录可能的最大分值。
	 * O(n^2)
	 */
	static class Solution {
		public int bestRotation(int[] nums) {
			int n = nums.length;
			int[] getScore = new int[n];
			for (int i = 0; i < n; i++) {
				int num = nums[i];
				for (int k = 0; k < n; k++) {
					if (num <= (n + i - k) % n) {
						getScore[k]++;
					}
				}
			}
			int minK = 0;
			int max = 0;
			for (int i = 0; i < n; i++) {
				if (getScore[i] > max) {
					minK = i;
					max = getScore[i];
				}
			}
			return minK;
		}
	}

	/**
	 * 在上一个解答的基础上，可以看到第二个for循环其实是解决了当前num在不同k情况下的加分情况
	 * 其实加分情况可以通过数学方式计算出来。
	 * 首先k>=0,k<=n
	 * 当 x == i的情况下，向左移任何一小步，在不循环到右侧的情况下都是不满足要求的，只有当左撤循环到右侧时才满足。所以k=0或k>i。
	 * 当 x < i的情况下，可以向左移i-x步，同时如果循环到右侧，那么也是满足的。所以k<=i-x或k>i
	 * 当 x > i的情况下，不管怎么左移，都不满足，只有当循环到右侧(k>i)才可能满足，但是就算循环到右侧也不是都满足。循
	 * 环到右侧的下标为 i-k+n，要满足x<=i-k+n，k<=i-x+n。
	 * 整合三种条件可得
	 * x <= i: 更新score中 k属于[0,i-x]和(i,n)的值 [0,i-x],[i+1,n-1]
	 * x > i: 更新score中 k属于 (i,i-x+n]的值 [i+1,i-x+n]
	 * 由于批量更新数组的值也是会导致整个时间复杂度为O(n^2)，所以这里需要使用差分数列来保存值。
	 * 差分数列diff[i]中每一位数字的真实值为当前位数的前缀和。
	 * 如果i>=j的值需要+1，那么diff[j]++，可以让后面左右数的前缀和+1，即达到了批量更新的要求。
	 * 如果j<=i<=k的值需要+1，那么diff[j]++，diff[k+1]--，可以让k+1开始的值又还原了的状态，满足只更新j到i中间的值。
	 */
	static class Solution2 {
		public int bestRotation(int[] nums) {
			int n = nums.length;
			// n+1防止在i=n-1,num=0的情况下越界
			int[] scoreDiff = new int[n + 1];
			for (int i = 0; i < n; i++) {
				int num = nums[i];
				if (num <= i) {
					scoreDiff[0]++;
					scoreDiff[i - num + 1]--;
					scoreDiff[i + 1]++;
					scoreDiff[n]--;
				}
				else {
					scoreDiff[i + 1]++;
					scoreDiff[i - num + n + 1]--;
				}
			}
			int minK = 0;
			int max = 0;
			int score = 0;
			for (int i = 0; i < n; i++) {
				score += scoreDiff[i];
				if (score > max) {
					minK = i;
					max = score;
				}
			}
			return minK;
		}
	}

	/**
	 * 使用动态规划。
	 * 当左撤一位的情况下：
	 * (1)原来x=i的分数用于i--导致x>i而不能再得分，而x>i不会影响。
	 * (2)i=0的在左撤循环到最右侧时必然导致分数+1。
	 * 预处理不同k情况下x=i的数量，然后进行k的动态规划
	 */
	static class Solution3 {
		public int bestRotation(int[] nums) {
			int n = nums.length;
			int[] xEqualsIOnK = new int[n];
			int initialValue = 0;
			for (int i = 0; i < n; i++) {
				int x = nums[i];
				if (x == i) {
					initialValue++;
				}
				if (x <= i) {
					xEqualsIOnK[i - x]++;
				}
				else {
					xEqualsIOnK[i + n - x]++;
				}
			}
			int max = initialValue;
			int minK = 0;
			int[] dp = new int[n];
			dp[0] = initialValue;
			for (int i = 1; i < n; i++) {
				dp[i] = dp[i - 1] - xEqualsIOnK[i - 1] + 1;
				if (dp[i] > max) {
					max = dp[i];
					minK = i;
				}
			}
			return minK;
		}
	}
}

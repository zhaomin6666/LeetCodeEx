package com.zm.LeetCodeEx.weekcontest.contest_201_20200809;

/**
 * 1545. 找出第 N 个二进制字符串中的第 K 位
 * 给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：
 * <p>
 * S1 = "0"
 * 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
 * 其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）。
 * <p>
 * 例如，符合上述描述的序列的前 4 个字符串依次是：
 * <p>
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * 请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, k = 1
 * 输出："0"
 * 解释：S3 为 "0111001"，其第 1 位为 "0" 。
 * 示例 2：
 * <p>
 * 输入：n = 4, k = 11
 * 输出："1"
 * 解释：S4 为 "011100110110001"，其第 11 位为 "1" 。
 * 示例 3：
 * <p>
 * 输入：n = 1, k = 1
 * 输出："0"
 * 示例 4：
 * <p>
 * 输入：n = 2, k = 3
 * 输出："1"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= 2n - 1
 *
 * @author zm
 * @version 1.0
 * @date 2022-3-4
 * @since 1.8
 */
public class LEET1545 {
	public static void main(String[] args) {
		System.out.println(new Solution().findKthBit(4, 11));
		System.out.println(new Solution().findKthBit(3, 1));
	}

	/**
	 * 如果是后半部分，说明是反转过去的。将index翻转到前半部分。
	 */
	static class Solution {
		public char findKthBit(int n, int k) {
			// 翻转n次后的长度
			int[] len = new int[n + 1];
			len[1] = 1;
			for (int i = 2; i < n + 1; i++) {
				len[i] = len[i - 1] * 2 + 1;
			}
			boolean reverse = false;
			while (n > 1) {
				// 如果是中间的直接判断并返回
				if (k == (len[n] + 1) / 2) {
					return reverse ? '0' : '1';
				}
				if (k > (len[n] + 1) / 2) {
					reverse = !reverse;
					k = len[n] - k + 1;
				}
				n--;
			}
			return reverse ? '1' : '0';
		}
	}
}

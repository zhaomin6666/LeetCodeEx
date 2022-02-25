package com.zm.LeetCodeEx.algorithms.ex501_600;

/**
 * 537. 复数乘法
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 * <p>
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2：
 * <p>
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i^2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 * <p>
 * 提示：
 * <p>
 * num1 和 num2 都是有效的复数表示。
 *
 * @author zm
 */
public class LEET537 {
	public static void main(String[] args) {
		System.out.println(new Solution().complexNumberMultiply("1+1i", "1+1i"));
		System.out.println(new Solution().complexNumberMultiply("1+-1i", "1+-1i"));
	}

	static class Solution {
		public String complexNumberMultiply(String num1, String num2) {
			int[] num1array = trans(num1);
			int[] num2array = trans(num2);
			int b = num1array[0] * num2array[1] + num2array[0] * num1array[1];
			int a = num1array[0] * num2array[0] - num1array[1] * num2array[1];
			return a + "+" + b + "i";
		}

		private int[] trans(String num) {
			int[] numArray = new int[2];
			// String[] numStrArray = num.split("[+i]");
			// numArray[0] = Integer.parseInt(numStrArray[0]);
			// numArray[1] = Integer.parseInt(numStrArray[1]);
			String[] numStrArray = num.split("\\+");
			numArray[0] = Integer.parseInt(numStrArray[0]);
			numArray[1] = Integer.parseInt(numStrArray[1].substring(0, numStrArray[1].length() - 1));
			return numArray;
		}
	}
}

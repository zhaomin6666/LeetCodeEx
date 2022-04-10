package com.zm.LeetCodeEx.weekcontest.contest_288_20220410;

import java.util.Arrays;

/**
 * 6038. 向表达式添加括号后的最小结果
 * 给你一个下标从 0 开始的字符串 expression ，格式为 "<num1>+<num2>" ，其中 <num1> 和 <num2> 表示正整数。
 * <p>
 * 请你向 expression 中添加一对括号，使得在添加之后， expression 仍然是一个有效的数学表达式，并且计算后可以得到 最小 可能值。左括号 必须 添加在 '+' 的左侧，而右括号必须添加在 '+' 的右侧。
 * <p>
 * 返回添加一对括号后形成的表达式 expression ，且满足 expression 计算得到 最小 可能值。如果存在多个答案都能产生相同结果，返回任意一个答案。
 * <p>
 * 生成的输入满足：expression 的原始值和添加满足要求的任一对括号之后 expression 的值，都符合 32-bit 带符号整数范围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：expression = "247+38"
 * 输出："2(47+38)"
 * 解释：表达式计算得到 2 * (47 + 38) = 2 * 85 = 170 。
 * 注意 "2(4)7+38" 不是有效的结果，因为右括号必须添加在 '+' 的右侧。
 * 可以证明 170 是最小可能值。
 * 示例 2：
 * <p>
 * 输入：expression = "12+34"
 * 输出："1(2+3)4"
 * 解释：表达式计算得到 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20 。
 * 示例 3：
 * <p>
 * 输入：expression = "999+999"
 * 输出："(999+999)"
 * 解释：表达式计算得到 999 + 999 = 1998 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= expression.length <= 10
 * expression 仅由数字 '1' 到 '9' 和 '+' 组成
 * expression 由数字开始和结束
 * expression 恰好仅含有一个 '+'.
 * expression 的原始值和添加满足要求的任一对括号之后 expression 的值，都符合 32-bit 带符号整数范围
 */
public class LEET6038 {
	public static void main(String[] args) {
		System.out.println(new Solution().minimizeResult("247+38"));
		System.out.println(new Solution().minimizeResult("12+34"));
		System.out.println(new Solution().minimizeResult("999+999"));
	}

	/**
	 * 预处理一下，暴力模拟
	 */
	static class Solution {
		public String minimizeResult(String expression) {
			String[] splits = expression.split("\\+");
			String leftPart = splits[0];
			String rightPart = splits[1];
			int[] leftPartFromLeft = new int[leftPart.length()];
			int[] leftPartFromRight = new int[leftPart.length()];
			int[] rightPartFromLeft = new int[rightPart.length()];
			int[] rightPartFromRight = new int[rightPart.length()];
			doAnalyzeFromLeft(leftPart, leftPartFromLeft);
			doAnalyzeFromRight(leftPart, leftPartFromRight);
			doAnalyzeFromLeft(rightPart, rightPartFromLeft);
			doAnalyzeFromRight(rightPart, rightPartFromRight);
			System.out.println(Arrays.toString(leftPartFromLeft));
			System.out.println(Arrays.toString(leftPartFromRight));
			System.out.println(Arrays.toString(rightPartFromLeft));
			System.out.println(Arrays.toString(rightPartFromRight));
			int min = Integer.MAX_VALUE;
			int[] minIJ = new int[2];
			for (int i = 1; i <= leftPart.length(); i++) {
				for (int j = 1; j <= rightPart.length(); j++) {
					int cur = (i == leftPart.length() ? 1 : leftPartFromLeft[leftPart.length() - i - 1])
							* (leftPartFromRight[leftPart.length() - i] + rightPartFromLeft[j - 1])
							* (j == rightPart.length() ? 1 : rightPartFromRight[j]);
					if (cur < min) {
						min = cur;
						minIJ[0] = i;
						minIJ[1] = j;
					}
				}
			}
			System.out.println(Arrays.toString(minIJ));
			StringBuilder sb = new StringBuilder();
			sb.append(leftPart, 0, leftPart.length() - minIJ[0]);
			sb.append("(");
			sb.append(leftPart, leftPart.length() - minIJ[0], leftPart.length());
			sb.append("+");
			sb.append(rightPart, 0, minIJ[1]);
			sb.append(")");
			sb.append(rightPart, minIJ[1], rightPart.length());
			return sb.toString();
		}

		private void doAnalyzeFromLeft(String part, int[] partFromLeft) {
			char[] cs = part.toCharArray();
			int result = 0;
			for (int i = 0; i < cs.length; i++) {
				result *= 10;
				result += cs[i] - '0';
				partFromLeft[i] = result;
			}
		}

		private void doAnalyzeFromRight(String part, int[] partFromRight) {
			char[] cs = part.toCharArray();
			int result = 0;
			int ten = 1;
			for (int i = cs.length - 1; i >= 0; i--) {
				result += ten * (cs[i] - '0');
				ten *= 10;
				partFromRight[i] = result;
			}
		}
	}
}

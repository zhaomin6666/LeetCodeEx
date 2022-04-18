package com.zm.LeetCodeEx.algorithms.ex401_500;

/**
 * 420. 强密码检验器
 * <p>
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 * 由至少 6 个，至多 20 个字符组成。
 * 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。
 * 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。
 * 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0 。
 * <p>
 * 在一步修改操作中，你可以：
 * <p>
 * 插入一个字符到 password ，
 * 从 password 中删除一个字符，或
 * 用另一个字符来替换 password 中的某个字符。
 * <p>
 * 示例 1：
 * <p>
 * 输入：password = "a"
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：password = "aA1"
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：password = "1337C0d3"
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 1 <= password.length <= 50
 * password 由字母、数字、点 '.' 或者感叹号 '!'
 *
 * @author zm
 */
public class LEET420 {
	public static void main(String[] args) {
		System.out.println(new Solution().strongPasswordChecker("bbaaaaaaaaaaaaaaacccccc"));
		System.out.println(new Solution().strongPasswordChecker("aaaAAA"));
		System.out.println(new Solution().strongPasswordChecker("a"));
		System.out.println(new Solution().strongPasswordChecker("aA1"));
		System.out.println(new Solution().strongPasswordChecker("aabaa"));
		System.out.println(new Solution().strongPasswordChecker("1337C0d3"));
		System.out.println(new Solution().strongPasswordChecker("a1a1a1aaa111"));
	}

	/**
	 * 分成3种情况进行考虑
	 * 1.len<6，只考虑增加
	 * 2.6<=len<=20，只考虑替换
	 * 3.len>20，考虑替换和删除，由于只替换是不能完成任务的，所以删除是必要的。
	 * 当连续数长度mod3=0时，删除1个字符和替换1个字符结果一样。直接执行。
	 * 当连续数长度mod3=1时，删除2个字符和替换1个字符结果一样。如果最后还需要删除的话优先执行这个。
	 * 当连续数长度mod3=2时，删除3个字符和替换1个字符结果一样。没办法了再执行这个。
	 */
	static class Solution {
		public int strongPasswordChecker(String password) {
			char[] cs = password.toCharArray();
			int len = cs.length;
			int hasDigits = 0;
			int hasUpper = 0;
			int hasLower = 0;

			if (len < 6) {
				for (char c : cs) {
					// 统计三种数数量
					if (hasDigits == 0 && Character.isDigit(c)) {
						hasDigits = 1;
					}
					else if (hasUpper == 0 && Character.isUpperCase(c)) {
						hasUpper = 1;
					}
					else if (hasLower == 0 && Character.isLowerCase(c)) {
						hasLower = 1;
					}
				}
				return Math.max(6 - len, 3 - hasDigits - hasUpper - hasLower);
			}
			else if (len <= 20) {
				int modifyCount = 0;
				char lastChar = '#';
				int lastCharCount = 1;
				for (char c : cs) {
					// 统计三种数数量
					if (hasDigits == 0 && Character.isDigit(c)) {
						hasDigits = 1;
					}
					else if (hasUpper == 0 && Character.isUpperCase(c)) {
						hasUpper = 1;
					}
					else if (hasLower == 0 && Character.isLowerCase(c)) {
						hasLower = 1;
					}
					if (c == lastChar) {
						if (lastCharCount == 2) {
							++modifyCount;
							lastChar = '-';
							lastCharCount = 1;
						}
						else {
							lastCharCount++;
						}
					}
					else {
						lastChar = c;
						lastCharCount = 1;
					}
				}
				return Math.max(modifyCount, 3 - hasDigits - hasUpper - hasLower);
			}
			else {
				int modifyCount = 0;
				int needRemoveCount = len - 20;
				char lastChar = '#';
				int lastCharCount = 1;
				int mod3Is1Count = 0;
				for (char c : cs) {
					// 统计三种数数量
					if (hasDigits == 0 && Character.isDigit(c)) {
						hasDigits = 1;
					}
					else if (hasUpper == 0 && Character.isUpperCase(c)) {
						hasUpper = 1;
					}
					else if (hasLower == 0 && Character.isLowerCase(c)) {
						hasLower = 1;
					}
					if (c == lastChar) {
						lastCharCount++;
					}
					else {
						if (needRemoveCount > 0 && lastCharCount >= 3) {
							if (lastCharCount % 3 == 0) {
								// 如果连续数字是3的整数，那么只要删除一个字符就可以减少一次替换操作
								--modifyCount;
								--needRemoveCount;
							}
							else if (lastCharCount % 3 == 1) {
								// 对于mod为1的连续数，需要删掉两个字符才能减少一次替换操作
								++mod3Is1Count;
							}
							// 对于mod为2的连续数，需要删掉三个字符才能减少一次替换操作
						}
						// 执行中间部分必要的替换操作
						modifyCount += lastCharCount / 3;
						lastChar = c;
						lastCharCount = 1;
					}
				}
				if (needRemoveCount > 0 && lastCharCount >= 3) {
					if (lastCharCount % 3 == 0) {
						--modifyCount;
						--needRemoveCount;
					}
					else if (lastCharCount % 3 == 1) {
						++mod3Is1Count;
					}
				}
				modifyCount += lastCharCount / 3;

				// 由于一定要满足删除字符达到长度小于等于20，所以在继续删除字符的情况下，尽可能将原来在遍历中的替换操作用删除操作置换出来
				// 使用mod3Is1的数量，每删除两个可以置换出一个替换操作
				int useMod3Is1 = Math.min(Math.min(modifyCount, mod3Is1Count), needRemoveCount / 2);
				modifyCount -= useMod3Is1;
				needRemoveCount -= useMod3Is1 * 2;
				// 如果还需要删除字符，那么只能删除三个字符来置换出一个替换操作
				int useMod3Is2 = Math.min(modifyCount, needRemoveCount / 3);
				modifyCount -= useMod3Is2;
				// 最后这个needRemoveCount应该<=0
				needRemoveCount -= useMod3Is2 * 2;
				return len - 20 + Math.max(modifyCount, 3 - hasDigits - hasUpper - hasLower);
			}
		}
	}
}

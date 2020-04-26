package com.zm.LeetCodeEx.weekcontest.before20200426;

/**
 * 周赛 2020年4月5日
 * <p>
 * 5377. 将二进制表示减到 1 的步骤数
 * <p>
 * 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：
 * <p>
 * 如果当前数字为偶数，则将其除以 2 。
 * <p>
 * 如果当前数字为奇数，则将其加上 1 。
 * <p>
 * 题目保证你总是可以按上述规则将测试用例变为 1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1101" <br>
 * 输出：6 <br>
 * 解释："1101" 表示十进制数 13 。 <br>
 * Step 1) 13 是奇数，加 1 得到 14 <br>
 * Step 2) 14 是偶数，除 2 得到 7 <br>
 * Step 3) 7 是奇数，加 1 得到 8 <br>
 * Step 4) 8 是偶数，除 2 得到 4 <br>
 * Step 5) 4 是偶数，除 2 得到 2 <br>
 * Step 6) 2 是偶数，除 2 得到 1
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "10" <br>
 * 输出：1 <br>
 * 解释："10" 表示十进制数 2 。 <br>
 * Step 1) 2 是偶数，除 2 得到 1
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "1" <br>
 * 输出：0
 * 
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500 <br>
 * s 由字符 '0' 或 '1' 组成。 <br>
 * s[0] == '1'
 *
 * @author zm
 */
public class LEET5377 {
	public static void main(String[] args) {
		LEET5377 l5377 = new LEET5377();
		System.out.println(l5377.new Solution().numSteps("1111")); // 5 = 1+4
		System.out.println(l5377.new Solution().numSteps("1101")); // 6 = 2+4
		System.out.println(l5377.new Solution().numSteps("1001")); // 7 = 3+4
		System.out.println(l5377.new Solution().numSteps("1000")); // 3 = 0+3
		System.out.println(l5377.new Solution().numSteps("1100")); // 5 = 1+4
		System.out.println(l5377.new Solution().numSteps("1010")); // 6 = 2+4

		System.out.println(l5377.new Solution().numSteps("111")); // 4 = 1+3
		System.out.println(l5377.new Solution().numSteps("101")); // 5 = 2+3
		System.out.println(l5377.new Solution().numSteps("100")); // 2 = 0+2
		System.out.println(l5377.new Solution().numSteps("10")); //1
		System.out.println(l5377.new Solution().numSteps("1")); //0
	}

	class Solution {
		public int numSteps(String s) {
			char[] cs = s.toCharArray();
			boolean has1 = false;
			int cnt0 = 0;
			for (int i = s.length() - 1; i > 0; i--) {
				if (cs[i] == '1') {
					has1 = true;
				}else if (has1) {
					cnt0 ++;
				}
			}
			return has1?(cnt0+1+s.length()) : (s.length()-1);
		}
	}
}

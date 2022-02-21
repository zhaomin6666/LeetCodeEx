package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 8. 字符串转换整数 (atoi)
 * 
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 
 * 说明：
 * 
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31, 2^31 − 1]。如果数值超过这个范围，qing返回 INT_MAX
 * (2^31 − 1) 或 INT_MIN (−2^31) 。
 * 
 * @author zm
 *
 */
public class LEET008 {
	public static void main(String[] args) {
		LEET008 L008 = new LEET008();
		//System.out.println(L008.new Solution2().myAtoi("  -000123415611"));
		
		System.out.println(L008.new Solution2().myAtoi(String.valueOf(Integer.MIN_VALUE)));
		System.out.println(L008.new Solution2().myAtoi(String.valueOf(Integer.MAX_VALUE)));
		System.out.println(L008.new Solution2().myAtoi("-91283472332"));
		

	}

	class Solution {
		/**
		 * 去掉空格后判断第一个字符，然后去除连续的数字
		 * 
		 * @param str
		 * @return
		 */
		public int myAtoi(String str) {
			str = str.trim();

			if (str.length() == 0) {
				return 0;
			}

			int i = 0, digit = 0;
			double sum = 0;

			// 判断正负
			if (str.charAt(0) == '-') {
				digit = -1;
				i++; // 下标后移
			} else if (str.charAt(0) == '+') {
				digit = 1;
				i++;
			} else if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
				digit = 1;
			} else {
				return 0;
			}

			while (i < str.length()) {
				if (sum > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				if (sum < Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}
				if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
					sum = sum * 10 + (str.charAt(i) - '0') * digit;
				} else {
					break;
				}
				i++;
			}
			return (int) sum;
		}
	}
	
	class Solution2 {
	    public int myAtoi(String str) {
	        char[] chars = str.toCharArray();
	        int n = chars.length;
	        int idx = 0;
	        while (idx < n && chars[idx] == ' ') {
	            // 去掉前导空格
	            idx++;
	        }
	        if (idx == n) {
	            //去掉前导空格以后到了末尾了
	            return 0;
	        }
	        boolean negative = false;
	        if (chars[idx] == '-') {
	            //遇到负号
	            negative = true;
	            idx++;
	        } else if (chars[idx] == '+') {
	            // 遇到正号
	            idx++;
	        } else if (!Character.isDigit(chars[idx])) {
	            // 其他符号
	            return 0;
	        }
	        int ans = 0;
	        while (idx < n && Character.isDigit(chars[idx])) {
	            int digit = chars[idx] - '0';
	            if (ans > (Integer.MAX_VALUE - digit) / 10) {
	                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
	                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
	                return negative? Integer.MIN_VALUE : Integer.MAX_VALUE;
	            }
	            ans = ans * 10 + digit;
	            idx++;
	        }
	        return negative? -ans : ans;
	    }
	}
}

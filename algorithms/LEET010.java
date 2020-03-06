package com.zm.LeetCodeEx.algorithms;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * 
 * '.' 匹配任意单个字符。 '*' 匹配零个或多个前面的元素。 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 * 
 * @author zm
 *
 */
public class LEET010 {
	public static void main(String[] args) {
		System.out.println(isMatch("bbcacbabbcbaaccabc", "b*a*a*.c*bb*b*.*.*"));
	}

	/**
	 * 如果p为0位1位很好理解 如果p是2位及以上 那么如果p第2位不是*，那么s不能为空，不然无法匹配p的第1位
	 * 然后直接判断首位是否相同（包括.）是的话去掉首位，递归调用函数，不是返回false 如果p第2位是*，那么s可以为空，如果s为空，则把p首两位的
	 * ‘某*’去掉，递归调用 如果s不为空，那么判断s首位是否相同（包括.），不同的话则把p首两位的
	 * ‘某*’去掉，递归调用，相同的话判断s的第二位即去s.substring(1)
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isMatch(String s, String p) {
		if (p.length() == 0)
			return s.length() == 0;
		if (p.length() == 1) {
			return (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
		}
		if (p.charAt(1) != '*') {
			if (s.length() == 0)
				return false;
			return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
		}
		while (s.length() != 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
			if (isMatch(s, p.substring(2)))
				return true;
			s = s.substring(1);
		}
		return isMatch(s, p.substring(2));
	}

}

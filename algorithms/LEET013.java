package com.zm.LeetCodeEx.algorithms;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 
 * 字符 数值 I 1 V 5 X 10 L 50 C 100 D 500 M 1000 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做
 * XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + II 。
 * 
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
 * 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * 
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999
 * 的范围内。
 * 
 * @author zm
 *
 */
public class LEET013 {
	public static void main(String[] args) {
		System.out.println(new LEET013().romanToInt("MDCCCXLI"));// 1841
	}

	/**
	 * 调换*M和M的顺序再做判断
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		int values[] = { 900, 1000, 400, 500, 90, 100, 40, 50, 10, 9, 5, 4, 1 };
		String reps[] = { "CM", "M", "CD", "D", "XC", "C", "XL", "L", "IX", "X", "IV", "V", "I" };
		int res = 0;
		while (s.length() > 0) {
			for (int i = 0; i < 13; i++) {
				if (s.startsWith(reps[i])) {
					res += values[i];
					s = s.substring(reps[i].length());
					break;
				}
			}
		}
		return res;
	}
}

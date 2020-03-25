package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 43. 字符串相乘 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3" 输出: "6" 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456" 输出: "56088" 说明：
 * <p>
 * num1 和 num2 的长度小于110。 num1 和 num2 只包含数字 0-9。 num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author zm
 */
public class LEET043 {
	public static void main(String[] args) {
		LEET043 l043 = new LEET043();
		String num1 = "999";
		String num2 = "1";
		System.out.println(l043.multiply(num1, num2));
		// System.out.println(l043.multiply("1998", "21"));
	}

	/**
	 * 两个数相乘，每个数的各个位数对应相乘之后根据不同的位数加上0之后累加<br>
	 * 比如12*23，2(个位)*3(个位)=6(不用加0)，2(个位)*2(十位)=4(加1个0)，<br>
	 * 1(十位)*2(十位)=2(加2个0)，1(十位)*3(个位)=3(加1个0)<br>
	 * 然后6+40+200+30 = 276
	 *
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiply(String num1, String num2) {
		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}
		int[] multiValue = new int[num1.length() + num2.length()];
		for (int i = num1.length() - 1; i >= 0; i--) {
			for (int j = num2.length() - 1; j >= 0; j--) {
				multiValue[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
			}
		}
		int carry = 0;
		for (int i = multiValue.length - 1; i >= 0; i--) {
			multiValue[i] += carry;
			carry = multiValue[i] / 10;
			multiValue[i] %= 10;
		}
		int beginIndex = 0;
		if (multiValue[0] == 0) {
			beginIndex = 1;
		}
		for (int i = beginIndex; i < multiValue.length; i++) {
			multiValue[i] += '0';
		}
		return new String(multiValue, beginIndex, multiValue.length - beginIndex);
	}
}

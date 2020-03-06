package com.zm.LeetCodeEx.algorithms;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 
 * @author zm
 *
 */
public class LEET007 {
	public static void main(String[] args) {
		LEET007 L007 = new LEET007();
		// System.out.println(L007.reverse(-21474));
		System.out.println(L007.reverse2(-1136481199));

	}

	/**
	 * 用String
	 * 
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		String xstr = String.valueOf(x);
		StringBuffer sb = new StringBuffer();
		if (x < 0) {
			sb.append("-");
			xstr = xstr.substring(1);
		}
		for (int i = 0; i < xstr.length(); i++) {
			sb.append(xstr.substring(xstr.length() - 1 - i, xstr.length() - i));
		}
		String result = sb.toString();
		return Double.valueOf(result) > Integer.MAX_VALUE || Double.valueOf(result) < Integer.MIN_VALUE ? 0
				: Integer.valueOf(result);
	}

	/**
	 * 
	 * @param x
	 * @return
	 */
	public int reverse2(int x) {
		long temp = 0;
		int len = (int) Math.log10(Math.abs((double) x));
		while (len >= 0) {
			temp += Math.pow(10, len--) * (x % 10);
			x = x / 10;
		}
		if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) {
			return 0;
		}
		return (int) temp;
	}

	/**
	 * 上述方法优化
	 * 
	 * @param x
	 * @return
	 */
	public int reverse3(int x) {
		long temp = 0;
		while (Math.abs(x) > 0) {
			temp = temp * 10 + (x % 10);
			x = x / 10;
		}
		if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) {
			return 0;
		}
		return (int) temp;
	}
}

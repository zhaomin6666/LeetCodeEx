package LeetCode;

public class LEET008 {
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

	public static void main(String[] args) {
		LEET008 L008 = new LEET008();
		System.out.println(L008.myAtoi("  -000123415611"));
	}
}

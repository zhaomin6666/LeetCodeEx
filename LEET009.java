package LeetCode;

public class LEET009 {
	public static boolean isPalindrome(int x) {
		int raw = x;
		if (x < 0) {
			return false;
		}
		int y = 0;
		while (x > 0) {
			y = y * 10 + x % 10;
			x /= 10;
		}
		if (y > Integer.MAX_VALUE || y < 0) {
			return false;
		}
		return y == raw;
	}

	public static boolean isPalindrome2(int x) {
		if (x < 0 || (x % 10 == 0 && x != 0)) {
			return false;
		}
		int y = 0;
		while (x > y) {
			y = y * 10 + x % 10;
			x /= 10;
		}
		return x == y || x == y / 10;
	}

	public static boolean isPalindrome3(int x) {
		String str = String.valueOf(x);
		char[] s = str.toCharArray();
		if (s.length == 1) {
			return true;
		}
		int i = 0;
		int j = str.length() - 1;
		while (j >= 0 && i <= str.length() - 1) {
			if (s[i] == s[j]) {
				i++;
				j--;
			} else {
				return false;
			}
			if (j < i) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isPalindrome3(10));
	}
}

package LeetCode;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * @author zm
 *
 */
public class LEET009 {
	public static void main(String[] args) {
		System.out.println(isPalindrome3(10));
	}
	
	/**
	 * 翻转之后是否相同
	 * @param x
	 * @return
	 */
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

	/**
	 * 13435
	 * x为13435不断的/10
	 * y为"5"+"3"+"4"..
	 * 当534>13时跳出循环
	 * 
	 * 如果是互文，那么应该是134和134相等
	 * 
	 * 1331
	 * x  1331	y  0
	 * x  133	y  1
	 * x  13	y  13	x==y
	 * 
	 * 13431
	 * x  13431	y  0
	 * x  1343	y  1
	 * x  134	y  13
	 * x  13	y  134	x==y/10
	 * @param x
	 * @return
	 */
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

	/**
	 * 使用String
	 * @param x
	 * @return
	 */
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
}

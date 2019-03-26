package LeetCode;

public class LEET010 {
	public static boolean isMatch(String s, String p) {
		if (p.length() == 0)
			return s.length() == 0;
		if (p.length() == 1) {
			return (s.length() == 1
					&& (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
		}
		if (p.charAt(1) != '*') {
			if (s.length() == 0)
				return false;
			return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
					&& isMatch(s.substring(1), p.substring(1));
		}
		while (s.length() != 0
				&& (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
			if (isMatch(s, p.substring(2)))
				return true;
			s = s.substring(1);
		}
		return isMatch(s, p.substring(2));
	}

	public static void main(String[] args) {
		System.out.println(isMatch("bbcacbabbcbaaccabc","b*a*a*.c*bb*b*.*.*"));
	}
}

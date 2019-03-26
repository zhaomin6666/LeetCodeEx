package LeetCode;

public class LEET013 {
	public int romanToInt(String s) {
		int values[] = {900, 1000, 400, 500, 90, 100, 40, 50, 10, 9, 5, 4, 1};
		String reps[] = {"CM", "M", "CD", "D", "XC", "C", "XL", "L", "IX", "X",
				"IV", "V", "I"};
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

	public static void main(String[] args) {
		System.out.println(new LEET013().romanToInt("MDCCCXLI"));// 1841
	}
}

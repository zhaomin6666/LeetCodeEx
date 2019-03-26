package LeetCode;

public class LEET006 {
	public String convert(String s, int numRows) {
		if ("".equals(s)) {
			return "";
		}
		if (numRows == 0 || numRows == 1) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		int len = s.length();
		char[] schars = s.toCharArray();
		int grouplen = numRows * 2 - 2;
		int groupcnt = (int) Math.ceil((double) len / grouplen);
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < groupcnt; j++) {
				if (i == 0 || i == numRows - 1) {
					if (j * grouplen + i < len) {
						sb.append(schars[j * grouplen + i]);
					}
				} else {
					if (j * grouplen + i < len) {
						sb.append(schars[j * grouplen + i]);
					}
					if (j * grouplen + grouplen - i < len) {
						sb.append(schars[j * grouplen + grouplen - i]);
					}
				}

			}

		}
		return sb.toString();
	}

	public static void main(String[] args) {
		LEET006 L006 = new LEET006();
		System.out.println(L006.convert("A", 1));
		// System.out.println(L006.convert("LEETCODEISHIRING", 3)); // 16
		// LCIRETOESIIGEDHN
		// System.out.println(L006.convert("LEETCODEISHIRING", 4)); //
		// LDREOEIIECIHNTSG
	}
}

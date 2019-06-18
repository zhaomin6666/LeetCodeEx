package com.zm.LeetCodeEx;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。 比如输入字符串为 "LEETCODEISHIRING" 行数为 3
 * 时，排列如下： L C I R E T O E S I I G E D H N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 
 * @author zm
 *
 */
public class LEET006 {
	public static void main(String[] args) {
		LEET006 L006 = new LEET006();
		System.out.println(L006.convert("A", 1));
		// System.out.println(L006.convert("LEETCODEISHIRING", 3)); // 16
		// LCIRETOESIIGEDHN
		// System.out.println(L006.convert("LEETCODEISHIRING", 4)); //
		// LDREOEIIECIHNTSG
	}

	/**
	 * 根据图形查找特征，按照上面的举例3行的"LCIRETOESIIGEDHN"。每4个分为一组。然后第一行就是每一组的第1个，第二行就是每一组的第2个和倒数第1个，第n行就是每一组的第n个和倒数第n-1个
	 * 官方解答类似
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
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

	/**
	 * 官方解答2：按行扫描 比如上述例子，分为3行，那么就生成3个StringBuilder放到List中
	 * List.get(0)....StringBuilder==== List.get(1)....StringBuilder====
	 * List.get(2)....StringBuilder====
	 * 
	 * 用一个参数来平判断行数0,1,2是增大还是减小，即向上还是向下
	 * 
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert2(String s, int numRows) {
		if (numRows == 1)
			return s;

		List<StringBuilder> rows = new ArrayList<>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++)
			rows.add(new StringBuilder());

		int curRow = 0;
		boolean goingDown = false;

		for (char c : s.toCharArray()) {
			rows.get(curRow).append(c);
			if (curRow == 0 || curRow == numRows - 1)
				goingDown = !goingDown;
			curRow += goingDown ? 1 : -1; // 变换方向
		}

		StringBuilder ret = new StringBuilder();
		for (StringBuilder row : rows)
			ret.append(row);
		return ret.toString();
	}
}

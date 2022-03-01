package com.zm.LeetCodeEx.algorithms.ex1001_1100;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * <p>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 *
 * @author zm
 */
public class LEET1047 {
	public static void main(String[] args) {
		System.out.println(new Solution2().removeDuplicates("azxxzy"));
	}

	/**
	 * 用一个指针标识栈顶元素位置
	 */
	static class Solution {
		public String removeDuplicates(String s) {
			StringBuilder stack = new StringBuilder();
			char[] cs = s.toCharArray();
			int index = -1;
			for (char c : cs) {
				if (index >= 0 && stack.charAt(index) == c) {
					stack.deleteCharAt(index);
					index--;
				}
				else {
					index++;
					stack.append(c);
				}
			}
			return stack.toString();
		}
	}

	/**
	 * 优化，不使用StringBuilder，在原数组上进行操作。
	 * 把暂时不重复的值移动到有效index的位置。
	 */
	static class Solution2 {
		public String removeDuplicates(String s) {
			char[] cs = s.toCharArray();
			int index = -1;
			for (char c : cs) {
				if (index >= 0 && cs[index] == c) {
					index--;
				}
				else {
					cs[++index] = c;
				}
			}
			return String.valueOf(cs, 0, index + 1);
		}
	}
}

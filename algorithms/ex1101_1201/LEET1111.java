package com.zm.LeetCodeEx.algorithms.ex1101_1201;

import java.util.Arrays;

/**
 * 1103. 分糖果 II
 * <p>
 * 有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。
 * <p>
 * 嵌套深度 depth 定义：即有效括号字符串嵌套的层数，depth(A) 表示有效括号字符串 A 的嵌套深度。详情参见题末「嵌套深度」部分。
 * <p>
 * 给你一个「有效括号字符串」 seq，请你将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小。
 * <p>
 * 不相交：每个 seq[i] 只能分给 A 和 B 二者中的一个，不能既属于 A 也属于 B 。<br>
 * A 或 B 中的元素在原字符串中可以不连续。<br>
 * A.length + B.length = seq.length <br>
 * max(depth(A), depth(B)) 的可能取值最小。<br>
 * 划分方案用一个长度为 seq.length 的答案数组 answer 表示，编码规则如下：<br>
 * answer[i] = 0，seq[i] 分给 A 。<br>
 * answer[i] = 1，seq[i] 分给 B 。<br>
 * 如果存在多个满足要求的答案，只需返回其中任意 一个 即可。
 * <p>
 * 示例 1：<br>
 * 输入：seq = "(()())"<br>
 * 输出：[0,1,1,1,1,0]
 * <p>
 * 示例 2：<br>
 * 输入：seq = "()(())()"<br>
 * 输出：[0,0,0,1,1,0,1,1]
 * <p>
 * 提示：<br>
 * 1 <= text.size <= 10000  
 * <p>
 * 有效括号字符串：
 * <p>
 * 仅由 "(" 和 ")" 构成的字符串，对于每个左括号，都能找到与之对应的右括号，反之亦然。<br>
 * 下述几种情况同样属于有效括号字符串：<br>
 * 1. 空字符串<br>
 * 2. 连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串<br>
 * 3. 嵌套，可以记作 (A)，其中 A 是有效括号字符串
 * <p>
 * 嵌套深度：
 * <p>
 * 类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：<br>
 * 1. s 为空时，depth("") = 0<br>
 * 2. s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A
 * 和 B 都是有效括号字符串<br>
 * 3. s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
 * <p>
 * 例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
 * 
 * 
 * @author zm
 */
public class LEET1111 {
	public static void main(String[] args) {
		LEET1111 l1111 = new LEET1111();
		System.out.println(Arrays.toString(l1111.new Solution().maxDepthAfterSplit("(()())")));
		System.out.println(Arrays.toString(l1111.new Solution().maxDepthAfterSplit("()((()))()")));
	}

	/**
	 * 用a,b记录两个字符串中的'('数量 1.优先往数量少的字符串上加'('，相同就放a上。优先往a上放'('
	 * 2.优先往数量多的字符串上加')'，相同就放b上。优先消去b上的'('，与1中优先往a上放'('对应。
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int[] maxDepthAfterSplit(String seq) {
			int a = 0;
			int b = 0;
			char[] cs = seq.toCharArray();
			int[] ret = new int[seq.length()];
			for (int i = 0; i < ret.length; i++) {
				if (cs[i] == '(') {
					if (a <= b) {
						a++;
						ret[i] = 0;
					} else {
						b++;
						ret[i] = 1;
					}
				} else {
					if (b >= a) {
						b--;
						ret[i] = 1;
					} else {
						a--;
						ret[i] = 0;
					}
				}
			}
			return ret;
		}
	}

	/**
	 * 用gap表示ab差值，少用一个变量
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int[] maxDepthAfterSplit(String seq) {
			int gap = 0;
			char[] cs = seq.toCharArray();
			int[] ret = new int[seq.length()];
			for (int i = 0; i < ret.length; i++) {
				if (cs[i] == '(') {
					if (gap <= 0) {
						gap++;
						ret[i] = 0;
					} else {
						gap--;
						ret[i] = 1;
					}
				} else {
					if (0 >= gap) {
						gap++;
						ret[i] = 1;
					} else {
						gap--;
						ret[i] = 0;
					}
				}
			}
			return ret;
		}
	}

	/**
	 * 找规律（官方题解） 思路及算法
	 * 
	 * 我们还是使用上面的例子 (()(())())，但这里我们把 ( 和 ) 的嵌套深度分成两行：
	 * 
	 * 括号序列 ( ( ) ( ( ) ) ( ) ) <br>
	 * 下标编号 0 1 2 3 4 5 6 7 8 9 <br>
	 * 嵌套深度 1 2 - 2 3 - - 2 - - <br>
	 * 嵌套深度 - - 2 - - 3 2 - 2 1 <br>
	 * 有没有发现什么规律？<br>
	 * 左括号 ( 的下标编号与嵌套深度的奇偶性相反，也就是说：<br>
	 * 下标编号为奇数的 (，其嵌套深度为偶数，分配给 B；<br>
	 * 下标编号为偶数的 (，其嵌套深度为奇数，分配给 A。<br>
	 * 右括号 ) 的下标编号与嵌套深度的奇偶性相同，也就是说：<br>
	 * 下标编号为奇数的 )，其嵌套深度为奇数，分配给 A；<br>
	 * 下标编号为偶数的 )，其嵌套深度为偶数，分配给 B。<br>
	 * 这样以来，我们只需要根据每个位置是哪一种括号以及该位置的下标编号，就能确定将对应的对应的括号分到哪个组了。
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		public int[] maxDepthAfterSplit(String seq) {
			char[] cs = seq.toCharArray();
			int[] ret = new int[seq.length()];
			for (int i = 0; i < ret.length; i++) {
				if (cs[i] == '(') {
					ret[i] = i % 2;
				} else {
					ret[i] = 1 - i % 2;
				}
			}
			return ret;
		}
	}
}

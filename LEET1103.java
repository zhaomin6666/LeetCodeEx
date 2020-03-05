package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

/**
 * 1103. 分糖果 II
 * <p>
 * 排排坐，分糖果。
 * <p>
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 * 
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 * 
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推， <br>
 * 直到给最后一个小朋友 2 * n 颗糖果。
 * 
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。
 * 
 * 注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 * 
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小
 * 朋友分到的糖果数）。
 * 
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：candies = 7, num_people = 4 <br>
 * 输出：[1,2,3,1] <br>
 * 解释： 第一次，ans[0] += 1，数组变为 [1,0,0,0]。 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为
 * [1,2,3,1]。
 * <p>
 * 示例 2：
 * <p>
 * 输入：candies = 10, num_people = 3 <br>
 * 输出：[5,2,3] <br>
 * 解释： 第一次，ans[0] += 1，数组变为 [1,0,0]。 第二次，ans[1] += 2，数组变为 [1,2,0]。 第三次，ans[2] +=
 * 3，数组变为 [1,2,3]。 第四次，ans[0] += 4，最终数组变为 [5,2,3]。  
 * 
 * 提示： <br>
 * 
 * 1 <= candies <= 10^9 <br>
 * 1 <= num_people <= 1000 <br>
 * 
 * 
 * @author zm
 */
public class LEET1103 {
	public static void main(String[] args) {
		LEET1103 l1103 = new LEET1103();
		// System.out.println(JSON.toJSONString(l1103.new
		// Solution2().distributeCandies(7, 4)));
		System.out.println(JSON.toJSONString(l1103.new Solution2().distributeCandies(1000000000, 1000)));
		// System.out.println(JSON.toJSONString(l1103.new
		// Solution2().distributeCandies(10, 3)));
		// System.out.println(JSON.toJSONString(l1103.new
		// Solution2().distributeCandies(100, 2)));
	}

	/**
	 * 循环不断减少糖的数量知道没有糖
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int[] distributeCandies(int candies, int num_people) {
			int[] result = new int[num_people];
			int n = 0;
			int i = 0;
			int distributeNums = 1;
			candies -= distributeNums;
			while (candies >= 0) {
				result[i++] += distributeNums;
				n += i / num_people;
				i %= num_people;
				distributeNums = n * num_people + i + 1;
				candies -= distributeNums;
			}
			if (candies != -distributeNums) {
				result[i] += candies + distributeNums;
			}
			return result;
		}
	}

	/**
	 * 数学方法，先计算一共完整的循环有多少次，然后再把剩余的加上
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		// 1,2,3 6 6+(n-1)*3*3
		// 4,5,6 6+3*3

		// 1,2 3 3+(n-1)*2*2
		// 3,4 3+2*2

		// (u + u+(n-1)*p*p)*n= 2c
		// (2u + n*p*p -p*p) *n = 2c
		// p*p*n*n + (2u-p*p)n -2c = 0
		// p=3,u=6,c=21 9n*n+3n-42=0
		// p=1000,u=500500,c=1000000000 1000000n*n+1000n-1000000000=0
		public int[] distributeCandies(int candies, int num_people) {
			int[] result = new int[num_people];
			int oneLoopUsage = (int) ((num_people + 1.0) / 2 * num_people);
			double m = Math.pow(2 * oneLoopUsage - num_people * num_people, 2)
					+ 2 * 4 * num_people * 1.0 * num_people * candies;
			double loops = (-(2 * oneLoopUsage - num_people * num_people) + Math.sqrt(m))
					/ (2 * num_people * num_people);
			int loopsInt = (int) loops;
			for (int i = 0; i < result.length; i++) {
				result[i] = loopsInt * i + (2 + (loopsInt - 1) * num_people) * loopsInt / 2;
			}
			if (Double.valueOf(loops).equals(Double.valueOf((int) loops))) {
				return result;
			}
			int reserve = candies - (result[0] + result[num_people - 1]) * num_people / 2;
			int first = loopsInt * num_people + 1;
			for (int i = 0; i < result.length; i++) {
				if (reserve >= first) {
					result[i] += first;
					reserve -= first++;
				} else {
					result[i] += reserve;
					break;
				}
			}
			return result;
		}
	}

	/**
	 * 官方题解，进一步优化上面的方法，化简了求循环次数的方式
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		public int[] distributeCandies(int candies, int num_people) {
			int n = num_people;
			// how many people received complete gifts
			int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
			int remaining = (int) (candies - (p + 1) * p * 0.5);
			int rows = p / n, cols = p % n;

			int[] d = new int[n];
			for (int i = 0; i < n; ++i) {
				// complete rows
				d[i] = (i + 1) * rows + (int) (rows * (rows - 1) * 0.5) * n;
				// cols in the last row
				if (i < cols)
					d[i] += i + 1 + rows * n;
			}
			// remaining candies
			d[cols] += remaining;
			return d;
		}
	}

}

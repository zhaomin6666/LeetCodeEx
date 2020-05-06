package com.zm.LeetCodeEx.algorithms.ex901_1000;

import java.util.HashSet;
import java.util.Set;

/**
 * 983. 最低票价
 * <p>
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
 * <p>
 * 火车票有三种不同的销售方式：
 * <p>
 * 一张为期一天的通行证售价为 costs[0] 美元； <br>
 * 一张为期七天的通行证售价为 costs[1] 美元； <br>
 * 一张为期三十天的通行证售价为 costs[2] 美元。 <br>
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第
 * 5 天、第 6 天、第 7 天和第 8 天。
 * <p>
 * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
 * <p>
 * 示例 1：
 * <p>
 * 输入：days = [1,4,6,7,8,20], costs = [2,7,15] <br>
 * 输出：11 <br>
 * 解释： <br>
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划： <br>
 * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。 <br>
 * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。 <br>
 * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。 <br>
 * 你总共花了 $11，并完成了你计划的每一天旅行。
 * <p>
 * 示例 2：
 * <p>
 * 输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15] <br>
 * 输出：17 <br>
 * 解释： <br>
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划： <br>
 * 在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。 <br>
 * 在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。 <br>
 * 你总共花了 $17，并完成了你计划的每一天旅行。 <br>
 * <p>
 * 提示：
 * <p>
 * 1 <= days.length <= 365 <br>
 * 1 <= days[i] <= 365 <br>
 * days 按顺序严格递增 <br>
 * costs.length == 3 <br>
 * 1 <= costs[i] <= 1000 <br>
 * 
 * 
 * @author zm
 */
public class LEET938 {
	public static void main(String[] args) {
		LEET938 l912 = new LEET938();
		System.out
				.println(l912.new Solution2().mincostTickets(new int[] { 1, 4, 6, 7, 8, 20 }, new int[] { 2, 7, 15 }));
		System.out.println(l912.new Solution2().mincostTickets(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 },
				new int[] { 2, 7, 15 }));
	}

	/**
	 * 回溯超时
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		private int ret = Integer.MAX_VALUE;
		int[] days;
		int[] costs;

		public int mincostTickets(int[] days, int[] costs) {
			this.days = days;
			this.costs = costs;
			ret = Math.min(ret, back(0, days[0], costs[0]));
			ret = Math.min(ret, back(0, days[0] + 6, costs[1]));
			ret = Math.min(ret, back(0, days[0] + 29, costs[2]));
			return ret;
		}

		private int back(int curDayIndex, int ticketToDay, int curCost) {
			if (ticketToDay >= days[days.length - 1]) {
				return curCost;
			}
			int curRet = Integer.MAX_VALUE;
			for (int i = curDayIndex + 1; i < days.length; i++) {
				if (days[i] <= ticketToDay) {
					continue;
				}
				curRet = Math.min(curRet, back(i, days[i], curCost + costs[0]));
				curRet = Math.min(curRet, back(i, days[i] + 6, curCost + costs[1]));
				curRet = Math.min(curRet, back(i, days[i] + 29, curCost + costs[2]));
				break;
			}
			return curRet;
		}
	}

	/**
	 * 从后向前dp 如果当前日期不需要出行 dp[i] = dp[i+1];
	 * 如果当前日期需要出行，那么需要看是第i天需要买一张1天票或第i+6天买一张7天票或第i+29天买一张30天票
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int mincostTickets(int[] days, int[] costs) {
			int[] dp = new int[396];
			Set<Integer> dayset = new HashSet<>();
			for (int day : days) {
				dayset.add(day);
			}
			for (int i = 365; i > 0; i--) {
				if (dayset.contains(i)) {
					dp[i] = Math.min(Math.min(dp[i + 1] + costs[0], dp[i + 7] + costs[1]), dp[i + 30] + costs[2]);
				} else {
					dp[i] = dp[i + 1];
				}
			}
			return dp[1];
		}
	}

	/**
	 * 官方题解：方法二换成递归
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		private int[] costs;
		private Integer[] memo;
		private Set<Integer> dayset;

		public int mincostTickets(int[] days, int[] costs) {
			this.costs = costs;
			memo = new Integer[366];
			dayset = new HashSet<>();
			for (int d : days) {
				dayset.add(d);
			}
			return dp(1);
		}

		public int dp(int i) {
			if (i > 365) {
				return 0;
			}
			if (memo[i] != null) {
				return memo[i];
			}
			if (dayset.contains(i)) {
				memo[i] = Math.min(Math.min(dp(i + 1) + costs[0], dp(i + 7) + costs[1]), dp(i + 30) + costs[2]);
			} else {
				memo[i] = dp(i + 1);
			}
			return memo[i];
		}
	}
	
	class Solution4 {
	    private int[] days, costs;
	    private Integer[] memo;
	    private int[] durations = new int[]{1, 7, 30};

	    public int mincostTickets(int[] days, int[] costs) {
	        this.days = days;
	        this.costs = costs;
	        memo = new Integer[days.length];
	        return dp(0);
	    }

	    public int dp(int i) {
	        if (i >= days.length) {
	            return 0;
	        }
	        if (memo[i] != null) {
	            return memo[i];
	        }
	        memo[i] = Integer.MAX_VALUE;
	        int j = i;
	        for (int k = 0; k < 3; ++k) {
	        	// 只有当两天的差距>1,7,30时，需要重新购票
	            while (j < days.length && days[j] < days[i] + durations[k]) {
	                j++;
	            }
	            memo[i] = Math.min(memo[i], dp(j) + costs[k]);
	        }
	        return memo[i];
	    }
	}
}

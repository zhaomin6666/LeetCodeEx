package com.zm.LeetCodeEx.weekcontest.contest_187_20200503;

import java.util.HashMap;
import java.util.List;

import com.zm.LeetCodeEx.CommonFunctions;

/**
 * 周赛 2020年5月3日
 * <p>
 * 5400. 旅行终点站
 * <p>
 * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从
 * cityAi 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
 * <p>
 * 题目数据保证线路图会形成一条不存在循环的线路，因此只会有一个旅行终点站。
 * <p>
 * 示例 1：
 * <p>
 * 输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * <br>
 * 输出："Sao Paulo" <br>
 * 解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" ->
 * "Lima" -> "Sao Paulo" 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：paths = [["B","C"],["D","B"],["C","A"]] <br>
 * 输出："A" <br>
 * 解释：所有可能的线路是： <br>
 * "D" -> "B" -> "C" -> "A". <br>
 * "B" -> "C" -> "A". <br>
 * "C" -> "A". <br>
 * "A". <br>
 * 显然，旅行终点站是 "A" 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：paths = [["A","Z"]] <br>
 * 输出："Z"
 * <p>
 * 
 * 提示：
 * <p>
 * 1 <= paths.length <= 100 <br>
 * paths[i].length == 2 <br>
 * 1 <= cityAi.length, cityBi.length <= 10 <br>
 * cityAi != cityBi <br>
 * 所有字符串均由大小写英文字母和空格字符组成。
 *
 * @author zm
 */
public class LEET5400 {
	public static void main(String[] args) {
		LEET5400 l5400 = new LEET5400();
		System.out.println(l5400.new Solution()
				.destCity(CommonFunctions.stringArrayToList(new String[][] { { "A", "B" }, { "B", "Z" } })));
	}

	class Solution {
		public String destCity(List<List<String>> paths) {
			HashMap<String, String> map = new HashMap<>();
			for (List<String> path : paths) {
				map.put(path.get(0), path.get(1));
			}

			String dest = paths.get(0).get(0);
			while (map.containsKey(dest)) {
				dest = map.get(dest);
			}
			return dest;
		}
	}
}

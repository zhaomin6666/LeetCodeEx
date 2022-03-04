package com.zm.LeetCodeEx.weekcontest.contest_203_20200823;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 1562. 查找大小为 M 的最新分组
 * 给你一个数组 arr ，该数组表示一个从 1 到 n 的数字排列。有一个长度为 n 的二进制字符串，该字符串上的所有位最初都设置为 0 。
 * <p>
 * 在从 1 到 n 的每个步骤 i 中（假设二进制字符串和 arr 都是从 1 开始索引的情况下），二进制字符串上位于位置 arr[i] 的位将会设为 1 。
 * <p>
 * 给你一个整数 m ，请你找出二进制字符串上存在长度为 m 的一组 1 的最后步骤。一组 1 是一个连续的、由 1 组成的子串，且左右两边不再有可以延伸的 1 。
 * <p>
 * 返回存在长度 恰好 为 m 的 一组 1  的最后步骤。如果不存在这样的步骤，请返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,5,1,2,4], m = 1
 * 输出：4
 * 解释：
 * 步骤 1："00100"，由 1 构成的组：["1"]
 * 步骤 2："00101"，由 1 构成的组：["1", "1"]
 * 步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
 * 步骤 4："11101"，由 1 构成的组：["111", "1"]
 * 步骤 5："11111"，由 1 构成的组：["11111"]
 * 存在长度为 1 的一组 1 的最后步骤是步骤 4 。
 * 示例 2：
 * <p>
 * 输入：arr = [3,1,5,4,2], m = 2
 * 输出：-1
 * 解释：
 * 步骤 1："00100"，由 1 构成的组：["1"]
 * 步骤 2："10100"，由 1 构成的组：["1", "1"]
 * 步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
 * 步骤 4："10111"，由 1 构成的组：["1", "111"]
 * 步骤 5："11111"，由 1 构成的组：["11111"]
 * 不管是哪一步骤都无法形成长度为 2 的一组 1 。
 * 示例 3：
 * <p>
 * 输入：arr = [1], m = 1
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：arr = [2,1], m = 2
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == arr.length
 * 1 <= n <= 10^5
 * 1 <= arr[i] <= n
 * arr 中的所有整数 互不相同
 * 1 <= m <= arr.length
 *
 * @author zm
 * @version 1.0
 * @date 2022-3-4
 * @since 1.8
 */
public class LEET1562 {
	public static void main(String[] args) {
		System.out.println(new Solution().findLatestStep(new int[]{3, 5, 1, 2, 4}, 1));
		System.out.println(new Solution2().findLatestStep(new int[]{1, 3, 5, 6, 2, 4}, 3));
	}

	/**
	 * 使用带weight的uf，这样可以判断weight中是否存在所需的长度
	 */
	static class Solution {
		public int findLatestStep(int[] arr, int m) {
			int len = arr.length;
			UnionFind uf = new UnionFind(len + 1);
			int ret = -1;
			for (int i = 0; i < len; i++) {
				uf.union(arr[i] - 1, arr[i]);
				if (uf.hasWeight(m + 1)) {
					ret = i + 1;
				}
			}
			return ret;
		}

		static class UnionFind {
			int[] ufArr;
			int[] weight;
			HashSet<Integer> set = new HashSet<>();
			HashMap<Integer, Integer> weightCnt = new HashMap<>();

			public UnionFind(int size) {
				ufArr = new int[size];
				weight = new int[size];
				for (int i = 0; i < size; i++) {
					ufArr[i] = i;
					set.add(i);
				}
				Arrays.fill(weight, 1);
				weightCnt.put(1, size);
			}

			public int getRoot(int index) {
				while (ufArr[index] != index) {
					index = ufArr[index];
				}
				return index;
			}

			public void union(int c1, int c2) {
				int index1 = getRoot(c1);
				int index2 = getRoot(c2);
				if (weight[index1] >= weight[index2]) {
					ufArr[index2] = index1;
					set.remove(index2);
					weightCnt.put(weight[index1], weightCnt.get(weight[index1]) - weight[index1]);
					weightCnt.put(weight[index2], weightCnt.get(weight[index2]) - weight[index2]);
					weight[index1] += weight[index2];
					weightCnt.put(weight[index1], weightCnt.getOrDefault(weight[index1], 0) + weight[index1]);
				}
				else {
					ufArr[index1] = index2;
					set.remove(index1);
					weightCnt.put(weight[index1], weightCnt.get(weight[index1]) - weight[index1]);
					weightCnt.put(weight[index2], weightCnt.get(weight[index2]) - weight[index2]);
					weight[index2] += weight[index1];
					weightCnt.put(weight[index2], weightCnt.getOrDefault(weight[index2], 0) + weight[index2]);
				}
			}

			public boolean hasWeight(int target) {
				return weightCnt.containsKey(target) && weightCnt.get(target) > 0;
			}
		}
	}

	/**
	 * 数组当前的取值为 [1, 0, 1, 0, 1, 1]，则数组 endpoints 的取值为
	 * [(1,1),(-1,-1),(3,3),(−1,−1),(5,6),(5,6)]
	 * 更新index=2为1，[1, 1, 1, 0, 1, 1]
	 * [(1,3),(-1,-1),(1,3),(−1,−1),(5,6),(5,6)]
	 * 更新index=4为1，[1, 1, 1, 1, 1, 1]
	 * [(1,6),(-1,-1),(1,3),(−1,−1),(5,6),(1,6)]
	 */
	static class Solution2 {
		public int findLatestStep(int[] arr, int m) {
			int n = arr.length;
			int[][] endpoints = new int[n + 1][2];
			for (int i = 0; i <= n; i++) {
				Arrays.fill(endpoints[i], -1);
			}
			int cnt = 0;
			int ret = -1;

			for (int i = 0; i < n; i++) {
				int left = arr[i], right = arr[i];
				if (arr[i] > 1 && endpoints[arr[i] - 1][0] != -1) {
					left = endpoints[arr[i] - 1][0];
					int leftLength = endpoints[arr[i] - 1][1] - endpoints[arr[i] - 1][0] + 1;
					if (leftLength == m) {
						cnt--;
					}
				}
				if (arr[i] < n && endpoints[arr[i] + 1][1] != -1) {
					right = endpoints[arr[i] + 1][1];
					int rightLength = endpoints[arr[i] + 1][1] - endpoints[arr[i] + 1][0] + 1;
					if (rightLength == m) {
						cnt--;
					}
				}
				int length = right - left + 1;
				if (length == m) {
					cnt++;
				}
				if (cnt > 0) {
					ret = i + 1;
				}
				endpoints[left][0] = endpoints[right][0] = left;
				endpoints[left][1] = endpoints[right][1] = right;
			}
			return ret;
		}
	}

	/**
	 * 优化解法。不再用二维数组标识段头段尾，只有一个数字标识。如果当前是一个段的段尾，那么就保存段首位置，因为如果要合并，也是后面一格置成1，
	 * 这样后面一格就可以直接获取到这个段起始位置。
	 * 数组当前的取值为 [0, 1, 0, 1, 0, 1, 1]，则数组 endpoints 的取值为
	 * [0,0,2,0,4,0,7,6,0]
	 * 更新index=3为1，[0, 1, 1, 1, 0, 1, 1]
	 * [0,0,4,0,2,0,7,6,0]
	 * 更新index=4为1，[0, 1, 1, 1, 1, 1, 1]
	 * [0,0,7,0,2,0,7,2,0]
	 */
	static class Solution3 {
		public int findLatestStep(int[] arr, int m) {

			// 段：1 1 1 1 1 1
			// 核心是1个指针定义段：如果是段头就指向段尾，如果是段尾就指向段头，段长度 = abs(link[i] - i) + 1

			// link数组：头尾各有一个空点，所以是大小是 n + 2：_ 1 2 3 4 5 _
			int[] link = new int[arr.length + 2];

			// 段长为 m 的个数
			int cnt = 0;
			int res = -1;

			for (int i = 0; i < arr.length; i++) {

				int x = arr[i];
				// 左段 link
				int l = link[x - 1] != 0 ? link[x - 1] : x;
				// 右段 link
				int r = link[x + 1] != 0 ? link[x + 1] : x;

				// 修改 cnt
				if (x - l == m) {
					cnt--;
				}
				if (r - x == m) {
					cnt--;
				}
				if (r - l + 1 == m) {
					cnt++;
				}
				if (cnt > 0) {
					res = i + 1;
				}

				// 合并段
				link[l] = r;
				link[r] = l;
			}
			return res;
		}
	}
}

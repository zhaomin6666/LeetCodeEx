package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class LEET015 {
	public static void main(String[] args) {
		int[] intArray = {-1, 2, -2, 3, -3, 1};
		// int[] intArray = {-1, 2};
		List<List<Integer>> ans = threeSum(intArray);
		System.out.println(JSONObject.toJSONString(ans));
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		if (nums.length < 3) {
			return ret;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
				int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
				while (l < r) {
					if (nums[l] + nums[r] == sum) {
						ret.add(Arrays.asList(nums[i], nums[l], nums[r]));
						while (l < r && nums[l] == nums[l + 1])
							l++;
						while (l < r && nums[r] == nums[r - 1])
							r--;
						l++;
						r--;
					} else if (nums[l] + nums[r] > sum) {
						while (l < r && nums[r] == nums[r - 1])
							r--;
						r--;
					} else if (nums[l] + nums[r] < sum) {
						while (l < r && nums[l] == nums[l + 1])
							l++;
						l++;
					}
				}
			}
		}
		return ret;
	}

	public static List<List<Integer>> threeSum2(int[] nums) {

		Arrays.sort(nums);
		List<List<Integer>> lists = new ArrayList<>();
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			int target = -nums[i];
			if (target < 0) {
				break;
			}
			int j = i + 1, k = len - 1;
			while (j < k) {
				if (nums[j] + nums[k] == target) {
					lists.add(Arrays.asList(nums[i], nums[j], nums[k]));
					int v1 = nums[j], v2 = nums[k];
					// 下面两个判断做了优化处理
					while (j < k && nums[j] == v1) {
						j++;
					}
					while (j < k && nums[k] == v2) {
						k--;
					}
				} else if (nums[j] + nums[k] < target) {
					j++;
				} else {
					k--;
				}
			}
			while (i + 1 < len && nums[i] == nums[i + 1]) {
				i++;
			}
		}
		return lists;
	}
}

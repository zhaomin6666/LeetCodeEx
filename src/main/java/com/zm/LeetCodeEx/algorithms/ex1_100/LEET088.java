package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 
 * 说明:
 * 
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存
 * nums2 中的元素。 示例:
 * 
 * 输入: nums1 = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6], n = 3
 * 
 * 输出: [1,2,2,3,5,6]
 * 
 * @author zm
 *
 */
public class LEET088 {
	public static void main(String[] args) {
		LEET088 l088 = new LEET088();
		int[] nums1 = { 1, 2, 3, 0, 0, 0 };
		int[] nums2 = { 2, 5, 6 };
		l088.merge(nums1, nums1.length - nums2.length, nums2, nums2.length);
		System.out.println(JSON.toJSONString(nums1));
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int a = 0, b = 0;
		for (int i = 0; i < n + m; i++) {
			if (a >= m + b) {
				nums1[m + b] = nums2[b];
				a++;
				b++;
			} else if (b >= n) {
			} else {
				if (nums1[a] <= nums2[b]) {
					a++;
				} else {
					for (int j = nums1.length - 1; j > a; j--) {
						nums1[j] = nums1[j - 1];
					}
					nums1[a] = nums2[b];
					a++;
					b++;
				}
			}
		}
	}

	public void merge2(int[] nums1, int m, int[] nums2, int n) {
		int pos = m+n-1;
        int curM = m-1;
        int curN = n-1;
        while(curN >= 0 && curM >= 0) {
            if(nums1[curM] < nums2[curN])
                nums1[pos--] = nums2[curN--];
            else {
                nums1[pos--] = nums1[curM--];
            }
        }
        
        if(curN >= 0) {
            for(int i = 0; i <= curN; i++)
                nums1[i] = nums2[i];
        }
	}
}

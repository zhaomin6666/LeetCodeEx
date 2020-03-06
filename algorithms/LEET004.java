package com.zm.LeetCodeEx.algorithms;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m +
 * n))。 你可以假设 nums1 和 nums2 不会同时为空。
 * 
 * @author zm
 *
 */
public class LEET004 {
	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 8, 9 };
		int[] nums2 = { 3, 4 };
		LEET004 l004 = new LEET004();
		/*
		 * long starttime = System.currentTimeMillis(); for (long i = 0; i < 100000000;
		 * i++) { //System.out.println(l004.findMedianSortedArrays(nums1, nums2));
		 * l004.findMedianSortedArrays(nums1, nums2); } long midtime =
		 * System.currentTimeMillis(); for (long i = 0; i < 100000000; i++) {
		 * //System.out.println(l004.findMedianSortedArrays2(nums1, nums2));
		 * l004.findMedianSortedArrays2(nums1, nums2); } long endtime =
		 * System.currentTimeMillis(); System.out.println("1:"+String.valueOf(midtime -
		 * starttime)); System.out.println(endtime - midtime);
		 */
		System.out.println(l004.findMedianSortedArrays(nums1, nums2)); // 不满足时间要求
		System.out.println(l004.findMedianSortedArrays2(nums1, nums2)); // 快很多
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int[] nums = new int[nums2.length + nums1.length];
		for (int i = 0, j = 0, k = 0; i < nums2.length + nums1.length; i++) {
			if (j < nums1.length && k < nums2.length) {
				if (nums1[j] > nums2[k]) {
					nums[i] = nums2[k];
					k++;
				} else {
					nums[i] = nums1[j];
					j++;
				}
			} else {
				if (j >= nums1.length) {
					nums[i] = nums2[k];
					k++;
				} else {
					nums[i] = nums1[j];
					j++;
				}
			}
		}
		return getMid(nums);
	}

	public double getMid(int[] nums) {
		int len = nums.length;
		double nums1mid;
		if (len % 2 == 0) {
			nums1mid = ((double) nums[len / 2 - 1] + nums[len / 2]) / 2;
		} else {
			nums1mid = nums[(int) Math.floor(len / 2)];
		}
		return nums1mid;
	}

	/**
	 * 官方解答 理解： 假设有A=[1,9,10,11],B=[2,3,4,8,12] m=4, n=5 确保m>n, m=5,n=4 把A分成两部分A1&A2
	 * A1<A2 把B分成两部分B1&B2 B1<B2 把A1和B1放在一起 A2和B2放在一起 因为A1<A2,B1<B2,
	 * 如果能够满足A1<B2,B1<A2,那么 {A1,B1}就小于 {A2,B2} 同时如果两边元素个数相同，或者相差1就找到了
	 * 
	 * iMin=0 iMax=5 halflen=5 i=2 j=3 把A1: 1,9 &B1: 2,3,4放在了一起 A2: 10,11 & B2: 8,12
	 * 放在一起 发现A1并不是小于B2(9>8) i-- i=1,j=4 A1:1 B1:2,3,4,8 A2:9,10,11 B2:12 5====4
	 * 所以是左边的最后一个 就是8
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public double findMedianSortedArrays2(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		if (m > n) { // to ensure m<=n
			int[] temp = A;
			A = B;
			B = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
		while (iMin <= iMax) {
			int i = (iMin + iMax) / 2;
			int j = halfLen - i;
			if (i < iMax && B[j - 1] > A[i]) {
				iMin = i + 1; // i is too small
			} else if (i > iMin && A[i - 1] > B[j]) {
				iMax = i - 1; // i is too big
			} else { // i is perfect
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = B[j - 1];
				} else if (j == 0) {
					maxLeft = A[i - 1];
				} else {
					maxLeft = Math.max(A[i - 1], B[j - 1]);
				}
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}

				int minRight = 0;
				if (i == m) {
					minRight = B[j];
				} else if (j == n) {
					minRight = A[i];
				} else {
					minRight = Math.min(B[j], A[i]);
				}

				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}
}

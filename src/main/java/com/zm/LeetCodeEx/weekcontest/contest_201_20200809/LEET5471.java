package com.zm.LeetCodeEx.weekcontest.contest_201_20200809;

public class LEET5471 {

    public static void main(String[] args) {
        LEET5471 leet5453 = new LEET5471();
        System.out.println(leet5453.new Solution().maxNonOverlapping(new int[]{5,5,5,20,10,5,29,1,30,5,5},30));

    }

    class Solution {
        public int maxNonOverlapping(int[] nums, int target) {
            int ADD = 10000;
            int ret = 0;
            int left = 0;
            int right = 0;
            int curSum = 0;
            while(right < nums.length){
                if (nums[right] == target){
                    left=++right;
                    ret++;
                    curSum=0;
                    continue;
                }
                curSum+= nums[right]+ADD;
                if(curSum == target+(right-left+1)*ADD){
                    ret++;
                    left = ++right;
                    curSum = 0;
                }else if(curSum < target+(right-left+1)*ADD){
                    right++;
                }else {
                    while(curSum > target+(right-left+1)*ADD){
                        curSum -= nums[left]+ADD;
                        left++;
                        if(curSum == target+(right-left+1)*ADD){
                            ret++;
                            left = right+1;
                            curSum = 0;
                            break;
                        }
                    }
                    right++;
                }
            }
            return ret;
        }
    }
}

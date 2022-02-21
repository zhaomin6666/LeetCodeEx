package com.zm.LeetCodeEx.weekcontest.contest_d33_20200822;

public class LEET5470 {

    public static void main(String[] args) {
        LEET5470 leet5453 = new LEET5470();
        System.out.println(leet5453.new Solution().minOperations(new int[]{1,5}));

    }

    class Solution {
        public int minOperations(int[] nums) {
            int ret = 0;
            int suc = 0;
            int len = nums.length;
            boolean[] suca = new boolean[len];
            while(suc < len){
                boolean needD = false;
                for(int i = 0; i < len; i++){
                    if(suca[i]){
                        continue;
                    }
                    if(nums[i] == 0){
                        suca[i] = true;
                        suc++;
                    }
                    else {
                        if(nums[i] % 2 == 1){
                            ret++;
                            nums[i]--;
                        }
                        if(nums[i] != 0){
                            nums[i] /=2;
                            needD = true;
                        }
                    }

                }
                if(needD){
                    ret++;
                }
            }
            return ret;
        }
    }
}

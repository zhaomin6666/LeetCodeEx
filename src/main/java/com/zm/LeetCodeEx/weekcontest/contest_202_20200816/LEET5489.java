package com.zm.LeetCodeEx.weekcontest.contest_202_20200816;

import java.util.Arrays;

public class LEET5489 {

    public static void main(String[] args) {
        LEET5489 leet5453 = new LEET5489();
        System.out.println(leet5453.new Solution().maxDistance(new int[]{1,2,3,4,7},3));

    }

    class Solution {
        int max = 0;
        int[] pos;
        public int maxDistance(int[] position, int m) {
            Arrays.sort(position);
            this.pos = position;
            helper(0,m,Integer.MAX_VALUE, 0);
            return max;
        }

        private void helper(int curIndex, int leftBalls, int thisMin, int lastPos){
            if(leftBalls == 0){
                max = Math.max(max, thisMin);
                return;
            }
            for(int i = curIndex; i<pos.length; i++){
                int curMin = thisMin;
                if(curIndex != 0){
                    curMin = Math.min(thisMin, pos[i]-lastPos);
                }
                helper(i+1, leftBalls-1, curMin, pos[i]);
            }
        }
    }
}

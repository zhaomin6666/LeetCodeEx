package com.zm.LeetCodeEx.weekcontest.contest_d32_20200808;

public class LEET5470 {

    public static void main(String[] args) {
        LEET5470 leet5453 = new LEET5470();
        System.out.println(leet5453.new Solution().minInsertions("()())))()"));

    }

    class Solution {
        public int minInsertions(String s) {
            int cnt = 0;
            char[] cs = s.toCharArray();
            int leftCnt = 0;
            int rightCnt = 0;
            for(int i = 0; i < cs.length; i++){
                if(cs[i] == '('){
                    if(rightCnt == 1){
                        if(leftCnt >= 1){
                            cnt++;
                            leftCnt--;
                        }else {
                            cnt+=2;
                        }
                        rightCnt = 0;
                    }
                    leftCnt++;
                }else {
                    if(leftCnt==0){
                        cnt++;
                        if(i+1 == cs.length || cs[i+1] != ')'){
                            cnt++;
                        }else {
                            i++;
                        }
                    }else {
                        if(rightCnt == 1){
                            rightCnt = 0;
                            leftCnt--;
                        }else {
                            rightCnt++;
                        }
                    }
                }
            }
            cnt+=leftCnt*2-rightCnt;

            return cnt;
        }
    }
}

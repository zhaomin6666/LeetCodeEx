package com.zm.LeetCodeEx.weekcontest.contest_d34_20200905;

public class LEET5471 {

    public static void main(String[] args) {
        LEET5471 leet5453 = new LEET5471();

    }

    class Solution {
        public int numWays(String s) {
            int cnt1 = 0;
            char[] cs = s.toCharArray();
            for(int i = 0; i <cs.length; i++){
                if(cs[i] == '1'){
                    cnt1++;
                }
            }
            if(cnt1 % 3 != 0){
                return 0;
            }
            if(cnt1 == 0){
                return (cs.length-1)*(cs.length-2)/2;
            }
            int sub1 = cnt1/3;
            int cnt01 = 0;
            int cnt02 = 0;
            boolean needCnt0 = false;
            int cnt0num = 0;
            for(int i = 0; i <cs.length; i++){
                if(needCnt0){
                    if(cs[i] == '0'){
                        if(cnt0num == 1){
                            cnt01++;
                        }else {
                            cnt02++;
                        }
                    }else {
                        needCnt0 = false;
                        i--;
                    }
                }else {
                    if(cs[i] == '1'){
                        sub1--;
                        if(sub1 == 0){
                            cnt0num++;
                            if(cnt0num == 3){
                                break;
                            }
                            needCnt0 = true;
                            sub1 = cnt1/3;
                        }
                    }
                }

            }
            System.out.println(cnt01);
            System.out.println(cnt02);
            return (cnt01+1)*(cnt02+1)*1;
        }
    }
}

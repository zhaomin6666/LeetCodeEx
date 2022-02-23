package com.zm.LeetCodeEx.competition.y2020fall;

import java.util.HashSet;

public class LCP06 {

    public static void main(String[] args) {
        LCP06 LCP06 = new LCP06();
        System.out.println(LCP06.new Solution().paintingPlan(3,6));
    }

    class Solution {
        public int paintingPlan(int n, int k) {
            if(k == n*n){ return 1;}
            if(k > n*n){ return 0;}
            int ret = 0;
            HashSet<Integer> used = new HashSet<>();
            for(int i = 0; i <= n && k >= 0; i++){
                int j = 0;
                int left = k;
                while(left > 0){
                    left -= n-i;
                    j++;
                }
                if (left == 0){
                    if(i == j){
                        if (!used.contains(i)){
                            ret += combination(i,n)*combination(j,n);
                            used.add(i);
                        }
                    }else {
                        ret += combination(i,n)*combination(j,n);
                    }

                }
                k -= n;
            }
            return ret;
        }

        private int factorial(int n) {
            int sum = 1;
            while (n > 0) {
                sum = sum * n--;
            }
            return sum;
        }

        private int combination(int m, int n) {
            return m < n ? factorial(n) / (factorial(n - m) * factorial(m)) : 1;
        }
    }
}

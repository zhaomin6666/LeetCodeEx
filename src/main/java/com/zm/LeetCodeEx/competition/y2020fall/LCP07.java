package com.zm.LeetCodeEx.competition.y2020fall;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

public class LCP07 {

    public static void main(String[] args) {
        LCP07 LCP07 = new LCP07();
        System.out.println(LCP07.new Solution().isMagic(new int[]{2, 4, 3, 1, 5}));
        System.out.println(LCP07.new Solution().isMagic(new int[]{2, 1, 4, 3, 5}));
        System.out.println(LCP07.new Solution().isMagic(new int[]{2, 4, 1, 5, 3}));
    }

    class Solution {
        public boolean isMagic(int[] target) {
            int n = target.length;
            ArrayList<Integer> list = new ArrayList<>();
            int k = 0;
            boolean isGetK = false;
            int i = 2;
            for (; i % 2 == 0 || i <= n; i += 2) {
                if (!isGetK) {
                    if (i > n){
                        i = 1;
                    }
                    if (target[k] != i) {
                        isGetK = true;
                        list.add(i);
                    } else {
                        k++;
                    }

                } else {
                    if (i > n){
                        i = 1;
                        break;
                    }
                    list.add(i);
                }
            }
            for (;i <= n; i += 2) {
                list.add(i);
            }
            if (k == 0) {
                return false;
            }
            System.out.println(k);
            System.out.println(JSON.toJSONString(list));
            int curTargetI = k;

            while (list.size() > 0) {
                ArrayList<Integer> newList = new ArrayList<>();
                int listI = 1;
                for (int j = 0; j < k; j++, listI += 2) {
                    if (curTargetI >= n){
                        return true;
                    }
                    if (listI >= list.size()) {
                        listI = 0;
                    }
                    if (target[curTargetI] != list.get(listI)) {
                        return false;
                    }
                    curTargetI++;
                }
                for (; listI < list.size(); listI += 2) {
                    newList.add(list.get(listI));
                }
                if (listI % 2 == 1) {
                    listI = 0;
                    for (; listI < list.size(); listI += 2) {
                        newList.add(list.get(listI));
                    }
                }
                list = newList;
            }
            int lastListI = 0;
            for (; curTargetI < n; curTargetI++) {
                if (target[curTargetI] != list.get(lastListI)) {
                    return false;
                }
                curTargetI++;
                lastListI++;
            }
            return true;
        }
    }
}

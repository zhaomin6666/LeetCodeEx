package com.zm.LeetCodeEx.weekcontest.contest_268_20211121;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LEET5930 {
    public static void main(String[] args) {
        LEET5930 leet5930 = new LEET5930();
        System.out.println(leet5930.new Solution().maxDistance(new int[]{1,1,1,6,1,1,1}));
        System.out.println(leet5930.new Solution().maxDistance(new int[]{1,8,3,8,3}));
    }


    class Solution {
        public int maxDistance(int[] colors) {
            HashMap<Integer, Integer> lastestByColor = new HashMap<>();
            int max = 0;
            for(int i = 0; i < colors.length; i++){
                int curMax = 0;
                for(Map.Entry<Integer, Integer> entry : lastestByColor.entrySet()){
                    if(entry.getKey() != colors[i]){
                        curMax = Math.max(curMax, i - entry.getValue());
                    }
                }
                max = Math.max(max, curMax);
                if (!lastestByColor.containsKey(colors[i])) {
                    lastestByColor.put(colors[i], i);
                }
            }
            return max;
        }
    }
}

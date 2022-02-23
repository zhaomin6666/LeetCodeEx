package com.zm.LeetCodeEx.weekcontest.contest_201_20200809;

import java.util.Stack;

public class LEET5474 {

    public static void main(String[] args) {
        LEET5474 leet5453 = new LEET5474();
        System.out.println(leet5453.new Solution().makeGood("abBAcC"));

    }

    class Solution {
        public String makeGood(String s) {
            Stack<Character> stack = new Stack<>();
            int len = s.length();
            char[] cs = s.toCharArray();
            for(int i = 0; i < len; i++){
                if(stack.isEmpty()){
                    stack.push(cs[i]);
                }else {
                    if(Math.abs(stack.peek() - cs[i]) == 32){
                        stack.pop();
                    }else {
                        stack.push(cs[i]);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }
            sb.reverse();
            return sb.toString();
        }
    }
}

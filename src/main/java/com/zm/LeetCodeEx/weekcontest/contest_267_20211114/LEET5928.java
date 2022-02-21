package com.zm.LeetCodeEx.weekcontest.contest_267_20211114;

/**
 *
 */
public class LEET5928 {
    public static void main(String[] args) {
        LEET5928 leet5928 = new LEET5928();
        System.out.println(leet5928.new Solution()
                .decodeCiphertext("ch   ie   pr", 3));
        System.out.println(leet5928.new Solution()
                .decodeCiphertext("iveo    eed   l te   olc", 4));
        System.out.println(leet5928.new Solution()
                .decodeCiphertext("coding", 1));
        System.out.println(leet5928.new Solution()
                .decodeCiphertext(" b  ac", 2));
    }


    class Solution {
        public String decodeCiphertext(String encodedText, int rows) {
            int needColumns = encodedText.length() / rows;
            int[][] arr = new int[rows][needColumns];
            char[] cs = encodedText.toCharArray();
            int csi = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < needColumns; j++) {
                    arr[i][j] = cs[csi++];
                }
            }
            StringBuilder decodeString = new StringBuilder();
            for (int i = 0; i < needColumns; i++) {
                for (int j = 0; j < rows; j++) {
                    if (i+j < needColumns){
                        decodeString.append((char)arr[j][i+j]);
                    }
                }
            }

            while(decodeString.charAt(decodeString.length()-1) == ' '){
                decodeString.delete(decodeString.length()-1,decodeString.length());
            }
            return decodeString.toString();
        }
    }
}

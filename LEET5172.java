package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 周赛 2020年2月23日 5172. 形成三的最大倍数
 * <p>
 * 给你一个整数数组 digits，你可以通过按任意顺序连接其中某些数字来形成 3 的倍数，请你返回所能得到的最大的 3 的倍数。
 * <p>
 * 由于答案可能不在整数数据类型范围内，请以字符串形式返回答案。
 * <p>
 * 如果无法得到答案，请返回一个空字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = [8,1,9]
 * 输出："981"
 * 示例 2：
 * <p>
 * 输入：digits = [8,6,7,1,0]
 * 输出："8760"
 * 示例 3：
 * <p>
 * 输入：digits = [1]
 * 输出：""
 * 示例 4：
 * <p>
 * 输入：digits = [0,0,0,0,0,0]
 * 输出："0"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= digits.length <= 10^4
 * 0 <= digits[i] <= 9
 * 返回的结果不应包含不必要的前导零。
 *
 * @author zm
 */
public class LEET5172 {
    public static void main(String[] args) {
        LEET5172 l5172 = new LEET5172();
        System.out.println(JSON.toJSONString(l5172.largestMultipleOfThree(new int[]{8, 1, 9})));
        System.out.println(JSON.toJSONString(l5172.largestMultipleOfThree(new int[]{8, 6, 7, 1, 0})));
        System.out.println(JSON.toJSONString(l5172.largestMultipleOfThree(new int[]{1})));
        System.out.println(JSON.toJSONString(l5172.largestMultipleOfThree(new int[]{0, 0, 0, 0, 0, 0})));
    }

    public String largestMultipleOfThree(int[] digits) {
        try {
            for (int needNums = digits.length; needNums > 0; needNums--) {
                int[] retNums = getArrays(digits, needNums);
                if (retNums.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = retNums.length - 1; i >= 0; i--) {
                        sb.append(retNums[i]);
                    }
                    return sb.toString();
                }
            }
            return "";
        } finally {
            list = new ArrayList<>();
        }

    }

    private int[] getArrays(int[] digits, int needNums) {
        for (int i = 0; i <= digits.length - needNums; i++) {
            int[] temp = new int[needNums];
            temp[0] = digits[i];
            getArrays(Arrays.copyOfRange(digits, i + 1, digits.length), needNums - 1, temp, 1);
        }
        //System.out.println(JSON.toJSONString(list));
        if (!list.isEmpty()) {
            int[] curMax = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                int[] temp = list.get(i);
                for (int j = curMax.length - 1; j >= 0; j--) {
                    if (temp[j] > curMax[j]) {
                        curMax = temp;
                        break;
                    } else if (temp[j] < curMax[j]) {
                        break;
                    }
                }
            }
            return curMax;
        }
        return new int[]{};
    }

    private ArrayList<int[]> list = new ArrayList<>();

    private void getArrays(int[] digits, int needNums, int[] temp, int cur) {
        int[] temp2 = Arrays.copyOfRange(temp, 0, temp.length);
        if (needNums == 0) {
            int sum = 0;
            for (int i = 0; i < temp2.length; i++) {
                sum += temp2[i];
            }
            if (sum % 3 == 0) {
                if (sum == 0) {
                    list.add(new int[]{0});
                } else {
                    Arrays.sort(temp2);
                    list.add(temp2);
                }
            }
            return;
        }
        for (int i = 0; i <= digits.length - needNums; i++) {
            temp2[cur] = digits[i];
            getArrays(Arrays.copyOfRange(digits, i + 1, digits.length), needNums - 1, temp2, cur + 1);
        }
    }
}

package com.zm.LeetCodeEx;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author zm
 */
public class LEET043 {
    public static void main(String[] args) {
        LEET043 l043 = new LEET043();
        String num1 = "123";
        String num2 = "456";
        System.out.println(l043.multiply(num1, num2));
        System.out.println(l043.plus("1324", "16"));
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        return "0";
    }

    private String multiplyOneSimple(String num1, String oneDigitalNum) {
        for (int i = num1.length() - 1; i >= 0; i--) {
            String ret = multiplyTwoSimple(num1.substring(i, i + 1), oneDigitalNum);
        }
        return "0";
    }

    private String multiplyTwoSimple(String oneDigitalNum1, String oneDigitalNum2) {
        return String.valueOf(Integer.valueOf(oneDigitalNum1) * Integer.valueOf(oneDigitalNum2));
    }

    private String plus(String num1, String num2) {
        int max = Math.max(num1.length(), num2.length());
        int temp = 0;
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < max; i++) {
            int resTemp = temp + Integer.valueOf(num1.substring(num1.length() - i - 1, num1.length() - i)) + Integer.valueOf(num2.substring(num2.length() - i - 1, num2.length() - i));
            ret.insert(0, resTemp % 10);
            temp = resTemp / 10;
        }
        if (temp != 0) {
            ret.insert(0, temp);
        }
        return ret.toString();

    }
}

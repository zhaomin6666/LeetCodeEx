package com.zm.LeetCodeEx.weekcontest;

/**
 * 周赛 2020年2月23日 5169. 日期之间隔几天
 * <p>
 * 请你编写一个程序来计算两个日期之间隔了多少天。
 * <p>
 * 日期以字符串形式给出，格式为 YYYY-MM-DD，如示例所示。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：date1 = "2019-06-29", date2 = "2019-06-30"
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：date1 = "2020-01-15", date2 = "2019-12-31"
 * 输出：15
 * <p>
 * <p>
 * 提示：
 * <p>
 * 给定的日期是 1971 年到 2100 年之间的有效日期。
 *
 * @author zm
 */
public class LEET5169 {
    public static void main(String[] args) {
        LEET5169 l5169 = new LEET5169();
        System.out.println(l5169.daysBetweenDates("2019-06-29", "2019-06-30"));
        System.out.println(l5169.daysBetweenDates("2020-01-15", "2019-12-31"));
    }

    public int daysBetweenDates(String date1, String date2) {
        String[] dateStrArray1 = date1.split("-");
        String[] dateStrArray2 = date2.split("-");
        String[] biggerArray;
        String[] smallerArray;
        int com = dateCompare(dateStrArray1, dateStrArray2);
        if (com == 0) {
            return 0;
        } else if (com > 0) {
            biggerArray = dateStrArray1;
            smallerArray = dateStrArray2;
        } else {
            biggerArray = dateStrArray2;
            smallerArray = dateStrArray1;
        }

        return 0;
    }

    private int dateCompare(String[] dateStrArray1, String[] dateStrArray2) {
        for (int i = 0; i < 3; i++) {
            if (Integer.valueOf(dateStrArray1[i]) > Integer.valueOf(dateStrArray2[i])) {
                return 1;
            } else if (Integer.valueOf(dateStrArray1[i]) < Integer.valueOf(dateStrArray2[i])) {
                return -1;
            }
        }
        return 0;
    }
}

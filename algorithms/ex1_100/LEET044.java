package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 44. 通配符匹配
 * <p>
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 * <p>
 *
 * @author zm
 */
public class LEET044 {
    public static void main(String[] args) {
        LEET044 l044 = new LEET044();
        String s = "baaa";
        String p = "baa";
        String s2 = "aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba";
        String p2 = "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*";
        System.out.println(l044.isMatch3(s, p));
    }

    public boolean isMatch(String s, String p) {
        p = removeUseless(p);
        return checkIsMatch(s, p);
    }

    private String removeUseless(String p) {
        if (p.length() == 0) {
            return p;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(p.charAt(0));
        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i) == '*' && p.charAt(i - 1) == '*') {

            } else {
                sb.append(p.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 超出时间限制
     * <p>
     * 前提，去掉连续*
     * 1.如果s,p均为空满足条件
     * 2.如果s为空，p只有一个*，满足条件
     * 3.如果s为空，p不只有一个*，不满足条件
     * 4.如果s不为空，p为空，不满足条件
     * 5.如果p第一个为*，匹配s.substring(1),p，
     * 若返回成功，则满足条件（*匹配s.substring(0)），
     * 若返回不成功则匹配s,p.substring(1)即*不匹配任何字符串
     * 6.如果p第一个部位*，这个很好理解直接匹配即可
     * <p>
     * s = "adceb"
     * p = "*a*b"
     * 1.输入"adceb,"*a*b": p第一个为*,匹配dceb,*a*b
     * 2.输入"dceb,"*a*b": p第一个为*,匹配ceb,*a*b
     * ...
     * 3.输入"","*a*b": 判断3不满足条件退回
     * 4.输入"b","*a*b": 退回后匹配b,a*b，显然不满足，退回
     * 5.输入"eb","*a*b": 退回后匹配eb,a*b，显然不满足，退回
     * ...
     * 6.输入"adceb,"*a*b": 退回后匹配adceb,a*b
     * 7.输入"adceb,"a*b": 第一个相同，匹配dceb,*b
     * 8.输入"dceb,"*b": p第一个为*,匹配ceb,*b
     * 9.输入"ceb,"*b": p第一个为*,匹配eb,*b
     * 10.输入"eb,"*b": p第一个为*,匹配b,*b
     * 11.输入"b,"*b": p第一个为*,匹配,*b
     * 12.输入"","*b": 判断3不满足条件退回
     * 13.输入"b,"*b": 退回后匹配b,b，满足，返回成功
     *
     * @param s
     * @param p
     * @return
     */
    private boolean checkIsMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        } else if (s.length() == 0 && p.length() > 0) {
            if (p.charAt(0) == '*' && p.length() == 1) {
                return true;
            } else {
                return false;
            }
        } else if (s.length() > 0 && p.length() == 0) {
            return false;
        } else {
            if (p.charAt(0) == '*') {
                if (checkIsMatch(s.substring(1), p)) {
                    return true;
                } else {
                    return checkIsMatch(s, p.substring(1));
                }
            } else {
                if (p.charAt(0) != '?' && p.charAt(0) != s.charAt(0)) {
                    return false;
                } else {
                    return checkIsMatch(s.substring(1), p.substring(1));
                }
            }
        }
    }

    /**
     * 评论区评论使用的动态规划
     * 1.当s,p都是""的时候，显然符合，dp[0][0]=true
     * 2.当s为""，p中只要有非*的字符则不符合，从第一个开始如果是*则dp[0][1]取dp[0][0]，以此类推
     * 3.当s[i]==p[j]或者p[j]=='?'，此时的状态dp[i+1][j+1]和dp[i][j]相同
     * 4.当p[j]=='*'，此时的dp[i+1][j+1]等于 dp[i+1][j]或dp[i][j+1]，
     * 例如s="a",p="a*"
     * dp[0][0]=true,dp[0][1]=false,dp[0][2]=false
     * dp[1][0]=false,dp[1][1]=true,dp[1][2]=true(因为dp[1][1]为true,p加上一个*肯定也满足)
     * <p>
     * 例如s="aa",p="a*"
     * dp[0][0]=true,dp[0][1]=false,dp[0][2]=false
     * dp[1][0]=false,dp[1][1]=true,dp[1][2]=true(因为dp[1][1]为true,p加上一个*肯定也满足)
     * dp[2][0]=false,dp[2][1]=false(等于dp[1][0]),dp[2][2]=true(因为dp[1][2]为true,*能匹配任意字符串，s加上任意字符都满足)
     *
     * @param s
     * @param p
     * @return
     * @author powcai
     */
    public boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j < p.length() + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 回溯贪心
     * 参考C++代码写了一个
     *
     * @param s
     * @param p
     * @return
     * @author ~~辰风~~
     */
    public boolean isMatch3(String s, String p) {
        p = removeUseless(p);
        int I = 0, J = 0;//用i，j代表当前s，p的索引，I,J来记录上一次匹配成功的地方
        int i = 0, j = 0;
        while (i < s.length()) {
            if (j < p.length()) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {//如果匹配就继续判断
                    i++;
                    j++;
                    continue;
                } else if (p.charAt(j) == '*') {
                    I = i;//记下当前成功匹配的一段的索引，以后回溯用，这时星号不占字符
                    J = ++j;
                    continue;
                }
            }
            if (J != 0) {
                i = I + 1;//不匹配的时候，星号多占一个字符，j回溯到J，I往后移一位
                I++;
                j = J;
                continue;
            }
            return false;
        }
        if (j < p.length() && p.charAt(j) == '*') ++j;//把结尾的*，也去了

        return j == p.length();
    }
}

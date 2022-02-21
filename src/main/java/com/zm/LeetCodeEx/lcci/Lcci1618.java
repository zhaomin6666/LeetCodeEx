package com.zm.LeetCodeEx.lcci;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 面试题 16.18. 模式匹配
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 * 示例 2：
 * <p>
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 * 示例 3：
 * <p>
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 * 示例 4：
 * <p>
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 * 提示：
 * <p>
 * 0 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 *
 * @author zm
 */
public class Lcci1618 {
    public static void main(String[] args) {
        System.out.println(new Solution2().patternMatching("a", ""));
        System.out.println(new Solution2().patternMatching("aab", ""));
        System.out.println(new Solution2().patternMatching("aab", "dodo"));
        System.out.println(new Solution2().patternMatching("abba", "dogcatcatdog"));
        System.out.println(new Solution2().patternMatching("abba", "dogcatcatfish"));
        System.out.println(new Solution2().patternMatching("aaaa", "dogcatcatdog"));
        System.out.println(new Solution2().patternMatching("abba", "dogdogdogdog"));
        System.out.println(new Solution2().patternMatching("aaaa", "dogdogdogdog"));
    }

    /**
     * 回溯遍历所有的可能性
     */
    static class Solution {
        char[] pChars;
        char[] vChars;
        String value;
        HashMap<Character, String> map;
        HashSet<String> strSet;

        public boolean patternMatching(String pattern, String value) {
            this.value = value;
            pChars = pattern.toCharArray();
            vChars = value.toCharArray();
            map = new HashMap<>(pattern.length());
            strSet = new HashSet<>();
            return back(0, 0);
        }

        private boolean back(int ip, int iv) {
            if (ip == pChars.length && iv != vChars.length) {
                return false;
            }
            if (ip == pChars.length) {
                return true;
            }
            char curP = pChars[ip];
            if (map.containsKey(curP)) {
                String pString = map.get(curP);
                if (iv + pString.length() > vChars.length) {
                    return false;
                }
                if (value.startsWith(pString, iv)) {
                    return back(ip + 1, iv + pString.length());
                }
            }
            else {
                for (int i = iv; i <= vChars.length; i++) {
                    String temp = value.substring(iv, i);
                    if (strSet.contains(temp)) {
                        continue;
                    }
                    map.put(curP, temp);
                    strSet.add(temp);
                    boolean backRet = back(ip + 1, i);
                    if (backRet) {
                        return true;
                    }
                    map.remove(curP);
                    strSet.remove(temp);
                }
            }
            return false;
        }
    }

    /**
     * “abb” --> "dogcatcat"
     * 题目可以从长度入手，记a对应的字符串长度为aLength，记b对应的字符串长度为bLength，a的数量为aCount，b的数量为bCount
     * 那么一定有aLength*aCount+bLength*bCount=vLength，这时候再去解这个方程，只有自然数解才有可能成功。
     * aLength*aCount+(vLength-bLength)*bCount=vLength，保证这个方程有有且仅有一个解，我们枚举a的长度的时候，要保证aCount>0。
     */
    static class Solution2 {
        public boolean patternMatching(String pattern, String value) {
            // 判断a和b的数量，直接把数量多的置为a，保证aCount>0
            int aCount = 0;
            int bCount = 0;
            char[] cs = pattern.toCharArray();
            for (char c : cs) {
                if (c == 'a') {
                    ++aCount;
                }
                else {
                    ++bCount;
                }
            }
            if (value.length() == 0) {
                return bCount == 0;
            }
            if (pattern.length() == 0) {
                return false;
            }
            if (bCount > aCount) {
                for (int i = 0; i < cs.length; i++) {
                    cs[i] = cs[i] == 'a' ? 'b' : 'a';
                }
                int temp = aCount;
                aCount = bCount;
                bCount = temp;
            }
            System.out.println("p=" + new String(cs) + ", aCount=" + aCount + ", bCount=" + bCount);
            // 处理完成之后开始解方程 aLength*aCount+(vLength-bLength)*bCount=vLength，枚举aLength。
            for (int aLength = 0; aLength * aCount <= value.length(); ++aLength) {
                int rest = value.length() - aLength * aCount;
                if ((rest == 0 && bCount == 0) || (bCount != 0 && rest % bCount == 0)) {
                    int bLength = (bCount == 0 ? 0 : rest / bCount);
                    boolean success = true;
                    // 记录位置
                    int index = 0;
                    // 记录a和b代表的字符串
                    String aString = null, bString = null;
                    // 循环判断
                    for (char c : cs) {
                        if (c == 'a') {
                            String subString = value.substring(index, index + aLength);
                            if (aString == null) {
                                aString = subString;
                            }
                            else if (!aString.equals(subString)) {
                                success = false;
                                break;
                            }
                            index += aLength;
                        }
                        else {
                            String subString = value.substring(index, index + bLength);
                            if (bString == null) {
                                bString = subString;
                            }
                            else if (!bString.equals(subString)) {
                                success = false;
                                break;
                            }
                            index += bLength;
                        }
                    }
                    if (success && !aString.equals(bString)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}

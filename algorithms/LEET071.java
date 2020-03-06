package com.zm.LeetCodeEx.algorithms;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 71. 简化路径
 * <p>
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * <p>
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 * <p>
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * 示例 3：
 * <p>
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 * <p>
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * 示例 5：
 * <p>
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * 示例 6：
 * <p>
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 *
 * @author zm
 */
public class LEET071 {
    public static void main(String[] args) {
        LEET071 l071 = new LEET071();
        System.out.println(l071.new Solution().simplifyPath("/home/"));
        System.out.println(l071.new Solution().simplifyPath("/../"));
        System.out.println(l071.new Solution().simplifyPath("/home//foo/"));
        System.out.println(l071.new Solution().simplifyPath("/a/./b/../../c/"));
        System.out.println(l071.new Solution().simplifyPath("/a/../../b/../c//.//"));
        System.out.println(l071.new Solution().simplifyPath("/a/../../b/./c//.//"));
        System.out.println(l071.new Solution().simplifyPath("/.../../"));
    }

    class Solution {
        public String simplifyPath(String path) {
            path += "/";
            LinkedList<String> listDir = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            int dotCnt = 0;
            int charCnt = 0;
            char c;
            for (int i = 0; i < path.length(); i++) {
                c = path.charAt(i);
                if (c == '/') {
                    if (charCnt == 0 && dotCnt == 0) {
                        continue;
                    }
                    if (charCnt == 0 && dotCnt == 1) {
                    } else if (charCnt == 0 && dotCnt == 2) {
                        if (!listDir.isEmpty()) {
                            listDir.removeLast();
                        }
                    } else {
                        listDir.add(sb.toString());
                        charCnt = 0;
                    }
                    dotCnt = 0;
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                    if (c == '.') {
                        dotCnt++;
                    } else {
                        charCnt++;
                    }
                }
            }
            if (listDir.isEmpty()) {
                return "/";
            }
            for (String s : listDir) {
                sb.append("/").append(s);
            }
            return sb.toString();
        }
    }

    /**
     * 每个字符用栈接受，判断与前面1-3个字符的关系
     */
    class Solution2 {
        public String simplifyPath(String path) {
            path += "/";
            Stack<Character> stackChar = new Stack<>();
            char c;
            for (int i = 0; i < path.length(); i++) {
                c = path.charAt(i);
                if (stackChar.empty()) {
                    stackChar.push(c);
                } else {
                    if (c == '/') {
                        if (stackChar.peek() == '/') {
                            continue;
                        } else if (stackChar.peek() != '.') {
                            stackChar.push(c);
                        } else {
                            stackChar.pop();
                            if (stackChar.peek() == '.') {
                                stackChar.pop();
                                if (stackChar.peek() == '.') {
                                    stackChar.push('.');
                                    stackChar.push('.');
                                    stackChar.push('/');
                                } else {
                                    boolean delDirCnt = false;
                                    while (stackChar.size() > 1 && !(delDirCnt && stackChar.peek() == '/')) {
                                        if (stackChar.peek() == '/') {
                                            delDirCnt = true;
                                        }
                                        stackChar.pop();
                                    }
                                }
                            }
                        }
                    } else {
                        stackChar.push(c);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            if (stackChar.size() > 1) {
                char f = stackChar.pop();
                if (f != '/') {
                    sb.append(f);
                }
            }
            while (!stackChar.empty()) {
                sb.append(stackChar.pop());
            }
            return sb.reverse().toString();
        }
    }
}

package com.zm.LeetCodeEx.algorithms.ex801_900;

import java.util.Arrays;

/**
 * 820. 单词的压缩编码
 * <p>
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * <p>
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * <p>
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 * <p>
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母。
 *
 * @author zm
 */
public class LEET820 {
    public static void main(String[] args) {
        LEET820 l820 = new LEET820();
        System.out.println(
                l820.new Solution().minimumLengthEncoding(new String[]{"time", "me", "bell", "ell", "ebell"}));
    }

    /**
     * 使用字典树，由于每个单词是以#为结尾所以构造字典数的时候需要将单词倒序插入
     * 同时还要按单词长度，长度长的先插入
     */
    class Solution {
        TrieNode root = new TrieNode();
        int len = 0;

        public int minimumLengthEncoding(String[] words) {
            Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
            for (int i = 0; i < words.length; i++) {
                insert(words[i]);
            }
            return len;
        }

        public void insert(String word) {
            TrieNode cur = root;
            boolean isNew = false;
            for (int i = word.length() - 1; i >= 0; i--) {
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) {
                    isNew = true;
                    cur.children[c - 'a'] = new TrieNode(c);
                }
                cur = cur.children[c - 'a'];
            }
            if (isNew) {
                len += word.length() + 1;
            }
        }
    }

    class TrieNode {
        char val;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {
        }

        public TrieNode(char val) {
            this.val = val;
        }
    }
}

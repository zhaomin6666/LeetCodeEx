package LeetCode;

import java.util.HashSet;

/**
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * 
 * 示例 1:</br>
 * 
 * 输入: "hello" 输出: "holle" </br>
 * 
 * 示例 2:</br>
 * 
 * 输入: "leetcode" 输出: "leotcede" 说明: 元音字母不包含字母"y"。</br>
 * 
 * @author zm
 *
 */
public class LEET345 {
	public static void main(String[] args) {
		LEET345 l345 = new LEET345();
		String s = "hello";
		String s2 = "leetcode";
		System.out.println(l345.reverseVowels2(s));
		System.out.println(l345.reverseVowels2(s2));
	}

	public String reverseVowels(String s) {
		HashSet<Character> set = new HashSet<>();
		set.add('a');
		set.add('A');
		set.add('e');
		set.add('E');
		set.add('i');
		set.add('I');
		set.add('o');
		set.add('O');
		set.add('u');
		set.add('U');
		char[] ch = s.toCharArray();
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			while (!set.contains(ch[i]) && i < j) {
				++i;
			}
			while (!set.contains(ch[j]) && i < j) {
				--j;
			}
			if (i < j) {
				char t = ch[i];
				ch[i] = ch[j];
				ch[j] = t;
				++i;
				--j;
			}
		}
		return String.valueOf(ch);
	}

	public String reverseVowels2(String s) {
		char[] ch = s.toCharArray();
		int i = 0;
		int j = s.length() - 1;
		char t;
		while (i < j) {
			while (!isValid(ch[i]) && i < j) {
				++i;
			}
			while (!isValid(ch[j]) && i < j) {
				--j;
			}
			if (i < j) {
				t = ch[i];
				ch[i] = ch[j];
				ch[j] = t;
				++i;
				--j;
			}
		}
		return String.valueOf(ch);
	}
	
	private boolean isValid(char c){
        return c=='a' || c=='e' || c=='i' || c=='o' || c=='u'
            || c=='A' || c=='E' || c=='I' || c=='O' || c=='U';
    }
}

package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 
 * @author zm
 *
 */
public class LEET017 {
	public static void main(String[] args) {
		LEET017 l016 = new LEET017();
		System.out.println(
				JSONObject.toJSONString(l016.letterCombinations("23")));
	}

	HashMap<Character, String> hashMap = new HashMap<>();
	List<String> ret = new LinkedList<String>();

	/**
	 * 递归调用即可
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {
		hashMap.put('2', "abc");
		hashMap.put('3', "def");
		hashMap.put('4', "ghi");
		hashMap.put('5', "jkl");
		hashMap.put('6', "mno");
		hashMap.put('7', "pqrs");
		hashMap.put('8', "tuv");
		hashMap.put('9', "wxyz");

		if (digits == null || "".equals(digits)) {
			return ret;
		}
		find(digits, 0, "");
		return ret;
	}

	private void find(String digits, int index, String now) {
		if (index == digits.length()) {
			ret.add(now);
			return;
		}
		char c = digits.charAt(index);
		String ch = hashMap.get(c);
		for (int i = 0; i < ch.length(); i++) {
			find(digits, index + 1, now + ch.charAt(i));
		}
	}
}

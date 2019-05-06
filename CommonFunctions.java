package LeetCode;

import java.util.HashMap;

public class CommonFunctions {
	/**
	 * 把words写成map key = 某个单词 ; value = 出现次数
	 * 
	 * @param words
	 * @return
	 */
	public static HashMap<String, Integer> convertListToMap(String[] words) {
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			if (map.get(words[i]) == null) {
				map.put(words[i], 1);
			} else {
				map.put(words[i], map.get(words[i]) + 1);
			}
		}
		return map;
	}

	/**
	 * 把word写成map key = 某个字母 ; value = 出现次数
	 * 
	 * @param words
	 * @return
	 */
	public static HashMap<Character, Integer> convertStringToMap(String word) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < word.length(); i++) {
			if (map.get(word.charAt(i)) == null) {
				map.put(word.charAt(i), 1);
			} else {
				map.put(word.charAt(i), map.get(word.charAt(i)) + 1);
			}
		}
		return map;
	}

	public static ListNode stringToListNode(String input) {
		// Generate array from the input
		int[] nodeValues = stringToIntegerArray(input);

		// Now convert that list into linked list
		ListNode dummyRoot = new ListNode(0);
		ListNode ptr = dummyRoot;
		for (int item : nodeValues) {
			ptr.next = new ListNode(item);
			ptr = ptr.next;
		}
		return dummyRoot.next;
	}

	public static String listNodeToString(ListNode node) {
		if (node == null) {
			return "[]";
		}

		String result = "";
		while (node != null) {
			result += Integer.toString(node.val) + ", ";
			node = node.next;
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	/**
	 * "[1,2,3]" ----> [1,2,3]
	 * 
	 * @param input
	 * @return
	 */
	public static int[] stringToIntegerArray(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return new int[0];
		}

		String[] parts = input.split(",");
		int[] output = new int[parts.length];
		for (int index = 0; index < parts.length; index++) {
			String part = parts[index].trim();
			output[index] = Integer.parseInt(part);
		}
		return output;
	}

	/**
	 * [1,2,3] 0 最后一个节点连接到第一个节点 [1,2,3] 1 最后一个节点连接到第二个节点
	 * 
	 * @param input
	 * @param pos
	 * @return
	 */
	public static ListNode stringToListNodeWithCircle(String input, int pos) {
		// Generate array from the input
		int[] nodeValues = stringToIntegerArray(input);

		// Now convert that list into linked list
		ListNode dummyRoot = new ListNode(0);
		ListNode ptr = dummyRoot;
		for (int item : nodeValues) {
			ptr.next = new ListNode(item);
			ptr = ptr.next;
		}
		ListNode posNode = dummyRoot.next;
		for (int i = 0; i < pos; i++) {
			posNode = posNode.next;
		}
		if (pos >= 0) {
			ptr.next = posNode;
		}
		return dummyRoot.next;
	}
}

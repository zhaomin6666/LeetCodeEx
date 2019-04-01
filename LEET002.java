package LeetCode;

import java.io.IOException;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 方法的区别就在于判断，减少无用的逻辑
 * @author zm
 *
 */
public class LEET002 {
	public static void main(String[] args) throws IOException {
		ListNode l1 = stringToListNode("[2,4,3]");
		ListNode l2 = stringToListNode("[5,6]");
		ListNode ret = new LEET002().addTwoNumbers2(l1, l2);
		String out = listNodeToString(ret);
		System.out.print(out);
	}
	
	/**
	 * 循环末尾判断，两数都取完并没有进位
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = null;
		ListNode resulttemp = null;
		ListNode temp1 = l1;
		ListNode temp2 = l2;
		int temp = 0;
		while (true) {
			int temp1val = temp1 == null ? 0 : temp1.val;
			int temp2val = temp2 == null ? 0 : temp2.val;
			int sum = temp1val + temp2val + temp;
			temp = sum / 10;
			if (result == null) {
				result = new ListNode(sum % 10);
				resulttemp = result;
			} else {
				resulttemp.val = sum % 10;
			}
			temp1 = temp1 == null ? null : temp1.next;
			temp2 = temp2 == null ? null : temp2.next;
			if (temp == 0 && temp1 == null && temp2 == null) {
				break;
			} else {
				resulttemp.next = new ListNode(0);
				resulttemp = resulttemp.next;
			}
		}
		return result;
	}
	
	/**
	 * 只要两个数有一个没有取完，则循环继续，结束之后判断是否有进位，加上
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode newlist = new ListNode(0);
		int sum = 0;
		ListNode cur = newlist;
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			cur.next = new ListNode(sum % 10);
			sum /= 10;
			cur = cur.next;
		}
		if (sum == 1) {
			cur.next = new ListNode(sum);
		}
		return newlist.next;
	}

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

}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
	}
}

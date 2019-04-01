package LeetCode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 
 * 示例：
 * 
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5. 说明：
 * 
 * 给定的 n 保证是有效的。
 * 
 * @author zm
 *
 */
public class LEET019 {
	public static void main(String[] args) {
		LEET019 l019 = new LEET019();
		ListNode l1 = stringToListNode("[2,4,3,5]");
		System.out.println(listNodeToString(l019.removeNthFromEnd(l1, 1)));
	}

	/**
	 * 两个标识，一个用来判断是否走到最后一个结点，另一个固定和后面那个结点的举例为n，这样就是倒数第n个节点的父结点。<br>
	 * 最后还要判断下是否是第一个结点。
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode ln1 = head;
		ListNode ln2 = head;
		int index = 0;
		while (ln2.next != null) {
			if (index < n) {
				index++;
				ln2 = ln2.next;
			} else {
				ln2 = ln2.next;
				ln1 = ln1.next;
			}
		}
		if (index == n - 1) {
			return head.next;
		}
		ln1.next = ln1.next.next;
		return head;
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

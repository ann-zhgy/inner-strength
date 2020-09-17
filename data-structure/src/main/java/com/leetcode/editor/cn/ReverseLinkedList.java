package com.leetcode.editor.cn;

//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1204 👎 0


/**
 * @author 88475
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list;
        ListNode node = list = new ListNode(2);
        for (int i = 0; i < 5; i++) {
            node.next = new ListNode(4 + i);
            node = node.next;
        }
        System.out.println(list);
        ListNode result = solution.reverseList(list);
        System.out.println(result);
    }

    private static

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        /*
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }*/

        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode result = null;
            while (head != null) {
                ListNode temp = head.next;
                head.next = result;
                result = head;
                head = temp;
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

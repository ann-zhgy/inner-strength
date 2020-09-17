package com.leetcode.editor.cn;

/**
 * Description：
 * <p>
 * Date: 2020-9-3 12:26
 *
 * @author 88475
 * @version v1.0
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ListNode: ");
        ListNode node = this;
        while (node != null) {
            builder.append(node.val).append(" -> ");
            node = node.next;
        }
        return builder.append("NULL").toString();
    }
}

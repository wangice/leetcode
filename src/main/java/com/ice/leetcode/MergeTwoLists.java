package com.ice.leetcode;


/**
 * @author ice
 * @Date 2019/1/31 14:21
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode secodeNode = new ListNode(2);
        l1.next = secodeNode;
        secodeNode.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        ListNode secodeNode2 = new ListNode(3);
        l2.next = secodeNode2;
        secodeNode2.next = new ListNode(4);

        MergeTwoLists me = new MergeTwoLists();
        ListNode listNode = me.mergeTwoLists(l1, l2);

        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode target = root;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ListNode listNode = new ListNode(l1.val);
                target.next = listNode;
                target = target.next;
                l1 = l1.next;
            } else {
                ListNode listNode = new ListNode(l2.val);
                target.next = listNode;
                target = target.next;
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            ListNode listNode = new ListNode(l1.val);
            target.next = listNode;
            target = target.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            ListNode listNode = new ListNode(l2.val);
            target.next = listNode;
            target = target.next;
            l2 = l2.next;
        }
        return root.next;
    }

    public void execution(ListNode root, ListNode node) {
        ListNode listNode = new ListNode(node.val);
        root.next = listNode;
        root = root.next;
        node = node.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }
}

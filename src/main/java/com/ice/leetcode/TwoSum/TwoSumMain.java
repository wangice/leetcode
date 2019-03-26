package com.ice.leetcode.TwoSum;

/**
 * @author ice
 * @Date 2019/1/8 14:06
 */
public class TwoSumMain {

  public static void main(String[] args) {
    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(3);
    ListNode l2 = new ListNode(8);
    l2.next = new ListNode(3);
    TwoSumMain twoSumMain = new TwoSumMain();
    ListNode listNode = twoSumMain.addTwoNumbers(l1, l2);
    StringBuilder sb = new StringBuilder();
    while (listNode != null) {
      sb.append(listNode.val);
      listNode = listNode.next;
    }
    System.out.println(sb.toString());
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode node = null;
    int x = 0;
    int y = 0;
    if (l1 == null) {
      x = l2.val;
    } else if (l2 == null) {
      x = l1.val;
    } else {
      x = l1.val + l2.val;
    }
    if (x >= 10) {
      x = x % 10;
      y = 1;
    }
    node = new ListNode(x);
    l1 = l1 == null ? null : l1.next;
    l2 = l2 == null ? null : l2.next;
    if (l1 != null || l2 != null) {
      if (l1 == null) {
        l2.val = l2.val + y;
      } else {
        l1.val = l1.val + y;
      }
      node.next = addTwoNumbers(l1, l2);
    } else {
      if (y > 0) {
        node.next = addTwoNumbers(new ListNode(y), null);
      }
    }
    return node;
  }
}

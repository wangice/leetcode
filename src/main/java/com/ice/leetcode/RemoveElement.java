package com.ice.leetcode;

/**
 * @author ice
 * @Date 2019/1/30 10:25
 */
public class RemoveElement {

    public static void main(String[] args) {
        RemoveElement removeElement = new RemoveElement();
        int[] nums = {1, 3, 3, 3, 4, 4, 5, 6, 3, 6};
        int len = removeElement.removeElement(nums, 3);
        System.out.println(len);
    }

    public int removeElement(int[] nums, int val) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[len] = nums[i];
                len++;
            }
        }
        return len;
    }
}

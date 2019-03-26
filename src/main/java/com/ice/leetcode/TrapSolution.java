package com.ice.leetcode;

/**
 * @author ice
 * @Date 2019/1/31 10:19
 */
public class TrapSolution {

    public static void main(String[] args) {
        TrapSolution trap = new TrapSolution();
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int sum = trap.trap(nums);
        System.out.println(sum);
    }

    public int trap(int[] height) {
        int x = 10;
        ;
        int sum = 0;
        int len = height.length;
        if (len < 3)
            return 0;
        int leftHign = height[0];
        int rightHgin = height[len - 1];
        int left = 1;
        int right = len - 2;
        while (left <= right) {
            if (leftHign < rightHgin) {
                if (leftHign < height[left])
                    leftHign = height[left];
                else
                    sum += leftHign - height[left];
                left++;
            } else {
                if (rightHgin < height[right])
                    rightHgin = height[right];
                else
                    sum += rightHgin - height[right];
                right--;
            }
        }
        return sum;
    }
}

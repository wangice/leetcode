package com.ice.leetcode;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为
 * (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * @author ice
 * @Date 2019/1/19 12:02
 */
public class MaxArea {

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        long startTiem = System.currentTimeMillis();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int max = maxArea.maxArea(height);
        long endTime = System.currentTimeMillis();
        System.out.println(max);
        System.out.println(endTime - startTiem);
    }

    // 暴力
    public int maxArea1(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int sum = (j - i) * Math.min(height[i], height[j]);
                if (sum > max)
                    max = sum;
            }
        }
        return max;
    }

    //双指针法，获取最大值，则高度和长度最够长
    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        while (start < end) {
            int sum = (end - start) * Math.min(height[start], height[end]);
            if (sum > max) {
                max = sum;
            }
            if (height[start] > height[end]) {
                end--;
            } else {
                start++;
            }
        }
        return max;
    }
}

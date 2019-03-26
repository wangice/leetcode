package com.ice.leetcode;

/**
 * @author ice
 * @Date 2019/1/31 09:24
 */
public class SearchInsert {

    public static void main(String[] args) {
        SearchInsert searchInsert = new SearchInsert();
        int[] nums = {1, 3, 5, 6};
        int i = searchInsert.searchInsert(nums, 7);
        System.out.println(i);
    }


    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int midle = 0;
        while (start <= end) {
            midle = (end + start) / 2;
            if (nums[midle] == target) {
                return midle;
            } else if (nums[midle] > target) {
                if (midle - 1 > -1 && nums[midle - 1] < target) {
                    return midle < 0 ? 0 : midle;
                }
                end = midle - 1;
            } else {
                if (midle + 1 < nums.length && nums[midle + 1] > target) {
                    return midle + 1 > nums.length - 1 ? nums.length - 1 : midle + 1;
                }
                start = midle + 1;
            }

        }
        if (nums[0] > target)
            return 0;
        else
            return nums.length;
    }
}

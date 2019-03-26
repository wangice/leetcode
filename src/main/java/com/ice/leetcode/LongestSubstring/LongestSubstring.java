package com.ice.leetcode.LongestSubstring;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *
 * @author ice
 * @Date 2019/1/9 14:12
 */
public class LongestSubstring {

  public static void main(String[] args) {
    String str = " ";
    LongestSubstring solution = new LongestSubstring();
    int i = solution.lengthOfLongestSubstring(str);
    System.out.println(i);
  }

  public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int[] lengths = new int[s.length()];
    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      StringBuilder sb = new StringBuilder().append(chars[i]);
      int j = i + 1;
      for (; j < chars.length; j++) {
        if (sb.toString().contains(chars[j] + "")) {
          break;
        } else {
          sb.append(chars[j]);
        }
      }
      lengths[i] = j - i;
    }
    int max = 0;
    for (int i = 0; i < lengths.length; i++) {
      if (max < lengths[i]) {
        max = lengths[i];
      }
    }
    return max;
  }
}

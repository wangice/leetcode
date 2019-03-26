package com.ice.leetcode.LongestPalindrome;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"
 * @author ice
 * @Date 2019/1/10 18:59
 */
public class LongestPalindrome {


  public static void main(String[] args) {
    String str = "abcba";
    long startTime = new Date().getTime();
    LongestPalindrome palindrome = new LongestPalindrome();
    String s = palindrome.longestPalindrome(str);
    System.out.println(s);
    long endTime = new Date().getTime();
    System.out.println((endTime - startTime) / 1000);

    boolean palidrome = palindrome.isPalidrome(12321);
    System.out.println(palidrome);
  }

  public String longestPalindrome(String s) {
    if (s == null) {
      return null;
    }
    if (s.length() <= 1) {
      return s;
    }
    String longStr = s.substring(0, 1);
    for (int i = 0; i < s.length(); i++) {
      String tmp = helper(s, i, i);
      if (tmp.length() > longStr.length()) {
        longStr = tmp;
      }
      tmp = helper(s, i, i + 1);
      if (tmp.length() > longStr.length()) {
        longStr = tmp;
      }
    }
    return longStr;
  }

  public String helper(String s, int begin, int end) {
    while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
      begin--;
      end++;
    }
    String substring = s.substring(begin + 1, end);
    return substring;
  }


  public String longestPalindrome1(String s) {
    if (s == null) {
      return null;
    }
    if (s.length() <= 1) {
      return s;
    }
    List<String> list = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) {
      for (int j = i; j < s.length(); j++) {
        char[] cs = new char[j - i + 1];
        s.getChars(i, j + 1, cs, 0);
        String rome = new String(cs);
        if (isPalindrome(rome)) {
          list.add(rome);
        }
      }
    }
    int max = 0;
    int maxLength = 0;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).length() > maxLength) {
        maxLength = list.get(i).length();
        max = i;
      }
    }
    return list.get(max);
  }

  public boolean isPalindrome(String s) {
    if (s.length() == 1) {
      return true;
    }
    char[] chars = s.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int j = chars.length - 1; j >= 0; j--) {
      sb.append(chars[j]);
    }
    if (s.equals(sb.toString())) {
      return true;
    }
    return false;
  }

  public boolean isPalidrome(int x) {
    if (x < 0 || (x % 10 == 0 && x != 0)) {
      return false;
    }
    int reset = 0;
    while (x > reset) {
      reset = reset * 10 + x % 10;
      x /= 10;
    }
    return x == reset || x == reset / 10;
  }

}

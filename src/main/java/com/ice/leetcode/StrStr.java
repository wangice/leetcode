package com.ice.leetcode;

/**
 * @author ice
 * @Date 2019/1/30 15:41
 */
public class StrStr {

    public static void main(String[] args) {

        StrStr strStr = new StrStr();
        String haystack = "mississippi";
        String needle = "issip";
        int i = strStr.strStr(haystack, needle);
        System.out.println(i);
    }

    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        int max = haystack.length() - needle.length();
        for (int i = 0; i < haystack.length(); i++) {
            int start = 0;
            int index = needle.length() - 1;
            while (i + index < haystack.length() && start <= index) {
                if (haystack.charAt(i + start) == needle.charAt(start) && haystack.charAt(i + index) == needle.charAt(index)) {
                    start++;
                    index--;
                    if (start > index) {
                        return i;
                    }
                } else {
                    break;
                }
            }

        }
        return -1;
    }
}

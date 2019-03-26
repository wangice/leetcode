package com.ice.leetcode;

/**
 * @author ice
 * @Date 2019/1/19 13:39
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        LongestCommonPrefix log = new LongestCommonPrefix();
        String[] strs = {};
        String s = log.longestCommonPrefix(strs);
        System.out.println(s);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1)
            return strs[0];
        int index = 0;
        int min = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < min) {
                min = strs[i].length();
                index = i;
            }
        }
        String str = strs[index];
        int indx = 0;
        boolean flag = true;
        for (int i = 0; i < min; i++) {
            if (!flag) {
                break;
            }
            for (int j = 0; j < strs.length; j++) {
                if (j == index)
                    continue;
                if (str.charAt(i) != strs[j].charAt(i)) {
                    flag = false;
                    break;
                }
            }
            indx = i;
        }
        if (!flag && indx == 0) {
            return "";
        } else if (!flag && indx != 0) {
            return str.substring(0, indx);
        } else {
            return str;
        }
    }
}

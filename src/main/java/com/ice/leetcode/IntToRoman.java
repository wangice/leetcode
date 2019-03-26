package com.ice.leetcode;

/**
 * @author ice
 * @Date 2019/1/19 12:46
 */
public class IntToRoman {
    public static void main(String[] args) {
        IntToRoman intToRoman = new IntToRoman();
        String s = intToRoman.intToRoman(1993);
        System.out.println(s);
        int mcmxciv = intToRoman.romanToInt("DCXXI");
        System.out.println(mcmxciv);
    }

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] reps = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String res = "";
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                res += reps[i];
            }
        }
        return res;
    }

    public int romanToInt(String s) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] reps = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int num = 0;
        String rep = s;
        for (int i = 0; i < reps.length; i++) {
            if (rep.length() == 0)
                break;
            while (rep.length() >= reps[i].length() && rep.substring(0, reps[i].length()).equals(reps[i])) {
                rep = rep.substring(reps[i].length());
                num += values[i];
                if (rep.equals(""))
                    break;
            }
        }
        return num;
    }
}

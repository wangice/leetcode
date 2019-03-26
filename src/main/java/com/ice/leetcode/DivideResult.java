package com.ice.leetcode;

/**
 * @author ice
 * @Date 2019/1/30 18:09
 */
public class DivideResult {
    public static void main(String[] args) {
        DivideResult divideResult = new DivideResult();
        int divide = divideResult.divide(7, -3);
        System.out.println(divide);
    }

    public int divide(int dividend, int divisor) {

        int i = 1;
        boolean flag = false;
        if (dividend < 0 || divisor < 0) {
            flag = true;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while (divisor < dividend) {
            divisor <<= 1;
            i++;
        }
        if (flag) {
            return -i + 1;
        } else {
            return i - 1;
        }
    }
}

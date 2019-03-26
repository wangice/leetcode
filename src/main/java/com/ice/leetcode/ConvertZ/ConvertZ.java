package com.ice.leetcode.ConvertZ;

/**
 * @author ice
 * @Date 2019/1/11 09:47
 */
public class ConvertZ {

  public static void main(String[] args) {
    ConvertZ convertZ = new ConvertZ();
    int reverse = convertZ.reverse(-1285685);
    System.out.println(reverse);
  }

  public int reverse(int x) {
    int rev = 0;
    while (x != 0) {
      int pop = x % 10;
      x /= 10;
      if ((rev > Integer.MAX_VALUE / 10) || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
        return 0;
      }
      if ((rev < Integer.MIN_VALUE / 10) || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
        return 0;
      }
      rev = rev * 10 + pop;
    }
    return rev;
  }


  public String convert(String s, int numRows) {

    return null;
  }

}

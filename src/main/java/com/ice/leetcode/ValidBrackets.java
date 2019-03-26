package com.ice.leetcode;

import java.util.Stack;

/**
 * @author ice
 * @Date 2019/1/29 20:17
 */
public class ValidBrackets {

    public static void main(String[] args) {
        ValidBrackets validBrackets = new ValidBrackets();
        boolean valid = validBrackets.isValid("(([]){})");
        System.out.println(valid);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(s.charAt(i));
            } else {
                char cs = stack.peek() == null ? '#' : stack.pop();
                if (!isSym(cs, s.charAt(i)))
                    return false;
            }

        }
        return stack.isEmpty();
    }

    private boolean isSym(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }
}

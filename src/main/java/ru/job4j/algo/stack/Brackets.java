package ru.job4j.algo.stack;

import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        var stack = new Stack<Character>();
        for (char current : s.toCharArray()) {
            if (!stack.isEmpty() && (stack.peek() == current - 1 || stack.peek() == current - 2)) {
                stack.pop();
            } else {
                stack.push(current);
            }
        }
        return stack.isEmpty();
    }
}

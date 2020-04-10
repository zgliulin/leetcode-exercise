package com.liulin.demo.algorithm.e2020.e04.e9.generateparentheses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * \数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
 * <p>
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * Create by DbL on 2020/4/9 0009
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateParenthesis(3).toArray()));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();
        if (n == 0) return results;
        dfs("", n, n, results);
        return results;
    }

    /**
     *
     * @param curStr
     * @param left
     * @param right
     * @param results
     */
    private static void dfs(String curStr, int left, int right, List<String> results) {
        if (left == 0 && right == 0) {
            results.add(curStr);
            return;
        }

        if (left > 0) {
            dfs(curStr+"(", left - 1, right, results);
        }
        if (right > left) {
            dfs(curStr+")", left, right - 1, results);
        }
    }


}

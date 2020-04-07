package com.liulin.demo.algorithm.e2020.e04.e06.editdistance;

import com.sun.media.sound.ModelStandardIndexedDirector;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * Create by DbL on 2020/4/6 0006
 */
public class editDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("intention","execution"));
    }
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        // 定义二维数组 长度加1是因为word1和word2可能存在空字符串的情况
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 假设Word2为空，那么只需要对Word1进行word1长度的对应删除操作
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        // 假设word1为空，那么只需要对word1进行word2长度的对应插入操作
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 如果word1与word2对应的字符串相等，表示在上一个字符串比较的基础上，不做任何操作
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 如果不相等 增加删除或修改的最小值
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}

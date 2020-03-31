package com.liulin.demo.algorithm.e2020.e03.e30;

import java.util.LinkedList;

/**
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * 示例 1：
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 * 输入: n = 10, m = 17
 * 输出: 2
 * Create by DbL on 2020/3/30 0030
 */
public class LastRemaining {
    public static void main(String[] args) {
        System.out.println(lastRemaining2(10,3));
    }
    public static int lastRemaining(int n, int m) {
        return n==1?0:(lastRemaining(n-1,m)+m)%n;
    }

    public static int lastRemaining2(int n, int m) {
        LinkedList<Integer> list = new LinkedList<>();
        // 全部加进去
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        // 只要链表长度不为 1，就不断循环
        while (list.size() != 1) {
            for (int i = 0; i < m; i++) {
                // 取出第一个元素并删除
                Integer pre = list.pollFirst();
                if (i != m - 1) {
                    // 如果不是第m个，就加到末尾
                    list.add(pre);
                }
            }
        }
        return list.pollFirst();
    }
}

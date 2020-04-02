package com.liulin.demo.algorithm.e2020.e04.e2;

import java.util.Arrays;

/**
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），
 * 或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 * Create by DbL on 2020/4/2 0002
 */
public class GameOfLife {
    public static void main(String[] args) {
        int[][] a = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        System.out.println(20/10);
        gameOfLife(a);
    }

    public static void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[] x = {-1, 0, 1, -1,  1, -1,  0,  1};
        int[] y = { 1, 1, 1,  0,  0, -1, -1, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 8; k++) {
                    if (i + x[k] >= n || i + x[k] < 0 || j + y[k] >= m || j + y[k] < 0) continue;
                    if (board[i + x[k]][j + y[k]] % 10 == 1)  board[i][j] += 10;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] / 10 == 3) {
                    board[i][j] = 1;
                    continue;
                }
                if (board[i][j] / 10 > 3 || board[i][j] / 10 < 2){
                    board[i][j] = 0;
                    continue;
                }
                if (board[i][j] / 10 == 2){
                    board[i][j] = board[i][j] % 10;
                    continue;
                }
            }
        }

    }
}

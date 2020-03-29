package com.liulin.demo.algorithm.e2020.e03.e29.maxdistance;

import java.util.ArrayList;
import java.util.List;

/**
 * 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。
 * 其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
 * 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
 *如果我们的地图上只有陆地或者海洋，请返回 -1。
 *
 * 输入：[[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
 *
 * 输入：[[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
 * Create by DbL on 2020/3/29 0029
 */
public class ViolentSolution {
    public static void main(String[] args) {
        int[][] grid = {{1,0,0},{0,0,0},{0,0,0}};
        System.out.println(maxDistance(grid));
    }
    public static int maxDistance(int[][] grid) {
        // 距离陆地区域最远的海洋区域到离它最近的陆地区域的距离
        int minDistance = 0;
        // 将所有海洋和陆地的坐标分别存储
        List<int[]> listLand = new ArrayList<int[]>();
        List<int[]> ListOcean = new ArrayList<int[]>();
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[i].length ; j++) {
                if(grid[i][j]==0){
                    ListOcean.add(new int[]{i,j});
                }else{
                    listLand.add(new int[]{i,j});
                }
            }
        }
        // 如果全部都是海洋或者陆地直接返回-1
        if(listLand.size()==0 || ListOcean.size()==0){
            return -1;
        }

        for (int i = 0; i < ListOcean.size(); i++) {
            int curMinDistance = 0; // 当前循环海洋区域距离其他陆地区域最近的距离
            for (int j = 0; j < listLand.size(); j++) {
                // 每个陆地与改海洋点位的距离
                int distance = Math.abs(listLand.get(j)[0]-ListOcean.get(i)[0]) + Math.abs(listLand.get(j)[1]-ListOcean.get(i)[1]);
                // 如果该海洋距离陆地的点位的距离比其他已比较的点位的最小距离还小  则修改距离陆地的最近距离为该距离
                // 首次循环直接赋值
                curMinDistance = curMinDistance == 0 ? distance : distance < curMinDistance ? distance : curMinDistance;
            }
            // 如果该海洋点位与其所有陆地的最小距离比其他海洋点位与所有陆地点位的距离还要小，则认为当前点位为距离陆地区域最远的海洋区域
            if(minDistance < curMinDistance){
                minDistance = curMinDistance;
            }
        }
        return minDistance;
    }
}

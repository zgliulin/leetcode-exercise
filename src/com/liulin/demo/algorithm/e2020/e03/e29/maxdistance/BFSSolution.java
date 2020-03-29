package com.liulin.demo.algorithm.e2020.e03.e29.maxdistance;

import java.util.ArrayDeque;
import java.util.Queue;

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
public class BFSSolution {
    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0,0,0},
                        {0,0,0,0,0,0},
                        {0,0,0,0,0,1},
                        {0,0,0,0,0,0},
                        {0,0,0,0,0,0},
                        {0,0,1,0,0,0}};
        System.out.println(maxDistance(grid));
    }
    public static int maxDistance(int[][] grid) {
        // 对应当前坐标周围的四个坐标
        int[] aroundX = {0,0,1,-1};
        int[] aroundY = {1,-1,0,0};
        // 记录所有陆地区域的点
        Queue<int[]> queueLand = new ArrayDeque<int[]>();
        int colLength = grid.length;
        int rowLength = grid[0].length; // 因为grid中每个元素的长度都相同，这里取第一个
        for (int i = 0; i < colLength; i++) {
            for (int j = 0; j < rowLength; j++) {
                if(grid[i][j] == 1){
                    queueLand.offer(new int[]{i,j});
                }
            }
        }

        // 循环各个陆地 向外扩散 这里运用队列先进先出的特性
        boolean isAllLandOrOcean = false;
        int[] maxPoint = null;
        while (!queueLand.isEmpty()){
            maxPoint = queueLand.poll(); // 取出第一个点位的值并从队列中删除
            // 遍历周围的四个点 以当前区域对X,Y坐标进行增减可得到周围的四个点
            for (int i = 0; i < 4 ; i++) {
                int newX = maxPoint[0] + aroundX[i];
                int newY = maxPoint[1] + aroundY[i];
                // 只对海洋区域的点位操作
                if(newX > -1 &&  newX < colLength && newY > -1 && newY < rowLength && grid[newX][newY] == 0){
                    isAllLandOrOcean = true; // 能进入这里说明队列肯定不为空且存在未海洋区域的点
                    // 修改海洋区域的点位为距离最近的陆地的距离 从陆地点位向外扩散 每次以当前节点的值+1
                    grid[newX][newY] = grid[maxPoint[0]][maxPoint[1]] + 1;
                    // 把点位加到队列中进行下一轮循环
                    queueLand.offer(new int[]{newX,newY});
                }
            }
        }
        if(maxPoint == null || ! isAllLandOrOcean){
            return -1;
        }
        return grid[maxPoint[0]][maxPoint[1]] - 1;
    }
}

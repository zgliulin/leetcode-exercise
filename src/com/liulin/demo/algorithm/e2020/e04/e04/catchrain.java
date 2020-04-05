package com.liulin.demo.algorithm.e2020.e04.e04;

/**
 * Create by DbL on 2020/4/4 0004
 */
public class catchrain {
    public static void main(String[] args) {
        int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] b = {2, 0, 1, 1, 0, 3, 2, 1, 0, 0, 4, 1, 2, 0, 2, 1};
        int[] c = {1, 2, 1, 2, 3};
        int[] d = {8,2,8,9,0,1,7,7,9};
        System.out.println(trap(b));

    }
    public static int trap(int[] height) {
        int n = height.length;
        if (n < 2) return 0;
        int entity = 0;
        int index = 0;
        while (height[index] == 0 ) index++;
        int left = height[index];
        int leftIndex = index;
        int flag = 0;
        int count = 0;
        int curEntity = 0;
        for (int i = index + 1; i < n; i++) {
            if (left > height[i]) {
                flag++;
                curEntity += height[i];
                continue;
            }
            if (left <= height[i]) {
                if (flag > 0) {
                    count += (i - leftIndex - 1) * left;
                    entity += curEntity;
                    if(i + 1 < n - 1 && height[i] < height[i + 1]){
                        i++;
                    }
                    left = height[i];
                    leftIndex = i;
                    flag = 0;
                    curEntity = 0;
                }else{
                    if(leftIndex+1 == i){
                        left = height[i];
                        leftIndex = i;
                    }
                }
            }
        }

        int rightNum = 0;
        if (left > height[n - 1]) {
            int[] right = new int[n - leftIndex];
            int rightIndex = 0;
            for (int i = n - 1; i >= leftIndex; i--) {
                right[rightIndex] = height[i];
                rightIndex++;
            }
            rightNum = trap(right);
        }
        return count - entity + rightNum;
    }
}

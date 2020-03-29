package com.liulin.demo.algorithm.e2020.e03.e29.sumoftwo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * Create by DbL on 2020/3/29 0029
 */
public class GetNumOfSumByMap {
    public static void main(String[] args) {
        int[] nums = {3, 2,4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums,target)));
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map  = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            int numA = nums[i];
            int numB = target - numA;
            if(map.containsKey(numB)){
                return new int[]{map.get(numB),i};
            }
            map.put(numA,i);

        }
        return null;
    }
}

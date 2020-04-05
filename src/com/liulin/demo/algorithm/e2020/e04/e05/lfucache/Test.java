package com.liulin.demo.algorithm.e2020.e04.e05.lfucache;

/**
 * Create by DbL on 2020/4/5 0005
 */
public class Test {
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(5);


        lfuCache.put(1,1);
        lfuCache.put(2,2);
        lfuCache.put(3,3);
        lfuCache.put(4,4);
        lfuCache.put(5,5);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(4));
        System.out.println(lfuCache.get(5));
        lfuCache.put(6,6);
        System.out.println("push(6,6)此时cache：");
        lfuCache.printCache();
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(6));
        System.out.println("================此时剩余12456 均只调用一次 1距离上次调用时间最久================");
        lfuCache.put(7,7); // 此时应该为 24567
        System.out.println("push（7,7），此时cache：");
        lfuCache.printCache();
        System.out.println(lfuCache.get(1));// 应为 -1


    }
}

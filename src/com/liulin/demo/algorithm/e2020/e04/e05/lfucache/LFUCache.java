package com.liulin.demo.algorithm.e2020.e04.e05.lfucache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by DbL on 2020/4/5 0005
 */
public class LFUCache {
    private int cap; // 缓存总容量
    private int size; // 当前缓存使用量
    private HashMap<Integer, Node> cache;
    private HashMap<Integer, ArrayList<Node>> freqNodes;

    // 打印当前缓存内容 测试用
    public void printCache(){
        for (Map.Entry<Integer, Node> entry : cache.entrySet()){
            System.out.print(entry.getKey()+" ");
        }
        System.out.println();
    }
    public LFUCache(int capacity) {
        this.cap = capacity;
        this.cache = new HashMap<Integer, Node>(capacity);
        this.freqNodes = new HashMap<Integer, ArrayList<Node>>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        node.freq++;
        // 次数有变化，需要更新次数使用最少且最近调用时间靠后的元素
        adjustPosition(node);
        return node.value;
    }

    private void adjustPosition(Node node) {
        if (freqNodes.containsKey(node.freq - 1)) {
            freqNodes.get(node.freq - 1).remove(node);
            if (freqNodes.get(node.freq - 1).size() == 0) {
                freqNodes.remove(node.freq - 1);
            }
        }
        if (freqNodes.containsKey(node.freq)) {
            freqNodes.get(node.freq).add(node);
        } else {
            ArrayList<Node> newNodes = new ArrayList<Node>();
            newNodes.add(node);
            freqNodes.put(node.freq, newNodes);
        }

    }


    public int getMinReq() {

        int req  = 0;
        boolean flag = true;
        for (Map.Entry<Integer, ArrayList<Node>> entry : freqNodes.entrySet()) {
            if(flag){
                req = entry.getKey();
                flag = false;
            }else{
                req = Integer.min(req, entry.getKey());
            }
        }
        return req;
    }

    public void put(int key, int value) {
        // 容量为0无法存储
        if (cap == 0) return;
        // 判断是否已经存在
        Node node = cache.get(key);

        if (node == null) {
            // 不存在的情况，需要判断缓存是否已满，没有满，直接加入，若满了，需要找到使用次数最少最近调用时间最靠后的
            if (size == cap) {
                int remove = getMinReq();
                int k = freqNodes.get(remove).get(0).key;
                freqNodes.get(remove).remove(0);
                cache.remove(k);
                node = new Node(key,value);
                adjustPosition(node);
                cache.put(key, node);
            } else {
                Node newNode = new Node(key, value);
                // 插入前操作
                adjustPosition(newNode);
                cache.put(key, newNode);
                // 当前缓存大小增加
                size++;
            }

        } else {
            // 已存在的情况，更新调用次数，更新值
            node.value = value;
            node.freq++;
            // 次数有变化，需要更新次数使用最少且最近调用时间靠后的元素
            adjustPosition(node);
        }
    }


}

class Node {
    int key;
    int value;
    int freq = 0;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public Node() {

    }
}

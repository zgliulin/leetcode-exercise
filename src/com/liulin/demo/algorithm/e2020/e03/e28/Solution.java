package com.liulin.demo.algorithm.e2020.e03.e28;

import java.util.Arrays;

/**
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 *
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 *
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 *
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 *
 * 示例：
 *
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 *
 * 提示：
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 *
 * Create by DbL on 2020/3/28 0028
 */
public class Solution {
    public static void main(String[] args) {
        String[] strings = new String[]{"time","me","bell","la","ll"};
        System.out.println(minimumLengthEncoding(strings));
    }
    public static int minimumLengthEncoding(String[] words) {
        int len = 0;
        Trie trie = new Trie();
        // 先对单词列表根据单词长度由长到短排序
        // 排序的意义：比如示例中的["time", "me", "bell"]的逆序就是["emit", "em", "lleb"]。
        // 我们可以发现em是emit的前缀。所以"em"就可以忽略了。我们必须要先插入单词长的数组，否则会有问题。
        // 比如如果我先插入了"em"，再插入"emit",会发现两个都可以插入进去，很显然是不对的，所以在插入之前需要先根据单词的长度由长到短排序
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        // 单词插入trie，返回该单词增加的编码长度
        for (String word: words) {
            len += trie.insert(word);
        }
        return len;
    }
}
// 定义tire
class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public int insert(String word) {
        TrieNode cur = root;
        boolean isNew = false;
        // 因为题中要求的是某个单词为另外一个单词的后缀则不用计数，所以这里倒着插入单词
        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';
            if (cur.children[c] == null) {
                isNew = true; // 是新单词
                cur.children[c] = new TrieNode();
            }
            // 这里很重要，如果读取到这个节点，取出这个节点的子节点用于后面循环查找
            cur = cur.children[c];
        }
        // 如果是新单词的话编码长度增加新单词的长度+1（多的"#"号），否则不变。
        return isNew? word.length() + 1: 0;
    }
}

class TrieNode {
    char val;
    // 字母一共26个，所以定义长度为26
    TrieNode[] children = new TrieNode[26];

    public TrieNode() {}
}

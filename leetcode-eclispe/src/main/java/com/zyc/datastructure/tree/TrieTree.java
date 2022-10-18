package com.zyc.datastructure.tree;

/**
 * @author zyc
 * @date 2022/10/18
 */
public class TrieTree {

    public void add(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (p.child[idx] == null)
                p.child[idx] = new TrieNode();
            p = p.child[idx];
        }
        p.isWord = true;
        ++size;
    }

    public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = find(prefix);
        return node != null;
    }

    public void remove(String word) {
        TrieNode trieNode = find(word);
        if (trieNode != null) {
            trieNode.isWord = false;
            --size;
        }
    }

    private TrieNode find(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (p.child[idx] == null) return null;
            p = p.child[idx];
        }
        return p;
    }

    public int getSize() {
        return size;
    }

    private final TrieNode root;

    private int size;

    public TrieTree() {
        root = new TrieNode();
        size = 0;
    }

    private static class TrieNode {
        // 是否是某个单词的结束
        public boolean isWord;
        // 到下一个节点的映射
        public TrieNode[] child;

        public TrieNode() {
            this(false);
        }

        public TrieNode(boolean isWord) {
            this.isWord = isWord;
            child = new TrieNode[26];
        }
    }
}

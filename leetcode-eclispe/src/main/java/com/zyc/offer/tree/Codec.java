package com.zyc.offer.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 序列化与反序列化二叉树
 *
 * @author zyc
 * @date 2022/10/3
 */
public class Codec {


    /**
     * 序列化
     *
     * @param root 树根
     * @return 树结构字符串
     */
    public String serialize(TreeNode<Number> root) {
        StringBuilder builder = new StringBuilder();
        encode(root, builder);
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * 根据树结构字符串反序列化树
     *
     * @param data 树结构
     * @return 树根
     */
    public TreeNode<Number> deserialize(String data) {
        String[] strings = data.split(",");
        List<String> list = Arrays.asList(strings);
        return decode(new LinkedList<>(list));
    }

    private void encode(TreeNode<Number> root, StringBuilder builder) {
        if (root != null) {
            builder.append(root.getVal()).append(",");
            encode(root.left, builder);
            encode(root.right, builder);
        } else {
            builder.append("null,");
        }
    }

    private TreeNode<Number> decode(List<String> list) {
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        int val = Integer.parseInt(list.get(0));
        TreeNode<Number> root = new TreeNode<>(val);
        list.remove(0);
        root.left = decode(list);
        root.right = decode(list);
        return root;
    }

}

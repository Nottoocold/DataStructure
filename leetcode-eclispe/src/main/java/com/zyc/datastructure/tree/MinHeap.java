package com.zyc.datastructure.tree;

import com.zyc.datastructure.node.HuffmanNode;

import java.util.*;

/**
 * @author zyc
 * @date 2022/10/17
 */
public class MinHeap<E> {

    private final Queue<? super E> queue;

    @SuppressWarnings("unchecked")
    public MinHeap(int[] arr) {
        Collection<HuffmanNode> huffmanNodes = Arrays.stream(arr).mapToObj(HuffmanNode::new).toList();
        queue = (Queue<E>) new PriorityQueue<>(Comparator.comparingInt(HuffmanNode::getVal));
        queue.addAll((Collection<? extends E>) huffmanNodes);
    }

    @SuppressWarnings("unchecked")
    public E poll() {
        return (E) queue.poll();
    }

    public void add(E node) {
        queue.offer(node);
    }

    public void destroy() {
        queue.clear();
    }
}

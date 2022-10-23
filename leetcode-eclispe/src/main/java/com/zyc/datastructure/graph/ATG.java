package com.zyc.datastructure.graph;

import com.zyc.datastructure.list.linklist.LinkList;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 邻接表
 *
 * @author zyc
 * @date 2022/10/19
 */
public class ATG<V> implements Graph<V> {
    private final Set<V> nodes;
    private final Map<V, LinkList<V>> edges;
    private final boolean isDirection;

    public ATG(boolean isDirection) {
        nodes = new LinkedHashSet<>();
        edges = new LinkedHashMap<>();
        this.isDirection = isDirection;
    }

    public boolean isDirection() {
        return isDirection;
    }

    public void addNode(V vex) {
        if (nodes.contains(vex))
            return;
        nodes.add(vex);
        edges.computeIfAbsent(vex, v -> new LinkList<>());
    }

    public void addEdge(V v1, V v2) {
        addNode(v1);
        addNode(v2);
        if (!isDirection)
            edges.get(v2).addLastIfAbsent(v1);
        edges.get(v1).addLastIfAbsent(v2);
    }

    @SafeVarargs
    public final void addEdge(V v, V... es) {
        for (V u : es) {
            addEdge(v, u);
        }
    }

    /**
     * @param vex 顶点
     * @return 返回顶点vex的第一个邻接点
     */
    public V firstNeighbor(V vex) {
        LinkList<V> linkList = edges.get(vex);
        if (linkList != null && linkList.size() > 0)
            return linkList.getFirst();
        return null;
    }

    /**
     * @param vex 顶点已经存在于图中
     * @param u   顶点vex的一个邻接点
     * @return 返回除顶点u之外的下一个邻接点；若u已经是最后一个邻接点，则返回null
     */
    public V nextNeighbor(V vex, V u) {
        return edges.get(vex).nextVal(u);
    }

    public int nodeSize() {
        return nodes.size();
    }

    public int edgeSize() {
        AtomicInteger count = new AtomicInteger(0);
        edges.values().forEach(l -> count.addAndGet(l.size()));
        return count.get();
    }

    /**
     * @param vex 顶点
     * @return 顶点vex的度
     */
    public int degree(V vex) {
        if (isDirection) {
            return inDegree(vex) + outDegree(vex);
        }
        if (!edges.containsKey(vex))
            return 0;
        return edges.get(vex).size();
    }

    /**
     * @param vex
     * @return 顶点vex的入度
     */
    public int inDegree(V vex) {
        AtomicInteger count = new AtomicInteger(0);
        if (isDirection) {
            edges.forEach((n, es) -> {
                if (!n.equals(vex)) {
                    if (es.contain(vex))
                        count.incrementAndGet();
                }
            });
            return count.get();
        }
        return degree(vex);
    }

    /**
     * @param vex
     * @return 顶点vex的出度
     */
    public int outDegree(V vex) {
        int count = 0;
        if (isDirection) {
            if (edges.containsKey(vex))
                count = edges.get(vex).size();
            return count;
        }
        return degree(vex);
    }

    /**
     * 删除图中的顶点
     *
     * @param v 顶点
     */
    public void removeVex(V v) {
        if (!nodes.contains(v))
            return;
        edges.remove(v);
        edges.values()
                .forEach(list -> list.remove(v));
        nodes.remove(v);
    }

    public void removeEdge(V v1, V v2) {
        if (!containEdge(v1, v2))
            return;
        if (!isDirection) {
            edges.get(v2).remove(v1);
        }
        edges.get(v1).remove(v2);
    }

    public boolean containEdge(V v1, V v2) {
        if (!isDirection) {
            return edges.containsKey(v1) && edges.get(v1).contain(v2);
        }
        if (edges.containsKey(v1)) {
            LinkList<V> list = edges.get(v1);
            return list.size() > 0 && list.contain(v2);
        }
        return false;
    }

    /**
     * @return 返回图G的点集
     */
    @Override
    public Set<V> nodes() {
        return nodes;
    }

    /**
     * @return 返回图G的边集
     */
    @Override
    public Map<V, Set<V>> edges() {
        Map<V, Set<V>> ret = new LinkedHashMap<>();
        edges.forEach((v, es) -> ret.put(v, es.values()));
        return Collections.unmodifiableMap(ret);
    }

    /**
     * @param vertex 顶点
     * @return 返回与顶点vertex的所有邻接点
     */
    @Override
    public Set<V> adjacentNodes(V vertex) {
        return Collections.unmodifiableSet(edges.get(vertex).values());
    }

    /**
     * @param vertex 顶点
     * @return 返回图G是否包含顶点vertex
     */
    @Override
    public boolean containV(V vertex) {
        return nodes.contains(vertex);
    }

}

package com.zyc.datastructure.graph;

import java.util.*;

/**
 * 邻接矩阵存储结构的图
 *
 * @param <E> 定点类型
 * @author zyc
 * @date 2022/10/18
 */
public class AMG<E> implements Graph<E> {
    private final int maxVertexNum;
    private final List<E> Vex;
    private final boolean[][] Edge;

    private AMG(int maxVertexNum, List<E> vex, boolean[][] edge) {
        this.maxVertexNum = maxVertexNum;
        this.Vex = vex;
        this.Edge = edge;
    }

    @Override
    public Set<E> nodes() {
        Set<E> nodes = new HashSet<>(Vex);
        return Collections.unmodifiableSet(nodes);
    }

    @Override
    public Map<E, Set<E>> edges() {
        Map<E, Set<E>> map = new HashMap<>();
        for (E e : Vex) {
            Set<E> es = adjacentNodes(e);
            map.put(e, es);
        }
        return Collections.unmodifiableMap(map);
    }

    @Override
    public Set<E> adjacentNodes(E vertex) {
        Set<E> ads = new HashSet<>();
        int i = Vex.indexOf(vertex);
        for (int j = 0; j < Vex.size(); j++) {
            if (Edge[i][j]) {
                ads.add(Vex.get(j));
            }
        }
        return Collections.unmodifiableSet(ads);
    }

    @Override
    public boolean containV(E vertex) {
        return Vex.contains(vertex);
    }

    public int getMaxVertexNum() {
        return maxVertexNum;
    }

    static class Builder<E> {
        final List<E> vex = new ArrayList<>();
        boolean[][] edge;
        private int size;

        public Builder<E> size(int size) {
            edge = new boolean[size][size];
            this.size = size;
            return this;
        }

        public Builder<E> addNode(E v) {
            if (vex.size() >= size)
                return this;
            vex.add(v);
            return this;
        }

        public Builder<E> addEdge(E v1, E v2) {
            if (vex.contains(v1) && vex.contains(v2)) {
                int i = vex.indexOf(v1);
                int j = vex.indexOf(v2);
                edge[i][j] = true;
                edge[j][i] = true;
            } else if (!vex.contains(v1) && !vex.contains(v2)) {
                addNode(v1);
                addNode(v2);
                addEdge(v1, v2);
            } else {
                if (!vex.contains(v1)) {
                    addNode(v1);
                    addEdge(v1, v2);
                } else if (!vex.contains(v2)) {
                    addNode(v2);
                    addEdge(v1, v2);
                }
            }

            return this;
        }

        public int getSize() {
            return vex.size();
        }

        public Graph<E> build() {
            return new AMG<>(vex.size(), vex, edge);
        }
    }
}

package com.zyc.datastructure.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @param <V> 顶点类型
 * @author zyc
 * @date 2022/10/19
 */
public class GeneralGraph<V> implements Graph<V> {
    /**
     * 点集
     */
    private final Set<V> nodes;

    /**
     * 边集
     */
    private final Map<V, Set<V>> edges;

    private GeneralGraph(Set<V> nodes, Map<V, Set<V>> edges) {
        this.nodes = Collections.unmodifiableSet(nodes);
        this.edges = Collections.unmodifiableMap(edges);
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
        return edges;
    }

    /**
     * @param vertex 顶点
     * @return 返回与顶点vertex的所有邻接点
     */
    @Override
    public Set<V> adjacentNodes(V vertex) {
        return edges.get(vertex);
    }

    /**
     * @param vertex 顶点
     * @return 返回图G是否包含顶点vertex
     */
    @Override
    public boolean containV(V vertex) {
        return nodes.contains(vertex);
    }

    /**
     * @param v 顶点
     * @return 返回与指定顶点v相邻的边
     */
    public Set<Edge<V>> edgeSetFrom(V v) {
        return edges.get(v).stream()
                .map(u -> new Edge<>(v, u))
                .collect(Collectors.toSet());
    }

    /**
     * @param roots 起点集合
     * @return 返回从指定点出发遍历的结果
     */
    public Set<V> dfs(Set<V> roots) {
        Deque<V> deque = new ArrayDeque<>(roots);
        Set<V> visited = new LinkedHashSet<>();
        while (!deque.isEmpty()) {
            V v = deque.pop();
            if (!visited.contains(v)) {
                visited.add(v);
                if (containV(v)) {
                    adjacentNodes(v)
                            .stream()
                            .filter(u -> !visited.contains(u))
                            .forEach(deque::push);
                }
            }
        }
        return visited;
    }

    public void printGraph() {
        System.out.println(this);
        nodes.forEach(v1 -> {
            adjacentNodes(v1)
                    .forEach(v2 -> System.out.printf("%s->%s\t", v1, v2));
            System.out.println();
        });
    }

    @Override
    public String toString() {
        return "Nodes: " + nodes.toString();
    }

    public static <V> Builder<V> builder(Class<V> type) {
        return new Builder<>();
    }

    record Edge<V>(V v1, V v2) {

        @Override
        public String toString() {
            return String.format("%s->%s", v1, v2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            @SuppressWarnings("unchecked")
            Edge<V> edge = (Edge<V>) o;

            if (!Objects.equals(v1, edge.v1)) return false;
            return Objects.equals(v2, edge.v2);
        }

        @Override
        public int hashCode() {
            int result = v1 != null ? v1.hashCode() : 0;
            result = 31 * result + (v2 != null ? v2.hashCode() : 0);
            return result;
        }
    }

    static class Builder<V> {
        final Set<V> nodes = new LinkedHashSet<>();
        final Map<V, Set<V>> edges = new LinkedHashMap<>();

        public Builder<V> addNode(V node) {
            if (nodes.contains(node)) {
                return this;
            }
            nodes.add(node);
            edges.computeIfAbsent(node, v -> new HashSet<>());
            return this;
        }

        public Builder<V> addNodes(Set<V> nodes) {
            this.nodes.addAll(nodes);
            return this;
        }

        public Builder<V> addEdge(V u, V v) {
            addNode(u);
            addNode(v);
            edges.get(u).add(v);
            return this;
        }

        public Graph<V> build() {
            return new GeneralGraph<>(nodes, edges);
        }
    }
}

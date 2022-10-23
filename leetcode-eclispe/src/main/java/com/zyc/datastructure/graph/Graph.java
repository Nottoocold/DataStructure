package com.zyc.datastructure.graph;

import java.util.Map;
import java.util.Set;

/**
 * @param <V> 顶点的数据类型
 * @author zyc
 * @date 2022/10/18
 */
public interface Graph<V> {

    /**
     * @return 返回图G的点集
     */
    Set<V> nodes();

    /**
     * @return 返回图G的边集
     */
    Map<V, Set<V>> edges();

    /**
     * @param vertex 顶点
     * @return 返回与顶点vertex的所有邻接点
     */
    Set<V> adjacentNodes(V vertex);

    /**
     * @param vertex 顶点
     * @return 返回图G是否包含顶点vertex
     */
    boolean containV(V vertex);


}

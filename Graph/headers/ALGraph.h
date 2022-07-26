/*图的邻接表储存方式
 *无向图空间复杂度O(V+2E)
  有向图空间复杂度O(V+E)
 * */
#ifndef _ALGRAPH_H_
#define _ALGRAPH_H_

#define MaxVertexNum 100
typedef struct ArcNode {
    int adjvex;//该边指向顶点的位置
    struct ArcNode* next;
    //InfoType info //用来存储边权值
}ArcNode;//图的边集

typedef int VertexType;
typedef struct VNode {
    VertexType data;//顶点信息
    ArcNode* first;//第一个依附于该点的边的指针
}VNode,AdjList;//图的点集

typedef struct {
    AdjList vertices[MaxVertexNum];
    int vexnum, arcnum;//顶点个数，边个数
}ALGraph;//邻接表，适合存储稀疏图

#endif

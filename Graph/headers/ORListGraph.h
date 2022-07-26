/*有向图的十字链表存储法,空间复杂度O(V+E)*/
#ifndef _ORLISTGRAPH_H_
#define _ORLISTGRAPH_H_
#define MaxVertexNum 100

typedef struct ArcNode{
    int tailvex;//弧尾节点编号
    int headvex;//弧头节点编号
    struct ArcNode* hlink;//弧头相同的下一条弧
    struct ArcNode* tlink;//弧尾相同的下一条弧
    //InfoType info //弧权值
}ArcNode;

typedef char VertexType;

typedef struct Vertex {
    VertexType data;//顶点数据
    ArcNode* firstin;//该顶点作为弧头的第一条弧
    ArcNode* firstout;//该顶点作为弧尾的第一条弧
}VerNode,ORLGraph;

typedef struct {
    ORLGraph AdjList[MaxVertexNum];
    int vernum, arcnum;
}G;
#endif

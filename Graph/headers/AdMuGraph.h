/*邻接多重表存储无向图,空间复杂度O(V+E)*/
#ifndef _ADMUGRAPH_H_
#define _ADMUGRAPH_H_

#define MaxVertexNum 100
typedef enum {
    false;
    true;
}flag;

/*
 * ivex,jvex分别为该边所依附的两个顶点的编号
 * ilink,jlink分别为依附于顶点ivex的下一条边，依附于顶点jvex的下一条边
 * mask 为标记字段
 */
typedef struct ArcNode {
    int ivex;
    int jvex;
    struct ArcNode* ilink;
    struct ArcNode* jlink;
    flag mask;
    //Info
}ArcNode;

typedef char VexType;

typedef struct VexNode {
    VexType data;
    ArcNode* firstedge;//与该顶点相连的第一条边
}VexNode,AdMuList;

typedef struct {
    AdMuList G[MaxVextexNum];
    int vexnum, arcnum;
}AdMuGraph;

#endif

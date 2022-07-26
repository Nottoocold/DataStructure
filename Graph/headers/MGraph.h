/*图的邻接矩阵存储法，时间复杂度O(n)，空间复杂度O(n2)*/
#ifndef _MGRAPH_H_
#define _MGRAPH_H_

#define MaxVertexNum 100 // 图的最大定点数目
typedef char VertexType //顶点的数据类型

enum tag{
    false;//无边
    true;//有边
};

typedef enum tag EdgeType;//当矩阵元素仅表示相应边是否存在时,EdgeType可采用值为0或1的枚举类型

typedef struct {
    VertexType Vex[MaxVertexNum];//顶点表
    EdgeType Edge[MaxVertexNum][MaxVertexNum];//边矩阵
    int vexnum, arcnum;//图的当前顶点数和边的数目
}MGraph;//邻接矩阵表示法,稠密图适合用此方式存储

/*
 *求图G中顶点x的第一个邻接点,若有则返回顶点号;若x没有邻接点或图中没有x则返回-1
 * */
int FirstNeighbor(MGrapg G, VertexType x);

/*
 * y是x的一个邻接点,返回除了y之外顶点x的下一个邻接点,若y是x的最后一个邻接点则返回-1
 * */
int NextNeighbor(MGrapg G, VertexType x, VertexType y);

/*因为无向图的邻接矩阵一定是对称矩阵，所以存储时可以使用矩阵的压缩存储来节省空间*/
#endif

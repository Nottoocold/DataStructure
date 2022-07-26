#include "headers/MGraph.h"

int FirstNeighbor(MGraph G, VertexType x){
    int i = 0;
    while(i < G.vexnum){
        if(G.vex[i] == x)
            break;
        ++i;
    }
    if(i >= G.vexnum)//图G中没有x
        return -1;
    int j = 0;
    while(j < G.vexnum){//找第一个邻接点
        if(G.Edge[i][j] == true)
            break;
        ++j;
    }
    if(j > G.vexnum)//图G中没有x的邻接点
        return -1;
    return j;
}

int NextNeighbor(MGraph G, VertexType x, VertexType y){
    int i = 0, j = 0;
    while(i < G.vexnum){
        if(G.vex[i] == x)
            break;
        else{
            if(G.vex[i] == y)
                j = i+1;//记录y之后的顶点
            ++i;
        }
    }
    while(j < G.vexnum){
        if(G.Edge[i][j] == true)
            break;
        ++j;
    }
    if(j >= G.vexnum)//当y是最后一个邻接点时,上面的while不会执行;当y不是最后一个顶点时,若循环结束j下标越界
       return -1;
    return j;
}

/*
 * 单源最短路径问题(无权图),BFS解法.伪代码实现
 * @param u 源点
 */
void BFS_MIN_PATH(Graph G, int u){
    //d[i]表示源点u到节点i的最短路径
    for(int i = 0; i < G.vexnum; ++i){
        d[i] = INT_MAX;
        path[i] = -1;//表示节点i是由哪个节点过来的
    }
    d[u] = 0;
    visited[u] = true;//标记数组,标识节点已经被访问
    EnQueue(Q,u);
    while(!isEmpty(Q)){
        DeQueue(Q,u);//出队
        for(VexType w = FirstNeighbor(G,u); w >= 0; w = NextNeighbor(G,u,w)){
            if(!visited[w]){
                d[w] = d[u] + 1;//路径+1
                path[w] = u;//记录w是从u过来的
                visited[w] = true;
                EnQueue(Q,w);
            }
        }
    }//while
    //d[] 数组中记录了从源点u到各个顶点的最短路径长度
}

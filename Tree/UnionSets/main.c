#include <stdio.h>

#define SIZE 16
int UFSets[SIZE];//并查集数组
//规定根节点中保存集合中元素个数的相反数
void Init(int S[])
{
	for (int i = 0; i < SIZE; ++i)
	{
		S[i] = -1;
	}
}
//在S中查找并返回包含元素 i 的树的根
int Find(int S[], int i)
{
	int root = i;
	while (S[root] >= 0)//向上找双亲
		root = S[root];
	while (i != root)//优化，将搜索路径上的孩子节点都直接挂在根节点下
	{
		int tmp = S[i];
		S[i] = root;
		i = tmp;
	}
	return root;
}
//合并两集合
void Union(int S[], int n1, int n2)
{
	int root1 = Find(UFSets, n1);
	int root2 = Find(UFSets, n2);
	if (root1 == root2)//是同一个集合
		return;
	if (S[root2] > S[root1])//root2是小集合，小并大
	{
		S[root1] += S[root2];
		S[root2] = root1;
	}
	else
	{
		S[root2] += S[root1];
		S[root1] = root2;
	}
}

int main()
{
	Init(UFSets);
	Union(UFSets, 0, 3);
	Union(UFSets, 3, 4);
	Union(UFSets, 3, 8);
	Union(UFSets, 3, 9);
	Union(UFSets, 4, 10);

	Union(UFSets, 1, 5);
	Union(UFSets, 5, 6);
	Union(UFSets, 5, 11);
	Union(UFSets, 5, 12);
	Union(UFSets, 6, 13);
	Union(UFSets, 6, 14);

	Union(UFSets, 7, 2);
	Union(UFSets, 15, 2);

	Union(UFSets, 8, 6);
	Union(UFSets, 8, 2);
	
	for(int i = 0 ; i < 16; ++i)
	{
		printf("UFSets i is %d, UFSets[i] is %d\n",i,UFSets[i]);
	}
	return 0;
}

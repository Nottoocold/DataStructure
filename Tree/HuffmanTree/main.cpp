#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <vector>
#include <limits.h>
using std::vector;

typedef struct HTNode
{
	unsigned int weight;
	unsigned int parent, lchild, rchild;
}HTNode,*HuffmanTree;

typedef char** HuffmanCode;
unsigned int count = 0;//计数Select函数调用次数

unsigned int FindKey(vector<unsigned int> nums, unsigned int key)
{
	int len = nums.size();
	for (int i = 0; i < len; ++i)
	{
		if (nums.at(i) == key) return i;
	}
	return -1;
}

void Select(HuffmanTree& HT, unsigned int index, unsigned int* node1_idx, unsigned int* node2_idx)
{
	//每调用一次函数，会去掉两个孩子节点
	vector<unsigned int> nums_i(index - 2 * count);//存放双亲为0的节点的位序
	vector<unsigned int> nums_w(index - 2 * count);//存放双亲为0的节点的权值
	int a = 0;
	//在HT[1...index]中查找双亲为0且权值最小的两个节点 node1 node2
	for (unsigned int i = 1; i <= index; ++i)
	{
		if (!HT[i].parent)
		{
			nums_i[a] = i;
			nums_w[a] = HT[i].weight;
			a++;
		}
	}
	unsigned int min1 = UINT_MAX;//最小权值
	unsigned int min2 = UINT_MAX;//第二小的权值
	for (unsigned int j = 0; j < nums_w.size(); ++j)//
	{
		if (nums_w[j] < min1)
		{
			min2 = min1;
			if (min2 != UINT_MAX)
			{
				*node2_idx = nums_i[FindKey(nums_w, min1)];//记录位序
			}
			min1 = nums_w[j];
			*node1_idx = nums_i[j];
		}
		else if (nums_w[j] < min2)
		{
			min2 = nums_w[j];
			*node2_idx = nums_i[j];
		}
	}
	count++;
}

void HuffmanCoding(HuffmanTree& HT, HuffmanCode& HC, unsigned int* w, int n)
{
	//w为权值字符串基址，共n个字符
	if (n <= 1) return;
	//n个字符，即n个叶子节点，需要合并n-1次，则哈夫曼树共有2n-1个节点
	int HTNode_NUM = 2 * n - 1;
	HT = (HuffmanTree)malloc(sizeof(HTNode) * (HTNode_NUM + 1));//0号位置不使用
	assert(HT);
	unsigned int i = 1;
	HuffmanTree p = &HT[1];
	for (; i <= n; ++i, ++p, ++w)//初始化叶节点赋权值 1~n
	{
		*p = { *w,0,0,0 };
	}
	for (; i <= HTNode_NUM; ++i, ++p)//初始化父节点   n+1 ~ 2n-1
	{
		*p = { 0,0,0,0 };
	}
	for (i = n + 1; i <= HTNode_NUM; ++i)//构造哈弗曼树
	{
		unsigned int node1 = 0, node2 = 0;//两最小权值节点的位序
		Select(HT, i - 1, &node1, &node2);
		HT[node1].parent = i;
		HT[node2].parent = i;
		HT[i].lchild = node1;
		HT[i].rchild = node2;
		HT[i].weight = HT[node1].weight + HT[node2].weight;
	}

	//-----从叶子到根节点逆向求每个字符的哈夫曼编码-----
	HC = (HuffmanCode)malloc(sizeof(char*) * (n + 1));//n个字符编码的头指针
	char* tmp = (char*)malloc(sizeof(char) * n);//分配求编码的工作空间
	assert(tmp);
	tmp[n - 1] = '\0';
	for (int i = 1; i <= n; ++i)
	{
		int start = n - 1;
		for (int c = i, f = HT[i].parent; f != 0; c = f, f = HT[f].parent)
		{
			if (HT[f].lchild == c)
			{
				tmp[--start] = '0';
			}
			else
			{
				tmp[--start] = '1';
			}
		}
		HC[i] = (char*)malloc(sizeof(char) * (n - start));
		assert(HC[i]);
		strcpy(HC[i], &tmp[start]);
	}
	free(tmp);
	tmp = nullptr;
}

int main()
{
	HuffmanTree HT;
	HuffmanCode HC;
	unsigned int w[] = { 5,29,7,8,14,23,3,11 };
	HuffmanCoding(HT, HC, w, 8);
	int i = 1;
	while (i <= 15)
	{
		printf("HT[%d] info: weigth->%d,parent->%d,lchild->%d,rchild->%d\n", i, HT[i].weight, HT[i].parent, HT[i].lchild, HT[i].rchild);
		i++;
	}
	i = 1;
	for (unsigned int a : w)
	{
		printf("%u HuffmanCoding is %s\n", a, HC[i]);
		++i;
	}//WPL=271
	return 0;
}
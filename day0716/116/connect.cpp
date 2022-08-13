#include <iostream>
#include <queue>
using namespace std;
/*
	给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点.
	填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
	节点数目在 [0, 2^12 - 1]
*/
typedef struct Node
{
	int val;
	struct Node* left;
	struct Node* right;
	struct Node* next;//右指针，指向相邻的右节点，若没有相邻的右节点则置为NULL
}Node;

Node* connect(Node* root)
{
	if(!root)
		return NULL;
	queue<Node*> Q;
	Q.push(root);
	while(!Q.empty())
	{
		int size = Q.size();//获取队列中元素个数
		for(int i = 1; i <= size; ++i)//遍历该层所有节点
		{
			Node* p = Q.front();
			Q.pop();
			if(i <= size - 1)
				p->next = Q.front();
			if(p->left != NULL)
				Q.push(p->left);
			if(p->right != NULL)
				Q.push(p->right);
		}
	}
	return root;
}

int main()
{
	return 0;
}

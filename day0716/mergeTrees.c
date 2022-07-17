#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode
{
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
}TreeNode;
/*
    给定两颗二叉树，合并他们,
    合并规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
    返回新的根节点
*/
//在root1的基础上修改，返回的新二叉树的根节点就是root1
struct TreeNode* DFS(struct TreeNode* root1,struct TreeNode* root2)
{
    if(!root1)//当前root1节点为NULL
        return root2;
    if(!root2)//当前root2节点为NULL
        return root1;
    root1->val += root2->val;//合并
    root1->left = DFS(root1->left,root2->left);//递归合并左子树
    root1->right = DFS(root1->right,root2->right);//递归合并右子树
    return root1;
}

struct TreeNode* mergeTrees(struct TreeNode* root1, struct TreeNode* root2)
{
    return DFS(root1,root2);
}


int main()
{

    return 0;
}
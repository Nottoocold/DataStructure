#include <stdio.h>
#include <stdlib.h>
typedef int ElemType;
typedef struct BSTNode{
    ElemType key;
    struct BSTNode* left, *right;
}BSTNode,*BSTree;

void InOrderBST(BSTree root){
    if(root){
        InOrderBST(root->left);
        printf("%d ",root->key);
        InOrderBST(root->right);
    }
}

/*查找,时间复杂度O(logN),*/
//查找节点
BSTNode* BST_Search(BSTree T, ElemType key){
    if(T == NULL){//查找失败
        return NULL;//NULL
    }
    if(T->key == key)
        return T;//查找成功
    else if(T->key > key)
        return BST_Search(T->left,key);//递归左子树
    else
        return BST_Search(T->right,key);
}

//插入节点
int BST_Insert(BSTree &T, ElemType key){
    if(T == NULL){
        T = (BSTNode*)calloc(1,sizeof(BSTNode));
        (T)->key = key;
        return 1;
    }
    else if(T->key == key)
        return 0;//重复值
    else if(T->key > key)
        return BST_Insert(T->left,key);
    else
        return BST_Insert(T->right,key);
}

/* 删除节点,若被删除节点存在左右子树,两种方案,设需要删除的节点为X,
 * 1.使用X节点的直接前驱替代X的位置,即X节点的左子树的中序序列下最后一个被访问的节点,然后删除前驱节点
 * 2.使用X节点的直接后继替代X的位置,即X节点的右子树的中序序列下第一个被访问的节点,然后删除后继节点
 * 其余情况,
 * 1.X只有左子树，左子树替代
 * 2.X只有右子树，右子树替代
 * 3.X时叶子节点，直接删除
 */
int DeleteNode(BSTNode* &p){
    if(!p->right){//右子树空树
      BSTNode* q = p->left;
      p->key= q->key;
      p->left = q->left;
      p->right = q->right;
      free(q);
    }else if(!p->left){//左子树空树
      BSTNode* q = p->right;
      p->key= q->key;
      p->left = q->left;
      p->right = q->right;
      free(q);
  }else{//左右子树都不是空树
    BSTNode* pre = p, *s = p->left;
    while(s->right){  pre = s; s = s->right;}//找p的直接前驱节点s
    p->key = s->key;
    if(pre != p)
      pre->right = s->left;
    else//此时说明直接前驱s就是p的左孩子
      pre->left = s->left;
    free(s);
  }
  return 1;
}

int BST_Delete(BSTree* T, ElemType key){
    //先查找key
    BSTNode* p = BST_Search(*T,key);
    if(!p)//查找失败p=NULL
      return 0;
    else
      return DeleteNode(p);
}

int main(int argc, char const *argv[]) {
  BSTree root = NULL;
  int nums[] = {6, 3, 9, 1, 2, 5 , 7, 4, 10};
  unsigned int i = 0;
  while(i < (sizeof(nums) / sizeof(nums[0])))
    BST_Insert(root,nums[i++]);
  InOrderBST(root);
  printf("\n");
  printf("%d\n", BST_Search(root,4)->key);
  printf("BST_Delete(1) %d\n", BST_Delete(&root,1));
  printf("BST_Delete(3) %d\n", BST_Delete(&root,3));
  printf("BST_Delete(9) %d\n", BST_Delete(&root,9));
  InOrderBST(root);
  return 0;
}

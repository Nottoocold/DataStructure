#ifndef __THREAD_TREE__
#define __THREAD_TREE__
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

enum tag
{
	zero,
	one
};

typedef char ElementType;

typedef struct ThreadNode
{
	ElementType data;
	struct ThreadNode *lchild, *rchild;
	enum tag ltag, rtag;
} ThreadNode, *ThreadTree;

void CreateThreadTree_ByLevel(ThreadTree *root, ElementType *base); // create a tree

void Visit(ThreadNode *_Node_);
void Thread_Visit(ThreadNode *_Node_);

void InThread(ThreadTree *_Cur_Node_);
void _In_CreateInThread(ThreadTree *T);
ThreadNode *In_FirstNode(ThreadNode *_Node_);
ThreadNode *In_LastNode(ThreadNode *_Node_);
ThreadNode *In_NextNode(ThreadNode *_Node_);
ThreadNode *In_PreNode(ThreadNode *_Node_);
void InOrderThreadTree(ThreadTree root);
void RevInOrderThreadTree(ThreadTree root);

void PreThread(ThreadTree *_Cur_Node_);
void _Pre_CreatePreThread(ThreadTree *T);
ThreadNode *Pre_NextNode(ThreadNode *_Node_);
ThreadNode *Pre_PreNode(ThreadNode *_Node_); //

void PostThread(ThreadTree *_Cur_Node_);
void _Post_CreatePostThread(ThreadTree *T);
ThreadNode *Post_NextNode(ThreadNode *_Node_); //
ThreadNode *Post_PreNode(ThreadNode *_Node_);

#endif // !__THREAD_TREE__

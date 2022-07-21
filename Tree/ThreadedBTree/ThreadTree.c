#include "ThreadTree.h"
#include "SQueue.h"

ThreadNode* _pre_ = NULL;

void CreateThreadTree_ByLevel(ThreadTree* root, ElementType* base)
{
	ThreadNode* T;
	LinkQueue Q;
	LinkNode* cur = NULL;
	char* p = base;
	InitQueue(&Q);
	while (*p)
	{
		T = (ThreadNode*)calloc(1, sizeof(ThreadNode));
		T->data = *p;
		if (!(*root))
		{
			EnQueue(&Q, T);
			cur = Q.front->next;
			*root = T;
			p++;
			continue;
		}
		else
		{
			EnQueue(&Q, T);
		}
		if (cur->data->lchild == NULL)
		{
			cur->data->lchild = T;
		}
		else if (cur->data->rchild == NULL)
		{
			cur->data->rchild = T;
			cur = cur->next;
		}
		p++;
	}
}

void Visit(ThreadNode* _Node_)
{
	if (_Node_)
	{
		putchar(_Node_->data);
	}
}

void Thread_Visit(ThreadNode* _Node_)
{
	if (_Node_->lchild == NULL)//线索前驱
	{
		_Node_->lchild = _pre_;
		_Node_->ltag = one;
	}
	if (_pre_ != NULL && _pre_->rchild == NULL)//线索后继
	{
		_pre_->rchild = _Node_;
		_pre_->rtag = one;
	}
	_pre_ = _Node_;
}


void InThread(ThreadTree* _Cur_Node_)
{
	if (*_Cur_Node_ != NULL)
	{
		InThread(&((*_Cur_Node_)->lchild));
		Thread_Visit(*_Cur_Node_);
		InThread(&((*_Cur_Node_)->rchild));
	}
}
//中序线索化二叉树
void _In_CreateInThread(ThreadTree* T)
{
	if (*T != NULL)
	{
		InThread(T);
		_pre_->rchild = NULL;
		_pre_->rtag = one;
	}
}


ThreadNode* In_FirstNode(ThreadNode* _Node_)
{
	while (!_Node_->ltag)
	{
		_Node_ = _Node_->lchild;
	}
	return _Node_;
}

ThreadNode* In_LastNode(ThreadNode* _Node_)
{
	while (!_Node_->rtag)
	{
		_Node_ = _Node_->rchild;
	}
	return _Node_;
}

ThreadNode* In_NextNode(ThreadNode* _Node_)
{
	if (!_Node_->rtag)
	{
		return In_FirstNode(_Node_->rchild);
	}
	return _Node_->rchild;
}

ThreadNode* In_PreNode(ThreadNode* _Node_)
{
	if (!_Node_->ltag)
	{
		return In_LastNode(_Node_->lchild);
	}
	return _Node_->lchild;
}

void PreThread(ThreadTree* _Cur_Node_)
{
	if (*_Cur_Node_ != NULL)
	{
		Thread_Visit(*_Cur_Node_);
		if ((*_Cur_Node_)->ltag == zero)
		{
			PreThread(&((*_Cur_Node_)->lchild));
		}
		if ((*_Cur_Node_)->rtag == zero)
		{
			PreThread(&((*_Cur_Node_)->rchild));
		}
	}
}
//
void _Pre_CreatePreThread(ThreadTree* T)
{
	if (*T != NULL)
	{
		PreThread(T);
		if (_pre_->rchild == NULL)
		{
			_pre_->rtag = one;
		}
	}
}


ThreadNode* Pre_NextNode(ThreadNode* _Node_)
{
	if (!_Node_->rtag)
	{
		if (_Node_->lchild)
		{
			return _Node_->lchild;
		}	
		return _Node_->rchild;
	}
	return _Node_->rchild;
}


ThreadNode* Pre_PreNode(ThreadNode* _Node_)
{
	if (!_Node_->ltag)
	{
		return NULL;
	}
	return _Node_->lchild;
}


void PostThread(ThreadTree* _Cur_Node_)
{
	if (*_Cur_Node_ != NULL)
	{
		PostThread(&((*_Cur_Node_)->lchild));
		PostThread(&((*_Cur_Node_)->rchild));
		Thread_Visit(*_Cur_Node_);
	}
}
//
void _Post_CreatePostThread(ThreadTree* T)
{
	if (*T != NULL)
	{
		PostThread(T);
		if (_pre_->rchild == NULL)
		{
			_pre_->rtag = one;
		}
	}
}


ThreadNode* Post_NextNode(ThreadNode* _Node_)
{
	if (!_Node_->rtag)
	{
		return NULL;
	}
	return _Node_->rchild;
}


ThreadNode* Post_PreNode(ThreadNode* _Node_)
{
	if (!_Node_->ltag)
	{
		if (_Node_->rchild)
		{
			return _Node_->rchild;
		}
		return _Node_->lchild;
	}
	return _Node_->lchild;
}


void InOrderThreadTree(ThreadTree root)
{
	assert(root);
	for (ThreadNode* p = In_FirstNode(root); p != NULL; p = In_NextNode(p))
	{
		Visit(p);
	}
}


void RevInOrderThreadTree(ThreadTree root)
{
	assert(root);
	for (ThreadNode* p = In_LastNode(root); p != NULL; p = In_PreNode(p))
	{
		Visit(p);
	}
}

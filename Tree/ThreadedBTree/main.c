#include "ThreadTree.h"

int main()
{
	ThreadTree root = NULL;
	char ch[] = "ABCDE";
	CreateThreadTree_ByLevel(&root, ch);
	_In_CreateInThread(&root);
	//_Pre_CreatePreThread(&root);
	//_Post_CreatePostThread(&root);
	InOrderThreadTree(root);
	printf("\n");
	RevInOrderThreadTree(root);
	return 0;
}

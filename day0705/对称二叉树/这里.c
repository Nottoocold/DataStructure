struct TreeNode {
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
};

int check(struct TreeNode* p1, struct TreeNode* p2)
{
	if (!p1 && !p2)//都是空树
	{
		return 1;
	}
	if (!p1 || !p2)//一个空树，一个不是
	{
		return 0;
	}
	return p1->val == p2->val && check(p1->left, p2->right) && check(p1->right, p2->left);
}

int isSymmetric1(struct TreeNode* root) {
	return check(root, root);
}

int main()
{
	
	return 0;
}
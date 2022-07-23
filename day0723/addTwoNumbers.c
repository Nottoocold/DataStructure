#include <stdio.h>
#include <stdlib.h>

struct ListNode{
	int val;
	struct ListNode* next;
};

/*
 给定两个非空链表，表示两个非负整数。每位数字逆序存储，讲两个数相加并以相同的方式返回一个链表。
 */
struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
	struct ListNode* head = (struct ListNode*)calloc(1,sizeof(struct ListNode));
	struct ListNode* tail = head;
	struct ListNode* p = l1;
	struct ListNode* q = l2;
	int add = 0;//进位标志
	while(p || q || add){//条件
		int a = p?p->val:0;//若p q指针为空指针则置0
		int b = q?q->val:0;
		int temp = a + b + add;
		int ret = temp % 10;//本位和
		add = temp / 10;//新的进位
		struct ListNode* node = (struct ListNode*)calloc(1,sizeof(struct ListNode));
		node->val = ret;
		tail->next = node;
		tail = node;
		if(p)
			p = p->next;
		if(q)
			q = q->next;
	}
	struct ListNode* result = head->next;
	free(head);
	return result;
}


int main(){
	struct ListNode* l1 = NULL;
	struct ListNode* l2 = NULL;
	struct ListNode* p = NULL;
	for(int i = 1; i <= 6; ++i)
	{
		if(i >= 1 && i <= 3)
		{
			if(!l1)
			{
				l1 = (struct ListNode*)calloc(1,sizeof(struct ListNode));
				l1->val = i*2;
				p = l1;
			}
			else
			{
				struct ListNode* n = (struct ListNode*)calloc(1,sizeof(struct ListNode));
				n->val = i*2;
				p->next = n;
				p = n;
			}
		}
		else
		{
			
			if(!l2)
			{
				l2 = (struct ListNode*)calloc(1,sizeof(struct ListNode));
				l2->val = i+1;
				p = l2;
			}
			else
			{
				struct ListNode* n = (struct ListNode*)calloc(1,sizeof(struct ListNode));
				n->val = i+1;
				p->next = n;
				p = n;
			}
		}
	}
	struct ListNode* ret = addTwoNumbers(l1,l2);
	p = ret;
	while(p)
	{
		printf("%d ",p->val);
		p = p->next;
	}
	return 0;
}


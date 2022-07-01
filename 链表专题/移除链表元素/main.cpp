/*
����һ�������ͷ�ڵ� head ��һ������ val ������ɾ���������������� Node.val == val �Ľڵ㣬������ �µ�ͷ�ڵ� ��

���һ������ͷ��㣬ɾ��ͷ���Ͳ�����������.
*/

#include <stdio.h>
#include <stdlib.h>

//* Definition for singly-linked list.
struct ListNode {
      int val;
      struct ListNode *next;
};

struct ListNode* removeElements(struct ListNode* head, int val) {
    if (!head) return NULL;
    struct ListNode* a = (struct ListNode*)malloc(sizeof(struct ListNode));
    struct ListNode* pre = NULL;
    struct ListNode* cur = head;
    struct ListNode* p = NULL;
    if (a)
    {
        a->next = head;
        pre = a;
    }
    while (cur) {
        if (cur->val == val) {
            p = cur;
            pre->next = cur->next;
            cur = cur->next;
            free(p);
            p = NULL;
        }
        else {
            pre = cur;
            cur = cur->next;
        }
    }
    if (a->next == NULL) {
        free(a);
        a = NULL;
        return NULL;
    }
    struct ListNode* HEAD = a->next;
    free(a);
    a = NULL;
    return HEAD;
}

int main()
{
    return 0;
}
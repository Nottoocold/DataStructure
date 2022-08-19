/*
给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。

添加一个虚拟头结点，删除头结点就不用另做考虑.
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
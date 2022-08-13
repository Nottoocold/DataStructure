/*
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
*/
#include <stdio.h>
#include <stdlib.h>

//Definition for singly-linked list.
struct ListNode {
     int val;
    struct ListNode *next;
};


struct ListNode* reverseList(struct ListNode* head) {
    if (head == NULL || head->next == NULL)//头节点为空 或者 只有一个节点的链表
    {
        return head;
    }
    struct ListNode* pre, * cur, * next;
    pre = NULL;
    cur = head;
    next = NULL;
    while (cur != NULL)
    {
        next = cur->next;//拿去当前节点的下一个节点的地址
        cur->next = pre;//修改指针
        pre = cur;
        cur = next;
    }
    return pre;
}

int main()
{
    return 0;
}

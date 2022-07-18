#include <stdio.h>
#include <stdlib.h>

 struct ListNode {
     int val;
     struct ListNode *next;
 };
 /*
    将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
 */
struct ListNode* mergeTwoLists(struct ListNode* list1, struct ListNode* list2) {
    struct ListNode* head, * tail = NULL;
    head = (struct ListNode*)malloc(sizeof(struct ListNode));//申请新链表的头结点
    if (head)
    {
        tail = head;
    }
    while (list1 && list2)//遍历两个链表，每次将较小者放入新链表中
    {
        if (list1->val <= list2->val)
        {
            tail->next = list1;
            tail = list1;
            list1 = list1->next;
        }
        else
        {
            tail->next = list2;
            tail = list2;
            list2 = list2->next;
        }
    }
    tail->next = (list1 == NULL ? list2 : list1);
    struct ListNode* HEAD = head->next;
    free(head);
    head = NULL;
    return HEAD;
}

int main()
{
    return 0;
}
#include <stdio.h>
#include <stdlib.h>

//Definition for singly-linked list.
struct ListNode {
     int val;
    struct ListNode *next;
};

/*
    给定一个链表头，反转此链表，并返回反转后的链表头
*/
struct ListNode* reverseList(struct ListNode* head) {
    if (head == NULL || head->next == NULL)//空指针或只有一个节点
    {
        return head;
    }
    struct ListNode* pre = NULL, * cur = head, * next = NULL;
    while (cur != NULL)
    {
        next = cur->next;//保存后继节点地址
        cur->next = pre;//修改当前节点的前驱节点
        pre = cur;
        cur = next;
    }
    return pre;
}

int main()
{
    return 0;
}

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
    if (head == NULL)//如果链表空返回NULL
    {
        return head;
    }
    struct ListNode* headNode = (ListNode*)malloc(sizeof(struct ListNode));//申请虚拟头
    headNode->next = head;
    struct ListNode* pre = headNode;//当前需要比较值的节点的前一个节点
    while (pre->next != NULL)
    {
        if (pre->next->val == val)
        {
            pre->next = pre->next->next;
        }
        else
        {
            pre = pre->next;
        }
    }
    return headNode->next;
}

int main()
{
    return 0;
}
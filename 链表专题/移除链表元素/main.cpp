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
    if (head == NULL)//�������շ���NULL
    {
        return head;
    }
    struct ListNode* headNode = (ListNode*)malloc(sizeof(struct ListNode));//��������ͷ
    headNode->next = head;
    struct ListNode* pre = headNode;//��ǰ��Ҫ�Ƚ�ֵ�Ľڵ��ǰһ���ڵ�
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
/*
���㵥�����ͷ�ڵ� head �����㷴ת���������ط�ת�������
*/
#include <stdio.h>
#include <stdlib.h>

//Definition for singly-linked list.
struct ListNode {
     int val;
    struct ListNode *next;
};


struct ListNode* reverseList(struct ListNode* head) {
    if (head == NULL || head->next == NULL)//ͷ�ڵ�Ϊ�� ���� ֻ��һ���ڵ������
    {
        return head;
    }
    struct ListNode* pre, * cur, * next;
    pre = NULL;
    cur = head;
    next = NULL;
    while (cur != NULL)
    {
        next = cur->next;//��ȥ��ǰ�ڵ����һ���ڵ�ĵ�ַ
        cur->next = pre;//�޸�ָ��
        pre = cur;
        cur = next;
    }
    return pre;
}

int main()
{
    return 0;
}

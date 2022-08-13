/*
���Ƕ�������ָ�룬һ��һ������ָ��ÿ��ֻ�ƶ�һ��������ָ��ÿ���ƶ�������
��ʼʱ����ָ����λ�� head������ָ����λ�� head.next������һ����������ƶ��Ĺ����У���ָ�뷴����׷����ָ�룬
��˵��������Ϊ�������������ָ�뽫��������β����������Ϊ��������
*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
     int val;
     struct ListNode* next;
 };

bool hasCycle(struct ListNode* head) {
    if (head == NULL || head->next == NULL)
    {
        return false;
    }
    struct ListNode* slow = head;
    struct ListNode* fast = head->next;
    while (slow != fast)
    {
        if (fast == NULL || fast->next == NULL)
        {
            return false;
        }
        slow = slow->next;
        fast = fast->next->next;
    }
    return true;
}

int main()
{
    return 0;
}
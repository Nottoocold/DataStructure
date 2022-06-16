/*
���ڸ������������ź���ģ�����ظ���Ԫ���������г��ֵ�λ���������ģ��������ֻ��Ҫ���������һ�α������Ϳ���ɾ���ظ���Ԫ�ء�

ʹ������ָ��ֱ�ָʾ��ǰԪ�غ͵�ǰԪ�ص��¸�Ԫ�أ�ֻҪ����Ԫ����ͬ��ɾ����һ����Ȼ���һ��ָ������ƶ�
��������ָ�붼����ƶ�һλ��ֱ����һ��ָ��Ϊ��Ϊֹ
*/

#include <stdio.h>
#include <stdlib.h>

 struct ListNode {
      int val;
      struct ListNode *next;
  };

struct ListNode* deleteDuplicates(struct ListNode* head) {
    if (!head || !head->next)
    {
        return head;
    }
    struct ListNode* p1, * p2;
    p1 = head;
    p2 = head->next;
    while (p1 && p2)
    {
        if (p1->val == p2->val)
        {
            p1->next = p2->next;
            p2 = p2->next;
        }
        else
        {
            p1 = p1->next;
            p2 = p2->next;
        }
    }
    return head;
}

int main()
{
    return 0;
}
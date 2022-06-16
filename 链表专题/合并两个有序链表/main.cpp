/*
���ǿ����õ����ķ�����ʵ�������㷨���� l1 �� l2 �����ǿ�����ʱ���ж� l1 �� l2 ��һ�������ͷ�ڵ��ֵ��С��
����Сֵ�Ľڵ���ӵ�������һ���ڵ㱻��ӵ������֮�󣬽���Ӧ�����еĽڵ������һλ��

���ȣ������趨һ���ڱ��ڵ� prehead �����������������ǱȽ����׵ط��غϲ������������ά��һ�� prev ָ�룬
������Ҫ�����ǵ������� next ָ�롣Ȼ�������ظ����¹��̣�ֱ�� l1 ���� l2 ָ���� null ����� l1 ��ǰ�ڵ��ֵС�ڵ��� l2 ��
���ǾͰ� l1 ��ǰ�Ľڵ���� prev �ڵ�ĺ���ͬʱ�� l1 ָ��������һλ���������Ƕ� l2 ��ͬ���Ĳ������������ǽ���һ��Ԫ�ؽ����˺��棬
���Ƕ���Ҫ�� prev �����һλ��

��ѭ����ֹ��ʱ�� l1 �� l2 ������һ���Ƿǿյġ������������������������ģ����Բ����ĸ������Ƿǿյģ�
������������Ԫ�ض���ǰ���Ѿ��ϲ������е�����Ԫ�ض�Ҫ������ζ������ֻ��Ҫ�򵥵ؽ��ǿ�������ںϲ�����ĺ��棬�����غϲ�������
*/
//�ڱ�˼��
#include <stdio.h>
#include <stdlib.h>

 struct ListNode {
     int val;
     struct ListNode *next;
 };
 
struct ListNode* mergeTwoLists(struct ListNode* list1, struct ListNode* list2) {
    struct ListNode* head, * tail;
    head = (struct ListNode*)malloc(sizeof(struct ListNode));
    tail = head;
    while (list1 && list2)
    {
        if (list1->val <= list2->val)
        {
            tail->next = list1;
            list1 = list1->next;
        }
        else
        {
            tail->next = list2;
            list2 = list2->next;
        }
        tail = tail->next;
    }
    if (!list1)
    {
        tail->next = list2;
        tail = tail->next;
    }
    else
    {
        tail->next = list1;
        tail = tail->next;
    }
    return head->next;
}

int main()
{
    return 0;
}
/*
ʹ��˫ָ��ķ��������Խ��ռ临�ӶȽ��� O(1)

ֻ�е����� headA �� headB ����Ϊ��ʱ����������ſ����ཻ��
��������ж����� headA �� headB �Ƿ�Ϊ�գ��������������һ������Ϊ�գ�����������һ�����ཻ������null��

������ headA �� headB ����Ϊ��ʱ����������ָ�� pA �� pB����ʼʱ�ֱ�ָ�����������ͷ�ڵ� headA �� headB��
Ȼ������ָ�����α������������ÿ���ڵ㡣�����������£�

ÿ��������Ҫͬʱ����ָ�� pA �� pB��

���ָ�� pA ��Ϊ�գ���ָ�� pA �Ƶ���һ���ڵ㣻���ָ��pB ��Ϊ�գ���ָ�� pB �Ƶ���һ���ڵ㡣

���ָ�� pA Ϊ�գ���ָ�� pA �Ƶ����� headB ��ͷ�ڵ㣻���ָ�� pB Ϊ�գ���ָ�� pB �Ƶ����� headA ��ͷ�ڵ㡣

��ָ�� pA �� pB ָ��ͬһ���ڵ���߶�Ϊ��ʱ����������ָ��Ľڵ���� null��
*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
      int val;
      struct ListNode *next;
 };

struct ListNode* getIntersectionNode(struct ListNode* headA, struct ListNode* headB) {
    if (!headA || !headB)
    {
        return NULL;
    }
    struct ListNode* pa = headA;
    struct ListNode* pb = headB;
    while (pa != pb)
    {
        if (!pa)
        {
            pa = headB;
        }
        else
        {
            pa = pa->next;
        }
        if (!pb)
        {
            pb = headA;
        }
        else
        {
            pb = pb->next;
        }
    }
    return pa;
}

int main()
{
    return 0;
}
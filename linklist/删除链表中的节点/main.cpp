/*
 ɾ����������ĳ���ض��ڵ� ������ƺ���ʱ��Ҫע�⣬���޷����������ͷ�ڵ� head ��ֻ��ֱ�ӷ��� Ҫ��ɾ���Ľڵ� ��
*/

#include <stdio.h>
#include <stdlib.h>

 struct ListNode {
     int val;
    struct ListNode *next;
 };

void deleteNode(struct ListNode* node) {
    struct ListNode* pre = node;
    struct ListNode* nextp = pre->next;
    while (nextp->next != NULL)
    {
        pre->val = nextp->val;
        pre = nextp;
        nextp = nextp->next;
    }//ѭ������ʱ, pre �� nextp ָ��պ÷ֱ�ָ�� β�ڵ��ǰ�� �� β�ڵ�, ֻ����ѭ�����ٴν���ֵ����pre��ָ�ڵ�ָ������Ϊnull����
    pre->val = nextp->val;
    pre->next = NULL;
}

int main()
{
    return 0;
}
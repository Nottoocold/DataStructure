/*
��һ  �ȸ��Ʊ���һ������ֵ���Ƶ������У�Ȼ��������е�ֵ���бȽϣ��ж��ǲ��ǻ������� ʱ�临�ӶȺͿռ临�Ӷȶ���O(n)

����  ����ָ�� 

����ʹ�� O(n)O(n) ����ռ�ķ������Ǹı����롣

���ǿ��Խ�����ĺ�벿�ַ�ת���޸�����ṹ����Ȼ��ǰ�벿�ֺͺ�벿�ֽ��бȽϡ�
�Ƚ���ɺ�����Ӧ�ý�����ָ�ԭ������Ȼ����Ҫ�ָ�Ҳ��ͨ����������������ʹ�øú�������ͨ����ϣ������ṹ�����ġ�

�÷�����Ȼ���Խ��ռ临�ӶȽ��� O(1)�������ڲ��������£��÷���Ҳ��ȱ�㡣
�ڲ��������£���������ʱ��Ҫ���������̻߳���̶�����ķ��ʣ���Ϊ�ں���ִ�й���������ᱻ�޸ġ�

�������̿��Է�Ϊ����������裺

�ҵ�ǰ�벿�������β�ڵ㡣
��ת��벿������
�ж��Ƿ���ġ�
�ָ�����
���ؽ����

ִ�в���һ�����ǿ��Լ�������ڵ��������Ȼ����������ҵ�ǰ�벿�ֵ�β�ڵ㡣

����Ҳ����ʹ�ÿ���ָ����һ�α������ҵ�����ָ��һ����һ������ָ��һ��������������ָ��ͬʱ������
����ָ���ƶ��������ĩβʱ����ָ��ǡ�õ�������м䡣ͨ����ָ�뽫�����Ϊ�����֡�

���������������ڵ㣬���м�Ľڵ�Ӧ�ÿ�����ǰ�벿�֡�

���������ʹ�á�206. ��ת���������еĽ����������ת����ĺ�벿�֡�

�������Ƚ��������ֵ�ֵ������벿�ֵ���ĩβ��Ƚ���ɣ����Ժ��Լ�������е��м�ڵ㡣

�������벽���ʹ�õĺ�����ͬ���ٷ�תһ�λָ�������

*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
     int val;
     struct ListNode *next;
};

//��һ
/*
bool isPalindrome(struct ListNode* head) {
    if (head == NULL || head->next == NULL)
    {
        return true;
    }
    int a[100001], len = 0;
    struct ListNode* p = head;
    while (p != NULL)
    {
        a[len++] = p->val;
        p = p->next;
    }
    for (int i = 0, j = len - 1; i < j; ++i, --j)
    {
        if (a[i] != a[j])
        {
            return false;
        }
    }
    return true;
}
*/

//����

//��ת����
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

//�ҵ�����ǰ�벿�ֵı�β�ڵ�,����ָ�뷨
struct ListNode* endOfFirstHalf(struct ListNode* head)
{
    struct ListNode* slow = head;
    struct ListNode* fast = head;
    while (fast ->next != NULL && fast->next->next != NULL)
    {
        slow = slow->next;
        fast = fast->next->next;
    }
    return slow;
}

//�ж��Ƿ�Ϊ��������
bool isPalindrome(struct ListNode* head)
{
    if (head == NULL)
    {
        return true;
    }
    struct ListNode* fisrtHalfOfend = endOfFirstHalf(head);//ǰ�벿������β��ָ��
    struct ListNode* secondHalfOfend = reverseList(fisrtHalfOfend->next);//�õ���벿�ַ�ת�������ͷ����֮ǰ������β��
    struct ListNode* p1 = head;
    struct ListNode* p2 = secondHalfOfend;
    while (p1!=NULL&&p2!=NULL)
    {
        if (p1->val != p2->val)
        {
            return false;
        }
        p1 = p1->next;
        p2 = p2->next;
    }
    fisrtHalfOfend->next = reverseList(secondHalfOfend);
    return true;
}

int main()
{
    return 0;
}
/*
法一  先复制遍历一边链表将值复制到数组中，然后对数组中的值进行比较，判断是不是回文链表 时间复杂度和空间复杂度都是O(n)

法二  快慢指针 

避免使用 O(n)O(n) 额外空间的方法就是改变输入。

我们可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。
比较完成后我们应该将链表恢复原样。虽然不需要恢复也能通过测试用例，但是使用该函数的人通常不希望链表结构被更改。

该方法虽然可以将空间复杂度降到 O(1)，但是在并发环境下，该方法也有缺点。
在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，因为在函数执行过程中链表会被修改。

整个流程可以分为以下五个步骤：

找到前半部分链表的尾节点。
反转后半部分链表。
判断是否回文。
恢复链表。
返回结果。

执行步骤一，我们可以计算链表节点的数量，然后遍历链表找到前半部分的尾节点。

我们也可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。
当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。

若链表有奇数个节点，则中间的节点应该看作是前半部分。

步骤二可以使用「206. 反转链表」问题中的解决方法来反转链表的后半部分。

步骤三比较两个部分的值，当后半部分到达末尾则比较完成，可以忽略计数情况中的中间节点。

步骤四与步骤二使用的函数相同，再反转一次恢复链表本身。

*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
     int val;
     struct ListNode *next;
};

//法一
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

//法二

//反转链表
struct ListNode* reverseList(struct ListNode* head) {
    if (head == NULL || head->next == NULL)//头节点为空 或者 只有一个节点的链表
    {
        return head;
    }
    struct ListNode* pre, * cur, * next;
    pre = NULL;
    cur = head;
    next = NULL;
    while (cur != NULL)
    {
        next = cur->next;//拿去当前节点的下一个节点的地址
        cur->next = pre;//修改指针
        pre = cur;
        cur = next;
    }
    return pre;
}

//找到链表前半部分的表尾节点,快慢指针法
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

//判断是否为回文链表
bool isPalindrome(struct ListNode* head)
{
    if (head == NULL)
    {
        return true;
    }
    struct ListNode* fisrtHalfOfend = endOfFirstHalf(head);//前半部分链表尾巴指针
    struct ListNode* secondHalfOfend = reverseList(fisrtHalfOfend->next);//得到后半部分反转后的链表头，即之前的链表尾巴
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
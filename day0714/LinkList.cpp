/*
    876.链表的中间节点
 */
#include <stdlib.h>
typedef struct ListNode
{
    int val;
    struct ListNode *next;
} ListNode, *LinkList;

/*链表的中间节点，带头结点head的链表，若有两个中间节点，则返回第二个*/
//链表节点数在1-100
struct ListNode *middleNode(struct ListNode *head)
{
    ListNode *p = head;
    int count = 0; //记录节点数
    while (p)
    {
        count++;
        p = p->next;
    }
    int i = 0;
    p = head;
    while (i < count / 2)
    {
        i++;
        p = p->next;
    }
    return p;
}

//快满指针法，慢指针一次走一步，快指针一次走两步，当快指针到达表尾时，慢指针就是中间节点
ListNode *middleNode(LinkList head)
{
    ListNode *slow = head;
    ListNode *fast = head;
    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }
    return slow;
}

/*
    19.删除链表的倒数第N个节点
*/

//反转链表,返回新链表的头节点
ListNode *reverseList(LinkList head)
{
    if (!head)
        return nullptr;
    ListNode *pre = nullptr;  //记录前驱节点
    ListNode *cur = head;     //当前访问的节点
    ListNode *next = nullptr; //当前访问节点的下一个节点
    while (cur)
    {
        next = cur->next;
        cur->next = pre;
        pre = cur;
        cur = next;
    }
    return pre;
}

//删除倒数第n个节点,节点个数1-30
struct ListNode *removeNthFromEnd(struct ListNode *head, int n)
{
    if (head->next == nullptr && n == 1)
        return 0;
    if (n == 1) //删除的是倒数第一个节点
    {
        ListNode *p = head;
        while (p->next->next != nullptr)
        {
            p = p->next;
        }
        free(p->next);
        p->next = NULL;
    }
    else //否则
    {
        head = reverseList(head);
        int i = 0;
        ListNode *p = head;      //指向要删除的节点
        ListNode *pre = nullptr; //删除节点的前驱节点
        do
        {
            ++i;
            if (i == n)
                break;
            pre = p;
            p = p->next;
        } while (p);
        pre->next = p->next;
        free(p);
        p = NULL;
        return reverseList(head);
    }
    return head;
}